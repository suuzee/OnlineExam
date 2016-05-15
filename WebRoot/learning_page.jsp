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
    <link rel="stylesheet" type="text/css" href="css/learningcenter.css"/>
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
    <div class="myprofile_content">
        <div class="myprofile_link">
            <div class="zuji"><a href="<%=path%>/notice.do">首页</a>><a href="<%=path%>/material.do">学习中心</a></div>
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
                <p class="learning_name"> ${archive.personname}</p>
                <div class="learning_login"><a href="<%=path%>">退出登录</a></div>
            </div>
            <div class="learning_content">
                <div class="learning_download">
                    <div class="download_h3">
                        <img src="images/download.png" alt=""><span>下载专区</span>
                    </div>
                    <div class="ldownload_list">
                        <ul>
                            <li>
                                <div class="ldown_listword"> ${material[0].title} </div>
                                <div class="ldown_listbtn"><a href="javascript:;" title="${material[0].materialkey}" onclick="getDown(this)">点击查看</a></div>
                            </li>
                            <li>
                                <div class="ldown_listword"> ${material[1].title} </div>
                                <div class="ldown_listbtn"><a href="javascript:;" title="${material[1].materialkey}" onclick="getDown(this)">点击查看</a></div>
                            </li>
                            <li>
                                <div class="ldown_listword"> ${material[2].title} </div>
                                <div class="ldown_listbtn"><a href="javascript:;" title="${material[2].materialkey}" onclick="getDown(this)">点击查看</a></div>
                            </li>
                        </ul>
                    </div>
                    <div class="ldown_more"><a href="javascript:;" onclick="more()">更多 >> </a></div>
                </div>
                <div class="learning_download learning_online">
                    <div class="download_h3">
                        <img src="images/record_write.png" alt=""><span>在线学习</span>
                    </div>
                    <div class="ldownload_list">
                       <ul>
                            <li>
                                <div class="ldown_listword"> ${material1[0].title} </div>
                                <div class="ldown_listbtn"><a href="javascript:;" title="${material1[0].materialkey}" onclick="getDown1(this)">点击查看</a></div>
                            </li>
                            <li>
                                <div class="ldown_listword"> ${material1[1].title} </div>
                                <div class="ldown_listbtn"><a href="javascript:;" title="${material1[1].materialkey}" onclick="getDown1(this)">点击查看</a></div>
                            </li>
                            <li>
                                <div class="ldown_listword"> ${material1[2].title} </div>
                                <div class="ldown_listbtn"><a href="javascript:;" title="${material1[2].materialkey}" onclick="getDown1(this)">点击查看</a></div>
                            </li>
                        </ul>
                    </div>
                    <div class="ldown_more"><a href="javascript:;" onclick="more1()">更多>></a></div>
                </div>
            </div>
        </div>
        
        
    </div>
   
</body>
<script>
	function getDown(t){
		var key = t.title;
		window.location.href="<%=path %>/getDown.do?materialkey="+key;
	}
	
	function getDown1(t){
		var key = t.title;
		window.location.href="<%=path %>/getDown1.do?materialkey="+key;
	}
	
	function more(){
		window.location.href="<%=path %>/moreMaterial.do";
	}
	
	function more1(){
		window.location.href="<%=path %>/moreMaterial1.do";
	}
</script>
</html>
