<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.productes.Productes_veure_staff"/>

<c:import url="../imports/headerStaff.jsp"></c:import>

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
					<p><fmt:message key="Anar"/> <a href="staff"><fmt:message key="privada"/></a><p>
					<p><fmt:message key="textIntro">
					<fmt:param value="${productes.id}"></fmt:param>
					</fmt:message>
					</p>
					<!-- Border -->
					<div class="border"></div>
				</div>						
</div>	
<!-- Inner Content -->
	<div class="checkout">
		<div class="container">
		<!-- Heading -->
		<h4><fmt:message key="titolH1"/></h4>
			<div class="row">
				<div class="col-md-7 col-sm-6">	
				<p><fmt:message key="llistatProductes"/></p>
				
					<p>El controlador diu: ${msg}</p>
					<table border="3" class="tableEwok">
						<tr>
							<th><fmt:message key="id"/></th>
							<th><fmt:message key="nom"/></th>
							<th><fmt:message key="DescripcioCurta"/></th>
							<th><fmt:message key="Descripcio"/></th>
							<th><fmt:message key="IniciVigencia"/></th>
							<th><fmt:message key="FiVigencia"/></th>
							<th><fmt:message key="IdUnitat"/></th>
							<th><fmt:message key="IdTipusProducte"/></th>
							<th><fmt:message key="accio"/></th>
							<th><fmt:message key="accio"/></th>

						</tr>
						<tr>
							<td>${productes.id}</td>
							<td>${productes.nom}</td>
							<td>${productes.descripcioCurta}</td>
							<td>${productes.descripcio}</td>
							<td>${productes.iniciVigencia}</td>
							<td>${productes.fiVigencia}</td>
							<td>${productes.idUnitat}</td>
							<td>${productes.idTipusProducte}</td>
							<td>
							<a href="staff?accioProductes=esborrar&id=${productes.id}">
							<i class="fa fa-times fa-2x"></i>
							</td>
							<td>
							<a href="staff?accioProductes=prepararModificacio&id=${productes.id}">
							<i class="fa fa-pencil-square-o fa-2x"></i>
							</td>

						</tr>
					</table>
				<h2><fmt:message key="Anar"/>...</h2>
				<div class="list-group">
					<a class="list-group-item" href="staff?accioProductes=prepararAlta"><fmt:message key="nou"/></a>
					<a class="list-group-item" href="staff?accioProductes=llistat"><fmt:message key="llistat"/></a>
					<ul>
						<li><fmt:message key="Tria"/> <a href="staff?accioProductes=llistat&idioma=ca">Català<a/>
					 | <a href="staff?accioProductes=llistat&idioma=es">Castellà<a/>
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