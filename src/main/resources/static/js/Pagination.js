/**
 * 注：应用前，html文件请刀路架包
 * <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />  
 * <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
 * <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
 */


/**
 * 调用方式：$.Pagination(19,20,"showPosition");
 */

/**
 * 视频网站：b站搜索烟之逍客
 */

/**
 * Pagination前端分页
 * @param {Object} curPage 当前页号
 * @param {Object} totalPage 总页数
 * @param {Object} showPosition 插入的div的class名
 */
$.Pagination = function(curPage, totalPage, PaginationPosition,tablePosition,url,func) {
	function PaginationClick(obj){		
		$(tablePosition).empty();
		$(PaginationPosition).empty();
		func(url,tablePosition,obj.getAttribute("value"));
		
	}

	function PaginationLast(curPage){		
		if((curPage-1)>=1){
			$(tablePosition).empty();
			$(PaginationPosition).empty();
			func(url,tablePosition,curPage-1);
		}	
	}

	function PaginationNext(curPage,totalPage){		
		if((curPage+1) <= totalPage){
			$(tablePosition).empty();
			$(PaginationPosition).empty();
			func(url,tablePosition,curPage+1);
		}	
	}
	
	var ul = $('<ul class="pagination" />');
	ul.appendTo($(PaginationPosition));

	var li = $('<li class="lastPage" />');
	var a = $('<a href="#"/>');
	a.html("&laquo;");
	a.appendTo(li);
	li.appendTo(ul);
	a.click(function(){
		PaginationLast(curPage);
	});

	var a = curPage - 1;
	var b = totalPage - curPage;
	var a_sign = 3;
	var b_sign = 3;
	if(a < 3) {
		b_sign = b_sign + (3 - a);
	}
	if(b < 3) {
		a_sign = a_sign + (3 - b);
	}

	for(var i = 1; i <= (curPage - 1); i++) {
		if((curPage - 1) <= 3 || i == 1) {
			var li = $('<li/>');
			var a = $('<a value="'+i+'"  href="#" />');
			a.html(i);
			a.appendTo(li);
			li.appendTo(ul);
			a.click(function(){
				PaginationClick(this);
			});
		} else if((curPage - i) <= (a_sign - 1)) {
			var li = $('<li />');
			var a = $('<a value="'+i+'"   href="#" />');
			a.html(i);
			a.appendTo(li);
			li.appendTo(ul);
			a.click(function(){
				PaginationClick(this);
			});

		} else {
			if($('li').hasClass("beforeLeave") == false) {
				var li = $('<li class="beforeLeave"/>');
				var a = $('<a href="#" />');
				a.html('...');
				a.appendTo(li);
				li.appendTo(ul);
			}
		}

	}

	if(curPage != 0) {
		var li = $('<li class="active"/>');
		var a = $('<a href="#" />');
		a.html(curPage);
		a.appendTo(li);
		li.appendTo(ul);
	}

	for(var i = (curPage + 1); i <= totalPage; i++) {
		if((totalPage - curPage) <= 3 || i == totalPage) {
			var li = $('<li/>');
			var a = $('<a value="'+i+'" href="#" />');
			a.html(i);
			a.appendTo(li);
			li.appendTo(ul);
			a.click(function(){
				PaginationClick(this);
			});
		} else if((i - curPage) <= (b_sign - 1)) {
			var li = $('<li />');
			var a = $('<a value="'+i+'" href="#" />');
			a.html(i);
			a.appendTo(li);
			li.appendTo(ul);
			a.click(function(){
				PaginationClick(this);
			});
		} else {
			if($('li').hasClass("afterLeave") == false) {
				var li = $('<li class="afterLeave"/>');
				var a = $('<a href="#" />');
				a.html('...');
				a.appendTo(li);
				li.appendTo(ul);
			}
		}
	}

	var li = $('<li class="lastPage" />');
	var a = $('<a href="#"/>');
	a.html("&raquo;");
	a.appendTo(li);
	li.appendTo(ul);
	a.click(function(){
		PaginationNext(curPage , totalPage);
	});
}

