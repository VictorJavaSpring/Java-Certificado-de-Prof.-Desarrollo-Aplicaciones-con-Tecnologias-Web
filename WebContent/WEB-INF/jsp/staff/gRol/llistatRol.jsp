<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.rol.Rol_llistat_staff"/>

<c:import url="../imports/headerStaff.jsp"></c:import>

<!-- capçalera de pàgina -->
			<div class="container">
				<!-- Default Heading -->
				<div class="default-heading">
					<h1><fmt:message key="titolH1"/></h1>
					<!-- Crown image -->
					<img class="img-responsive" src="img/crown.png" alt="" />
					<!-- Heading -->
					<h2><fmt:message key="titolH4"/></h2>
					<!-- Paragraph -->
					<p><fmt:message key="Anar"/> <a href="staff"><fmt:message key="privada"/></a><p>
					<p><fmt:message key="textIntro"/></p>
					<!-- Border -->
					<div class="border"></div>
				</div>						
			</div>					
<!-- Fi capçalera de pàgina -->
<!-- Inner Content -->
<div class="inner-page padd">	
	<div class="checkout">
		<div class="container">
		<!-- Heading -->
		<h1><fmt:message key="titolH1"/></h1>
			<div class="row">
				<div class="col-md-12 col-sm-6">	
				<p><fmt:message key="numRols"/>: ${fn:length(rol)}</p>
				
				<h4><fmt:message key="titolH4"/></h4>
						<table border="3" class="tableEwok">
								<tr>
									<th><fmt:message key="id"/></th>
									<th><fmt:message key="nom"/></th>
									<th><fmt:message key="accio"/></th>
									<th></th>
									
								</tr>
								
							<c:forEach items="${rol}" var="rol">
								<tr>
									<td>
										<a href="staff?accioRol=veure&Id=${rol.id}">
											<c:out value="${rol.id}"/>
										</a>
									</td>
									<td>${rol.nom}</td>
									<td>
										<a href="staff?accioRol=esborrar&Id=${rol.id}">
										<fmt:message key="esborrar"/>
										</a>
									</td>
									<td>
										<a href="staff?accioRol=prepararModificacio&Id=${rol.id}">
										<fmt:message key="modificar"/>
										</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					<p><fmt:message key="Anar"/> <a href="staff?accioRol=prepararAlta"><fmt:message key="nou"/></a><p>
					<p><fmt:message key="Anar"/> <a href="staff"><fmt:message key="home"/></a><p>
					<p><fmt:message key="diu"/> ${msg}</p>
					<p>Escull l'idioma: <a href="staff?accioRol=llistat&idioma=ca">Català</a> | <a href="staff?accioRol=llistat&idioma=es">Castellà</a> | <a href="staff?accioRol=llistat&idioma=en">Anglès</a></p>
					</div>
			</div>
		</div>
	</div>
</div>
</body>
	<c:import url="../imports/footerStaff.jsp"></c:import>

</html>