<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Messaging | Social Ceep</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
<script src="https://code.jquery.com/jquery-3.4.0.js"></script>
<link rel="icon" type="image/vnd.microsoft.icon"
	href="${pageContext.request.contextPath}/assets/img/ceep_logo_no_back.ico">
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

body {
	background-color: #f5f5f5;
}

.cursor-pointer {
	cursor: pointer;
}

.message-hover:hover {
	background: #f3f6f8;
}

.fw-600 {
	font-weight: 600;
}

.thread-selected {
	border-left: 2px solid #202c5a;
}
</style>
</head>
<body>
	<jsp:include page="../views/tiles/navbar.jsp" />



	<div id="container" class="container d-flex p-0 py-4"
		style="height: calc(100vh - 51px); margin-top: 51px;">

		<section class="bg-white col-4 p-0 border d-flex flex-column"
			id="side-left">
			<header class="p-2 border-bottom fw-600">Mensajes</header>
			<main style="overflow-y: scroll; flex: 1;">
			<section class="border-bottom bg-white" id="threads">
			
			
			
			<c:choose>
				<c:when test="${hasConversations}">
				<c:forEach var="conversation" items="${conversations}">
					<a
						href="${pageContext.request.contextPath}/messaging/thread/${conversation.conversationThread}">
						<div
							class="thread  <c:if test="${conversation.conversationThread == messages[0].messageConversationThread}">thread-selected</c:if>  message-hover border-bottom cursor-pointer d-flex align-items-center p-2">
							<div>
								<img src="${pageContext.request.contextPath}/images/${conversation.participantsConversation[0].userProfilePhotoProfile}" width="56" class="rounded-circle">
							</div>
							<div class="px-2" style="flex: 1;">
								<div class="d-flex justify-content-between" style="flex: 1;">
									<span class="fw-600">
										${conversation.participantsConversation[0].userProfileName} ${conversation.participantsConversation[0].userProfileLastName} </span><span
										class="text-muted">${conversation.messageConversation[0].messageDate}</span>
								</div>
								<div>
									<small style="line-height:">${conversation.messageConversation[0].messageBody}</small>
								</div>
							</div>
						</div>
					</a>
				</c:forEach>
				</c:when>
				<c:otherwise>
				<div class="col-12 p-0 d-flex justify-content-center align-items-center flex-column">
					<img src="${pageContext.request.contextPath}/assets/img/messages_no_result.png" />
					<div class="d-flex justify-content-center text-center flex-column">
						<span class="py-2">Aun no hay mensajes.</span>
						<span>Comunicate y entabla una comunicacion.</span>
						<span>Podrias obtener buenos resultados.</span>
					</div>
				</div>
				
				</c:otherwise>
			</c:choose>

				
			</section>
			</main>
		</section>

		<section class="bg-danger col-8 p-0 border d-flex flex-column" id="side-right" style="align-self: baseline; height: 100%;">
			
				<c:choose>
				<c:when test="${!hasConversations}">
				<header style="min-height: 41px;" id="participant-conversation"	class="border-bottom p-2 bg-white fw-600 text-center">Nuevo mensaje</header>
				<div id="lookfor" style="position: relative;">
					<input id="lookfor-friend-to-send-message" style="border-style: hidden; outline: none;" class="col-12 p-2 border-bottom" type="text" placeholder="Escribe el nombre de algun amigo...">
					<div id="friends-match-to-send-message" style="position: absolute;" class="col-12 p-2 border-bottom d-none">
						<span>No hay contactos con ese nombre</span>
					</div>
				</div>
				
				</c:when>
				
				<c:otherwise>
				<header style="min-height: 41px;" id="participant-conversation"	class="border-bottom p-2 bg-white fw-600"></header>
				</c:otherwise>				
				</c:choose>
		
			<section id="story"	class="bg-white border-bottom py-2  justify-content-center align-items-center" style="height: 290px; overflow: auto;">

				<c:forEach var="messages" items="${messages}">
					<div class="message-container d-flex flex-column pb-4">
						<div style="z-index: 1;" class="px-2 d-flex align-items-start">
							<img src="${pageContext.request.contextPath}/images/${messages.messageAuthorPhotoProfile}" width="40" class="rounded-circle message-author-photo">
							<div style="margin-left: 10px;"	class="fw-600 message-author-name">
								<span><a href="${pageContext.request.contextPath}/profile/${messages.messageAuthorId}/">${messages.messageAuthor}</a></span><span class="text-muted px-2">&middot;</span><small>${messages.messageDate}</small>
							</div>
						</div>
						<div class="message-hover" style="margin-top: -20px;">
							<div style="margin-left: 50px;" class="px-2 message-body">${messages.messageBody}</div>
						</div>
					</div>
				</c:forEach>
				<!-- 				<style type="text/css">
	.it1{
		animation-name: example;
  		animation-duration: 1s;
  		animation-iteration-count: 50;
	}
	@keyframes example {
  		0%   {width:190px;}
		25%  {width:120px;}
    	50%  {width:150px;}
    	75%  {width:80px;}
    	100% {width:190px;}
	}

	.it2{
		animation-name: example2;
  		animation-duration: 1s;
  		animation-iteration-count: 50;
	}
	@keyframes example2 {
  		0%   {width:200px;}
		25%  {width:60px;}
    	50%  {width:150px;}
    	75%  {width:10px;}
    	100% {width:350px;}
	}

	.it3{
		animation-name: example3;
  		animation-duration: 1s;
  		animation-iteration-count: 50;
	}
	@keyframes example3 {
  		0%   {width:400px;}
		25%  {width:500px;}
    	50%  {width:250px;}
    	75%  {width:190px;}
    	100% {width:310px;}
	}
