<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page session="false"%>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
</head>
<body>
    <jsp:include page="common/_menu.jsp" />
    <h1>Message : ${message}</h1>
</body>
</html>