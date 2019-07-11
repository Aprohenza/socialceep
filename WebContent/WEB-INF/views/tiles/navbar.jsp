<%@page import="com.formbean.session.UserSession"%>
<%@ page import="com.formbean.session.SessionManager" %>
<%@ page import="com.formbean.entity.LoginEntity" %>
<%-- <%
	essionDTO userSession = SessionManager.getUserSession(request);
%> --%>

<header style="position: fixed; top: 0; width: 100%; z-index: 1;">
		<div style="background-color: #202c5a;">
		<div class="container p-0 text-white" >
				<div class="d-flex align-items-center justify-content-between"> <!-- bg-success -->
					<section style="flex: 1;" class="d-flex align-items-center " id="search"> <!-- bg-danger -->
						<div id="search-brand"><img src="${pageContext.request.contextPath}/assets/img/siglas-215x70.png" width="90"></div>
						<div style="width: 60%;" id="search-field"><input style="box-shadow: none;" class="form-control form-control-sm rounded-0" type="text" name="search-input" id="search-input" placeholder="Busqueda"></div>
					</section>
					<nav style="" class="d-flex align-items-center col-6 py-1"> <!-- bg-danger -->
						<a style="text-decoration: none; color: white; flex:1;" href="${pageContext.request.contextPath}/feed"><div id="nav-item-ini" class="d-flex flex-column align-items-center"><i class="nav-item-pointer fas fa-home fa-lg"></i><small class="nav-item-pointer">Inicio</small></div></a>
						<a style="text-decoration: none; color: white; flex:1;" href="${pageContext.request.contextPath}/mynetwork"><div id="nav-item-mired" class="nav-item d-flex flex-column align-items-center"><i class="fas fa-users fa-lg nav-item-pointer"></i><small class="nav-item-pointer">Mi red</small></div></a>
						<a style="text-decoration: none; color: white; flex:1;" href="${pageContext.request.contextPath}/messaging"><div id="mensajes" class="nav-item d-flex flex-column align-items-center"><i class="fas fa-envelope fa-lg nav-item-pointer"></i><small class="nav-item-pointer">Mensajes</small></div></a>
						<a style="text-decoration: none; color: white; flex:1;" href="${pageContext.request.contextPath}/notifications"><div id="notificaciones" class="nav-item d-flex flex-column align-items-center"><i class="fas fa-bell fa-lg nav-item-pointer"></i><small class="nav-item-pointer">Notificaciones</small></div></a>
						<div style="flex: 1;" id="yo" class="nav-item d-flex flex-column align-items-center"><img src="${pageContext.request.contextPath}/images/${uSession.userProfilePhoto}" width="24" height="24" class="rounded-circle cursor-pointer"><small class="cursor-pointer">Yo</small></div> <!-- bg-primary -->
					</nav>
				</div>
		</div>
		</div>
		<section  class="container p-0 d-flex justify-content-end" >
			<div id="profile-box" style="position: absolute; z-index: 1; display: none;" class="col-3 p-0 bg-white border">
				<div class="p-4">
					<div class="d-flex cursor-pointer">
						<div><img src="${pageContext.request.contextPath}/images/${uSession.userProfilePhoto}" class="rounded-circle" width="56"></div>
						<div class="d-flex flex-column px-2">
							<small style="font-weight: 600;">${uSession.userProfileName} ${uSession.userProfileLastName}</small>
							<small class="text-muted">${uSession.userProfileRole}</small>
						</div>
					</div>
					<a style="text-decoration: none;" href="${pageContext.request.contextPath}/profile/${uSession.userProfileId}/"><div class="p-2 text-primary text-center mt-2 cursor-pointer" id="view-profile">Ver perfil</div></a>
				</div>
				<div id="close-user-session" class="col-12 p-2 border-top cursor-pointer">
					<span><a class="col-12 p-0" href="${pageContext.request.contextPath}/logout" style="text-decoration: none; display: block;">Cerrar sesion</a></span>
				</div>
			</div>
		</section>
		
	</header>
	
	<script>
	//WORKER
	if (window.WebSocket === undefined) {
		console.log("sockets not supported");
	} else {
		console.log("Websocket Supported");
		var worker = new Worker('${pageContext.request.contextPath}/assets/js/worker.js');
		console.log("ENVIO: " + "${uSession.userProfileId}");
		worker.postMessage("${uSession.userProfileId}");
		worker.onmessage = function(event) {
			
			
			console.log(event.data);
			
			if(event.data.contentType == 'sessionStatus'){
				if(localStorage.sessionStatus == null){
					localStorage.setItem("sessionStatus", JSON.stringify(event.data.content));
					console.log("Contenido de sessionStatus guardado.");
					//loadSessionComponents();	
				}				
			}
			
			if(event.data.contentType == 'posts'){
				localStorage.setItem("posts", JSON.stringify(event.data.content));
				console.log("Contenido de posts guardado.");
				renderizePost();
			}
			if(event.data.contentType == 'sessionFriends'){
				localStorage.setItem("sessionFriends", JSON.stringify(event.data.content));
				console.log("Contenido de fiends guardado.");
			}
			if(event.data.contentType == 'sessionFriendsRequest'){
				localStorage.setItem("sessionFriendsRequest", JSON.stringify(event.data.content));
				console.log("Contenido de fiends request guardado.");
				renderAllFriendRequest(JSON.parse(localStorage.sessionFriendsRequest));
			}
			
			if(event.data.contentType == 'conversations'){
				localStorage.setItem("conversations", JSON.stringify(event.data.content));
				console.log("Contenido conversations guardado.");
				renderAllFriendRequest(JSON.parse(localStorage.sessionFriendsRequest));
			}
			
			
			
			if(event.data.contentType == 'newPost'){
				var posts = JSON.parse(localStorage.posts);
				posts.unshift(event.data.content);
				localStorage.setItem("posts", JSON.stringify(posts));
				
				console.log("Nuevo post agregado.");
				
				addNewPost(event.data.content);
			}
			
			if(event.data.contentType == 'newMessageChat'){
				console.log("tengo nuevo mensaje");
				renderNewMessage(event.data.content);
			}
			
			
			
			
		};
	}
	
	//render new mwssage
	function renderNewMessage(newMessage){
		console.log(newMessage);
		messages = newMessage
		if(window.location.pathname.includes("messaging/thread")){
			$('#story').append('<jsp:include page="message_item.jsp" />');	
		}else{
			alert("Tienes un nuevo mensaje.");
		}
		
	}
	
	/* function loadSessionComponents(){
		if (typeof(Storage) !== "undefined") {
			if(localStorage.sessionStatus != null && localStorage.sessionStatus == 1){
				var xhr = new XMLHttpRequest();
				xhr.open("POST", "http://192.168.1.40:8080/FormBeanSpringExample/session/components/load", true);
				xhr.send(null);
				
				xhr.onreadystatechange = function(){
					if(xhr.readyState == 4 && xhr.status == 200){
						console.log("Lanzada solicitud de componentes de la session.");
						localStorage.setItem("sessionStatus", 2);
					}else{
						console.log("Error en la peticion. Status: " + xhr.status + ". Text: " + xhr.statusText);						
					}
				}
			}else{
				console.log("No se ha abierto la session!!!");
			}
		}else{
			alert("Sorry! No Web Storage support..");
		}	
	} */
	
	
	
	
	
	
	</script>