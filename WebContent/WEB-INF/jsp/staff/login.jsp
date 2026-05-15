<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${idioma}"/>
<fmt:setBundle basename="com.soc.ewok.recursos.jsp.login.login"/>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<title>eWok Staff</title>
	<script src = "jseWok/login.js"></script>
	</head>
	<body>
	<c:import url="imports/headerStaff.jsp"></c:import>
	<!-- Formulari -->
			<div class="inner-page padd">	
				<div class="checkout">
					<div class="container">
					<!-- Heading -->
					<h4>Login Staff</h4>
						<div class="row">
							<div class="col-md-7 col-sm-6">
								<!-- Checkout Form -->
								<form class="form-horizontal" role="form" action="staff?accio=login" method="post" onsubmit="return isLoginValid();">
									
									<%-- Camp usuari --%>
									<div class="form-group">
										<label for="inputName" class="col-md-2 control-label"><fmt:message key="user"/></label>
										<div class="col-md-8">
											<fmt:message key="userInfo" var="plcUsuario" scope="request"/>
											<c:choose>
												<c:when test="${param.usuari == null}">
													<input type="text" class="form-control" name="usuari" id="usuari" value="${cookie.lastUser.value}" placeholder="${plcUsuario}">
												</c:when>
												<c:otherwise>
													<input type="text" class="form-control" name="usuari" id="usuari" value="${param.usuari}" placeholder="${plcUsuario}">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									
									<%-- Div para el error user en javascript --%>
									<div 
										class="alert-danger alert alert-form" 
										role="alert"
										<c:if test="${!hiddenUser}">
											hidden="true"
										</c:if>
										id="errUsuariDiv">
										<span class="error" id="errUsuari"><fmt:message key="userErr" /></span>
									</div>
																
									<%-- Camp Contrasenya --%>
									<div class="form-group">
										<label for="password" class="col-md-2 control-label"><fmt:message key="pwd"/></label>
										<div class="col-md-8">
										<fmt:message key="userPwd" var="plcPassword" scope="request"/>
											<input type="password" class="form-control" name="pwd" id="pwd" placeholder = "${plcPassword}"/>
										</div>
									</div>
									
									<%-- Div para el error password en javascript --%>
									<div 
										class="alert-danger alert alert-form" 
										role="alert" 
										<c:if test="${!hiddenPwd}"> 
											hidden="true"
										</c:if>
										id="errPwdDiv">
										<p><span class="error" id="errPwd"><fmt:message key="pwdErr" /></span></p>	
									</div>
							
									<div class="form-group">
										<div class="col-md-offset-2 col-md-8">
											<button type="submit" class="btn btn-danger btn-sm"><fmt:message key="login"/></button>&nbsp;
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-offset-2 col-md-8">
											<p><fmt:message key="chooseLang"/><a href="staff?idioma=ca">Català</a> | <a href="staff?idioma=es">Castellà</a> | <a href="staff?idioma=it">Italià</a> | <a href="staff?idioma=en">Anglés</a></p>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Fi formulari -->
			<c:import url="imports/footerStaff.jsp"></c:import>
	</body>
</html>	