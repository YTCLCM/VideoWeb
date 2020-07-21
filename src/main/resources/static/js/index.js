document.write('<script src="/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>');
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;

};


$(function() {
	var a = $(".videodiv").width();
	$('.videodiv').css('height', a * 0.57);
	var a1 = $(".commenddiv").width();
	$('.commenddiv').css('height', a1* 0.57);
	var a2 = $(".lunbodiv").width();
	$('.lunbodiv').css('height', a2*0.35);
	$(window).resize(function() { // 当浏览器大小变化时
		a = $(".videodiv").width();
		$('.videodiv').css('height', a * 0.57);
		a1 = $(".commenddiv").width();
		$('.commenddiv').css('height', a1* 0.57);
		a2 = $(".lunbodiv").width();
		//$('.lunbodiv').css('height', a2* 0.57);
	});
});

$(window).load(function() {

	$("#flexiselDemo1").flexisel({
		visibleItems: 5,
		animationSpeed: 1000,
		autoPlay: true,
		autoPlaySpeed: 3000,
		pauseOnHover: false,
		enableResponsiveBreakpoints: true,
		responsiveBreakpoints: {
			portrait: {
				changePoint: 480,
				visibleItems: 2
			},
			landscape: {
				changePoint: 640,
				visibleItems: 3
			},
			tablet: {
				changePoint: 800,
				visibleItems: 4
			}
		}
	});
});

$(window).load(function() {
	$('.flexslider').flexslider({
		animation: "slide",
		start: function(slider) {
			$('body').removeClass('loading');
		}
	});
});
jQuery(document).ready(function($) {
	$(".scroll").click(function(event) {
		event.preventDefault();
		$('html,body').animate({
			scrollTop: $(this.hash).offset().top
		}, 1200);
	});
});
addEventListener("load", function() {
	setTimeout(hideURLbar, 0);
}, false);

function hideURLbar() {
	window.scrollTo(0, 1);
};
$(document).ready(function() {
	$(".megamenu").megamenu();
});
$(document).ready(function() {
	$('#horizontalTab').easyResponsiveTabs({
		type: 'default', // Types: default, vertical, accordion
		width: 'auto', // auto or any width like 600px
		fit: true // 100% fit in a container
	});
});
$(document).ready(function() {
	$().UItoTop({
		easingType: 'easeOutQuart'
	});

});

$("#videoupload").click(function(){
	window.open("/RedirectController/videouploadredirect");
});

$("#UserInformationRedirect").click(function(){
	window.open("/RedirectController/UserInformationRedirect");
});

$("#messageRedirect").click(function(){
	window.open("/RedirectController/messageRedirect");
});

$("#videostore").click(function(){
	window.open("/RedirectController/videostoreRedirect");
});

$("#fd").click(function(){
	window.open("/RedirectController/fdRedirect");
});

$('#reload').click(
	function(){
		window.location.reload();
	}		
);

$(document).ready(function(){
	$.ajax({
		url:"/VideoController/videoRecommend",
		async:true,
		dataType:"JSON",
	  	success:function(data){
  		 $.each(data, function(i,val){
  		 	
  		 	var li=$('<li class="nbs-flexisel-item"/>');
  		 	var a1=$('<a class="commenddiv" style="width: 100%;display: block;"/>');
  		 	a1.appendTo(li);
  		 	
  		 	var img=$('<img alt="" style="width: 100%;height: 100%;"/>');
  		 	img.attr("src",this.thumbnailUrl);
  		 	img.appendTo(a1);
  		 	
  		 	var div1=$('<div class="slide-title"/>');
  		 	div1.appendTo(li);
  		 	
  		 	var h4=$('<h4 class="videoInfo"/>');
  		 	h4.html("Top"+(i+1)+": "+val.videoInfo.substring(0,20)+"...");
  		 	h4.appendTo(div1);
  		 	var div2=$('<div class="date-city"/>');
  			div2.appendTo(li);
  			
  		 	var h5=$('<h5 class="videoDate"/>');
  		 	var date=(new Date(this.editDate)).Format("yyyy-MM-dd hh:mm:ss");
			h5.html(date);
  		 	h5.appendTo(div2);
  		 	
  		 	var h6=$('<h5 class="userName"/>');
  		 	h6.html("用户："+val.user.userName);
  		 	h6.appendTo(div2);
  		 	
  		 	var div3=$('<div class="buy-tickets"/>');
  		 	div3.appendTo(div2);
  		 	var a2=$('<a href="#"/>');
  		 	a2.html("前往观看");
  		 	a2.appendTo(div3);
  		 	
  		 	li.appendTo($('#flexiselDemo1'));

			var ul=$('<ul class="mov_list"/>');
			
			var li1=$('<li><i class="fa fa-star"></i></li>');
			li1.appendTo(ul);
			
			var li2=$('<li/>');
			li2.appendTo(ul)
			
			var a3=$('<a/>');
			a3.html("Top"+(i+1)+": "+val.videoTitle.substring(0,11));
			a3.appendTo(li2);
			ul.appendTo($('.topmovies'));
  		 	
			a2.click(function() {
  				 window.open("/VideoController/videoPlay?videoId="+val.videoId);
			});
			a3.click(function() {
 				 window.open("/VideoController/videoPlay?videoId="+val.videoId);
			});
		  	});
		}
	})
});

