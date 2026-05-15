<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../imports/headerStaff.jsp"></c:import>
<!-- Formulari -->
<div class="inner-page padd">
	<div class="checkout">
		<div class="container">
			<!-- Heading -->
			<h4>Càrrega de fitxer</h4>
			<div class="row">
				<div class="col-md-7 col-sm-6">
					<form class="form-horizontal" role="form" method="post"
						action="staff?accioFoto=upload" enctype="multipart/form-data">
						<div class="form-group">
							<label for="idProd" class="col-md-2 control-label">Producte:</label>
							<div class="col-md-8">
								<select class="form-control" id="idProd" name="idProd" <%-- onChange="onProductSelect();" --%>">
									<option value="-1">Triï el producte</option>
									<c:forEach items="${productes}" var="prod">
										<option value="${prod.id}"
											<c:if test="${prod.id == currId}"> selected="true"</c:if>
										>
											${prod.nom}
										</option>
									</c:forEach>
								</select>
								<!-- <input type="text" class="form-control" id="idProd"
									name="idProd" />-->
							</div>
						</div>
						<div class="form-group">
							<label for="fitxer" class="col-md-2 control-label">Fitxer:</label>
							<div class="col-md-8">
								<input type="file" class="form-control" id="fitxer"
									name="fitxer" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-2 col-md-8">
								<button type="submit" class="btn btn-danger btn-sm">Pujar
									foto</button>
							</div>
						</div>

					</form>
				</div>
				<div class="col-md-5 col-sm-6">
					<!-- Checkout sidebar items -->
					<div class="sidebar-item">
						<!-- Heading -->
						<h5 class="pull-left">Imatge actual</h5>
						<!-- Sidebar content icon -->
						<i class="fa fa-file-image-o br-green pull-right"></i>
						<div class="clearfix"></div>
						<!-- Sidebar Paragraph -->
						<p id="imatgeProd">Escull un producte</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Fi formulari -->

</body>

<c:import url="../imports/footerStaff.jsp"></c:import>
<script src = "jseWok/fotos.js"></script>
</html>