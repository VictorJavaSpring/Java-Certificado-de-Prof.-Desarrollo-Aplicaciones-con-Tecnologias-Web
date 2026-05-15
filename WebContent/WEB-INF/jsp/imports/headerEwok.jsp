<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" 
           uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.imports.headerEwok"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
			<!-- Description, Keywords and Author -->
			<meta name="description" content="Your description">
			<meta name="keywords" content="Your,Keywords">
			<meta name="author" content="ResponsiveWebInc">
			
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			
			<!-- Styles -->
			
			<!-- Bootstrap CSS -->
			<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
			<!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
			<link href="css/settings.css" type="text/css" rel="stylesheet"/>		
			<!-- FlexSlider Css -->
			<link rel="stylesheet"  type="text/css" href="../css/flexslider.css" media="screen"/>
			<!-- Portfolio CSS -->
			<link href="css/prettyPhoto.css" type="text/css" rel="stylesheet"/>
			<!-- Font awesome CSS -->
			<link href="css/font-awesome.min.css" type="text/css" rel="stylesheet"/>	
			<!-- Custom Less -->
			<link href="css/less-style.css" type="text/css" rel="stylesheet"/>	
			<!-- Custom CSS -->
			<link href="css/style.css" type="text/css" rel="stylesheet"/>
			<!-- eWok CSS -->
			<link href="css/estilsEwok.css" type="text/css" rel="stylesheet"/>
			<!--[if IE]><link rel="stylesheet" type="text/css" href="css/ie-style.css"><![endif]-->
			
			<!-- Favicon -->
			<link rel="shortcut icon" href="#">

		<title>eWok</title>
	</head>
	<body>	
	<!-- Shopping cart Modal -->
		<div class="modal fade" id="shoppingcart1" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Shopping Cart</h4>
					</div>
					<div class="modal-body">
						<!-- Items table -->
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Name</th>
									<th>Quantity</th>
									<th>Price</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><a href="#">Exception Reins Evocative</a></td>
									<td>2</td>
									<td>$200</td>
								</tr>
								<tr>
									<td><a href="#">Taut Mayoress Alias Appendicitis</a></td>
									<td>1</td>
									<td>$190</td>
								</tr>
								<tr>
									<td><a href="#">Sinter et Molests Perfectionist</a></td>
									<td>4</td>
									<td>$99</td>
								</tr>
								<tr>
									<th></th>
									<th>Total</th>
									<th>$489</th>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Continue Shopping</button>
						<button type="button" class="btn btn-info">Checkout</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		<!-- Model End -->
		
		<!-- Page Wrapper -->
		<div class="wrapper">
		<div class="header">
				<div class="container">
					<!-- Header top area content -->
					<div class="header-top">
						<div class="row">
							<div class="col-md-4 col-sm-4">
								<!-- Header top left content contact -->
								<div class="header-contact">
									<!-- Contact number -->
									<span><i class="fa fa-phone red"></i> 93 666 66 66</span>
								</div>
							</div>
							<div class="col-md-4 col-sm-4">
								<!-- Header top right content search box -->
								<div class=" header-search">
									<form class="form" role="form">
										<div class="input-group">
										
										  <input type="text" class="form-control" placeholder="<fmt:message key="emVeDeGust" />">
										  <span class="input-group-btn">
											<button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
										  </span>
										</div>
									</form>
								</div>
							</div>
							<div class="col-md-4 col-sm-4">
								<!-- Button Kart -->
								<div class="btn-cart-md">
									<a class="cart-link" href="#">
										<!-- Image -->
										<img class="img-responsive" src="img/cart.png" alt="" />
										<!-- Heading -->
										<h4><fmt:message key="comandaEnCurs"/></h4>
										<c:set value="${comandaActual.liniesComandaSize}" var="numProductes" scope="session"/>
										<span id="totalProductes"></span>
										<div class="clearfix"></div>
									</a>
									<ul class="cart-dropdown" role="menu" id="carrito">
										<li>
											<!-- Cart items for shopping list -->
											<div class='cart-item'>
												<a class='btn btn-danger' data-toggle='modal' href='#shoppingcart1'>Checkout</a>
											</div>
										</li>
									</ul>
									<div class="clearfix"></div>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 col-sm-5">
							<!-- Link -->
							<a href="/eWok/home">
								<!-- Logo area -->
								<div class="logo">
									<img class="img-responsive" src="img/logo.svg" alt=""/>
									<!-- Heading -->
									<h1>eWok</h1>
									<!-- Paragraph -->
									<p><fmt:message key="noImaginis"/></p>
								</div>
							</a>
						</div>
						<div class="col-md-8 col-sm-7">
							<!-- Navigation -->
							<nav class="navbar navbar-default navbar-right" role="navigation">
								<div class="container-fluid">
									<!-- Brand and toggle get grouped for better mobile display -->
									<div class="navbar-header">
										<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
											<span class="sr-only">Toggle navigation</span>
											<span class="icon-bar"></span>
											<span class="icon-bar"></span>
											<span class="icon-bar"></span>
										</button>
									</div>

									<!-- Collect the nav links, forms, and other content for toggling -->
									<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
										<ul class="nav navbar-nav">
											<li><a href="index.html"><img src="img/nav-menu/nav1.jpg" class="img-responsive" alt="" /> Home</a></li>
											<li class="dropdown hidden-xs">
												<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="img/nav-menu/nav2.jpg" class="img-responsive" alt="" /> Menu <b class="caret"></b></a>
												<ul class="dropdown-menu dropdown-md">
													<li>
														<div class="row">
															<div class="col-md-4 col-sm-6">
																<!-- Menu Item -->
																<div class="menu-item">
																	<!-- Heading -->
																	<h3>Vegetarians</h3>
																	<!-- Image -->
																	<img src="img/dish/dish1.jpg" class="img-responsive" alt="" />
																	<!-- Paragraph -->
																	<p>Pasta, arrós, vegetals... Boníssim i molt sa.</p>
																	<!-- Button -->
																	<a href="menu.html" class="btn btn-danger btn-xs">Veure'n més</a>
																</div>
															</div>
															<div class="col-md-4 col-sm-6">
																<!-- Menu Item -->
																<div class="menu-item">
																	<!-- Heading -->
																	<h3>Carnívors</h3>
																	<!-- Image -->
																	<img src="img/dish/dish2.jpg" class="img-responsive" alt="" />
																	<!-- Paragraph -->
																	<p>Tria la carn que vulguis per al teu wok.</p>
																	<!-- Button -->
																	<a href="menu.html" class="btn btn-danger btn-xs">Veure'n més</a>
																</div>
															</div>
															<div class="col-md-4">
																<!-- Menu Item -->
																<div class="menu-item">
																	<!-- Heading -->
																	<h3>Special Menu</h3>
																	<!-- Image -->
																	<img src="img/dish/dish3.jpg" class="img-responsive" alt="" />
																	<!-- Paragraph -->
																	<p>Sea nut perspicacity under omni piste natures mirror consequent.</p>
																	<!-- Button -->
																	<a href="menu.html" class="btn btn-danger btn-xs">Veure'n més</a>
																</div>
															</div>
														</div>
													</li>
												</ul>
											</li>
											<li class="dropdown visible-xs">
												<a href="#" class="dropdown-toggle" data-toggle="dropdown"> Menu <b class="caret"></b></a>
												<ul class="dropdown-menu">
													<li><a href="menu.html">Vegetarians</a></li>
													<li><a href="menu.html">Carnívors</a></li>
													<li><a href="menu.html">Special Menu</a></li>
												</ul>
											</li>
											<li><a href="gallery.html"><img src="img/nav-menu/nav3.jpg" class="img-responsive" alt="" /> Gallery</a></li>
											<li class="dropdown">
												<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="img/nav-menu/nav4.jpg" class="img-responsive" alt="" /> Shop <b class="caret"></b></a>
												<ul class="dropdown-menu">
													<li><a href="items.html">Shopping</a></li>
													<li><a href="item-single.html">Order Now</a></li>
													<li><a href="checkout.html">Checkout</a></li>
													<li><a href="reserve-seats.html">Reservation</a></li>
													<li><a href="contact.html">Contact Us</a></li>
												</ul>
											</li>
											<li class="dropdown">
												<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="img/nav-menu/nav5.jpg" class="img-responsive" alt="" /> Pages <b class="caret"></b></a>
												<ul class="dropdown-menu">
													<li><a href="error.html">404 Error</a></li>
													<li><a href="0-base.html">Blank Page</a></li>
													<li><a href="blog.html">Blog</a></li>
													<li><a href="blog-single.html">Blog Single</a></li>
													<li><a href="components.html">Components</a></li>
													<li><a href="general.html">General</a></li>
													<li><a href="nutrition-info.html">Nutrition Info</a></li>
													<li><a href="recipe.html">Recipes</a></li>
												</ul>
											</li>
											<li><a href="aboutus.html"><img src="img/nav-menu/nav6.jpg" class="img-responsive" alt="" /> About</a></li>
										</ul>
									</div><!-- /.navbar-collapse -->
								</div><!-- /.container-fluid -->
							</nav>
						</div>
					</div>
				</div> <!-- / .container -->
	<!-- alerts -->
	<c:if test="${errorMsg != null}">
		<div class="alert-ewok alert alert-danger" role="alert">
		
			<c:forEach items="${errorMsg}" var="msg">
				<p class="textError">${msg}</p>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${warnMsg != null}">
		<div class="alert-ewok alert alert-warning" role="alert">
			<c:forEach items="${warnMsg}" var="msg">	
				<p class="textError">${msg}</p>
			</c:forEach>		
		</div>
	</c:if>
	<c:if test="${infoMsg != null}">
		<div class="alert-ewok alert alert-success" role="alert">		
			<c:forEach items="${infoMsg}" var="msg">
				<p class="textError">${msg}</p>
			</c:forEach>		
		</div>
	</c:if>
	
	
			</div>
		</div>
	