<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>eWok Staff</title>
</head>
<body>
	<h1>Home secció clients</h1>
	<p>El controlador diu: ${msg}</p>
	<p>Anar a <a href="staff?accioProductes=llistat">gestió productes</a><p>
	<p>Anar a <a href="staff?accioClients=llistat">gestió clients</a><p>
	<p>Anar a <a href="staff?accioUnitat=llistat">gestió unitats</a><p>
	<p>Anar <a href="exemple">exemple</a><p>
</body>
</html>







