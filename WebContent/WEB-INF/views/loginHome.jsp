<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<title>Login | Social CEEP</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="icon" type="image/vnd.microsoft.icon" href="assets/img/ceep_logo_no_back.ico">
	<style type="text/css">
		*{
			margin: 0px;
			padding: 0px;
		}

	</style>
</head>
<body class="container p-0" style="background: linear-gradient(white, #D3D8E8); height: 100vh;">
	<header id="menubar" style="height: 82px; background-color: #202c5a; background-image:  linear-gradient(#202c5a, #021583 70%); position: absolute; top: 0; left: 0; width: 100%;" class="d-flex justify-content-around align-items-center">

		<div class="container px-4 d-flex justify-content-between align-items-center"> <!-- bg-warning -->

			<div class="row col-12 m-0 p-0">
				<div id="branding-header" class="col p-0 d-flex align-items-center">
					<h3 class="m-0" style="color: white; font-weight: 900; font-family: arial;">CEEP</h3>
				</div>
				

				<div class="d-flex justify-content-between align-items-center col p-0"> <!-- bg-danger -->
					<form:form method="post" action="login" modelAttribute="user" class="d-flex justify-content-between align-items-center col p-0">
						<div class="d-flex">
							<div class="form-group mr-2">
								<label class="small text-white" for="user-name">Correo electronico</label>
								<form:input path="emailUser" style="box-shadow: none;" type="text" class="rounded-0 form-control form-control-sm" id="user-name" name="user-name" placeholder="Correo electronico"/>
	    						
							</div>
	
							<div class="form-group ml-2">
								<label class="small text-white" for="user-password">Contraseña</label>
								<form:password path="passwordUser" style="box-shadow: none;" class="rounded-0 form-control form-control-sm" id="user-password" name="user-password" placeholder="Contraseña"/>
	    						
							</div>
						</div>
						
						<div class="form-group">
							<label for="user-password" style="color: transparent;">&nbsp;&nbsp;&nbsp;</label>
	    					<input type="submit" class="form-control form-control-sm rounded-0" value="Entrar" style="box-shadow: none;">
						</div>					
					</form:form>
				</div>
			</div>
			
		</div>
		
	</header>
	<main class="d-flex justify-content-around align-items-center" style="height: 100vh;">
		<div class="row p-0 m-0">
			<section class="col p-0" id="branding"> <!-- bg-danger -->
				<img src="${pageContext.request.contextPath}/assets/img/ceep_25_years.png" width="420">
			</section>

			<section class="col px-4"> <!-- bg-success -->
				<form:form modelAttribute="userRegistration" method="post" action="register">
					<div class="col-12 d-flex flex-wrap p-0" id="form"> <!-- bg-primary -->
						<div class="col-12 p-0 pb-2 m-0 d-flex">
							<form:input path="userNameRegistration" style="box-shadow: none; width: 48%; margin-right: 2%;" class="form-control" placeholder="Nombre" required=""/>
							<form:input path="userLastNameRegistration" style="box-shadow: none; width: 48%; margin-left: 2%;" class="form-control" placeholder="Apellidos"/>	
						</div>
					
						<div class="py-2 col-12 p-0">
							<form:input path="userEmailRegistration" style="box-shadow: none;" class="form-control" placeholder="Correo Electronico"/>
						</div>
	
						<div class="py-2 col-12 p-0">
							<form:password path="userPasswordRegistration" style="box-shadow: none;" class="form-control" placeholder="Contaseña nueva"/>
						</div>
					
					
						<div id="born-date" class="col-12 p-0 d-flex flex-wrap">
							<label class="col-12 p-0">Fecha de nacimiento</label>
							<div class="col-6 p-0 d-flex">
								<form:select path="userBornDayRegistration" items="${bornDay}" class="custom-select rounded-0" style="flex: 1;" id="day"/>
								<form:select path="userBornMonthRegistration" items="${bornMonth}" class="custom-select rounded-0" style="flex: 1;" id="month"/>
								<form:select path="userBornYearRegistration" items="${bornYear}" class="custom-select rounded-0" style="flex: 1;" id="year"/>
							</div>
						</div>
					
						<div class="col-12 p-0 d-flex" id="gender-selector">
							<div class="form-check form-check-inline">
								<form:radiobutton path="userGenderRegistration" class="form-check-input" value="FEMALE"/>
								<label class="form-check-label" for="userGenderRegistration1">Mujer</label>
							</div>
		
							<div class="form-check form-check-inline">
								<form:radiobutton path="userGenderRegistration" class="form-check-input" value="MALE"/>
								<label class="form-check-label" for="userGenderRegistration2">Hombre</label>
							</div>
						</div>
	
					
	
						<div class="form-group">
		    				<input type="submit" class="form-control form-control-sm rounded-0" value="Registrate" style="box-shadow: none;">
						</div>
					</div>
				</form:form>
		</section>
	</div>
	</main>
</body>

<script>
	sessionStorage.clear();
</script>
</html>