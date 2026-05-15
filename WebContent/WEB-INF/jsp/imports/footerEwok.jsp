<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" 
           uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="es"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.imports.footerEwok"/>
	
	<!-- Javascript files -->
		<!-- jQuery -->
		<script src="js/jquery.js"></script>
		<!-- Bootstrap JS -->
		<script src="js/bootstrap.min.js"></script>
		<!-- SLIDER REVOLUTION 4.x SCRIPTS  -->
		<script type="text/javascript" src="js/jquery.themepunch.plugins.min.js"></script>
		<script type="text/javascript" src="js/jquery.themepunch.revolution.min.js"></script>
		<!-- FLEX SLIDER SCRIPTS  -->
		<script defer src="js/jquery.flexslider-min.js"></script>
		<!-- Pretty Photo JS -->
		<script src="js/jquery.prettyPhoto.js"></script>
		<!-- Respond JS for IE8 -->
		<script src="js/respond.min.js"></script>
		<!-- HTML5 Support for IE -->
		<script src="js/html5shiv.js"></script>
		<!-- Custom JS -->
		<script src="js/custom.js"></script>
		
	<!--  -->
		<!-- JS code for this page -->
		<script>
		/* ******************************************** */
		/*  JS for SLIDER REVOLUTION  */
		/* ******************************************** */
				jQuery(document).ready(function() {
					   jQuery('.tp-banner').revolution(
						{
							delay:9000,
							startheight:500,
							
							hideThumbs:10,
							
							navigationType:"bullet",	
														
							hideArrowsOnMobile:"on",
							
							touchenabled:"on",
							onHoverStop:"on",
							
							navOffsetHorizontal:0,
							navOffsetVertical:20,
							
							stopAtSlide:-1,
							stopAfterLoops:-1,

							shadow:0,
							
							fullWidth:"on",
							fullScreen:"off"
						});
				});
		/* ******************************************** */
		/*  JS for FlexSlider  */
		/* ******************************************** */
		
			$(window).load(function(){
				$('.flexslider-recent').flexslider({
					animation:		"fade",
					animationSpeed:	1000,
					controlNav:		true,
					directionNav:	false
				});
				$('.flexslider-testimonial').flexslider({
					animation: 		"fade",
					slideshowSpeed:	5000,
					animationSpeed:	1000,
					controlNav:		true,
					directionNav:	false
				});
			});
		
		/* Gallery */

		jQuery(".gallery-img-link").prettyPhoto({
		   overlay_gallery: false, social_tools: false
		});
		
		</script>
		<!-- Script per tancar els alerts -->
		<script src = "jseWok/tancarAlerts.js"></script>
		
		<!-- Script per esborrar un producte del shopping cart -->
		<script src="jseWok/esborraProducteCarrito.js"></script>
		
		<!-- Script per afegir o modificar la quantitat d'un producte del shopping cart -->
		<script src="jseWok/afegirModificarProdCarrito.js" type="text/javascript"></script>
		
	<!-- Footer Start -->
			
			<div class="footer padd">
				<div class="container">
					<div class="row">
						<div class="col-md-3 col-sm-6">
							<!-- Footer widget -->
							<div class="footer-widget">
								<!-- Logo area -->
								<div class="logo">
									<img class="img-responsive" src="img/logo.svg" alt="" />
									<!-- Heading -->
									<h1>eWok</h1>
								</div>
								<!-- Paragraph -->
								<p><fmt:message key="millorsWoks"/></p>
								<hr />
								<!-- Heading -->
								<h6><fmt:message key="pagamentsOnline"/></h6>
								<!-- Images -->
								<a href="#"><img class="payment img-responsive" src="img/payment/2co.gif" alt="" /></a>
								<a href="#"><img class="payment img-responsive" src="img/payment/authorizenet.gif" alt="" /></a>
								<a href="#"><img class="payment img-responsive" src="img/payment/discover.gif" alt="" /></a>
								<a href="#"><img class="payment img-responsive" src="img/payment/egold.gif" alt="" /></a>
								<a href="#"><img class="payment img-responsive" src="img/payment/mastercard.gif" alt="" /></a>
								<a href="#"><img class="payment img-responsive" src="img/payment/paypal.gif" alt="" /></a>
								<a href="#"><img class="payment img-responsive" src="img/payment/visa.gif" alt="" /></a>
								<a href="#"><img class="payment img-responsive" src="img/payment/worldpay.gif" alt="" /></a>
							</div> <!--/ Footer widget end -->
						</div>
						<div class="col-md-3 col-sm-6">
							<!-- Footer widget -->
							<div class="footer-widget">
								<!-- Heading -->
								<h4><fmt:message key="mesDemanats"/></h4>
								<!-- Images -->
								<a href="#"><img class="dish img-responsive" src="img/dish/dish1.jpg" alt="" /></a>
								<a href="#"><img class="dish img-responsive" src="img/dish/dish1.jpg" alt="" /></a>
								<a href="#"><img class="dish img-responsive" src="img/dish/dish1.jpg" alt="" /></a>
								<a href="#"><img class="dish img-responsive" src="img/dish/dish1.jpg" alt="" /></a>
								<a href="#"><img class="dish img-responsive" src="img/dish/dish1.jpg" alt="" /></a>
								<a href="#"><img class="dish img-responsive" src="img/dish/dish1.jpg" alt="" /></a>
								<a href="#"><img class="dish img-responsive" src="img/dish/dish1.jpg" alt="" /></a>
								<a href="#"><img class="dish img-responsive" src="img/dish/dish1.jpg" alt="" /></a>
								<a href="#"><img class="dish img-responsive" src="img/dish/dish1.jpg" alt="" /></a>
							</div> <!--/ Footer widget end -->
						</div>
						<div class="clearfix visible-sm"></div>
						<div class="col-md-3 col-sm-6">
							<!-- Footer widget -->
							<div class="footer-widget">
								<!-- Heading -->
								<h4><fmt:message key="mesInformacio"/></h4>
								<!-- Paragraph -->
								<p><fmt:message key="subscriute"/></p>
								<!-- Subscribe form -->
								<form role="form">
									<div class="form-group">
										<input class="form-control" type="text" placeholder="<fmt:message key="elTeuNom" />" />
									</div>
									<div class="form-group">
										<input class="form-control" type="email" placeholder="<fmt:message key="elTeuEmail" />" />
									</div>
									<button class="btn btn-danger" type="button"><fmt:message key="subscriume"/></button>
								</form>
							</div> <!--/ Footer widget end -->
						</div>
						<div class="col-md-3 col-sm-6">
							<!-- Footer widget -->
							<div class="footer-widget">
								<!-- Heading -->
								<h4><fmt:message key="contacte"/></h4>
								<div class="contact-details">
									<!-- Address / Icon -->
									<i class="fa fa-map-marker br-red"></i> <span>Carrer dels Almogàvers 123<br />08018, Barcelona</span>
									<div class="clearfix"></div>
									<!-- Contact Number / Icon -->
									<i class="fa fa-phone br-green"></i> <span>+93 666 66 66</span>
									<div class="clearfix"></div>
									<!-- Email / Icon -->
									<i class="fa fa-envelope-o br-lblue"></i> <span><a href="#">ewok@email.com</a></span>
									<div class="clearfix"></div>
								</div>
								<!-- Social media icon -->
								<div class="social">
									<a href="#" class="facebook"><i class="fa fa-facebook"></i></a>
									<a href="#" class="google-plus"><i class="fa fa-google-plus"></i></a>
									<a href="#" class="twitter"><i class="fa fa-twitter"></i></a>
									<a href="#" class="pinterest"><i class="fa fa-pinterest"></i></a>
								</div>
							</div> <!--/ Footer widget end -->
						</div>
					</div>
					<!-- Copyright -->
					<div class="footer-copyright">
						<!-- Paragraph -->
						<p>&copy; Copyright 2015 <a href="#">eWok Inc.</a></p>
					</div>
				</div>
			</div>
			
			<!-- Footer End -->