</style>
					<section class="col-12 pl-2 mb-4" id="preload-messages">
		<div class="d-flex align-items-start intermitente">
			<div class="rounded-circle" style="width: 40px; height: 40px; background: #e6e2eb;"></div>
			<div class="rounded mx-2" style="width: 190px; height: 15px; ">
				<div class="rounded m-1" style="width: 190px; height: 15px; background: #e6e2eb;"></div>
				<div class="rounded m-1 it2" style="width: 350px; height: 10px; background: #e6e2eb;"></div>
				<div class="rounded m-1 it3" style="width: 310px; height: 10px; background: #e6e2eb;"></div>
				<div class="rounded m-1 it1" style="width: 210px; height: 10px; background: #e6e2eb;"></div>
			</div>
		</div>

		<div class="d-flex align-items-start" style="margin-top: 50px;" id="">
			<div class="rounded-circle" style="width: 40px; height: 40px; background: #e6e2eb;"></div>
			<div class="rounded mx-2" style="width: 190px; height: 15px; ">
				<div class="rounded m-1" style="width: 190px; height: 15px; background: #e6e2eb;"></div>
				<div class="rounded m-1 it1" style="width: 350px; height: 10px; background: #e6e2eb;"></div>
				<div class="rounded m-1 it2" style="width: 310px; height: 10px; background: #e6e2eb;"></div>
				<div class="rounded m-1 it3" style="width: 210px; height: 10px; background: #e6e2eb;"></div>
			</div>
		</div>

		<div class="d-flex align-items-start" style="margin-top: 50px;">
			<div class="rounded-circle" style="width: 40px; height: 40px; background: #e6e2eb;"></div>
			<div class="rounded mx-2" style="width: 190px; height: 15px; ">
				<div class="rounded m-1" style="width: 190px; height: 15px; background: #e6e2eb;"></div>
				<div class="rounded m-1 it3" style="width: 350px; height: 10px; background: #e6e2eb;"></div>
				<div class="rounded m-1 it2" style="width: 310px; height: 10px; background: #e6e2eb;"></div>
				<div class="rounded m-1 it1" style="width: 210px; height: 10px; background: #e6e2eb;"></div>
			</div>
		</div>
	</section>		-->		
			</section> 

				<form name="message" class="d-flex flex-column" style="flex: 1;">
					<section class="bg-warning p-0" style="min-height: 100px;">
						<textarea name="messageBody" class="col-12 p-2 m-0"
							style="resize: none; box-shadow: none; outline: none; height: 100%; border-top: 2px solid #202c5a; border-left: 0; border-right: 0; border-bottom: 0;"
							placeholder="Escribe un mensaje o adjunta un archivo"
							id="box-message" ></textarea>

					</section>
					<div id="show-preview" class="bg-white d-flex justify-content-center">
						<!-- <img src="assets/img/blank_default_profile.png"> -->
					</div>
					<section style="background: #f3f6f8; flex: 1; min-height: 50px;" class="col-12 p-3 d-flex justify-content-between align-items-center" id="botones">
						<div>
							<i id="upload-image-message" class="far fa-image fa-lg mr-2 cursor-pointer"></i>
							<input type="file" name="image" accept="image/*" style="display: none;">
							<i class="far fa-smile fa-lg mr-2 cursor-pointer"></i>
						</div>
						<div>
							<input type="hidden" name="messageThread" value="${messageThread}" />
							<input type="hidden" name="messageRecipient"  value="" />
							<input type="submit" class="btn btn-primary btn-sm rounded-0" disabled id="send-new-message" style="box-shadow: none;" value="Enviar">
						</div>
					</section>
				</form>
			</section>
	</div>
