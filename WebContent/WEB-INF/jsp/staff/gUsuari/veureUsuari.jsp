<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.imports.veureUsuariStaff"/>

<c:import url="../imports/headerStaff.jsp"></c:import>
<!-- Inner Content -->
<div class="inner-page padd">	
	<div class="checkout">
		<div class="container">
		<!-- Heading -->
		<h4><fmt:message key="titolH1"/></h4>
			<div class="row">
				<div class="col-md-7 col-sm-6">	
				
				
					
					<table border="3">
						<tr>
							<th><fmt:message key="email"/></th>
							
							
						</tr>
						
						<tr>
							<td>${usuaris.mail}</td>
							
						</tr>
						
					</table>
					<p><fmt:message key="Anar"/> <a href="staff"><fmt:message key="home"/></a><p>
					<p><fmt:message key="Anar"/> <a href="staff?accioClients=llistat"><fmt:message key="clients"/></a><p>
					<p><fmt:message key="Anar"/> <a href="staff?accioUsuari=prepararAlta"><fmt:message key="nouUsuari"/></a><p>
					<p><fmt:message key="Anar"/> <a href="exemple"><fmt:message key="exemple"/></a><p>
					<p><fmt:message key="diu"/> ${msg}</p>
					<p>Escull l'dioma: <a href="staff?accioUsuari=llistat&idioma=ca">Català<a/> | <a href="staff?accioUsuari=llistat&idioma=es">Castellà<a/>  | <a href="staff?accioUsuari=llistat&idioma=en">Anglés<a/></p>
					</div>
			</div>
		</div>
	</div>
</div>
	
</body>
	<c:import url="../imports/footerStaff.jsp"></c:import>
</html>







