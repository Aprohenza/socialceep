<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Social Ceep</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="icon" type="image/vnd.microsoft.icon"
	href="${pageContext.request.contextPath}/assets/img/ceep_logo_no_back.ico">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
<script src="https://code.jquery.com/jquery-3.4.0.js"></script>

<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

.nav-item-pointer {
	cursor: pointer;
}

.cursor-pointer {
	cursor: pointer;
}
</style>
</head>
<body style="background-color: #f5f5f5; position: relative;">
	<jsp:include page="../views/tiles/navbar.jsp" />

	<section style="position: relative; margin-top: 51px;"
		class="container d-flex p-0 py-4">
		<aside style="position: relative;" id="left-rail" class="col-3 p-0">
			<!--bg-warning-->
			<div class="d-flex flex-column border bg-white"
				style="height: 190px; position: fixed;">
				<div
					style="height: 60px; background-image: url(${pageContext.request.contextPath}/images/${uSession.userProfilePhotoCover}); background-size: cover; background-position: center;"
					id="cover"
					class="bg-info feed-identity-module profile-rail-card container ember-view">
					<div
						class="d-flex justify-content-center flex-column align-items-center">
						<img class="rounded-circle mt-4" alt="${uSession.userProfileName} ${uSession.userProfileLastName}" height="64"
							width="64"
							src="${pageContext.request.contextPath}/images/${uSession.userProfilePhotoProfile}">
						<span class="text-center" style="font-weight: 600;">¡Te
							damos la bienvenida, ${uSession.userProfileName}!</span> <span><a
							href="${pageContext.request.contextPath}/profile/${uSession.userProfileId}/">Actualiza
								tu perfil</a></span>
					</div>
				</div>
			</div>
		</aside>

		<main class="col-6 px-4">		
		
			<section class="col-12 p-0 shadows">
				<div class="pre-post-container bg-white col-12 p-3 mb-4">			
					<main class="py-3">
						<div class="col-12 my-1" style="height: 10px; background-color: #5d5b5b2b"></div>
						<div class="col-8 my-1" style="height: 10px; background-color: #5d5b5b2b"></div>
					</main>
				</div>
				<div class="pre-post-container bg-white col-12 p-3 mb-4">
					<header class="d-flex align-items-center bg-white">
						<div class="photo-circle d-flex">
							<div class="rounded-circle" style="width: 60px; height: 60px; background-color: #5d5b5b2b"></div>
						</div>
						<div class="col-12 px-2">
							<div class="col-2 my-1" style="height: 10px; background-color: #5d5b5b2b"></div>
							<div class="col-8 my-1" style="height: 10px; background-color: #5d5b5b2b"></div>
						</div>
					</header>
					<main class="py-3">
						<div class="col-12 my-1" style="height: 10px; background-color: #5d5b5b2b"></div>
						<div class="col-8 my-1" style="height: 10px; background-color: #5d5b5b2b"></div>
					</main>
				</div>
				<div class="pre-post-container bg-white col-12 p-3 mb-4" style="opacity: 0.7">
					<header class="d-flex align-items-center bg-white">
						<div class="photo-circle d-flex">
							<div class="rounded-circle" style="width: 60px; height: 60px; background-color: #5d5b5b2b"></div>
						</div>
						<div class="col-12 px-2">
							<div class="col-2 my-1" style="height: 10px; background-color: #5d5b5b2b"></div>
							<div class="col-8 my-1" style="height: 10px; background-color: #5d5b5b2b"></div>
						</div>
					</header>
					<main class="py-3">
						<div class="col-12 my-1" style="height: 10px; background-color: #5d5b5b2b"></div>
						<div class="col-8 my-1" style="height: 10px; background-color: #5d5b5b2b"></div>
					</main>
				</div>
				<div class="pre-post-container bg-white col-12 p-3 mb-4" style="opacity: 0.5">
					<header class="d-flex align-items-center bg-white">
						<div class="photo-circle d-flex">
							<div class="rounded-circle" style="width: 60px; height: 60px; background-color: #5d5b5b2b"></div>
						</div>
						<div class="col-12 px-2">
							<div class="col-2 my-1" style="height: 10px; background-color: #5d5b5b2b"></div>
							<div class="col-8 my-1" style="height: 10px; background-color: #5d5b5b2b"></div>
						</div>
					</header>
					<main class="py-3">
						<div class="col-12 my-1" style="height: 10px; background-color: #5d5b5b2b"></div>
						<div class="col-8 my-1" style="height: 10px; background-color: #5d5b5b2b"></div>
					</main>
				</div>
			</section>
			
			<section id="new-post" class="cursor-pointer rounded-0 col-12 p-0 bg-white p-4 border d-none">
				<i class="far fa-edit fa-lg"></i> <span class="px-2 text-dark" style="font-weight: 600;">Comienza una publicacion</span>
			</section>

			<section id="posts-container" class="d-none"></section>
		</main>


	</section>

	<section class="d-none justify-content-center align-items-start pt-4"
		id="global"
		style="height: 100%; width: 100%; position: absolute; top: 0; background-color: rgba(0, 0, 0, 0.8);">
		<jsp:include page="../views/tiles/newpost.jsp" />
	</section>



