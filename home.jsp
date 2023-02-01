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
</head>
<body>

	<div class="container">
		<h1>Namaste, <c:out value="${user.name}"/></h1>
		<div class="logout">
		<a href="/logout" class="course-link">logout</a>
		</div>
		<h3>Course Schedule:</h3>
		
		
		<table class="table">
		
			<tr>
				<th>Class Name</th>
				<th>Instructor</th>
				<th>Weekday</th>
				<th>Price</th>
			</tr>
			<c:forEach var="course" items="${courses}">
				<tr>
					<td><a href="courses/${course.id}"><c:out value="${course.name}"/></a> <c:if test = "${course.user.id ==userId}">
		<a href="courses/edit/${course.id}" class="course-link">edit</a></c:if> </td>
					<td><c:out value="${course.user.name}"/></td>
					<td><c:out value="${course.day}"/></td>
					<td>$<c:out value="${course.price}"/></td>
				</tr>
			
			</c:forEach>
		
		</table>
	</div>
	<a href="/addCourse" class="course-link">+new course </a>
</body>
</html>