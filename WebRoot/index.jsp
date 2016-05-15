<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.setAttribute("personkey",null);
session.setAttribute("personname",null);
session.setAttribute("validateCode",null);
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>明亚考试系统</title>
	<link rel="stylesheet" type="text/css" href="css/base.css"/>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  	<body class="loginbody">
  	<form id="login" name="login" action="login.do" method="post">
    <div class="login">
        <h1 class="logo"></h1>
        <div class="login_write">
            <div class="user">
                <span>用户名</span><input type="text" name="userkey" id="userkey" value="" style="background-color:#fff;"><img src="images/arr_xiala.png" alt="">
            </div>
            <div class="password">
                <span>密&nbsp;&nbsp;&nbsp;码</span><input type="password" id="password" name="password" value="" placeholder="请输入密码"><img src="images/keyboard.png" alt="">
            </div>
            <div class="yanzheng">
                <img id="codeImg" src="image.jsp" onclick="adjustImg();" style="line-height:40px;height:25px;"/>
                <input type="text" name="validateCode" id="yanzheng" value="" placeholder="验证码">
            </div>
            <div class="login_btn">
                <a href="javascript:clear();"><img src="images/btn_clear.png" alt=""></a>
                <a href="javascript:subpage();"><img src="images/btn_login.png" alt=""></a>
            </div>
            <div class="login-mess" id="mess">${mess}</div>
        </div>
    </div>
     	</form>
</body>
</html>
<script >
function adjustImg()
	{
		var codeImg = document.getElementById("codeImg");
		codeImg.src	="image.jsp?"+"&ts=" + new Date().getTime(); 
	}
	
function subpage()
{
	var mess = $("#mess");
	mess.text("");
	var userkey = $("#userkey");
	var password = $("#password");
	var yanzheng = $("#yanzheng");
	if(!yanzheng.val() == "" && !userkey.val() == "" && !password.val() == ""){
		document.forms[0].submit();
		}else if(userkey.val() == "" ){
			alert("请输入用户名");
		}else if(password.val() == "" ){
			alert("请输入密码");
		}else if(yanzheng.val() == "" ){
			alert("请输入验证码");
		}
}

function clear(){
 	var pw = document.getElementById("password");
	var uk = document.getElementById("userkey");
	pw.value = "";
	uk.value = ""; 	
}

</script>