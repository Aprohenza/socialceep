<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Social Ceep</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<script src="http://code.jquery.com/jquery-3.4.1.js"></script>

	<style type="text/css">
		
		*{
			margin: 0px;
			padding: 0px;
		}

		body{
			background: linear-gradient(white, #D3D8E8);
			height: 100vh;
		}

	

	</style>
</head>
<body>

	<header id="menubar" style="height: 82px; /*background-color: #202c5a;*/ /*background-image:  linear-gradient(#202c5a, #021583 70%);*/ position: absolute; top: 0; left: 0; width: 100%;" class="d-flex justify-content-around align-items-center">

		<div class="container px-4 d-flex justify-content-between align-items-center"> <!-- bg-warning -->

			<div class="row col-12 m-0 p-0">
				<div id="branding-header" class="col p-0 d-flex align-items-center">
					<h3 class="m-0" style="color: #202c5a; font-weight: 900; font-family: arial;">CEEP</h3>
				</div>
			</div>			
		</div>
		
	</header>

	<section style="height: 100vh;" class="container d-flex justify-content-center align-items-center">
		<div class="d-flex justify-content-center align-items-center flex-column">
			<div class="d-flex justify-content-center align-items-center flex-column">
				<img src="${pageContext.request.contextPath}/assets/img/ceep_logo_no_back.png" width="120">
				<h3 style="font-weight: 400; font-family: arial;">Social Ceep</h3>
			</div>
			<div class="d-flex justify-content-center align-items-center flex-column">
				<h3 style="font-weight: 200;">Vaya!!! Tu perfil aun no ha sido aprobado.</h3>
				<h3 style="font-weight: 200;">Te enviaremos una notificacion cuando sea validado.</h3>
			</div>

			<div class="pt-4">
				<a href="${pageContext.request.contextPath}/" title="Ir a tu feed"><button style="outline: none; box-shadow: none;" class="btn btn-outline-primary rounded-0">Ir a Inicio</button></a>
			</div>
		</div>

		
	</section>

</body>
</html>>