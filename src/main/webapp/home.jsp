<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>

<!-- CSS -->
<link href="common/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

<!-- JS -->
<script type="text/javascript" src="common/js/jquery.min.js"></script>
<script type="text/javascript" src="common/js/bootstrap.min.js"></script>

</head>
<body>

	<!-- Menu -->
	<jsp:include page="common/menu.jsp"></jsp:include>
	
	<div class="container">
		<div class="row mt-5">
			<div class="col-md-7">
				<!-- Item List -->
				<table class="table" id="dataTable" width="100%" cellspacing="0">
					<thead>
						<tr>
							<th>Product</th>
							<th>Price</th>
							<th>Category</th>
							<th colspan="2">Expire Date</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${itemlists}" var="i">
							<tr>
								<td>${i.name}</td>
								<td>${i.price}</td>
								<td>${i.category.name}</td>
								<td>${i.exp_date}</td>
								<td>
									<c:url value="/add-to-cart" var="addToCart">
										<c:param name="id" value="${i.id}"></c:param>]
									</c:url>
									<a href="${addToCart}" class="btn btn-outline-primary btn-sm">Add To Cart</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-md-5 ${empty cart ? 'd-none' : ''}">
				<!-- Sale Detail -->
				<div class="card">
					<div class="card-header">
						<h4>Sale Details</h4>
					</div>
					<div class="card-body">
						<table class="table" id="dataTable" width="100%" cellspacing="0">
							<thead>
								<tr>
									<th>Product</th>
									<th>Price</th>
									<th>Qty</th>
									<th>Total</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${cart.detailList}" var="sd">
									<tr>
										<td>${sd.item.name}</td>
										<td>${sd.item.price}</td>
										<td>${sd.subQty}</td>
										<td>${sd.item.price * sd.subQty}</td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="3" align="right">SubTotal :</td>
									<td>${cart.subTotal}</td>
								</tr>
								<tr>
									<td colspan="3" align="right">Tax :</td>
									<td>${cart.tax}</td>
								</tr>
								<tr>
									<td colspan="3" align="right">Total :</td>
									<td>${cart.total}</td>
								</tr>
							</tbody>
						</table>
						<c:url value="/cart-action" var="cartAction"></c:url>
						<form action="${cartAction}" method="post" class="form">
							<div class="form-row">
								<div class="col">
									<input type="submit" class="form-control btn btn-primary" value="Paid" name="btnAction">
								</div>
								<div class="col">
									<input type="submit" class="form-control btn btn-danger" value="Clear" name="btnAction">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>

</body>
</html>