$.ajax({
	url: "/VideoTypeController/getAllVideoTypeByAjax",
	async: true,
	type: "post",
	dataType: "JSON",
	error: function() {
		window.wxc
			.xcConfirm(
				"失败",
				window.wxc.xcConfirm.typeEnum.error);
	},
	success: function(data1) {
		//window.wxc.xcConfirm("成功",window.wxc.xcConfirm.typeEnum.success);

		$.each(data1,function(i,val) {
			var li=$('<li class="resp-tab-item" role="tab"/>');
			var span=$('<span/>');
			span.appendTo(li);
			
			var a=$('<a href="#section'+(i+1)+'"'+'/>');
			a.html(val.typeName);
			a.appendTo(span);
			
			a.mouseover(function(){
				li.css("background-color","#337ab77a");
			});
			a.mouseout(function(){
				li.css("background-color","rgba(0, 0, 0, .0001)");
			});
			
			li.appendTo($(".menu2-bar"));
			
			var div1=$('<div id="section'+(i+1)+'" '+'class="container-fluid" style="padding: 0;"/>');
			var div11=$('<div class="top-movies-in-india"/>')
			div11.appendTo(div1);
			var h4=$('<h4 style="color:white; float:left;height: auto; width: 100%; padding: 4px 14px; background-color: #003b64;"/>');
			h4.html(val.typeName+'<a id="update'+(i+1)+'"'+'style="float:right;"><span class="glyphicon glyphicon-repeat"></span></a>');
			h4.appendTo(div11);
			
//			var div111=$('<a href="#" style="float:right;"><span class="glyphicon glyphicon-repeat"></span></a>');
//			div111.appendTo(div11);
			
			var div12=$('<div id="videoType'+val.videoTypeId+'"/>');
			div12.appendTo(div1);
			div1.appendTo($('#videoshow'));
			
			
			var videoTypeId=val.videoTypeId;
			$.ajax({
				url: "/VideoController/VideoListByTypeAjax",
				async: true,
				type: "get",
				data:{"videoTypeId":videoTypeId},
				dataType: "JSON",
				error: function() {
					window.wxc.xcConfirm("失败",window.wxc.xcConfirm.typeEnum.error);
				},
				success: function(data1) {
					$.each(data1,function(i,val) {
						var div121=$('<div class="col-sm-3"/>');
						var div1211=$('<div class="view view-first" style="width: 100%; height: auto;"/>');
						div1211.appendTo(div121);
						
						var div1211_a=$('<a class="videodiv" style="width: 100%; display: block;"/>');
						div1211_a.appendTo(div1211);
						
						var div1211_a_img=$('<img class="img-responsive" alt="" style="width: 100%; height: 100%;"/>');
						div1211_a_img.attr("src",this.thumbnailUrl);
						div1211_a_img.appendTo(div1211_a);
						
						var div121_div=$('<div class="tab_desc"/>');
						div121_div.appendTo(div121);
						
						var div121_div_a=$('<a href="#">Look Now</a>');
						div121_div_a.appendTo(div121_div);
						
						var id="#videoType"+videoTypeId;
						div121.appendTo($(id));
				
						div121_div_a.click(function(){
							 window.open("/VideoController/videoPlay?videoId="+val.videoId);
						});
						
						
					});
				}
			});
			
			
			$("#update"+(i+1)).click(function(){
				$.ajax({
					url: "/VideoController/VideoListByTypeAjax",
					async: true,
					type: "get",
					data:{"videoTypeId":videoTypeId},
					dataType: "JSON",
					error: function() {
						window.wxc.xcConfirm("失败",window.wxc.xcConfirm.typeEnum.error);
					},
					success: function(data1,statusxhr) {
						var id="#videoType"+videoTypeId;
						$(id).empty();
						$.each(data1,function(i,val) {
							var div121=$('<div class="col-sm-3"/>');
							var div1211=$('<div class="view view-first" style="width: 100%; height: auto;"/>');
							div1211.appendTo(div121);
							
							var div1211_a=$('<a class="videodiv" style="width: 100%; display: block;"/>');
							div1211_a.appendTo(div1211);
							
							var div1211_a_img=$('<img class="img-responsive" alt="" style="width: 100%; height: 100%;"/>');
							div1211_a_img.attr("src",this.thumbnailUrl);
							div1211_a_img.appendTo(div1211_a);
							
							var div121_div=$('<div class="tab_desc"/>');
							div121_div.appendTo(div121);
							
							var div121_div_a=$('<a href="#">Look Now</a>');
							div121_div_a.appendTo(div121_div);
							
							
							div121.appendTo($(id));
					
							div121_div_a.click(function(){
								 window.open("/VideoController/videoPlay?videoId="+val.videoId);
							});
							
							
						});
					}
				});
				
			});
		});
		var div=$('<div class="clearfix"></div>');
		div.appendTo($(".menu2-bar"));
	}
});
