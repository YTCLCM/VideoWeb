$('#reload').click(function() {
	window.location.reload();
});

var allMsg_signal = false;
var privateMsg_signal = false;
var systemMsg_signal = false;
var suportNotice_signal = false;
var evaluateNotice_signal = false;
var collectionNotice_signal = false;

$(document).ready(function() {
	$("#allMsgPage").show();
	$("#privateMsgPage").hide();
	$("#systemMsgPage").hide();
	$("#suportNoticePage").hide();
	$("#evaluateNoticePage").hide();
	$("#collectionNoticePage").hide();
	myAjax("/MsgController/getMsgListByAjax", "allMsgPage", "allMsgPage", "All");
	allMsg_signal = true;
});
$("#allMsgButton").click(function() {
	$("#allMsgPage").show();
	$("#privateMsgPage").hide();
	$("#systemMsgPage").hide();
	$("#suportNoticePage").hide();
	$("#evaluateNoticePage").hide();
	$("#collectionNoticePage").hide();
	if (allMsg_signal != true) {
		allMsg_signal = true;
		myAjax("/MsgController/getMsgListByAjax", "allMsgPage", "allMsgPage", "All");
	}
});
$("#privateMsgButton").click(function() {
	$("#privateMsgPage").show();
	$("#allMsgPage").hide();
	$("#systemMsgPage").hide();
	$("#suportNoticePage").hide();
	$("#evaluateNoticePage").hide();
	$("#collectionNoticePage").hide();
	if (privateMsg_signal != true) {
		privateMsg_signal = true;
		myAjax("/MsgController/getMsgListByAjax", "privateMsgPage", "privateMsgPage", "PrivateMsg");
	}
});
$("#systemMsgButton").click(function() {
	$("#systemMsgPage").show();
	$("#allMsgPage").hide();
	$("#privateMsgPage").hide();
	$("#suportNoticePage").hide();
	$("#evaluateNoticePage").hide();
	$("#collectionNoticePage").hide();
	if (systemMsg_signal != true) {
		systemMsg_signal = true;
		myAjax("/MsgController/getMsgListByAjax", "systemMsgPage", "systemMsgPage", "SystemMsg");
	}
});
$("#suportNoticeButton").click(function() {
	$("#suportNoticePage").show();
	$("#allMsgPage").hide();
	$("#privateMsgPage").hide();
	$("#systemMsgPage").hide();
	$("#evaluateNoticePage").hide();
	$("#collectionNoticePage").hide();
	if (suportNotice_signal != true) {
		suportNotice_signal = true;
		myAjax("/MsgController/getMsgListByAjax", "suportNoticePage", "suportNoticePage", "SuportNotice");
	}
});

$("#evaluateNoticeButton").click(function() {
	$("#evaluateNoticePage").show();
	$("#allMsgPage").hide();
	$("#privateMsgPage").hide();
	$("#systemMsgPage").hide();
	$("#suportNoticePage").hide();
	$("#collectionNoticePage").hide();
	if (evaluateNotice_signal != true) {
		evaluateNotice_signal = true;
		myAjax("/MsgController/getMsgListByAjax", "evaluateNoticePage", "evaluateNoticePage", "EvaluateNotice");
	}
});

$("#collectionNoticeButton").click(function() {
	$("#collectionNoticePage").show();
	$("#allMsgPage").hide();
	$("#privateMsgPage").hide();
	$("#systemMsgPage").hide();
	$("#suportNoticePage").hide();
	$("#evaluateNoticePage").hide();
	if (collectionNotice_signal != true) {
		collectionNotice_signal = true;
		myAjax("/MsgController/getMsgListByAjax", "collectionNoticePage", "collectionNoticePage", "CollectionNotice");
	}
});

