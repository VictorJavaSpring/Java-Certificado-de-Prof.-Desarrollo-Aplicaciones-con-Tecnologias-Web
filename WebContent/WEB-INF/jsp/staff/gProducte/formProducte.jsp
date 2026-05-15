<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.productes.Productes_form_staff"/>

<c:import url="../imports/headerStaff.jsp"></c:import>

<script src = "jseWok/tancarAlerts.js"></script>
<script src = "jseWok/formProducte.js"></script>

<div class="inner-page padd">	
	<div class="checkout">
		<div class="container">

				<!-- Default Heading -->
				<div class="default-heading">
					<h1><fmt:message key="titolH1"/></h1>
					<!-- Crown image -->
					<img class="img-responsive" src="img/crown.png" alt="" />
					<!-- Heading -->
					<h2><fmt:message key="titolH4"/></h2>
					<!-- Paragraph -->
					<p><fmt:message key="Anar"/> <a href="staff"><fmt:message key="privada"/></a><p>
					<p><fmt:message key="textIntro"/></p>
					<!-- Border -->
					<div class="border"></div>
				</div>						
			
				<!-- --------------- COMENÇA FORMULARI PRODUCTE  --------------- -->
				<!-- Heading -->
				<!-- titol h4 -->
				<c:if test="${productes == null }">
					<h2><fmt:message key="titolH4alta"/></h2>
				</c:if>
				
				<c:if test="${productes != null }">
					<h2><fmt:message key="titolH4modificacio"/>${productes.id}</h2>
				</c:if>						
				<!-- Fi titol h4 -->
				<div class="row">
				<div class="col-md-7 col-sm-6">
					<!-- Checkout Form -->
					<c:if test="${productes == null }">
						<form class="form-horizontal" action="staff?accioProductes=alta" onsubmit="return isFormValid();" method="post" >
					</c:if>
					<c:if test="${productes != null }">
						<form class="form-horizontal" action="staff?accioProductes=modificacio" onsubmit="return isFormValid();" method="post">
					</c:if>
								
					<!-- --------------- Id --------------- -->
					<div class="form-group">
						<c:if test="${productes == null }">
