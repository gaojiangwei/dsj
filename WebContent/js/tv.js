$(function() {
	setStyle('#tv');
	showAllTv();
});


//展示所有电视剧
function showAllTv() {
	var str = "";
	$.ajax({
		url: '../vipdy/showAllTv',
		type: 'post',
		async: true,
		success: function(data) {
			data = $.parseJSON(data);
			for(var i = 0; i < data.length; i++) {
				str += "<li>" +
						"<img src='/image/"+ data[i].dsjHb +"' onclick='clickTv(src)'>" +
						"<input type='text' readonly='readonly' value='"+ data[i].dsjName +"'/>" +
					   "</li>"
				if((i+1) % 3 ==0) {
					str += "<br>"
				}
			}
			$(".all-tv").append(str);
		}
	});
}

//单击电视剧
function clickTv(src) {
	imgName = src.split('image/')[1];
	window.open("tv-xx.html?load="+imgName, "_self");
}


//单击首页
function onIndex() {
	window.open('index.html', "_self");
}

//单击电影
function onMovie() {
	window.open('movie.html', "_self");
}

//单击综艺
function onVariety() {
	window.open('variety.html', "_self");
}

//更多视频
var i = 1;
function getMore() {
	if(i % 2 != 0) {
		$("#gdsp").show();
	}else {
		$("#gdsp").hide();
	}
	i++;
}






function setStyle(str) {
	$('.top span').css('color', 'black');
	$('.top span').css('font-weight', '400');
	$('.top span').css('font-size', '16px');
	
	$(str).css('color', '#d62001');
	$(str).css('font-weight', 'bold');
	$(str).css('font-size', '25px');
}