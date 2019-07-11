<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.0.slim.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="icon" type="image/vnd.microsoft.icon" href="assets/img/ceep_logo_no_back.ico">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../views/tiles/navbar.jsp" />
<h1>PROFILE</h1>

${userid}
${username}
${userlastname}
${usergender}
${userrole}
${userpais}

</body>
<script type="text/javascript">
	$(document).ready(function(){
		$('#yo').click(function(){
			$('#profile-box').toggle();
		})

		

		$('#close-user-session').hover(function(){
			$(this).addClass('bg-secondary');
			$('a', this).addClass('text-white');
		}, function(){
			$(this).removeClass('bg-secondary');
			$('a', this).removeClass('text-white');
		})

		$('#view-profile').hover(function(){
			$(this).addClass('bg-info');
			$(this).removeClass('text-primary');
			$(this).addClass('text-white');
		}, function(){
			$(this).removeClass('bg-info');
			$(this).removeClass('text-white');
			$(this).addClass('text-primary');
		})

	})
</script>
</html>