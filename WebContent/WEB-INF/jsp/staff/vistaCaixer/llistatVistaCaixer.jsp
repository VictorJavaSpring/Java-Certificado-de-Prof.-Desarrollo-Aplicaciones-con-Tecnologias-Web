<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<fmt:setLocale value="${idioma}"/>
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.vistacaixer.llistat_vistacaixer"/>
<c:import url="../imports/headerStaff.jsp"></c:import>
<!-- capçalera de pàgina -->
			<div class="container">
				<!-- Default Heading -->
				<div class="default-heading">
					<h1><fmt:message key="titolH1"/></h1>
					<!-- Crown image -->
					<img class="img-responsive" src="img/crown.png" alt="" />
					<!-- Heading -->

					<h2><fmt:message key="titolH4"/></h2>
					<!-- Paragraph -->
					<p><fmt:message key="Anar"/>  <a href="staff"><fmt:message key="privada"/></a><p>
					<p><fmt:message key="textIntro"/></p>
					<!-- Border -->
					<div class="border"></div>
				</div>						
			</div>					
<!-- Border -->
<div class="border"></div>

<div class="inner-page padd">
	<div class="checkout">
		<div class="container">
		
			<div class="row">
				<div class="col-md-12 col-sm-6">

					<table class="table table-striped table-bordered">
						<tr >
							<th><fmt:message key="dataComanda" /></th>
							<th><fmt:message key="preuFinal" /></th>
							<th><fmt:message key="comentaris" /></th>
							<th><fmt:message key="estat" /></th>
							<th><fmt:message key="accio" /></th>
						</tr>
						<c:forEach items="${comandesVP}" var="comanda">
						<tr id ="TbodyC_${comanda.id}" class="normal table table-striped table-bordered" style="background-color:rgb(255, 255, 255)">
							<td>${comanda.data}</td>
							<td>${comanda.preuFinal}</td>
							<td>${comanda.comentaris}</td>
							<td id="estatComanda_${comanda.id}">${comanda.estat}</td>
							<td >timo
							<fmt:message key="canviEstatComanda"/>
								<!-- 							comanda en estat validada -->
								<c:if test="${comanda.estat == 'validada'}">
								
<%-- 									<button  onclick="canviEstatComanda(${comanda.id})"  class="btn btn-default btn-sm"> 
  <h2>Button Tags</h2>
  <a href="#" class="btn btn-info" role="button">Link Button</a>
  <button type="button" class="btn btn-info">Button</button>
  <input type="button" class="btn btn-info" value="Input Button">
  <input type="submit" class="btn btn-info" value="Submit Button">

--%>
									<button disabled>
										<span class="fa-stack fa-lg">
										<%--  BORDE DEL ICONO --%> 
										<i class="fa fa-square-o fa-stack-2x"></i>
  									    <i class="fa fa-eur fa-stack-1x"  id="icon_${comanda.id}"></i>
										 <input type="text" hidden="true" id="value_${comanda.id}" value="${comanda.estat}">
										</input>
										</span>
										
									</button>								
<%-- 									<i class="fa fa-eur"  id="icon_${comanda.id}"></i></button> --%>
								</c:if>
								
								<!-- 							comanda en estat pagada -->
								<c:if test="${comanda.estat == 'pagada'}">
<%-- 									<button onclick="canviEstatComanda(${comanda.id})"  class="btn btn-default btn-sm"> --%>
									<button disabled>
										<span class="fa-stack fa-lg">
										  <i class="fa fa-square-o fa-stack-2x"></i>
										  <i class="fa fa-eur fa-stack-1x"  id="icon_${comanda.id}"></i>
										 <input type="text" hidden="true" id="value_${comanda.id}" value="${comanda.estat}"/>
											
										</span>
									</button>	
								</c:if>
								
								<!-- 							comanda en estat preparada -->
								<c:if test="${comanda.estat == 'preparada'}">
									<button onclick="canviEstatComanda(${comanda.id})" >
										<span class="fa-stack fa-lg">
										 	<i class="fa fa-square-o fa-stack-2x"></i>
											<i class="fa fa-eur fa-stack-1x"  id="icon_${comanda.id}"></i>
											<input type="text" hidden="true" id="value_${comanda.id}" value="${comanda.estat}"/>
										</span>	
									</button>
								</c:if>	
								
								<!-- 							comanda en estat preparadaIPagada -->
								<c:if test="${comanda.estat == 'preparadaIPagada'}">
									<button onclick="canviEstatComanda(${comanda.id})" >
										<span class="fa-stack fa-lg">
										 	<i class="fa fa-square-o fa-stack-2x"></i>
											<i class="fa fa-eur fa-stack-1x"  id="icon_${comanda.id}"></i>
											<input type="text" hidden="true" id="value_${comanda.id}" value="${comanda.estat}"/>
										</span>	
									</button>
								</c:if>
								
								<!-- 							comanda en estat entregada -->
								<c:if test="${comanda.estat == 'entregada'}">
									<!-- 								   class="btn btn-default btn-sm" -->
									<button onclick="amagaComanda(${comanda.id})">
										<span class="fa-stack fa-lg">
										  <i class="fa fa-square-o fa-stack-2x"></i>
										  <i class="fa fa-eur fa-stack-1x"  id="icon_${comanda.id}"></i>
										 <input type="text" hidden="true" id="value_${comanda.id}" value="${comanda.estat}"/>
										</span>
									</button>	
								</c:if>
								
								<button onclick="requestLiniesComanda(${comanda.id})" class="btn btn-default btn-sm" >
								<i class="fa fa-eye fa-2x"> &bsp;<fmt:message key='detallsComanda' /></i>
								</button>
							</td>
						</tr>
						</c:forEach>
					</table>
				</div>
					
					<table id="TaulaLC" class="table table-striped table-bordered tableEwok " style="display:none;">
						<thead>
							<tr>
								<th><fmt:message key='LClinia' /></th>
								<th>FOTO Producte</th>
								<th>Nom Producte</th>
								<th><fmt:message key='LCestat' /></th>
								<th><fmt:message key='LCpreuVenda' /></th>
								<th><fmt:message key='LCquantitat' /></th>
								<th><fmt:message key='accio' /></th>
							</tr>
						</thead>
						<tbody id="TbodyLC" style="display:none;">
<!-- 						<tbody id="TbodyLC" class="table table-striped table-bordered tableEwok " style="display:none;"> -->
						</tbody>
					</table>
				</div>
			</div>
			<ul>
				<li><h3><fmt:message key="Tria"/> <a href="staff?accioVistaCaixer=llistat&idioma=ca">
				Català</a> | <a href="staff?accioVistaCaixer=llistat&idioma=es"
				>Castellà</a></h3>
				</li>
			</ul>
		</div>
		
	</div>
	
</div>
<script type="text/javascript" src="jseWok/AjaxVCaixer.js"></script>

</body>	

<c:import url="../imports/footerStaff.jsp"></c:import>

