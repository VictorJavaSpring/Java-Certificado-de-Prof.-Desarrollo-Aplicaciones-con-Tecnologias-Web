<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	
<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.ofertaPunts.llistaOfertaPunts"/>

<c:import url="../imports/headerStaff.jsp"></c:import>
<!-- Inner Content -->
<div class="inner-page padd">	
	<div class="checkout">
		<div class="container">
		<!-- Heading -->
		<h1><fmt:message key="titolH1"/></h1>
			<div class="row">
				<div class="col-md-12 col-sm-6">	
				<p><fmt:message key="numOfertaPunts"/>: ${fn:length(ofertapunts)}</p>
				
				<h4><fmt:message key="llistatOfertaPunts"/></h4>
						<table class="table">
								<tr>
									<th>ID</th>
									<th><fmt:message key="eurosPerPunt"/></th>
									<th><fmt:message key="puntsPerXec"/></th>
									<th><fmt:message key="diesVigenciaPunts"/></th>
									<th><fmt:message key="diesVigenciaXecs"/></th>
									<th><fmt:message key="iniciVigencia"/></th>
									<th><fmt:message key="fiVigencia"/></th>
									<th colspan="2"><fmt:message key="accions"/></th>																		
								</tr>								
							<c:forEach items="${ofertapunts}" var="ofertapunt">
								<tr>
									<td>
											<a href="staff?accioOfertaPunts=veure&id=${ofertapunt.id}">
												<c:out value="${ofertapunt.id}"/>
											</a>
									</td>
									<td>${ofertapunt.eurosPerPunt}</td>
									<td>${ofertapunt.puntsPerXec}</td>
									<td>${ofertapunt.diesVigenciaPunts}</td>
									<td>${ofertapunt.diesVigenciaXecs}</td>									
									<td>${ofertapunt.iniciVigencia}</td>
									<td>${ofertapunt.fiVigencia}</td>									
									<td>
									<a href="staff?accioOfertaPunts=prepararModificacio&id=${ofertapunt.id}">
									<i class="fa fa-pencil"></i>									
									</td>
									<td>
									<a href="staff?accioOfertaPunts=esborrar&id=${ofertapunt.id}">
									<i class="fa fa-trash-o"></i>
									</td>
								</tr>
							</c:forEach>
						</table>
					<p><fmt:message key="Anar"/> <a href="staff">Home</a><p>					
					<p><fmt:message key="Anar"/> <a href="staff?accioOfertaPunts=prepararAlta">Nova Oferta de Punts</a><p>
					<p><fmt:message key="Anar"/> <a href="exemple">a web pública</a>.<p>					
					<p>Escull l'idioma: <a href="staff?accioOfertaPunts=llistat&idioma=ca">Català</a> | <a href="staff?accioOfertaPunts=llistat&idioma=es">Castellà</a></p>
					</div>
			</div>
		</div>
	</div>
</div>
	
</body>
	<c:import url="../imports/footerStaff.jsp"></c:import>
</html>