<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	<fmt:setLocale value="${idioma}"/>
	<fmt:setBundle basename="com.soc.ewok.recursos.jsp.formapagament.FPHome"/>
	
	<c:import url="../../imports/headerEwok.jsp"></c:import>
			
		
<!-- contingut principal -->
<!-- capçalera de pàgina -->
					<div class="container">
						<!-- Default Heading -->
						<div class="default-heading">
							<!-- Crown image -->
							<img class="img-responsive" src="img/crown.png" alt="" />
							<!-- Heading -->
							<h2>Gestio Formes Pagament</h2>
							<!-- Paragraph -->
							<p>Anar a la part <a href="/exemple">publica</a><p>
							<p>Secció de gestió de les diverses formes de pagament de la web</p>
							<!-- Border -->
							<div class="border"></div>
						</div>						
					</div>					
				<!-- Fi capçalera de pàgina -->



<p>El controlador diu: ${msg}</p>
<p><a href="eWok/staff?accioForPag=prepararnova">Nova</a> forma pagament</p>
<p><a href ="eWok/staff?accioForPag=llistat">Llistat</a> formes pagament</p>


<!-- Fi contingut principal -->


</body>	

<c:import url="../../imports/footerEwok.jsp"></c:import>
		
</html>






