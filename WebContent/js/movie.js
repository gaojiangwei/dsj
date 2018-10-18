$(function() {
	setStyle('#movie');
	showAllMovie();
});


//单击首页
function onIndex() {
	window.open('index.html', "_self");
}

//单击电视剧
function onTv() {
	window.open('tv.html', "_self");
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

//展示所有电影
function showAllMovie() {
	var str = "";
	$.ajax({
		url: '../vipdy/showAllMovie',
		type: 'post',
		async: true,
		success: function(data) {
			data = $.parseJSON(data);
			for(var i = 0; i < data.length; i++) {
				str += "<li>" +
						"<img src='/image/"+ data[i].spImg +"' onclick='clickMovie(src)'>" +
						"<input type='text' readonly='readonly' value='"+ data[i].spName +"'/>" +
					   "</li>"
				if((i+1) % 3 ==0) {
					str += "<br>"
				}
			}
			$(".all-movie").append(str);
		}
	});
}

//单击电影
function clickMovie(src) {
	imgName = src.split('image/')[1];
	$.ajax({
		url: '../vipdy/getOneMovie',
		type: 'POST',
		async: true,
		data: {
			spImg: imgName
		},
		success: function(data) {
			window.open("movie-xx.html?load="+imgName, "_self");
		}
	});
}

//广告
function goAd() {
	//alert("广告");
}

function setStyle(str) {
	$('.top span').css('color', 'black');
	$('.top span').css('font-weight', '400');
	$('.top span').css('font-size', '16px');
	
	$(str).css('color', '#d62001');
	$(str).css('font-weight', 'bold');
	$(str).css('font-size', '25px');
}