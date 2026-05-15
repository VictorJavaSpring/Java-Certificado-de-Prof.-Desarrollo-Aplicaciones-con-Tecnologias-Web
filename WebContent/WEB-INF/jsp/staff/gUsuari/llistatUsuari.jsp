<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	
<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.imports.llistatUsuariStaff"/>

<c:import url="../imports/headerStaff.jsp"></c:import>
<!-- Inner Content -->
<div class="inner-page padd">	
	<div class="checkout">
		<div class="container">
		<!-- Heading -->
		<h1><fmt:message key="titolH1"/></h1>
			<div class="row">
				<div class="col-md-12 col-sm-6">	
				<p><fmt:message key="numUsuaris"/>: ${fn:length(usuaris)}</p>

	

				<p><fmt:message key="llistatUsuaris"/></p>
							<p>
							<table border="3">
								<tr>
									<th><fmt:message key="emailUsuari"/></th>
									<th><fmt:message key="rol"/></th>
									<th></th>
									<th></th>
									<th></th>
									
									
								</tr>
								
								<c:forEach items="${usuaris}" var="usuari">
									<tr>
										
										<td>${usuari.mail}</td>
										<td>${usuari.actiu}</td>
										
										<td>
											<a href="staff?accioUsuari=veure&mail=${usuari.mail}">
											<fmt:message key="veure"/></a>
										</td>
										<td>
											<a href="staff?accioUsuari=esborrar&mail=${usuari.mail}">
											<fmt:message key="esborrar"/></a>
										</td>
										<td>
											<a href="staff?accioUsuari=prepararModificacio&mail=${usuari.mail}">
											<fmt:message key="modificar"/></a>
										</td>
										<td></td>
									</tr>
								</c:forEach>
							</table>
						</p></br>
						
									<p><fmt:message key="Anar"/> <a href="staff"><fmt:message key="Home"/></a><p>
									<p><fmt:message key="Anar"/> <a href="staff?accioUsuari=llistat"><fmt:message key="llistat"/></a><p>
									<p><fmt:message key="Anar"/> <a href="staff?accioProducte=llistat"><fmt:message key="productes"/></a><p>
									<p><fmt:message key="Anar"/> <a href="staff?accioUsuari=prepararAlta"><fmt:message key="nouUsuari"/></a><p>
									<p><fmt:message key="Anar"/> <a href="exemple"><fmt:message key="exemple"/></a><p>
									
									<p><fmt:message key="diu"/> ${msg}</p>
									<p>Escull l'idioma: <a href="staff?accioUsuari=llistat&idioma=ca">Català<a/> | <a href="staff?accioUsuari=llistat&idioma=es">Castellà<a/>  | <a href="staff?accioUsuari=llistat&idioma=en">Anglés<a/></p>
							</div>
								</div>
							</div>
						</div>
					</div>

			</body>

			<c:import url="../imports/footerStaff.jsp"></c:import>
	
</html>







