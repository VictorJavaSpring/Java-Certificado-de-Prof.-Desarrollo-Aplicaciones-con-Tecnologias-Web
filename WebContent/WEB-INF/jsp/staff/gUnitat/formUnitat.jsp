<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
		
<%-- Internacionalització --%>
<fmt:setLocale value="${idioma}"/>
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.unitat.formUnitat"/>

<%--Des d'aquí fins al tag "<body>" quedarà substituït pel header --%>
<%--
	<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<c:if test="${unitat == null }">
			<title>eWok Staff/<fmt:message key="titolHead1"/>Alta Unitat(hardcoded)</title>
		</c:if>
		<c:if test="${unitat != null }">
			<title>eWok Staff/<fmt:message key="titolHead2"/>Modificació Unitat(hardcoded)</title>
		</c:if>
		<link rel="stylesheet" type="text/css" href="css/main.css"/>
	</head>
	<body>
--%>

<%-- Inici del header --%>
	<c:import url="../imports/headerStaff.jsp"></c:import>
<%-- Fi del header --%>  

<%-- ****FORM UNITAT (inici)**** --%> 

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
	    	<a class="unitat" href="staff?accioUnitat=llistat">
		   		<fmt:message key="gUnitat"/>
		   		<span><fmt:message key="gUnitatSub"/></span>
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
		
				
			<!-- Formulari -->
		<div class="formUnitat">
		
			<div class="inner-page padd innUnitat">	
				<div class="checkout cheUnitat">
					<div class="container con2Unitat">
						<!-- Heading -->
						<%-- El títol "Formulari" --%>
						<h2><fmt:message key="titolH1"/></h2>
						<p>
						<c:if test="${unitat == null }">
							<h4><fmt:message key="altaUnitat"/></h4>
							<%--
							<c:set scope="request" var="titol" value="Alta unitat"/>
							--%>
						</c:if>
						<c:if test="${unitat != null }">
							<h4><fmt:message key="modificacioUnitat">
								<fmt:param value="${unitat.id}"/>
							</fmt:message></h4>
						</c:if>
						</p>
							<div class="row">
								<%--
								<div class="col-md-7 col-sm-6">
								--%>
								<div class="col-md-12 col-sm-6">
									<!-- Checkout Form -->
									<c:if test="${unitat == null }">
										<form class="form-horizontal"
									     	  action="staff?accioUnitat=alta" method="post" onsubmit="return valida();">
									</c:if>
									<c:if test="${unitat != null }">
										<form class="form-horizontal"
											  action="staff?accioUnitat=modificacio" method="post" onsubmit="return valida();">
									</c:if>
											<!-- Camp Id -->
											<div class="form-group">
												<label for="id" class="col-md-2 control-label"><fmt:message key="id" />:</label>
												<div class="col-md-8">
													<c:if test="${unitat == null }">
														<fmt:message key="unitatId1" var="plcId" scope="request"/>
														<input type="text" class="form-control" name="id" id="id"
															   value="${param.id}" placeholder="${plcId}" readonly <%--disabled--%> />
													</c:if>
													<c:if test="${unitat != null }">
														<fmt:message key="unitatId2" var="plcId" scope="request"/>
														<input type="text" class="form-control" name="id" id="id" 
															   value="${unitat.id}" placeholder="${plcId}" readonly <%--disabled--%> />
													</c:if>
													<%-- XAVIER: incialment el "type" d'id estava "hidden" --%>
												</div>
											</div>
											
											<%-- Camp Nom --%>
											<div class="form-group">
												<label for="inputNom" class="col-md-2 control-label"><fmt:message key="nom" />:</label>
												<div class="col-md-8">
													<c:if test="${unitat == null }">
														<fmt:message key="unitatNom1" var="plcNom" scope="request"/>
														<input type="text" class="form-control" name="nom" id="nom"
															   value="${param.nom}" placeholder="${plcNom}" autofocus/>
													</c:if>
													<c:if test="${unitat != null }">
														<fmt:message key="unitatNom2" var="plcNom" scope="request"/>
														<input type="text" class="form-control" name="nom" id="nom"
															   value="${unitat.nom}" placeholder="${plcNom}" autofocus/>
													</c:if>
												</div>
											</div>
											<%-- Error Nom (javascript) --%>
											<div class="alert-danger alert-form" id="errorNomDiv" role="alert" 
												<c:if test="${!errorNom}">hidden="true"</c:if> >
												<span class="alert-form alert-danger alert-ewok" id="errorNomSpan"><fmt:message key="errorNom" /></span>	
											</div>
										
											<%-- Camp Acrònim --%>
											<div class="form-group">
												<label for="inputAcron" class="col-md-2 control-label"><fmt:message key="acron" />:</label>
												<div class="col-md-8">
													<c:if test="${unitat == null }">
														<fmt:message key="unitatAcron1" var="plcAcron" scope="request"/>
														<input type="text" class="form-control" name="acron" id="acron" 
															   value="${param.acron}" placeholder="${plcAcron}">
													</c:if>
													<c:if test="${unitat != null }">
														<fmt:message key="unitatAcron2" var="plcAcron" scope="request"/>
														<input type="text" class="form-control" name="acron" id="acron"
															   value="${unitat.acron}" placeholder="${plcAcron}">
													</c:if>
												</div>
											</div>
											<%-- Error Acrònim (javascript) --%>
											<div class="alert-danger alert-form" id="errorAcronDiv" role="alert" 
												<c:if test="${!errorAcron}">hidden="true"</c:if> >
												<span class="alert-form alert-danger alert-ewok" id="errorAcronSpan">
													<fmt:message key="errorAcron" />
												</span>	
											</div>
											
											<%-- Els botons (submit i reset) --%>
											<div class="form-group">
												<div class="col-md-offset-2 col-md-8">
													<button type="submit" class="btn btn-danger btn-sm"><fmt:message key="enviar" /></button>&nbsp;
													<button type="reset" class="btn btn-default btn-sm"><fmt:message key="reiniciar" /></button>
												</div>
											</div>
										</form>
								<%--		
									<c:if test="${unitat == null }">
										<form action="staff?accioUnitat=prepararAlta" method="post">
									</c:if>
									<c:if test="${unitat != null }">
										<form action="staff?accioUnitat=prepararModificacio&id=${unitat.id}" method="post">
									</c:if>			
										<input type="submit" value="Reset"/>
										</form>
								--%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>	
<%-- ****FORM UNITAT (fi)**** --%>				
	
	<%-- Separador --%>
	<div class="separador"></div>	
	
	<%-- Opcions a d'altres idiomes --%>
	<nav class="navbar navbar-inverse">
		<div class="menuIdioma">
			<fmt:message key="Idioma"/>
		    <a href="staff?accioUnitat=prepararAlta&idioma=ca"><fmt:message key="ca"/></a> | 
		    <a href="staff?accioUnitat=prepararAlta&idioma=es"><fmt:message key="es"/></a> | 
		    <a href="staff?accioUnitat=prepararAlta&idioma=en"><fmt:message key="en"/></a> |
		    <a href="staff?accioUnitat=prepararAlta&idioma=it"><fmt:message key="it"/></a>
		</div>
	</nav>

<%-- Script - validació per javascript (inici) --%>
	<script src = "jseWok/formUnitat.js"></script>
	<script src = "jseWok/tancarAlerts.js"></script>
<%-- Script - validació per javascript (fi) --%>
	
<%-- Inici del footer --%>            
	<c:import url="../imports/footerStaff.jsp"></c:import>
<%-- Fi del footer --%>  
</body>
</html>












