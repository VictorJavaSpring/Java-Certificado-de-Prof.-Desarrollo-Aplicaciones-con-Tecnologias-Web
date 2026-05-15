<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>

	<c:import url="../imports/headerEwok.jsp"></c:import>
<!-- Internacionalitzacio -->
	<%--
<fmt:setLocale value="${idioma}" />
	--%>
<fmt:setLocale value="ca" />
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.viewMenuServlet.viewMenuServlet" />

<%-- Inici del header --%>            
	<c:import url="../imports/headerEwok.jsp"></c:import>        
<%-- Fi del header --%> 

	<%-- ****ViewMenuServlet (inici)**** --%> 
	<%-- El servlet diu --%>	
		<div class="parlaServlet">
			<h6><fmt:message key="hola">
					<fmt:param value="${Ok}"/>
				</fmt:message>
			</h6>
		</div>
		
		<!-- Inner Content -->
			<div class="inner-page padd">
			
				<!-- Single Item Start -->
				
				<div class="single-item">
					<div class="container">
						<!-- Shopping single item contents -->
						<div class="single-item-content">
							<div class="row">
								<div class="col-md-4 col-sm-5">
									<!-- Product image -->
									<img class="img-responsive img-thumbnail" src="img/shopping/shop-single.jpg" alt="" />
								</div>
								<div class="col-md-8 col-sm-7">
									<!-- Heading -->
									<h3>Plan Butter Cake</h3>
									<div class="row">
										<div class="col-md-7 col-sm-12">
											<!-- Single item details -->
											<div class="item-details">
												<!-- Paragraph -->
												<p class="text-justify">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
												<!-- Heading -->
												<h5>Ingredients :-</h5>
												<!-- Recipe ingredients -->
												<ul class="list-unstyled">
													<li>
														<i class="fa fa-check"></i> Cream <span class="pull-right"> 100g</span>
														<div class="clearfix"></div>
													</li>
													<li>
														<i class="fa fa-check"></i> Suger <span class="pull-right"> 250g</span>
														<div class="clearfix"></div>
													</li>
													<li>
														<i class="fa fa-check"></i> Nam libero et <span class="pull-right"> 1/2 cup</span>
														<div class="clearfix"></div>
													</li>
													<li>
														<i class="fa fa-check"></i> Lam hured ore <span class="pull-right"> 100ml</span>
														<div class="clearfix"></div>
													</li>
												</ul>
											</div>
										</div>
										<div class="col-md-5 col-sm-12"> 
											<!-- Form inside table wrapper -->
											<div class="table-responsive">
												<!-- Ordering form -->
												<form role="form">
													<!-- Table -->
													<table class="table table-bordered">
														<tr>
															<td>Price</td>
															<td>$49</td>
														</tr>
														<tr>
															<td>Quantity</td>
															<td><div class="form-group">
																<select class="form-control input-sm">
																	<option>1</option>
																	<option>2</option>
																	<option>3</option>
																	<option>4</option>
																</select>
															</div></td>
														</tr>
														<tr>
															<td>Payment Mode</td>
															<td><div class="form-group">
																<select class="form-control input-sm">
																	<option>Cash on delivery</option>
																	<option>Credit Card</option>
																	<option>Debit Card</option>
																</select>
															</div></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td><div class="form-group">
																<button type="submit" class="btn btn-danger btn-sm">Payment</button>
															</div></td>
														</tr>
													</table>
												</form> <%-- <form role="form"> (fi)  --%>
												<!--/ Table End-->
											
											</div> <%-- <div class="table-responsive"> (fi)  --%>
											<!--/ Table responsive class end -->
										
										</div> <%--<div class="row"> (fi) --%>
									</div> <%-- <div class="col-md-8 col-sm-7"> (fi) --%>
									<!--/ Inner row end  -->
								
								</div> <%-- <div class="row"> (fi) --%>
							</div> <%-- <div class="single-item-content"> (fi) --%>
						</div>  <%-- <div class="container"> (fi) --%>
					</div> <%-- <div class="single-item"> (fi) --%>
				</div> <%-- <div class="inner-page"> (fi) --%>
				<!-- Single Item End -->
			
				<!-- Showcase Start -->
				<div class="showcase">
					<div class="container">
						<div class="row">
							<div class="col-md-6 col-sm-6">
								<!-- Showcase section item -->
								<div class="showcase-item">
									<!-- Image -->
									<img class="img-responsive" src="img/fruit2.png" alt="" />
									<!-- Heading -->
									<h3><a href="#">Equine Porno Sumos</a></h3>
									<!-- Paragraph -->
									<p>Nam libero tempore, cum soluta nobis est minis voluptas assum simple and easy to distinguis quo.</p>
									<div class="clearfix"></div>
								</div>
							</div>
							<div class="col-md-6 col-sm-6">
								<!-- Showcase section item -->
								<div class="showcase-item">
									<!-- Image -->
									<img class="img-responsive" src="img/fruit3.png" alt="" />
									<!-- Heading -->
									<h3><a href="#">Equine Porno Sumos</a></h3>
									<!-- Paragraph -->
									<p>Nam libero tempore, cum soluta nobis est minis voluptas assum simple and easy to distinguis quo.</p>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- Showcase End -->
				
			</div><!-- / Inner Page Content End -->	
		
		
		
	<%-- ****ViewMenuServlet (fi)**** --%>

	<%-- Inici del footer --%>            
		<c:import url="../imports/footerEwok.jsp"></c:import>
	<%-- Fi del footer --%>  
</body>
</html>