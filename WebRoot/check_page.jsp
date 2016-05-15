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
    <link rel="stylesheet" type="text/css" href="css/testpage.css"/>
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
  </head>

  <body>
    <div class="index_header">
        <div class="header_inner">
            <h1 class="index_logo"></h1>
            <div class="index_title"></div>
        </div>
    </div>
    <div class="testpage_content">
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
        <div class="zuji"><a href="<%=path%>/notice.do">首页</a> > </div>
        <h2 class="testtitle">考试科目：培训考试题</h2>
        <div class="testtitle_type">选择题<span class="type_detail">（单选题，每题一分，共100题，满分100分）</span></div>
        <div class="test_problems">
        <c:forEach items="${question}" var="t" varStatus="i">
            <h3 class="problems_title">${i.count}.${t.content} &nbsp &nbsp &nbsp  您的答案：${t.questionanswer} &nbsp &nbsp &nbsp正确答案：${t.correntanswer}</h3>
            <div class="problems_con">
                <ul>
                    <li class="problems_one">
                    	 <div style ="display : none"><input type="radio" id = "${t.questionkey}01" name = "${t.questionkey}" value = "A"></div>
                        <span class="choosecon">${t.alternativeanswers[0]}</span>
                    </li>
                    <li class="problems_one">
                        <div style ="display : none"><input type="radio" id = "${t.questionkey}02" name = "${t.questionkey}" value = "B"></div>
                        <span class="choosecon">${t.alternativeanswers[1]}</span>
                    </li>
                    <li class="problems_one">
                        <div style ="display : none"> <input type="radio" id = "${t.questionkey}03" name = "${t.questionkey}" value = "C"></div>
                        <span class="choosecon">${t.alternativeanswers[2]}</span>
                    </li>
                    <li class="problems_one">
                        <div style ="display : none"><input type="radio" id = "${t.questionkey}04" name = "${t.questionkey}" value = "D"></div>
                        <span class="choosecon">${t.alternativeanswers[3]}</span>
                    </li>
                </ul>
            </div>
            </c:forEach>
          </div>
        <div class="test_page">
            <ul>
                <li class="pageone" onclick="firstPage(0)">首页</li>
                <li class="pagelast" onclick="upPage()">上页</li>
                <li class="pagenext" onclick="downPage()">下页</li>
                <li class="lastpage" onclick="lastPage(9)">尾页</li>
                <li class="pagenum">第<input type="text" onblur="jumpPage(this);" onkeypress="getKey(this);" id="pageno">页</li>
                <li class="page_number" id="page_number">1/10</li>
            </ul>
        </div>

               
    </div>


