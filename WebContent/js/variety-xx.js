$(function() {
	imgName = $.query.get("load");
	$.ajax({
		url: '../vipdy/getOneMovie',
		type: 'POST',
		async: true,
		data: {
			spImg: imgName
		},
		success: function(data) {
			data = $.parseJSON(data);
			$("#zybox").attr("src", data);
		}
	});
});