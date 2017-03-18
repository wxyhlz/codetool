<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>角色</title> 
  	
 	<!-- JQUERY AND EASYUI -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/ui/jquery-easyui-1.3.4/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/ui/jquery-easyui-1.3.4/themes/icon.css"/>
	<script type="text/javascript" src="<%=path%>/ui/jquery/jquery-1.6.min.js"></script>	
	<script type="text/javascript" src="<%=path%>/ui/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
	
	<!-- FRAME JS -->
	<script type="text/javascript" src="<%=path %>/js/public/public.js"></script>
	<script type="text/javascript" src="<%=path %>/js/public/easyui.js"></script>
	
	<!-- FRAME CSS... -->	
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm/comm.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm/element.css"/>
	
	<!-- OTHER FRAME -->
	<link rel="stylesheet" href="<%=path%>/ui/zTree v3.5.15/css/zTreeStyle/zTreeStyle.css" type="text/css">	
	<script type="text/javascript" src="<%=path%>/ui/zTree v3.5.15/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=path%>/ui/zTree v3.5.15/js/jquery.ztree.excheck-3.5.js"></script>
	
	
	<!-- LAST CONFIG --> 
	<script type="text/javascript" src="<%=path%>/ui/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var path="<%=path%>";
	var easyui = datagridExtends();
	var gl_roleId;
		 
	/**************************************************************************************
	 *
	 * 树节点定义	 
	 */
	var setting = {
		check: {enable: true},
		data:{
			simpleData: {
				enable: true,
				idKey:"id",
				pIdKey:"pid"			
			}
		}
	};

	/*************************************************************************************
	 * DEFINE *
	 *************************************************************************************
	 * 1、DATAGRID DEFINE [{数据查询},{表格初始化}]
	 * 2、DATAGRID LINK DEFINE [{删除}]	 	 
	 * 3、TOOLBAR BUTTON DEFINE [{更新属性},{生成框架}]  
	 * 4、JSP JQUERY INITIALIZATION 
	 *************************************************************************************/
	  
	/**************************************************************************************
	 *
	 * 1、DATAGRID DEFINE [{数据查询},{表格初始化}]	 
	 */

	//--1.1、数据查询 
	function getGridData(vParams) {  
		var params = new Object;
		params = vParams; 
		
		var url = path+'/sysrole/findSysroleByMap.action';
		$.ajax({
       		type:"POST",
      			url:url,
       		data:params,
      			dataType:"json",
      			success:function callback(data){
      				var analyMsg = data;
      				if(!analyMsg.successed){ 
       				$.messager.alert('友情提醒',analyMsg.errorMsg,'info');
       			}else{ 
      					$("#tt").datagrid("loadData", analyMsg.dataMap.data); 
      				}
      			},
			error: function (msg) {
      				$.messager.alert('友情提醒',"查询操作Ajax响应出错，请检查。",'info'); 
               }
      		});  
      	}  

	//--1.2、表格初始化
	function initGrid(){
		$('#tt').datagrid({
			title:'查询结果',
			iconCls:'', 
			fit: true,
			pageList:[10,20,50,100], 
			nowrap:false,
			striped: true,
			sigleSelect:true,			
			loadMsg:'',
			remoteSort:false,
			singleSelect:true,
			loaderror:function(){alert('dsdsds');},
			columns:[[    						
				{title:'名称',field:'name',width:'220',rowspan:2,align:'left'},
				{title:'操作',field:'opt',width:'180',rowspan:2,align:'center',
					formatter:function(value,rowData,rowIndex){                             
                           var s;                    
                           s = '<a href="#" style="color:blue;" onclick="showAssignModuleDialog('+rowIndex+');">分配模块</a> ';
                           s = s + '<a href="#" style="color:blue;" onclick="showUpdateDialog('+rowIndex+');">修改</a> ';
                           s = s + '<a href="#" style="color:blue;" onclick="deleteData('+rowIndex+');">删除</a> '; 
                           return s;                    
                       }
				}  
			]],
			pagination:false,
			rownumbers:true,
			toolbar:'#toolbar',
			onLoadSuccess: function (data) {
	    		easyui.datagrid.headcenter();
	    		//easyui.datagrid.fixRownumber(); 
	        }	
		}); 
	} 
	
	/**************************************************************************************
	 *
	 * 2、DATAGRID LINK DEFINE [{删除}]	 
	 */
	  	
	//删除
	function deleteData(rowIndex){
		$.messager.confirm('友情提醒','请确认是否删除?',function(r){
			if(r){
				var rows = $('#tt').datagrid('getRows'); 
				var row = rows[rowIndex]; 
				var params = new Object();
				params.id = row.id;
				
				var url = path+'/sysrole/delete.action'; 
				$.ajax({
	          		type:"POST",		          		
	         		url:url,
	         		data:param,
	         		success:function callback(data){
	         			var analyMsg = data;
	         			if(!analyMsg.successed){
	         				$.messager.alert('友情提醒',analyMsg.errorMsg,'info');  
	         			}else{
	         				$.messager.alert('友情提醒','删除成功!','info',function(){ 
	         					getGridData(gl_params);
							});
	         			}				         		
	         		},
	       		 	error : function(data,textstatus){  
		                $.messager.alert('友情提醒',data.responseText,'info');  
		            }
	        	});
        	} 
		});
	}
	 
	 /**************************************************************************************
	 *
	 * 3、DIALOG DEFINE [{初始化弹出窗体},{弹出新增窗体},{弹出修改窗体},{关闭增加窗体},{关闭修改窗体},{增加Action},{修改Action}]	 
	 */ 
	 
	//--3.1、初始化弹出窗体
	function initDialog() {
		//新增
		$("#addDialog").show();  
	    $("#addDialog").dialog({
		    iconCls: "icon-add",
	        modal: true,	//模态显示
	        closable:false,	//控制右上角的红叉
	        buttons: [{text: "确定",iconCls: "icon-ok",handler: btnInsert}, 
	  		        {text: "取消",iconCls: "icon-cancel",handler: closeAddDialog}
		        ]
	  	}); 

	    //修改
	    $("#updateDialog").show();  
	    $("#updateDialog").dialog({
		    iconCls: "icon-edit",
	        modal: true,
	        closable:false,	//控制右上角的红叉
	        buttons: [{text: "确定",iconCls: "icon-ok",handler: function(){btnUpdate();}}, 
	  		        {text: "取消",iconCls: "icon-cancel",handler: function(){closeUpdateDialog();}}
		        ]
	    });

	    //模块分配
	    $("#assignModuleDialog").show();  
	    $("#assignModuleDialog").dialog({
		    iconCls: "icon-edit",
	        modal: true,
	        closable:false,	//控制右上角的红叉
	        buttons: [{text: "确定",iconCls: "icon-ok",handler: function(){linkAssignModule();}}, 
	  		        {text: "取消",iconCls: "icon-cancel",handler: function(){closeAssignModuleDialog();}}
		        ]
	    });
	} 

	//--3.2、弹出新增窗体
	function showAddDialog() {
		$("#ff").form("clear");			
	    $('#ff').appendTo('#addContent');
	    $('#ff').show();
	    $('#addDialog').dialog('open');  
	}

	//--3.3、关闭增加窗体
	function closeAddDialog(){
		$("#addDialog").dialog("close"); 
	}
	
	//--3.4、增加Action
	function btnInsert(){ 			
		var params = new Object();
		params.name = $("#name").val();
	 
		//为空判断
		var elename = "name";
		var elehint = "名称";
		var hint = blankValidate(elename,elehint);
		if(hint!=""){
			$.messager.alert('友情提醒',hint+"不能为空。",'info');
			return;
		} 
		
		//赋值  
		params.name = $("#name").val();  

		var url = path+'/sysrole/insert.action'; 
		$.ajax({
       		type:"POST",
      			url:url,
       		data:params,
      			dataType:"json",
      			success:function callback(data){
      				var analyMsg = data;
      				if(!analyMsg.successed){
      					$.messager.alert('友情提醒',analyMsg.errorMsg,'info');
       			}else{ 
      					closeAddDialog();
      					btnQuery(true);  
      				}
      			},
      		 	error : function(data,textstatus){  
                $.messager.alert('友情提醒',data.responseText,'info');  
            } 
      		});  
	} 

	//--3.5、弹出修改窗体
 	function showUpdateDialog(rowIndex){
 		$("#ff").form("clear");			
	    $('#ff').appendTo('#updateContent');
	    $('#ff').show();
	    $('#updateDialog').dialog('open'); 

	    var rows = $('#tt').datagrid('getRows'); 
		var row = rows[rowIndex];		
					
	    $('#id').val(row.id);
	    $('#name').val(row.name); 
	} 
	
	//--3.6、关闭修改窗体
	function closeUpdateDialog(){
		$("#updateDialog").dialog("close");
	} 
			
	//3.7、修改Action
	function btnUpdate(){ 
		//为空判断
		var elename = "name";
		var elehint = "名称";
		var hint = blankValidate(elename,elehint);
		if(hint!=""){
			$.messager.alert('友情提醒',hint+"不能为空。",'info');
			return;
		} 
		
		var params = new Object();
		params.id = $('#id').val(); 
		params.name = $("#name").val(); 
		
		var url = path+'/sysrole/update.action'; 
		$.ajax({
       		type:"POST",
      		url:url,
       		data:params,
      		dataType:"json",
      		success:function callback(data){
      			var analyMsg = data;
      			if(!analyMsg.successed){
      				$.messager.alert('友情提醒',analyMsg.errorMsg,'info');
       			}else{ 
      				closeUpdateDialog();
      				btnQuery(true); 
      			}
      		},
      		error : function(data,textstatus){  
            	$.messager.alert('友情提醒',data.responseText,'info');  
            } 
      	});  
	} 

	//--3.8、弹出分配模块窗体
 	function showAssignModuleDialog(rowIndex){		
 		var rows = $('#tt').datagrid('getRows'); 
		var row = rows[rowIndex];			
		$('#assignModuleDialog').dialog('open'); 

		gl_roleId = row.id;
       	var param = new Object();
       	param.id = row.id;
       	var url = path + "/sysrole/findAllModulesAndMarkCheckedByRole.action";
	    $.ajax({
       		type:"POST",
      		url:url,
       		data:param,
      		dataType:"json",
      		success:function callback(data){
      			var analyMsg = data;
      			if(!analyMsg.successed){
      				$.messager.alert('友情提醒',analyMsg.errorMsg,'info');
       			}else{ 
       				$.fn.zTree.init($("#treeDemo"), setting, analyMsg.dataMap.data);
       				//Y：勾选，N：反选；p：关联父节点，s：关联子节点
					setting.check.chkboxType = { "Y" : "ps", "N" : "ps" }; 
      			}
      		},
     		error : function(data,textstatus){  
               	$.messager.alert('友情提醒',data.responseText,'info');  
           	} 
      	});   
	} 
	
	//--3.9、关闭修改窗体
	function closeAssignModuleDialog(){
		$("#assignModuleDialog").dialog("close");
	} 

	//--3.10、分配模块
	function linkAssignModule(){ 		
		//获取选择的模块
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var checkNodes = zTree.getCheckedNodes(true);
		
		var moduleIds="";
		for(var i=0; i<checkNodes.length; i++){
			if(moduleIds==""){
				moduleIds=checkNodes[i].id;
			}else{
				moduleIds = moduleIds+','+checkNodes[i].id;
			}
		} 

		var param = new Object();
		param.roleId = gl_roleId;
		param.moduleIds = moduleIds;

		var url = path + "/sysrole/updateModulesToRole.action";	
		//数据提交
		$.ajax({
         	type:"POST",
        	url:url, 
        	data:param,
        	dataType:"json",
        	success:function callback(data){
         		var analyMsg = data;
         		if(!analyMsg.successed){
	   				$.messager.alert('友情提醒',analyMsg.errorMsg,'info',function(){});
		   		}else{ 					   			 
					$.messager.alert('友情提醒',"模块分配成功。",'info',function(){
						closeAssignModuleDialog();
					});
				} 
         	},
     		error : function(data,textstatus){  
               	$.messager.alert('友情提醒',data.responseText,'info');  
           	}
        });
	}


	/**************************************************************************************
	 *
	 * 3、TOOLBAR BUTTON DEFINE [{刷新属性},{生成框架}]	 
	 */
	 
	
	//查询
	function btnQuery() { 
		
		var param = new Object();
		getGridData(param); 
	} 
	
	/**************************************************************************************
	 *
	 * 5、JSP JQUERY INITIALIZATION 
	 */ 
	$(function(){  
		//setAjaxLoading();
		initGrid();  
		$('#tt').datagrid("loadData",[]);//datagrid居中 		
		initDialog();   
	});   
