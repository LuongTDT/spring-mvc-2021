<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value = '/resources/css/styles.css' />" rel="stylesheet" />
<title>${title}</title>
</head>
<body>
<jsp:include page="common/_menu.jsp" />
	<h1>Message : ${message}</h1>
</body>
</html>