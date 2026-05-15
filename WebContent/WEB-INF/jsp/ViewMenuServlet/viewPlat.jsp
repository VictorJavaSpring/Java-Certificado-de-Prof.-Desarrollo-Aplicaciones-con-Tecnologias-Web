<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>

	<c:import url="../imports/headerEwok.jsp"></c:import>
<!-- Internacionalitzacio -->
	<%--
<fmt:setLocale value="${idioma}" />
	--%>
<fmt:setLocale value="ca" />
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.viewMenuServlet.viewMenuServlet" />

<%-- Inici del header --%>            
	<c:import url="../imports/headerEwok.jsp"></c:import>        
<%-- Fi del header --%> 

	<%-- ****ViewMenuServlet (inici)**** --%> 
	<%-- El servlet diu --%>	
	<div class="parlaServlet">
		<h6><fmt:message key="hola">
				<fmt:param value="${Ok}"/>
			</fmt:message>
		</h6>
	</div>

	<%-- ****ViewMenuServlet (fi)**** --%>

	<%-- Inici del footer --%>            
		<c:import url="../imports/footerEwok.jsp"></c:import>
	<%-- Fi del footer --%>  
</body>
</html>