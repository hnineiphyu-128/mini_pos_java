<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items</title>

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
		<div class="row">
			<div class="col">
				<h4 class="d-inline-block my-3">All Items</h4>
				<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					<thead>
						<tr>
							<th>#</th>
							<th>Name</th>
							<th>Price</th>
							<th>Category</th>
							<th>Expire Date</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${itemlists}" var="i">
							<tr>
								<td>${i.id}</td>
								<td>${i.name}</td>
								<td>${i.price}</td>
								<td>${i.category.name}</td>
								<td>${i.exp_date}</td>
								<td>
								<c:url value="/edit-item" var="edit">
									<c:param name="itemId" value="${i.id}"></c:param>
								</c:url>
									<a href="${edit}" class="btn btn-outline-warning btn-sm">Edit</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>