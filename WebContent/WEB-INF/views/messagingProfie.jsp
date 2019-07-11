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
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
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

				<c:forEach var="conversation" items="${conversations}">
					<a
						href="${pageContext.request.contextPath}/messaging/thread/${conversation.conversationThread}">
						<div
							class="thread  <c:if test="${conversation.conversationThread == messages[0].messageConversationThread}">thread-selected</c:if>  message-hover border-bottom cursor-pointer d-flex align-items-center p-2">
							<div>
								<img src="${pageContext.request.contextPath}/images/${conversation.participantsConversation[0].participantPhotoProfile}" width="56" class="rounded-circle">
							</div>
							<div class="px-2" style="flex: 1;">
								<div class="d-flex justify-content-between" style="flex: 1;">
									<span class="fw-600">
										${conversation.participantsConversation[0].participantName} ${conversation.participantsConversation[0].participantLastName} </span><span
										class="text-muted">${conversation.messageConversation[0].messageDate}</span>
								</div>
								<div>
									<small style="line-height:">${conversation.messageConversation[0].messageBody}</small>
								</div>
							</div>
						</div>
					</a>
				</c:forEach>
			</section>
			</main>
		</section>

		<section class="bg-danger col-8 p-0 border d-flex flex-column" id="side-right" style="align-self: baseline; height: 100%;">
			<header style="min-height: 41px;" id="participant-conversation"	class="border-bottom p-2 bg-white fw-600"></header>
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

				<form:form name="message" class="d-flex flex-column"
					style="flex: 1;" method="post" modelAttribute="message"
					action="${pageContext.request.contextPath}/messaging/insert">
					<section class="bg-warning p-0" style="min-height: 100px;">
						<form:textarea path="messageBody" class="col-12 p-2 m-0"
							style="resize: none; box-shadow: none; outline: none; height: 100%; border-top: 2px solid #202c5a; border-left: 0; border-right: 0; border-bottom: 0;"
							placeholder="Escribe un mensaje o adjunta un archivo"
							id="box-message" />

					</section>
					<div id="show-preview"
						class="bg-white d-flex justify-content-center">
						<!-- <img src="assets/img/blank_default_profile.png"> -->
					</div>
					<section style="background: #f3f6f8; flex: 1; min-height: 50px;"
						class="col-12 p-3 d-flex justify-content-between align-items-center"
						id="botones">
						<div>
							<i id="upload-image-message"
								class="far fa-image fa-lg mr-2 cursor-pointer"></i> <input
								type="file" name="image" accept="image/*" style="display: none;">
							<i class="far fa-smile fa-lg mr-2 cursor-pointer"></i>
						</div>
						<div>
							<form:hidden path="messageThread" value="${messageThread}" />
							<input type="submit" class="btn btn-primary btn-sm rounded-0"
								disabled id="send-new-message" style="box-shadow: none;"
								value="Enviar">

						</div>
					</section>
				</form:form>
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

						//carga ajax
						/*  $.ajax({
							type: 'GET',
							url: '${pageContext.request.contextPath}/messaging/thread/messages/${messageThread}',
							success: function(respuesta) {
								$('#story').html('');
								$('#story').removeClass('d-flex');
								console.log(respuesta);
								if(respuesta.length == 0){
									$('#story').append('Los sentimos, no encontramos la conversacion que buscas.');
									return;
								}
								respuesta.forEach(function(message){
									<c:forEach var="conversation" items="${conversations}">
									if(message.authorIdMessage != "${userSession.userProfileId}"){
										$('#participant-conversation').html(message.authorMessage);
									}
									</c:forEach>
									
									$('#story').append('<jsp:include page="../views/tiles/message_item.jsp" />');
								})
							},
							error: function(jqXHR, textStatus, errorThrown) {
						        console.log(jqXHR.status, textStatus, errorThrown);
						    }
						}); */

						//send message ajax
						$('#message').submit(function(e) {
							e.preventDefault();
							$.ajax({
								type : 'POST',
								data : $("form[name='message']").serialize(),
								url : '${pageContext.request.contextPath}/messaging/insert',
								success : function(messages) {
													/* $('#story').html('');
													if (respuesta.length == 0) {
														$('#story').append('Los sentimos, no se ha podido enviar sumensaje.');
														return;
													} */
									console.log(messages);
									$('#story').append('<jsp:include page="../views/tiles/message_item.jsp" />');
													/* response
													respuesta.forEach(function(message) {
														$('#story').append('<jsp:include page="../views/tiles/message_item.jsp" />');
													}) */
									$('#box-message').val('');
								},
								error : function(jqXHR, textStatus, errorThrown) {
									console.log(jqXHR.status, textStatus, errorThrown);
								}
							});// ajax
						})
						
						
						
					})// ready
</script>
</html>