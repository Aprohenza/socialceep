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

	<main class="d-flex flex-column justify-content-around align-items-center" style="height: 100vh;">

		<section>
			<h1 style="font-weight: 200;">Añade una foto para que los demas te reconozcan</h1>
		</section>

		<section>
			<div class="p-4" style="position: relative; border: 1px solid #cec8c8;">
					<div style="position: absolute; left: 0; top: -50%;" class="col-12 d-flex justify-content-center">
						<img id="photo-profile" src="${pageContext.request.contextPath}/assets/img/blank_default_profile.png" width="128" height="128" class="rounded-circle border border-border">
					</div>
					<div class="col-12 d-flex justify-content-center flex-column text-center mt-4">
						<h3 style="font-weight: 200;">${name} ${lastname}</h3>
						<h5 style="font-weight: 200;">${cycleName}</h5>
					</div>
				</div>

				<div class="d-flex justify-content-center my-2 bg-danger" >
	    			<button id="add-photo" class="col-12  rounded-0 btn btn-primary" style="box-shadow: none;">Añadir foto</button>
	    			<button id="continue" class="save-new-user col-12  rounded-0 btn btn-primary d-none" style="box-shadow: none;">Continuar</button>
	    			<input type="file" name="photo" class="d-none">
				</div>
				<div class="d-flex justify-content-center my-2" >
	    			<button class="save-new-user col-6  rounded-0 btn btn-outline-secondary" style="box-shadow: none;">Saltar</button>
				</div>

		</section>


			

	</main>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$('#add-photo').click(function(){
			$('input[name="photo"]').trigger('click');
		})

		$('input[name="photo"]').change(function(e){
				var reader = new FileReader();
				reader.readAsDataURL(e.target.files[0]);
				reader.onload = function() {
					$('#photo-profile').attr('src', reader.result);
					$('#add-photo').addClass('d-none');
					$('#continue').removeClass('d-none');
				}
			})
			
			$('.save-new-user').click(function(){
				formData = new FormData();
				
				if($('input[name="photo"]')[0].files[0] != null){
					formData.append("photoProfile", $('input[name="photo"]')[0].files[0]);
				}else{
					formData.append("photoProfile", null);
				}
			
				$.ajax({
					url : '${pageContext.request.contextPath}/onboarding/user/save',
					type : 'POST',
					processData : false,
					contentType : false,
					data : formData,
					success : function(response) {
						window.location.href = '${pageContext.request.contextPath}/onboarding/register-success';
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(jqXHR.status, textStatus, errorThrown);
					}
				});
			})
	})
</script>
</html>