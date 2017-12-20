<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@	page isELIgnored="false"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PhysioApp</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery.ui.all.css" type="text/css" media="screen">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
</head>
<%@	include file="header.jsp"%>
<body>

	<div class="jumbotron">
		<h2 class="text-center">EDIT VISIT FORM</h2>
	</div>
	
	<div class="container center_div">
		<div class="col-sm-4 col-sm-offset-4">
			<a href="<c:url value="/calendar"/>" class="btn btn-default">Go back <span class="glyphicon glyphicon-arrow-left"></span></a>
			<hr class="style-one">
			<form:form method="post" modelAttribute="visit">
			Patient:<br>
				<form:select path="patient" class="form-control">
					<form:option value="${visit.patient.id}"
						label="${visit.patient.firstname} ${visit.patient.lastname}" />
					<form:options items="${patients}" itemValue="id"
						itemLabel="lastname" />
				</form:select>
				<form:errors path="patient" class="error" />
				<br>
			Date:<br>
				<form:input path="date" class="form-control datepicker"/>
				<form:errors path="date" class="error" />
				<span class="error">${message}</span>
				<br>
			Time:<br>
				<form:select path="hour" class="form-control">
					<form:option value="9" label="9:00" />
					<form:option value="10" label="10:00" />
					<form:option value="11" label="11:00" />
					<form:option value="12" label="12:00" />
					<form:option value="13" label="13:00" />
					<form:option value="14" label="14:00" />
					<form:option value="15" label="15:00" />
					<form:option value="16" label="16:00" />
					<form:option value="17" label="17:00" />
					<form:option value="18" label="18:00" />
				</form:select>
				<br>
				<form:errors path="hour" class="error" />
			Service:<br>
				<form:select path="service" class="form-control">
					<form:option value="${visit.service.id}"
						label="${visit.service.name}" />
					<form:options items="${services}" itemValue="id" itemLabel="name" />
				</form:select>
				<form:errors path="service" class="error" />
				<br>
				<input type="submit" value="Edit" class="btn btn-default"/>
			</form:form>
		</div>
	</div>
	
</body>
<script type="text/javascript" src="<c:url value="/js/datepicker.js"/>"></script>
</html>