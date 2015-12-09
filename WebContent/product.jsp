<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://xmlns.jcp.org/jsf/html">
	<!-- @Author: Charlotte & Joel -->
<h:head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<link href="css/qrlayout.css" rel="stylesheet" type="text/css" />
	<title>QR - ${currentProduct.name}</title>
</h:head>
<h:body>

	<h2>${currentProduct.name}</h2>

	${currentProduct.text}
	<br />
	<br />
	
	<div class="reviewDivInProduct">
		<form action="ReviewServlet" method="post">
			<a href="reviews.jsp" target="main">Write review</a>
			<input type="button" class="productbutton" value="grade: ${currentProduct.grade}/10" />
		</form>
		<br />
		<!-- Kanske sortera reviews så högst betygsatta review är överst -->
		<c:forEach var="review" items="${productReviews}">
			<div>
				<b>${review.title}</b> <br /> ${review.text}
			</div>
			<br />
		</c:forEach>
	</div>

</h:body>
</html>