<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	session = "true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://xmlns.jcp.org/jsf/html">
<h:head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<link href="css/qrlayout.css" rel="stylesheet" type="text/css" />
	<title>Products</title>
</h:head>
<h:body>

	<form action="ProductServlet" method="post">
		<c:forEach var="product" items="${products}">

			<div class="productdiv">

				<img src="${product.imgURL}" alt="placeholder" class="productimg" />

				<input type="submit" name="productname" class="productbutton" value="${product.name}" /><br />
			</div>

		</c:forEach>
	</form>

</h:body>
</html>