<!-- 							<label for="id" class="col-md-2 control-label">ID</label> -->
							<div class="col-md-8">
								<input type="hidden" class="form-control" id="id" name="id" 
								placeholder="Id" value="${productesAlta.id}" readonly/>
							</div>
						</c:if>
						<c:if test="${productes != null }">
							<label for="id" class="col-md-2 control-label">Id</label>
							<div class="col-md-8">
								<input type="number" class="form-control" id="id" name="id" 
								placeholder="id" value="${productes.id}" readonly/>
							</div>
						</c:if>
					</div>
	
					<!-- --------------- Nom --------------- -->
					<div class="form-group">
						<label for="nom" class="col-md-2 control-label">
						<fmt:message key="Nom" /></label>
						<c:if test="${productes == null }">
							<div class="col-md-8">
								<input type="text" class="form-control" id="nom" name="nom" accesskey="n" tabindex="1"
								placeholder="<fmt:message key="NomPH" />" value="${productesAlta.nom}" autofocus />
							</div>
						</c:if>
						<c:if test="${productes != null }">
							<div class="col-md-8">
								<input type="text" class="form-control" id="nom" name="nom" accesskey="n" tabindex="1"
								placeholder="<fmt:message key="NomPH" />" value="${productes.nom}" autofocus />
							</div>
						</c:if>
					</div>
			
					<!-- --------------- ERROR Nom  --------------- -->
					<div class="alert-danger alert-form" <c:if test="${!errorNomJAVA}">
					hidden="true" 
					</c:if>  role="alert"  id="errorNomDiv">
					<span id="errorNom"  class="alert-form alert-danger alert-ewok error">
					<fmt:message key = "errorNom"></fmt:message>
					</span>
					</div>
					
					<!-- --------------- descripcioCurta --------------- -->
					<div class="form-group">
						<label for="descripcioCurta" class="col-md-2 control-label">
						<fmt:message key="DescCurta" /></label>
						<c:if test="${productes == null }">
							<div class="col-md-8">
								<input type="text" class="form-control" id="descripcioCurta" name="descripcioCurta" 
								placeholder="<fmt:message key='DescCurtaPH' />" value="${productesAlta.descripcioCurta}"/>
							</div>
						</c:if>
						<c:if test="${productes != null }">
							<div class="col-md-8">
								<input type="text" class="form-control" id="descripcioCurta" name="descripcioCurta" 
								placeholder="<fmt:message key="DescCurtaPH" />" value="${productes.descripcioCurta}"/>
							</div>
						</c:if>
					</div>
					
					<!-- --------------- descripcio --------------- -->
					<div class="form-group">
						<label for="descripcio" class="col-md-2 control-label">
						<fmt:message key="Desc" /></label>
						<!-- 	<textarea rows="" cols=""></textarea> -->
						<c:if test="${productes == null }">
							<div class="col-md-8" >
								<textarea class="form-control" id="descripcio" name="descripcio" cols="10" rows="4"  maxlength="256" wrap="soft">${productesAlta.descripcio}</textarea>
							</div>
						</c:if>
						
						<c:if test="${productes != null }">
							<div class="col-md-8">
								<textarea class="form-control" id="descripcio" name="descripcio" cols="10" rows="4"  maxlength="256" wrap="soft">${productes.descripcio}</textarea>
							</div>
						</c:if>
					</div>

					<!-- --------------- IniciVigencia ------------------------------ -->
					<!-- --------------- Date IniciVigencia --------------- -->
					<div class="form-group">
						<label for="inVig" class="col-md-2 control-label">
						<fmt:message key="inVig" /></label>
						<div class="col-md-8">
							<c:if test="${productes == null }">
								<fmt:formatDate value='${productesAlta.iniciVigencia}' type="date" pattern="yyyy-MM-dd" var="iniciVigenciaFormatted" />
								<input type="date" class="form-control" id="inVig" name="inVig" 
								placeholder="<fmt:message key='inVigPH' />" value="${iniciVigenciaFormatted}"/> 
							</c:if>
							<c:if test="${productes != null }">
								<fmt:formatDate value='${productes.iniciVigencia}' type="date" pattern="yyyy-MM-dd" var="iniciVigenciaFormatted" />
								<input type="date" class="form-control" id="inVig" name="inVig" 
									placeholder="<fmt:message key='inVigPH' />" value="${iniciVigenciaFormatted}"/>
							</c:if>
						</div>
					</div>
					<!-- --------------- ERROR Date IniciVigencia  --------------- -->
					
					<div class="alert-danger alert-form" role="alert"<c:if test="${!errorIniciVigenciaJAVA}">
					hidden="true" 
					</c:if>  role="alert"  id="errorIniciVigenciaDiv">
					<span id="errorIniciVigencia"  class="alert-form alert-danger alert-ewok error">
					<fmt:message key="errorIniciVigencia"></fmt:message>
					</span>
					</div>
					
					<!-- --------------- Time IniciVigencia --------------- -->
					<div class="form-group">
					<label for="inVigTime" class="col-md-2 control-label">
					<fmt:message key="inVigTime" /></label>
						<div class="col-md-8">
						<c:if test="${productes == null }">
							<fmt:formatDate value="${productesAlta.iniciVigencia}" type="time" pattern="HH:mm" var="iniciVigenciaFormattedTime" />
							<input type="time" class="form-control" id="inVigTime" name="inVigTime" 
							placeholder="<fmt:message key='inVigPHTime' />"  value="${iniciVigenciaFormattedTime}"/>											
						</c:if>
						<c:if test="${productes != null }">
							<fmt:formatDate value="${productes.iniciVigencia}" type="time" pattern="HH:mm" var="iniciVigenciaFormattedTime" />
							<input type="time" class="form-control" id="inVigTime" name="inVigTime" 
							placeholder="<fmt:message key='inVigPHTime' />" value="${iniciVigenciaFormattedTime}"/>											
						</c:if>
						</div>
					</div>
					<!-- --------------- ERROR  errorIniciVigenciaTime  --------------- -->
					<div class="alert-danger alert-form" role="alert"<c:if test="${!errorIniciVigenciaTimeJAVA}">
					hidden="true" 
					</c:if>   id="errorIniciVigenciaTimeDiv" >
					<span id="errorIniciVigenciaTime"  class="alert-form alert-danger alert-ewok">
					<fmt:message key="errorIniciVigenciaTime"></fmt:message>
					</span>
					</div>
				
					<!-- --------------- ERROR  Hora sense Data IniciVigencia  --------------- -->
					<div class="alert-danger alert-form" role="alert"<c:if test="${!errorIniciVigenciaSenseDataJAVA}">
					hidden="true" 
					</c:if>   id="errorIniciVigenciaSenseDataDiv" >
					<span id="errorIniciVigenciaSenseData"  class="alert-form alert-danger alert-ewok">
					<fmt:message key="errorIniciVigenciaSenseData"></fmt:message>
					</span>
					</div>					

					<!-- ---------------  fiVigencia ------------------------------ -->
					<!-- --------------- Date fiVigencia --------------- -->
					<div class="form-group">
					<label for="fiVig" class="col-md-2 control-label">
					<fmt:message key="fiVig" /></label>
						<div class="col-md-8">
						<c:if test="${productes == null }">
							<fmt:formatDate value="${productesAlta.fiVigencia}" type="date" pattern="yyyy-MM-dd" var="fiVigenciaFormatted" />
							<input type="date" class="form-control" id="fiVig" name="fiVig" 
								placeholder="<fmt:message key='fiVigPH' />" value="${fiVigenciaFormatted}"/>
						</c:if>
						<c:if test="${productes != null }">
							<fmt:formatDate value="${productes.fiVigencia}" type="date" pattern="yyyy-MM-dd" var="fiVigenciaFormatted" />
							<input type="date" class="form-control" id="fiVig" name="fiVig" 
								placeholder="<fmt:message key='fiVigPH' />" value="${fiVigenciaFormatted}"/>
						</c:if>
						</div>
					</div>
					<!-- --------------- ERROR Date fiVigencia  --------------- -->
					<div class="alert-danger alert-form" role="alert"<c:if test="${!errorFiVigenciaJAVA}">
					hidden="true" 
					</c:if>  id="errorFiVigenciaDiv"  >
					<span id="errorFiVigenciaTime"  class="alert-form alert-danger alert-ewok error">
					<fmt:message key="errorFiVigencia"></fmt:message>
					</span>
					</div>
					
					
					<!-- --------------- Time fiVigencia --------------- -->
					<div class="form-group">
					<label for="fiVigTime" class="col-md-2 control-label">
					<fmt:message key="fiVigTime" /></label>
						<div class="col-md-8">
						<c:if test="${productes == null }">
							<fmt:formatDate value="${productesAlta.fiVigencia}" type="time" pattern="HH:mm" var="fiVigenciaFormattedTime" />
							<input type="time" class="form-control" id="fiVigTime" name="fiVigTime" 
							placeholder="<fmt:message key='fiVigPHTime' />"  value="${fiVigenciaFormattedTime}"/>											
						</c:if>
						<c:if test="${productes != null }">
							<fmt:formatDate value="${productes.fiVigencia}" type="time" pattern="HH:mm" var="fiVigenciaFormattedTime" />
							<input type="time" class="form-control" id="fiVigTime" name="fiVigTime" 
							placeholder="<fmt:message key='fiVigTime' />" value="${fiVigenciaFormattedTime}"/>											
						</c:if>
						</div>
					</div>
					<!-- --------------- ERROR  Time fiVigencia  --------------- -->
					<div class="alert-danger alert-form" role="alert"<c:if test="${!errorFiVigenciaTimeJAVA}">
					hidden="true" 
					</c:if>  id="errorFiVigenciaTimeDiv">
					<span id="errorFiVigenciaTime"  class="alert-form alert-danger alert-ewok error">
					<fmt:message key="errorFiVigenciaTime"></fmt:message>
					</span>
					</div>
					
					<!-- --------------- ERROR  Hora sense Data FiVigencia  --------------- -->
					<div class="alert-danger alert-form" role="alert"<c:if test="${!errorFiVigenciaSenseDataJAVA}">
					hidden="true" 
					</c:if>   id="errorFiVigenciaSenseDataDiv" >
					<span id="errorFiVigenciaSenseData"  class="alert-form alert-danger alert-ewok">
					<fmt:message key="errorFiVigenciaSenseData"></fmt:message>
					</span>
					</div>		

		
					<!-- --------------- IdUnitat --------------- -->
					<div class="form-group">
						<label for="idUnitat" class="col-md-2 control-label">
						<fmt:message key="IdUnitat" /></label>
						<c:if test="${productes == null }">
							<div class="col-md-8">
								<input type="number" class="form-control" id="idUnitat" name="idUnitat" 
								placeholder="<fmt:message key="IdUnitatPH" />" value="${productesAlta.idUnitat}"/>
							</div>
						</c:if>
						<c:if test="${productes != null }">
							<div class="col-md-8">
								<input type="number" class="form-control" id="idUnitat" name="idUnitat" 
								placeholder="<fmt:message key="IdUnitatPH" />" value="${productes.idUnitat}"/>
							</div>
						</c:if>
					</div>
		
					<!-- --------------- ERROR idUnitat  --------------- -->
					<div class="alert-danger alert-form" role="alert"<c:if test="${!errorIdUnitatJAVA}">
					hidden="true" 
					</c:if>  id=errorIdUnitatDiv>
					<span id="errorIdUnitat"  class="alert-form alert-danger alert-ewok error">
					<fmt:message key="errorIdUnitat"></fmt:message>
					</span>
					</div>
					
					<!-- --------------- IdTipusProducte --------------- -->
					<div class="form-group">
						<label for="idTipusProducte" class="col-md-2 control-label">
						<fmt:message key="IdTipusProducte" /></label>
						
						<c:if test="${productes == null }">
							<div class="col-md-8">
								<input type="number" class="form-control" id="idTipusProducte" name="idTipusProducte" 
								placeholder="<fmt:message key="IdTipusPH" />" value="${productesAlta.idTipusProducte}"/>
							</div>
						</c:if>
						<c:if test="${productes != null }">
							<div class="col-md-8">
								<input type="number" class="form-control" id="idTipusProducte" name="idTipusProducte" 
								placeholder="<fmt:message key="IdTipusPH" />" value="${productes.idTipusProducte}"/>
							</div>
						</c:if>
					</div>
					<!-- --------------- ERROR IdTipusProducte  --------------- -->
					<div class="alert-danger alert-form" role="alert"<c:if test="${!errorIdTipusProducteJAVA}">
					hidden="true" 
					</c:if>  id=errorIdTipusProducteDiv>
					<span id="errorIdTipusProducte"  class="alert-form alert-danger alert-ewok error">
					<fmt:message key="errorIdTipusProducte"></fmt:message>
					</span>
					</div>
		
					<!-- submit i reset -->
					<div class="form-group">
						<div class="col-md-offset-2 col-md-8">
							<button type="submit" class="btn btn-danger btn-sm"><fmt:message key="confirmar"></fmt:message></button>&nbsp;
							<a class="btn btn-danger btn-sm" href="staff?accioProductes=llistat">Cancel·lar</a>
						</div>
					</div>
				<!-- --------------- FI FORMULARI PRODUCTE  --------------- -->
					
		</form>
		</div>
		</div>
				<h2><fmt:message key="Anar"/>...</h2>
				<div class="list-group">
					<a class="list-group-item" href="staff?accioProductes=llistat"><fmt:message key="llistat"/></a>
			 		<ul>
						<li><h3><fmt:message key="Tria"/> <a href="staff?accioProductes=prepararAlta&id=${producte.id}&idioma=ca">Català<a/>
						 | <a href="staff?accioProductes=prepararAlta&id=${producte.id}&idioma=es">Castellà<a/></h3>
						</li>
					 </ul>
				</div>
				</div>
 			</div>
		</div>
		</div>
		</div>
</body>	
	<c:import url="../imports/footerStaff.jsp"></c:import>
</html>