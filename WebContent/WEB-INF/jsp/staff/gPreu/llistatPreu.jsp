<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../imports/headerStaff.jsp"></c:import>

<fmt:setLocale value="${idioma}"/>
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.preu.llistatPreu"/>

<!-- contingut principal -->

<!-- capçalera de pàgina -->
	<div class="inner-page padd">
		<div class="checkout">
			<div class="container">
				<h4><fmt:message key="h4GestióPreus"/></h4>
	
				<!-- Fi capçalera de pàgina -->
	
				<div style="margin-left: 30px">
					<table class="tableEwok">
						<tr>
							<th><fmt:message key="thId"/></th>
							<th><fmt:message key="thPreu"/></th>
							<th><fmt:message key="thIniciVigencia"/></th>
							<th><fmt:message key="thFinalVigencia"/></th>
							<th><fmt:message key="thIdProducte"/></th>
							<th><fmt:message key="thModificar"/></th>
							<th><fmt:message key="thEsborrar"/></th>
						</tr>
						<c:forEach items="${preus}" var="preu">
							<tr>
								<td><a href="staff?accioPreu=veure&id=${preu.id}"> <c:out
											value="${preu.id}" />
								</a></td>
								<td>${preu.preu}</td>
								<fmt:formatDate value="${preu.iniciVigencia}"
									var="dataIniciVigencia" type="date" pattern="yyyy-MM-dd" />
								<td>${dataIniciVigencia}</td>
								<fmt:formatDate value="${preu.finalVigencia}"
									var="dataFinalVigencia" type="date" pattern="yyyy-MM-dd" />
								<td>${dataFinalVigencia}</td>
								<td>${preu.idProducte}</td>
								<td>
									<fmt:message key="titleModificar" var="modificar" scope="request"/>
									<a href="staff?accioPreu=prepararModificacio&id=${preu.id}"
										title="${modificar}"> 
										<i class="fa fa-pencil-square-o"></i>
									</a>
								</td>
								<td>
								<fmt:message key="titleEsborrar" var="esborrar" scope="request"/>
								<a href="staff?accioPreu=esborrar&id=${preu.id}" title="${esborrar}"> 
									<i class="fa fa-times"></i>
								</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<hr />
				<a href="staff?accioPreu=prepararAlta"><fmt:message key="linkAltaPreu"/></a>
				<!-- Fi contingut principal -->
			</div>
		</div>
	</div>

<c:import url="../imports/footerStaff.jsp"></c:import>
</body>
</html>