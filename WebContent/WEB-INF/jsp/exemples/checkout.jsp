<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	
<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.checkout.checkout"/>

<c:import url="../imports/headerEwok.jsp"></c:import>
	
		<!-- Banner Start -->
			
			<div class="banner padd">
				<div class="container">
					<!-- Image -->
					<img class="img-responsive" src="img/crown-white.png" alt="" />
					<!-- Heading -->
					<h2 class="white"><fmt:message key="titol" /></h2>
					<ol class="breadcrumb">
						<li><a href="index.html">Home</a></li>
						<li><a href="items.html">Shopping</a></li>
						<li><a href="item-single.html">Order Now</a></li>
						<li class="active">Checkout</li>
					</ol>
					<div class="clearfix"></div>
				</div>
			</div>
			
			<!-- Banner End -->
		<!-- Inner Content -->
<div class="inner-page padd">	
	<div class="checkout">
		<div class="container">	
			<div class="row">
				<div class="col-md-12 col-sm-6">				
				<h4><fmt:message key="comandaActual"/></h4>
				<p><fmt:message key="prodsEnComanda"/>: ${fn:length(comandaActual.liniesComanda)}</p>
						<table class="table table-stripped">
								<tr>
									<th><fmt:message key="fotoProd"/></th>
									<th><fmt:message key="producte"/></th>
									<th><fmt:message key="descProd"/></th>
									<th><fmt:message key="unitats"/></th>
									<th><fmt:message key="preu"/></th>																											
								</tr>													
							<c:forEach items="${comandaActual.liniesComanda}" var="liniaComanda">
								<tr>
									<td><img alt="img_wok" src="img?accioFoto=get&idProd=${liniaComanda.producte.id}"></td>									
									<td>${liniaComanda.producte.nom}</td>
									<td>${liniaComanda.producte.descripcio}</td>
									<td><input type="number" step="1" value="${liniaComanda.quantitat}"/></td>
									<td>${liniaComanda.preuVenda} €</td>									
								</tr>
							</c:forEach>
							<c:forEach items="${comandaActual.liniesComanda}" var="liniaComanda">
								<tr>
									<td><img alt="img_wok" src="img?accioFoto=get&idProd=${liniaComanda.producte.id}"></td>									
									<td>${liniaComanda.producte.nom}</td>
									<td>${liniaComanda.producte.descripcio}</td>
									<td><input type="number" step="1" value="${liniaComanda.quantitat}"/></td>
									<td>${liniaComanda.preuVenda} €</td>									
								</tr>
							</c:forEach>
						</table>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="modificarComanda"/></button>
						<button type="button" class="btn btn-info"><fmt:message key="confirmarComanda"/></button>
					</div>
						
										
					<p>Escull l'idioma: <a href="checkout?accioComanda=checkout&idioma=ca"><fmt:message key="catala"/></a> | <a href="checkout?accioComanda=checkout&idioma=es"><fmt:message key="castella"/></a></p>
					</div>
			</div>
		</div>
	</div>
</div>
	<c:import url="../imports/footerEwok.jsp"></c:import>		
	</body>	

</html>