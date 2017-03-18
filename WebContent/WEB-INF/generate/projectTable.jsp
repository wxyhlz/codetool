<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
	<title>代码生成器</title>
	
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
			//--对datagrid赋值，解决第二页也从1开始排序			
			gridOptions.pageSize = vParams.pageSize;			
			/*
			 * End 分页插件定义
			 *********************/ 

			/*********************
			 * Begin Ajax 数据请求
			 */
				var params = new Object;
				params = gridOptions.queryParams; 
				
				var url = path+'/generateproject/findProjectTableByPage.action';
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
					{title:'编写者',field:'author',width:'70',rowspan:2,align:'center'},	
					{title:'项目名称',field:'prjname',width:'105',rowspan:2,align:'center'},		
					{title:'表名',field:'tblname',width:'105',rowspan:2,align:'center'},						
					{title:'表注释',field:'tblcomment',width:'200',rowspan:2,align:'center'}, 
					{title:'包',field:'pkgmodelpath',width:'245',rowspan:2,align:'center'},
					{title:'实体类',field:'entityclass',width:'110',rowspan:2,align:'center'},  
					{title:'登记时间',field:'regtime',width:'160',rowspan:2,align:'center'},
					{title:'操作',field:'opt',width:'110',rowspan:2,align:'center',
						formatter:function(value,rowData,rowIndex){                          
					        var s="";                       
					        s = '<a href="javascript:void(0);" style="color:blue;" onclick="showUpdateDialog('+rowIndex+');">修改</a> ';  
					        s = s + '<a href="javascript:void(0);" style="color:blue;" onclick="deleteData('+rowIndex+');">删除</a> ';
					        s = s + '<a href="javascript:void(0);" style="color:blue;" onclick="detailData('+rowIndex+');">明细</a> ';
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
		 * 2、DATAGRID LINK DEFINE [{弹出新增窗体},{删除}]	 
		 */ 			
		 //屏幕中间
		 function ffDigCenter(owidth,oheight){
			var bleft = ($(window).width() - owidth) / 2;
			var btop = ($(window).height() - oheight); 
			
			// 参数
			return p = 'dialogLeft:' + bleft + 'px;dialogTop:' + btop + 'px;'; 
		}
	 	//--2.1、明细数据
	 	function detailData(rowIndex){ 
	 		var rows = $('#tt').datagrid('getRows'); 
			var row = rows[rowIndex]; 
			
	 		var params = new Object();
	 		params.id = row.id;
	 		params.prjid = row.prjid;
	 		params.author = row.author;	//编写者	
	 		params.tblname = row.tblname;	//表名
	 		params.tblcomment = row.tblcomment;	//表注释
	 		params.pkgmodelpath = row.pkgmodelpath;//包路径
	 		params.entityclass = row.entityclass;	//实体类

			var url = path+'/generateproject/projectColumnForm.action';
	 		//弹出窗体
	 		var p = ffDigCenter(1000,600);
			window.showModalDialog(url,params,"dialogwidth:1000px;dialogheight:600px;help=no;status=no;center=yes;edge=sunken;resizable=yes;Minimize=no;Maximize=no;"+p);

			//if(returnValue == undefined){
			//	//最后别忘记去删除Session
			//	$.ajax({
	        //		type:"POST",
	       	//		url:"delSessionAttach.action",
	       	//		dataType:"json",
	       	//		success:function callback(data){ 
	       	//		}
	       	//	});	   
			//}
		} 		

		//--2.2、删除
		function deleteData(rowIndex){
			$.messager.confirm('友情提醒','请确认是否删除所选记录?',function(r){
				if(r){
					var rows = $('#tt').datagrid('getRows'); 
					var row = rows[rowIndex]; 
					var params = new Object();
					params.id = row.id;
					
					var url = path+'/generateproject/deleteProjectTable.action';
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
		         				$.messager.alert('友情提醒','删除成功!','info',function(){ 
		         					var gridOptions = $("#tt").datagrid("getPager").pagination("options");
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
		}

		//--3.3、关闭增加窗体
		function closeAddDialog(){
			$("#addDialog").dialog("close"); 
		}
		
		//--3.4、增加Action
		function btnInsert(){ 			
			var params = new Object();
			params.prjid = $("#project").combobox("getValue");
			params.author = $("#author").combobox("getValue");
			params.tblname = $("#tblname").val();
			params.pkgmodelpath = $("#pkgmodelpath").val();
			params.entityclass = $("#entityclass").val();
			
			var jdgEntity = params.entityclass.substr(-6);
			if(jdgEntity!='Entity'){
				$.messager.alert('友情提醒','实体类结尾必须“Entity”','info');  
				return ;
			}

			var url = path+'/generateproject/insertProjectTable.action'; 
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
       					//$("#tt").datagrid("loadData", analyMsg.data);
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
		    getAuthor(row.prjid);
		    $('#author').combobox("setValue",row.author);
		    $('#tblname').val(row.tblname);
		    $('#pkgmodelpath').val(row.pkgmodelpath);
		    $('#entityclass').val(row.entityclass); 
		} 
		
		//--3.6、关闭修改窗体
		function closeUpdateDialog(){
			$("#updateDialog").dialog("close");
		} 
				
		//3.7、修改Action
		function btnUpdate(){
			var params = new Object();
			params.id = $('#id').val();
			params.prjid = $("#project").combobox("getValue");
			params.author = $("#author").combobox("getValue");
			params.tblname = $("#tblname").val();
			params.pkgmodelpath = $("#pkgmodelpath").val();
			params.entityclass = $("#entityclass").val();
			var jdgEntity = params.entityclass.substr(-6);
			if(jdgEntity!='Entity'){
				$.messager.alert('友情提醒','实体类结尾必须“Entity”','info');  
				return ;
			}

			var url = path+'/generateproject/updateProjectTable.action'; 
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
       					//$("#tt").datagrid("loadData", analyMsg.data);
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
		 * 4、COMBOBOX DEFINE [{根据条件查询ACTION}]	 
		 */
		function getAuthor(n){
			var params = new Object();
				params.prjid = n;
				params.pageNumber = "1";
				params.pageSize = "100";			
				var url = path+'/projectAuthor/findProjectauthorByPage.action'; 
				$.ajax({
	        		type:"POST",
	       			url:url,
	        		data:params,
	       			dataType:"json",
	       			async: false,
	       			success:function callback(data){
	       				var analyMsg = data;
	       				if(!analyMsg.successed){
	       					$.messager.alert('友情提醒',analyMsg.errorMsg,'info');
	        			}else{ 
	        				$('#author').combobox({
	        					editable:false,
	        					data:analyMsg.dataMap.rows,
	        					valueField:'nickname',
	        					textField:'nickname',
	        					panelHeight:100
	        				});
	       				}
	       			},
	       		 	error : function(data,textstatus){  
		                $.messager.alert('友情提醒',data.responseText,'info');  
		            } 
	       		});	
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
       						panelHeight:50,
       						onChange:function(n,o){
       							getAuthor(n);
       						} 
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
		 * 5、BUTTON DEFINE [{根据条件查询ACTION}]	 
		 */ 
		 
		//--5.1、根据条件查询ACTION
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
			params.tblname = $('#stblname').val();
			
			getGridData("button",params);  
		} 

		/**************************************************************************************
		 *
		 * 6、JSP JQUERY INITIALIZATION 
		 */ 
		 
		$(function(){  
			//setAjaxLoading();
			initCombobox();
			initGrid();
			initDialog();
			pageSelect();
			displayMsg();
			$('#tt').datagrid("loadData",[]);//datagrid居中 
		 	//SetLength("#tchCode",19); 
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
							<td width=100 style="text-align:right;">数据库表名：</td>
							<td width=200><input class="input22" id="stblname" name="stblname" type="text" style="width:198px;"></input></td>
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
   <div id="addDialog" closed="true" title="新增" style="width:400px;height:260px; display: none;">
        <div id="addContent" align="center">
           <!-- <p align="center" style="font-size: 13px; margin-top: 5px; margin-bottom: 5px;">新增</p> -->  
        </div>
   </div>
   
   <!-- 弹出修改窗体 -->  
   <div id="updateDialog" closed="true" title="修改" style="width:400px;height:260px; display: none;">
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
					<td align=right>编写者：</td>
					<td> 
						<input class="input22" type="text"  id="author"  name="author" style="width:260px"></input>
					</td>
					<td><span style="font-size:16px;color:red;">*</span></td>
				</tr> 
				<tr height=28>
					<td align=right>表名：</td>
					<td>
						<input class="input22" type="text" id="tblname" name="tblname" validType="length[0,99]"  style="width:260px"></input>
<%--						<input class="easyui-validatebox input" type="text" id="tblname" name="tblname" validType="length[0,99]"  style="width:260px"	required="true"></input>--%>
					</td>
					<td><span style="font-size:16px;color:red;">*</span></td>
				</tr>
				<tr height=28>
					<td align=right>包路径：</td>
					<td><!-- required="true" -->
						<input class="input22" type="text" id="pkgmodelpath" name="pkgmodelpath" validType="length[0,199]"  style="width:260px" ></input>
					</td>
					<td><span style="font-size:16px;color:red;">*</span></td>
				</tr>
				<tr height=28>
					<td align=right>实体类：</td>
					<td colspan="2"> 
						<input class="easyui-validatebox input22" type="text"  id="entityclass"  name="entityclass" validType="length[0,30]" style="width:190px;margin-right:8px;"></input><span style="font-size:12px;color:red;font-weight:bold;">*必须Entity结尾</span>
					</td>
				</tr> 
			</table> 
		</form>
	</div>    
</body>
</html>
