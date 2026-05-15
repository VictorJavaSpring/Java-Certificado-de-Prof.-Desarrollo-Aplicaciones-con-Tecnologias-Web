<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" 
           uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
		   uri="http://java.sun.com/jsp/jstl/functions" %>     

<%--Des d'aquí fins al tag "<body>" quedarà substituït pel header --%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>eWok Staff</title>
	<link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body> 
           
<%-- Inici del header --%>            
	<%--
		<c:import url="imports/headerStaff.jsp"></c:import>  
	--%>         
<%-- Fi del header --%>       
    
	<h1>Llistat secció Unitats</h1>
	<p>El controlador diu: ${msg}</p>
	<p>Numero d'unitats: ${fn:length(unitats)}</p>
	<p>Llistat d'unitats</p>
		<table>
			<c:forEach items="${unitats}" var="unitat">
				<tr>
					<td>
						<a href="staff?accioUnitat=veure&id=${unitat.id}">
							<c:out value="${unitat.id}"/>
						</a>
					</td>
					<th>Id</th>
					<th>Nom</th>
					<th>Acrònim</th>
				</tr>
				<tr>
					<td>${unitat.id}</td>
					<td>${unitat.nom}</td>
					<td>${unitat.acron}</td>
					<td>
						<a href="staff?accioUnitat=esborrar&id=${unitat.id}"><fmt:message key="esborrar"/></a>
					</td>
					<td>
						<a href="staff?accioUnitat=prepararModificacio&id=${unitat.id}"><fmt:message key="modificar"/></a>
					</td>
				</tr>
			</c:forEach>
		</table>
	<p>Anar a <a href="staff">Home</a><p>
	<p>Anar a <a href="staff?accioClients=llistat">Gestió Clients</a><p>
	<p>Anar a <a href="staff?accioProductes=llistat">Gestió Productes</a><p>
	<p>Anar a <a href="staff?accioUnitat=prepararAlta">Nova Unitat</a><p>
	<p>Anar a <a href="exemple">exemple</a><p>
	
<%-- Inici del footer --%>      
     <%-- 
		<c:import url="imports/footerStaff.jsp"></c:import>  
     --%>    
<%-- Fi del footer --%>     
</body>
</html>