</body>
<script src="${pageContext.request.contextPath}/assets/js/navbar.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

						//open new post box
						$('#new-post').click(function() {
							$('#global').addClass('d-flex');
							//$('#global').load('${pageContext.request.contextPath}/views/tiles/newpost.jsp');
						})

						$('#global').on('click', '#close-new-post', function() {
							$('#global').removeClass('d-flex');
							$('#global').addClass('d-none');
							$('#global #show-preview').html('');
						})

						$('#global').on('click', function() {
							if ($('#global').hasClass('d-flex')) {
								$('#global').removeClass('d-flex');
								$('#global').addClass('d-none');
								$('#global #show-preview').html('');
							}
						})

						$("#global").on('click', '#intro-newpost', function(e) {
							e.stopPropagation();
						});

						//open upload image
						$('#global').on('click', '#upload-image', function() {
							$('input[name=attachment]').trigger('click');
						})

						//open upload file
						$('#global').on('click', '#upload-file', function() {
							$('input[name=file]').trigger('click');
						})

						//preview load image
						$('#global')
								.on(
										'change',
										'input[name=attachment]',
										function(e) {
											var reader = new FileReader();

											reader
													.readAsDataURL(e.target.files[0]);

											reader.onload = function() {
												$('#global #show-preview')
														.append(
																'<div class="d-flex justify-content-center" style="height: 200px;" class="d-flex"><img style="height: 100%;" src="'
																		+ reader.result
																		+ '"/><i style="cursor: pointer;" class="far fa-times-circle fa-lg clean-load-preview"></i></div>');
											}
										})

						//clean load preview
						$('#global').on('click', '.clean-load-preview',
								function() {

									$('#global #show-preview').html('');

								})

						//preview load file
						$('#global')
								.on(
										'change',
										'input[name=file]',
										function(e) {

											extension = $(this).val()
													.split('.').pop()
													+ '.svg';
											console.log(extension);
											var reader = new FileReader();
											name = e.target.files[0].name;
											reader
													.readAsDataURL(e.target.files[0]);
											reader.onload = function() {
												$('#global #show-preview')
														.append(
																'<div class="border rounded p-2" style="display: flex; align-items: center;"><img width="45" src="${pageContext.request.contextPath}/assets/img/extentions/svg/'+extension+'"><div><a href="'+reader.result+'">'
																		+ name
																		+ '</a></div></div>');

											}
										})

						

						//create new post ajax
						$('form[name="newpost"]').submit(function(e) {
							e.preventDefault();

							formData = new FormData();
							formData.append("attachment", $('input[name="attachment"]', this)[0].files[0]);
							formData.append("file", $('input[name="file"]', this)[0].files[0]);
							formData.append("postBody", $('textarea[name="postBody"]', this).val());

							$.ajax({
								url : '${pageContext.request.contextPath}/post/insert',
								type : 'POST',
								processData : false,
								contentType : false,
								data : formData,
								success : function(post) {
									console.log(post);

									/* if (post != null) {
										if (post.postAttachment != null) {
											if (post.postAttachment.attachmentPath == 'files') {
												extension = post.postAttachment.attachmentName.split('.').pop() + '.svg';
												$('#posts-container').append('<jsp:include page="../views/tiles/post_item_with_files.jsp" />');
											} else {
												$('#posts-container').append('<jsp:include page="../views/tiles/post_item.jsp" />');
											}
										} else {
											$("#posts-container").before('<jsp:include page="../views/tiles/post_item_no_attachment.jsp" />');
										}	
									} */

									$('#close-new-post').trigger('click');
								},
								error : function(jqXHR, textStatus, errorThrown) {
									console.log(jqXHR.status, textStatus, errorThrown);
								}
							});
						})

						//init open box to comment and show comments
						$('#posts-container').on('click', '.reaction-new-comment', function() {
							//console.log($(this).parents());
							var target = $(this);
							
							displayPostComments(target);
							
							renderizeCommentsPost(target);
						})//end open box to comment and show comments
						
						$('#display-comments').click(function(){
							var target = $(this);
							displayPostComments(target);
							
							renderizeCommentsPost(target);
						})
						
						function displayPostComments(target){
							$('.comments-container', target.parents()[2]).show();
						}
						
						function renderizeCommentsPost(target){
							var postId = $('.comments-container .new-comment-container .post-new-comment', target.parents()[2]).attr('value')
							console.log("valor del post: " +  postId);
							$('.comments-container .comments', target.parents()[2]).html(function(){
								var html = "";
								
								JSON.parse(sessionStorage.posts).forEach(function(post){
									
									if(post.postId == postId){
										console.log("match");
										if(post.commentPostDto!=null && post.commentPostDto.length>0){
											post.commentPostDto.forEach(function(comment){
												
												html = html + '<div class="comment-container d-flex bg-white py-2"><div><img class="rounded-circle" src="${pageContext.request.contextPath}/images/'+comment.commentAuthorPhotoProfile+'" width="40"></div><div style="flex: 1;" class="px-2"><div style="background-color: #f3f6f8; border-radius: 0 20px 20px 20px;" class="p-2"><div style="font-weight: 600;"><span>'+comment.commentAuthorName+' '+comment.commentAuthorLastName+'</span><small class="px-1">&middot;</small><small>'+comment.commentAuthorRole+'</small></div><div class="pt-2">'+ comment.commentBody +'</div></div></div></div>';	
											})
										}
										
										
										
									}
									
								})
								return html;
								
							})
							
							$('.input-new-comment', target.parents()[0]).html('');
						}

						// mostrar boton publicar nuevo post
						$('#posts-container')
								.on(
										'keyup',
										'.input-new-comment',
										function() {
											//console.log($(this).html());
											//console.log($(this).parents()[2]);
											if ($(this).html() != '') {
												$('.post-new-comment',
														$(this).parents()[2])
														.addClass('d-flex');
												$('.new-comment',
														$(this).parents()[2])
														.removeClass(
																'align-items-center');
												$('.new-comment',
														$(this).parents()[2])
														.addClass(
																'align-items-start');
											} else {
												$('.post-new-comment',
														$(this).parents()[2])
														.removeClass('d-flex');
												$('.new-comment',
														$(this).parents()[2])
														.addClass(
																'align-items-center');
												$('.new-comment',
														$(this).parents()[2])
														.removeClass(
																'align-items-start');
											}
										})

						//init insert new comment's post
						$('#posts-container').on('click', '.post-new-comment', function() {
							//alert('post: ' + $(this).attr('value') + '\n' + $('.input-new-comment', $(this).parents()[0]).html()  );
							var target = $(this);
							$.ajax({
								type : 'POST',
								data : {'postId': target.attr('value'), 'comment' : $('.input-new-comment', target.parents()[0]).html()},
								url : '${pageContext.request.contextPath}/comment/new',
								success : function(response) {
									if (response != null) {
										console.log(response);
										var posts = JSON.parse(sessionStorage.posts);
										posts.forEach(function(post){
											if(post.postId == response.commentPostId){
												post.commentPostDto.push(response);
												sessionStorage.setItem("posts", JSON.stringify(posts));
												renderizeCommentsPost(target);
												
											}
										})
										
										
									}
									
								},
								error : function(jqXHR, textStatus, errorThrown) {
									console.log(jqXHR.status, textStatus, errorThrown);
								}
							});

						})//end insert new comment's post
						

					})//ready
					
	
	
					
					
	if(sessionStorage.posts == null){
			console.log("NO HAY POST EN EL CLIENTE. PREPARANDO PETICION AL SERVER...");
			 if(sessionStorage.sessionFriends != null){
				console.log("HAY FRIENDS EN EL CLIENTE. SOLICITANDO SOLO LOS FEED POST...");
				//evaluar si los friends tienen elementos
				console.log("cantidad de amigos: " + sessionStorage.sessionFriends.length);
				if(JSON.parse(sessionStorage.sessionFriends).length > 0){
					$.ajax({
						type: 'GET',
						url: '${pageContext.request.contextPath}/post-feed/load',
						success: function(respuesta) {},
						error: function(jqXHR, textStatus, errorThrown) {
					       console.log(jqXHR.status, textStatus, errorThrown);
					   }
					})
				}else{
					console.log("LA SESSION NO TIENE AMIGOS, POR LO Q TAMPOCO POST...");
					renderizePost();
				}
				
				
			} else{
				console.log("NO HAY FRIENDS EN EL CLIENTE. SOLICITANDO CARGA COMPLETA...");
				$.ajax({
					type: 'GET',
					url: '${pageContext.request.contextPath}/friends-session/load',
					success: function(respuesta) {},
					error: function(jqXHR, textStatus, errorThrown) {
				       console.log(jqXHR.status, textStatus, errorThrown);
				   }
				})				
			}  
			 
		}else{
			console.log("RENDER POST");
			renderizePost();
		}

	
	function renderizePost(){
		console.log("RENDERIZANDO POST");
		$('.shadows').addClass('d-none');
		$('#new-post').removeClass('d-none');
		$('#posts-container').removeClass('d-none');
		
		var posts;
		if(sessionStorage.posts!=null){
			posts = JSON.parse(sessionStorage.posts);
			
			if(posts.length != 0){
				posts.forEach(function(post) {
					if (post.postAttachment != null) {
						if (post.postAttachment.attachmentPath == 'files') {
							extension = post.postAttachment.attachmentName.split('.').pop() + '.svg';
							$('#posts-container').append('<jsp:include page="../views/tiles/post_item_with_files.jsp" />');							
						} else {
							$('#posts-container').append('<jsp:include page="../views/tiles/post_item.jsp" />');
						}
					} else {
						$('#posts-container').append('<jsp:include page="../views/tiles/post_item_no_attachment.jsp" />');
					}

				})
			}else{
				$('#posts-container').append('<jsp:include page="../views/tiles/post_welcome.jsp" />');
			}
		}else{
			$('#posts-container').append('<jsp:include page="../views/tiles/post_welcome.jsp" />');
		}
		
		//console.log(posts);
		
		
	}
	
	function addNewPost(post){
		console.log(post);
		if (post.postAttachment != null) {
			if (post.postAttachment.attachmentPath == 'files') {
				extension = post.postAttachment.attachmentName.split('.').pop() + '.svg';
				$('#posts-container').prepend('<jsp:include page="../views/tiles/post_item_with_files.jsp" />');
			} else {
				$('#posts-container').prepend('<jsp:include page="../views/tiles/post_item.jsp" />');
			}
		} else {
			$('#posts-container').prepend('<jsp:include page="../views/tiles/post_item_no_attachment.jsp" />');
		}
		
	}
	
	
	$('.give-like').click(function(){
		var target = $(this);
		target.addClass("text-primary");
		var target_post = $(this).attr('post');
		var count;
		console.log(target_post);
		
		console.log('like');
				//update like in local storage
				var posts = JSON.parse(sessionStorage.posts);
				posts.forEach(function(post){
					if(post.postId == target_post && post.iLiked!=true){
						//console.log("true");						
						count = post.postLike;
						count = count + 1;
						post.postLike = count;
						post.iLiked = true;
						sessionStorage.setItem("posts", JSON.stringify(posts));
						renderPostLike(target, count);
						console.log('target: ' + target);
						//persistir en bd
						$.ajax({
							type: 'POST',
							data: {"postId": target.attr('post')},
							url: '${pageContext.request.contextPath}/post/action/like',
							success: function(respuesta) {},
							error: function(jqXHR, textStatus, errorThrown) {
								console.log(jqXHR.status, textStatus, errorThrown);
							}
							
						})
											
					}
				})//foreach
		
		
		
	})
	
	function renderPostLike(target, likes){
		//render like				
		$('.likes-count', target.parents()[2] ).html(" " + likes + " ");	
		
	}
	

	
</script>
</html>