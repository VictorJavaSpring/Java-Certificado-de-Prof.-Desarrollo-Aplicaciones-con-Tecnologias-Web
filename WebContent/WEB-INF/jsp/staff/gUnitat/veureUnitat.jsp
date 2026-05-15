<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" 
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
		   uri="http://java.sun.com/jsp/jstl/functions" %>     
<c:if test="${flag==null}">
	<c:redirect url="/staff"/>
</c:if>
<%-- Internacionalització --%>
<fmt:setLocale value="${idioma}"/>
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.unitat.veureUnitat"/>

<%--Des d'aquí fins al tag "<body>" quedarà substituït pel header --%>
<%--
	<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>eWok Staff/<fmt:message key="titolHead"/></title>
		<link rel="stylesheet" type="text/css" href="css/main.css"/>
	</head>
	<body> 
--%>  

<%-- Inici del header --%>            
	<c:import url="../imports/headerStaff.jsp"></c:import>        
<%-- Fi del header --%> 

<%-- ****LLISTAT UNITAT (inici)**** --%> 
	<%-- El controlador diu --%>	
	<div class="parlaControlador">
		<h6><fmt:message key="parlaControlador">
				<fmt:param value="${msg}"/>
			</fmt:message>
		</h6>
	</div>
	
	<%-- Opcions a d'altres gestors (inici)--%>
	<div class="nav nav-tabs navGestors">
		<ul class="nav nav-tabs navGestors">
		<li role="presentation">
		   	<a class="home" href="staff"><i class="fa fa-info-circle"></i>
		   		<fmt:message key="home"/>
		   	</a>
		</li>
		<li role="presentation">
	   		<a href="staff?accioFormaPagament=llistat">
		   		<fmt:message key="gFormaPagament"/>
		   	</a>
		</li>
		<li role="presentation">
		   	<a href="staff?accioOfertaComanda=llistat">
		   		<fmt:message key="gOfertaComanda"/>
		   	</a>
		</li>
		<li role="presentation">
	   		<a href="staff?accioOfertaProducte=llistat">
		   		<fmt:message key="gOfertaProducte"/>
		   	</a>
		</li>
		<li role="presentation">
		   	<a href="staff?accioOfertaPunts=llistat">
		   		<fmt:message key="gOfertaPunts"/>
		   	</a>
		</li>
		<li role="presentation">
	    	<a href="staff?accioPreu=llistat">
		    	<fmt:message key="gPreu"/>
	    	</a>
		</li>
		<li role="presentation">
		  	<a href="staff?accioProducte=llistat">
	    		<fmt:message key="gProducte"/>
	    	</a>
	    </li>	
		<li role="presentation">
	    	<a href="staff?accioRol=llistat">
	    		<fmt:message key="gRol"/>
	    	</a>
	      </li>
		<li role="presentation">
	    	<a href="staff?accioTipusProducte=llistat">
	    		<fmt:message key="gTipusProducte"/>
	    	</a>
	    </li>
	    <li role="presentation" class="active">
	    	<a class="unitat" href="staff?accioUnitat=prepararAlta">
		   		<fmt:message key="nUnitat"/>
		   		<span><fmt:message key="nUnitatSub"/></span>
		   	</a>
		</li>
		<li role="presentation">
		   	<a href="staff?accioUsuari=llistat">
		   		<fmt:message key="gUsuari"/>
		   	</a>
	    </li>
		</ul>
	</div>
	<%-- Opcions a d'altres gestors (fi)--%>
		
	<%-- Separador --%>
	<div class="separador"></div>	
	
	<div class="liniaUnitat">	
	
		<!-- capçalera de pàgina (inici)-->
			<div class="container containerUnitat">
				<!-- Default Heading -->
				<div class="default-heading defUnitat">
					<div class="inner-default-heading">
					<!-- Crown image -->
					<img class="img-responsive" src="img/crown.png" alt="" />
					<!-- Heading -->
					<h2><fmt:message key="homeUnitat"/></h2>
					<p>- <fmt:message key="homeUnitat.subtitol"/> -</p>
					<!-- Paragraph -->
					<p>( <fmt:message key="partPub.pre"/>
						 <a href="exemple">
						 <fmt:message key="partPub.center"/></a><fmt:message key="partPub.post"/> )<p>
					<!-- Border -->
					<div class="border"></div>
					</div>
				</div>						
			</div>		
		<!-- capçalera de pàgina (fi)-->
	
		<div class="veureUnitat">
			<!-- Heading -->
			<%-- El títol "Llistat" --%>
			<h2><fmt:message key="titolH1"/></h2>
			
			<%-- Número d'unitats --%>
			<p>
			<h4><fmt:message key="triaUnitat">
				<fmt:param value="${unitat.id}"/>
			</fmt:message></h4>
			</p>
		
				<%-- Unitat a veure --%>
				<table class="tableVeureUnitat">
					<%-- Id --%>
					<tr>
						<td class="col1"><fmt:message key="id"/></th>
						<td class="col2">${unitat.id}</th>
					</tr>
					<%-- Nom --%>
					<tr>
						<td class="col1"><fmt:message key="nom"/></td>
						<td class="col2">${unitat.nom}</td>
					</tr>
					<%-- Acrònim --%>
					<tr>
						<td class="col1"><fmt:message key="acron"/></td>
						<td class="col2">${unitat.acron}</td>
					</tr>
				</table>
				
				<%-- Separador --%>
				<div class="separador"></div>	
		
				
				<%-- botó veure --%>
				<div class="botonsVeure">
					<a class="btn btn-info btn-xs active"
						href="staff?accioUnitat=llistat">
						<i class="fa fa-search-minus fa-lg"></i>
						<fmt:message key="llistat"/>
					</a>	
					
					<%-- botó esborrar --%>
					<a class="btn btn-danger btn-xs active" 
						href="staff?accioUnitat=esborrar&id=${unitat.id}">
						<i class="fa fa-trash-o fa-lg"></i>
						<fmt:message key="esborrar"/>
					</a>
					
					<%-- botó modificar --%>
					<a class="btn btn-success btn-xs active"
						href="staff?accioUnitat=prepararModificacio&id=${unitat.id}">
						<i class="fa fa-pencil-square-o fa-lg"></i>
						<fmt:message key="modificar"/>
					</a>
				</div>			
		</div>	
	</div>			
	
<%-- ****VEURE UNITAT (fi)**** --%>	

	<%-- Separador --%>
	<div class="separador"></div>	
	
	<%-- Opcions a d'altres idiomes --%>
	<nav class="navbar navbar-inverse">
		<div class="menuIdioma">
			<fmt:message key="Idioma"/>
		    <a href="staff?accioUnitat=veure&id=${unitat.id}&idioma=ca"><fmt:message key="ca"/></a> | 
		    <a href="staff?accioUnitat=veure&id=${unitat.id}&idioma=es"><fmt:message key="es"/></a> | 
		    <a href="staff?accioUnitat=veure&id=${unitat.id}&idioma=en"><fmt:message key="en"/></a> |
		    <a href="staff?accioUnitat=veure&id=${unitat.id}&idioma=it"><fmt:message key="it"/></a>
		</div>
	</nav>

	<%-- Scripts (inici) --%>
		<script src = "jseWok/tancarAlerts.js"></script>
	<%-- Scripts (fi) --%>
						


	<%-- Inici del footer --%>            
		<c:import url="../imports/footerStaff.jsp"></c:import>
	<%-- Fi del footer --%>  
</body>
</html>






