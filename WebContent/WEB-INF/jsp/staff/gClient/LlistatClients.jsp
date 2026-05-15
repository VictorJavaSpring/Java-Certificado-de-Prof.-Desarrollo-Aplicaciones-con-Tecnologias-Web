<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../imports/headerStaff.jsp"></c:import>

<!-- Internacionalitzacio -->
<fmt:setLocale value="${idioma}" />
<fmt:setBundle
	basename="com.soc.ewok.recursos.jsp.clients.llistaClients" />
<!-- FI Internacionalitzacio -->

<!-- Inner Content -->
<div class="inner-page padd">
	<div class="checkout">
			<div class="container">
				<!-- Default Heading -->
				<div class="default-heading">
					<h1><fmt:message key="gestiodeH4"/></h1>
					<!-- Crown image -->
					<img class="img-responsive" src="img/crown.png" alt="" />
					<!-- Heading -->
					<h2><fmt:message key="titolllistat"/></h2>
					<!-- Paragraph -->
					<p><fmt:message key="anar"/>  <a href="staff"><fmt:message key="privada"/></a><p>
					<p><fmt:message key="textIntro"/></p>
					<!-- Border -->
					<div class="border"></div>
				</div>						
			</div>
			<!-- Heading -->
			<div class="row">
				<div class="col-md-10 col-sm-2">
					<table class="tableEwok">
						<tr>
							<th><fmt:message key="ide" /></th>
							<th><fmt:message key="nom" /></th>
							<th><fmt:message key="cognom" /></th>
							<th><fmt:message key="telefon" /></th>
							<th><fmt:message key="email" /></th>
							<th><fmt:message key="dni" /></th>
							<th><fmt:message key="datalta" /></th>
							<th><fmt:message key="idusuari" /></th>
							<th><fmt:message key="veure" /></th>
							<th><fmt:message key="modificar" /></th>
							<th><fmt:message key="eliminar" /></th>
						</tr>
					<c:forEach items="${client}" var="c">
						<tr>
							<td>${c.id}</td>
							<td>${c.nom}</td>
							<td>${c.cognoms}</td>
							<td>${c.telefon}</td>
							<td>${c.email}</td>
							<td>${c.dni}</td>
							<td>${c.dataAlta}</td>
							<td>${c.idUsuari}</td>
							<td><a class="btn btn-default btn-sm" href="staff?accioClients=veure&id=${c.id}"><i class="fa fa-eye" title="<fmt:message key="veure" />"></i></a></td>
							<td><a class="btn btn-default btn-sm" href="staff?accioClients=prepararModificacio&id=${c.id}"><i class="fa fa-wrench" title="<fmt:message key="modificar" />"></i></a></td>
							<td><a class="btn btn-default btn-sm" href="staff?accioClients=esborrar&id=${c.id}"><i class="fa fa-trash-o" title="<fmt:message key="eliminar" />"></i></a></td>
						</tr>
					</c:forEach>
					</table>
				</div>
			</div>
			<div class="row">
				<p>
					<a class="btn btn-default btn-sm" href="staff?accioClients=prepararAlta"><fmt:message key="prepalta" /></a>
				</p>
			</div>
			<hr>
			<div class="row">
				<p>
					<a class="btn btn-default btn-sm" href="staff">Staff Home</a>
				</p>
			</div>
		
	</div>
</div>
<c:import url="../imports/footerStaff.jsp"></c:import>
</body>
</html>