</script>	 
<style type="text/css">
	.colTitle{
		text-align:right;
		width:80px; 
	}
	.colValue{ 
		width:180px;
	} 
</style>	
</head>
<body class="easyui-layout" style="margin:0px; padding:0px;" >
	<!-- Begin easyui-layout --> 
	<div region="center" style="padding: 5px;"  border="false" >
	<div class="easyui-layout" fit="true"  border="false">		
		<div region="north"  title="查询条件" style="overflow:hidden;height:97px;" split="true"> 
			<!-- Begin Condition -->
			<div style="text-align:center;">
			<div class="ffCenter" style="width:260px;">
				<table style="border-collapse:collapse;margin-top:5px;" bordercolor=#99ccff cellspacing=0 rules=rows width=260 align=center border=1>
					<tbody>
						<tr style="height:26px;">
							<td class="colTitle">角色名称：</td>
							<td class="colValue"><input class="input22 tdinput" id="sname" name="sname" type="text" style="width:178px;"></input></td>								
						</tr> 	 						 
						<tr style="border-top:1px solid #99ccff;height:24px;">
							<td colspan="2" style="text-align:right;padding-right:2px;">
								<input class="anniu" type="button" onclick="javascript:btnQuery();this.blur();" value="查询">
								<input class="anniu" type="button" onclick="btnReset();this.blur();" value="重置">
							</td>
						</tr>
					</tbody>
				</table>
			</div>	
			</div> 
			<!-- End Condition --> 
		</div>   			 				
		<div region="center" style="overflow:hidden;" border="false">
	       <table id="tt" ></table> 
	    </div>
	</div>
	</div>
	<!-- End easyui-layout --> 
	
	<!-- 挂在datagrid下，toolbar功能按钮 --> 
	<div id="toolbar" style="padding: 1px; height: auto; display: none;">
    	<div>
    		<a href="#" class="easyui-linkbutton" iconcls="icon-add" plain="true" onclick="showAddDialog();">新增</a>
        </div>
	</div>
	
	<!-- 弹出新增窗体 -->  
   <div id="addDialog" closed="true" title="新增" style="width:400px;height:200px; display: none;">
        <div id="addContent" align="center">
           <!-- <p align="center" style="font-size: 13px; margin-top: 5px; margin-bottom: 5px;">新增</p> -->  
        </div>
   </div>
   
   <!-- 弹出修改窗体 -->  
   <div id="updateDialog" closed="true" title="修改" style="width:400px;height:200px; display: none;">
        <div id="updateContent" align="center">
           <!-- <p align="center" style="font-size: 13px; margin-top: 5px; margin-bottom: 5px;">新增</p> -->  
        </div>
   </div>
   
   <!-- link atag 模块分配 -->
   <div id="assignModuleDialog" closed="true" title="模块分配" style="width:320px;height:380px; display: none;">	        
		<div id="p" align="center"><!-- title="" class="easyui-panel" iconCls="icon-save" style="width:315px;height:250px;" -->
	    	<input class="input22" type="text"  id="roleId" style="display:none"></input>
	    	<ul id="treeDemo" class="ztree" style="width:250px;height:180px;"></ul>
	    </div> 
	</div>
	
	<!-- form元素定义 -->
    <div style="display:none">
		<form id="ff" method="post">
			<table>  
				<tr>
					<td></td>
					<td><span style="padding-left:0px;font-size:12px;color:blue;"><span style="font-size:16px;color:red;">*</span>号为必输或必选项</span></td>
				</tr>
				<tr style="display:none;">
					<td></td>
					<td> 
						<input class="input22" type="text"  id="id"  name="id" style="width:260px"></input>
					</td>
					<td></td>
				</tr>
				<tr height=28>
					<td align=right>名称：</td>
					<td>
						<input class="input22" type="text" id="name" name="name" validType="length[0,99]"  style="width:260px"></input>
					</td>
					<td><span style="font-size:16px;color:red;">*</span></td>
				</tr> 
			</table> 
		</form>
	</div>    
</body>
</html>
