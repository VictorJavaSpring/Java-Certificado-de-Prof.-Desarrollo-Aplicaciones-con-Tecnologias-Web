<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../imports/headerStaff.jsp"></c:import>
<!-- Internacionalitzacio -->
<fmt:setLocale value="${idioma}" />
<fmt:setBundle
	basename="com.soc.ewok.recursos.jsp.productes.modificaProducteCompost" />
<!-- FI Internacionalitzacio -->
<!-- contingut principal -->

<!-- capçalera de pàgina -->
<div class="container">
	<!-- Default Heading -->
	<div class="default-heading">
		<!-- Crown image -->
		<img class="img-responsive" src="img/crown.png" alt="" />
		<!-- Heading -->
		<div class="col-md-8 col-sm-6">
			<h2>
				<fmt:message key="titolh2" />
			</h2>
		</div>
		<div class="col-md-2 col-sm-6">
			<a href="staff" class="btn btn-default btn-sm"><fmt:message key="cancelar" /></a>
		</div>
		<div class="col-md-2 col-sm-6">
			<a class="btn btn-danger btn-sm" onclick="enviarDades();"><fmt:message key="gravar" /></a>
		</div>
	</div>
		
	<!-- Fi capçalera de pàgina -->

	<!-- Inner Content -->
	<div class="inner-page">
		<!-- bloc amb 2 arees per posar productes -->
		<div class="row">
			<!-- area de producte a modificar -->
			<div class="col-md-9 col-sm-6">
				<input type="hidden" id="prodcompostid" value="${prodcompost.id }" />
				<input type="hidden" id="prodcomposttipus" value="${prodcompost.idTipusProducte}" />
				<form method="post" id="formObjecte" action="staff?accioProductes=gravacompost&id=${prodcompost.id }" >
					<input type="hidden" name="inputObjectePC" id="inputObjectePC" />
					<input type="hidden" name="inputObjectePreu" id="inputObjectePreu" />
				</form>
				<div class="component-content compoproductes">
					<!-- contingut -->
					<div id="areaProdCompost">
						<div class="row">
							<!-- titol, descripcio, calculs -->
							<div class="col-md-6 col-sm-6" style='height:80px;'>
								<h3><span id="nomProdCompost"></span></h3>
								<h4 id="descripcioProdCompost"></h4>
							</div>
							<div id="dpreuFinal" class=" col-md-3 col-sm-3 ">
								<span id="labelpreuFinal" class="label label-info"><fmt:message key="preuFinal" /></span>
								<input type="number" id="preuFinal" class="form-control" onblur="actualitzaPreuFinal();">
								<br/>
								<span id="labelcostTot" class="label label-info"><fmt:message key="costTotal" /></span>
								<input type="number" readonly id="costTot" class="form-control">
								
							</div>
							<div id="dcostTot" class=" col-md-3 col-sm-3">
								<span id="labeliniVigenciaPF" class="label label-info"><fmt:message key="iniVigencia" /></span>
								<input  type="date" id="iniVigenciaPF" class="form-control" onblur="actualitzaDataIni();" placeholder="data inici vigència">
								<br/>
								<span id="labelfiVigenciaPF" class="label label-info"><fmt:message key="fiVigencia" /></span>
								<input type="date" id="fiVigenciaPF" class="form-control" onblur="actualitzaDataFinal();" placeholder="data fi vigència">
							</div>
						</div>
						<hr class="border" />
						<div class="row">
							<!-- headers -->
							<div class="col-md-8 col-sm-6">
								<span style='margin-left:20px;' class="label label-primary" ><fmt:message key="components" /></span>
							</div>
							<div class="col-md-2 col-sm-3">
								<span class="label label-info"><fmt:message key="quantitat" /></span>
							</div>
							<div class="col-md-2 col-sm-3 pull-center">
								<span class="label label-info"><fmt:message key="cost" /></span>
							</div>
						</div>
						<hr class="border" />
						<!-- area per mostrar components actuals -->
						<div id="componentsActuals" class="col-md-12 col-sm-6" ondragover="permetDrop(event);" ondrop="drop(event,this);" ondragenter="mostradropable(event);" ondragleave="surtdropable(event);"></div>
					</div>

				</div>
			</div>
			<!-- area de llistat de productes compatibles -->
			<div class="col-md-3 col-sm-6">
				<div class="component-content compoproductes">
					<h3><fmt:message key="compoDisponibles" /></h3>
					<div id="areaComponents" ondragover="permetDrop(event);" ondrop="drop(event,this);" ondragenter="mostradropable(event);" ondragleave="surtdropable(event);">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- / Inner Page Content End -->
<!-- Fi contingut principal -->

<c:import url="../imports/footerStaff.jsp"></c:import>
<script type="text/javascript" src="jseWok/divProducte.js"></script>
<script type="text/javascript" src="jseWok/drag_drop.js"></script>
<script type="text/javascript" src="jseWok/modificaProducteCompostAjax.js"></script>
</body>
</html>







