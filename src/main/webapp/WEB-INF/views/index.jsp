<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@	page isELIgnored="false"%>
<%@	taglib	prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
<title>PhysioApp</title>
	
	
</head>
<%@	include file="header.jsp"%>
<body>

<div class="jumbotron">
 	<h2 class="text-center">PHYSIO-APP created by MATEUSZ MEISNER</h2>
 	<p class="text-center">It is my final project of Java Web Developer Course at Coders Lab Programming School.<br>
 	What you see is a demonstration version with fake patient data so feel free to use all the funcionality.</p>
</div>
</body>
</html>
