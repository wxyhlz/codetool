<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head> 
	<title>用户登录</title> 
	<!-- JQUERY AND EASYUI -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/ui/jquery-easyui-1.3.4/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/ui/jquery-easyui-1.3.4/themes/icon.css"/>
	<script type="text/javascript" src="<%=path%>/ui/jquery/jquery-1.6.min.js"></script>	
	<script type="text/javascript" src="<%=path%>/ui/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>	
	
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/login/login.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm/comm.css"/> 
	
<script type="text/javascript">
	//服务路径
	var path="<%=path%>";
	
	/*************************************************************************************
	 * DEFINE *
	 *************************************************************************************
	 * 1、PAGING DATAGRID DEFINE [{数据查询},{分页显示},{插件查询},{表格初始化}] 
	 *************************************************************************************/

	 /**************************************************************************************
	 *
	 * 1、PAGING DATAGRID DEFINE [{数据查询},{分页显示},{插件查询},{表格初始化}]	 
	 */ 
	function imgRefresh(){ 
		var timenow = new Date().getTime();     
		document.getElementById("captchaCode").src="<%=path%>/image.action?str="+timenow; 	
	}

	/**************************************************************************************
	 *
	 * 1、PAGING DATAGRID DEFINE [{数据查询},{分页显示},{插件查询},{表格初始化}]	 
	 */
	function clearInput(){
		$("#password_txt").show(); 
		$("#password").hide();
		$("#password").val("");
	        
		$("#accountCode").val("请输入用户名");
		$("#password_txt").val("请输入密码");
		$("#captcha").val("验证码");
		$("#accountCode").css("color","#A8A8A8");
		$("#password").css("color","#A8A8A8");
		$("#captcha").css("color","#A8A8A8");
	}

	/**************************************************************************************
	 *
	 * 1、PAGING DATAGRID DEFINE [{数据查询},{分页显示},{插件查询},{表格初始化}]	 
	 */
	function hintControl(){
		//用户名 
        $("#accountCode").focus(function(){ 
			var txt_value = $(this).val(); 
			if(txt_value == "请输入用户名"){ $(this).val(""); } 
			$(this).addClass("focus"); 
			$(this).css("color","black");
        });  
		$("#accountCode").blur(function(){ 
        	var txt_value = $(this).val(); 
            if(txt_value=="") { $(this).val("请输入用户名"); $(this).css("color","#A8A8A8");} 
            $(this).removeClass("focus");
        }); 

        //密码 
        var $pwd_text=$("#password_txt"); 
        var $password=$("#password");
         
        $password.hide();        
        $pwd_text.focus(function(){ 
			if($pwd_text.val()=="请输入密码"){ 
				$pwd_text.hide(); 
				$password.show(); 
				$password.show().focus(); 
			} 
			$password.css("color","black");
			$password.addClass("focus"); 
        }); 

		$password.blur(function(){ 
			if($password.val()==""){ 
				$password.hide(); 
				$pwd_text.show(); 
			} 
		}); 

		//验证码 
        $("#captcha").focus(function(){ 
			var txt_value = $(this).val(); 
			if(txt_value == "验证码"){ $(this).val(""); } 
			$(this).addClass("focus"); 
			$(this).css("color","black");
        });  
		$("#captcha").blur(function(){ 
        	var txt_value = $(this).val(); 
            if(txt_value=="") { $(this).val("验证码"); $(this).css("color","#A8A8A8");} 
            $(this).removeClass("focus"); 
        }); 
	}

	/**************************************************************************************
	 *
	 * 1、PAGING DATAGRID DEFINE [{数据查询},{分页显示},{插件查询},{表格初始化}]	 
	 */

	function login(){
		var params = new Object();
		params.accountCode = $("#accountCode").val();
		params.password = $("#password").val();
		params.captcha = $("#captcha").val();

		var url = path+'/operator/findLogin.action'; 	       
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
   					location.href = path + "/" + analyMsg.dataMap.url;
  				}
  			},
  		 	error : function(data,textstatus){  
               $.messager.alert('友情提醒',data.responseText,'info');  
			} 
  		});  
	}
	

	/**************************************************************************************
	 *
	 * 6、JSP JQUERY INITIALIZATION 
	 */ 
	$(function(){
		hintControl();
		clearInput();
	});  
</script>
</head>
<body>
<%@ include file="/loading.jsp" %>
	<div id="login">	
		<div id="top">
			<div id="top_left"><img src="css/login/images/login_03.gif" /></div>
			<div id="top_center"></div>
		</div>		 
		<div id="center">
			<div id="center_left"></div>
			<div id="center_middle">
				<div id="div_user">用　户
					<input type="text" id="accountCode" value="请输入用户名"/>
				</div>
				<div id="div_password">密　码
					<input type="text" id="password_txt"  value="请输入密码"/><input type="password" id="password" value="请输入密码"/>
				</div>
				<div id="div_captcha">验证码
					<input type="text" id="captcha" value="验证码" style="width:58px;vertical-align:middle;margin-right:2px;"/><a href="#" style="color:blue;" onclick="javascript:imgRefresh();this.blur();"><img style="vertical-align:middle;" title="点击刷新验证码" alt="点击刷新验证码" id="captchaCode" src="<%=path%>/image.action"/></a>
				</div>
				<div id="btn"><a href="#" onclick="login();">登录</a><a href="#" onclick="clearInput();">清空</a></div>
			</div>
			<div id="center_right"></div>		 
		</div>
		<div id="down">
			<div id="down_left">
				<div id="inf">
					<span class="inf_text">版本信息</span>
					<span class="copyright">日期 2014.01 v1.0</span>
				</div>
			</div>
			<div id="down_center"></div>		 
		</div>
	</div>
</body>
</html>
