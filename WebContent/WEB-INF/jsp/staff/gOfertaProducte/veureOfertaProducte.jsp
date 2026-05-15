<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../imports/headerStaff.jsp"></c:import>

<!-- Internacionalitzacio -->
<fmt:setLocale value="${idioma}" />
<fmt:setBundle
	basename="com.soc.ewok.recursos.jsp.ofertaproducte.veureOfertaProducte" />
<!-- FI Internacionalitzacio -->

<!-- Inner Content -->
<div class="inner-page padd">
	<div class="checkout">
		<div class="container">
			<!-- Heading -->
			<h4>
				<fmt:message key="gestiodeH4" />
			</h4>
			<div class="row">
				<div class="col-md-12 col-sm-6">
					<h1>
						<fmt:message key="titolveure" />
					<span>${ofertaproducte.nom}</span> 
					</h1>
					<table class="table table-bordered">
						<tr>
							<th><fmt:message key="ide" /></th>
							<th><fmt:message key="descompte" /></th>
							<th><fmt:message key="nom" /></th>
							<th><fmt:message key="inivigencia" /></th>
							<th><fmt:message key="fivigencia" /></th>
							<th><fmt:message key="descripcio" /></th>
							<th><fmt:message key="producte" /></th>
							<th><fmt:message key="accions" /></th>
						</tr>
						<tr>
							<td>${ofertaproducte.id}</td>
							<td>${ofertaproducte.pctDescompte}</td>
							<td>${ofertaproducte.nom}</td>
							<td>${ofertaproducte.iniciVigencia}</td>
							<td>${ofertaproducte.fiVigencia}</td>
							<td>${ofertaproducte.otext}</td>
							<td>${nomProducte}</td>
							<td><a href="staff?accioOfertaProducte=prepararModificacio&id=${ofertaproducte.id}"><fmt:message key="modificar" /></a> 
							<a href="#"><fmt:message key="esborrar" /></a></td>
						</tr>
					</table>
				</div>
			</div>
			<hr>
			<div class="row">
				<p>
					<a class="btn btn-default btn-sm" href="staff?accioOfertaProducte=llistat"><fmt:message key="anar" /> <fmt:message key="llistat" /></a>
				</p>
			</div>
		</div>
	</div>
</div>
<c:import url="../imports/footerStaff.jsp"></c:import>
</body>
</html>