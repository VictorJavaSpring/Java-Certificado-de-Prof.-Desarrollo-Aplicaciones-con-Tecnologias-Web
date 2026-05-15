<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.ofertaPunts.formOfertaPunts"/>

<c:import url="../imports/headerStaff.jsp"></c:import>
<script src = "jseWok/ofertaPunts.js"></script>
<script src = "jseWok/tancarAlerts.js"></script>			
	<div class="inner-page padd">	
				<div class="checkout">
					<div class="container">
					<!-- Heading -->
					<h2><fmt:message key="titolForm"/></h2>
						<div class="row">
							<div class="col-md-9 col-sm-12">
							
								<!-- Checkout Form -->
										<c:if test="${ofertapunts == null }">
											<form class="form-horizontal" action="staff?accioOfertaPunts=alta" method="post" onsubmit="return isOfertaPuntsValid();" method="post" name="formOfertaPunts">
										</c:if>
										<c:if test="${ofertapunts != null }">
											<form class="form-horizontal" action="staff?accioOfertaPunts=modificacio&id=${ofertapunts.id}" onsubmit="return isOfertaPuntsValid();" method="post" name="formOfertaPunts">
										</c:if>
								
									<!-- Id -->
									<div class="form-group">
										<label for="id" class="col-md-2 control-label">Id</label>
										<div class="col-md-8">
											<p>${ofertapunts.id}</p>
										</div>
									</div> 
									<!-- Euros per punt -->				
									<div class="form-group">
										<label for="eurosPerPunt" class="col-md-2 control-label">
										<fmt:message key="eurosPerPunt" /></label>
										<div class="col-md-8">
											<c:if test="${ofertapunts == null }">
											<input type="text" class="form-control" id="eurosPerPunt" name="eurosPerPunt" 
											placeholder="<fmt:message key="eurosPerPuntPH" />" value="${ofertapuntsAlta.eurosPerPunt}"/>
											</c:if>
											<c:if test="${ofertapunts != null }">
											<input type="text" class="form-control" id="eurosPerPunt" name="eurosPerPunt" 
											placeholder="<fmt:message key="eurosPerPuntPH" />" value="${ofertapunts.eurosPerPunt}"/>
											</c:if>
										</div>
									</div>
									<%-- Div para el error en javascript 1 --%>
									<div class="alert-danger alert-form" role="alert" <c:if test="${errorEurosPunt == null}">
									hidden="true" 
									</c:if>  id="errorEPPDiv">
										<span class="error" id="spanErroreurosPerPunt"><fmt:message key="errorEurosPunt" /></span>
									
									</div>
									
									
									<!-- Punts per xec -->   
									<div class="form-group">
										<label for="puntsPerXec" class="col-md-2 control-label">
										<fmt:message key="puntsPerXec" /></label>
										<div class="col-md-8">
											<c:if test="${ofertapunts == null }">
											<input type="text" class="form-control" id="puntsPerXec" name="puntsPerXec" 
											placeholder=<fmt:message key="puntsPerXecPH" /> value="${ofertapuntsAlta.puntsPerXec}"/>
											</c:if>
											<c:if test="${ofertapunts != null }">
											<input type="text" class="form-control" id="puntsPerXec" name="puntsPerXec" 
											placeholder=<fmt:message key="puntsPerXecPH" /> value="${ofertapunts.puntsPerXec}"/>
											</c:if>
										</div>
									</div>
									<%-- Div para el error en javascript 1 --%>
									<div class="alert-danger alert-form" role="alert" <c:if test="${errorPuntsXec == null}">
									hidden="true" 
									</c:if>  id="errorPPXDiv">
										<span class="error" id="spanErrorpuntsPerXec"><fmt:message key="errorPuntsXec" /></span>
									
									</div>
									
									<!-- Dies de vigència dels Punts-->
									<div class="form-group">
										<label for="diesVigenciaPunts" class="col-md-2 control-label">
										<fmt:message key="diesVigenciaPunts" /></label>
										<div class="col-md-8">
											<c:if test="${ofertapunts == null }">											
											<input type="text" class="form-control" id="diesVigenciaPunts" name="diesVigenciaPunt" 
											placeholder=<fmt:message key="diesVigenciaPuntsPH" /> value="${ofertapuntsAlta.diesVigenciaPunts}"/>
											</c:if>
											<c:if test="${ofertapunts != null }">											
											<input type="text" class="form-control" id="diesVigenciaPunts" name="diesVigenciaPunt" 
											placeholder=<fmt:message key="diesVigenciaPuntsPH" /> value="${ofertapunts.diesVigenciaPunts}"/>
											</c:if>
										</div>
									</div>
									<%-- Div para el error en javascript 1 --%>
									<div class="alert-danger alert-form" role="alert" <c:if test="${errorDiesVigPunt == null}">
									hidden="true" 
									</c:if>  id="errorDVPDiv">
										<span class="error" id="spanErrordiesVigenciaPunts"><fmt:message key="errorDiesPunts" /></span>
									
									</div>
									
									<!-- Dies de vigència dels Xecs-->
									<div class="form-group">
										<label for="diesVigenciaXecs" class="col-md-2 control-label">
										<fmt:message key="diesVigenciaXecs" /></label>
										<div class="col-md-8">
											<c:if test="${ofertapunts == null }">
											<input type="text" class="form-control" id="diesVigenciaXecs" name="diesVigenciaXec" 
											placeholder=<fmt:message key="diesVigenciaXecsPH" /> value="${ofertapuntsAlta.diesVigenciaXecs}"/>
											</c:if>
											<c:if test="${ofertapunts != null }">
											<input type="text" class="form-control" id="diesVigenciaXecs" name="diesVigenciaXec" 
											placeholder=<fmt:message key="diesVigenciaXecsPH" /> value="${ofertapunts.diesVigenciaXecs}"/>
											</c:if>
										</div>
									</div>
									<%-- Div para el error en javascript 1 --%>
									<div class="alert-danger alert-form" role="alert" <c:if test="${errorDiesVigXec == null}">
									hidden="true" 
									</c:if>  id="errorDVXDiv">
										<span class="error" id="spanErrordiesVigenciaXecs"><fmt:message key="errorDiesXecs" /></span>
									
									</div>
									
							<!-- --------------- IniciVigencia ------------------------------ -->
									<!-- --------------- Date Inici Vigencia --------------- -->
									<div class="form-group">
									<label for="inVig" class="col-md-2 control-label">
									<fmt:message key="iniciVigencia" /></label>
										<div class="col-md-8">
										<c:if test="${ofertapunts == null }">
												<fmt:formatDate value='${ofertapuntsAlta.iniciVigencia}' type="date" pattern="yyyy-MM-dd" var="iniciVigenciaFormatted" />
												<input type="date" pattern="yyyy-MM-dd" class="form-control" id="inVig" pattern="yyyy-MM-dd" name="inVig" value="${iniciVigenciaFormatted}" placeholder="<fmt:message key='iniciVigenciaPH' />" />

										</c:if>
										<c:if test="${ofertapunts != null }">
											<fmt:formatDate value='${ofertapunts.iniciVigencia}' type="date" pattern="yyyy-MM-dd" var="iniciVigenciaFormatted" />
											<input type="date" pattern="yyyy-MM-dd" class="form-control" id="inVig" name="inVig" value="${iniciVigenciaFormatted}"/>
										</c:if>
										</div>
									</div>
									<!-- --------------- ERROR Date Inici Vigencia  --------------- -->
									<%-- Div para el error en javascript 1 --%>
									<div class="alert-danger alert-form" role="alert" <c:if test="${errorIniciVigencia == null}">
									hidden="true" 
									</c:if>  id="errorInVigDiv">
										<span class="error" id="spanErrorinVig"><fmt:message key="errorIniciVigencia" /></span>
									
									</div>									
									
									<!-- --------------- Time IniciVigencia --------------- -->
									<div class="form-group">
									<label for="inVigTime" class="col-md-2 control-label">
									<fmt:message key="iniciVigenciaTime" /></label>
										<div class="col-md-8">
										<c:if test="${ofertapunts == null }">
											<fmt:formatDate value="${ofertapuntsAlta.iniciVigencia}" type="time" pattern="HH:mm" var="iniciVigenciaFormattedTime" />
											<input type="time" class="form-control" id="inVigTime" name="inVigTime" value="${iniciVigenciaFormattedTime} placeholder="<fmt:message key='iniciVigenciaPHTime' />" />
										</c:if>
										<c:if test="${ofertapunts != null }">
											<fmt:formatDate value="${ofertapunts.iniciVigencia}" type="time" pattern="HH:mm" var="iniciVigenciaFormattedTime" />
											<input type="time" class="form-control" id="inVigTime" name="inVigTime" value="${iniciVigenciaFormattedTime}"/>											
										</c:if>
										</div>
									</div>
									<!-- --------------- ERROR  Time IniciVigencia  --------------- -->									
									<%-- Div para el error en javascript 1  NO CAL VALIDAR
									<div class="alert-danger alert-form" role="alert" <c:if test="${errorinVigTime == null}">
									hidden="true" 
									</c:if>  id="errorInVigTimeDiv">
										<span class="error" id="spanErrorinVigTime"><fmt:message key="errorIniciVigenciaTime" /> !!</span>
									
									</div>
									--%>
									<!-- ---------------  fiVigencia ------------------------------ -->
									<!-- --------------- Date fiVigencia --------------- -->
									<div class="form-group">
									<label for="fiVig" class="col-md-2 control-label">
									<fmt:message key="fiVigencia" /></label>
										<div class="col-md-8">
										<c:if test="${ofertapunts == null }">
											<fmt:formatDate value="${ofertapuntsAlta.fiVigencia}" type="date" pattern="yyyy-MM-dd" var="fiVigenciaFormatted" />
											<input type="date" class="form-control" id="fiVig" name="fiVig" 
												placeholder="<fmt:message key='fiVigPH' />" value="${fiVigenciaFormatted}"/>
										</c:if>
										<c:if test="${ofertapunts != null }">
											<fmt:formatDate value="${ofertapunts.fiVigencia}" type="date" pattern="yyyy-MM-dd" var="fiVigenciaFormatted" />
											<input type="date" class="form-control" id="fiVig" name="fiVig" 
												placeholder="<fmt:message key='fiVigPH' />" value="${fiVigenciaFormatted}"/>
										</c:if>
										</div>
									</div>
									<!-- --------------- ERROR Date fiVigencia  --------------- -->
									<%-- Div para el error en javascript 1 --%>
									<div class="alert-danger alert-form" role="alert" <c:if test="${errorFiVigencia == null}">
									hidden="true" 
									</c:if>  id="errorFiVigDiv">
										<span class="error" id="spanErrorfiVig"><fmt:message key="errorFiVigencia" /></span>
									
									</div>
										
									<!-- --------------- Time fiVigencia --------------- -->
									<div class="form-group">
									<label for="fiVigTime" class="col-md-2 control-label">
									<fmt:message key="fiVigenciaTime" /></label>
										<div class="col-md-8">
										<c:if test="${ofertapunts == null }">
											<fmt:formatDate value="${ofertapuntsAlta.fiVigencia}" type="time" pattern="HH:mm" var="fiVigenciaFormattedTime" />
											<input type="time" class="form-control" id="fiVigTime" name="fiVigTime" value="${fiVigenciaFormattedTime}" />
										</c:if>
										<c:if test="${ofertapunts != null }">
											<fmt:formatDate value="${ofertapunts.fiVigencia}" type="time" pattern="HH:mm" var="fiVigenciaFormattedTime" />
											<input type="time" class="form-control" id="fiVigTime" name="fiVigTime" 
												placeholder="<fmt:message key='fiVigTime' />" value="${fiVigenciaFormattedTime}"/>											
										</c:if>
										</div>
									</div>
									<!-- --------------- ERROR  Time fiVigencia  --------------- -->
									<%-- Div para el error en javascript 1  NO CAL VALIDAR
									<div class="alert-danger alert-form" role="alert" <c:if test="${errorfiVigTime == null}">
									hidden="true" 
									</c:if>  id="errorFiVigTimeDiv">
										<span class="error" id="spanErrorfiVigTime"><fmt:message key="errorFiVigenciaTime" /> !!</span>
									
									</div>--%>
									
									<!-- submit i reset -->
									<div class="form-group">
										<div class="col-md-offset-2 col-md-8">
											<button type="submit" class="btn btn-danger btn-sm">Confirmar Oferta</button>&nbsp;
											<button type="reset" class="btn btn-default btn-sm">Reiniciar</button>
										</div>
									</div>
								</form>
								<div>
					<p><fmt:message key="Anar"/> <a href="staff">Home</a><p>	
					<p><fmt:message key="Anar"/> <a href="staff?accioOfertaPunts=llistat">Llista</a><p>
					<p><fmt:message key="Anar"/> <a href="exemple">a web pública</a>.<p>					
					<p>Escull l'idioma: <a href="staff?accioOfertaPunts=prepararAlta&idioma=ca">Català</a> | <a href="staff?accioOfertaPunts=prepararAlta&idioma=es">Castellà</a></p>
					</div>
							</div>
						
							</div>									
					
						</div>
						
					</div>
					
				</div>
						<!-- Fi formulari -->
		
	</body>	

	<c:import url="../imports/footerStaff.jsp"></c:import>

</html>