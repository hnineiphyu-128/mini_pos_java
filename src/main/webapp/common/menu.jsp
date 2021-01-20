<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="common/css/bootstrap.min.css" var="bootstrapCSS"></c:url>
<c:url value="common/js/jquery.min.js" var="jquery"></c:url>
<c:url value="common/js/bootstrap.bundle.js" var="bootstrapJS"></c:url>

<!-- CSS -->
<link href="${bootstrapCSS}" rel="stylesheet" type="text/css"/>

<!-- JS -->
<script type="text/javascript" src="${jquery}"></script>
<script type="text/javascript" src="${bootstrapJS}"></script>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
	<div class="container">
		<c:url value="/home" var="home"></c:url>
		<a class="navbar-brand" href="${home}">POS</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item">
					<c:url value="/items" var="items"></c:url>
					<a class="nav-link " href="${items}">Item</a>
				</li>
				<li class="nav-item">
					<c:url value="/add-item" var="itemAdd"></c:url>
					<a class="nav-link " href="${itemAdd}">Add Item</a>
				</li>
				<li class="nav-item">
					<c:url value="/sales" var="sale"></c:url>
					<a class="nav-link " href="${sale}">Sale</a>
				</li>
			</ul>
		</div>
	</div>
</nav>