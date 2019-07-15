<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/vnd.microsoft.icon" href="${pageContext.request.contextPath}/assets/img/ceep_logo_no_back.ico">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
<script src="http://code.jquery.com/jquery-3.4.1.js"></script>

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
<body>

<jsp:include page="../views/tiles/navbar.jsp" />


<div class="container p-0 pt-4  d-flex justify-content-between align-items-start " style="margin-top: 51px;">
	<section class="col-8 p-0">
		<section id="ember2511" class="bg-white border"><!---->
	    	<div class="p-3">
	        	<h2 class="t-24 t-black t-light" style="font-weight: 200; font-size: 1.6rem;">Aún no tienes ningún contacto.</h2>
	        	<h3 class="t-16 t-black t-normal pt3" style="font-weight: 400; font-size: 1.2rem;">Descubre nuevas ideas y empleos en Social Ceep a través de tus contactos y sus redes. Encuentra a tu primer contacto a continuación.</h3>
	      	</div>
	      	<div class="mn-connections__empty-state-inbug"></div>
		</section>

		<section id="ember2512" class="mt-2"><!---->
			<div class="border bg-white p-3">
	  			<div class="mn-abi-form__container container-with-shadow" tabindex="-1">
					<div class="mn-abi-form__content" style="position: relative;">
	      				<div>
		    				<h2 class="" style="font-weight: 200; font-size: 1.6rem;">Averigua a quién conoces en Social Ceep</h2>
							<p class="t-16 t-black--light t-normal mb4">Encuentra a tus amigos e invitalos a conectar.</p>
		              		<label for="email" class="visually-hidden"></label>
		                	<input  name="search" placeholder="Busqueda" id="search" class="col-12 p-0" type="text">
						</div>
						<div id="ember2517" class="border d-none col-12 p-0" style="position: absolute; z-index: 2;">
							<div class="bg-white border-bottom p-2 contact" style="cursor: pointer;">
								<div class="d-flex align-items-center justify-content-between  p-2">
									<div class="d-flex align-items-start">Cargando...</div>
								</div>
							</div>
						</div>
				
						<div class="mn-abi-form__graphic"></div>
	    			</div>
	 			</div>
			</div>
		</section>

		<section class="border mt-2 bg-white">
			
			<header class="p-2 border-bottom">
				<span style="font-weight: 600;">Gente que podrias conocer</span>
			</header>
			<main id="friends-suggestion" class="d-flex flex-wrap px-4"></main>
			
		</section>
	</section>

	<section class="col-4 sticky-top" style="height: 100vh; padding-top: 51px;">
		<div class="bg-danger" style="height: 200px;" ></div>
	</section>
</div>

<script type="text/javascript">
	$('input[name="search"]').keyup(function(){
		if ($(this).val() != '' && $(this).val().length >= 4) {
			$.ajax({
				type : 'GET',
				url : '${pageContext.request.contextPath}/search/user/' + $(this).val(),
				success : function(response) {
					$('#ember2517').html('');
					JSON.parse(response).forEach(function(user){
						$('#ember2517').append('<a href="${pageContext.request.contextPath}/profile/'+user.userProfileId+'/"><div class="bg-white border-bottom p-2 contact" style="cursor: pointer;"><div class="d-flex align-items-center justify-content-between  p-2"><div class="d-flex align-items-start"><div><div><img src="${pageContext.request.contextPath}/images/'+user.userProfilePhotoProfile+'" class="rounded-circle" width="36"></div></div><div class="px-2"><div class="d-flex flex-column"><span class="fw-600 lh-8">'+user.userProfileName+' '+user.userProfileLastName+'</span></div><div><span class="text-muted">'+user.userProfileRole+'</span><span>&middot;</span><span>Desarrollo de aplicaciones multiplataformas</span></div></div></div></div></div></a>');	
					})
					
					
					console.log(response);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR.status, textStatus, errorThrown);
				}
			});
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
	
	renderAllFriendsSuggestion();

function renderAllFriendsSuggestion(){
	//console.log(response);
	if(sessionStorage.sessionFriendsSuggestion != null){
		console.log("suggestion es distinto de null");
		var sessionFriendsSuggestion = JSON.parse(sessionStorage.sessionFriendsSuggestion);
		
		if(sessionFriendsSuggestion.length > 0){
			console.log("suggestion es mayor a 0");
			sessionFriendsSuggestion.forEach(function(friend){
				console.log(friend.userProfileName);
				if(friend.userProfileCycle == null){
					friend.userProfileCycle = '__';
				}
				$('#friends-suggestion').append('<div style="width: 165px;" class="d-block pt-2"><div style="height: 100%;" class="d-flex justify-content-center align-items-center px-2" ><div class="d-flex flex-column justify-content-center border pt-4" style="position: relative; width: 100%; height: 100%;"><div class="bg-info" style="height: 60px; position: absolute; top: 0; width: 100%"></div><div class="d-flex justify-content-center" style="z-index: 1;"><div><img src="${pageContext.request.contextPath}/images/'+friend.userProfilePhotoProfile+'" class="rounded-circle" width="72" height="72"></div></div><div class="px-2 d-flex flex-column justify-content-center"><div class="d-flex flex-column justify-content-center"><span style="overflow-x: hidden; text-overflow: ellipsis; white-space: nowrap;" class="text-center">'+friend.userProfileName+' '+friend.userProfileLastName+'</span><small class="text-center text-muted">'+friend.userProfileRole+'</small></div><div class="d-flex justify-content-center"><small style="overflow-x: hidden; text-overflow: ellipsis; white-space: nowrap;" class="text-center text-muted py-1">'+friend.userProfileCycle+'</small></div></div><div class="d-flex justify-content-center p-2"><button class="btn-ceep btn-sm rounded-0" style="flex: 1;">Conectar</button></div></div></div></div>');
			})	
		}
		
		
	}else{
		//$('#invitaciones-header').html('No tienes invitaciones de amistad.');
	}
	
}
		
</script>


</body>
</html>