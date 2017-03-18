/**********************************************************************************************************
 * 
 * 日期的常用操作：1、润年；2、日期转换
 * eg.
 * 	strDate=2012-01-04
 * 
 */
CommfunDate() = function(){}
CommfunDate.prototype = {
	isLeapYear : function(strDate){//润年判断
		var regEx = new RegExp("\\-","gi");
		strDate = strDate.replace(regEx,"/"); 
	    var dt = new Date(strDate);
	    var y = dt.getFullYear();  
	    var isLeap = false;  
		if(y%4==0 && y%100!=0 || y%400==0) {  
			isLeap = true;  
		}  
		return isLeap;  
	},
	strToDate : function(strDate){//字符串转成日期
		var regEx = new RegExp("\\-","gi");
		strDate = strDate.replace(regEx,"/"); 
	    return new Date(strDate);
	}
}	

 