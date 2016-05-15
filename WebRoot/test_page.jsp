<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
	<title>明亚考试系统</title>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
    <link rel="stylesheet" type="text/css" href="css/testcenter.css"/>
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
<body class="loginbody">
    <div class="index_header">
        <div class="header_inner">
            <h1 class="index_logo"></h1>
            <div class="index_title"></div>
        </div>
    </div>
    <div class="testcenter_content">
        <div class="testcenter_link">
            <div class="zuji"><a href="<%=path%>/notice.do">首页</a>><a href="javascript:;">考试中心</a></div>
            <div class="testcenter_information">
                <ul>
                    <li class="infor_border"><a href="<%=path%>/notice.do">返回首页</a></li>
                    <li><a href="javascript:;">帮助中心</a></li>
                </ul>
            </div>
        </div>
        <div class="testcenter_main">
            <div class="main_left">
                <img src="images/dangan.png" alt="">
                <h3>考试中心</h3>
                <a href="<%=path%>" class="exit">退出登录</a>
            </div>
            <div class="main_right">
                <div class="main_message">
                    <ul>
                        <li>
                            <img src="images/name_img.png" alt="" class="message_img">
                            <span class="message_name">姓名：<em class="message_con">${archive.personname}</em></span>
                        </li>
                        <li>
                            <span class="message_name">编号：<em class="message_con">${archive.personcode}</em></span>
                        </li>
                        <li>
                            <span class="message_name">等级：<em class="message_con">${archive.optionname}</em></span>
                        </li>
                    </ul>
                </div>
                <div class="main_test">
                    <div class="test_title"><img src="images/notice.png" alt=""><h3>考试通知：</h3></div>
                    <div class="test_con">
                        <ul>
                        <c:forEach items="${exam}" var="t">
                            <li class="test_list">
                                <img src="images/test_write.png" alt="">
                                <p><em> ${t.optionname} </em> 已经可以开始考试。</p>
                                <a href="javascript:;" onclick="getQuestion(this)" title="${t.optioncode}">开始考试</a>
                            </li>
                        </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        
    </div>
   
</body>
<script>
	function getQuestion(t){
		var examType = t.title;
		window.location.href="<%=path %>/getQuestion.do?examType="+examType;
	}

</script>
</html>
