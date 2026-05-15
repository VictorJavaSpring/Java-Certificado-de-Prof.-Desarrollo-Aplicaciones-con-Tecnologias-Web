<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	<fmt:setLocale value="${idioma}"/>
	<fmt:setBundle basename="com.soc.ewok.recursos.jsp.formapagament.FPNovaModificar"/>
		
	<c:import url="../imports/headerStaff.jsp"></c:import>
			
		
		<!-- contingut principal -->
					
				<!-- capçalera de pàgina -->
		<div class="container">
						<!-- Default Heading -->
						<div>
							<!-- Heading -->
							<c:if test="${fp == null }">
							<h4><fmt:message key = "h4Nova"></fmt:message></h4>
							</c:if>
							<c:if test="${fp != null }">
							<h4><fmt:message key = "h4Modificacio"></fmt:message></h4>
							</c:if>
							<!-- Paragraph -->
							<p><fmt:message key = "anarA"></fmt:message><a href="/exemple"><fmt:message key = "publica">
							</fmt:message></a><p>
							<p><fmt:message key = "tornarA"></fmt:message><a href="staff?accioForPag=llistat"><fmt:message key = "llistat">
							</fmt:message></a><p>
							<!-- Border -->
							<div class="border"></div>
						</div>						
									
				<!-- Fi capçalera de pàgina -->
					
	
	
	
	
	
	<p>
		<c:if test="${fp == null }">
			<form action="staff?accioForPag=nova" method="post" onsubmit = "return validarForm();">
		</c:if>
		<c:if test="${fp != null }">
			<form action="staff?accioForPag=modificar" method="post" onsubmit = "return validarForm();">
		</c:if>
		
		
		<!-- Id -->
			<input type="hidden" value="${fp.nId}" name="id"/>
			
			<!-- Nom Forma Pagament -->

			<label for="nom"><fmt:message key = "labelNom"></fmt:message></label>
			
			
			<c:if test="${fp == null }">
				<input id="inputNom" type="text" name="nom" value="${param.nom}"/>
			</c:if>
			<c:if test="${fp != null }">
				<input id="inputNom" type="text" name="nom" value="${fp.sNom}"/>
			</c:if>
			

			<!-- falta errorNom -->
			
			<span id="spanErrorNom"  
			<c:if test="${errorNom == null}">
			hidden="true"
			</c:if>
			class="alert-form alert-danger">
			<fmt:message key = "errorNom"></fmt:message>
			</span><br/>
			
			
		
			<!-- enviar formulari -->

			<input class="FP" type="submit" value="Enviar"/>
			
			
			
			</form>
					
	</p> 
	
	<!-- Fi contingut principal -->
	</div>
			
</body>	
	<script src = "jseWok/FPvalidForm.js"></script>
	<c:import url="../imports/footerStaff.jsp"></c:import>
		
</html>

	
	
	










		
	
			 







