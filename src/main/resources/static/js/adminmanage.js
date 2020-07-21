function userAjax(url, idName , curPage) {
	$.ajax({
		url : url,
		async : true,
		type : "get",
		data:{"curPage":curPage},
		dataType : "JSON",
		error : function() {
			//window.wxc.xcConfirm("操作失败", window.wxc.xcConfirm.typeEnum.success);
		},
		success : function(result) {
			
			$.each(result.data, function(i, val) {			
				
				var userId = val.userId;
				
				var tr = $('<tr id ="user'+userId+'"/>');
				tr.appendTo($(idName));
				
				var td1 = $('<td/>');
				td1.appendTo(tr);
				td1.html(val.userId);
				
				var td2 = $('<td/>');
				td2.appendTo(tr);
				td2.html(val.userName);
				
				var td3 = $('<td/>');
				td3.appendTo(tr);
				td3.html(val.userAge);
				
				var td4 = $('<td/>');
				td4.appendTo(tr);
				td4.html(val.userSex);
				
				var td5 = $('<td/>');
				td5.appendTo(tr);
				td5.html(val.userMail);
				
				var td6 = $('<td/>');
				td6.appendTo(tr);
				td6.html(val.userPhone);
				
				var td7 = $('<td/>');
				td7.appendTo(tr);
				td7.html(val.userAddress);
				
				var td8 = $('<td/>');
				td8.appendTo(tr);
				td8.html(val.userRole.userRole);
				
				var td9 = $('<td/>');
				td9.appendTo(tr);
				td9.html((new Date(val.registerDate)).Format("yyyy-MM-dd hh:mm:ss"));
				
				var td10 = $('<td/>');
				td10.appendTo(tr);
				td10.html(val.state.stateName);
				
				var td11 = $('<td/>');
				td11.appendTo(tr);
				
				var button1=$('<button class="btn btn-primary btn-sm updateUser" disabled="disabled" data-toggle="modal" data-target="#updateUser">修改</button>');
				var button2=$('<button class="btn btn-primary btn-sm delUser" disabled="disabled" data-toggle="modal" data-target="#delUser">删除</button>');
				
				button1.appendTo(td11);
				button2.appendTo(td11);
				
				//用户修改
				button1.click(function(){
					$.ajax({
						type : "get",
						url : "/AdminUserController/getUserByUserId",
						data:{"userId":userId},
						async : true,
						dataType : "JSON",
						error : function(data) {
							window.wxc.xcConfirm("上传失败", window.wxc.xcConfirm.typeEnum.error);
						},
						success:function(result){
							//userId
							$('#userUpdateId').attr("value",userId);
							//userName
							$('#userName').attr("value",result.data.userName);
							//userAge
							$('#userAge').attr("value",result.data.userAge);
							//userSex
							$('#userSex').attr("value",result.data.userSex);
							//userMail
							$('#userMail').attr("value",result.data.userMail);
							//userPhone
							$('#userPhone').attr("value",result.data.userPhone);
							//userAddress
							$('#userAddress').attr("value",result.data.userAddress);
							//registerDate
							$('#registerDate').attr("value",(new Date(result.data.registerDate)).Format("yyyy-MM-dd hh:mm:ss"));
							//userRole
							$('#userRole').attr("value",result.data.userRole.userRole);
							//state
							$('#state').attr("value",result.data.state.stateName);
						}
					});//ajax
				});//click
				
				//用户删除
				button2.click(function(){
					$('#userId').attr("value",userId);
					$("#delUserSubmit").click(function() {
						$.ajax({
							type : "post",
							url : "/AdminUserController/deleteUserByAjax",
							async : true,
							data : $('#delUserForm').serialize(),
							dataType : "JSON",
							error : function(data) {
								window.wxc.xcConfirm("上传失败", window.wxc.xcConfirm.typeEnum.error);
							},
							success : function(result) {
								if(result.message=="用户删除成功"){
									tr.remove();
									window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.success);
								}else{
									window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.error);
								}		
							}//success:function
						});//ajax

					});
				});
			});

			$.Pagination(result.curPage,result.totalNum,".userPaginationPosition",".userTable",url,userAjax);
		}
	});
}
$(function(){
	userAjax("/AdminUserController/getUserByAjax",".userTable",1);
});




