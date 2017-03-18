<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
	<title>部门</title>
		
	<!-- JQUERY AND EASYUI -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/ui/jquery-easyui-1.3.4/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/ui/jquery-easyui-1.3.4/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/ui/jquery-easyui-1.3.4/demo/demo.css">
	<script type="text/javascript" src="<%=path%>/ui/jquery/jquery-1.6.min.js"></script>	
	<script type="text/javascript" src="<%=path%>/ui/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
	
	<!-- FRAME JS -->
	<script type="text/javascript" src="<%=path %>/js/public/public.js"></script> 
	
	<!-- FRAME CSS... -->	
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm/comm.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm/element.css"/>
<%--	<!-- OTHER FRAME... -->	--%>
	<!-- LAST CONFIG --> 
	<script type="text/javascript" src="<%=path%>/ui/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript">
		//服务路径
		var path="<%=path%>";	
		//var easyui = datagridExtends();	//datagrid部分内容改写
		var isQuery = false; 	//查询操作 
		
		/*************************************************************************************
		 * DEFINE *
		 *************************************************************************************
		 * 1、PAGING DATAGRID DEFINE [{数据查询},{分页显示},{插件查询},{表格初始化}]
		 * 2、DATAGRID LINK DEFINE [{弹出新增窗体}]	 
		 * 3、DIALOG DEFINE [{初始化弹出窗体},{弹出新增窗体},{关闭增加窗体},{增加Action},{弹出修改窗体},{关闭修改窗体},{修改Action}]
		 * 4、COMBOBOX DEFINE[{author初始化}]          		 
		 * 5、BUTTON DEFINE [{根据条件查询ACTION}]	 
		 * 6、JSP JQUERY INITIALIZATION 
		 *************************************************************************************/

		/**************************************************************************************
		 *
		 * 1、PAGING DATAGRID DEFINE [{数据查询},{分页显示},{插件查询},{表格初始化}]	 
		 */
		 		
		//--1.1、数据查询
		// vQueryType — [{"button"},{"delete"},{"page"}]
		function getGridData(vQueryType,vParams) {
			/*********************
			 * Begin 分页插件定义
			 */						 
			var gridOptions = $("#tt").treegrid("options");
			if(vQueryType == "button"){
				//--按钮查询
				gridOptions.queryParams = vParams;
			}else if(vQueryType == "page"){
				//--分页插件查询
				gridOptions.queryParams = vParams;
			}else if(vQueryType == "delete"){
				//--删除插件查询
				var rows=$("#tt").treegrid("getRows");
				if((rows.length==1) && (vParams.pageNumber>1)){
					vParams.pageNumber = vParams.pageNumber-1;
				}
				gridOptions.queryParams = vParams;
			}			
			//--对datagrid赋值，解决第二页也从1开始排序			
			gridOptions.pageNumber = vParams.pageNumber;
			//--对datagrid赋值，解决第二页也从1开始排序			
			gridOptions.pageNumber = vParams.pageNumber;			
			/*
			 * End 分页插件定义
			 *********************/ 

			/*********************
			 * Begin Ajax 数据请求
			 */
			var params = new Object;
			params = gridOptions.queryParams; 
			
			var url = path+'/sysdepart/findSysdepartByPage.action';
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
       					$("#tt").treegrid("loadData", analyMsg.dataMap); 
       					displayMsg();
       				}
       			},
				error: function (data) {
       			 	$.messager.alert('友情提醒',data.responseText,'info');
       				//alert(msg);
                }
       		}); 
			/*
			 * End Ajax 数据请求
			 *********************/ 
       	}  

		//--1.2、分页显示
		function displayMsg(){
			var rows=$("#tt").treegrid("getData");			
			if(rows.length>0){
				$('#tt').treegrid('getPager').pagination({displayMsg:'当前显示从{from}到{to}共{total}记录'});
			}else{
				$('#tt').treegrid('getPager').pagination({displayMsg:'没有记录'});
			}
		}
				
		//--1.3、插件查询
    	function pageSelect(){              
    		var pg = $("#tt").treegrid("getPager");
		 	if(pg) {
            	$(pg).pagination({
	            	onSelectPage : function(pageNumber, pageSize) {
	                	if(isQuery==false){
		                	var params = new Object();
		                	params.pageNumber = pageNumber;
		                	params.pageSize = pageSize;
	                		getGridData("page",params);
		                }else{ 
		                	btnQuery(false);
			            }
	                 }
               	});
           	} 
        }

    	//--1.4、表格初始化
		function initGrid(){
			$('#tt').treegrid({
				title:'查询结果',
				iconCls:'', 
				fit: true,
				pageList:[5,10,20,50,100],  
				rownumbers: true,
				//animate:true,合并时，出现甩尾动作，未解决
				collapsible:true,
				//fitColumns:true, 
				//method: 'get',
				idField:'id',
				treeField:'name',
				columns:[[  
					{title:'id',field:'id',hidden:true,width:70,rowspan:2,align:'center'},
					{title:'pids',field:'pids',hidden:true,width:70,rowspan:2,align:'center'},
					{title:'编码',field:'code',hidden:true,width:70,rowspan:2,align:'center'},
					{title:'名称',field:'name',width:220,rowspan:2,align:'left'},		
					{title:'状态',field:'validstr',width:120,rowspan:2,align:'center',
						formatter:function(value,rowData,rowIndex){
							var s="";
							if(rowData.valid == "1"){
								s = "有效";
							}else if(rowData.valid == "0"){
								s = "无效";
							}
							return s;
						}
					}, 
					{title:'操作',field:'opt',width:150,rowspan:2,align:'center',
						formatter:function(value, rowData, rowIndex){
							var rowid = rowData.id;						
					        var s="";                       
					        s = '<a href="javascript:void(0);" style="color:blue;" onclick="showUpdateDialog('+"'"+rowid+"'"+');">修改</a> ';  
					        s = s + '<a href="javascript:void(0);" style="color:blue;" onclick="deleteData('+"'"+rowid+"'"+');">删除</a> ';
					        return s;                    
					    }
					}  
				]],
				pagination:true, 
				toolbar:'#toolbar' 
			});
		} 

		/**************************************************************************************
		 *
		 * 2、DATAGRID LINK DEFINE [{弹出新增窗体},{删除}]	 
		 */
		 
		//--2.1、删除
		function deleteData(rowid){
			$.messager.confirm('友情提醒','请确认是否删除所选记录?',function(r){
				if(r){
					var row = $('#tt').treegrid('find',rowid);					
					var params = new Object();
					params.id = row.id;
										
					var url = path+'/sysdepart/delete.action';
					$.ajax({
		          		type:"POST",
		          		data:params,
		         		url:url,
		         		success:function callback(data){
		         			var analyMsg = data;
		         			if(!analyMsg.successed){
		         				$.messager.alert('友情提醒',analyMsg.errorMsg,'info');
		         			}else{
		         				$.messager.alert('友情提醒','删除成功!','info',function(){ 
		         					var gridOptions = $("#tt").treegrid("getPager").pagination("options");
		         					var gridParams = new Object(); 
		         					gridParams.pageNumber = gridOptions.pageNumber;
		         					gridParams.pageSize = gridOptions.pageSize
		         					getGridData("delete",gridParams)
									displayMsg();
								});
		         			}				         		
		         		},error : function(data,textstatus){  
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
		} 

		//--3.2、弹出新增窗体
		function showAddDialog() {
			$("#ff").form("clear");			
		    $('#ff').appendTo('#addContent');
		    $('#ff').show();
		    $('#addDialog').dialog('open'); 
		  	//$("#rootNode").removeAttr("disabled");
			//$("#rootNode").attr("disabled",false); 
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
			
			//父节点判断
			var row = $('#tt').treegrid('getSelected');
			if((row==undefined)||(row==null)){
				 $.messager.alert('友情提醒',"请选择要新增的上级部门。",'info'); 
				return;
			}
			
			//赋值
			params.pid = row.id;
			params.code = row.code;
			params.pids = row.pids;  

			var url = path+'/sysdepart/insert.action'; 
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
       					displayMsg();
       				}
       			},
       		 	error : function(data,textstatus){  
	                $.messager.alert('友情提醒',data.responseText,'info');  
	            } 
       		});  
		} 

		//--3.5、弹出修改窗体
	 	function showUpdateDialog(rowid){
	 		$("#ff").form("clear");			
		    $('#ff').appendTo('#updateContent');
		    $('#ff').show();
		    $('#updateDialog').dialog('open'); 
 
		    var row = $('#tt').treegrid('find',rowid);			
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
			
			var url = path+'/sysdepart/update.action'; 
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
       					displayMsg();
       				}
       			},
       		 	error : function(data,textstatus){  
	                $.messager.alert('友情提醒',data.responseText,'info');  
	            } 
       		});  
		} 

		/**************************************************************************************
		 *
		 * 5、BUTTON DEFINE [{根据条件查询ACTION}]	 
		 */ 
		 
		//--5.1、根据条件查询ACTION
		function btnQuery(first){    
			isQuery = true;

			//获取当前分页信息			
			//var gridOptions = $("#tt").datagrid("options");	
			var gridOptions = $("#tt").treegrid("getPager").pagination("options");  
            	
			if(first){
				gridOptions.pageNumber = 1;
			}
			var pageNumber = gridOptions.pageNumber;
			var pageSize = gridOptions.pageSize;

            //查询条件
			//........................
			var params = new Object(); 
			params.pageNumber = pageNumber;
			params.pageSize = pageSize;
			//params.name = $('#sname').val();
			
			getGridData("button",params);  
		} 

		/**************************************************************************************
		 *
		 * 6、JSP JQUERY INITIALIZATION 
		 */ 
		 
		$(function(){  
			//setAjaxLoading(); 
			initGrid();
			initDialog();
			pageSelect();
			displayMsg();
			$('#tt').treegrid("loadData",[]);//datagrid居中 
		 	//SetLength("#tchCode",19); 
		});  
		/**************************************************************************************
		 * Business 
		 */
		function rnLogicCtrl(){
			if($("#rootNode").attr("checked")){
				$("#openType").attr("checked",false);
				$("#actionUrl").val("");
				$("#openType").attr("disabled",true);
				$("#actionUrl").attr("disabled",true);				
			}else{				
				$("#openType").attr("disabled",false);
				$("#actionUrl").attr("disabled",false);
			}
		} 
	</script>	 	
