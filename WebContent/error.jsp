<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	session = "false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://xmlns.jcp.org/jsf/html">
<h:head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<link href="css/qrlayout.css" rel="stylesheet" type="text/css"/>
	<title>Error Page</title>
</h:head>
<h:body>
<center><h3>Something went terribly wrong!</h3></center>
<br/>
<center>
<form>
<input type="button" value="< Back" onClick="history.go(-1);return true;">
</form>
</center>
</h:body>
</html>