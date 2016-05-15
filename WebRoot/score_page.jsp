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
    <link rel="stylesheet" type="text/css" href="css/testresult.css"/>
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
    <body>
    <div class="index_header">
        <div class="header_inner">
            <h1 class="index_logo"></h1>
            <div class="index_title"></div>
        </div>
    </div>
    <div class="testpage_content testresult_content">
        <div class="testperson_message">
            <ul>
                <li>
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
        <!-- 成功部分 -->
        <c:if test="${score>=60}">
        <div class="testresult_success">
            <img class="testresult_bg" src="images/testresult_bg.png" alt="">
            <div class="testresult_innner">
                <div class="tests_wish"><img src="images/testresult_ok.png" alt=""><span>恭喜您，您已通过考试！</span></div>
                <div class="testresult_mark">您的得分：<em>${score}132</em></div>
                <div class="testresult_button">
                    <a href="javascript:;" class="testresult_sure">点击查看<br>正确答案</a>
                    <a href="first_page.jsp" class="testresult_back">点击返回首页</a>
                </div>
            </div>
            <div class="testresult_person"><img src="images/testresult_successbg.png" alt=""></div>
        </div>
        </c:if>
        <!-- 失败部分 -->
        <c:if test="${score<60}">
        <div class="testresult_fail">
            <img class="testresult_bg" src="images/testresult_bg.png" alt="">
            <div class="testresult_innner">
                <div class="tests_wish"><img src="images/testresult_no.png" alt=""><span>很遗憾，您未通过考试！</span></div>
                <div class="testresult_mark">您的得分：<em>${score}</em></div>
                <div class="testresult_button">
                    <a href="javascript:subpage();" class="testresult_sure">点击查看<br>正确答案</a>
                    <a href="<%=path%>/notice.do" class="testresult_back">点击返回首页</a>
                </div>
            </div>
            <div class="testresult_person"><img src="images/testresult_fail.png" alt=""></div>
        </div>
        </c:if>
        
               
    </div>
<form id="check" name="check" action="check.do" method="post">
	<input type="hidden" name="paperkey" value="${paperkey}">
</form>

   
</body>
<script>
	function subpage(){
		document.forms[0].submit();
	}
</script>
</html>
