<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" 
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${idioma}"/>           
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.headerstaff.headerStaff"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
			<!-- Description, Keywords and Author -->
			<meta name="description" content="eWok Header">
			<meta name="keywords" content="Your,Keywords">
			<meta name="author" content="ResponsiveWebInc">
			
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			
			<!-- Styles -->
			<!-- Bootstrap CSS -->
			<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
			<!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
			<link href="css/settings.css" type="text/css" rel="stylesheet"/>		
			<!-- FlexSlider Css -->
			<link rel="stylesheet"  type="text/css" href="css/flexslider.css" media="screen"/>
			<!-- Portfolio CSS -->
			<link href="css/prettyPhoto.css" type="text/css" rel="stylesheet"/>
			<!-- Font awesome CSS -->
			<link href="css/font-awesome.min.css" type="text/css" rel="stylesheet"/>	
			<!-- Custom Less -->
			<link href="css/less-style.css" type="text/css" rel="stylesheet"/>	
			<!-- Custom CSS -->
			<link href="css/style.css" type="text/css" rel="stylesheet"/>
			<!--[if IE]><link rel="stylesheet" type="text/css" href="css/ie-style.css"><![endif]-->
			<!-- Estils eWok -->
			<link href="css/estilsEwok.css" type="text/css" rel="stylesheet"/>
			<!-- Estils Unitat (inici)-->
			<link href="css/cssUnitat/estilsUnitat.css" type="text/css" rel="stylesheet"/>
			<!-- Estils Unitat (fi)-->
			
			<!-- Favicon -->
			<link rel="shortcut icon" href="#">
		<title>eWok</title>
	</head>
	
	<body>	
			
		<!-- Page Wrapper -->
	<div class="wrapper">
		<div class="header">
				<div class="container">
					
							
	
					<div class="row">
						<div class="col-sm-3">
							<!-- Link -->
							<a href="/eWok/home">
								<!-- Logo area -->
								<div class="logo goUp">
									<img class="img-responsive" src="img/logo.svg" alt=""/>
									<!-- Heading -->
									<h1>eWok</h1>
									
								</div>
							</a>
						</div>
				
				<!-- si no hi ha usuari no mostrem el menu -->
				<c:if test="${usuActual != null}">
						<div class="col-sm-3  goDown">
							<div class="header-contact">
								<!-- Contact number -->
								<span><i class="fa fa-user red"></i> ${usuActual.mail} <a href="staff?accio=logout"><i class="fa fa-sign-out black"></i></a></span>
							</div>
						</div>
					
						<div class="col-sm-6 goDown">
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
											<li><a href="staff?"><fmt:message key = "inici"></fmt:message></a></li>
											<c:forEach items="${usuActual.rols}" var="rol">
												<c:if test="${rol.codi == 'c3cxr'}">
													<li><a href="staff?accioVistaCaixer=llistat"><fmt:message key = "caixer"></fmt:message></a></li>
												</c:if>
											</c:forEach>
											<c:forEach items="${usuActual.rols}" var="rol">
												<c:if test="${rol.codi == 'c2cnr'}">
													<li><a href="staff?accioVistaCuiner=veure"><fmt:message key = "cuiner"></fmt:message></a></li>
												</c:if>
											</c:forEach>
											
											<c:forEach items="${usuActual.rols}" var="rol">
												<c:if test="${rol.codi == 'c1admin'}">
													<li class="dropdown">
													<a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key = "administracio"></fmt:message><b class="caret"></b></a>
													<ul class="dropdown-menu">
													
													<c:forEach items="${items}" var="item">
														
															<li><a href="${item.url}"><fmt:message key="${item.key}"></fmt:message></a></li>
											
													</c:forEach>
													
													</ul>
													</li>
												</c:if>
											</c:forEach>
																																	
											</ul>
																		
									</div><!-- /.navbar-collapse -->
								</div><!-- /.container-fluid -->
							</nav>
						</div>
					</div>
				</div> <!-- / .container -->
			</c:if>
			
		
		
	<!-- alerts -->
	<br/>
	<div class="clearfix"></div>
	<c:if test="${errorMsg != null}">
		<div class="alert alert-ewok alert-danger" role="alert">
		
			<c:forEach items="${errorMsg}" var="msg">
				<p class="textError">${msg}</p>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${warnMsg != null}">
		<div class="alert alert-ewok alert-warning" role="alert">
			<c:forEach items="${warnMsg}" var="msg">	
				<p class="textError">${msg}</p>
			</c:forEach>		
		</div>
	</c:if>
	<c:if test="${infoMsg != null}">
		<div class="alert alert-ewok alert-success" role="alert">		
			<c:forEach items="${infoMsg}" var="msg">
				<p class="textError">${msg}</p>
			</c:forEach>		
		</div>
	</c:if>
	
	</div>
</div>
	
			