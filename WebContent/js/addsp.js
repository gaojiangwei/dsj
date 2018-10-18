$(function(){
	getAddrVal("spAddrBm");
	getAddrVal("addrId");
	//更新视频解析地址
	updateAddr();
	
	$("input[type=radio][name=dsj]").click(function() {
		dsjClick($(this).val());
	});
	
	getAllDsj();
});

//获取所有电视剧
function getAllDsj() {
	var str = "";
	$.ajax({
		url: '../vipdy/getAllDsj',
		type: 'POST',
		success: function(data) {
			data = $.parseJSON(data);
			for(var i = 0; i < data.length; i++) {
				str += "<option value='"+ data[i].dsjId +"'>"+ data[i].dsjName +"</option>";
			}
			$('#selectDsj option:not(:first)').remove();
			$("#selectDsj").append(str);
		}
	});
}

function dsjClick(text) {
	if(text == "电视剧") {
		//新增电视剧
		$("#addDsj").show();
		$("#addJs").hide();
	}else if(text == "集数") {
		//新增集数
		$("#addDsj").hide();
		$("#addJs").show();
	}
}

//新增电视剧
function commitDsj() {
	var file = document.getElementById('dsjHb');
	if(file.files[0] == undefined){  
        alert('未上传文件！');  
    }else{
    	var formData = new FormData($("#addDsj")[0]); 
		$.ajax({
	         url: '../vipdy/addDsj' ,  
	         type: 'POST',  
	         data: formData,  
	         async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false,  
	         success: function(data) {
 				alert(data);
 			} 
	    });
		getAllDsj();
    }
}

//新增集数
function commitJs() {
	var options=$("#selectDsj option:selected");  //获取选中的电视剧
	var dsjId = options.val(); 
	var dsjAddr = $("#jsAddr").val();
	$.ajax({
		url: '../vipdy/addJs',
		data: {
			dsjId: dsjId,
			dsjAddr: dsjAddr
		},
		type: 'post',
		success: function(data) {
			alert(data);
		}
	});
}


//获取列表值
var select = "";
function getSelectVal() {
	var options=$("#select option:selected");  //获取选中的项
	select = options.val(); 
}

//视频解析地址信息保存
function addrCommit() {
	var jx_name = $("#jx_name").val();
	var jx_addr = $("#jx_addr").val();
	$.ajax({
		url: '../vipdy/addrcommit',
		type: 'POST',
		data: {
			jxName: jx_name,
			jxAddr: jx_addr
		},
		async: true,
		success: function(data) {
			alert(data);
		}
	});
	getAddrVal("spAddrBm");
	updateAddr();
}

//解析地址获取 	<option value="0">电影</option>
function getAddrVal(addrId) {
	var str = "";
	$.ajax({
		url: '../vipdy/getAddrVal',
		type: 'POST',
		success: function(data) {
			data = $.parseJSON(data);
			for(var i = 0; i < data.length; i++) {
				str += "<option value='"+ data[i].addrId +"' title='"+ data[i].jxAddr +"'>"+ data[i].jxName +"</option>";
			}
			$('#'+addrId+' option:not(:first)').remove();
			$("#"+addrId).append(str);
		}
	});
}

//获取解析地址列表值
var addrId = "";
function getAddrId() {
	var options=$("#jx-addr option:selected"); 
	addrId = options.val(); 
}


//视频信息提交
function toCommit() {
	var file = document.getElementById('spImg');
	if(file.files[0] == undefined){  
        alert('未上传文件！');  
    }else{
        //获取上传文件的文件名  
        spImg = file.files[0].name;
        if(select == -1) {
    		alert("请选择视频类型");
    	}else {
    		var formData = new FormData($("#sp-form")[0]); 
    		$.ajax({
    	         url: '../vipdy/insertSpInfo' ,  
    	         type: 'POST',  
    	         data: formData,  
    	         async: false,  
    	         cache: false,  
    	         contentType: false,  
    	         processData: false,  
    	         success: function(data) {
     				alert(data);
     			} 
    	    });  
    	}
    }
	updateAddr();
}

//更新视频解析地址
function updateAddr() {
	getAddrVal("upd-addr");
}
function getUpdAddrId() {
	var options=$("#upd-addr option:selected"); 
	addrId = options.val(); 
	addr = options.attr("title");
	$("#addr-val").val(addr);
}
function toUpdate() {
	addr = $('#addr-val').val();
	var options=$("#upd-addr option:selected");
	$.ajax({
		url: '../vipdy/toUpdate',
		type: 'POST',
		data: {
			jxAddr: addr,
			addrId: addrId
		},
		success: function(data) {
			alert(data);
		}
	});
}