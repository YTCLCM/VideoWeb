lastScrollY = 0;

function heartBeat() {
	var a;
	if(document.documentElement && document.documentElement.scrollTop) {
		a = document.documentElement.scrollTop
	} else {
		if(document.body) {
			a = document.body.scrollTop
		} else {}
	}
	percent = 0.1 * (a - lastScrollY);
	if(percent > 0) {
		percent = Math.ceil(percent)
	} else {
		percent = Math.floor(percent)
	}
	document.getElementById("faiscoService").style.top = parseInt(document.getElementById("faiscoService").style.top) + percent + "px";
	lastScrollY = lastScrollY + percent
}
$(function() {
	var a = 0;
	if(typeof _serviceHide != "undefined" && _serviceHide) {
		a = -131
	}
	suspendcode = ["<div id='faiscoService' class='serviceBox' style='right:" + a + "px; position:absolute; top:200px;'>", "<a hidefocus='true' class='serviceTitle'>", "QQ咨询", "</a>", "<div class='serviceText' onclick='window.open(\"http://sc.chinaz.com\")'>", "QQ:259829814", "</div>", "<a hidefocus='true' class='oemServiceTitle' href='javascript:;'>", "电话咨询", "</a>", "<div class='oemServiceText'>", "Tel:13576921363", "</div>", "</div>"];
	$(suspendcode.join("")).appendTo("body");
	window.setInterval("heartBeat()", 1);
	$("#faiscoService").mouseover(function() {
		$("#faiscoService").animate({
			right: 0
		}, {
			queue: false,
			duration: 500
		})
	}).mouseleave(function() {
		$("#faiscoService").animate({
			right: -131
		}, {
			queue: false,
			duration: 500
		})
	})
});
cusSayScroll = function(j) {
	var e = {
		pauseDuration: 2000,
		showDuration: 800,
		scrollMode: "up"
	};
	var b = e;
	if(j) {
		b = $.extend({}, e, j)
	}
	var h = $("#cusSay").children("div.say-item-parent"),
		i = b.pauseDuration,
		g = b.showDuration,
		f = b.scrollMode,
		a = "alwayssay";
	h.mouseover(function() {
		Fai.stopInterval(a)
	}).mouseout(function() {
		Fai.startInterval(a)
	});

	function c() {
		function k() {
			if(f == "up") {
				var l = h.children("div.say-item").first().height();
				h.animate({
					top: "-=" + l
				}, g, function() {
					var m = h.children("div.say-item").first();
					m.appendTo(h).end().hide().fadeIn(400);
					h.css({
						top: 0
					});
					d()
				})
			} else {
				var l = h.children("div.say-item").last().height();
				h.animate({
					top: "+=" + l
				}, g, function() {
					var m = h.children("div.say-item").last();
					m.insertBefore(h.children("div.say-item").first()).end().hide().fadeIn(400);
					h.css({
						top: 0
					});
					d()
				})
			}
		}
		Fai.addTimeout(a, k, i);
		Fai.startInterval(a)
	}

	function d() {
		c()
	}
	c()
};
logDog = function(b, a) {
	$.ajax({
		type: "POST",
		url: "http://" + portalHost + "/_dogonly.jsp?di=" + Fai.encodeUrl(b) + "&ds=" + Fai.encodeUrl(a),
		dataType: "jsonp"
	})
};