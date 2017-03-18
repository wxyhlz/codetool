<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
	<title>编写者</title>
	
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
<%--	<!-- OTHER FRAME... -->	--%>
	<!-- LAST CONFIG --> 
	<script type="text/javascript" src="<%=path%>/ui/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript">
		//服务路径
		var path="<%=path%>";	
		var easyui = datagridExtends();	//datagrid部分内容改写
		var isQuery = false; 	//查询操作 
		
		/*************************************************************************************
		 * DEFINE *
		 *************************************************************************************
		 * 1、PAGING DATAGRID DEFINE [{数据查询},{分页显示},{插件查询},{表格初始化}]
		 * 2、DATAGRID LINK DEFINE [{弹出新增窗体}]	 
		 * 3、DIALOG DEFINE [{初始化弹出窗体},{弹出新增窗体},{关闭增加窗体},{增加Action},{弹出修改窗体},{关闭修改窗体},{修改Action}]
		 * 4、BUTTON DEFINE [{根据条件查询ACTION}]	 
		 * 5、JSP JQUERY INITIALIZATION 
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
			var gridOptions = $("#tt").datagrid("options");
			if(vQueryType == "button"){
				//--按钮查询
				gridOptions.queryParams = vParams;
			}else if(vQueryType == "page"){
				//--分页插件查询
				gridOptions.queryParams = vParams;
			}else if(vQueryType == "delete"){
				//--删除插件查询
				var rows=$("#tt").datagrid("getRows");
				if((rows.length==1) && (vParams.pageNumber>1)){
					vParams.pageNumber = vParams.pageNumber-1;
				}
				gridOptions.queryParams = vParams;
			}			
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
				
				var url = path+'/projectAuthor/findProjectauthorByPage.action';
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
	       					$("#tt").datagrid("loadData", analyMsg.dataMap); 
	       					displayMsg();
	       				}
	       			},
					error: function (msg) {
	       				$.messager.alert('友情提醒',"查询操作Ajax响应出错，请检查。",'info');
	       				//alert(msg);
	                }
	       		}); 
			/*
			 * End Ajax 数据请求
			 *********************/ 
       	}  

		//--1.2、分页显示
		function displayMsg(){
			var rows=$("#tt").datagrid("getRows");
			if(rows.length>0){
				$('#tt').datagrid('getPager').pagination({displayMsg:'当前显示从{from}到{to}共{total}记录'});
			}else{
				$('#tt').datagrid('getPager').pagination({displayMsg:'没有记录'});
			}
		}
				
		//--1.3、插件查询
    	function pageSelect(){
    		var pg = $("#tt").datagrid("getPager");
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
			$('#tt').datagrid({
				title:'查询结果',
				iconCls:'', 
				fit: true,
				pageList:[5,10,20,50,100], 
				nowrap:false,
				striped: true,
				sigleSelect:true,			
				loadMsg:'',
				remoteSort:false,
				singleSelect:true,
				loaderror:function(){alert('dsdsds');},
				columns:[[  
					{title:'id',field:'id',hidden:true,width:'70',rowspan:2,align:'center'},
					{title:'项目名称',field:'prjname',width:'150',rowspan:2,align:'center'},
					{title:'昵称',field:'nickname',width:'150',rowspan:2,align:'center'},		
					{title:'姓名',field:'name',width:'150',rowspan:2,align:'center'},
					{title:'文件路径',field:'filepath',width:'260',rowspan:2,align:'center'},
					{title:'操作',field:'opt',width:'180',rowspan:2,align:'center',
						formatter:function(value,rowData,rowIndex){                          
					        var s="";                       
					        s = '<a href="javascript:void(0);" style="color:blue;" onclick="showUpdateDialog('+rowIndex+');">修改</a> ';  
					        s = s + '<a href="javascript:void(0);" style="color:blue;" onclick="deleteData('+rowIndex+');">删除</a> ';
					        return s;                    
					    }
					}  
				]],
				pagination:true,
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
		 * 2、DATAGRID LINK DEFINE [{弹出新增窗体}]	 
		 */ 		
		
		//--2.1、删除
		function deleteData(rowIndex){
			$.messager.confirm('友情提醒','请确认是否删除所选记录?',function(r){
				if(r){
					var rows = $('#tt').datagrid('getRows'); 
					var row = rows[rowIndex]; 
					var params = new Object();
					params.id = row.id;
					
					var url = path+'/projectAuthor/deleteProjectauthor.action';
					$.ajax({
		          		type:"POST",
		          		url:url,
		          		data:params,
		          		dataType:"json",		         		
		         		success:function callback(data){
		         			//eval("("+data+")")
		         			var analyMsg = data;
		         			if(!analyMsg.successed){
		         				$.messager.alert('友情提醒',analyMsg.errorMsg,'info');
		         			}else{
		         				$.messager.alert('友情提醒','删除成功!','info',function(){ 
		         					var gridOptions = $("#tt").datagrid("getPager").pagination("options");
		         					var gridParams = new Object(); 
		         					gridParams.pageNumber = gridOptions.pageNumber;
		         					gridParams.pageSize = gridOptions.pageSize
		         					getGridData("delete",gridParams)
									displayMsg();
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
			var elename = "nickname,name,filepath";
			var elehint = "昵称,姓名,文件路径";
			var hint = blankValidate(elename,elehint);
			if(hint!=""){
				$.messager.alert('友情提醒',hint+"不能为空。",'info');
				return;
			}
			
			var params = new Object();
			params.prjid = $("#project").combobox("getValue"); 
			params.nickname = $("#nickname").val();
			params.name = $("#name").val();
			params.filepath = $("#filepath").val();

			if(params.prjid==""){
				$.messager.alert('友情提醒',"请选择所属项目。",'info');
				return;
			}

			var url = path+'/projectAuthor/insertProjectauthor.action'; 
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
	 	function showUpdateDialog(rowIndex){
	 		$("#ff").form("clear");			
		    $('#ff').appendTo('#updateContent');
		    $('#ff').show();
		    $('#updateDialog').dialog('open'); 

		    var rows = $('#tt').datagrid('getRows'); 
			var row = rows[rowIndex]; 
		    $('#id').val(row.id);
		    $("#project").combobox("setValue",row.prjid); 
		    $('#nickname').val(row.nickname);
		    $('#name').val(row.name);
		    $('#filepath').val(row.filepath);  
		} 
		
		//--3.6、关闭修改窗体
		function closeUpdateDialog(){
			$("#updateDialog").dialog("close");
		} 
				
		//3.7、修改Action
		function btnUpdate(){
			var elename = "nickname,name";
			var elehint = "昵称,姓名";
			var hint = blankValidate(elename,elehint);
			if(hint!=""){
				$.messager.alert('友情提醒',hint+"不能为空。",'info');
				return;
			}
			
			var params = new Object();
			params.id = $('#id').val();
			params.prjid = $("#project").combobox("getValue"); 
			params.nickname = $("#nickname").val();
			params.name = $("#name").val();
			params.filepath = $("#filepath").val();

			var url = path+'/projectAuthor/updateProjectauthor.action'; 
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
		 * 4、BUTTON DEFINE [{根据条件查询ACTION}]	 
		 */ 
		 
		//--4.1、根据条件查询ACTION
		function btnQuery(first){    
			isQuery = true;

			//获取当前分页信息			
			//var gridOptions = $("#tt").datagrid("options");	
			var gridOptions = $("#tt").datagrid("getPager").pagination("options");  
            	
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
			params.nickname = $('#snickname').val();
			
			getGridData("button",params); 
		} 

		function initCombobox(){
			var params = new Object();
			var url = path+'/project/findProjectByMap.action';
			$.ajax({
        		type:"POST",
       			url:url,
        		data:params,
       			dataType:"json",
       			success:function callback(data){
       				var analyMsg = data;
       				if(analyMsg.successed){
       					$('#project').combobox({   
       						editable:false,
       					    data:data.dataMap.data,
       					    valueField:'id',
       					    textField:'name',
       						panelHeight:50  
       					}); 
        			}else{  
        				$.messager.alert('友情提醒',analyMsg.errorMsg,'info');
       				}
       			},
       		 	error : function(data,textstatus){  
	                $.messager.alert('友情提醒',data.responseText,'info');  
	            } 
       		});  			
		}
		/**************************************************************************************
		 *
		 * 5、JSP JQUERY INITIALIZATION 
		 */ 
		 
		$(function(){  
			setAjaxLoading();
			initGrid();
			initDialog();
			initCombobox();
			pageSelect();
			displayMsg();
			$('#tt').datagrid("loadData",[]);//datagrid居中 

			//限制长度
		 	//SetLength("#nickname",20);
		 	//SetLength("#name",20); 
		});   
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
							<td width=100 style="text-align:right;">昵称：</td>
							<td width=200><input class="input22" id="snickname" name="snickname" type="text" style="width:198px;"></input></td>
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
   <div id="addDialog" closed="true" title="新增" style="width:400px;height:220px; display: none;">
        <div id="addContent" align="center">
           <!-- <p align="center" style="font-size: 13px; margin-top: 5px; margin-bottom: 5px;">新增</p> -->  
        </div>
   </div>
   
   <!-- 弹出修改窗体 -->  
   <div id="updateDialog" closed="true" title="修改" style="width:400px;height:220px; display: none;">
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
					<td align=right>所属项目：</td>
					<td> 
						<input class="input22" type="text"  id="project"  name="project" style="width:260px"></input>
					</td>
					<td><span style="font-size:16px;color:red;">*</span></td>
				</tr> 
				<tr height=28>
					<td align=right>昵称：</td>
					<td> 
						<input class="input22" type="text"  id="nickname"  name="nickname" style="width:260px"></input>
					</td>
					<td><span style="font-size:16px;color:red;">*</span></td>
				</tr> 
				<tr height=28>
					<td align=right>姓名：</td>
					<td>
						<input class="input22" type="text" id="name" name="name" style="width:260px"></input>
					</td>
					<td><span style="font-size:16px;color:red;">*</span></td>
				</tr>
				<tr height=28>
					<td align=right>文件路径：</td>
					<td>
						<input class="input22" type="text" id="filepath" name="filepath" style="width:260px"></input>
					</td>
					<td><span style="font-size:16px;color:red;">*</span></td>
				</tr>
			</table> 
		</form>
	</div>    
</body>
</html>
