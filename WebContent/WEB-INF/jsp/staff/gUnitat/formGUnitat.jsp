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
	<c:if test="${unitat == null }">
		<title>Alta unitat</title>
	</c:if>
	<c:if test="${unitat != null }">
		<title>Modificació unitat</title>
	</c:if>
	<link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body>
	<%-- Inici del formGUnitat --%> 
	<h1>Home secció Unitats</h1>
	<p>El controlador diu: ${msg}</p>
	<p>Anar a <a href="staff?accioProductes=llistat">gestió productes</a><p>
	<p>Anar a <a href="staff?accioClients=llistat">gestió clients</a><p>
	<p>Anar a <a href="staff?accioUnitat=llistat">gestió unitats</a><p>
	<p>Anar <a href="exemple">exemple</a><p>
	
	<h1>Modificació de la unitat ${unitat.id}</h1>

	<c:if test="${unitat == null }">
		<c:set scope="request" var="titol" value="Alta unitat"/>
	</c:if>
	<c:if test="${unitat != null }">
		<c:set scope="request" var="titol" value="Modificació de la unitat ${unitat.id}"/>
	</c:if>
	<%-- Inici del header --%>            
		<%--	
			<c:import url="imports/headerStaff.jsp"></c:import>
		--%>	           
	<%-- Fi del header --%>  
	<p>
		<c:if test="${unitat == null }">
			<form action="staff?accioUnitat=alta" method="post">
		</c:if>
		<c:if test="${unitat != null }">
			<form action="staff?accioUnitat=modificacio" method="post">
		</c:if>
			<!-- Id -->
			<p>
			<label for="id">Id:</label>
			<input type="hidden" value="${unitat.id}" name="id"/>
			</p>
			
			<!-- Nom -->
			<p>
			<label for="nom">Nom:</label>
			<c:if test="${unitat == null }">
				<input type="text" name="nom" value="${param.nom}"/>
			</c:if>
			<c:if test="${unitat != null }">
				<input type="text" name="nom" value="${unitat.nom}"/>
			</c:if>
			<span class="error">${errorNom}</span><br/>
			</p>
			
			<!-- Acronim -->
			<p>
			<label for="acronim">Acrònim:</label>
			<c:if test="${unitat == null }">
				<input type="text" name="acron" value="${param.acron}"/>
			</c:if>
			<c:if test="${unitat != null }">
				<input type="text" name="acron" value="${unitat.acron}"/>
			</c:if>			
			<span class="error">${errorAcron}</span><br/>
			</p>
			
			<input type="submit" value="Enviar"/>
			<%-- <a href="staff?accioUnitat=prepararAlta">Reset</a> --%>
		</form>
		<c:if test="${unitat == null }">
			<form action="staff?accioUnitat=prepararAlta" method="post">
		</c:if>
		<c:if test="${unitat != null }">
			<form action="staff?accioUnitat=prepararModificacio&id=${unitat.id}" method="post">
		</c:if>			
			<input type="submit" value="Reset"/>
		</form>
	</p> 
<%-- Inici del footer --%>            
	<%--	
		<c:import url="imports/footerStaff.jsp"></c:import>
	--%>	           
<%-- Fi del footer --%>  
</body>
</html>