</body>
<script>
	var obj,obj1,j;
	var page=0;
	var nowPage=0;//当前页
	var listNum=10;//每页显示<ul>数
	var PagesLen;//总页数
	var PageNum=10;//分页链接接数(5个)
	var page_number =document.getElementById("page_number");
	$(document).ready(
	function(){
		obj=$(".problems_con");
		obj1=$("h3");
		j=obj.length;
		PagesLen=Math.ceil(j/listNum);
		firstPage(0);
	}
	)
	
	function upPage(){
	$.ajax("<%=path %>/page.do","",function(){
				
			});
		var question = $('input:radio:checked');
  		var questionlength = question.length;
  		if(questionlength%10 == 0){
			if(nowPage != 0)
				nowPage=nowPage - 1;
			for (var i=0;i<j;i++){
				obj[i].style.display="none";
				obj1[i].style.display="none";
			}
			for (var i=nowPage*listNum;i<(nowPage+1)*listNum;i++){
				if(obj[i])
					obj[i].style.display="block";
					obj1[i].style.display="block";
					page_number.innerText=nowPage+1+"/"+PagesLen;
			}
			$(window).scrollTop(0);
		}else{
			alert("您本页有题没答，请答完后翻页");
		}
	}
	
	function downPage(){
	$.ajax("<%=path %>/page.do","",function(){
				
			});
		var question = $('input:radio:checked');
  		var questionlength = question.length;
  		if(questionlength%10 == 0){
		if(nowPage != PagesLen-1)
			nowPage=nowPage + 1;
		for (var i=0;i<j;i++){
			obj[i].style.display="none";
			obj1[i].style.display="none";
		}
		for (var i=nowPage*listNum;i<(nowPage+1)*listNum;i++){
			if(obj[i])
				obj[i].style.display="block";
				obj1[i].style.display="block";
				page_number.innerText=nowPage+1+"/"+PagesLen;
		}
		$(window).scrollTop(0);
		}else{
			alert("您本页有题没答，请答完后翻页");
		}
	}
	
	function firstPage(){
	$.ajax("<%=path %>/page.do","",function(){
				
			});
		var question = $('input:radio:checked');
  		var questionlength = question.length;
  		if(questionlength%10 == 0){
		nowPage=0;
		for (var i=0;i<j;i++){
			obj[i].style.display="none";
			obj1[i].style.display="none";
		}
		for (var i=nowPage*listNum;i<(nowPage+1)*listNum;i++){
			if(obj[i])
				obj[i].style.display="block";
				obj1[i].style.display="block";
				page_number.innerText=nowPage+1+"/"+PagesLen;
		}
		$(window).scrollTop(0);
		}else{
			alert("您本页有题没答，请答完后翻页");
		}
	}
	
	function lastPage(){
	$.ajax("<%=path %>/page.do","",function(){
				
			});
		var question = $('input:radio:checked');
  		var questionlength = question.length;
  		if(questionlength%10 == 0){
		nowPage=PagesLen-1;
		for (var i=0;i<j;i++){
			obj[i].style.display="none";
			obj1[i].style.display="none";
		}
		for (var i=nowPage*listNum;i<(nowPage+1)*listNum;i++){
			if(obj[i])
				obj[i].style.display="block";
				obj1[i].style.display="block";
				page_number.innerText=nowPage+1+"/"+PagesLen;
		}
		$(window).scrollTop(0);
		}else{
			alert("您本页有题没答，请答完后翻页");
		}
	}

	
	function jumpPage(p){
	$.ajax("<%=path %>/page.do","",function(){
				
			});
		var question = $('input:radio:checked');
  		var questionlength = question.length;
  		if(questionlength%10 == 0){
		if(p.value>0&&p.value<=PagesLen){
		
			var page = parseInt(p.value)-1;
			nowPage=page;
			for (var i=0;i<j;i++){
				obj[i].style.display="none";
				obj1[i].style.display="none";
			}
			 for (var i=page*listNum;i<(page+1)*listNum;i++){
				if(obj[i])
					obj[i].style.display="block";
					obj1[i].style.display="block";
					page_number.innerText=nowPage+1+"/10";
			} 
		}
		p.value="";
		$(window).scrollTop(0);
		}else{
			alert("您本页有题没答，请答完后翻页");
			p.value="";
		}
	}
	
	function getKey(p){
	$.ajax("<%=path %>/page.do","",function(){
				
			});
	if(event.keyCode==13){
		var question = $('input:radio:checked');
  		var questionlength = question.length;
  		if(questionlength%10 == 0){
		if(p.value>0&&p.value<=PagesLen){
		
			var page = parseInt(p.value)-1;
			nowPage=page;
			for (var i=0;i<j;i++){
				obj[i].style.display="none";
				obj1[i].style.display="none";
			}
			 for (var i=page*listNum;i<(page+1)*listNum;i++){
				if(obj[i])
					obj[i].style.display="block";
					obj1[i].style.display="block";
					page_number.innerText=nowPage+1+"/10";
			} 
		}
		p.value="";
		$(window).scrollTop(0);
		}else{
			alert("您本页有题没答，请答完后翻页");
			p.value="";
		}
	}
}
</script>
</html>


