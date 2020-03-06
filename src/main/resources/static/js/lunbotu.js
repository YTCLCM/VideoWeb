window.οnlοad = function() {      //获得ul的元素
	     
	var imgList = document.getElementById("imgList");      //获得图片的数组
	     
	var imgArr = document.getElementsByTagName("img");     
	var navId = document.getElementById("navId");     
	var outer = document.getElementById("outer");     
	imgList.style.width = 520 * imgArr.length + "px";      //设置navId的位置 使其居中
	     
	navId.style.left = (outer.offsetWidth - navId.offsetWidth) / 2 + "px";      //得到所有的a 标签 如果有其他的A的话  这里需要注意要使用navId子元素的a 
	     
	var allA = document.getElementsByTagName("a");     
	var index = 0;     
	allA[index].style.backgroundColor = 'red'; //设置默认的a为黑色
	     
	for(var i = 0; i < allA.length; i++) {        
		allA[i].num = i;         //alert(allA[i].num);
		        
		allA[i].οnclick = function() {           
			clearInterval(timer);           
			index = this.num;            /* imgList.style.left= -520*index+"px"; */            
			setA();           
			move(imgList, "left", -520 * index, 50, function() {              
				autoChange();           
			});        
		};     
	}          
	function setA() {         //alert(index);
		         //当indcx值比图片的数目多的时候 就归0
		        
		if(index >= imgArr.length - 1) {           
			index = 0;           
			imgList.style.left = 0;        
		}         
		for(var i = 0; i < allA.length; i++) {            //去掉未点击的颜色  仍然保留a : hover有用
			           
			allA[i].style.backgroundColor = "";        
		}        
		allA[index].style.backgroundColor = "red";     
	}     
	var timer;     
	function autoChange() {                
		timer = setInterval(function() {           
			index++;          
			index %= imgArr.length;           
			move(imgList, "left", -520 * index, 1, function() {                 
				setA();              
			});        
		}, 2000);      
	}     
	autoChange();

	//可以根据 target 参数进行判断 向哪个方向移动
	    
	function move(obj, attr, target, speed, callback) {       
		var current = parseInt(getStyle(obj, attr));        //alert(current);
		        //根据目标的位置来判定 speed的值是正是负
		       
		if(current > target) {          
			speed = -speed;       
		}        //自定义对象定时器 防止对象之间的混乱操作 
		       
		clearInterval(obj.timer);        //alert(oldValue);
		       
		obj.timer = setInterval(function() {          
			var oldValue = parseInt(getStyle(obj, attr));          
			var newVal = oldValue + speed;           //如果移动的越界 进行重置
			          
			if((speed < 0 && newVal <= target) || (speed > 0 && newVal >= target)) {             
				newVal = target;          
			}          
			obj.style[attr] = newVal + "px";          
			if(newVal == target) {             
				clearInterval(obj.timer);             
				callback && callback(); //回掉函数 先判断 有就执行 没有不执行
				          
			}        
		}, 30);    
	}         //obj:获取样式元素
	     //name:获取样式名
	    
	function getStyle(obj, name) {       
		if(window.getComputedStyle) {          
			return getComputedStyle(obj, null)[name];       
		} else {          
			return obj.currentStyle[name];       
		}    
	}  
}




