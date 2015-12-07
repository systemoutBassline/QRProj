<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	session = "true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://xmlns.jcp.org/jsf/html">
<h:head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<link href="css/qrlayout.css" rel="stylesheet" type="text/css" />
	<title>QualityRunner</title>
</h:head>
<h:body>
	<center>
		<div class="navcontainer">
			<div class="navbar">
				<ul>
					<li><a href="./ProductServlet" target="main">Products</a></li>
					<li><a href="./ReviewServlet" target="main">Reviews</a></li>
				</ul>
			</div>

			<div class="main" width="600">
				<iframe class="mainbox1" src="reviews.jsp" name="main" width="600"
					height="550"></iframe>
			</div>
		</div>
	</center>
</h:body>
</html>