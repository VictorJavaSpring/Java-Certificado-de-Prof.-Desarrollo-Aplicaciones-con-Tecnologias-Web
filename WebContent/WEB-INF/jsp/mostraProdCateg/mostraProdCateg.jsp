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
								<c:if test="${errorNoProd!=null}">
									<div class="default-heading">
										<!-- Crown image -->
										<img class="img-responsive" src="img/crown.png" alt="" />
										<h2><fmt:message key="errorNoProd"/></h2>
									</div>
								</c:if>
								<c:forEach items="${productesFiltrats}" var="prod">
								<%-- per a cada producte fer un shoppping item --%>
									<div class="col-md-3 col-sm-6">
										<!-- Shopping items -->
										<div class="shopping-item">
											<!-- Image -->
											<a href="item-single.html">
									<%-- assignar src img --%>
											<img class="img-responsive" src="img?accioFoto=get&idProd=${prod.id}" alt="Producte${prod.id}" /></a>
											<!-- Shopping item name / Heading -->
											<h4 class="pull-left"><a href="#">${prod.nom}</a></h4>
											<span class="item-price pull-right">${prod.preu}€</span>
											<div class="clearfix"></div>
											<!-- Paragraph -->
											<p>${prod.descripcioCurta}</p>
											<!-- Buy now button -->
											<div class="visible-xs">
												<a class="btn btn-danger btn-sm" href="#"><fmt:message key="compraAra" /></a>
											</div>
											<!-- Shopping item hover block & link -->
											<div class="item-hover-2 item-hover br-red hidden-xs"></div>
											<a class="link hidden-xs" onclick="onClickAfegirModificar(${prod.id}, 1)" style="cursor: pointer"><fmt:message key="afegirCarro" /></a>
											<!-- <a class="link hidden-xs" onclick="onClickAfegirModificar(1, 1)"><fmt:message key="afegirCarro" /></a>-->
											 
											<!-- Hot tag -->
<%-- 											<c:if test="${prod.oferta}"> --%>
<%-- 											<span class="hot-tag br-green">${prod.oferta}</span> --%>
<%-- 											</c:if> --%>
										</div>
									</div>
								</c:forEach>
							</div>
<%-- 							<!-- Pagination -->
							<div class="shopping-pagination">
								<ul class="pagination">
									<li class="disabled"><a href="#">&laquo;</a></li>
									<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">&raquo;</a></li>
								</ul>
							</div>
							<!-- Pagination end-->
--%>
						</div>
					</div>
				</div>
				
				<!-- Shopping End -->
		
			</div><!-- / Inner Page Content End -->		
		<!-- Fi contingut principal -->

	<c:import url="../imports/footerEwok.jsp"></c:import>
	</body>	
</html>







