<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	<fmt:setLocale value="${idioma}"/>
	<fmt:setBundle basename="com.soc.ewok.recursos.jsp.formapagament.FPLlistat"/>
<%@ taglib prefix="fn"
	uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="../imports/headerStaff.jsp"></c:import>
			
		
<!-- contingut principal -->
<!-- capçalera de pàgina -->
		<div class="container">
						<!-- Default Heading -->
						<div>
							<!-- Heading -->
							<h4><fmt:message key = "h4Llistat"></fmt:message></h4>
							<!-- Paragraph -->
							<p><fmt:message key = "anarA"></fmt:message><a href="/exemple"><fmt:message key = "publica">
							</fmt:message></a><p>
							<!-- Border -->
							<div class="border"></div>
						</div>						
										
				<!-- Fi capçalera de pàgina -->





<p>
	${fn:length(llistat)} <fmt:message key = "pFormesPagament"></fmt:message>
</p>
<br/>	
<p>
	<table class="tableEwok FP-table">
		<tr>
		<th><fmt:message key = "campFormaPagament"></fmt:message></th>
		<th><fmt:message key = "campAccio"></fmt:message></th>
		<th></th>
		<th></th>
		</tr>
		<c:forEach items="${llistat}" var="fp">
			<tr>
				<td>${fp.sNom}</td>
				
				<td>
					<a href="staff?accioForPag=esborrar&id=${fp.nId}">
					<fmt:message key = "aEsborrar"></fmt:message></a>
				</td>
				
				<td>
					<a href="staff?accioForPag=prepararmodificar&id=${fp.nId}">
					<fmt:message key = "aModificar"></fmt:message></a>
				</td>
				<td>
					<a href="staff?accioForPag=veure&id=${fp.nId}">
					<fmt:message key = "aVeure"></fmt:message></a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="staff?accioForPag=prepararnova" class="FP"><fmt:message key = "aNova"></fmt:message></a>
</p>
	
<!-- Fi contingut principal -->
	</div>
	
</body>	

<c:import url="../imports/footerStaff.jsp"></c:import>
		
</html>








