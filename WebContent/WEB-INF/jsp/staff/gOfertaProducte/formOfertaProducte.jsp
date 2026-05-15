<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../imports/headerStaff.jsp"></c:import>

<%-- Internacionalitzacio --%>
<fmt:setLocale value="${idioma}" />
<fmt:setBundle
	basename="com.soc.ewok.recursos.jsp.ofertaproducte.formOfertaProducte" />
<%-- FI Internacionalitzacio --%>

<%-- Formulari --%>
<div class="inner-page padd">
	<div class="checkout">
		<div class="container">
			<!-- Heading -->
				<h4>
					<fmt:message key="gestiodeH4" />
				</h4>
			<div class="row">
				<div class="col-md-12 col-sm-6">
					<c:if test="${ofertaproducte != null }">
						<h1>
							<fmt:message key="titolform" />
						</h1>
					</c:if>
					<c:if test="${ofertaproducte == null }">
						<h1>
							<fmt:message key="titolformalta" />
						</h1>
					</c:if>
					<!-- Checkout Form -->
					<c:if test="${ofertaproducte != null }">
						<form class="form-horizontal" role="form"
							action="staff?accioOfertaProducte=modificacio" method="post">
					</c:if>
					<c:if test="${ofertaproducte == null }">
						<form class="form-horizontal" role="form"
							action="staff?accioOfertaProducte=alta" method="post">
					</c:if>
			<%-- id --%>
					<%-- modificacio --%>
					<c:if test="${ofertaproducte != null }">
						<div class="form-group">
							<label for="id" class="col-md-2 control-label"><fmt:message
									key="ide" /></label>
							<div class="col-md-8">
								<input type="text" readonly class="form-control" id="id"
									name="id" value="${ofertaproducte.id}">
							</div>
						</div>
					</c:if>
					<%-- alta --%>
					<c:if test="${ofertaproducte == null }">
						<div class="form-group">
							<%-- ><label for="id" class="col-md-2 control-label"><fmt:message
									key="ide" /></label> --%>
							<div class="col-md-8">
								<input type="hidden" class="form-control" id="id"
									name="id" value="${oferproductealta.id}">
							</div>
						</div>
					</c:if>
					<%--
	 						<c:if test="${errorId != null}"> 
	 							<div class="alert-danger alert-form" role="alert">Cal informar un id</div> 
	 						</c:if>
					 --%>
			<%-- descompte --%>
					<%-- modificacio --%>
					<c:if test="${ofertaproducte != null }">
					<div class="form-group">
						<label for="inputDescompte" class="col-md-2 control-label"><fmt:message
								key="descompte" /></label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="inputDescompte"
								name="inputDescompte" value="${ofertaproducte.pctDescompte}"
								placeholder="Indiqui el Descompte de l'oferta">
						</div>
					</div>
					</c:if>
					<%-- alta --%>
					<c:if test="${ofertaproducte == null }">
					<div class="form-group">
						<label for="inputDescompte" class="col-md-2 control-label"><fmt:message
								key="descompte" /></label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="inputDescompte"
								name="inputDescompte" value="${oferproductealta.pctDescompte}"
								placeholder="Indiqui el Descompte de l'oferta">
						</div>
					</div>
					</c:if>
					<%-- errormsg1 --%>
					<c:if test="${errorDescompte != null}">
						<div class="alert-danger alert-form" role="alert">
					</c:if>
					<c:if test="${errorDescompte == null}">
						<div class="alert-danger alert-form" role="alert" hidden="true">
					</c:if>
						<fmt:message key="errorDescompte" />
					</div>
					<%-- errormsg2 --%>
					<c:if test="${errorValorDescompte != null}">
						<div class="alert-danger alert-form" role="alert">
					</c:if>
					<c:if test="${errorValorDescompte == null}">
						<div class="alert-danger alert-form" role="alert" hidden="true">
					</c:if>
							<fmt:message key="errorDescompte" />
						</div>
					
			<%-- nom --%>
					<%-- modificacio --%>
					<c:if test="${ofertaproducte != null}">
					<div class="form-group">
						<label for="inputNom" class="col-md-2 control-label"><fmt:message
								key="nom" /></label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="inputNom"
								name="inputNom" value="${ofertaproducte.nom}"
								placeholder="El nom de la Oferta">
						</div>
					</div>
					</c:if>
					<%-- alta --%>
					<c:if test="${ofertaproducte == null}">
					<div class="form-group">
						<label for="inputNom" class="col-md-2 control-label"><fmt:message
								key="nom" /></label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="inputNom"
								name="inputNom" value="${oferproductealta.nom}"
								placeholder="El nom de la Oferta">
						</div>
					</div>
					</c:if>
					<%-- errormsg --%>
					<%--
			 						<c:if test="${errorMsg != null}"> 
			 							<div class="alert-danger alert-form" role="alert">El format	no és correcte</div> 
									</c:if>
					 --%>
			<%-- inici vigencia --%>
					<%-- modificacio --%>
					<c:if test="${ofertaproducte != null}">
					<fmt:formatDate value="${ofertaproducte.iniciVigencia}"
						var="iniVigenciaformatted" type="date" pattern="yyyy-MM-dd" />
					<div class="form-group">
						<label for="inputIniVigencia" class="col-md-2 control-label"><fmt:message
								key="inivigencia" /></label>
						<div class="col-md-8">
							<input type="date" class="form-control" id="inputIniVigencia"
								name="inputIniVigencia" value="${iniVigenciaformatted}"
								placeholder="La data d'inici de la Oferta">
						</div>
					</div>
					</c:if>
					<%-- alta --%>
					<c:if test="${ofertaproducte == null}">
					<fmt:formatDate value="${oferproductealta.iniciVigencia}"
						var="iniVigenciaformatted" type="date" pattern="yyyy-MM-dd" />
					<div class="form-group">
						<label for="inputIniVigencia" class="col-md-2 control-label"><fmt:message
								key="inivigencia" /></label>
						<div class="col-md-8">
							<input type="date" class="form-control" id="inputIniVigencia"
								name="inputIniVigencia" value="${iniVigenciaformatted}"
								placeholder="La data d'inici de la Oferta">
						</div>
					</div>
					</c:if>
					<%-- errormsg --%>
					<c:if test="${errorIniVigencia != null}">
						<div class="alert-danger alert-form" role="alert">	
					</c:if>
					<c:if test="${errorIniVigencia == null}">
						<div class="alert-danger alert-form" role="alert" hidden="true">	
					</c:if>
							<fmt:message key="errorIniVigencia" />
						</div>
					
			<%-- fi vigencia --%>
					<%-- modificacio --%>
					<c:if test="${ofertaproducte != null}">
					<fmt:formatDate value="${ofertaproducte.fiVigencia}"
						var="fiVigenciaformatted" type="date" pattern="yyyy-MM-dd" />
					<div class="form-group">
						<label for="inputFiVigencia" class="col-md-2 control-label"><fmt:message
								key="fivigencia" /></label>
						<div class="col-md-8">
							<input type="date" class="form-control" id="inputFiVigencia"
								name="inputFiVigencia" value="${fiVigenciaformatted}"
								placeholder="La data d'inici de la Oferta">
						</div>
					</div>
					</c:if>
					<%-- alta --%>
					<c:if test="${ofertaproducte == null}">
					<fmt:formatDate value="${oferproductealta.fiVigencia}"
						var="fiVigenciaformatted" type="date" pattern="yyyy-MM-dd" />
					<div class="form-group">
						<label for="inputFiVigencia" class="col-md-2 control-label"><fmt:message
								key="fivigencia" /></label>
						<div class="col-md-8">
							<input type="date" class="form-control" id="inputFiVigencia"
								name="inputFiVigencia" value="${fiVigenciaformatted}"
								placeholder="La data d'inici de la Oferta">
						</div>
					</div>
					</c:if>
					<%-- errormsg --%>
					<c:if test="${errorFiVigencia != null}">
						<div class="alert-danger alert-form" role="alert">
					</c:if>
					<c:if test="${errorFiVigencia == null}">
						<div class="alert-danger alert-form" role="alert" hidden="true">
					</c:if>
							<fmt:message key="errorFiVigencia" />
						</div>
					
			<%-- descripcio --%>
					<%-- modificacio --%>
					<c:if test="${ofertaproducte != null}">
					<div class="form-group">
						<label for="inputDescripcio" class="col-md-2 control-label"><fmt:message
								key="descripcio" /></label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="inputDescripcio"
								name="inputDescripcio" value="${ofertaproducte.otext}"
								placeholder="Descripció de la oferta">
						</div>
					</div>
					</c:if>
					<%-- alta --%>
					<c:if test="${ofertaproducte == null}">
					<div class="form-group">
						<label for="inputDescripcio" class="col-md-2 control-label"><fmt:message
								key="descripcio" /></label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="inputDescripcio"
								name="inputDescripcio" value="${oferproductealta.otext}"
								placeholder="Descripció de la oferta">
						</div>
					</div>
					</c:if>
					<%--
	 						<c:if test="${errorMsg != null}"> 
	 							<div class="alert-danger alert-form" role="alert">El format no és correcte</div>
	 						</c:if> 
					 --%>
			<%-- tipus producte --%>
				<%-- modificacio --%>
				<c:if test="${ofertaproducte != null}">
					<div class="form-group">
						<label for="inputTipusProducte" class="col-md-2 control-label"><fmt:message
								key="tipusproducte" /></label>
						<div class="col-md-8">
							<select class="form-control" id="inputTipusProducte" onload="requestAjaxProductes(this.value)" onchange="requestAjaxProductes(this.value)">
							<c:forEach items="${tipusprodlist}" var="tp">
							<c:if test="${producte.idTipusProducte == tp.id}">
							<option value="${tp.id}" selected>${tp.nom}</option>
							</c:if>
							<c:if test="${producte.idTipusProducte != tp.id}">
							<option value="${tp.id}">${tp.nom}</option>
							</c:if>
							</c:forEach>
							</select>
						</div>
					</div>
					</c:if>
					<%-- alta --%>
					<c:if test="${ofertaproducte == null}">
					<div class="form-group">
						<label for="inputTipusProducte" class="col-md-2 control-label"><fmt:message
								key="tipusproducte" /></label>
						<div class="col-md-8">
							<select class="form-control" id="inputTipusProducte" onload="requestAjaxProductes(this.value)" onchange="requestAjaxProductes(this.value)">
							<c:forEach items="${tipusprodlist}" var="tp">
							<c:if test="${producte.idTipusProducte == tp.id}">
							<option value="${tp.id}" selected>${tp.nom}</option>
							</c:if>
							<c:if test="${producte.idTipusProducte != tp.id}">
							<option value="${tp.id}">${tp.nom}</option>
							</c:if>
							</c:forEach>
							</select>
						</div>
					</div>
					</c:if>
					<%-- errormsg1 --%>
					<c:if test="${errorIdTipusProducte != null}">
						<div class="alert-danger alert-form" role="alert">${errorIdTipusProducte}</div>
					</c:if>
