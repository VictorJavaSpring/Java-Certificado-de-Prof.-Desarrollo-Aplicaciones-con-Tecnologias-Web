<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<fmt:setLocale value="${idioma}"/>
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.vistacuiner.vistacuiner"/>
<c:import url="imports/headerStaff.jsp"></c:import>
<div class="inner-page padd">
	<div class="checkout">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-6">
										
					<h1>
						<fmt:message key="title" />
					</h1>
					<table class="table table-striped table-bordered">
						<tr>
							<th><fmt:message key="idComanda" /></th>
							<th><fmt:message key="idProducte" /></th>
							<th><fmt:message key="prodNom" /></th>
							<th><fmt:message key="Quantitat" /></th>
							<th><fmt:message key= "data" /></th>	
							<th><fmt:message key="Estat" /></th>
						</tr>
						<c:forEach items="${comandes}" var="lc">
						<tr id="linia_${lc.id},${lc.linia}">
							<td>${lc.id}</td>
							<td>${lc.idProducte}</td>
							<td>${lc.producte.nom}</td>
							<td>${lc.quantitat}</td>
							<td>${lc.comanda.data}</td>
							<td>
								<c:if test="${lc.estat == 'inicial'}">
									<button onclick="requestPlat(${lc.id},${lc.linia})"><i class="fa fa-play"  id="icon_${lc.id},${lc.linia}"></i></button>
									<input type="text" hidden="true" id="value_${lc.id},${lc.linia}" value="${lc.estat}"/>
								</c:if>
								<c:if test="${lc.estat == 'enPreparacio'}">
									<button onclick="requestPlat(${lc.id},${lc.linia})"><i class="fa fa-spinner fa-spin" id="icon_${lc.id},${lc.linia}"></i></button>
									<input type="text" hidden="true" id="value_${lc.id},${lc.linia}" value="${lc.estat}"/>
								</c:if>
								<c:if test="${lc.estat == 'preparat'}">
									<button onclick="requestPlat(${lc.id},${lc.linia})"><i class="fa check-square" id="icon_${lc.id},${lc.linia}"></i></button>
									<input type="text" hidden="true" id="value_${lc.id},${lc.linia}" value="${lc.estat}"/>
								</c:if>
								<%-- <button onclick="deletePlat()" hidden="true" id="del_${lc.id},${lc.linia}"></button> --%>
								<i onclick="deletePlat()" hidden="true" id="del_${lc.id},${lc.linia}"></i> 
							</td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<p><fmt:message key="idioma"/><a href="staff?accioVistaCuiner=llistat&idioma=ca">Català</a> | <a href="staff?accioVistaCuiner=llistat&idioma=es">Castellà</a> | <a href="staff?accioVistaCuiner=llistat&idioma=it">Italià</a> | <a href="staff?accioVistaCuiner=llistat&idioma=en">Anglés</a></p>
</body>
<c:import url="imports/footerStaff.jsp"></c:import>
<script type="text/javascript" src="jseWok/AjaxVCuiner.js"></script>

</html>