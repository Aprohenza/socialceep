<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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

	</style>
</head>
<body class="container p-0" style="background: linear-gradient(white, #D3D8E8); height: 100vh;">
	<header id="menubar" style="height: 82px; /*background-color: #202c5a;*/ /*background-image:  linear-gradient(#202c5a, #021583 70%);*/ position: absolute; top: 0; left: 0; width: 100%;" class="d-flex justify-content-around align-items-center">

		<div class="container px-4 d-flex justify-content-between align-items-center"> <!-- bg-warning -->

			<div class="row col-12 m-0 p-0">
				<div id="branding-header" class="col p-0 d-flex align-items-center">
					<h3 class="m-0" style="color: #202c5a; font-weight: 900; font-family: arial;">CEEP</h3>
				</div>
				

			</div>
			
		</div>
		
	</header>

	<main class="d-flex flex-column justify-content-around align-items-center" style="height: 100vh;">

		<section>
			<h1 style="font-weight: 200;">Perfecto <span>${name}</span>!!!</h1>
			<h2 style="font-weight: 200;">Ahora necesitas que un Administrador de Social Ceep valide tu perfil.</h2>
			<h2 style="font-weight: 200;">Una vez que se compruebe que perteneces a Centro Europeo de Estudios Profesionales recibiras una notificacion en tu correo electronico para que puedas acceder.</h2>
			<div class="float-right">
				<small>The Social Ceep Team</small>
			</div>
		</section>

		


			

	</main>
</body>
</html>