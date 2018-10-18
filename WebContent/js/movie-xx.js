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
			$("#dybox").attr("src", data);
		}
	});
});


//广告
function goAd() {
	//alert("广告");
}