</head>
<body class="easyui-layout" style="margin:0px; padding:0px;" >
	<!-- Begin easyui-layout --> 
	<div region="center" style="padding: 5px;"  border="false" >
	<div class="easyui-layout" fit="true"  border="false">
		<div region="north"  title="查询条件" style="overflow:hidden;height:96px;" split="true"> 
			<!-- Begin Condition -->
			<div style="text-align:center;">
			<div class="ffCenter" style="width:300px;">
				<table style="border-collapse:collapse;margin-top:5px;" bordercolor=#99ccff cellspacing=0 rules=rows width=300 align=center border=1>
					<tbody>
						<tr style="height:26px;">
							<td width=100 style="text-align:right;">部门名称：</td>
							<td width=200><input class="input22" id="sname" name="sname" type="text" style="width:198px;"></input></td>
						</tr> 	
						<tr style="border-top:1px solid #99ccff;height:24px;">
							<td colspan="2" style="text-align:right;padding-right:2px;">
								<input class="anniu" type="button" onclick="javascript:btnQuery(true);this.blur();" value="查询">
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
	</div><!-- End easyui-layout --> 
	 
   	   	 
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
    
   <!-- 挂在datagrid下，toolbar功能按钮 --> 
   <div id="toolbar" style="padding: 1px; height: auto; display: none;">
        <div>        	
        	<a href="#" class="easyui-linkbutton" iconcls="icon-add" plain="true" onclick="showAddDialog();">新增</a>
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
