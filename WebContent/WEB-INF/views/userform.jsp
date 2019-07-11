<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>User Form</h1>
	
	<spring:url value="/login" var="userActionUrl"/>
	
	<form:form method="post" action="${userActionUrl}" modelAttribute="user">
		<form:input path="emailUser" type="text" />
		<form:errors path="emailUser"/>
		<form:input path="passwordUser" type="text" />
		<form:errors path="passwordUser"/>
		
		<form:button>Enviar</form:button>
	</form:form>
</body>
</html>