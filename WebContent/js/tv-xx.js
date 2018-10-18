$(function() {
	imgName = $.query.get("load");
	$.ajax({
		url: '../vipdy/getOneTV',
		type: 'POST',
		async: true,
		data: {
			dsjHb: imgName
		},
		success: function(data) {
			data = $.parseJSON(data);
			//播放第一集
			$('#dsjbox').attr("src", data[0].dsjAddr);
			var str = "";
			var url = "";
			for(var i = 0; i < data.length; i++) {
				url = data[i].dsjAddr;
				str += "<li onclick='playTv(\""+url+"\")'>" +
						"<input type='text' readonly='readonly' value='"+ (i+1) +"' onclick='playTv(\""+url+"\")'/>" +
					   "</li>"
			}
			$("#oneTv").append(str);
		}
	});
});

function playTv(src) {
	$('#dsjbox').attr("src", src);
}