<%-- <%@ page language="java" contentType="text/html"%> --%>
<%@ page contentType="text/html; charset=UTF-8" %>
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

 .emoji-span{

      font-size: 24px;
      cursor: pointer;
    }

    .emoji-span:hover{
    -webkit-transform: scale(1.5); /* Safari 3-8 */
     -moz-transform: scale(1.5); 
    transform: scale(1.5);
      cursor: pointer;
    }

    .emoji-box{
      width: 30px;
      height: 30px;
      margin: 2.5px;
      display: flex;
      align-items: center;
      justify-content: center;
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
								<img src="${pageContext.request.contextPath}/images/${conversation.participantsConversation[0].userProfilePhotoProfile}" width="56" height="56" class="rounded-circle">
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
		
			<section id="story"	class="bg-white border-bottom py-2  justify-content-center align-items-center" style="flex: 1; overflow: auto;">

				<c:forEach var="messages" items="${messages}">
					<div class="message-container d-flex flex-column pb-4">
						<div style="z-index: 1;" class="px-2 d-flex align-items-start">
							<img src="${pageContext.request.contextPath}/images/${messages.messageAuthorPhotoProfile}" width="40"  height="40" class="rounded-circle message-author-photo">
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
					<section class="bg-warning p-0" style="min-height: 100px; flex: 1;">
						<textarea name="messageBody" class="col-12 p-2 m-0"
							style="resize: none; box-shadow: none; outline: none; height: 100%; border-top: 2px solid #202c5a; border-left: 0; border-right: 0; border-bottom: 0;"
							placeholder="Escribe un mensaje o adjunta un archivo"
							id="box-message" ></textarea>

					</section>
					<div id="show-preview" class="bg-white d-flex justify-content-center">
						<!-- <img src="assets/img/blank_default_profile.png"> -->
					</div>
					<section style="background: #f3f6f8; flex: 1; min-height: 50px;" class="col-12 p-3 d-flex justify-content-between align-items-center" id="botones">
						<div style="position: relative" class="d-flex">
							<i id="upload-image-message" class="far fa-image fa-lg mr-2 cursor-pointer"></i>
							<input type="file" name="image" accept="image/*" style="display: none;">
							<i class="emoji-popup far fa-smile fa-lg mr-2 cursor-pointer"></i>
							
							<section  class="emoji-popover__emojis border bg-white d-none" style="width: 320px; height: 420px; overflow-y: scroll; position: absolute; bottom: 30px; z-index: 1;">
							
							    	<section id="emojis-section-people" class="emoji-popover__emojis-section d-flex flex-wrap justify-content-center align-items-center">
							       
							      		<div class="emoji-box"><span class="emoji-span relative" >😀</span></div> <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sonriendo">😀</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara radiante con ojos sonrientes">😁</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara llorando de risa">😂</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara revolviéndose de la risa">🤣</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sonriendo con ojos grandes">😃</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sonriendo con ojos sonrientes">😄</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sonriendo con sudor frío">😅</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sonriendo con los ojos cerrados">😆</span></div>

 
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara guiñando el ojo">😉</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara feliz con ojos sonrientes">😊</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara saboreando comida">😋</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sonriendo con gafas de sol">😎</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sonriendo con ojos de corazón">😍</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara lanzando un beso">😘</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara">😗</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara besando con ojos sonrientes">😙</span></div>

 
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara besando con ojos cerrados">😚</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sonriendo ligeramente">🙂</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con manos abrazando">🤗</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sonriendo con estrellas">🤩</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara pensativa">🤔</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con ceja alzada">🤨</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara neutral">😐</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sin expresión">😑</span></div>

 
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sin boca">😶</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con ojos en blanco">🙄</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sonriendo con superioridad">😏</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara desesperada">😣</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara triste pero aliviada">😥</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con la boca abierta">😮</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con la boca cerrada con cremallera">🤐</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara estupefacta">😯</span></div>

 
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de sueño">😪</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara cansada">😫</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara durmiendo">😴</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de alivio">😌</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sacando la lengua">😛</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sacando la lengua y guiñando un ojo">😜</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con ojos cerrados y lengua fuera">😝</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara babeando">🤤</span></div>

 
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de desaprobación">😒</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con sudor frío">😓</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara desanimada">😔</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de confusión">😕</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara al revés">🙃</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con lengua de dinero">🤑</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara asombrada">😲</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con el ceño ligeramente fruncido">🙁</span></div>

 
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de frustración">😖</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara decepcionada">😞</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara preocupada">😟</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara resoplando">😤</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara llorando">😢</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara llorando fuerte">😭</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con el ceño fruncido y la boca abierta">😦</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara angustiada">😧</span></div>


 
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara asustada">😨</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara agotada">😩</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cabeza explotando">🤯</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara haciendo una mueca">😬</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con ansiedad y sudor">😰</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara gritando de miedo">😱</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sonrojada">😳</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de loco">🤪</span></div>
  
 
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara mareada">😵</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara cabreada">😡</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara enfadada">😠</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con símbolos en la boca">🤬</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con mascarilla médica">😷</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con termómetro">🤒</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con la cabeza vendada">🤕</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de náuseas">🤢</span></div>
  
 
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara vomitando">🤮</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara estornudando">🤧</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sonriendo con aureola">😇</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con sombrero de vaquero">🤠</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de mentiroso">🤥</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara pidiendo silencio">🤫</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con mano sobre la boca">🤭</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara con monóculo">🧐</span></div>
  
 
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de empollón">🤓</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara sonriendo con cuernos">😈</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara enfadada con cuernos">👿</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de payaso">🤡</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="demonio japonés oni">👹</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="demonio japonés tengu">👺</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="calavera">💀</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="calavera y huesos cruzados">☠</span></div>
  
 
        <div class="emoji-box"><span class="emoji-span relative" arial-label="fantasma">👻</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="alienígena">👽</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="monstruo alienígena">👾</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de robot">🤖</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="caca con ojos">💩</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de gato sonriendo">😺</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de gato sonriendo con ojos sonrientes">😸</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de gato llorando de risa">😹</span></div>
  
 
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de gato sonriendo y ojos de corazón">😻</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de gato con sonrisa irónica">😼</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de gato besando">😽</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de gato asustado">🙀</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de gato llorando">😿</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="cara de gato enfadado">😾</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="mono con los ojos tapados">🙈</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="mono con los oídos tapados">🙉</span></div>
        <div class="emoji-box"><span class="emoji-span relative" arial-label="mono con la boca tapada">🙊</span></div>
     
 
   
      <div class="emoji-box"><span class="emoji-span relative" arial-label="selfi">🤳</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="bíceps flexionado">💪</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="dorso de mano con índice a la izquierda">👈</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="dorso de mano con índice a la derecha">👉</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="dedo índice hacia arriba">☝</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="dorso de mano con índice hacia arriba">👆</span></div>
  
      <div class="emoji-box"><span class="emoji-span relative" arial-label="dedo corazón hacia arriba">🖕</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="dorso de mano con índice hacia abajo">👇</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="mano con señal de victoria">✌</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="dedos cruzados">🤞</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="saludo vulcano">🖖</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="mano haciendo el signo de cuernos">🤘</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="mano haciendo el gesto de llamar">🤙</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="mano abierta">🖐</span></div>
 
      <div class="emoji-box"><span class="emoji-span relative" arial-label="mano levantada">✋</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="señal de aprobación con la mano">👌</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="pulgar hacia arriba">👍</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="pulgar hacia abajo">👎</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="puño en alto">✊</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="puño cerrado">👊</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="puño hacia la izquierda">🤛</span></div>
      <div class="emoji-box"><span class="emoji-span relative" arial-label="puño hacia la derecha">🤜</span></div>
							      
							   
    
  
    								</section>

									
							</section>
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
		
		//init unlock button send messsage
		$('#box-message').keyup(function() {			
			checkCMessage();
		})//end unlock button send messsage

		//open upload image message
		$('#upload-image-message').click(function() {
			$('input[name=image]').trigger('click');
		})

		//init preview load image message
		$('input[name=image]').change(function(e) {

			var reader = new FileReader();
			reader.readAsDataURL(e.target.files[0]);

			reader.onload = function() {
				$('#show-preview').html('<div class="col-12 p-0" style="background-image: url(\''+ reader.result + '\'); height: 275px; background-size: contain; background-position: center; background-repeat: no-repeat;"><span id="close-preview" style="font-size: 32px; display: contents; cursor: pointer;" aria-hidden="true">&times;</span></div>');
				checkCMessage();
			}
		})//end preview load image message

		$('#show-preview').on('click', '#close-preview', function() {
			$('input[name=image]').val('');
			$('#show-preview').html('');
			checkCMessage();

		})

						

						//send message ajax
						$('form[name="message"]').submit(function(e) {
							e.preventDefault();
							var messageRecipient = $('input[name="messageRecipient"]', this).val();
							var formData = new FormData();
							formData.append("messageBody", $('textarea[name="messageBody"]', this).val());
							formData.append("messageThread", $('input[name="messageThread"]', this).val());
							formData.append("messageRecipient", messageRecipient);
							$.ajax({
								url : '${pageContext.request.contextPath}/messaging/insert',
								type : 'POST',
								processData : false,
								contentType : false,
								data : formData,
								
								
								success : function(messages) {													
									console.log(messageRecipient);
									if(messageRecipient!=null && messageRecipient!=''){
										console.log("akii");
										window.location.href = '${pageContext.request.contextPath}/messaging';
									}else{
										$('#story').append('<jsp:include page="../views/tiles/message_item.jsp" />');
										$('#box-message').val('');
									}
									
								},
								error : function(jqXHR, textStatus, errorThrown) {
									console.log(jqXHR.status, textStatus, errorThrown);
								}
							});//send message ajax
						})
						
						
						$('#lookfor-friend-to-send-message').keyup(function(){
							var pattern = $(this).val();
							if(pattern && pattern.length >= 4){
								if(sessionStorage.sessionFriends != null && JSON.parse(sessionStorage.sessionFriends).length > 0 ){
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
									$('#friends-match-to-send-message').html('<span>Aun no tienes contactos</span>');
									$('#friends-match-to-send-message').removeClass('d-none');
								}
								
								
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
						
						
						//init emojis
						$('.emoji-popup').click(function(e){
							e.stopPropagation();
							$('.emoji-popover__emojis').removeClass('d-none');
						})
						
						$('body').click(function(){
							$('.emoji-popover__emojis').addClass('d-none');
						})
						
						$('.emoji-popover__emojis').click(function(e){
							e.stopPropagation();
						})
						
						$('.emoji-span').click(function(e){
							var cMessage = $('#box-message').val();
							$('#box-message').val(cMessage + $(this).html());
							checkCMessage();
						})// end emojis
						
						//init check message content
						function checkCMessage(){
							var cMessage 		= $('#box-message').val();
							var mThread 		= $('input[name="messageThread"]').val();
							var mRecipient 		= $('input[name="messageRecipient"]').val();
							var attchMessage 	= $('input[name=image]')[0].files[0];
							console.log(cMessage);
							if (cMessage != '' && (mThread!=null && mThread!='' || mRecipient!=null && mRecipient!='') ||  attchMessage != null && (mThread!=null && mThread!='' || mRecipient!=null && mRecipient!='')) {								
								$('#send-new-message').removeAttr('disabled');
							} else {
								$('#send-new-message').attr('disabled', true);
							}
						}//end check message content
						
						
						
						
						
						
						
					})// ready
</script>
</html>