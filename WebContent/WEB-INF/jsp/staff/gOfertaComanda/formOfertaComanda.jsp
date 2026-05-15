<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Internacionalitzacio -->
<fmt:setLocale value="${idioma}" />
<fmt:setBundle
	basename="com.soc.ewok.recursos.jsp.imports.formOfertaComanda" />
<!-- FI Internacionalitzacio -->

<c:import url="../imports/headerStaff.jsp"></c:import>

<!-- Formulari -->
<div class="inner-page padd">
	<div class="checkout">
		<div class="container">
			<!-- Heading -->
			<c:if test="${ofertaComanda != null }">
				<h4>
					<fmt:message key="gestiodeH4" />
				</h4>
			</c:if>
			<div class="row">
				<div class="col-md-12 col-sm-6">
					<c:if test="${ofertaComanda != null }">
						<h1>
							<fmt:message key="titolform" />
						</h1>
					</c:if>
					<c:if test="${ofertaComanda == null }">
						<h1>
							<fmt:message key="titolformalta" />
						</h1>
					</c:if>
					<!-- Checkout Form -->
					<c:if test="${ofertaComanda != null }">
						<form class="form-horizontal" role="form"
							action="staff?accioOfertaComanda=modificacio" method="post">
					</c:if>
					<c:if test="${ofertaComanda == null }">
						<form class="form-horizontal" role="form"
							action="staff?accioOfertaComanda=alta" method="post">
					</c:if>
					<%-- id --%>
					<c:if test="${ofertaComanda != null }">
						<div class="form-group">
							<label for="id" class="col-md-2 control-label"><fmt:message
									key="ide" /></label>
							<div class="col-md-8">
								<input type="text" readonly class="form-control" id="id"
									name="id" value="${ofertaComanda.id}">
							</div>
						</div>
					</c:if>
					<c:if test="${ofertaComanda == null }">
						<div class="form-group">
							<label for="id" class="col-md-2 control-label"><fmt:message
									key="ide" /></label>
							<div class="col-md-8">
								<input type="hidden" class="form-control" id="id"
									name="id" value="${ofertaComandaalta.id}">
							</div>
						</div>
					</c:if>
					
					<%-- 						<c:if test="${errorId != null}"> --%>
					<!-- 							<div class="alert-danger alert-form" role="alert">Cal -->
					<!-- 								informar un id</div> -->
					<%-- 						</c:if> --%>
					
					<%-- limit inferior --%>
					<c:if test="${ofertaComanda != null}">
					<div class="form-group">
						<label for="inputLimitInferior" class="col-md-2 control-label"><fmt:message
								key="limitInferior" /></label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="inputLimitInferior"
								name="inputLimitInferior" value="${ofertaComanda.limitInferior}"
								placeholder="Limit inferior per fer l'oferta">
						</div>
					</div>
					</c:if>
					
					<c:if test="${ofertaComanda == null}">
					<div class="form-group">
						<label for="inputLimitInferior" class="col-md-2 control-label"><fmt:message
								key="limitInferior" /></label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="inputLimitInferior"
								name="inputLimitInferior" value="${ofertaComandaalta.nom}"
								placeholder="Limit inferior per fer l'oferta">
						</div>
					</div>
					</c:if>
					
					<%-- descompte --%>
					<c:if test="${ofertaComanda != null }">
					<div class="form-group">
						<label for="inputDescompte" class="col-md-2 control-label"><fmt:message
								key="descompte" /></label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="inputDescompte"
								name="inputDescompte" value="${ofertaComanda.pctDescompte}"
								placeholder="Indiqui el Descompte de l'oferta">
						</div>
					</div>
					</c:if>
					<c:if test="${ofertaComanda == null }">
					<div class="form-group">
						<label for="inputDescompte" class="col-md-2 control-label"><fmt:message
								key="descompte" /></label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="inputDescompte"
								name="inputDescompte" value="${ofertaComanda.pctDescompte}"
								placeholder="Indiqui el Descompte de l'oferta">
						</div>
					</div>
					</c:if>
					<!--<c:if test="${ofertaComanda != null}">
						<div class="alert-danger alert-form" role="alert">
							${errorDescompte}</div>
					</c:if>-->
					<c:if test="${errorValorDescompte != null}">
						<div class="alert-danger alert-form" role="alert">
							${errorValorDescompte}</div>
					</c:if>
					
					<%-- 						<c:if test="${errorMsg != null}"> --%>
					<!-- 							<div class="alert-danger alert-form" role="alert">El format -->
					<!-- 								no és correcte</div> -->
					<%-- 						</c:if> --%>
					
					<%-- inici vigencia --%>
					<c:if test="${ofertaComanda != null}">
					<fmt:formatDate value="${ofertaComanda.iniciVigencia}"
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
					<c:if test="${ofertaComanda == null}">
					<fmt:formatDate value="${ofertaComandaalta.iniciVigencia}"
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
					<c:if test="${errorIniVigencia != null}">
						<div class="alert-danger alert-form" role="alert">
							${errorIniVigencia}</div>
					</c:if>
					
					<%-- fi vigencia --%>
					<c:if test="${ofertaComanda != null}">
					<fmt:formatDate value="${ofertaComanda.fiVigencia}"
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
					<c:if test="${ofertaComanda == null}">
					<fmt:formatDate value="${ofertaComandaalta.fiVigencia}"
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
					<c:if test="${errorFiVigencia != null}">
						<div class="alert-danger alert-form" role="alert">
							${errorFiVigencia}</div>
					</c:if>
					

					<%-- botons submit reset--%>
					
					<c:if test="${ofertaComanda == null }">

					</c:if>
					<div class="form-group">
						<div class="col-md-offset-2 col-md-8">
							<button type="submit" class="btn btn-danger btn-sm">
							<c:if test="${ofertaComanda != null}">
								<a href="staff?accioOfertaComanda=alta&id=${oc.id}">
									<fmt:message key="valor-btn-submit2" />
								</a>
							</c:if>
							<c:if test="${ofertaComanda == null}">
								<fmt:message key="valor-btn-submit" />
							</c:if>
							</button>
							&nbsp;
							<button type="reset" class="btn btn-default btn-sm">Reiniciar</button>
						</div>
					</div>
					</form>
				</div>
				<!-- 				<div class="col-md-5 col-sm-6"> -->
				<!-- 					Checkout sidebar items -->
				<!-- 					<div class="sidebar-item"> -->
				<!-- 						Heading -->
				<!-- 						<h5 class="pull-left">Lliurament a domicili</h5> -->
				<!-- 						Sidebar content icon -->
				<!-- 						<i class="fa fa-truck br-green pull-right"></i> -->
				<!-- 						<div class="clearfix"></div> -->
				<!-- 						Sidebar Paragraph -->
				<!-- 						<p>Lorem ipsum dolor sit amet, conse ctetur adipi sicing elit, -->
				<!-- 							sed do eiusmod tempor incid idunt ut labore et conse ctetur adipi -->
				<!-- 							sicing elit, sed do eiusmod dolore magna aliqua.</p> -->
				<!-- 					</div> -->
				<!-- 					Checkout sidebar items -->
				<!-- 					<div class="sidebar-item"> -->
				<!-- 						Heading -->
				<!-- 						<h5 class="pull-left">Necessites ajuda?</h5> -->
				<!-- 						Sidebar content icon -->
				<!-- 						<i class="fa fa-headphones br-red pull-right"></i> -->
				<!-- 						<div class="clearfix"></div> -->
				<!-- 						Sidebar Paragraph -->
				<!-- 						<p>Lorem ipsum dolor sit amet, conse ctetur adipi sicing elit, -->
				<!-- 							sed do eiusmod tempor incid idunt ut labore et conse ctetur adipi -->
				<!-- 							sicing elit, sed do eiusmod dolore magna aliqua.</p> -->
				<!-- 					</div> -->
				<!-- 				</div> -->
			</div>
			<hr>
			<div class="row">
				<p>
					<a class="btn btn-default btn-sm"
						href="staff?accioOfertaComanda=llistat"><fmt:message
							key="anar" /> <fmt:message key="llistat" /></a> <a
						class="btn btn-default btn-sm" href="staff"><fmt:message
							key="anar" /> Staff Home</a>
				</p>
			</div>
		</div>
	</div>
</div>

<!-- Fi formulari -->

<c:import url="../imports/footerStaff.jsp"></c:import>
</body>
</html>