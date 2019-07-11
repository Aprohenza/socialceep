<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container d-flex justify-content-center">
	<div class="col-6">
		<section id="intro-newpost" class="bg-white">
			<header class="bg-info p-2 text-right">
				<i class="fas fa-times cursor-pointer" id="close-new-post"></i>
			</header>
			<form:form name="newpost" action="${pageContext.request.contextPath}/UploadFile" enctype="multipart/form-data" method="post">
				<main>
				<div class="d-flex justify-content-center align-items-center">
					<img style="margin-top: -20px"
						src="assets/img/blank_default_profile.png" width="48"
						class="rounded-circle">
				</div>
				
					
					<textarea name="postBody" placeholder="¿Sobre que quieres hablar?"
					style="resize: none; border: none; min-height: 190px; outline: none;"
					class="col-12 p-2"></textarea>

				<div id="show-preview" class="p-2"></div>
				</main>
				<footer
					class="p-2 border-top d-flex justify-content-between align-items-center">
					<div class="d-flex justify-content-center align-items-center">
						<i id="upload-image"
							class="btn fas fa-camera fa-lg mx-2 text-secondary"></i>
							<input type="file" name="attachment" accept="image/*" style="display: none;">
							
						<i id="upload-file" class="btn fas fa-file-alt fa-lg mx-2 text-secondary"></i>
						<input type="file" name="file" accept=".html, .pdf, .txt, .docx, .xml, .java" style="display: none;">
						<i class="btn fas fa-video fa-lg mx-2 text-secondary"></i>
					</div>
					<input type="submit" style="box-shadow: none;"
						class="btn btn-primary rounded-0" value="Publicar">
				</footer>
			</form:form>
		</section>
	</div>
</div>