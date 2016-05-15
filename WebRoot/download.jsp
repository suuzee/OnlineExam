<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.FileInputStream"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

 java.io.BufferedInputStream bis=null;
  java.io.BufferedOutputStream  bos=null;
try{
 String filename=request.getParameter("fileName"); 
  String fileName=request.getParameter("filename"); 
 filename = URLDecoder.decode(filename,"UTF-8");
 response.setContentType("application/x-msdownload");
 String[] a = filename.split("\\.");
 String b = a[1];
 response.setHeader("Content-disposition","attachment; filename="+new String(fileName.getBytes("gb2312"),"iso8859-1")+"."+b);
 bis =new java.io.BufferedInputStream(new java.io.FileInputStream(config.getServletContext().getRealPath("upload/" + filename)));
 bos=new java.io.BufferedOutputStream(response.getOutputStream()); 
 byte[] buff = new byte[2048];
 int bytesRead;
 while(-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
  bos.write(buff,0,bytesRead);
 }
}
catch(Exception e){
 e.printStackTrace();
}
finally {
 if (bis != null)bis.close();
 if (bos != null)bos.close();}
 out.clear();  
 out = pageContext.pushBody();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'download.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
