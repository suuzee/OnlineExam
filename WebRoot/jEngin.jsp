<%@page errorPage="/ErrorPage"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="com.jspsmart.upload.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
  String path = request.getContextPath();
  SmartUpload smartUpload = new SmartUpload();
  smartUpload.initialize(pageContext);
  smartUpload.upload();
  String fileName = smartUpload.getRequest().getParameter("upLoadModelDocumentId");
  try
  {
		int fileSize = smartUpload.getSize();
		com.jspsmart.upload.File myFile = smartUpload.getFiles().getFile(0);
		fileName = getServletContext().getRealPath("/upload/"+fileName);
		System.out.println(fileName);
		myFile.saveAs(fileName,SmartUpload.SAVE_PHYSICAL);

  }
  catch(Exception e){
  	e.printStackTrace();
  	fileName="";
  }
%>
<html>
<head>
<title>题库导入</title>
</head>
<body>
<FORM action="uploadfile.do" method=post name=fm target="_self">
<input type="hidden" name="fileName" id="fileName" value="<%=fileName%>">
<input type="submit" onclick="sub();">
</FORM>
</body>
<SCRIPT>
	function sub(){
		document.form.submit();
	}

</SCRIPT>
</html>
