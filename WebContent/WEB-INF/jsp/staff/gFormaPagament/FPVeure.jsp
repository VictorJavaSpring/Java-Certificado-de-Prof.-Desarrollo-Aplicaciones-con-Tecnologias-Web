<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	<fmt:setLocale value="${idioma}"/>
	<fmt:setBundle basename="com.soc.ewok.recursos.jsp.formapagament.FPVeure"/>
	

<c:import url="../imports/headerStaff.jsp"></c:import>
			
		
<!-- contingut principal -->
<!-- capçalera de pàgina -->
	<div class="container">
						<!-- Default Heading -->
						<div>
							<!-- Heading -->
							<h4><fmt:message key = "h4Veure"></fmt:message></h4>
							<!-- Paragraph -->
							<p><fmt:message key = "anarA"></fmt:message><a href="/exemple"><fmt:message key = "publica">
							</fmt:message></a><p>
							<p><fmt:message key = "tornarA"></fmt:message><a href="staff?accioForPag=llistat"><fmt:message key = "llistat">
							</fmt:message></a><p>
							<!-- Border -->
							<div class="border"></div>
						</div>						
										
				<!-- Fi capçalera de pàgina -->

	
	
	<table class = tableEwok>
		<tr><th><fmt:message key = "id"></fmt:message></th><th><fmt:message key = "nom"></fmt:message></th></tr>
		<tr><td>${fp.nId}</td><td>${fp.sNom}</td></tr>
	</table>
	
	
	<!-- Fi contingut principal -->
	</div>
	
</body>	

<c:import url="../imports/footerStaff.jsp"></c:import>
		
</html>
	 








