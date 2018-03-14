$(document).ready(function() {
	$("#languajeDropdownMenuButton a").click(function(e) {
		e.preventDefault(); // cancel the link behaviour
		var languajeSelectedText = $(this).text();
		var languajeSelectedValue = $(this).attr("value");

		$("#btnLanguaje").text(languajeSelectedText);
		window.location.replace('?language=' + languajeSelectedValue);
		return false;
	});
});
