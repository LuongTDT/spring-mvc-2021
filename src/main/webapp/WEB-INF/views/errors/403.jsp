<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Access Denied</title>
</head>
<body>
	<jsp:include page="../common/_menu.jsp" />
	<h3 style="color: red;">${message}</h3>
</body>
</html>