function videoAjax(url, idName , curPage) {
	$.ajax({
		url : url,
		async : true,
		type : "get",
		data:{"curPage":curPage},
		dataType : "JSON",
		error : function() {
			//window.wxc.xcConfirm("操作失败", window.wxc.xcConfirm.typeEnum.success);
		},
		success : function(result) {
			
			$.each(result.data, function(i, val) {			
				var videoId = val.videoId;
				
				var tr = $('<tr id ="video'+videoId+'"/>');
				tr.appendTo($(idName));
				
				var td1 = $('<td/>');
				td1.appendTo(tr);
				td1.html(val.videoId);
				
				var td2 = $('<td/>');
				td2.appendTo(tr);
				td2.html(val.videoTitle);
				
				var td3 = $('<td/>');
				td3.appendTo(tr);
				td3.html(val.videoInfo.substring(0,18)+"...");
				
				var td4 = $('<td/>');
				td4.appendTo(tr);
				td4.html(val.videoType.typeName);
				
				var td5 = $('<td/>');
				td5.appendTo(tr);
				td5.html((new Date(val.editDate)).Format("yyyy-MM-dd hh:mm:ss"));
				
				var td6 = $('<td/>');
				td6.appendTo(tr);
				td6.html(val.user.userName);
				
				var td7 = $('<td/>');
				td7.appendTo(tr);
				td7.html(val.videoState.stateName);
				
			
				
				var td8 = $('<td/>');
				td8.appendTo(tr);

				var button1=$('<button class="btn btn-primary btn-sm viewVideo" data-toggle="modal" data-target="#videoPlay">播放</button> ');
				var button3=$('<button class="btn btn-primary btn-sm delVideo" disabled="disabled" data-toggle="modal" data-target="#delVideo">删除</button>');
				var button4=$('<button class="btn btn-primary btn-sm verifyVideo" disabled="disabled" data-toggle="modal" data-target="#verifyVideo">审核</button>');
				button1.appendTo(td8);
				button3.appendTo(td8);
				button4.appendTo(td8);
				
				button1.click(function(){
					$.ajax({
						type : "get",
						url : "/AdminVideoController/getVideoByVideoId",
						async : true,
						data : {"videoId":videoId},
						dataType : "JSON",
						error : function(data) {
							window.wxc.xcConfirm("上传失败", window.wxc.xcConfirm.typeEnum.error);
						},
						success : function(result) {
							

							$('#videoplaydiv').empty();
							var play = $('<video autoplay="autoplay" id="videodiv" class="fp-play" style="width:100%;"><source type="video/mp4" src="'+result.data.videoUrl+'"></video>');
							play.appendTo($('#videoplaydiv'));
							var a = $(".videodiv").width();
					        $('.videodiv').css('height',a*0.57);							
						}//success:function
					});//ajax
				});
				
				button3.click(function(){
					$('#videoId').attr("value",videoId);
					$("#delVideoSubmit").click(function() {
						$.ajax({
							type : "post",
							url : "/AdminVideoController/deleteVideoByAjax",
							async : true,
							data : $('#delVideoForm').serialize(),
							dataType : "JSON",
							error : function(data) {
								window.wxc.xcConfirm("上传失败", window.wxc.xcConfirm.typeEnum.error);
							},
							success : function(result) {					
								if(result.message == "视频删除成功"){
									tr.remove();
									window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.success);
								}else{
									window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.error);
								}
							}//success:function
						});//ajax

					});
				});
				
				button4.click(function(){
					$('#verifyVideoId1,#verifyVideoId2').attr("value",videoId);
				});
			});

			$.Pagination(result.curPage,result.totalNum,".videoPaginationPosition",idName,url,videoAjax);
		}
	});
}

$(function(){
	videoAjax("/AdminVideoController/getVideoByAjax",".videoTable",1);
});


function roleAjax(url, idName) {
	$.ajax({
		url : url,
		async : true,
		type : "get",
		dataType : "JSON",
		error : function() {
			//window.wxc.xcConfirm("操作失败", window.wxc.xcConfirm.typeEnum.success);
		},
		success : function(result) {
			
			$.each(result.data, function(i, val) {			
				var roleId = val.roleId;
				
				var tr = $('<tr id ="video'+videoId+'"/>');
				tr.appendTo($(idName));
				
				var td1 = $('<td/>');
				td1.appendTo(tr);
				td1.html(val.roleId);
				
				var td2 = $('<td/>');
				td2.appendTo(tr);
				td2.html(val.userRole);
			
				
				var td3 = $('<td/>');
				td3.appendTo(tr);

				var button1=$('<button class="btn btn-primary btn-sm delRole" disabled="disabled" data-toggle="modal" data-target="#delRole">删除</button>');
				button1.appendTo(td3);
				button1.click(function(){
					$('#roleId').attr("value",roleId);
					$("#delRoleSubmit").click(function() {
						$.ajax({
							type : "post",
							url : "/AdminRoleController/deleteRoleByAjax",
							async : true,
							data : $('#delRoleForm').serialize(),
							dataType : "JSON",
							error : function(data) {
								window.wxc.xcConfirm("上传失败", window.wxc.xcConfirm.typeEnum.error);
							},
							success : function(result) {								
								if(result.message == "角色刪除成功"){
									tr.remove();
									window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.success);
								}else{
									window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.error);
								}
							}//success:function
						});//ajax

					});
				});
			});

		}
	});
}

