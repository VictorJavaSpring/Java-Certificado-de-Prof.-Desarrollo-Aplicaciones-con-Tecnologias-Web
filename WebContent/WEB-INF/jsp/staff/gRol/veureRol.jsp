<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.rol.Rol_veure_staff"/>

<c:import url="../imports/headerStaff.jsp"></c:import>

<!-- Inner Content -->
<div class="inner-page padd">	
	<div class="checkout">
		<div class="container">
		<!-- Heading -->
		<h4><fmt:message key="titolH1"/></h4>
			<div class="row">
				<div class="col-md-7 col-sm-6">	
				<p><fmt:message key="veureRols"/></p>
				
					<p>El controlador diu: ${msg}</p>
					<table border="3" class="tableEwok">
						<tr>
							<th><fmt:message key="id"/></th>
							<th><fmt:message key="nom"/></th>
						</tr>
						<tr>
							<td>${rol.id}</td>
							<td>${rol.nom}</td>
						</tr>
					</table>
					<p><fmt:message key="Anar"/> <a href="staff?accioRol=prepararAlta"><fmt:message key="nou"/></a><p>
					<p><fmt:message key="Anar"/> <a href="staff?accioRol=llistat"><fmt:message key="llistat"/></a><p>
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







