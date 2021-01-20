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

	<c:url value="home" var="home"></c:url>
	<c:redirect url="${home}"></c:redirect>
	
</body>
</html>