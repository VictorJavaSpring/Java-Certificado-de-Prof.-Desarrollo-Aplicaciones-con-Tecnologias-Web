<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="../imports/headerStaff.jsp"></c:import>

<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.preu.formPreu"/>	
			
	<!-- Formulari -->
		<div class="inner-page padd">	
			<div class="checkout">
				<div class="container">
				<!-- Heading -->
					
					<c:if test="${preu == null}">
						<h4><fmt:message key="h4AltaPreu"/></h4>
					</c:if>
					<c:if test="${preu != null}">
						<h4>
							<fmt:message key="h4ModificaPreu">
								<fmt:param value="${preu.id}"/>
							</fmt:message>
						</h4>
					</c:if>
					
					<div class="row">
						<div class="col-md-7 col-sm-6">
							
							<!-- Checkout Form -->
							<c:if test="${preu == null}">
								<form class="form-horizontal form-preu" role="form" action="staff?accioPreu=alta" method="post">
							</c:if>
							<c:if test="${preu != null}">
								<form class="form-horizontal form-preu" role="form" action="staff?accioPreu=modificacio&id=${preu.id}" method="post">
							</c:if>
								
								<!-- Input preu -->

									<div class="form-group">
										<label for="preu" class="col-md-2 control-label"><fmt:message key="inputPreu"/></label>
										<div class="col-md-8">
										<fmt:message key="placeholderPreu" var="phPreu" scope="request"/>
											<c:if test="${preu == null}">
												<input type="text" class="form-control" id="preu" name="preu" placeholder="${phPreu}" value="${param.preu}"/>
											</c:if>
											<c:if test="${preu != null}">
												<input type="text" class="form-control" id="preu" name="preu" placeholder="${phPreu}" value="${preu.preu}"/>
											</c:if>
										</div>
									</div>
									
									<!-- Div error preu -->
									<div 
										class="alert-danger alert alert-form" 
										role="alert" id="errorPreu"
										<c:if test="${errorPreu == null}">
										hidden="true"
										</c:if>
										>
										<fmt:message key="errorPreu1"/>
									</div>
									
									
								<!-- Input data inici vigencia -->
								<div class="form-group">
									<label for="dataInici" class="col-md-2 control-label"><fmt:message key="inputDataInici"/></label>
									<div class="col-md-8">
									<fmt:message key="placeholderDataInici" var="phDataInici" scope="request"/>
										<c:if test="${preu == null}">
											<input type="date" class="form-control" id="dataInici" name="dataInici" placeholder="${phDataInici}" value="${param.dataInici}">
										</c:if>
										<c:if test="${preu != null}">
											<fmt:formatDate value='${preu.iniciVigencia}' var="dataIniciVigencia" type="date" pattern='yyyy-MM-dd'/>
											<input type="date" class="form-control" id="dataInici" name="dataInici" placeholder="${phDataInici}" value="${dataIniciVigencia}">
										</c:if>
									</div>
								</div>
								<c:if test="${errorDataInici != null}">
									<div class="alert-danger alert-form" role="alert">		
										<fmt:message key="errorDataInici1"/>
									</div>
								</c:if>
								<c:if test="${errorDataIniciNN != null}">
									<div class="alert-danger alert-form" role="alert">		
										<fmt:message key="errorDataInici2"/>
									</div>
								</c:if>

								<!-- Input hora inici vigencia -->
								<div class="form-group">
									<label for="horaInici" class="col-md-2 control-label"><fmt:message key="inputHoraInici"/></label>
									<div class="col-md-8">
									<fmt:message key="placeholderHoraInici" var="phHoraInici" scope="request"/>
										<c:if test="${preu == null}">
											<input type="time" class="form-control" id="horaInici" name="horaInici" placeholder="${phHoraInici}" value="${param.horaInici}">
										</c:if>
										<c:if test="${preu != null}">
											<fmt:formatDate value='${preu.iniciVigencia}' var="horaIniciVigencia" type="time" pattern='HH:mm'/>
											<input type="time" class="form-control" id="horaInici" name="horaInici" placeholder="${phHoraInici}" value="${horaIniciVigencia}">
										</c:if>
									</div>
								</div>
								<c:if test="${errorHoraInici != null}">
									<div class="alert-danger alert-form" role="alert">		
										<fmt:message key="errorHoraInici1"/>
									</div>
								</c:if>	

								<!-- Input data final vigencia -->
								<div class="form-group">
									<label for="dataFinal" class="col-md-2 control-label"><fmt:message key="inputDataFinal"/></label>
									<div class="col-md-8">
									<fmt:message key="placeholderDataFinal" var="phDataFinal" scope="request"/>
										<c:if test="${preu == null}">
											<input type="date" class="form-control" id="dataFinal" name="dataFinal" placeholder="${phDataFinal}" value="${param.dataFinal}">
										</c:if>
										<c:if test="${preu != null}">
										<fmt:formatDate value="${preu.finalVigencia}" var="dataFinalVigencia" type="date" pattern="yyyy-MM-dd" />
											<input type="date" class="form-control" id="dataFinal" name="dataFinal" placeholder="${phDataFinal}" value="${dataFinalVigencia}">
										</c:if>
									</div>
								</div>
								<c:if test="${errorDataFinal != null}">
									<div class="alert-danger alert-form" role="alert">		
										<fmt:message key="errorDataFinal1"/>
									</div>
								</c:if>
								<c:if test="${errorDataFinalNN != null}">
									<div class="alert-danger alert-form" role="alert">		
										<fmt:message key="errorDataFinal2"/>
									</div>
								</c:if>

								<!-- Input hora final vigencia -->
								<div class="form-group">
									<label for="horaFinal" class="col-md-2 control-label"><fmt:message key="inputHoraFinal"/></label>
									<div class="col-md-8">
									<fmt:message key="placeholderHoraFinal" var="phHoraFinal" scope="request"/>
										<c:if test="${preu == null}">
											<input type="time" class="form-control" id="horaFinal" name="horaFinal" placeholder="${phHoraFinal}" value="${param.horaFinal}">
										</c:if>
										<c:if test="${preu != null}">
										<fmt:formatDate value="${preu.finalVigencia}"	var="horaFinalVigencia" type="time" pattern="HH:mm" />
											<input type="time" class="form-control" id="horaFinal" name="horaFinal" placeholder="${phHoraFinal}" value="${horaFinalVigencia}">
										</c:if>
									</div>
								</div>
								<c:if test="${errorHoraFinal != null}">
									<div class="alert-danger alert-form" role="alert">		
										<fmt:message key="errorHoraFinal1"/>
									</div>
								</c:if>

								<!-- Input id producte -->
								
								<div class="form-group">
									<label for="idProducte" class="col-md-2 control-label"><fmt:message key="inputIdProducte"/></label>
									<div class="col-md-8">
									<fmt:message key="placeholderIdProducte" var="phIdProducte" scope="request"/>
										<c:if test="${preu == null}">
											<input type="text" class="form-control" id="idProducte" name="idProducte" placeholder="${phIdProducte}" value="${param.idProducte}">
										</c:if>
										<c:if test="${preu != null}">
											<input type="text" class="form-control" id="idProducte" name="idProducte" placeholder="${phIdProducte}" value="${preu.idProducte}">
										</c:if>
									</div>
								</div>
								
								<!-- Div Error idProducte -->
								<div 
									class="alert-danger alert-form" 
									role="alert" id="errorIdProducte" 
									<c:if test="${errorIdProducte == null}">
										hidden="true"
									</c:if>
									>		
									<fmt:message key="errorIdProducte1"/>
								</div>
									
								<!-- Botons -->
								<div class="form-group">
									<div class="col-md-offset-2 col-md-8">
										<c:if test="${preu == null}">
											<button type="submit" class="btn btn-danger btn-sm"><fmt:message key="botoAlta"/></button>&nbsp;
										</c:if>
										<c:if test="${preu != null}">
											<button type="submit" class="btn btn-danger btn-sm"><fmt:message key="botoModifica"/></button>&nbsp;
										</c:if>
										<button type="reset" class="btn btn-default btn-sm"><fmt:message key="botoReset"/></button>
									</div>
								</div>
							</form>
							<hr/>
							<p><fmt:message key="abansLinkLlistatPreus"/> <a href="staff?accioPreu=llistat"><fmt:message key="linkLlistatPreus"/></a><p>
						
						</div>
						<div class="col-md-5 col-sm-6">
							<!-- Checkout sidebar items -->
							<div class="sidebar-item">
								<!-- Heading -->
								<h5 class="pull-left"><fmt:message key="lliuramentDomicili"/></h5>
								<!-- Sidebar content icon -->
								<i class="fa fa-truck br-green pull-right"></i>
								<div class="clearfix"></div>
								<!-- Sidebar Paragraph -->
								<p>Lorem ipsum dolor sit amet, conse ctetur adipi sicing elit, sed do eiusmod tempor incid idunt ut labore et conse ctetur adipi sicing elit, sed do eiusmod dolore magna aliqua.</p>
							</div>
							<!-- Checkout sidebar items -->
							<div class="sidebar-item">
								<!-- Heading -->
								<h5 class="pull-left"><fmt:message key="ajuda"/></h5>
								<!-- Sidebar content icon -->
								<i class="fa fa-headphones br-red pull-right"></i>
								<div class="clearfix"></div>
								<!-- Sidebar Paragraph -->
								<p>Lorem ipsum dolor sit amet, conse ctetur adipi sicing elit, sed do eiusmod tempor incid idunt ut labore et conse ctetur adipi sicing elit, sed do eiusmod dolore magna aliqua.</p>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
			
		<!-- Fi formulari -->
			
		<!-- jQuery -->
		<script src="js/jquery.js"></script>
		<script src="jseWok/validacioFormPreu.js"></script>
		<!-- Fi jQuery -->
		
	</body>	

	<c:import url="../imports/footerStaff.jsp"></c:import>
		
</html>