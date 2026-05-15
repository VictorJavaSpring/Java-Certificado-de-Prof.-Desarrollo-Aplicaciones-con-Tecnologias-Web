<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../imports/headerStaff.jsp"></c:import>

<!-- Internacionalitzacio -->
<fmt:setLocale value="${idioma}" />
<fmt:setBundle
	basename="com.soc.ewok.recursos.jsp.ofertaproducte.llistaOfertaProducte" />
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
						<fmt:message key="titolllistat" />
					</h1>
					<table class="table table-striped table-bordered">
						<tr>
							<th><fmt:message key="ide" /></th>
							<th><fmt:message key="descompte" /></th>
							<th><fmt:message key="nom" /></th>
							<th><fmt:message key="inivigencia" /></th>
							<th><fmt:message key="fivigencia" /></th>
							<th><fmt:message key="accions" /></th>
						</tr>
						<c:forEach items="${ofertaproductellista}" var="op">
							<tr>
								<td>${op.id}</td>
								<td>${op.pctDescompte}</td>
								<td>${op.nom}</td>
								<td>${op.iniciVigencia}</td>
								<td>${op.fiVigencia}</td>
								<td>
									<a class="btn btn-default btn-sm" href="staff?accioOfertaProducte=veure&id=${op.id}"><i class="fa fa-eye" title="<fmt:message key="veure" />"></i></a> 
									<a class="btn btn-default btn-sm" href="staff?accioOfertaProducte=prepararModificacio&id=${op.id}"><i class="fa fa-wrench" title="<fmt:message key="modificar" />"></i></a> 
									<a class="btn btn-default btn-sm" href="staff?accioOfertaProducte=esborrar&id=${op.id}"><i class="fa fa-trash-o" title="<fmt:message key="esborrar" />"></i></a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div class="row">
				<p>
					<a class="btn btn-default btn-sm" href="staff?accioOfertaProducte=prepararAlta"><fmt:message key="prepalta" /></a>
				</p>
			</div>
		</div>
	</div>
</div>
<c:import url="../imports/footerStaff.jsp"></c:import>
</body>
</html>