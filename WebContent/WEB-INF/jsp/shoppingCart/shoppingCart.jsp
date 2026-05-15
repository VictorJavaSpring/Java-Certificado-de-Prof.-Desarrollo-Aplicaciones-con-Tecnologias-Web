<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>

	<c:import url="../imports/headerEwok.jsp"></c:import>
<!-- Internacionalitzacio -->
<fmt:setLocale value="${idioma}" />
<fmt:setBundle
	basename="com.soc.ewok.recursos.jsp.mostraproductesvigents.mostraProdCateg" />
<!-- FI Internacionalitzacio -->		
		<!-- contingut principal -->
<%-- 				
				<!-- capçalera de pàgina -->
					<div class="container">
						<!-- Default Heading -->
						<div class="default-heading">
							<!-- Crown image -->
							<img class="img-responsive" src="img/crown.png" alt="" />
							<!-- Heading -->
 							<h2><fmt:message key="titolh2" /></h2> 
							<!-- Paragraph -->
							<p><fmt:message key="lema" /></p>
							<!-- Border -->
							<hr class="border">

						</div>						
					</div>					
				<!-- Fi capçalera de pàgina -->
--%>		
			<!-- Inner Content -->
			<div class="inner-page">
				
				<!-- Shopping Start -->
				
				<div class="shopping">
					<div class="container">
						<!-- Shopping items content -->
						<div class="shopping-content">
							<div class="row">
								<%-- inserir la colecio rebuda i fer un bucle for each --%>
								<c:if test="${error!=null}">
									<div class="default-heading">
										<!-- Crown image -->
										<img class="img-responsive" src="img/crown.png" alt="" />
										<h2><fmt:message key="errorParams"/></h2>
									</div>
								</c:if>
								<h2>Shopping Cart</h2>
							</div>
						</div>
					</div>
				</div>
				
				<!-- Shopping End -->
		
			</div><!-- / Inner Page Content End -->		
		<!-- Fi contingut principal -->

	<c:import url="../imports/footerEwok.jsp"></c:import>
	</body>	
</html>