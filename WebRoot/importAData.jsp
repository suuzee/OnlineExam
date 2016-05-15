<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<SCRIPT language="JavaScript" src="/js/pos.js"></SCRIPT>
<SCRIPT language="JavaScript" src="/policy/js/pub.js"></SCRIPT>
<script type="text/javascript" src="/policy/js/date/WdatePicker.js"></script>
<SCRIPT language="JavaScript" src="/aviation/js/customer.js"></SCRIPT>
<SCRIPT language="JavaScript" src="/policy/js/ctrl.js"></SCRIPT>
<script language="JavaScript" src="/Mtree/MzTreeView10.js"></script>
<link rel="stylesheet" href="/css/content-base.css?v=110921" /> 
<link rel="stylesheet" href="/css/content-ishow.css?v=110921" />
<link href="/policy/images/skin.css" rel="stylesheet" type="text/css" />
<title>题库导入</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> 
</head>
<%
String flag = (String)request.getAttribute("flag");
String count = (String)request.getAttribute("count");
String upfileName = (String)request.getAttribute("upfileName");
String path = request.getContextPath();
 %>
<SCRIPT language="JavaScript" src="/js/pub.js"></SCRIPT>

<body onload ="flag()">
<a href="<%=path%>/manage.jsp">首页</a>
<div align="left" style="position:relative;width:800px;height:800px;">
<form name="fm" action="<%=path %>/jEngin.jsp" method='post' ENCTYPE="multipart/form-data">    
   <input type="hidden" name="upLoadModelDocumentId" value="">
   <input type="hidden" name="flag" value="<%= flag%>">
   <input type="hidden" name="count" value="<%= count%>">
   <input type="hidden" name="upfileName" value="<%= upfileName%>">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="10"></td>
      </tr>
    </table>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" >
      <tr>
        <td>
        	 <table border="0" cellpadding="0" cellspacing="5" align="center" width="800" style="font-size:13px;">
		      <tr>
		        <td style="padding-left:15px;">数据导入：</td>
		         <td colspan="5">
		         	<input type="text" name="txt" readonly style="width:375px;height:20px; font-size:12px; line-height:16px; border:1px #ffb200 solid" />
					<input type="button" value="浏览" size="30" onclick="fileName.click()"/>
					<input type="file" id="fileName" name="fileName" onchange="changeFile(this)"  style="position:absolute;filter:alpha(opacity=0);-moz-opacity:0;opacity:0;margin-left:-100px;" size="1"/>
				</td>                 
		       </tr>
		     </table>
        </td>
      </tr>
    </table>
  
     <table width="100%" border="0">
       <tr>
         <td height="10"></td>
       </tr>
     </table>	  
     <table width="100%" border="0">
       <tr>
         <td height="20"></td>
       </tr>
     </table>
     <table width="80%" border="0" align="center">
       <tr>
         <td width="20%"></td>
         <td width="20%">
         	<div align="center">
         		<input type="button" class="operation"  value=" 导  入 "  onclick="subPage()">
         	</div>
         </td>
     <td width="20%"><br></td>
     </tr>
   </table>
</form>      
</div>
<script language="javascript">
var objArray=new Array();

function flag()
{    
	var count = document.getElementsByName("count")[0].value;
	var flag = document.getElementsByName("flag")[0].value;

	if(flag=="0")
	{
	alert("上传数据库成功，共上载"+count+"条数据");
	}

}


function changeFile(obj)
{
	var form = document.all.fm;
	form.txt.value=obj.value;
}
function subPage()
{
	var form = document.all.fm;
	var fileName = form.fileName.value;

	if(fileName=="")
	{
	  	alert("请先选择要上传的文件!");
	  	return;
	}
	if(fileName.toLowerCase().indexOf(".xls")<0)
	{
	  	alert("上传的文件应该为xls格式!");
	  	return;
	}  
	fileName = fileName.substring(fileName.lastIndexOf("\\")+1);    
	form.upLoadModelDocumentId.value=fileName;
	
	document.all.fm.submit();
	  
}

</script>
</body>
</html>