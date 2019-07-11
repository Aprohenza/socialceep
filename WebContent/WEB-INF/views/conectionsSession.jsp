<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
<script src="http://code.jquery.com/jquery-3.4.1.js"></script>



<head>
<style type="text/css">
	body{
			background-color: #f5f5f5;
		}
	.fw-600{
		font-weight: 600;
	}
	.lh-8{
		line-height: 0.8;
	}
	.btn-ceep{
		border: 0;
		outline: 1px solid #202c5a;
		color: #202c5a;
		font-weight: 600; 
		background: white;
		cursor: pointer;
	}

	.btn-ceep:hover{
		border: 0;
		outline: 2px solid #202c5a;
		background: #fce924;
		color: #202c5a;
		font-weight: 600;
		border-style: inset;
	}
	a:hover{
		text-decoration: none;
	}
</style>
</head>

<body style="background-color: #f5f5f5; position: relative;">
<jsp:include page="../views/tiles/navbar.jsp" />

<div class="container p-0 py-4  d-flex justify-content-between align-items-start " style="margin-top: 51px;">
	<section class="col-8 p-0">
		
		<div class="bg-white border">
			<div class=" p-2"> Contactos</div>
			<div>
			<c:forEach var="friend" items="${friendSession}">
				<div class="contact d-flex align-items-center justify-content-between border-bottom  p-2">
					<a href="${pageContext.request.contextPath}/profile/${friend.userProfileId}/">
						<div class="d-flex align-items-start">
							<div>
								<div><img src="${pageContext.request.contextPath}/images/${friend.userProfilePhotoProfile}" class="rounded-circle" width="72"></div>
							</div>
							<div class="px-2">
								<div class="d-flex flex-column">
									<span class="fw-600 lh-8">${friend.userProfileName} ${friend.userProfileLastName}</span><span class="text-muted">${friend.userProfileRole}</span>	
								</div>
								<div>
									<span>Desarrollo de aplicaciones multiplataformas</span>
								</div>
							</div>
						</div>
					</a>
					<div class="">
						<button class="btn btn-primary btn-sm rounded-0">Enviar mensaje</button>
					</div>
				</div>
			</c:forEach>
				
				
			</div>
		</div>

		<section class="border mt-2 bg-white">
			<header class="p-2 border-bottom">
				<span style="font-weight: 600;">Gente que podrias conocer</span>
			</header>
			<main class="d-flex flex-wrap px-4">
				<div style="width: 165px;" class="d-block pt-2">
					<div class="d-flex justify-content-center align-items-center px-2" >						
						<div class="d-flex flex-column justify-content-center border pt-4" style="position: relative; width: 100%;">
							<div class="bg-info" style="height: 60px; position: absolute; top: 0; width: 100%"></div>
							<div class="d-flex justify-content-center" style="z-index: 1;">
								<div><img src="me.jpg" class="rounded-circle" width="72"></div>
							</div>
							<div class="px-2 d-flex flex-column justify-content-center">
								<div class="d-flex flex-column justify-content-center">
									<span style="overflow-x: hidden; text-overflow: ellipsis; white-space: nowrap;" class="text-center">Aldo Prohenza Hernandez</span><small class="text-center text-muted">Estudiante</small>	
								</div>
								<div class="d-flex justify-content-center">
									<small style="overflow-x: hidden; text-overflow: ellipsis; white-space: nowrap;" class="text-center text-muted py-1">Desarrollo de aplicaciones multiplataformas</small>
								</div>
							</div>
							<div class="d-flex justify-content-center p-2">
								<button class="btn-ceep btn-sm rounded-0" style="flex: 1;">Conectar</button>
							</div>
						</div>
					</div>	
				</div>

				<div style="width: 165px;" class="d-block pt-2">
					<div class="d-flex justify-content-center align-items-center px-2" >						
						<div class="d-flex flex-column justify-content-center border pt-4" style="position: relative; width: 100%;">
							<div class="bg-info" style="height: 60px; position: absolute; top: 0; width: 100%"></div>
							<div class="d-flex justify-content-center" style="z-index: 1;">
								<div><img src="me.jpg" class="rounded-circle" width="72"></div>
							</div>
							<div class="px-2 d-flex flex-column justify-content-center">
								<div class="d-flex flex-column justify-content-center">
									<span style="overflow-x: hidden; text-overflow: ellipsis; white-space: nowrap;" class="text-center">Aldo Prohenza Hernandez</span><small class="text-center text-muted">Estudiante</small>
								</div>
								<div class="d-flex justify-content-center">
									<small style="overflow-x: hidden; text-overflow: ellipsis; white-space: nowrap;" class="text-center text-muted py-1">Desarrollo de aplicaciones multiplataformas</small>
								</div>
							</div>
							<div class="d-flex justify-content-center p-2">
								<button class="btn-ceep btn-sm rounded-0" style="flex: 1;">Conectar</button>
							</div>
						</div>
					</div>	
				</div>

				<div style="width: 165px;" class="d-block pt-2">
					<div class="d-flex justify-content-center align-items-center px-2" >						
						<div class="d-flex flex-column justify-content-center border pt-4" style="position: relative; width: 100%;">
							<div class="bg-info" style="height: 60px; position: absolute; top: 0; width: 100%"></div>
							<div class="d-flex justify-content-center" style="z-index: 1;">
								<div><img src="me.jpg" class="rounded-circle" width="72"></div>
							</div>
							<div class="px-2 d-flex flex-column justify-content-center">
								<div class="d-flex flex-column justify-content-center">
									<span style="overflow-x: hidden; text-overflow: ellipsis; white-space: nowrap;" class="text-center">Aldo Prohenza Hernandez</span><small class="text-center text-muted">Estudiante</small>
								</div>
								<div class="d-flex justify-content-center">
									<small style="overflow-x: hidden; text-overflow: ellipsis; white-space: nowrap;" class="text-center text-muted py-1">Desarrollo de aplicaciones multiplataformas</small>
								</div>
							</div>
							<div class="d-flex justify-content-center p-2">
								<button class="btn-ceep btn-sm rounded-0" style="flex: 1;">Conectar</button>
							</div>
						</div>
					</div>	
				</div>

				<div style="width: 165px;" class="d-block pt-2">
					<div class="d-flex justify-content-center align-items-center px-2" >						
						<div class="d-flex flex-column justify-content-center border pt-4" style="position: relative; width: 100%;">
							<div class="bg-info" style="height: 60px; position: absolute; top: 0; width: 100%"></div>
							<div class="d-flex justify-content-center" style="z-index: 1;">
								<div><img src="me.jpg" class="rounded-circle" width="72"></div>
							</div>
							<div class="px-2 d-flex flex-column justify-content-center">
								<div class="d-flex flex-column justify-content-center">
									<span style="overflow-x: hidden; text-overflow: ellipsis; white-space: nowrap;" class="text-center">Aldo Prohenza Hernandez</span><small class="text-center text-muted">Estudiante</small>
								</div>
								<div class="d-flex justify-content-center">
									<small style="overflow-x: hidden; text-overflow: ellipsis; white-space: nowrap;" class="text-center text-muted py-1">Desarrollo de aplicaciones multiplataformas</small>
								</div>
							</div>
							<div class="d-flex justify-content-center p-2">
								<button class="btn-ceep btn-sm rounded-0" style="flex: 1;">Conectar</button>
							</div>
						</div>
					</div>	
				</div>

				<div style="width: 165px;" class="d-block pt-2">
					<div class="d-flex justify-content-center align-items-center px-2" >						
						<div class="d-flex flex-column justify-content-center border pt-4" style="position: relative; width: 100%;">
							<div class="bg-info" style="height: 60px; position: absolute; top: 0; width: 100%"></div>
							<div class="d-flex justify-content-center" style="z-index: 1;">
								<div><img src="me.jpg" class="rounded-circle" width="72"></div>
							</div>
							<div class="px-2 d-flex flex-column justify-content-center">
								<div class="d-flex flex-column justify-content-center">
									<span style="overflow-x: hidden; text-overflow: ellipsis; white-space: nowrap;" class="text-center">Aldo Prohenza Hernandez</span><small class="text-center text-muted">Estudiante</small>
								</div>
								<div class="d-flex justify-content-center">
									<small style="overflow-x: hidden; text-overflow: ellipsis; white-space: nowrap;" class="text-center text-muted py-1">Desarrollo de aplicaciones multiplataformas</small>
								</div>
							</div>
							<div class="d-flex justify-content-center p-2">
								<button class="btn-ceep btn-sm rounded-0" style="flex: 1;">Conectar</button>
							</div>
						</div>
					</div>	
				</div>
			</main>
		</section>
	
	</section>

	<section class="col-4 p-0 sticky-top" style="height: 100vh;"></section>
</div>

<script type="text/javascript">
	$('input[name="search"]').keyup(function(){
		if ($(this).val() != '') {
			$('#ember2517').removeClass('d-none');
		}else{
			$('#ember2517').addClass('d-none');
		}
			
	})

	$('body').click(function(){
		$('#ember2517').addClass('d-none');
	})

	$('#ember2517').click(function(e){
		e.stopPropagation();
	})
		
</script>
</body>