<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.productes.Productes_llistat_staff"/>

<c:import url="../imports/headerStaff.jsp"></c:import>

<!-- capçalera de pàgina -->
		<div class="inner-page padd">	
			<div class="container">
				<!-- Default Heading -->
				<div class="default-heading">
					<h1><fmt:message key="titolH1"/></h1>
					<!-- Crown image -->
					<img class="img-responsive" src="img/crown.png" alt="" />
					<!-- Heading -->
					<h2><fmt:message key="titolH4"/></h2>
					<!-- Paragraph -->
					<p><fmt:message key="Anar"/>  <a href="staff"><fmt:message key="privada"/></a><p>
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
			<div class="row">
				<div class="col-md-12 col-sm-6">	
				<h2><fmt:message key="numProductes"/>: ${fn:length(productes)}</h2>
						<table border="3" class="tableEwok">
								<tr>
									<th>ID</th>
									<th><fmt:message key="Nom"/></th>
									<th><fmt:message key="DescripcioCurta"/></th>
									<th><fmt:message key="accio"/></th>
									<th><fmt:message key="accio"/></th>
								</tr>
								
							<c:forEach items="${productes}" var="producte">
								<tr>
									<td>
											<a href="staff?accioProductes=veure&id=${producte.id}">
												<c:out value="${producte.id}"/>
											</a>
									</td>
									<td>${producte.nom}</td>
									<td>${producte.descripcioCurta}</td>
									<td>
									<a href="staff?accioProductes=esborrar&id=${producte.id}">
									<i class="fa fa-times fa-2x"></i>
									</td>
									<td>
									<a href="staff?accioProductes=prepararModificacio&id=${producte.id}">
									<i class="fa fa-pencil-square-o fa-2x"></i>
									</td>
								</tr>
							</c:forEach>
						</table>
				<h2><fmt:message key="Anar"/>...</h2>
				<div class="list-group">
					<a class="list-group-item" href="staff?accioProductes=prepararAlta"><fmt:message key="nou"/></a>
					 <ul>
					<li><h3><fmt:message key="Tria"/> <a href="staff?accioProductes=llistat&idioma=ca">Català<a/>
					 | <a href="staff?accioProductes=llistat&idioma=es">Castellà<a/></h3>
					 </li>
					 </ul>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</body>
	<c:import url="../imports/footerStaff.jsp"></c:import>

</html>