<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>	

<%--Des d'aquí fins al tag "<body>" quedarà substituït pel header --%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>eWok Staff</title>
	<link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body>
	<h1>Home seccio Unitat</h1>
	<p>El controlador diu: ${msg}</p>
	
	<%-- Inici del header --%>            
		<%--	
			<c:import url="imports/headerStaff.jsp"></c:import>
		--%>	           
	<%-- Fi del header --%> 
	
	<p>
		Id: ${unitat.id}<br/>
		Nom: ${unitat.nom}<br/>
		Acrònim: ${unitat.acron}<br/>
	</p>

	<p>Anar a <a href="staff?accioProductes=llistat">gestió productes</a><p>
	<p>Anar a <a href="staff?accioClients=llistat">gestió clients</a><p>
	<p>Anar a <a href="staff?accioUnitat=llistat">gestió unitats</a><p>
	<p>Anar <a href="exemple">exemple</a><p>
	
	<%-- Inici del footer --%>            
		<%--
			<c:import url="imports/footerStaff.jsp"></c:import>
		--%>           
	<%-- Fi del footer --%>  
</body>
</html>






