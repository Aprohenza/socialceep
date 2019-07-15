<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Mi red | Social Ceep</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="icon" type="image/vnd.microsoft.icon" href="${pageContext.request.contextPath}/assets/img/ceep_logo_no_back.ico">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
	<script src="https://code.jquery.com/jquery-3.4.0.js"></script>
	
	<style type="text/css">
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
</style>
</head>
<body style="background-color: #f5f5f5; position: relative;">
<jsp:include page="../views/tiles/navbar.jsp" />

<div style="position: relative; margin-top: 51px;" class="container py-4 d-flex justify-content-center align-items-start">
<section class="col-4 p-0 pr-4 sticky-top">
		<div class="bg-white border">
			<a href="${pageContext.request.contextPath}/mynetwork/connections">
				<div class="p-2 border-bottom" style="cursor: pointer;">
				
					<i class="fas fa-user-friends"></i>
					<span class="px-2">contactos</span>
				
				</div>
			</a>
			<section id="publi" class="p-2">
				<div class="bg-white d-flex justify-content-center">
					<a href="">
					<img style="width: 120px;" src="${pageContext.request.contextPath}/assets/img/ceep_logo_no_back.png" class="col-12 p-0">
					</a>
				</div>
			</section>
			<a href="">
				<div class="border-top border-bottom p-2 d-flex justify-content-center" style="cursor: pointer;">
					<span>Amplia tu red</span>
				</div>
			</a>
			<div class="d-flex justify-content-center flex-column p-4" id="creditos">
				<div class="d-flex justify-content-around">
					<small>Acerca de<span class="px-2">&middot;</span>Centro de ayuda</small>
				</div>
				<div class="py-2 d-flex justify-content-center">
					<small>Unete al Social Ceep Team</small>
				</div>
				<div class="d-flex justify-content-center">
					<small>Social Ceep © 2019</small>
				</div>
			</div>
	
		</div>
	</section>
	<section class="p-0 col-8">
		<div id="invitaciones" class="border p-0 bg-white">
			<header id="invitaciones-header" class="p-2">
				<span style="font-weight: 600;">Invitaciones</span>
				<span id="invitaciones-count">0</span>
			</header>
			<main id="friend-request-container"></main>
		</div>

		<div id="sugerencias" class="border mt-2 bg-white">
			<header class="p-2 border-bottom">
				<span style="font-weight: 600;">Sugerencias para ti</span>
			</header>
			<main id="friends-suggestion" class="d-flex flex-wrap px-4"></main>
		</div>
		
	</section>
</div>
<section class="d-none justify-content-center align-items-start pt-4"
		id="global"
		style="min-height: calc(100vh - 51px); width: 100%; position: absolute; top: 0; background-color: rgba(0, 0, 0, 0.8); z-index: 1;">
		<div id="error-container">
			<div class="card">
  <div class="card-body">
    <div>Lo sentimos. Ha ocurrido un error!!!</div>
    <div id="error-message"></div>
  </div>
</div>
</div>
	</section>
</body>
<script src="${pageContext.request.contextPath}/assets/js/navbar.js"></script>
<script type="text/javascript">



	if(sessionStorage.sessionFriendsRequest != null && sessionStorage.sessionFriendsRequest != 0){
		//console.log(localStorage.sessionFriendsRequest.length);
		console.log("ESTOY AKI");
		renderAllFriendRequest(JSON.parse(sessionStorage.sessionFriendsRequest));
	}else{
		console.log("HACIENDO PETICION DE FRIENDS REQUEST");
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/mynetwork/friendrequest/all',
			success: function(respuesta) {
				
				renderAllFriendRequest(respuesta);
			},
			error: function(jqXHR, textStatus, errorThrown) {
		       console.log(jqXHR.status, textStatus, errorThrown);
		       $('#global').addClass('d-flex');
		       $('#global #error-message').html("Error code: " + jqXHR.status);
		   }
		})
	}


function renderAllFriendRequest(response){
	//console.log(response);
	if(sessionStorage.sessionFriendsRequest != null && sessionStorage.sessionFriendsRequest != 0){
		$('#friend-request-container').html('');
		$('#invitaciones-count').html('(' + response.length + ')');
		response.forEach(function(friend){
			$('#friend-request-container').append('<jsp:include page="../views/tiles/friend_request.jsp" />');
		})	
	}else{
		$('#invitaciones-header').html('No tienes invitaciones de amistad.');
	}
	
}

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




$('#friend-request-container').on('click', '.friend-request-actions>button', function(){
	action = $(this).attr('value');
	friendrequestid = $(this).parent().attr('friendrequestid');
	console.log(action + ": "+ friendrequestid);
	
	$.ajax({
		type: 'POST',
		data: {'action' : action, 'friendrequestid' : friendrequestid},
		url: '${pageContext.request.contextPath}/mynetwork/actionFriendRequest',
		success: function(response) {
			sessionStorage.setItem("sessionFriendsRequest", JSON.stringify(response));
			renderAllFriendRequest(response);
		},
		error: function(jqXHR, textStatus, errorThrown) {
	       console.log(jqXHR.status, textStatus, errorThrown);
	   }
	});
	
})



</script>
</html>