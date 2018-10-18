$(function() {
	$("#btn2").mouseover(function(){
	    $("#btn2").css("margin-top", "-100px");
	    $("#btn2").css("float", "left");
	  });
	  $("#btn2").mouseout(function(){
		  $("#btn2").css("margin-top", "18px");
		  $("#btn2").css("float", "right");
	  });
	  
	  $("#daka").click(function() {
		  $("#confirm").show();
		  $("#daka").hide();
	  });
	  $("#btn1").click(function() {
		  $("#confirm").hide();
		  $("#daka").show();
	  });
	  $("#close").click(function() {
		  $("#confirm").hide();
		  $("#daka").show();
	  });
	  
	  
	  
	    setInterval(function(){
	        var a = new Date();
	        var b = a.getHours();//小时
	        var minutes = a.getMinutes();//分钟
	        if(minutes<10){
	            minutes = "0"+minutes;
	        }
	        document.getElementById("time").innerHTML = b+":"+minutes;
	    },1000);
});