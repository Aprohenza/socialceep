<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Registro | Social Ceep</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<script src="http://code.jquery.com/jquery-3.4.1.js"></script>

	<style type="text/css">
		*{
			margin: 0px;
			padding: 0px;
		}

	</style>
</head>
<body class="container p-0" style="background: linear-gradient(white, #D3D8E8); height: 100vh;">
	
	<main class="bg-danger d-flex flex-column justify-content-center">
		<div class="bg-primary">
			<h3>Gracias por registrarte en Social Ceep.</h3>
			<h4>Hemos enviado un mail de confirmacion a ${userRegistration.userEmailRegistration}</h4>
			<h4>Que estara disponible durante 24 horas.</h4>
		</div>
		
	</main>
	
</body>
<script type="text/javascript">
	
</script>
</html>