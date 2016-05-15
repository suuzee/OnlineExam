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
    <link rel="stylesheet" type="text/css" href="css/myprofile.css"/>
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
            <div class="zuji"><a href="<%=path%>/notice.do">首页</a>><a href="javascript:;">我的档案</a></div>
            <div class="testcenter_information">
                <ul>
                    <li class="infor_border"><a href="<%=path%>/notice.do">返回首页</a></li>
                    <li><a href="javascript:;">帮助中心</a></li>
                </ul>
            </div>
        </div>
        <div class="myprofile_files">
            <div class="files_mine">
                <div class="mine_left">
                    <img src="images/dangan.png" alt="" class="mine_img">
                    <h3>我的档案</h3>
                    <a href="<%=path%>">退出登录</a>
                </div>
                <div class="mine_right">
                    <div class="mine_infor">
                        <ul>
                            <li class="infor_list">
                                <img src="images/name_img.png" alt="">
                                <span class="mine_name">姓名：</span>
                                <span class="mine_con">${archive.personname}</span>
                            </li>
                            <li class="infor_list">
                                <span class="mine_name">编号：</span>
                                <span class="mine_con">${archive.personcode}</span>
                            </li>
                            <li class="infor_list">
                                <span class="mine_name">等级：</span>
                                <span class="mine_con">${archive.optionname}</span>
                            </li>
                        </ul>
                    </div>
                    <div class="mine_score">
                        <ul>
                            <li class="score_li">
                                <img src="images/score.png" alt="">
                                <span class="score_word">我的学分：</span>
                                <span class="score_gray"><em class="red">${archive.credit}</em>分</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="files_record">
                <div class="record_title">
                    <img src="images/record.png" alt="">
                    <h3>考试记录</h3>
                </div>
                <div class="record_detail">
                    <div class="record_detail_title">
                        <img src="images/record_write.png" alt="">
                        <h4>考试记录</h4>
                    </div>
                    <div class="record_detail_list">
                        <ul>
                        <c:forEach items="${paper}" var="t">
                            <li class="list_border">
                                <div class="list_border_inner">
                                    <ul>
                                        <li class="list_inner_name">${t.papertype}</li>
                                        <li class="list_testtime"><span class="testtime_blue">考试时间：</span>${t.creatdate}</li>
                                        <li class="list_inner_num">
                                            <span>得分：</span>
                                            <span class="num_red">${t.score}</span>
                                            <span>分</span>
                                        </li>
                                        <li class="list_inner_state">状态：<span class="state_result">${t.passstate}</span></li>
                                        <li class="list_inner_btn"><a href="javascript:;"><img src="images/btn_check.png" onclick="sub(this)" title="${t.paperkey}" alt=""></a></li>
                                    </ul>
                                </div>
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
	function sub(t){
		var paperkey = t.title;
		window.location.href="<%=path %>/check.do?paperkey="+paperkey;
	}
	var page_number =$("#page_number");
	var obj,obj1,j;
	var arr,arr1;
	var nowPage=0;//当前页
	var listNum=5;//每页显示<ul>数
	var PagesLen=0;//总页数
	$(document).ready(
	function(){
		obj=$(".list_border_inner");
		obj1=$(".list_border");
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
	
	/* -----------------------function getKey(p){
		if(event.keyCode==13){
			if(p.value>0&&p.value<=PagesLen){
				var page = parseInt(p.value)-1;
				nowPage=page;
				for (var i=0;i<j;i++){
					arr[i].style.display="none";
					arr1[i].style.display="none";
				}
				for (var i=page*listNum;i<(page+1)*listNum;i++){
					if(obj[i])
						arr[i].style.display="block";
						arr1[i].style.display="block";
						page_number.text(nowPage+1+"/"+PagesLen);
				} 
			}
		}
		p.value="";
	} */
	
</script>
</html>
