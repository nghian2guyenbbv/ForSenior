<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>
   User:<sec:authentication property="principal.username"/>
   <br><br>
   Roles:<sec:authentication property="principal.authorities"/>
 </p>
<p>Hello guys! Welcome to my Home page</p>
<form:form action="${pageContext.request.contextPath}/logout" method="POST">
<input type="submit" value="Logout">
</form:form>
<sec:authorize access="hasRole('MYWIFE')">
<a href="${pageContext.request.contextPath}/myWife" >Be my wife</a> <br>
</sec:authorize>
<sec:authorize access="hasRole('ADMIN')">
<a href="${pageContext.request.contextPath}/adminMetting" >Admin meeting</a> <br>
</sec:authorize>
<sec:authorize access="hasRole('SYSADMIN')">
<a href="${pageContext.request.contextPath}/systemMetting" >System meeting</a>
</sec:authorize>
</body>
</html>