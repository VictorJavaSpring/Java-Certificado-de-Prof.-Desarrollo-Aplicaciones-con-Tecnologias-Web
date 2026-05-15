<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="imports/headerStaff.jsp"></c:import>

<!-- Inner Content -->
<div class="inner-page padd">	
	<div class="checkout">
		<div class="container">
			<div class="row">
				<div class="col-md-7 col-sm-6">	
					<h1>Hola Staff</h1>
					<p>El controlador diu: ${msg}</p>
					<p>Anar a <a href="staff?accioUsuari=llistat">gestió d'usuari</a><p>
					<p>Anar a <a href="staff?accioProductes=llistat">Gestió Productes</a><p>
					<p>Anar a <a href="staff?accioClients=llistat">gestió clients</a><p>
					<p>Anar a <a href="staff?accioForPag=llistat">gestió tipus pagament</a><p>
					<p>Anar a <a href="staff?accioPreu=llistat">gestió preu</a><p>
					<p>Anar a <a href="staff?accioOfertaPunts=llistat">gestió oferta punts</a><p>
					<p>Anar a <a href="staff?accioOfertaProducte=llistat">gestió Oferta Producte</a><p>
					<p>Anar a <a href="staff?accioRol=llistat">gestió Rols</a><p>
					<p>Anar a <a href="staff?accioVistaCuiner=veure">Vista del cuiner.</a><p>
					<p>Anar a <a href="staff?accioVistaCaixer=llistat">Vista del Caixer.</a><p>
					<p>Anar a <a href="staff?accioFoto=formulari">Formulari de fotos.</a><p>
					<p>Anar <a href="exemple">exemple</a><p>
					<p><a href="staff?accio=logout">Logout</a></p>
					<p>Escull l'idioma: <a href="staff?idioma=ca">Català<a/> | <a href="staff?idioma=es">Castellà<a/> | <a href="staff?idioma=it">Italià<a/> | <a href="staff?idioma=en">Anglés<a/></p>

				</div>
			</div>
		</div>
	</div>
</div>


</body>
	<c:import url="imports/footerStaff.jsp"></c:import>
</html>







