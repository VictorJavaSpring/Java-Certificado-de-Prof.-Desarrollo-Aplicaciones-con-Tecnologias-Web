<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.ofertaPunts.veureOfertaPunts"/>

<c:import url="../imports/headerStaff.jsp"></c:import>
<!-- Inner Content -->
<div class="inner-page padd">	
	<div class="checkout">
		<div class="container">
		<!-- Heading -->
		<h4><fmt:message key="titolH1"/></h4>
			<div class="row">
				<div class="col-md-7 col-sm-6">
					<p>El controlador diu: ${msg}</p>
					<table class="table ">
						<tr>
							<th>ID</th><td>${ofertapunts.id}</td>
						</tr>
						<tr>
							<th><fmt:message key="eurosPerPunt"/></th><td>${ofertapunts.eurosPerPunt}</td>
						</tr>
						<tr>
							<th><fmt:message key="puntsPerXec"/></th><td>${ofertapunts.puntsPerXec}</td>
						</tr>
						<tr>
							<th><fmt:message key="diesVigenciaPunts"/></th><td>${ofertapunts.diesVigenciaPunts}</td>
						</tr>
						<tr>
							<th><fmt:message key="diesVigenciaXecs"/></th><td>${ofertapunts.diesVigenciaXecs}</td>
						</tr>
						<tr>
							<th><fmt:message key="iniciVigencia"/></th><td>${ofertapunts.iniciVigencia}</td>
						</tr>
						<tr>
							<th><fmt:message key="fiVigencia"/></th><td>${ofertapunts.fiVigencia}</td>
						</tr>
						<tr>
							<th><fmt:message key="modificar"/></th><td><a href="staff?accioOfertaPunts=prepararModificacio&id=${ofertapunts.id}"><i class="fa fa-pencil"></i></a></td>
						</tr>
						<tr>
							<th><fmt:message key="esborrar"/></th><td><a href="staff?accioOfertaPunts=esborrar&id=${ofertapunts.id}"><i class="fa fa-trash-o"></i></a></td>
						</tr>
						
							
					</table>
					<p><fmt:message key="Anar"/> <a href="staff">Home</a><p>
					<p><fmt:message key="Anar"/> <a href="staff?accioOfertaPunts=llistat">Llista</a><p>
					<p><fmt:message key="Anar"/> <a href="staff?accioOfertaPunts=prepararAlta">fer una alta</a><p>
					<p><fmt:message key="Anar"/> <a href="exemple">web pública</a><p>
					<p><fmt:message key="diu"/> ${msg}</p>
					<p>Escull l'idioma: <a href="staff?accioOfertaPunts=veure&id=${ofertapunts.id}&idioma=ca">Català<a/> | <a href="staff?accioOfertaPunts=veure&id=${ofertapunts.id}&idioma=es">Castellà<a/></p>
					</div>
			</div>
		</div>
	</div>
</div>
	
</body>
	<c:import url="../imports/footerStaff.jsp"></c:import>
</html>