</body>
<script src="${pageContext.request.contextPath}/assets/js/navbar.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$('#box-message').keyup(function() {
			
			content = $(this).val();
			if (content != '') {
				$('#send-new-message').removeAttr('disabled');
			} else {
				$('#send-new-message').attr('disabled', true);
			}
		})

						//upload image message
						$('#upload-image-message').click(function() {
							$('input[name=image]').trigger('click');
						})

						//preview load image message
						$('input[name=image]')
								.change(
										function(e) {

											var reader = new FileReader();
											reader
													.readAsDataURL(e.target.files[0]);

											reader.onload = function() {
												$('#show-preview')
														.html(
																'<div class="col-12 p-0" style="background-image: url(\''
																		+ reader.result
																		+ '\'); height: 275px; background-size: contain; background-position: center; background-repeat: no-repeat;"><span id="close-preview" style="font-size: 32px; display: contents; cursor: pointer;" aria-hidden="true">&times;</span></div>');
												$('#send-new-message')
														.removeAttr('disabled');
											}
										})

						$('#show-preview').on(
								'click',
								'#close-preview',
								function() {
									$('input[name=image]').val('');
									$('#show-preview').html('');
									$('#send-new-message').attr('disabled',
											true);

								})

						

						//send message ajax
						$('form[name="message"]').submit(function(e) {
							e.preventDefault();
							var formData = new FormData();
							formData.append("messageBody", $('textarea[name="messageBody"]', this).val());
							formData.append("messageThread", $('input[name="messageThread"]', this).val());
							formData.append("messageRecipient", $('input[name="messageRecipient"]', this).val());
							$.ajax({
								url : '${pageContext.request.contextPath}/messaging/insert',
								type : 'POST',
								processData : false,
								contentType : false,
								data : formData,
								data : formData,
								
								success : function(messages) {													
									console.log(messages);
									$('#story').append('<jsp:include page="../views/tiles/message_item.jsp" />');
									$('#box-message').val('');
								},
								error : function(jqXHR, textStatus, errorThrown) {
									console.log(jqXHR.status, textStatus, errorThrown);
								}
							});//send message ajax
						})
						
						
						$('#lookfor-friend-to-send-message').keyup(function(){
							var pattern = $(this).val();
							if(pattern && pattern.length >= 4){
								JSON.parse(sessionStorage.sessionFriends).forEach(function(friend){
									console.log(friend.userProfileName);
									if(friend.userProfileName.toUpperCase().includes(pattern.toUpperCase()) || friend.userProfileLastName.toUpperCase().includes(pattern.toUpperCase())){
										$('#friends-match-to-send-message').removeClass('d-none');
										$('#friends-match-to-send-message').removeClass('border-bottom');
										$('#friends-match-to-send-message').html('<div contactid="'+friend.userProfileId+'" class="bg-white border-bottom p-2 contact-to-send-message" style="cursor: pointer;"><div class="d-flex align-items-center justify-content-between  p-2"><div class="d-flex align-items-center"><div><div><img src="${pageContext.request.contextPath}/images/'+friend.userProfilePhotoProfile+'" class="rounded-circle" width="36"></div></div><div class="px-2"><div><span class="fw-600 lh-8">'+friend.userProfileName+' '+friend.userProfileLastName+'</span><span class="px-2">&middot;</span><span class="text-muted">'+friend.userProfileRole+'</span></div><div><span>Desarrollo de aplicaciones multiplataformas</span></div></div></div></div></div>');		
									}else{
										$('#friends-match-to-send-message').html('<span>No hay contactos con ese nombre</span>');
										$('#friends-match-to-send-message').removeClass('d-none');
										$('#friends-match-to-send-message').addClass('border-bottom');
									}
								})
								
							}else{
								$('#friends-match-to-send-message').html('<span>No hay contactos con ese nombre</span>');
								$('#friends-match-to-send-message').addClass('d-none');
							}
						})
						
						$('#lookfor').on('click', '.contact-to-send-message', function(){
							
							var friendId = $(this).attr('contactid');
							$('input[name="messageRecipient"]').attr('value', friendId);
							var content;
							$('#story').html(function(content){
								JSON.parse(sessionStorage.sessionFriends).forEach(function(e){
									if(e.userProfileId == friendId){
										content = '<div class="px-2"><img width="72" height="72" class="rounded-circle" src="${pageContext.request.contextPath}/images/'+e.userProfilePhotoProfile+'" /><div class="d-flex flex-column"><span>'+e.userProfileName + ' ' + e.userProfileLastName +'</span><small class="text-muted">'+e.userProfileRole+'</small></div></div>';
										$('#friends-match-to-send-message').addClass('d-none');
										
									}
								})
								
								return content;
							})
							
						})
						
						
						
						
					})// ready
</script>
</html>