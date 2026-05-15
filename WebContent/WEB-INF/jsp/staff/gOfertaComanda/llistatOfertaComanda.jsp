<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Internacionalitzacio -->
<fmt:setLocale value="${idioma}" />
<fmt:setBundle
	basename="com.soc.ewok.recursos.jsp.imports.llistaOfertaComanda" />
<!-- FI Internacionalitzacio -->

<c:import url="../imports/headerStaff.jsp"></c:import>

<!-- Inner Content -->

<div class="inner-page padd">
	<div class="checkout">
		<div class="container">
			<!-- Heading -->
			<h1><fmt:message key="titolH1"/></h1>
			<div class="row">
				<div class="col-md-12 col-sm-6">
					<p><fmt:message key="numOfertesComandes"/>: ${fn:length(ofertacomanda)}</p>
					<p><fmt:message key="llistatProductes"/></p>
					<table class="table">
						<tr>
							<th><fmt:message key="ide" /></th>
							<th><fmt:message key="limitinferior" /></th>
							<th><fmt:message key="descompte" /></th>
							<th><fmt:message key="inivigencia" /></th>
							<th><fmt:message key="fivigencia" /></th>
							<th><fmt:message key="accions" /></th>
						</tr>
						<c:forEach items="${ofertaComanda}" var="oc">
							<tr>
								<td>${oc.id}</td>
								<td>${oc.limitInferior}</td>
								<td>${oc.pctDescompte}</td>
								<td>${oc.iniciVigencia}</td>
								<td>${oc.fiVigencia}</td>
								<td>
									<a href="staff?accioOfertaComanda=esborrar&id=${oc.id}">
									<fmt:message key="esborrar"/>
									</a>
								</td>
								<td>
									<a href="staff?accioOfertaComanda=prepararModificacio&id=${oc.id}">
									<fmt:message key="modificar"/>
									</a>
								</td>
							</tr>
						</c:forEach>
					</table>
					
					<p><fmt:message key="anar"/> <a href="staff">Home</a><p>
					<p><fmt:message key="anar"/> <a href="staff?accioClients=llistat">Gestió clients</a><p>
					<p><fmt:message key="anar"/> <a href="staff?accioOfertaComanda=prepararAlta">Nova Oferta comanda</a><p>
					<p><fmt:message key="anar"/> <a href="exemple">exemple</a><p>
					<p><fmt:message key="diu"/> ${msg}</p>
					<p>Escull l'idioma:
						<a href="staff?accioOfertaComanda=llistat&idioma=ca">Català</a> | 
						<a href="staff?accioOfertaComanda=llistat&idioma=es">Castellà</a> | 
						<a href="staff?accioOfertaComanda=llistat&idioma=en">Anglés</a>
					</p>		
				</div>
			</div>
			<div class="row">
				<p>
					<a href="staff">Staff Home</a>
				</p>
				<p>
					<a href="staff?accio=logout">Logout</a>
				</p>
			</div>
		</div>
	</div>
</div>

<!-- FI Inner Content -->

<c:import url="../imports/footerStaff.jsp"></c:import>
</body>
</html>