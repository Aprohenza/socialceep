$(document).ready(function() {

	$('#yo').click(function(e) {
		e.stopPropagation();
		$('#profile-box').toggle();
	})

	$('#view-profile').hover(function() {
		$(this).addClass('bg-info');
		$(this).removeClass('text-primary');
		$(this).addClass('text-white');
	}, function() {
		$(this).removeClass('bg-info');
		$(this).removeClass('text-white');
		$(this).addClass('text-primary');
	})

	$('#close-user-session').hover(function() {
		$(this).addClass('bg-secondary');
		$('a', this).addClass('text-white');
	}, function() {
		$(this).removeClass('bg-secondary');
		$('a', this).removeClass('text-white');
	})

	$('body').on('click', function() {
		$('#profile-box').hide();
	})

	$('#profile-box').on('click', function(e) {
		e.stopPropagation();
	})
	
	$('#close-user-session').on('click', function(e) {
		localStorage.clear();
	})

})


	