function myAjax(url, showpage_id, id_identify_prefix, msgTypeName) {
	$.ajax({
		url : url + "?msgTypeName=" + msgTypeName,
		async : true,
		type : "get",
		dataType : "JSON",
		error : function() {
			window.wxc.xcConfirm("失败", window.wxc.xcConfirm.typeEnum.error);
		},
		success : function(data) {
			// alert("成功");
			$.each(data, function(i, val) {
				var div1 = $('<div class="mydiv container-fluid"'+'id ="msg_'+id_identify_prefix+val.msgId+'" style="background-color: white;margin:5px 0;border: rgba(0,0,0,.125) solid 1px; "/>');
				
				
				
				var divplus=$('<div class="container-fluid" style="padding:0; width:90%; float:left;"/>');
				divplus.appendTo(div1);
				
				var div11 = $('<div style="padding: 20px 10% 0 20px;"/>');
				div11.appendTo(divplus);
				var span1_div11 = $('<span/>');
				span1_div11.appendTo(div11);
				span1_div11.html(this.msgTitle);

				var span2_div11 = $('<span style="margin-left: 10px;color: #CFCFCF;"/>');
				span2_div11.appendTo(div11);
				span2_div11.html(this.msgSendDate);

				var div12 = $('<div style="padding: 6px 10% 16px 20px;text-indent:2em; "/>');
				div12.appendTo(divplus);
				var span1_div12 = $('<span />');
				span1_div12.appendTo(div12);
				span1_div12.html(this.msgContext);

				var span2_div12 = $('<span/>');
				span2_div12.appendTo(div12);

				var a_span2_div12 = $('<a/>')

				a_span2_div12.appendTo(span2_div12);
				
				var msg_id=this.msgId;
				
				var dov_id="#msg_"+id_identify_prefix + val.msgId;
				
				//未读的信息添加读取的事件
				if (val.msgState.stateId == 6) {
					a_span2_div12.html("确认读取>>");
					var tip = $('<div style="float:right;" '+'id ="msg_'+id_identify_prefix+'_del'+val.msgId+'" class="tip"/>');
					tip.appendTo(div1);
					a_span2_div12.click(function(){
						a_span2_div12.html("已读");
						$.ajax({
							url : "/MsgController/updateMsgState?msgId=" + msg_id,
							async : true,
							type : "get",
							dataType : "JSON",
							error : function() {
								window.wxc.xcConfirm("失败", window.wxc.xcConfirm.typeEnum.error);
							},
							success : function(data) {
								tip.remove();
								//$(dov_id).remove();
								window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.success);
								
								a_span2_div12.html("已读");
								var del_a=$('<a '+'id ="msg_'+id_identify_prefix+'_del'+val.msgId+'" class="del" href="#"/>');
								var del_span = $(' <span class="glyphicon glyphicon-remove "></span>');
								del_span.appendTo(del_a);
								del_a.appendTo(div1);
								
								var id="#msg_"+id_identify_prefix+val.msgId;
								var id_height = $(id).height();
								var meg_del="#msg_"+id_identify_prefix+"_del"+val.msgId;
								var meg_del_height = $(meg_del).height();
								$(meg_del).css("top",(id_height-meg_del_height)/2);
								$(window).resize(function() { // 当浏览器大小变化时
									id="#msg_"+id_identify_prefix+val.msgId;
									id_height = $(id).height();
									meg_del="#msg_"+id_identify_prefix+val.msgId;
									meg_del_height = $(meg_del).height();
									$(meg_del).css("top",(id_height-meg_del_height)/2);
								});
								
								del_a.click(function(){
									var r = confirm("亲，您确定取消该信息吗？")
									if(r==true){
										$.ajax({
											url : "/MsgController/delMsgByAjax?msgId=" + msg_id,
											async : true,
											type : "get",
											dataType : "JSON",
											error : function() {
												window.wxc.xcConfirm("失败", window.wxc.xcConfirm.typeEnum.error);
											},
											success : function(data) {
												$(dov_id).remove();
												//window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.success);							
											}
										});
									}
								});
							}
							
						});
						
					});
				} else {			
					a_span2_div12.html("已读");
					var del_a=$('<a '+'id ="msg_'+id_identify_prefix+'_del'+val.msgId+'"  class="del" href="#"/>');
					var del_span = $(' <span class="glyphicon glyphicon-remove "></span>');
					del_span.appendTo(del_a);
					del_a.appendTo(div1);
					del_a.click(function(){
						var r = confirm("亲，您确定取消该信息吗？")
						if(r==true){
							$.ajax({
								url : "/MsgController/delMsgByAjax?msgId=" + msg_id,
								async : true,
								type : "get",
								dataType : "JSON",
								error : function() {
									window.wxc.xcConfirm("失败", window.wxc.xcConfirm.typeEnum.error);
								},
								success : function(data) {
									$(dov_id).remove();
									//window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.success);							
								}
							});
						}
					});
				}
				div1.appendTo("#" + showpage_id);
				
				var id="#msg_"+id_identify_prefix+val.msgId;
				var id_height = $(id).height();
				var meg_del="#msg_"+id_identify_prefix+"_del"+val.msgId;
				var meg_del_height = $(meg_del).height();
				$(meg_del).css("top",(id_height-meg_del_height)/2);
				$(window).resize(function() { // 当浏览器大小变化时
					id="#msg_"+id_identify_prefix+val.msgId;
					id_height = $(id).height();
					meg_del="#msg_"+id_identify_prefix+val.msgId;
					meg_del_height = $(meg_del).height();
					$(meg_del).css("top",(id_height-meg_del_height)/2);
				});
			});
		}
	})
};