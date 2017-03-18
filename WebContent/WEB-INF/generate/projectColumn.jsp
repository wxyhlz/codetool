<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>代码明细</title>
 	
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
		var path="<%=path%>";
		var easyui = datagridExtends();		
		var dialogParams = window.dialogArguments; //父窗口传入的参数
		//var dialogParams = new Object();
		//dialogParams.id = "3d0b8f4552ed414097cf5278367767d6";
		//dialogParams.author = "cuiqf";	//编写者	
		//dialogParams.tblname = "sysuserrole";	//表名
		//dialogParams.tblcomment = "对应—人员角色";	//表注释
		//dialogParams.pkgmodelpath = "com.dotblue.system.userrole.model";//包路径
		//dialogParams.entityclass = "SysuserroleInfo";	//实体类
		
		var gl_params = new Object();
		
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
		 * INITIALIZATION PARAMETER
		 */
		function initParams(){ 
			$("#author").val(dialogParams.author);
			$("#tblname").val(dialogParams.tblname);
			$("#tblcomment").val(dialogParams.tblcomment);
			$("#pkgmodelpath").val(dialogParams.pkgmodelpath);
			$("#entityclass").val(dialogParams.entityclass);

			gl_params.id = dialogParams.id;
			gl_params.prjid = dialogParams.prjid;
			gl_params.tblname = dialogParams.tblname;
			gl_params.tblcomment = dialogParams.tblcomment;
		}
		
		/**************************************************************************************
		 *
		 * 1、DATAGRID DEFINE [{数据查询},{表格初始化}]	 
		 */

		//--1.1、数据查询 
		function getGridData(vParams) {  
			var params = new Object;
			params = vParams; 
			
			var url = path+'/generateproject/findProjectColumnByMap.action';
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
					{title:'列名',field:'colname',width:'100',rowspan:2,align:'left'},
					{title:'Java属性',field:'javacolname',width:'100',rowspan:2,align:'left'},
					{title:'列注释',field:'colcomment',width:'320',rowspan:2,align:'left'}, 
					{title:'列类型',field:'coltype',width:'100',rowspan:2,align:'center'},
					{title:'Java类型',field:'javatype',width:'100',rowspan:2,align:'center'},
					{title:'操作',field:'opt',width:'90',rowspan:2,align:'center',
						formatter:function(value,rowData,rowIndex){                             
                            var s;                    
                            s='<a href="#" style="color:blue;" onclick="deleteData('+rowIndex+');">删除</a> '; 
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
					
					var url = path+'/generateproject/deleteProjectColumn.action'; 
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
		 * 3、TOOLBAR BUTTON DEFINE [{刷新属性},{生成框架}]	 
		 */
		  
		//刷新属性
		function refreshProperty(){
			var url = path+'/generateproject/updateRefresh.action'; 
			$.ajax({
        		type:"POST",
       			url:url,
        		data:dialogParams,
       			dataType:"json",
       			success:function callback(data){
       				var analyMsg = data;
       				if(!analyMsg.successed){
       					$.messager.alert('友情提醒',analyMsg.errorMsg,'info'); 
        			}else{
        				getGridData(gl_params);
       				}
       			},
       		 	error : function(data,textstatus){  
	                $.messager.alert('友情提醒',data.responseText,'info');  
	            }
       		}); 
		}
		
		//生成框架
		function generateCode() {
			//control file
			var rows = $('#tt').datagrid('getRows');
			if(rows.length<=0){
				$.messager.alert('友情提醒','请先刷新数据。','info');
				return;
			}
			$.messager.confirm('友情提醒','请确认是否动态生成代码?',function(r){
				if(r){					 
					var param = new Object(); 
		
					param.id = gl_params.id
					param.author = $("#author").val();
					param.tblname = $("#tblname").val();
					param.tblcomment = $("#tblcomment").val();
					param.pkgmodelpath = $("#pkgmodelpath").val();
					param.entityclass = $("#entityclass").val();
					 
					//文件控制
					param.vcontroller = $("#controll").is(':checked');
					param.vfacade = $("#facade").is(':checked');
					param.vservice = $("#service").is(':checked');
					param.vdao = $("#dao").is(':checked');
					param.ventity = $("#entity").is(':checked');
					
					//方法控制
					param.vinsert = $("#insert").is(':checked');
					param.vdelete = $("#delete").is(':checked');
					param.vupdate = $("#update").is(':checked');
					param.vinvalid = $("#invalid").is(':checked');					
					param.insertBatch = $("#insertBatch").is(':checked');
					param.deleteBatch = $("#deleteBatch").is(':checked');
					param.updateBatch = $("#updateBatch").is(':checked');
					param.selectEntity = $("#selectEntity").is(':checked');
					param.selectByPage = $("#selectByPage").is(':checked');
					param.selectList = $("#selectByList").is(':checked');
					param.selectByMap = $("#selectByMap").is(':checked');
					
					//所属项目
					param.prjid = gl_params.prjid;
			  			  		
					var url = path+'/generateproject/updateGenerateFile.action';  
					$.ajax({
		        		type:"POST",
		       			url:url,
		        		data:param,
		       			dataType:"json",
		       			success:function callback(data){
		       				var analyMsg = data;
		       				if(!analyMsg.successed){
		        				$.messager.alert('友情提醒',analyMsg.errors[0],'info',function(){});
		        			}else{
		        				$.messager.alert('友情提醒','成功生成，请刷新项目','info',function(){});
		       				}
		       			}
		       		}); 
				} 
			});
		}
		
		/**************************************************************************************
		 *
		 * 5、window close 
		 */ 
		function closeDialogGen(){
			window.close();
		}
 
		/**************************************************************************************
		 *
		 * 6、JSP JQUERY INITIALIZATION 
		 */ 
		$(function(){  
			//setAjaxLoading();
			initGrid(); 
			initParams();	
			$('#tt').datagrid("loadData",[]);//datagrid居中 		
			//$("#fun").val("[{insert},{delete},{update},{selectEntites},{selectPage},{selectSigle}]");
			getGridData(gl_params);
		});   
	</script>	 
	<style type="text/css">
		.colTitle{
			text-align:right;
			width:110px;
			border-right:1p solid #99ccff;
		}
		.colValue{
			border-right:1p solid #99ccff;
			width:160px;
		}
		.tdinput{
			margin:2px; 
			border:0px;
		}	
		#divul{float:left;width:640px;}	
		#divul li{float:left;width:128px;}
		#divu2{float:left;width:640px;}
		#divu2 li{float:left;width:160px;}
	</style>	
</head>
<body class="easyui-layout" style="margin:0px; padding:0px;" >
	<!-- Begin easyui-layout --> 
	<div region="center" style="padding: 5px;"  border="false" >
	<div class="easyui-layout" fit="true"  border="false">
		<div region="north"  title="数据表信息" style="overflow:hidden;height:240px;" split="true"> 
			<!-- Begin Condition -->
			<div style="text-align:center;">
			<div class="ffCenter">
				<table style="border-collapse:collapse;margin-top:5px;" bordercolor=#99ccff cellspacing=0 rules=rows width=860 align=center border=1>
					<tbody>
							
						<tr style="height:26px;">
							<td class="colTitle">编写者：</td> 
							<td class="colValue"><input class="input22 tdinput" id="author" name="author" type="text" style="width:178px;"></input></td>
							<td class="colTitle">表名：</td>
							<td class="colValue"><input class="input22 tdinput" id="tblname" name="tblname" type="text" style="width:178px;"></input></td>
							<td class="colTitle">注释：</td>
							<td width=160><input class="input22 tdinput" id="tblcomment" name="tblcomment" type="text" style="width:298px;"></input></td>
						</tr> 
						<tr style="height:26px;">
							<td class="colTitle">实体：</td>
							<td class="colValue"><input class="input22 tdinput" id="entityclass" name="entityclass" type="text" style="width:178px;"></input></td>
							<td class="colTitle">路径：</td>
							<td width=420 colspan="3"><input class="input22 tdinput" id="pkgmodelpath" name="pkgmodelpath" type="text" style="width:298px;"></input></td>
						</tr>		
						<tr style="height:7px;">
							<td colspan="6">&nbsp;</td>
						</tr>
						<tr style="height:26px;">							
							<td class="colTitle">文件：</td>
							<td width=680 colspan="5"> 
								<ul id="divul">
						      		<li><input type="checkbox" id="entity" checked="checked"/>Entity{文件}</li>
						      		<li><input type="checkbox" id="dao" checked="checked"/>Dao{文件}</li>
						      		<li><input type="checkbox" id="service" checked="checked"/>Service{文件}</li>
						      		<li><input type="checkbox" id="facade" checked="checked"/>Facade{文件}</li>
						      		<li><input type="checkbox" id="controll" checked="checked"/>Controller{文件}</li>
						      	</ul>	
							</td>
						</tr>
						<tr style="height:7px;">
							<td colspan="6">&nbsp;</td>
						</tr> 				 
						<tr style="height:78px;">
							<td class="colTitle">方法：</td>
							<td width=680 colspan="5">
								<ul id="divu2">
						      		<li><input type="checkbox" id="insert" checked="checked"/>新增{add*}</li>
									<!-- <li><input type="checkbox" id="delete" checked="checked"/>删除{delete*}</li>-->
									<li><input type="checkbox" id="update" checked="checked"/>修改{update*}</li>
									<li><input type="checkbox" id="invalid" checked="checked"/>作废{invalid*}</li>
									<li><input type="checkbox" id="selectByPage" checked="checked"/>分页查询{select*ByPage}</li>
									<li><input type="checkbox" id="selectEntity" checked="checked"/>实体查询{select*Entity}</li>
									<li><input type="checkbox" id="selectByList"/>List查询{select*ByList}</li>
									<li><input type="checkbox" id="selectByMap"/>Map查询{select*ByMap}</li>
									<li><input type="checkbox" id="insertBatch"/>批量新增{add*Batch}</li>
									<!--<li><input type="checkbox" id="deleteBatch"/>批量删除{delete*Batch}</li>-->
									<li><input type="checkbox" id="updateBatch"/>批量修改{update*Batch}</li>
					        	</ul>
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
        	<a href="#" class="easyui-linkbutton" iconcls="icon-reload" plain="true" onclick="refreshProperty();">刷新</a>
        	<a href="#" class="easyui-linkbutton" iconcls="icon-add" plain="true" onclick="generateCode();">生成</a>
        	<a href="#" class="easyui-linkbutton" iconcls="icon-back" plain="true" onclick="closeDialogGen();">退出</a>
        </div>
	</div> 
</body>
</html>
