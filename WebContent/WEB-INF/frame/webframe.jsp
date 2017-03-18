<%@ page language="java"  pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%--	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />--%>
	
	<!-- JQUERY AND EASYUI -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/ui/jquery-easyui-1.3.4/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/ui/jquery-easyui-1.3.4/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/ui/jquery-easyui-1.3.4/demo/demo.css">
	<script type="text/javascript" src="<%=path%>/ui/jquery/jquery-1.6.min.js"></script>	
	<script type="text/javascript" src="<%=path%>/ui/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
	
	<!-- FRAME JS -->
	<script type="text/javascript" src="<%=path %>/js/public/public.js"></script> 
	<!-- 手风琴导航栏
	<script type="text/javascript" src="<%=path%>/js/frame/acordNavigate.js"> </script>
	 -->	 
	<script type="text/javascript" src="<%=path%>/js/frame/ztreeNavigate.js"> </script>
	
	<!-- FRAME CSS... -->	
	<link rel="stylesheet" type="text/css" href="<%=path%>/js/frame/css/default.css"  />
	
	<!-- OTHER FRAME -->
	<link rel="stylesheet" href="<%=path%>/ui/zTree v3.5.15/css/zTreeStyle/zTreeStyle.css" type="text/css">	
	<script type="text/javascript" src="<%=path%>/ui/zTree v3.5.15/js/jquery.ztree.core-3.5.js"></script>
<%--	<script type="text/javascript" src="<%=path%>/ui/zTree v3.5.15/js/jquery.ztree.excheck-3.5.js"></script>--%>
	
	<!-- LAST CONFIG --> 
	<script type="text/javascript" src="<%=path%>/ui/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		javaScript:window.history.forward(1); 
		var path='<%=path%>';
		var width = window.screen.width;
	  	var height = window.screen.height;
	  	var flag_title1 = "0";
	  	var flag_title2 = "0";
	  	
	  	//页面加载脚本
        $(function(){ 
            
            $("#todolist").click(function() {
            	//addTab("待办事宜",path+"/nssb/dbsyInit.action","");
                
            });
            
            $("#changepwd").click(function() {
            	selectTab("欢迎使用");
            	$("#pwd").window("open");
            });
            
            $("#btnEp").click(function() {
                modifyPass();
             });
            
            $("#btnCancel").click(function(){
                closePwd();
                selectTab(curtab);
             });
            
            $("#help").click(function() {
            	selectTab("欢迎使用");
            });            

        });
	  	
        //修改密码窗口
        function openPwd() {
        	$("#pwd").show();
            $("#pwd").window({
                title: "修改密码",
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 180,
                resizable:false,
                closable:false
            });
        }
        //关闭修改密码窗口
        function closePwd() { 
        }
        
        //修改密码
        function modifyPass() {
        	  
        }
        
        function getStatusbarInfo(){
        	$.ajax({
        		type:"POST",
       			url:path+"/system/getStatusbarInfo.action", 
        		dataType:"json",
       			success:function callback(data){
       				var analyMsg = data;
       				if(!analyMsg.successed){
        				$.messager.alert("友情提醒",analyMsg.errors[0],"info",function(){});
        			}else{  
        				 
       				}
       			}
        	}); 
        } 
</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" split="true" border="false"
		 style="overflow: hidden; background:url('<%=path%>/images/logobg.png');height: 80px;#7f99be repeat-x; 
		        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑">
		<div style="height:90px;')">
			<div style="text-align:right;margin-right:50px;margin-top:50px;">			
				<div>
<!--					<a href="#" id="todolist">待办事宜</a> &nbsp&nbsp&nbsp-->
<!--					<a href="#" id="loginOut">重新登录</a> &nbsp&nbsp&nbsp-->
<!--					<a href="#" id="changepwd">修改密码</a> &nbsp&nbsp&nbsp-->
<!--					<a href="#" id="help">帮助</a>-->
				</div>
			</div>
		</div>
	</div>
	
	<div align="center" region="south" split="true" style="height:35px; background: #D2E0F2;">
		<div style="vertical-align:middle;line-height:25px">
			<a id="statusbar" style="font-family: Verdana, 微软雅黑">
					欢迎使用
			</a>
		</div>
	</div>
	
    <div region="west" hide="true" split="true" title="导航菜单" style="width:210px;font-family: Verdana, 微软雅黑" id="west">
		<!-- 
		<div id="nav" class="easyui-accordion" fit="true" border="false"></div>
		 -->
		<div>
			<ul id="treeDemo" class="ztree" style="width:400px;overflow:auto;"></ul>
        </div>
    </div>
    
   	<!--修改密码窗口-->
	<div id="pwd" title="修改密码" collapsible="false"
			minimizable="false" maximizable="false" icon="icon-save"
			style="width: 400px; height: 150px; padding: 5px; background: #fafafa;display:none">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false"
					style="padding: 10px; background: #fff; border: 1px solid #ccc;">
					<table cellpadding=3>
						<tr>
							<td>
								旧密码：
							</td>
							<td>
								<input id="txtOldPass" type="password" class="txt01" 
								onfocus="this.select();"
								onKeyPress="javascript:return moveFocus(event,'txtNewPass')"/>
							</td>
						</tr>
						<tr>
							<td>
								新密码：
							</td>
							<td>
								<input id="txtNewPass" type="password" class="txt01" 
								onfocus="this.select();"
								onKeyPress="javascript:return moveFocus(event,'txtRePass')"/>
							</td>
						</tr>
						<tr>
							<td>
								确认密码：
							</td>
							<td>
								<input id="txtRePass" type="password" class="txt01" 
								onfocus="this.select();"
								onKeyPress="javascript:return moveFocus(event,'btnEp')"/>
							</td>
						</tr>
					</table>
				</div>
				<div region="south" border="false"
					style="text-align: right; height: 30px; line-height: 30px;">
					<a id="btnEp" class="easyui-linkbutton" icon="icon-ok"
						href="#"> 确定</a>
					<a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel"
						href="#">取消</a>
				</div>
			</div>
		</div>
	</div>
    
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; " >				
			</div>
		</div>
    </div>
	<div id="mm" class="easyui-menu" style="width:150px;display:none">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
<%--		<div class="menu-sep"></div>--%>
<%--		<div id="mm-tabcloseright">当前页右侧全部关闭</div>--%>
<%--		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>--%>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
    </div>
</body>
</html>