<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Yoga Course</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css"> 
</head>
<body>

	<div class="container">
		<h1><c:out value="${course.name}"/> with <c:out value="${course.user.name}"></c:out></h1>
		
		<h3>Join in!</h3>
		<div class="selection-link">
		<a href="/home" class="course-link">Course Selection</a>
		</div>
		<div>
			<h4>Day: <c:out value="${course.day}"/></h4>
			<h4>Cost: $<c:out value="${course.price}"/></h4>
		</div>
		<div>
			<p><c:out value="${course.description}"></c:out></p>
		</div>
		
	</div>

</body>
</html>