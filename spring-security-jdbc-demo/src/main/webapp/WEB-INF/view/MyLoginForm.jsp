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
<style>
 .failed{
  color:red;
 }
  .logout{
 	color:green;
 }
</style>
</head>

<body>
 <p>This is my custom form login</p>
 <form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
 <c:if test="${param.error != null}">
  <i class="failed">Sorry!!! You cannot login </i>
 </c:if>
 <c:if test="${param.logout != null}">
  <i class="logout">You are logout</i> 
 </c:if>
 
 <p>
 	User name: <input type="text" name ="username">
 </p>
 <p>
    Password : <input type="password" name ="password">
 </p>
 <input type="submit" value="Login">
 
 </form:form>

</body>
</html>