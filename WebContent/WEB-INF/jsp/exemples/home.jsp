<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${idioma}"/>           
<!-- FALTA INTERNACIONALITZAR <fmt:setBundle basename="com.soc.ewok.recursos.jsp.imports.footerEwok"/> -->

	<c:import url="../imports/headerEwok.jsp"></c:import>
			
		
		<!-- Dishes Start -->
				
				<div class="dishes padd">
					<div class="container">
						<!-- Default Heading -->
						<div class="default-heading">
							<!-- Crown image -->
							<img class="img-responsive" src="img/crown.png" alt="" />
							<!-- Heading -->
							<h2>Part pública</h2>
							<!-- Paragraph -->
							<p>Anar a la part <a href="staff">privada</a><p>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
							<!-- Border -->
							<div class="border"></div>
						</div>
						<div class="row">
							<div class="col-md-3 col-sm-6">	
								<div class="dishes-item-container">
									<!-- Image Frame -->
									<div class="img-frame">
										<!-- Image -->
										<img src="img/dish/dish5.jpg" class="img-responsive" alt="" />
										<!-- Block for on hover effect to image -->
										<div class="img-frame-hover">
											<!-- Hover Icon -->
											<a href="#"><i class="fa fa-cutlery"></i></a>
										</div>
									</div>
									<!-- Dish Details -->
									<div class="dish-details">
										<!-- Heading -->
										<h3>Delicious Chinese</h3>
										<!-- Paragraph -->
										<p>At vero eos et accusal gusto for ides residuum lores.</p>
										<!-- Button -->
										<a href="#" class="btn btn-danger btn-sm">Read more</a>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-6">
								<div class="dishes-item-container">
									<!-- Image Frame -->
									<div class="img-frame">
										<!-- Image -->
										<img src="img/dish/dish6.jpg" class="img-responsive" alt="" />
										<!-- Block for on hover effect to image -->
										<div class="img-frame-hover">
											<!-- Hover Icon -->
											<a href="#"><i class="fa fa-cutlery"></i></a>
										</div>
									</div>
									<!-- Dish Details -->
									<div class="dish-details">
										<!-- Heading -->
										<h3>Spicy Onion Rings</h3>
										<!-- Paragraph -->
										<p>At vero eos et accusal gusto for ides residuum lores.</p>
										<!-- Button -->
										<a href="#" class="btn btn-danger btn-sm">Read more</a>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-6">
								<div class="dishes-item-container">
									<!-- Image Frame -->
									<div class="img-frame">
										<!-- Image -->
										<img src="img/dish/dish7.jpg" class="img-responsive" alt="" />
										<!-- Block for on hover effect to image -->
										<div class="img-frame-hover">
											<!-- Hover Icon -->
											<a href="#"><i class="fa fa-cutlery"></i></a>
										</div>
									</div>
									<!-- Dish Details -->
									<div class="dish-details">
										<!-- Heading -->
										<h3>Cream Cheese Cup</h3>
										<!-- Paragraph -->
										<p>At vero eos et accusal gusto for ides residuum lores.</p>
										<!-- Button -->
										<a href="#" class="btn btn-danger btn-sm">Read more</a>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-6">
								<div class="dishes-item-container">
									<!-- Image Frame -->
									<div class="img-frame">
										<!-- Image -->
										<img src="img/dish/dish8.jpg" class="img-responsive" alt="" />
										<!-- Block for on hover effect to image -->
										<div class="img-frame-hover">
											<!-- Hover Icon -->
											<a href="#"><i class="fa fa-cutlery"></i></a>
										</div>
									</div>
									<!-- Dish Details -->
									<div class="dish-details">
										<!-- Heading -->
										<h3>Spicy Cheese Pizza</h3>
										<!-- Paragraph -->
										<p>At vero eos et accusal gusto for ides residuum lores.</p>
										<!-- Button -->
										<a href="#" class="btn btn-danger btn-sm">Read more</a>
									</div>
								</div>
							</div>
						</div>
					</div>					
				</div>
				
				<!-- Dishes End -->

<c:import url="../imports/footerEwok.jsp"></c:import>

</body>			
</html>			









