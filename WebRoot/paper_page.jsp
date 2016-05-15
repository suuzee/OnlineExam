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
	<script type="text/javascript" src="js/page.js"></script>
<!-- 	<style type="text/css">
		.problems_title,.problems_con{display:none}
	</style> -->
  </head>

  <body>
   <form id="paper" name="paper" action="paper.do" method="post">
   <input type = "hidden" name = "personkey" value="${personkey}">
   	 <input type="hidden" id = "que" name = "que" value = ""/>
	 <input type="hidden" id = "quekey" name = "quekey" value = ""/>
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
		<div id="show">  
			
		</div> 
        <h2 class="testtitle">考试科目：培训考试题</h2>
        <div class="testtitle_type">选择题<span class="type_detail">（单选题，每题一分，共100题，满分100分）</span></div>
        <div class="test_problems">
        <c:forEach items="${question}" var="t" varStatus="i">
            <h3 class="problems_title">${i.count}.${t.content }</h3>
            <div class="problems_con">
                <ul>
                    <li class="problems_one">
                    	<img class="choose" src="images/nochoose.png" onclick="changeChecked(this)" title="${t.questionkey}01" alt="">
                    	 <div style ="display : none"><input type="radio" id = "${t.questionkey}01" name = "${t.questionkey}" value = "A"></div>
                        <span class="choosecon">${t.alternativeanswers[0]}</span>
                    </li>
                    <li class="problems_one">
                    	<img class="choose" src="images/nochoose.png" onclick="changeChecked(this)" title="${t.questionkey}02" alt="">
                        <div style ="display : none"><input type="radio" id = "${t.questionkey}02" name = "${t.questionkey}" value = "B"></div>
                        <span class="choosecon">${t.alternativeanswers[1]}</span>
                    </li>
                    <li class="problems_one">
                    	<img class="choose" src="images/nochoose.png" onclick="changeChecked(this)" title="${t.questionkey}03" alt="">
                        <div style ="display : none"> <input type="radio" id = "${t.questionkey}03" name = "${t.questionkey}" value = "C"></div>
                        <span class="choosecon">${t.alternativeanswers[2]}</span>
                    </li>
                    <li class="problems_one">
                    	<img class="choose" src="images/nochoose.png" onclick="changeChecked(this)" title="${t.questionkey}04" alt="">
                        <div style ="display : none"><input type="radio" id = "${t.questionkey}04" name = "${t.questionkey}" value = "D"></div>
                        <span class="choosecon">${t.alternativeanswers[3]}</span>
                    </li>
                </ul>
            </div>
            </c:forEach>
          </div>
        <div class="test_page">
            <ul>
                <li class="pageone" onclick="firstPage()">首页</li>
                <li class="pagelast" onclick="upPage()">上页</li>
                <li class="pagenext" onclick="downPage()">下页</li>
                <li class="lastpage" onclick="lastPage()">尾页</li>
                <li class="pagenum">第<input type="text" onblur="jumpPage(this);" onkeypress="getKey(this);">页</li>
                <li class="page_number" id="page_number"></li>
            </ul>
        </div>
        <input style="display:none">
        <div class="test_submit"><a href="javascript:;" onclick = "subpaper();"><img src="images/test_submit.png" alt=""></a></div>

    </div>

   </form>
</body>
  <script>
  
  	function subpaper(){
  		var question = $('input:radio:checked');
  		var questionlength = question.length;
  		var _que = document.getElementById("que");
  		var _quekey = document.getElementById("quekey");
  		var que = [];
  		var quekey = [];
  		for(var i =0;i<questionlength;i++){
  			que[i] = question[i].value;
  			quekey[i] = question[i].name;
  		}
  		_que.value = que;
  		_quekey.value = quekey;
  		if(questionlength == 100){
  			document.forms[0].submit();
  		}else{
  			alert("您还有题没答，请继续答题");
  		}
  	}
  	
  	function changeChecked(data){
  		var dt = data.title;
  		var t = document.getElementById(dt);
		t.checked = true;
  	}
  	
  	var obj,obj1,j;
	var nowPage=0;//当前页
	var listNum=10;//每页显示<ul>数
	var PagesLen;//总页数
	var page_number =$("#page_number");
	 $(document).ready(
	 function(){
		obj=$(".problems_con");
		obj1=$("h3");
		j=obj.length;
		PagesLen=Math.ceil(j/listNum);
		firstPage(0);
	}
	);
	
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
		$(window).scrollTop(0);
		}else{
			alert("您本页有题没答，请答完后翻页");
			p.value="";
		}
	}
	} 
	
	var time=5400;  
	function jishi(){  
		var t;		
		if(time>0){  
			if(time%60<10){
				t = "0"+time%60;
			}else{
				t= time%60;
			}
			if(time>3600){
				document.getElementById("show").innerHTML="距离交卷还有<font color=red size=18>"+Math.floor(time/3600)+":"+Math.floor((time-3600)/60)+":"+t+"</font>";
			}else if(time<=3600&&time>60){
				document.getElementById("show").innerHTML="距离交卷还有<font color=red size=18>00:"+Math.floor(time/60)+":"+t+"</font>";
			}else if(time<=60){
				document.getElementById("show").innerHTML="距离交卷还有<font color=red size=18>00:00:"+t+"</font>";
			}   
			time--;  
		}else{
			alert("考试时间已到，由于没有提交，本次考试作废");
			window.location.href="test_page.jsp";
		}
		 
	};
	
	window.setInterval(jishi,1000);
	
  </script>
</html>


