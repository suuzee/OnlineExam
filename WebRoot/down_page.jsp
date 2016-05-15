<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.net.URLEncoder" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>明亚考试系统</title>
     <link rel="stylesheet" type="text/css" href="css/base.css"/>
    <link rel="stylesheet" type="text/css" href="css/docpage.css"/>
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/login.js"></script>

  </head>
  
<body>
    <div class="index_header">
        <div class="header_inner">
            <h1 class="index_logo"></h1>
            <div class="index_title"></div>
        </div>
    </div>
    <div class="myprofile_content">
        <div class="myprofile_link">
            <div class="zuji">
                <a href="<%=path%>/notice.do">首页</a>><a href="<%=path%>/material.do">学习中心</a>><a href="javascript:;">下载专区</a>
            </div>
            <div class="testcenter_information">
                <ul>
                    <li class="infor_border"><a href="<%=path%>/notice.do">返回首页</a></li>
                    <li><a href="javascript:;">帮助中心</a></li>
                </ul>
            </div>
        </div>
        <div class="learning_files">
            <div class="learning_center">
                <img src="images/dangan.png" alt="">
                <h3>学习中心</h3>
                <p class="learning_name"> ${archive.personname} </p>
                <div class="learning_login"><a href="index.jsp">退出登录</a></div>
            </div>
            <div class="docpage_content">
                <div class="download_h3">
                    <img src="images/download.png" alt=""><span>下载专区</span>
                </div>
                <div class="docpage_h4">${material.title}</div>
                <div class="docpage_abstract">
                    <h4>内容简介：</h4>
                    <p>${material.content}</p>
                </div><%
                	String pat = (String)request.getAttribute("downpath");
                	String pa = (String)request.getAttribute("title");
                 %>
                <div class="docpage_btn"><a href="javascript:;"onclick="getDown('<%=pat%>','<%=pa%>')">点击下载</a></div>
            </div>
        </div>
        
        
    </div>
   <script>
	function getDown(t,t1){
		t = encodeURI(encodeURI(t));  
		t1 = encodeURI(encodeURI(t1));
		var url  ="download.jsp?method=download&fileName="+t+"&filename="+t1;
		window.location.href=url;
	}
	</script>
</body>
</html>
