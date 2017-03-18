/**********************************************************************************************************
 * 
 * 获取服务器时间
 * 因程序执行耗费时间,所以时间并不十分准确,误差大约在2000毫秒以下
 */
ServerDate() = function(){}
ServerDate.prototype = {	
	getServerDate : function(){
		var xmlHttp = false;
		//获取服务器时间
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
			    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e2) {
			    xmlHttp = false;
			}
		}
	
		if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
			xmlHttp = new XMLHttpRequest();
		}
	
		xmlHttp.open("GET", "null.txt", false);
		xmlHttp.setRequestHeader("Range", "bytes=-1");
		xmlHttp.send(null);
	
		severtime=new Date(xmlHttp.getResponseHeader("Date"));
	
		//获取服务器日期
		return severtime;
	},
	getFullYear : function(){
		var xmlHttp = false;
		//获取服务器时间
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
			    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e2) {
			    xmlHttp = false;
			}
		}

		if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
			xmlHttp = new XMLHttpRequest();
		}

		xmlHttp.open("GET", "null.txt", false);
		xmlHttp.setRequestHeader("Range", "bytes=-1");
		xmlHttp.send(null);

		severtime=new Date(xmlHttp.getResponseHeader("Date"));

		//获取服务器日期
		return severtime.getFullYear();
	},
	getMonth():function(){
		var xmlHttp = false;
		//获取服务器时间
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
			    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e2) {
			    xmlHttp = false;
			}
		}

		if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
			xmlHttp = new XMLHttpRequest();
		}

		xmlHttp.open("GET", "null.txt", false);
		xmlHttp.setRequestHeader("Range", "bytes=-1");
		xmlHttp.send(null);

		severtime=new Date(xmlHttp.getResponseHeader("Date"));

		//获取服务器日期
		return severtime.getMonth()+1;
	},
	getDate : function(){
		var xmlHttp = false;
		//获取服务器时间
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
			    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e2) {
			    xmlHttp = false;
			}
		}

		if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
			xmlHttp = new XMLHttpRequest();
		}

		xmlHttp.open("GET", "null.txt", false);
		xmlHttp.setRequestHeader("Range", "bytes=-1");
		xmlHttp.send(null);

		severtime=new Date(xmlHttp.getResponseHeader("Date"));

		//获取服务器日期
		return severtime.getDate();
	}
	
}	
 

