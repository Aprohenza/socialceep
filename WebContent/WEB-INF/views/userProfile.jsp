<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.socialceep.session.SessionManager" %>
<%@ page import="com.socialceep.entity.LoginEntity" %>
<%@ page import="com.socialceep.entity.UserEntity" %>
<%@ page import="org.springframework.ui.ModelMap" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>Perfil</title>
	<link href="${pageContext.request.contextPath}/assets/img/flags/flags.css" rel=stylesheet type="text/css">
	<script src="https://code.jquery.com/jquery-3.4.0.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="icon" type="image/vnd.microsoft.icon" href="${pageContext.request.contextPath}/assets/img/ceep_logo_no_back.ico">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
	<style type="text/css">
		*{
			margin: 0px;
			padding: 0px;
		}

		.nav-item-pointer{
			cursor: pointer;
		}
		.cursor-pointer{
			cursor: pointer;
		}
		body{
			font-family: -apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue","Fira Sans",Ubuntu,Oxygen,"Oxygen Sans",Cantarell,"Droid Sans","Apple Color Emoji","Segoe UI Emoji","Segoe UI Emoji","Segoe UI Symbol","Lucida Grande",Helvetica,Arial,sans-serif;
			background-color: #f5f5f5;
		}
	</style>
</head>
<body>
	<jsp:include page="../views/tiles/navbar.jsp" />
	
	<main style="margin-top: 51px;" class="container py-4">
		<div class="row">
		<section id="hero" class="border col-8 p-0 bg-white">
			<div id="hero-container">
				<div id="hero-photo-cover" style="background-image: url('${pageContext.request.contextPath}/images/${userProfile.userProfilePhotoCover}'); background-position: center; background-size: cover; height: 200px;"></div>
				<div id="hero-data" class="px-4" style="position: relative;">
					
					<div id="hero-photo-profile" style="position: absolute; top: -120%;"><img width="160" height="160" src="${pageContext.request.contextPath}/images/${userProfile.userProfilePhotoProfile}" class="rounded-circle"></div>
					<div id="hero-row-data" class="row mx-0" style="margin-top: 100px;">
						<div id="hero-data-left" class="">
							<div class="">
								<div class=""><span id="hero-name" style="font-size: 1.9rem; font-weight: 400; line-height: 1.3;">${userProfile.userProfileName} ${userProfile.userProfileLastName}</span><span style="font-size: 1.3rem" class="text-muted px-2">&middot;</span><span id="hero-role" class="text-muted" style="font-size: 1.3rem;">${userProfile.userProfileRole}</span></div>
								<div id="hero-course" style="font-size: 1.3rem; line-height: 0.9">${userProfile.userProfileCycle}</div>
								<div class="d-flex align-items-center" id="hero-country" style="line-height: 2.4;">
									<div id="hero-country-flag"><img src="${pageContext.request.contextPath}/assets/img/flags/blank.gif" class="flag flag-${userProfile.userProfileNationality.split('-')[1]}"/></div>
									<div id="hero-country-name" class="text-muted" style="font-size: 1.3rem;">${userProfile.userProfileNationality.split("-")[0]}</div>
								</div>
							</div>
							<c:choose>
								<c:when test="${isMe}">
									<div id="raw-btn" class="py-2">
										<div id="hero-profile-edit" class="btn btn-primary border rounded-0">Editar</div>
									</div>									
								</c:when>
								<c:otherwise>
									<div id="raw-btn" class="py-2">
									<c:if test="${!isMyFriend}">
										<div id="new-friend-request-action" userid="${userProfile.userProfileId}" class="btn btn-primary border rounded-0">Conectar</div>
									</c:if>
										
										<div class="btn btn-outline-primary border rounded-0">Enviar mensaje</div>
									</div>
								</c:otherwise>
							</c:choose>
							
						</div><!-- hero data left -->
						<div id="hero-data-right" class="col p-0 d-flex flex-column align-items-end justify-content-center">
							<div id="hero-contact-info">Ver informacion de contacto</div>
							<div id="hero-contacts">Mas de 500 contactos</div>
						</div>	
					</div>		
				</div> <!-- hero data -->
				<div id="hero-presentacion" class="px-4">
					<div class="border-top">
					Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
					tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
					quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
					consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
					cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
					proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>
				</div>				
			</div> <!-- hero container-->
		</section>

		<section class="col-4 border">
			<div>
				Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
				tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
				quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
				consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
				cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
				proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
			</div>
		</section>
	</div>
	</main>
	
	<div id="edit-hero-information" style="z-index: 1; height: 100vh; width: 100%; position: fixed; top: 0; background-color: rgba(0, 0, 0, 0.9);" class="d-none justify-content-center align-items-center">
		
		
		<section class="col-6 p-0 border" id="popup-container">
			<div style="height: 100vh;" class="bg-white">
				<header class="p-2 border-bottom">Editar presentacion</header>
				<main style="height: calc(100vh - 41px - 48px); overflow-y: scroll;" class="">
					<section>
						<div style="position: relative; height: 270px;">
							<div id="photo-profile-cover">
								<div id="photo-profile-cover-back" class="col-12 p-0" style="background-image: url('${pageContext.request.contextPath}/images/${userProfile.userProfilePhotoCover}'); background-size: cover; background-position: center; height: 190px;">
									<div style="cursor: pointer;" class="p-2" id="edit-photo-cover"><i class="fas fa-pencil-alt bg-white p-2 rounded-circle float-right"></i></div>
									<input type="file" name="new-photo-cover" style="display: none;">
								</div>
							</div>
							<div class="d-flex justify-content-center align-items-end" style="position: absolute; top: 100px; margin-left: 30px;" id="photo-profile">
								<img height="160" width="160" class="rounded-circle" src="${pageContext.request.contextPath}/images/${userProfile.userProfilePhotoProfile}">
								<div style="cursor: pointer;" class="p-2" id="edit-photo-profile"><i class="fas fa-pencil-alt bg-white p-2 rounded-circle float-right"></i></div>
								<input type="file" name="new-photo-profile" style="display: none;">
							</div>
						</div>
					</section>

					<div id="form-information" class="p-2">
						<form>
							<div class="d-flex">
								<div style="flex:1;" class="form-group mr-1">
							    	<label style="line-height: 0.3;" for="inputName">Nombre</label>
							    	<input style="box-shadow: none;" type="text" class="form-control rounded-0" id="inputName" aria-describedby="emailHelp" value="${userProfile.userProfileName}">
							  	</div>
							  	<div style="flex:1;" class="form-group ml-1">
							    	<label style="line-height: 0.3;" for="inputLatName">Apellidos</label>
							    	<input style="box-shadow: none;" type="text" class="form-control rounded-0" id="inputLatName" aria-describedby="emailHelp" value="${userProfile.userProfileLastName}">
							  	</div>						  	
						  	</div>

						  	<div class="d-flex" id="contact-information">
						  		<div style="flex:1;" class="form-group mr-1">
							    	<label style="line-height: 0.3;" for="inputEmailProfile">Correo electronico</label>
							    	<input style="box-shadow: none;" type="email" class="form-control rounded-0" name="new-email-profile" id="inputEmailProfile" aria-describedby="Direccion de email" value="${userProfile.userProfileEmail}">
							  	</div>

							  	<div style="flex: 1;" class="ml-1">
							  		<label style="line-height: 0.3;" for="inputEmailProfile">Telefono de contacto</label>
							    	<input style="box-shadow: none;" type="text" class="form-control rounded-0" id="inputPhoneProfile" aria-describedby="Telefono de contacto" value="${userProfile.userProfilePhone}">
							  	</div>
						  	</div>

						  	<div id="country" class="form-group">
						  			<label style="line-height: 0.3;">Pais</label>
							    	<select class="custom-select rounded-0">
										<option value="none" selected>Selecciona pais</option>
									  	<option value="af">Afganistán</option>
										<option value="ax">Aland Islands</option>
										<option value="al">Albania</option>
										<option value="de">Alemania</option>
										<option value="ad">Andorra</option>
										<option value="ao">Angola</option>
										<option value="ai">Anguila</option>
										<option value="ag">Antigua y Barbuda</option>
										<option value="sa">Arabia Saudita</option>
										<option value="dz">Argelia</option>
										<option value="ar">Argentina</option>
										<option value="am">Armenia</option>
										<option value="aw">Aruba</option>
										<option value="au">Australia</option>
										<option value="at">Austria</option>
										<option value="az">Azerbaiyán</option>
										<option value="bs">Bahamas</option>
										<option value="bd">Bangladés</option>
										<option value="bb">Barbados</option>
										<option value="bh">Baréin</option>
										<option value="bz">Belice</option>
										<option value="bj">Benin</option>
										<option value="bm">Bermudas</option>
										<option value="by">Bielorrusia</option>
										<option value="mm">Birmania</option>
										<option value="bo">Bolivia</option>
										<option value="ba">Bosnia y Herzegovina</option>
										<option value="bw">Botsuana</option>
										<option value="br">Brasil</option>
										<option value="bn">Brunéi</option>
										<option value="bg">Bulgaria</option>
										<option value="bf">Burkina Faso</option>
										<option value="bi">Burundi</option>
										<option value="bt">Bután</option>
										<option value="be">Bélgica</option>
										<option value="cv">Cabo Verde</option>
										<option value="kh">Camboya</option>
										<option value="cm">Camerún</option>
										<option value="ca">Canadá</option>
										<option value="ic">Canary Islands</option>
										<option value="qa">Catar</option>
										<option value="td">Chad</option>
										<option value="cl">Chile</option>
										<option value="cn">China</option>
										<option value="cy">Chipre</option>
										<option value="va">Ciudad del Vaticano</option>
										<option value="co">Colombia</option>
										<option value="km">Comoras</option>
										<option value="kp">Corea del Norte</option>
										<option value="kr">Corea del Sur</option>
										<option value="cr">Costa Rica</option>
										<option value="ci">Costa de Marfil</option>
										<option value="hr">Croacia</option>
										<option value="cu">Cuba</option>
										<option value="cw">Curazao</option>
										<option value="dk">Dinamarca</option>
										<option value="dm">Dominica</option>
										<option value="ec">Ecuador</option>
										<option value="eg">Egipto</option>
										<option value="sv">El Salvador</option>
										<option value="ae">Emiratos Árabes Unidos</option>
										<option value="er">Eritrea</option>
										<option value="sk">Eslovaquia</option>
										<option value="si">Eslovenia</option>
										<option value="es">España</option>
										<option value="us">Estados Unidos</option>
										<option value="ee">Estonia</option>
										<option value="et">Etiopía</option>
										<option value="eu">European Union</option>
										<option value="fk">Falkland Islands (Mallvinas)</option>
										<option value="ph">Filipinas</option>
										<option value="fi">Finlandia</option>
										<option value="fj">Fiyi</option>
										<option value="fr">Francia</option>
										<option value="ga">Gabón</option>
										<option value="gm">Gambia</option>
										<option value="ge">Georgia</option>
										<option value="gh">Ghana</option>
										<option value="gi">Gibraltar</option>
										<option value="gd">Granada</option>
										<option value="gr">Grecia</option>
										<option value="gl">Groenlandia</option>
										<option value="gu">Guam</option>
										<option value="gt">Guatemala</option>
										<option value="gg">Guernsey</option>
										<option value="gn">Guinea</option>
										<option value="gq">Guinea Ecuatorial</option>
										<option value="gw">Guinea-Bisáu</option>
										<option value="gy">Guyana</option>
										<option value="ht">Haití</option>
										<option value="hn">Honduras</option>
										<option value="hk">Hong Kong</option>
										<option value="hu">Hungría</option>
										<option value="in">India</option>
										<option value="id">Indonesia</option>
										<option value="iq">Irak</option>
										<option value="ie">Irlanda</option>
										<option value="ir">Irán</option>
										<option value="is">Islandia</option>
										<option value="ky">Islas Caimán</option>
										<option value="ck">Islas Cook</option>
										<option value="fo">Islas Feroe</option>
										<option value="gs">Islas Georgias del Sur y Sandwich del Sur</option>
										<option value="mp">Islas Marianas del Norte</option>
										<option value="tc">Islas Turcas y Caicos</option>
										<option value="vg">Islas Vírgenes Británicas</option>
										<option value="vi">Islas Vírgenes de los Estados Unidos</option>
										<option value="im">Isle of Man</option>
										<option value="il">Israel</option>
										<option value="it">Italia</option>
										<option value="jm">Jamaica</option>
										<option value="jp">Japón</option>
										<option value="je">Jersey</option>
										<option value="jo">Jordania</option>
										<option value="kz">Kazajistán</option>
										<option value="ke">Kenia</option>
										<option value="kg">Kirguistán</option>
										<option value="ki">Kiribati</option>
										<option value="kw">Kuwait</option>
										<option value="la">Laos</option>
										<option value="ls">Lesoto</option>
										<option value="lv">Letonia</option>
										<option value="lr">Liberia</option>
										<option value="ly">Libia</option>
										<option value="li">Liechtenstein</option>
										<option value="lt">Lituania</option>
										<option value="lu">Luxemburgo</option>
										<option value="lb">Líbano</option>
										<option value="mo">Macao</option>
										<option value="mk">Macedonia</option>
										<option value="mg">Madagascar</option>
										<option value="my">Malasia</option>
										<option value="mw">Malaui</option>
										<option value="mv">Maldivas</option>
										<option value="mt">Malta</option>
										<option value="ml">Malí</option>
										<option value="ma">Marruecos</option>
										<option value="mh">Marshall, Islas</option>
										<option value="mq">Martinica</option>
										<option value="mu">Mauricio</option>
										<option value="mr">Mauritania</option>
										<option value="yt">Mayotte</option>
										<option value="fm">Micronesia</option>
										<option value="md">Moldavia</option>
										<option value="mn">Mongolia</option>
										<option value="me">Montenegro</option>
										<option value="ms">Montserrat</option>
										<option value="mz">Mozambique</option>
										<option value="mx">México</option>
										<option value="mc">Mónaco</option>
										<option value="na">Namibia</option>
										<option value="nr">Nauru</option>
										<option value="np">Nepal</option>
										<option value="an">Netherlands Antilles</option>
										<option value="ni">Nicaragua</option>
										<option value="ng">Nigeria</option>
										<option value="nu">Niue</option>
										<option value="nf">Norfolk, Isla</option>
										<option value="no">Noruega</option>
										<option value="nc">Nueva Caledonia</option>
										<option value="nz">Nueva Zelanda</option>
										<option value="ne">Níger</option>
										<option value="om">Omán</option>
										<option value="pk">Pakistán</option>
										<option value="pw">Palaos</option>
										<option value="ps">Palestina</option>
										<option value="pa">Panamá</option>
										<option value="pg">Papúa Nueva Guinea</option>
										<option value="py">Paraguay</option>
										<option value="nl">Países Bajos</option>
										<option value="pe">Perú</option>
										<option value="pn">Pitcairn</option>
										<option value="pf">Polinesia Francesa</option>
										<option value="pl">Polonia</option>
										<option value="pt">Portugal</option>
										<option value="pr">Puerto Rico</option>
										<option value="gb">Reino Unido</option>
										<option value="cf">República Centroafricana</option>
										<option value="cz">República Checa</option>
										<option value="cd">República Democrática del Congo</option>
										<option value="do">República Dominicana</option>
										<option value="cg">República del Congo</option>
										<option value="rw">Ruanda</option>
										<option value="ro">Rumania</option>
										<option value="ru">Rusia</option>
										<option value="eh">Sahara Occidental</option>
										<option value="bl">Saint Barthelemy</option>
										<option value="kn">Saint Kitts and Nevis</option>
										<option value="lc">Saint Lucia</option>
										<option value="mf">Saint Martin</option>
										<option value="vc">Saint Vincent and the Grenadines</option>
										<option value="ws">Samoa</option>
										<option value="as">Samoa Americana</option>
										<option value="sm">San Marino</option>
										<option value="sh">Santa Elena</option>
										<option value="st">Santo Tomé y Príncipe</option>
										<option value="sn">Senegal</option>
										<option value="rs">Serbia</option>
										<option value="sc">Seychelles</option>
										<option value="sl">Sierra Leona</option>
										<option value="sg">Singapur</option>
										<option value="sy">Siria</option>
										<option value="sb">Solomon Islands</option>
										<option value="so">Somalia</option>
										<option value="lk">Sri Lanka</option>
										<option value="sz">Suazilandia</option>
										<option value="za">Sudáfrica</option>
										<option value="sd">Sudán</option>
										<option value="ss">Sudán del Sur</option>
										<option value="se">Suecia</option>
										<option value="ch">Suiza</option>
										<option value="sr">Surinam</option>
										<option value="th">Tailandia</option>
										<option value="tw">Taiwán</option>
										<option value="tz">Tanzania</option>
										<option value="tj">Tayikistán</option>
										<option value="tf">Territorios Australes Franceses</option>
										<option value="tl">Timor Oriental</option>
										<option value="tg">Togo</option>
										<option value="tk">Tokelau</option>
										<option value="to">Tonga</option>
										<option value="tt">Trinidad y Tobago</option>
										<option value="tm">Turkmenistán</option>
										<option value="tr">Turquía</option>
										<option value="tv">Tuvalu</option>
										<option value="tn">Túnez</option>
										<option value="ua">Ucrania</option>
										<option value="ug">Uganda</option>
										<option value="uy">Uruguay</option>
										<option value="uz">Uzbekistán</option>
										<option value="vu">Vanuatu</option>
										<option value="ve">Venezuela</option>
										<option value="vn">Vietnam</option>
										<option value="wf">Wallis y Futuna</option>
										<option value="ye">Yemen</option>
										<option value="dj">Yibuti</option>
										<option value="zm">Zambia</option>
										<option value="zw">Zimbabue</option>
									</select>
							</div>	

						  	<div id="formacion">
						  		<label style="line-height: 0.3;">Formacion</label>
								<div class="form-group mr-1">
							    	<select class="custom-select rounded-0">
									  <option selected>Selecciona tu ciclo de formacion</option>
									  <option value="1">Desarrollo de aplicaciones multiplataformas</option>
									  <option value="2">Higiene bucodental</option>
									  <option value="3">Enfermeria</option>
									</select>
							  	</div>	
							  	<div class="d-flex">
									<select name="grade" style="flex: 1;" class="custom-select rounded-0 mr-1">
										<option selected>Selecciona tu grado</option>
									  	<option value="1">1º</option>
									  	<option value="2">2º</option>
									  	<option value="3">Finalizado</option>
									</select>
								  	<select name="session" style="flex: 1;" class="custom-select rounded-0 ml-1">
									 	<option selected>Selecciona tu sesion</option>
									  	<option value="1">Presencial manyana</option>
									  	<option value="2">Presencial tarde</option>
									  	<option value="3">Online</option>
									</select>
									<select name="year-finished" style="flex: 1;" class="custom-select rounded-0 ml-1 d-none">
									 	<option selected>Anio de graduacion</option>
									  	<option value="2019">2019</option>
									  	<option value="2018">2018</option>
									  	<option value="2017">2017</option>
									</select>					  	
							  	</div>					  					  	
						  	</div>

						  	<div style="box-shadow: none;" class="form-group">
								<label style="line-height: 0.3;" for="inputPresentacion">Presentacion</label>
								<textarea style="box-shadow: none;" class="form-control rounded-0" id="inputPresentacion" rows="3"></textarea>
							</div>				  
						</form>
					</div>
				</main>
				<footer class="p-2 border-top">
					<div class="d-flex justify-content-end">
						<button id="save-profile-information" class="btn btn-primary btn-sm rounded-0">Guardar</button>
					</div>
				</footer>
			</div>
		</section>
	</div>
