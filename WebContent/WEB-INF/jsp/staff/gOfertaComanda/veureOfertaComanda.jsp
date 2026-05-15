<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${!flag}">
	<c:redirect url="/main"/>
</c:if>
<c:import url="imports/headerStaff.jsp"></c:import>

<!-- Inner Content -->
		<h1>Veure secció Oferta Comanda</h1>

<c:import url="imports/footerStaff.jsp"></c:import>
</body>
</html>