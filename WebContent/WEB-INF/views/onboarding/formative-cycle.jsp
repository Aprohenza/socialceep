<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Social Ceep</title>
	<link rel="icon" type="image/vnd.microsoft.icon" href="${pageContext.request.contextPath}/assets/img/ceep_logo_no_back.ico">
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
				

				<!-- <div class="d-flex justify-content-between align-items-center col p-0"> 					<div class="d-flex">
						<div class="form-group mr-2">
							<label class="small text-white" for="user-name">Correo electronico</label>
    						<input style="box-shadow: none;" type="text" class="rounded-0 form-control form-control-sm" id="user-name" name="user-name" placeholder="Correo electronico">
						</div>

						<div class="form-group ml-2">
							<label class="small text-white" for="user-password">Contraseña</label>
    						<input style="box-shadow: none;" type="password" class="rounded-0 form-control form-control-sm" id="user-password" name="user-password" placeholder="Contraseña">
						</div>
					</div>
					
					<div class="form-group">
						<label for="user-password" style="color: transparent;">&nbsp;&nbsp;&nbsp;</label>
    					<input type="submit" class="form-control form-control-sm rounded-0" value="Entrar" style="box-shadow: none;">
					</div>
				</div> -->
			</div>
			
		</div>
		
	</header>
	<main class="d-flex justify-content-around align-items-center" style="height: 100vh;">
		<div class="row p-0 m-0 d-flex justify-content-center">
			<!-- <section class="col p-0" id="branding"> 
				<img src="ceep_25_years.png" width="420">
			</section> -->

			<section class="col-8 px-4"> <!-- bg-success -->
			
			

				<div class="col-12 d-flex flex-wrap p-0" id="form"> <!-- bg-primary -->
					<div class="col-12 p-0 pb-2 m-0 d-flex flex-column text-center">
						<h1 style="font-weight: 200;">Hola ${name}. Bienvenido a Social Ceep</h1>
						<h4 style="font-weight: 200;">Selecciona tu ciclo formativo</h4>
						<!-- <input style="box-shadow: none; width: 48%; margin-right: 2%;" class="form-control " type="text" name="firstname" placeholder="Nombre" required="">
						<input style="box-shadow: none; width: 48%; margin-left: 2%;" class="form-control " type="text" name="lastname" placeholder="Apellidos"> -->
					</div>
				
					<div class="py-2 col-12 p-0">
					
					<select class="custom-select rounded-0" style="flex: 1;" id="cycleName">
							<option selected="" value="0">--- Selecciona tu ciclo formativo ---</option>
							<c:forEach items="${cycleList}" var="cycle">
							<option value="${cycle.key}">${cycle.value}</option>
							</c:forEach>
							 
						</select>	
						
					<%-- 	<form:select class="custom-select rounded-0" style="flex: 1;">
							 <form:option value="0" label="--- Selecciona tu ciclo formativo ---"/>
							  <form:options items="${cycleList}"/>
						</form:select> --%>
						
					</div>					

					<div class="py-2 col-12 p-0">
						<select class="custom-select rounded-0" style="flex: 1;" id="curse" disabled="disabled">
							<option selected="" value="0">Selecciona tu curso</option>
							
						</select>	
					</div>

					<div class="py-2 col-12 p-0" id="session-container">
						<select class="custom-select rounded-0" style="flex: 1;" id="session" disabled="disabled">
							<option selected="" value="0">Selecciona tu sesion</option>
							
						</select>	
					</div>

					<div class="py-2 col-12 p-0 d-none" id="finished-year-container">
						<select class="custom-select rounded-0" style="flex: 1;" id="finished-year">
							<option selected="" value="0">Año de graduacion</option>
							
						</select>	
					</div>

					<!-- <div class="py-2 col-12 p-0">
						<input style="box-shadow: none;" class="form-control" type="password" name="password" placeholder="Contaseña">
					</div> -->

					<div class="col-12 form-group d-flex justify-content-center">
	    				<input id="submit" disabled="disabled" type="button" class="col-6 form-control form-control-sm rounded-0 btn btn-primary" value="Continuar" style="box-shadow: none;">
					</div>
				</div>
			
			</section>
	</div>
	</main>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		
		$('#cycleName').change(function(){
			console.log($(this).val());
			if ($(this).val() != 0) {
				//hacer peticion solicitando curso del ciclo
				$.ajax({
					type: 'POST',
					data: {"cycleId": $(this).val()},
					url: '${pageContext.request.contextPath}/onboarding/cycle/curses',
					success: function(response) {
						$('#curse').html('');
						$('#curse').append('<option value="0">--- Selecciona tu curso ---</option>');
						console.log(response);
						console.log(Object.keys(response));
						$('#curse').attr('disabled', false);
						Object.keys(response).forEach(function(c){							
							$('#curse').append('<option value="'+c+'">'+response[c]+'</option>');
						})
					},
					error: function(jqXHR, textStatus, errorThrown) {
				       console.log(jqXHR.status, textStatus, errorThrown);
				   }
				})
				
			}else{
				checkSubmitAvailable();
				$('#curse').html('');
				$('#curse').append('<option value="0">--- Selecciona tu curso ---</option>');
				$('#curse').attr('disabled', 'disabled');
				
				$('#session').html('');
				$('#session').append('<option value="0">--- Selecciona tu session ---</option>');
				$('#session').attr('disabled', 'disabled');
				$('#session-container').removeClass('d-none');
				$('#finished-year-container').removeClass('d-block');
				$('#session-container').addClass('d-block');
				$('#finished-year-container').addClass('d-none');
			}
		})
		
		$('#curse').change(function(){
			if ($(this).val() != 0 && $(this).val() != 3){
				$.ajax({
					type: 'POST',
					data: {"curseId": $(this).val()},
					url: '${pageContext.request.contextPath}/onboarding/cycle/sessions',
					success: function(response) {
						$('#session').html('');
						$('#session').append('<option value="0">--- Selecciona tu sesion ---</option>');
						console.log(response);
						$('#session').attr('disabled', false);
						Object.keys(response).forEach(function(c){							
							$('#session').append('<option value="'+c+'">'+response[c]+'</option>');
						})
						
					
					},
					error: function(jqXHR, textStatus, errorThrown) {
				       console.log(jqXHR.status, textStatus, errorThrown);
				   }
				})
			}else{
				checkSubmitAvailable();
				$('#session').html('');
				$('#session').append('<option value="0">--- Selecciona tu sesion ---</option>');
				$('#session').attr('disabled', 'disabled');
			}
			
			 if ($(this).val() == 3) {
				$('#session-container').removeClass('d-block');
				$('#finished-year-container').removeClass('d-none');
				$('#session-container').addClass('d-none');
				$('#finished-year-container').addClass('d-block');
			}else{
				$('#session-container').removeClass('d-none');
				$('#finished-year-container').removeClass('d-block');
				$('#session-container').addClass('d-block');
				$('#finished-year-container').addClass('d-none');
			} 
		})
		
		$('#session').change(function(){
			checkSubmitAvailable();
		})
		
		$('#finished-year').change(function(){
			checkSubmitAvailable();
		})
		
		//formacion de anyos dde graduacion
		var year = new Date().getFullYear();
		for(i = year; i>=1994; i--){
			$('#finished-year').append('<option value="'+i+'">'+i+'</option>');
		}
		
		function checkSubmitAvailable(){
			console.log($('#cycleName').val());
			console.log($('#curse').val());
			console.log($('#session').val());
			 if ($('#cycleName').val() != 0 && $('#curse').val() != 0 && ($('#session').val() != 0 || $('#finished-year').val() != 0) ) {
				 $('#submit').attr('disabled', false);
			 }else{
				 $('#submit').attr('disabled', 'disabled');
			 }
		}
		
		 $('#submit').click(function(e){
			 e.preventDefault();
			 $.ajax({
					type: 'POST',
					data: {"cycle": $('#cycleName').val(), "cycleName": $('#cycleName option:selected').text(), "curse": $('#curse').val(), "session": $('#session').val(), "finished-year": $('#finished-year').val()},
					url: '${pageContext.request.contextPath}/onboarding/formative-cycle/save',
					success: function(response) {
					
					},
					error: function(jqXHR, textStatus, errorThrown) {
				       console.log(jqXHR.status, textStatus, errorThrown);
				   }
				})
			 window.location.href = '${pageContext.request.contextPath}/onboarding/your-photo-profile';
		 })
		
		
		
		
	})
</script>
</html>