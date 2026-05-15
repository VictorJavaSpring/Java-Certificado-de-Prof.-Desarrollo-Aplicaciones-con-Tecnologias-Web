<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.rol.Rol_form_staff"/>

<c:import url="../imports/headerStaff.jsp"></c:import>
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
					<!-- Border -->
					<div class="border"></div>
				</div>						
</div>						
			
		<!-- Formulari -->
				<div class="checkout">
					<div class="container">
					<!-- Heading -->
					<!-- titol h4 -->
					<c:if test="${rol == null }">
						<h1><c:set scope="request" var="titol" value="Alta persona"/></h1>
						<h2><fmt:message key="titolH4alta"/></h2>
					</c:if>
					<c:if test="${rol != null }">
					<h2><fmt:message key="titolH4modificacio"/>${rol.id}</h2>
					</c:if>						
					<!-- Fi titol h4 -->
					
					<div class="row">
							<div class="col-md-7 col-sm-6">
								<!-- Checkout Form -->
										<c:if test="${rol == null }">
											<form class="form-horizontal" action="staff?accioRol=alta" method="post">
										</c:if>
										<c:if test="${rol != null }">
											<form class="form-horizontal" action="staff?accioRol=modificacio" method="post">
										</c:if>
										
									<!-- --------------- Id --------------- -->
									<div class="form-group">
									<c:if test="${rol == null }">
										<div class="col-md-8">
											<input type="hidden" class="form-control" id="id" name="id" 
											placeholder="Id" value="${param.id}" readonly/>
										</div>
										</c:if>
										<c:if test="${rol != null }">
										<label for="id" class="col-md-2 control-label">Id</label>
											<div class="col-md-8">
												<input type="number" class="form-control" id="id" name="id" 
												placeholder="Id" value="${rol.id}" readonly/>
											</div>
										</c:if>
									</div> 
								
									<!-- --------------- Nom --------------- -->
									<div class="form-group">
										<label for="nom" class="col-md-2 control-label">
										<fmt:message key="Nom" /></label>
										
										<c:if test="${rol == null }">
										<div class="col-md-8">
											<input type="text" class="form-control" id="nom" name="nom" 
											placeholder="<fmt:message key="NomPH" />" value="${param.nom}"/>
										</div>
										</c:if>
										<c:if test="${rol != null }">
											<div class="col-md-8">
												<input type="text" class="form-control" id="nom" name="nom" 
												placeholder="<fmt:message key="NomPH" />" value="${rol.nom}"/>
											</div>
										</c:if>
									</div>
									<!-- --------------- ERROR Nom --------------- -->
									<c:if test="${errorNom != null}">
										<div class="alert-danger alert-form" role="alert">
										${errorNom}
										</div>
									</c:if>
									<c:if test="${errorNom != null}">
										<div class="alert-danger alert-form" role="alert">
										${errorNom}
										</div>
									</c:if>
									<!-- --------------- Codi Rol --------------- -->
									<div class="form-group">
										<label for="codiRol" class="col-md-2 control-label">
										<fmt:message key="codiRol" /></label>
										
										<c:if test="${rol == null }">
										<div class="col-md-8">
											<input type="text" class="form-control" id="nom" name="nom" 
											placeholder="<fmt:message key="NomPH" />" value="${param.codi}"/>
										</div>
										</c:if>
										<c:if test="${rol != null }">
											<div class="col-md-8">
												<input type="text" class="form-control" id="nom" name="nom" 
												placeholder="<fmt:message key="NomPH" />" value="${rol.codi}"/>
											</div>
										</c:if>
									</div>
									<!-- --------------- ERROR Codi Rol --------------- -->
									
							</div>
									<!-- submit i reset -->
									<div class="form-group">
										<div class="col-md-offset-2 col-md-8">
											<button type="submit" class="btn btn-danger btn-sm"><fmt:message key="confirmar" /></button>&nbsp;
											<button type="button" class="btn btn-default btn-sm" onClick="location.href='staff?accioProductes=llistat'"><fmt:message key="cancelar" /></button>
										</div>
									</div>
								</form>
							</div>
					</div>
				</div>
						<!-- Fi formulari -->
					<p><fmt:message key="Anar"/> <a href="staff?accioRol=prepararAlta"><fmt:message key="nou"/></a><p>
					<p><fmt:message key="Anar"/> <a href="staff?accioRol=llistat"><fmt:message key="llistat"/></a><p>
					<p><fmt:message key="Anar"/> <a href="staff?accioRol=veure"><fmt:message key="veure"/></a><p>
					<p><fmt:message key="Anar"/> <a href="staff"><fmt:message key="home"/></a><p>
					<p><fmt:message key="diu"/> ${msg}</p>
					<p>Escull l'idioma: <a href="staff?accioRol=llistat&idioma=ca">Català</a> | <a href="staff?accioRol=llistat&idioma=es">Castellà</a> | <a href="staff?accioRol=llistat&idioma=en">Anglès</a></p>
					
		
	</body>	

	<c:import url="../imports/footerStaff.jsp"></c:import>
		
</html>