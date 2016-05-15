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
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
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
    <div class="index_content">
        <div class="index_link">
            <div class="zuji"><a href="<%=path%>/notice.do">首页</a>></div>
            <div class="index_help"><a href="javascript:void(0);">帮助中心</a></div>
        </div>
        <div class="link_center">
            <ul>
                <li>
                    <a href="javascript:void(0);" onclick="getMyArchives()" title="我的档案">
                        <img src="images/dangan.png" alt="" class="dangan">
                        <p>我的档案</p>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="getExam()" title="考试中心">
                        <img src="images/test.png" alt="" class="test">
                        <p>考试中心</p>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="getMaterial();" class="a_last"  title="学习中心">
                        <img src="images/learning.png" alt="" class="learning">
                        <p>学习中心</p>
                    </a>
                </li>
            </ul>
        </div>
        <div class="notice">
            <div class="notice_inner">
                <ul>
                <c:forEach items="${notices}" var="t">
                    <li>
                        <div class="notice_border">
                            <a href="javascript:void(0);" onclick="getNoticeById('${t.content}','${t.title}');" title="${t.title}">
                                <span class="notice_content">${t.title}</span>
                                <span class="notice_time">${t.publishdate}</span>
                            </a>
                        </div>
                    </li>
                </c:forEach>
                </ul>
            </div>
        </div>
    </div>
   
</body>


<script>
	function getMyArchives(){
		window.location.href="<%=path %>/myArchives.do?";
	}
	
	function getMaterial(){
		window.location.href="<%=path %>/material.do";
	}
	
	function getExam(){
		window.location.href="<%=path %>/exam.do";
	}
	
	function getNoticeById(t,c){
		var t = encodeURI(encodeURI(t));
		var c = encodeURI(encodeURI(c));
		window.location.href="<%=path %>/notice_page.jsp?title="+c+"&content="+t;
	}
</script>
</html>
