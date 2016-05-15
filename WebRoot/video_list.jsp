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
    <link rel="stylesheet" type="text/css" href="css/doclist.css"/>
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
            <div class="zuji"><a href="<%=path%>/notice.do">首页</a>><a href="<%=path%>/material.do">学习中心</a>><a href="javascript:;">下载专区</a></div>
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
            <div class="learning_content">
                <div class="learning_download">
                    <div class="download_h3">
                        <img src="images/record_write.png" alt=""><span>在线学习</span>
                    </div>
                    <div class="ldownload_list">
                        <ul>
                        	 <c:forEach items="${material}" var="t">
                            <li>
                                <div class="ldown_listword">${t.title}</div>
                                <div class="ldown_listbtn"><a href="javascript:;" title="${t.materialkey}" onclick="getDown1(this)">点击查看</a></div>
                            </li>
                            </c:forEach>
                            
                        </ul>
                    </div>
                    <div class="record_test_page">
                        <ul>
                            <li class="record_pageone" onclick="firstPage(0)">首页</li>
                            <li class="record_pagelast" onclick="upPage(0)">上页</li>
                            <li class="record_pagenext" onclick="downPage(0)">下页</li>
                            <li class="record_lastpage" onclick="lastPage(0)">尾页</li>
                            <li class="record_pagenum">第<input type="text" onblur="jumpPage(this);" onkeypress="getKey(this);">页</li>
                            <li class="record_page_number" id="page_number"></li>
                        </ul>
                    </div>
                    
                </div>
                
            </div>
        </div>
        
        
    </div>
   
</body>
<script>
	function getDown1(t){
		var key = t.title;
		window.location.href="<%=path %>/getDown1.do?materialkey="+key;
	}
	
	var obj,obj1,j;
	var nowPage=0;//当前页
	var listNum=5;//每页显示<ul>数
	var PagesLen=0;//总页数
	var page_number =$("#page_number");
	var arr,arr1;
	$(document).ready(
	function(){
		obj=$(".ldown_listword");
		obj1=$(".ldown_listbtn");
		arr = obj.get();
		arr1 = obj1.get();
		j=arr.length;
		if(j > 0){
			PagesLen=Math.ceil(j/listNum);
			firstPage();
		}
	}
	);
	
	
	function upPage(){

		if(nowPage != 0)
			nowPage=nowPage - 1;
		for (var i=0;i<j;i++){
			arr[i].style.display="none";
			arr1[i].style.display="none";
		}
		for (var i=nowPage*listNum;i<(nowPage+1)*listNum;i++){
			if(obj[i]){
				arr[i].style.display="block";
				arr1[i].style.display="block";
				page_number.text(nowPage+1+"/"+PagesLen);
				}
		}
	}
	
	function downPage(){

		if(nowPage != PagesLen-1)
			nowPage=nowPage + 1;
		for (var i=0;i<j;i++){
			arr[i].style.display="none";
			arr1[i].style.display="none";
		}
		for (var i=nowPage*listNum;i<(nowPage+1)*listNum;i++){
			if(obj[i]){
				arr[i].style.display="block";
				arr1[i].style.display="block";
				page_number.text(nowPage+1+"/"+PagesLen);
				}
		}
	}
	
	function firstPage(){
		nowPage=0;
		for (var i=0;i<j;i++){
			arr[i].style.display="none";
			arr1[i].style.display="none";
		}
		for (var i=nowPage*listNum;i<(nowPage+1)*listNum;i++){
			if(obj[i]){
				arr[i].style.display="block";
				arr1[i].style.display="block";
				page_number.text(nowPage+1+"/"+PagesLen);
				}
		}
	}
	
	function lastPage(){
		nowPage=PagesLen-1;
		for (var i=0;i<j;i++){
			arr[i].style.display="none";
			arr1[i].style.display="none";
		}
		for (var i=nowPage*listNum;i<(nowPage+1)*listNum;i++){
			if(obj[i]){
				arr[i].style.display="block";
				arr1[i].style.display="block";
				page_number.text(nowPage+1+"/"+PagesLen);
				}
		}
	}
	
	function jumpPage(p){

		if(p.value>0&&p.value<=PagesLen){
				var page = parseInt(p.value)-1;
				nowPage=page;
				for (var i=0;i<j;i++){
					arr[i].style.display="none";
					arr1[i].style.display="none";
				}
				for (var i=page*listNum;i<(page+1)*listNum;i++){
					if(obj[i]){
						arr[i].style.display="block";
						arr1[i].style.display="block";
						page_number.text(nowPage+1+"/"+PagesLen);
						}
				} 
			}
		p.value="";
	}
	
/* 	function getKey(p){
		if(event.keyCode==13){
			if(p.value>0&&p.value<=PagesLen){
				var page = parseInt(p.value)-1;
				nowPage=page;
				for (var i=0;i<j;i++){
					obj[i].hide();
					obj1[i].hide();
				}
				for (var i=page*listNum;i<(page+1)*listNum;i++){
					if(obj[i])
						obj[i].show();
						obj1[i].show();
						page_number.text(nowPage+1+"/"+PagesLen);
				} 
			}
		}
		p.value="";
	} */
	
</script>
</html>
