<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">
	<a href="${pageContext.request.contextPath}/home">Home</a> | &nbsp;<a
		href="${pageContext.request.contextPath}/user-info">User Info</a>
	|&nbsp; <a href="${pageContext.request.contextPath}/admin">Admin</a>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
	| &nbsp;<a href="${pageContext.request.contextPath}/logout">Logout</a>
	</c:if>
	<c:if test="${pageContext.request.userPrincipal.name == null}">
	| &nbsp;<a href="${pageContext.request.contextPath}/login">login</a>
	</c:if>
</div>