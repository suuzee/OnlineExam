<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.net.URLEncoder" %>
<%@page import="java.net.URLDecoder"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>明亚考试系统--后台</title>
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
                <a href="<%=path%>/manage.jsp">首页</a>
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
                <h3>通知发布</h3>
                <p class="learning_name"> ${archive.personname} </p>
                <div class="learning_login"><a href="<%=path%>">退出登录</a></div>
            </div>
            <div class="docpage_content">
                <div class="download_h3">
                    <img src="images/download.png" alt=""><span>通知发布</span>
                </div>
                <div class="docpage_h4">通知列表</div>
                <div class="docpage_abstract">
                    <c:forEach items="${notices}" var="t">
                    <li>
                        <div class="notice_border">
                             <span class="notice_content">${t.title}</span>
                             <span class="notice_time">${t.publishdate}</span>
                             <span class="notice_time"><input type="button" value="删除" onclick="delNotice(this)" name="${t.noticekey}"></span>
                        </div>
                    </li>
                </c:forEach>
                <form method="post" action="addNotice.do">
                <table>
					<tr>
						<td>标题</td>
						<td><input type="text" name="title"></td>
					</tr>
					<tr>
						<td>内容</td>
						<td><input type="text" name="content"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="确认添加此消息"></td>
					</tr>
                </table> 
                </form>
	            </div>
        </div>
        
        
    </div>
   <script>
	function delNotice(t){
		var noticeKey = t.name;
		window.location.href = "<%=path %>/delNotice.do?noticekey="+noticeKey;
	}
	</script>
</body>
</html>
