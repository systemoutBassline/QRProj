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
	<title>QualityRunner</title>
</h:head>

<h:body>

	<center><h1>Welcome to QualityRunner!</h1></center>
	
<form action="LoginServlet" method="post">
<center><div style="border: 1px solid; width: 30%; box-shadow: 2px 2px 4px 1px #000000;"><center><h2>Sign in</h2></center>
	
	<table>
	<tr>
		<td>Username: </td><td><input type="text" name="username" placeholder="Username" /> </td>
		</tr>
		<tr>
		<td>Password: </td><td><input type="password" name="password" placeholder="Password" /></td>
		</tr>
		</table>
		<center><button type="submit" value="Submit">SIGN IN</button>
		<button type="reset">RESET</button></center>
		<br/>
		</div></center>
		</form>
	
	
	<br/>
	<form action="RegisterServlet" method="post">
	<center><div style="border: 1px solid; width: 30%; box-shadow: 2px 2px 4px 1px #000000;">
	<center><h2>Registration</h2></center>
	<table>
	<tr>
	<td>Username:</td><td> <input type="text" name="username" placeholder="Username" /></td>
	</tr>
	<tr>
	<td>Password: </td><td><input type="password" name="password" placeholder="Password" /></td>
	</tr>
	<!--<tr>
	<td>Password: </td><td><input type="password" name="password2" placeholder="Password" /></td>
	</tr>-->
		</table>
		<center><button type="submit" value="Submit">SUBMIT</button>
		<button type="reset">RESET</button></center>
		<br/>
		</div></center>
		</form>

</h:body>

</html>