<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://xmlns.jcp.org/jsf/html">
<h:head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<link href="css/qrlayout.css" rel="stylesheet" type="text/css" />
	<title>QR - ${product.name}</title>
</h:head>
<h:body>

	<h2>${product.name}</h2>

	<b>Info:</b> some text here about the product, so ill just
		blurt letter for the rest of the bit or whatever.. <br /> adnaugeau f
		awdaw dagpjeaigj ad apifsjfiwa bouranigjepa pdwj angenaon
		faunfuanfeoaf afaiej ae fjaeijaf a9fjaepigna gnnaeugbaegparogj apenf
		aeouean aogreaåfojeaå mai aonwidnaw idjaw+ a+gj ae+ign ae+iganwida
		d+wajwfd+9a jidakmmwdöawmåpirhgur
	<br />
	<br />
	
	<div class="reviewDivInProduct">
		<form action="ReviewServlet" method="post">
			<input type="button" class="productbutton" value="read reviews" /> 
			<input type="button" class="productbutton" value="write review" /> 
			<input type="button" class="productbutton" value="grade: ${product.grade}/10" />
		</form>
		<br />
		<!-- Kanske sortera reviews så högst betygsatta review är överst -->
		<c:forEach var="review" items="${productReviews}">
			<div>
				<b>${review.title}</b> - ${review.grade}/10 <br /> ${review.text}
			</div>
			<br />
		</c:forEach>
	</div>

</h:body>
</html>