<%-- 					errormsg2 --%>
<%-- 					<c:if test="${errorTipusProducte != null}"> --%>
<%-- 						<div class="alert-danger alert-form" role="alert">${errorTipusProducte}</div> --%>
<%-- 					</c:if> --%>
			<%-- producte relacionat --%>
					<%-- modificacio --%>
					<c:if test="${ofertaproducte != null}">
					<div class="form-group">
						<label for="inputProducte" class="col-md-2 control-label"><fmt:message
								key="producte" /></label>
						<div class="col-md-8">
							<input type="hidden" class="form-control" id="inputProducte"
								name="inputProducte" value="${ofertaproducte.idProducte}"
								placeholder="Selecciona el Producte">
						</div>
						<div class="col-md-8" id="llistatproductesfiltrat"></div>
					</div>
					</c:if>
					<%-- alta --%>
					<c:if test="${ofertaproducte == null}">
					<div class="form-group">
						<label for="inputProducte" class="col-md-2 control-label"><fmt:message
								key="producte" /></label>
						<div class="col-md-8">
							<input type="hidden" class="form-control" id="inputProducte"
								name="inputProducte" value="${oferproductealta.idProducte}"
								placeholder="Selecciona el Producte">
						</div>
						<div class="col-md-8" id="llistatproductesfiltrat"></div>
					</div>
					</c:if>
					<%-- errormsg1 --%>
					<c:if test="${errorIdProducte != null}">
						<div class="alert-danger alert-form" role="alert">
					</c:if>
					<c:if test="${errorIdProducte == null}">
						<div class="alert-danger alert-form" role="alert" hidden="true">
					</c:if>
							<fmt:message key="errorIdProducte" />
						</div>					
					<%-- errormsg2 --%>
					<c:if test="${errorProducte != null}">
						<div class="alert-danger alert-form" role="alert">
					</c:if>
					<c:if test="${errorProducte == null}">
						<div class="alert-danger alert-form" role="alert" hidden="true">
					</c:if>
							<fmt:message key="errorProducte" />
						</div>

			<%-- botons submit reset--%>
					
					<c:if test="${ofertaproducte == null }">

					</c:if>
					<div class="form-group">
						<div class="col-md-offset-2 col-md-8">
							<button type="submit" class="btn btn-danger btn-sm">
							<c:if test="${ofertaproducte != null }">
								<fmt:message key="valor-btn-submit2" />
							</c:if>
							<c:if test="${ofertaproducte == null }">
								<fmt:message key="valor-btn-submit" />
							</c:if>
							</button>
							&nbsp;
							<button type="reset" class="btn btn-default btn-sm">Reiniciar</button>
						</div>
					</div>
					</form>
				</div>
			<%-- sidebar --%>
				<%-- 
								<div class="col-md-5 col-sm-6">
									Checkout sidebar items
									<div class="sidebar-item">
										Heading
										<h5 class="pull-left">Lliurament a domicili</h5>
										Sidebar content icon
										<i class="fa fa-truck br-green pull-right"></i>
										<div class="clearfix"></div>
										Sidebar Paragraph
										<p>Lorem ipsum dolor sit amet, conse ctetur adipi sicing elit,
											sed do eiusmod tempor incid idunt ut labore et conse ctetur adipi
											sicing elit, sed do eiusmod dolore magna aliqua.</p>
									</div>
									Checkout sidebar items
									<div class="sidebar-item">
										Heading
										<h5 class="pull-left">Necessites ajuda?</h5>
										Sidebar content icon
										<i class="fa fa-headphones br-red pull-right"></i>
										<div class="clearfix"></div>
										Sidebar Paragraph
										<p>Lorem ipsum dolor sit amet, conse ctetur adipi sicing elit,
											sed do eiusmod tempor incid idunt ut labore et conse ctetur adipi
											sicing elit, sed do eiusmod dolore magna aliqua.</p>
									</div>
								</div>
				--%>
			</div>
			<hr>
			<div class="row">
				<p>
					<a class="btn btn-default btn-sm"
						href="staff?accioOfertaProducte=llistat"><fmt:message
							key="anar" /> <fmt:message key="llistat" /></a>
				</p>
			</div>
		</div>
	</div>
</div>

<!-- Fi formulari -->

</body>

<c:import url="../imports/footerStaff.jsp"></c:import>
<script type="text/javascript" src="jseWok/formAjaxOfertaProducte.js"></script>

</html>