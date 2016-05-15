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
    
    <title>明亚考试系统-后台</title>
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
                    <a href="javascript:void(0);" onclick="getNotice()" title="发布通知">
                        <img src="images/dangan.png" alt="" class="dangan">
                        <p>发布通知</p>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="importTest()" title="试题导入">
                        <img src="images/test.png" alt="" class="test">
                        <p>试题导入</p>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="importMaterial();" class="a_last"  title="资料导入">
                        <img src="images/learning.png" alt="" class="learning">
                        <p>资料导入</p>
                    </a>
                </li>
            </ul>
        </div>
    </div>
   
</body>


<script>
	function getNotice(){
		window.location.href="<%=path %>/getNotice.do?";
	}
	
	function importMaterial(){
		window.location.href="<%=path %>/importMaterial.do";
	}
	
	function importTest(){
		window.location.href="<%=path %>/importAData.jsp";
	}
	
	
</script>
</html>
