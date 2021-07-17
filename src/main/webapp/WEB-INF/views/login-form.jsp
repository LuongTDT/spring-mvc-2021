<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value = '/resources/css/styles.css' />" rel="stylesheet"/>
<title>Salary System</title>
</head>
<body>

<div id="login-container">
	<form action="<%= request.getContextPath() %>/login" method="POST">
		<div>
			<label>Username</label>
			<input name="username" type="text" id="username" />
		</div>
		<div>
			<label>password</label>
			<input name="password" type="password" id="password" />
		</div>
		<div><input name="login" type="submit" id="login" value = "login" style ="cursor: pointer" /></div>
		<div><span>${message }</span></div>
	</form>
</div>

</body>
</html>