<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>

	<c:import url="../imports/headerStaff.jsp"></c:import>

<fmt:setLocale value="${idioma}"/>
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.preu.veurePreu"/>
			
	<div class="inner-page padd">	
		<div class="checkout">
			<div class="container">
	
	<!-- capçalera de pàgina -->
				<h4>
					<fmt:message key="h4DetallsPreu">
						<fmt:param value="${preu.id}"/>
					</fmt:message>
				</h4>
	<!-- Fi capçalera de pàgina -->
		
	<!-- contingut principal -->
				<div style="margin-left:30px">
					<p>
						<fmt:message key="id"/>: ${preu.id}<br/>
						<fmt:message key="preu"/>: ${preu.preu}<br/>
						<fmt:message key="iniciVigencia"/>: ${preu.iniciVigencia}<br/>
						<fmt:message key="finalVigencia"/>: ${preu.finalVigencia}<br/>
						<fmt:message key="idProducte"/>: ${preu.idProducte}<br/>
					</p>
					<fmt:message key="titleModificar" var="modificar" scope="request"/>
					<a href="staff?accioPreu=prepararModificacio&id=${preu.id}" title="${modificar}">
						<i class="fa fa-pencil-square-o"></i>
					</a>
					&nbsp
					<fmt:message key="titleEsborrar" var="esborrar" scope="request"/>
					<a href="staff?accioPreu=esborrar&id=${preu.id}" title="${esborrar}">
						<i class="fa fa-times"></i>
					</a>
				</div>
			<hr/>
			<p><a href="staff?accioPreu=llistat"><fmt:message key="linkTornarLlistat"/></a><p>
			<!-- Fi contingut principal -->
			</div>
		</div>
	</div>
		
		
		<c:import url="../imports/footerStaff.jsp"></c:import>		
</body>	
</html>