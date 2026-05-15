<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>


	<c:import url="../imports/headerEwok.jsp"></c:import>
			
			
		<!-- Formulari -->
			<div class="inner-page padd">	
				<div class="checkout">
					<div class="container">
					<!-- Heading -->
					<h4>Títol del formulari</h4>
						<div class="row">
							<div class="col-md-7 col-sm-6">
								<!-- Checkout Form -->
								<form class="form-horizontal" role="form">
									<div class="form-group">
										<label for="inputName" class="col-md-2 control-label">Nom</label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="inputName" placeholder="Informi aquí del seu nom">
										</div>
									</div>
									<c:if test="${errorMsg != null}">
										<div class="alert-danger alert-form" role="alert">		
										Cal informar un nom amb format correcte
										</div>
									</c:if>									            
									<div class="form-group">
										<label for="inputEmail1" class="col-md-2 control-label">Email</label>
										<div class="col-md-8">
											<input type="email" class="form-control" id="inputEmail1" placeholder="Indiqui el seu correu electrónic">
										</div>
									</div>
									<c:if test="${errorMsg != null}">
										<div class="alert-danger alert-form" role="alert">		
										Cal un e-mail amb format correcte
										</div>
									</c:if>									
									<div class="form-group">
										<label for="inputPhone" class="col-md-2 control-label">Telèfon</label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="inputPhone" placeholder="El seu número de telèfon">
										</div>
									</div>
									<c:if test="${errorMsg != null}">
										<div class="alert-danger alert-form" role="alert">		
										El teléfon no és correcte
										</div>
									</c:if>
									<div class="form-group">
										<label for="inputCountry" class="col-md-2 control-label">Ciutat</label>
										<div class="col-md-8">
											<select class="form-control" id="inputCiutat">
												<option>Triï la seva ciutat</option>
												<option>Barcelona</option>
												<option>Badalona</option>
												<option>L'Hospitalet</option>
												<option>Sant Adrià</option>
											</select>
										</div>
									</div>
									<c:if test="${errorMsg != null}">
										<div class="alert-danger alert-form" role="alert">		
										Seleccioni una ciutat de la llista
										</div>
									</c:if>									              
									<div class="form-group">
										<label for="inputAddress" class="col-md-2 control-label">Adreça</label>
										<div class="col-md-8">
											<textarea class="form-control" id="inputAddress" rows="3" placeholder="Indiqui la seva adreça completa"></textarea>
										</div>
									</div>
									<c:if test="${errorMsg != null}">
										<div class="alert-danger alert-form" role="alert">		
										Indiqui la seva adreça
										</div>
									</c:if> 
									<div class="form-group">
										<label for="inputZip" class="col-md-2 control-label">Codi Postal</label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="inputZip" placeholder="00000">
										</div>																			
									</div>
									<c:if test="${errorMsg != null}">
										<div class="alert-danger alert-form" role="alert">		
										Introdueixi el seu Codi Postal amb format correcte
										</div>
									</c:if>									
									<div class="form-group">
										<div class="col-md-offset-2 col-md-8">
											<div class="">												
												<label for="usuari">Usuari</label><input type="checkbox" value="usuari" id="usuari"/>    | 
												<label for="administrador">Administrador</label><input type="checkbox" value="administardor" id="administrador"/>    |
												<label for="caixer">Caixer</label><input type="checkbox" value="caixer" id="caixer"/>    |
												<label for="cuiner">Cuiner</label><input type="checkbox" value="cuiner" id="cuiner"/> 
											</div>
										</div>
									</div> 
									<div class="form-group">
										<div class="col-md-offset-2 col-md-8">
											<div class="checkbox">
												<label>
													<input type="checkbox"> Accepto les condicions d'ús
												</label>
											</div>
										</div>
									</div>
									<c:if test="${errorMsg != null}">
										<div class="alert-danger alert-form" role="alert">		
										Cal acceptar les condicions d'ús
										</div>
									</c:if>		
									<div class="form-group">
										<div class="col-md-offset-2 col-md-8">
											
											<button type="reset" class="btn btn-default btn-sm">Reiniciar</button>
											<button type="submit" class="btn btn-danger btn-sm"><a href="checkout?accioComanda=checkout">Confirmar Oferta</a></button>
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
									<h5 class="pull-left">Necessites ajuda?</h5>
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
		
	</body>	

	<c:import url="../imports/footerEwok.jsp"></c:import>
</html>