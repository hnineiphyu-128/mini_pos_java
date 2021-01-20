<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${item.id != 0 ? 'Edit Item' : 'Add Item'}</title>

<!-- CSS -->
<link href="common/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

<!-- JS -->
<script type="text/javascript" src="common/js/jquery.min.js"></script>
<script type="text/javascript" src="common/js/bootstrap.min.js"></script>

</head>
<body>

	<jsp:include page="common/menu.jsp"></jsp:include>
	
	<div class="container">
		<div class="row">
			<div class="col-md-6">
			<h3>${item.id != 0 ? 'Edit Existing Item' : 'Add New Item'}</h3>
			<c:url value="/item-save" var="add"></c:url>
			<form action="${add}" method="post">
				<input type="hidden" name="itemId" value="${item.id}" >
				<div class="mb-3">
					<label for="name" class="form-label">Item Name</label> 
					<input type="text" class="form-control" id="name" name="name" value="${item.name}" required="required">
				</div>
				<div class="mb-3">
					<label for="price" class="form-label">Price</label>
					<input type="number" class="form-control" id="price" name="price" value="${item.price}" required="required">
				</div>
				<div class="mb-3">
					<label for="level" class="form-label">Category</label>
					<select class="form-control" id="level" name="category" required="required">
						<c:forEach items="${categories}" var="c">
							<option value="${c.id}"  value="${item.name}" ${item.category.id == c.id ? 'selected' : '' }>${c.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="mb-3">
					<label for="exp_date" class="form-label">Expire Date</label>
					<input type="date" class="form-control" id="exp_date" name="exp_date" value="${item.exp_date}" required="required">
				</div>
				<button type="submit" class="btn btn-primary">${item.id != 0 ? 'Update' : 'Save'}</button>
				<button type="reset" class="btn btn-danger">Clear</button>
			</form>
			</div>
		</div>
	</div>

</body>
</html>