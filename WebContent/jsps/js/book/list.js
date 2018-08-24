$(function() {
	$(".inner").hover(function() {
		$(this).css("border", "3px solid #aaa");
	}, function() {
		$(this).css("border", "3px solid #ffffff");
	});
});