<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://xmlns.jcp.org/jsf/html">
<h:head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<link href="css/qrlayout.css" rel="stylesheet" type="text/css"/>
	<title>Reviews</title>
</h:head>
<h:body>
<center><h1>Reviews for Product 1</h1></center>
<form name="login" action="ReviewServlet" method="post" onsubmit="checks(this)">
		<center><div style="border: 1px solid; width: 500px; height: 350px; box-shadow: 2px 2px 4px 1px #000000;">
	<table class="table1">
	<tr>
		<td><p>Headline:</p> </td>
		</tr>
		<tr>
		<td><input type="text" name="headline"/></td>
		</tr>
		<tr>
		<td><p>Review:</p> </td>
		</tr>
		<tr>
		<td style="padding-bottom: 10px;"><textarea name="review"></textarea></td>
		</tr>
		<tr>
		<td><button type="submit" value="Submit">SUBMIT</button> <button type="reset">RESET</button></td>
		</tr>
		</table>
		<br/>
		</div></center>
		</form>
</h:body>
</html>