</body>
<script src="${pageContext.request.contextPath}/assets/js/navbar.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		//INI - JS PARA VISTA EDITAR PERFILE
		$('#hero-profile-edit').click(function(){
			$('#edit-hero-information').addClass('d-flex');
		})
		
		$('#edit-hero-information').click(function(){
			$(this).removeClass('d-flex');
		})

		$('#popup-container').click(function(e){
			e.stopPropagation();
		})
		
		$('select[name="grade"]').change(function(){
			if ($(this).val() == 3) {
				$('select[name="year-finished"]').addClass('d-block');
				$('select[name="session"]').addClass('d-none');
			}else{
				$('select[name="year-finished"]').removeClass('d-block');
				$('select[name="session"]').removeClass('d-none');	
			}
		})

		$('#edit-photo-profile').click(function(){
			$('input[name="new-photo-profile"]').trigger('click');

			$('input[name="new-photo-profile"]').change(function(e){
				var reader = new FileReader();
				reader.readAsDataURL(e.target.files[0]);
				reader.onload = function() {
					$('#photo-profile img').attr("src", reader.result);
				}
			})
		})

		$('#edit-photo-cover').click(function(){
			$('input[name="new-photo-cover"]').trigger('click');

			$('input[name="new-photo-cover"]').change(function(e){
				var reader = new FileReader();
				reader.readAsDataURL(e.target.files[0]);
				reader.onload = function() {
					$('#photo-profile-cover>#photo-profile-cover-back').css({'background-image': 'url("'+reader.result+'")'});
				}
			})
		})
		
		// guardar edicion profile
		$('#save-profile-information').click(function(e) {
			e.preventDefault();

			formData = new FormData();
			formData.append("photoProfile", $('input[name="new-photo-profile"]')[0].files[0]);
			formData.append("photoProfileCover", $('input[name="new-photo-cover"]')[0].files[0]);
			formData.append("emailProfile", $('input[name="new-email-profile"]').val());
			formData.append("profileNationality", $('#country>select>option:selected').text() + '-' + $('#country>select').val());
			//formData.append("postBody", $('textarea[name="postBody"]', this).val());

			$.ajax({
				url : '${pageContext.request.contextPath}/profile/update',
				type : 'POST',
				processData : false,
				contentType : false,
				data : formData,
				success : function(response) {
					console.log(response);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR.status, textStatus, errorThrown);
				}
			});
		})//FIN - JS PARA VISTA EDITAR PROFILE
		
		//SEND NEW FRIEND REQUEST
		$('#new-friend-request-action').click(function(){
			var userid = $(this).attr('userid');
			fD = new FormData();
			fD.append("userid", userid);
			$.ajax({
				url : '${pageContext.request.contextPath}/mynetwork/sendNewFriendRequest',
				type : 'POST',
				processData : false,
				contentType : false,
				data : fD,
				success : function(jqXHR) {
					console.log(jqXHR.status);
					$('#new-friend-request-action').addClass('d-none');
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR.status, textStatus, errorThrown);
				}
			});
			
		})//END - SEND NEW FRIEND REQUEST
		
		
		$('#country select option').each(function(){
			if($(this).val() == '${userProfile.userProfileNationality.split("-")[1]}'){
				$(this).attr('selected', true);
			}
		})
		
		

	})// READY
</script>
</html>