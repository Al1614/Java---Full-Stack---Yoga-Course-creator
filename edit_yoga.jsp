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
	<title>Yoga Courses</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
<title>Edit Yoga</title>
</head>
<body>
<div class="container">

	<h1><c:out value="${course.name}"></c:out></h1>

	<div class="container">
		<form:form action="/courses/edit/${id}" modelAttribute="course" class="form" method="post">
		
			<div class="form-row">
			 	<form:errors path="name" class="error"/>
				<form:label  path="name">Name:</form:label>
				<form:input  path="name" class="form-control" value="${course.name}"/>
			</div>
			
			<div class="form-row">
				<form:errors path="day" class="error"/>
				<form:label  path="day">Day:</form:label>
				<form:input  path="day" class="form-control" value="${course.day}"/>
			</div>
			
			<div class="form-row">
				<form:errors path="price" class="error"/>
				<form:label path="price">Price:</form:label>
				<form:input type="number" path="price" class="form-control" value="${course.price}"/>
			</div>
			
			<div class="form-row">
				<form:errors path="description" class="error"/>
				<form:label  path="description">Description:</form:label>
				<form:textarea path="description" rows="6" columns="10" class="form-control" value="${course.description}"/>
			</div>
			
			<div class="form-row">
				<form:errors path="user" class="error"/>
				<form:input type="hidden" path="user" value="${user.id}" class="form-control"/>
			</div>
			
			<div class="form-row">
				<input type="submit" value="Submit" class="btn-primary"/>
				<a href="/home" class="course-link">Cancel</a>
			</div>
			<div class="delete">
				<a href="/courses/delete/${course.id}" class="delete-link">DELETE</a>
			</div>
		</form:form>
	</div>

</div>
</body>
</html>