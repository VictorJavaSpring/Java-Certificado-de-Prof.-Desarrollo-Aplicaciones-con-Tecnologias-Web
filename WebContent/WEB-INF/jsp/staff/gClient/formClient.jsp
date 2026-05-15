<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../imports/headerStaff.jsp"></c:import>

<!-- Internacionalitzacio -->
<fmt:setLocale value="${idioma}" />
<fmt:setBundle
	basename="com.soc.ewok.recursos.jsp.clients.formClient" />
<!-- FI Internacionalitzacio -->

<!-- Inner Content -->
<div class="inner-page padd">
	<div class="checkout">
			<div class="container">
				<!-- Default Heading -->
				<div class="default-heading">
					<h1><fmt:message key="gestiodeH4"/></h1>
					<!-- Crown image -->
					<img class="img-responsive" src="img/crown.png" alt="" />
					<!-- Heading -->
					<h2><fmt:message key="titolllistat"/></h2>
					<!-- Paragraph -->
					<p><fmt:message key="anar"/>  <a href="staff"><fmt:message key="privada"/></a><p>
					<p><fmt:message key="textIntro"/></p>
					<!-- Border -->
					<div class="border"></div>
				</div>						
			</div>
			<!-- Heading -->

		<!-- Formulari alta client -->
					<div class="checkout">
					<div class="container">
					<!-- Heading -->
					<!-- titol h4 -->
					<c:if test="${client == null }">
						<h1><c:set scope="request" var="titol" value="Alta persona"/></h1>
						<h2><fmt:message key="titolH4alta"/></h2>
					</c:if>
					<c:if test="${client != null }">
					<h2><fmt:message key="titolH4modificacio"/>${client.id}</h2>
					</c:if>						
					<!-- Fi titol h4 -->
					
					<div class="row">
							<div class="col-md-7 col-sm-6">
								<!-- Checkout Form -->
										<c:if test="${client == null }">
										<form class="form-horizontal" action="staff?accioClients=alta" method="post">
										</c:if>
										<c:if test="${client != null }">
											<form class="form-horizontal" action="staff?accioClients=modificacio" method="post">
										</c:if>
										
									<!-- --------------- Id --------------- -->
									<div class="form-group">
									<c:if test="${client == null }">
									<label for="id" class="col-md-2 control-label">Id</label>
										<div class="col-md-8">
											<input type="hidden" class="form-control" id="id" name="id" 
											placeholder="Id" value="${clientalta.id}" readonly/>
										</div>
										</c:if>
										<c:if test="${client != null }">
										<label for="id" class="col-md-2 control-label">Id</label>
											<div class="col-md-8">
												<input type="text" class="form-control" id="id" name="id" 
												placeholder="Id" value="${client.id}" readonly/>
											</div>
										</c:if>
									</div> 
								
									<!-- --------------- Nom --------------- -->
									<div class="form-group">
										<label for="nom" class="col-md-2 control-label">
										<fmt:message key="nom" /></label>
										
										<c:if test="${client == null }">
										<div class="col-md-8">
											<input type="text" class="form-control" id="nom" name="nom" 
											placeholder="<fmt:message key="nom" />" value="${clientalta.nom}"/>
										</div>
										</c:if>
										<c:if test="${client != null }">
											<div class="col-md-8">
												<input type="text" class="form-control" id="nom" name="nom" 
												placeholder="<fmt:message key="nom" />" value="${client.nom}"/>
											</div>
										</c:if>
									</div>
									<!-- --------------- ERROR Nom --------------- -->
									<c:if test="${errorNom != null}">
										<div class="alert-danger alert-form" role="alert">
										${errorNom}
										</div>
									</c:if>
									<!-- --------------- Cognoms --------------- -->
									<div class="form-group">
										<label for="Cognom" class="col-md-2 control-label">
										<fmt:message key="cognom" /></label>
										
										<c:if test="${client == null }">
										<div class="col-md-8">
											<input type="text" class="form-control" id="cognom" name="cognom" 
											placeholder="<fmt:message key="cognom" />" value="${clientalta.cognoms}"/>
										</div>
										</c:if>
										<c:if test="${client != null }">
											<div class="col-md-8">
												<input type="text" class="form-control" id="cognom" name="cognom" 
												placeholder="<fmt:message key="cognom" />" value="${client.cognoms}"/>
											</div>
										</c:if>
									</div>
									<!-- --------------- Telefon --------------- -->
									<div class="form-group">
										<label for="telefon" class="col-md-2 control-label">
										<fmt:message key="telefon" /></label>
										
										<c:if test="${client == null }">
										<div class="col-md-8">
											<input type="text" class="form-control" id="telefon" name="telefon" 
											placeholder="<fmt:message key="telefon" />" value="${clientalta.telefon}"/>
										</div>
										</c:if>
										<c:if test="${client != null }">
											<div class="col-md-8">
												<input type="text" class="form-control" id="cognom" name="cognom" 
												placeholder="<fmt:message key="telefon" />" value="${client.telefon}"/>
											</div>
										</c:if>
									</div>
									<!-- --------------- Email --------------- -->
									<div class="form-group">
										<label for="email" class="col-md-2 control-label">
										<fmt:message key="email" /></label>
										
										<c:if test="${client == null }">
										<div class="col-md-8">
											<input type="text" class="form-control" id="email" name="email" 
											placeholder="<fmt:message key="email" />" value="${clientalta.email}"/>
										</div>
										</c:if>
										<c:if test="${client != null }">
											<div class="col-md-8">
												<input type="text" class="form-control" id="email" name="email" 
												placeholder="<fmt:message key="email" />" value="${client.email}"/>
											</div>
										</c:if>
									</div>
									<!-- --------------- Dni --------------- -->
									<div class="form-group">
										<label for="dni" class="col-md-2 control-label">
										<fmt:message key="dni" /></label>
										
										<c:if test="${client == null }">
										<div class="col-md-8">
											<input type="text" class="form-control" id="dni" name="dni" 
											placeholder="<fmt:message key="dni" />" value="${clientalta.dni}"/>
										</div>
										</c:if>
										<c:if test="${client != null }">
											<div class="col-md-8">
												<input type="text" class="form-control" id="dni" name="dni" 
												placeholder="<fmt:message key="dni" />" value="${client.dni}"/>
											</div>
										</c:if>
									</div>
									<!-- --------------- Data Alta --------------- -->
									<div class="form-group">
										<label for="dataAlta" class="col-md-2 control-label">
										<fmt:message key="datalta" /></label>
										
										<c:if test="${client == null }">
										<div class="col-md-8">
											<input type="date" class="form-control" id="dataAlta" name="dataAlta" 
											placeholder="<fmt:message key="datalta" />" value="${clientalta.dataAlta}"/>
										</div>
										</c:if>
										<c:if test="${client != null }">
											<div class="col-md-8">
												<input type="date" class="form-control" id="dataAlta" name="dataAlta" 
												placeholder="<fmt:message key="datalta" />" value="${client.dataAlta}"/>
											</div>
										</c:if>
									</div>
									<!-- --------------- id Usuari --------------- -->
									<div class="form-group">
										<label for="idUsuari" class="col-md-2 control-label">
										<fmt:message key="idusuari" /></label>
										
										<c:if test="${client == null }">
										<div class="col-md-8">
											<input type="text" class="form-control" id="idUsuari" name="idUsuari" 
											placeholder="<fmt:message key="idusuari" />" value="${clientalta.idUsuari}"/>
										</div>
										</c:if>
										<c:if test="${client != null }">
											<div class="col-md-8">
												<input type="text" class="form-control" id="idUsuari" name="idUsuari" 
												placeholder="<fmt:message key="idusuari" />" value="${client.idUsuari}"/>
											</div>
										</c:if>
									</div>
									
							</div>
									<!-- submit i reset -->
									<div class="form-group">
										<div class="col-md-offset-2 col-md-8">
											<button type="submit" class="btn btn-danger btn-sm"><fmt:message key="confirmar" /></button>&nbsp;
											<button type="button" class="btn btn-default btn-sm" onClick="location.href='staff?accioClients=llistat'"><fmt:message key="cancelar" /></button>
										</div>
									</div>
								</form>
							</div>
					</div>
				</div>
						<!-- Fi formulari -->

	</body>	

	<c:import url="../imports/footerStaff.jsp"></c:import>
		
</html>