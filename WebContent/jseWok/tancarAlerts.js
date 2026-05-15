$(document).ready(function() {
	
	// Posem la creueta a tots els alerts
	$("p.textError").each(function() {
		$(this).append("<i class='fa fa-times pull-right'></i>");
	});
	
	$("p.textError i").click(function() {
		var nFills = $(this).closest("div.alert-ewok").children("p.textError").length;
		if (nFills > 1) {
			$(this).closest("p.textError").slideToggle(200, function() {
				$(this).remove();
			});
		} else {
			$(this).closest("div.alert-ewok").fadeOut(300, function() {
				$(this).remove();
			});
		}
	});
});