$(function(){
	roleAjax("/AdminRoleController/getRoleByAjax",".roleTable");
});


function videoTypeAjax(url, idName) {
	$.ajax({
		url : url,
		async : true,
		type : "get",
		dataType : "JSON",
		error : function() {
			//window.wxc.xcConfirm("操作失败", window.wxc.xcConfirm.typeEnum.success);
		},
		success : function(result) {
			
			$.each(result.data, function(i, val) {			
				var videoTypeId = val.videoTypeId;
				
				var tr = $('<tr id ="video'+videoId+'"/>');
				tr.appendTo($(idName));
				
				var td1 = $('<td/>');
				td1.appendTo(tr);
				td1.html(val.videoTypeId);
				
				var td2 = $('<td/>');
				td2.appendTo(tr);
				td2.html(val.typeName);
			
				
				var td3 = $('<td/>');
				td3.appendTo(tr);

				var button1=$('<button class="btn btn-primary btn-sm delVideoType" disabled="disabled" data-toggle="modal" data-target="#delVideoType">删除</button>');
				button1.appendTo(td3);
				button1.click(function(){
					$('#videoTypeId').attr("value",videoTypeId);
					$("#delVideoTypeSubmit").click(function() {
						$.ajax({
							type : "post",
							url : "/AdminVideoTypeController/deleteVideoTypeByAjax",
							async : true,
							data : $('#delVideoTypeForm').serialize(),
							dataType : "JSON",
							error : function(data) {
								window.wxc.xcConfirm("上传失败", window.wxc.xcConfirm.typeEnum.error);
							},
							success : function(result) {				
								if(result.message == "删除类型成功"){
									tr.remove();
									window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.success);
								}else{
									window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.error);
								}
							}//success:function
						});//ajax

					});
				});
			});

		}
	});
}

$(function(){
	videoTypeAjax("/AdminVideoTypeController/getVideoTypeByAjax",".videoTypeTable");
});



$(window).resize(function () {          //当浏览器大小变化时
	 var a = $(".videodiv").width();
     $('.videodiv').css('height',a*0.57);
});


$("#addVideoTypeSubmit").click(function() {
	$.ajax({
		type : "post",
		url : "/AdminVideoTypeController/addVideoTypByAjax",
		async : true,
		data : $('#addVideoTypeForm').serialize(),
		dataType : "JSON",
		error : function(data) {
			window.wxc.xcConfirm("上传失败", window.wxc.xcConfirm.typeEnum.error);
		},
		success : function(result) {
			window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.success);
		}//success:function
	});//ajax

});

$("#closevideo,#closevideo2").click(function(){
	$('#videoplaydiv').empty();
});

$("#addRoleSubmit").click(function() {
	$.ajax({
		type : "post",
		url : "/AdminRoleController/addRoleByAjax",
		async : true,
		data : $('#addRoleFrom').serialize(),
		dataType : "JSON",
		error : function(data) {
			window.wxc.xcConfirm("上传失败", window.wxc.xcConfirm.typeEnum.error);
		},
		success : function(result) {
			window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.success);
		}//success:function
	});//ajax

});


//userInfoUpdateForm
$("#userUpdateButton").click(function() {
	$.ajax({
		type : "post",
		url : "/AdminUserController/updateUserByAjax",
		async : true,
		data : $('#userInfoUpdateForm').serialize(),
		dataType : "JSON",
		error : function(data) {
			window.wxc.xcConfirm("上传失败", window.wxc.xcConfirm.typeEnum.error);
		},
		success : function(result) {
			window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.success);
		}//success:function
	});//ajax

});

//addUserForm
$("#addUserFormButton").click(function() {
	$.ajax({
		type : "post",
		url : "/AdminUserController/addUser",
		async : true,
		data : $('#addUserForm').serialize(),
		dataType : "JSON",
		error : function(data) {
			window.wxc.xcConfirm("上传失败", window.wxc.xcConfirm.typeEnum.error);
		},
		success : function(result) {
			window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.success);
		}//success:function
	});//ajax

});

//verifyVideoSubmit
$("#verifyVideoSubmit").click(function() {
	$.ajax({
		type : "post",
		url : "/AdminVideoController/verifyVideoByAjax",
		async : true,
		data : $('#verifyVideoForm').serialize(),
		dataType : "JSON",
		error : function(data) {
			window.wxc.xcConfirm("上传失败", window.wxc.xcConfirm.typeEnum.error);
		},
		success : function(result) {
			window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.success);
		}//success:function
	});//ajax

});





