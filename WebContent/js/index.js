$(function() {
	setStyle('#index');
	//获取首页电影
	getIndexMovie();
	//获取首页电视剧
	getIndexTv();
	//获取首页综艺
	getIndexVariety();
});

//单击电影
function clickMovie(src) {
	imgName = src.split('image/')[1];
	window.open("movie-xx.html?load="+imgName, "_self");
}

//单击电视剧
function clickTv(src) {
	var imgName = src.split('image/')[1];
	window.open("tv-xx.html?load="+imgName, "_self");
}

//单击综艺
function clickZy(src) {
	var imgName = src.split('image/')[1];
	window.open("variety-xx.html?load="+imgName, "_self");
}

//获取首页电影
function getIndexMovie() {
	$.ajax({
		url: '../vipdy/getIndexMovie',
		type: 'POST',
		async: true,
		success: function(data) {
			data = $.parseJSON(data);
			for(var i = 0; i < data.length; i++) {
				$('#m_img_'+(i+1)).attr("src", "/image/" + data[i].spImg);
				$('#m_title_'+(i+1)).val(data[i].spName);
			}
		}
	});
}

//获取首页电视剧
function getIndexTv() {
	$.ajax({
		url: '../vipdy/getIndexTv',
		type: 'POST',
		async: true,
		success: function(data) {
			data = $.parseJSON(data);
			for(var i = 0; i < data.length; i++) {
				$('#t_img_'+(i+1)).attr("src", "/image/" + data[i].dsjHb);
				$('#t_title_'+(i+1)).val(data[i].dsjName);
			}
		}
	});
}

//获取首页综艺
function getIndexVariety() {
	$.ajax({
		url: '../vipdy/getIndexVariety',
		type: 'POST',
		async: true,
		success: function(data) {
			data = $.parseJSON(data);
			for(var i = 0; i < data.length; i++) {
				$('#v_img_'+(i+1)).attr("src", "/image/" + data[i].spImg);
				$('#v_title_'+(i+1)).val(data[i].spName);
			}
		}
	});
}

function setStyle(str) {
	$('.top span').css('color', 'black');
	$('.top span').css('font-weight', '400');
	$('.top span').css('font-size', '16px');
	
	$(str).css('color', '#d62001');
	$(str).css('font-weight', 'bold');
	$(str).css('font-size', '25px');
}

//单击电影
function onMovie() {
	window.open('movie.html', "_self");
}

//单击电视剧
function onTv() {
	window.open('tv.html', "_self");
}

//单击综艺
function onVariety() {
	window.open('variety.html', "_self");
}


//搜索
function search() {
	var str = "";
	$(".searchAllSp li").remove();
	var searchName = $('#search-name').val();
	$.ajax({
		url: "../vipdy/searchDsj",
		data: {
			dsjName: searchName
		},
		type: 'post',
		async: true,
		success: function(data) {
			
			data = $.parseJSON(data);
			for(var i = 0; i < data.length; i++) {
				str += "<li>" +
						"<img src='/image/"+ data[i].dsjHb +"' onclick='clickTv(src)'>" +
						"<input type='text' readonly='readonly' value='"+ data[i].dsjName +"'/>" +
					   "</li>"
			}
			$(".searchAllSp").append(str);
		}
	});
	
	$.ajax({
		url: "../vipdy/searchSp",
		data: {
			spName: searchName
		},
		type: 'post',
		async: true,
		success: function(data) {
			str = "";
			data = $.parseJSON(data);
			for(var i = 0; i < data.length; i++) {
				str += "<li>" +
						"<img src='/image/"+ data[i].spImg +"' onclick='clickMovie(src)'>" +
						"<input type='text' readonly='readonly' value='"+ data[i].spName +"'/>" +
					   "</li>"
			}
			$(".searchAllSp").append(str);
		}
	});
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

//更多电影
function movieMore() {
	window.open('movie.html', "_self");
}

//更多电视剧
function tvMore() {
	window.open('tv.html', "_self");
}

//更多综艺
function varietyMore() {
	window.open('variety.html', "_self");
}

//第一条广告
function goAd1() {
	//alert("第一条广告");
}

//第2条广告
function goAd2() {
	//alert("第2条广告");
}

//第3条广告
function goAd3() {
	//alert("第3条广告");
}


//返回顶部
function goTop() {
	$('html, body').animate({ scrollTop: 0 }, 'fast');//带动画 
}