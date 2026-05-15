<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.imports.formUsuariStaff"/>

<c:import url="../imports/headerStaff.jsp"></c:import>
			
			
		
		
<p>
			<c:if test="${usuari == null }">
				<h2><fmt:message key="titolH4alta"/></h2>
			</c:if>
			
			<c:if test="${usuari != null }">
				<h2><fmt:message key="titolH4modificacio"/>${usuari.mail}</h2>
			</c:if>
</p>
			<!-- Formulari -->	
			
			<div class="inner-page padd">	
				<div class="checkout">
					<div class="container">
					<!-- Heading -->
					<h1><fmt:message key="titolH1Form"/></h1>
						<div class="row">
							<div class="col-md-7 col-sm-6">
							
								<!-- Checkout Form -->
							<c:if test="${usuaris == null }">
							<form class="form-horizontal" action="staff?accioUsuari=alta" method="post">
							</c:if>
							
							<c:if test="${usuaris != null }">
								<form class="form-horizontal" action="staff?accioUsuari=modificar&email=${usuaris.mail}" method="post">
							</c:if>
							
					
									<!-- Email -->
									
									<div class="form-group">
									<label for="pw" class="col-md-2 control-label"><fmt:message key="emailUsuari" /></label>
									
									<c:if test="${usuaris == null }">
										<div class="col-md-8">
											<input type="text" class="form-control"  id="email" name="mail" 
											placeholder="<fmt:message key="emailUsuariPH" />" value="${param.mail}"/>
										</div>
									</c:if>
									
									<c:if test="${usuaris != null }">
										<div class="col-md-8">
											<input type="text" class="form-control" readonly="readonly" name="mail" id="email" 
											value="${usuaris.mail}"/>
										</div>
									</c:if>
									</div>
									
									<c:if test="${errorMsg != null}">
										<div class="alert-danger alert-form" role="alert">		
										<fmt:message key="msjErrorEmail" />
										</div>
									</c:if>		
									
										
									
									<!-- Password -->
									
									<div class="form-group">
									<label for="pw" class="col-md-2 control-label"><fmt:message key="pwUsuari" /></label>
									
									<c:if test="${usuaris == null }">
										<div class="col-md-8">
											<input type="text" class="form-control" id="pw" name="pw" 
											placeholder="<fmt:message key="pwUsuariPH" />" value="${param.pw}"/>
										</div>
									</c:if>
									
									<c:if test="${usuaris != null }">
										<div class="col-md-8">
											<input type="text" class="form-control" id="pw" name="pw" 
											placeholder="<fmt:message key="pwUsuariPH" />" value="${usuaris.password}"/>
										</div>
									</c:if>
									</div>
									
									<c:if test="${errorMsg != null}">
										<div class="alert-danger alert-form" role="alert">		
											<fmt:message key="msjErrorPw" />
										</div>
									</c:if>								            
									
									
									
									
									<div class="form-group">
										<div class="col-md-offset-2 col-md-8">
											<div class="checkbox">
												
												<input type="checkbox" value="usuari" id="usuari"/><label for="usuari">Usuari</label>		|
												<input type="checkbox" value="administardor" id="administrador"><label for="administrador">Administrador</label>		|
												<input type="checkbox" value="caixer" id="caixer"><label for="caixer">Caixer</label>		|
												<input type="checkbox" value="cuiner" id="cuiner"><label for="cuiner">Cuiner</label> 
											</div>
										</div>
									</div> 
									           
									<div class="form-group">
										<div class="col-md-offset-2 col-md-8">
											<button type="submit" class="btn btn-danger btn-sm"><fmt:message key="alta" /></button>&nbsp;
											<button type="reset" class="btn btn-default btn-sm">Reiniciar</button>
										</div>
									</div>
								</form>
							
							</div>
							
							<div class="col-md-5 col-sm-6">
								<!-- Checkout sidebar items -->
								<div class="sidebar-item">
									<!-- Heading -->
									<h5 class="pull-left">Lliurament a domicili</h5>
									<!-- Sidebar content icon -->
									<i class="fa fa-truck br-green pull-right"></i>
									<div class="clearfix"></div>
									<!-- Sidebar Paragraph -->
									<p>Lorem ipsum dolor sit amet, conse ctetur adipi sicing elit, sed do eiusmod tempor incid idunt ut labore et conse ctetur adipi sicing elit, sed do eiusmod dolore magna aliqua.</p>
								</div>
								<!-- Checkout sidebar items -->
								<div class="sidebar-item">
									<!-- Heading -->
									<h5 class="pull-left"><fmt:message key="ayuda"/></h5>
									<!-- Sidebar content icon -->
									<i class="fa fa-headphones br-red pull-right"></i>
									<div class="clearfix"></div>
									<!-- Sidebar Paragraph -->
									<p><fmt:message key="msjAyuda"/></p>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- Fi formulari -->
				
					<p><fmt:message key="Anar"/> <a href="staff?accioUsuari=llistat"><fmt:message key="llistat"/></a><p>
					<p><fmt:message key="Anar"/> <a href="staff"><fmt:message key="home"/></a><p>
					<p><fmt:message key="Anar"/> <a href="staff?accioClients=llistat"><fmt:message key="clients"/></a><p>
					<p><fmt:message key="Anar"/> <a href="staff?accioPreu=llistat"><fmt:message key="preu"/></a></a><p>
					<p><fmt:message key="Anar"/> <a href="staff?accioOfertaProducte=llistat"><fmt:message key="ofProducte"/></a>
					<p><fmt:message key="Anar"/> <a href="exemple"><fmt:message key="exemple"/></a><p>
					<p><fmt:message key="diu"/> ${msg}</p>
					
					<p>Escull l'idioma: <a href="staff?accioUsuari=prepararAlta&idioma=ca">Català<a/> | <a href="staff?accioUsuari=prepararAlta&idioma=es">Castellà<a/> | <a href="staff?accioUsuari=prepararAlta&idioma=en">Anglés<a/></p>
		
		
	
	
	</body>	

	<c:import url="../imports/footerStaff.jsp"></c:import>
		
</html>