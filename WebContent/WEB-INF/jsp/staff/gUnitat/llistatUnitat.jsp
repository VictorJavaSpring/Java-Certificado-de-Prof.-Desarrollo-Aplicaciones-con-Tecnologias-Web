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
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.unitat.llistatUnitat"/>

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
		
		
		<div class="llistatUnitat">		
			<!-- Heading -->
			<%-- El títol "Llistat" --%>
			<h2><fmt:message key="titolH1"/></h2>
		
			<%-- Número d'unitats --%>
			<p>
				<c:if test="${fn:length(unitats) == 1}">
					<h4><fmt:message key="numSingularUnitats">
						<fmt:param value="${fn:length(unitats)}"/>
					</fmt:message></h4>
				</c:if>
				<c:if test="${fn:length(unitats) > 1}">
					<h4><fmt:message key="numPluralUnitats">
						<fmt:param value="${fn:length(unitats)}"/>
					</fmt:message></h4>
				</c:if>
			</p>
					
			<%-- Taula Unitats (llistat) --%>
			<table class="tableLlistatUnitat cap">
				<tr>
					<th class="col1"><fmt:message key="id"/></th>
					<th class="col2"><fmt:message key="nom"/></th>
					<th class="col3"><fmt:message key="acron"/></th>
					<th class="col4"><fmt:message key="veure"/></th>
									 <%-- "veure" queda ocult per css --%>
					<th class="col5"><fmt:message key="esborrar"/></th>
									 <%-- "esborrar" queda ocult per css --%>
					<th class="col6"><fmt:message key="modificar"/></th>
									 <%-- "modificar" queda ocult per css --%>
				</tr>
			</table>
			<div class="scroll">
				<table class="tableLlistatUnitat cos">
					<c:forEach items="${unitats}" var="unitat">
						<tr>
							<td class="col1">
								<a alt="veure unitat"
									href="staff?accioUnitat=veure&id=${unitat.id}">
									<c:out value="${unitat.id}"/>
								</a>
							</td>
							<td class="col2">${unitat.nom}</td>
							<td class="col3">${unitat.acron}</td>
							<%-- botó veure --%>
							<td class="col4">	
								<a class="btn btn-info btn-xs active"
									href="staff?accioUnitat=veure&id=${unitat.id}">
									<i class="fa fa-search-plus fa-lg"></i>
									<fmt:message key="veure"/>
								</a>	
							</td>	
							<%-- botó esborrar --%>
							<td class="col5">
								<a class="btn btn-danger btn-xs active" 
									href="staff?accioUnitat=esborrar&id=${unitat.id}">
									<i class="fa fa-trash-o fa-lg"></i>
									<fmt:message key="esborrar"/>
								</a>
							</td>
							<%-- botó modificar --%>
							<td class="col6">
								<a class="btn btn-success btn-xs active"
									href="staff?accioUnitat=prepararModificacio&id=${unitat.id}">
									<i class="fa fa-pencil-square-o fa-lg"></i>
									<fmt:message key="modificar"/>
								</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
<%-- ****LLISTAT UNITAT (fi)**** --%>

	<%-- Separador --%>
	<div class="separador"></div>	
	
	<%-- Opcions a d'altres idiomes --%>
	<nav class="navbar navbar-inverse">
		<div class="menuIdioma">
			<fmt:message key="Idioma"/>
		    <a href="staff?accioUnitat=llistat&idioma=ca"><fmt:message key="ca"/></a> | 
		    <a href="staff?accioUnitat=llistat&idioma=es"><fmt:message key="es"/></a> | 
		    <a href="staff?accioUnitat=llistat&idioma=en"><fmt:message key="en"/></a> |
		    <a href="staff?accioUnitat=llistat&idioma=it"><fmt:message key="it"/></a>
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







