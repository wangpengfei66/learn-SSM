<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h2>数据库异常</h2>
    <%
    	String message = exception.getMessage();
    	//send。。。
    	out.println(message);
    	int code = (Integer)request.getAttribute("javax.servlet.error.status_code");
    	out.print("</br>code" + code);
    	Class clazz = (Class)request.getAttribute("javax.servlet.error.exception_type");
    	out.print("</br>calzz" + clazz.getName());
    %>
</body>
</html>