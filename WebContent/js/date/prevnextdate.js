/**********************************************************************************************************
 * 
 * 操作当前日期：昨天，明天；上周，下周；上月，下月；上年，现年；
 * eg.
 * 	strDate=2012-01-04
 * 
 */
PNdate() = function(){}
PNDate.prototype = {	
	getYestoday : function(strDate){//获取昨天日期
		var regEx = new RegExp("\\-","gi");
		strDate = strDate.replace(regEx,"/"); 
		var date = new Date(strDate);
	    
		var yesterday_milliseconds=date.getTime()-1000*60*60*24;       
		var yesterday = new Date();       
		yesterday.setTime(yesterday_milliseconds);       
	      
		var strYear = yesterday.getFullYear();    
		var strDay = yesterday.getDate();    
		var strMonth = yesterday.getMonth()+1;  
	  
		if(strMonth<10){    
			strMonth="0"+strMonth;    
		}    
	  
		datastr = strYear+"-"+strMonth+"-"+strDay;  
		return datastr;  
	},	
	getTomorrow : function(strDate){//获取明天日期
		var regEx = new RegExp("\\-","gi");
		strDate = strDate.replace(regEx,"/"); 
		var date = new Date(strDate);
	    
		var tomorrow_milliseconds=date.getTime()+1000*60*60*24;       
		var tomorrow = new Date();       
	  	tomorrow.setTime(tomorrow_milliseconds);       
	      
		var strYear = tomorrow.getFullYear();    
		var strDay = tomorrow.getDate();    
		var strMonth = tomorrow.getMonth()+1;  
		
		if(strMonth<10){    
			strMonth="0"+strMonth;    
		}
		
		datastr = strYear+"-"+strMonth+"-"+strDay;  
		return datastr;  
	},	
	getPrevWeek : function(strDate){//获取上周这一天的日期
		var regEx = new RegExp("\\-","gi");
		strDate = strDate.replace(regEx,"/"); 
		var date = new Date(strDate);
	    
		var yesterday_milliseconds=date.getTime()-7000*60*60*24;       
		var yesterday = new Date();       
		yesterday.setTime(yesterday_milliseconds);       
	      
		var strYear = yesterday.getFullYear();    
		var strDay = yesterday.getDate();    
		var strMonth = yesterday.getMonth()+1;  
	  
		if(strMonth<10){    
			strMonth="0"+strMonth;    
		}    
	  
		datastr = strYear+"-"+strMonth+"-"+strDay;  
		return datastr;  
	}, 
	getNextWeek : function(strDate){//获取下周这一天的日期
		var regEx = new RegExp("\\-","gi");
		strDate = strDate.replace(regEx,"/"); 
		var date = new Date(strDate);
	    
		var tomorrow_milliseconds=date.getTime()+7000*60*60*24;       
		var tomorrow = new Date();       
	  	tomorrow.setTime(tomorrow_milliseconds);       
	      
		var strYear = tomorrow.getFullYear();    
		var strDay = tomorrow.getDate();    
		var strMonth = tomorrow.getMonth()+1;  
		
		if(strMonth<10){    
			strMonth="0"+strMonth;    
		}
		
		datastr = strYear+"-"+strMonth+"-"+strDay;  
		return datastr;  
	}, 
	getPrevMonthYestdy : function (strDate){//获得上个月在昨天这一天的日期  
		var regEx = new RegExp("\\-","gi");
		strDate = strDate.replace(regEx,"/"); 
		var date = new Date(strDate);
	    
		var daysInMonth = new Array([0],[31],[28],[31],[30],[31],[30],[31],[31],[30],[31],[30],[31]);  
		var strYear = date.getFullYear();    
		var strDay = date.getDate();    
		var strMonth = date.getMonth()+1;  
		if(strYear%4 == 0 && strYear%100 != 0){  
			daysInMonth[2] = 29;  
		}  
		
		if(strMonth - 1 == 0){  
			strYear -= 1;  
			strMonth = 12;  
		}else{  
			strMonth -= 1;  
		}
		
		strDay = daysInMonth[strMonth] >= strDay ? strDay : daysInMonth[strMonth];  
		if(strMonth<10){    
			strMonth="0"+strMonth;    
		}  
		
		if(strDay<10){    
			strDay="0"+strDay;    
		}  
		datastr = strYear+"-"+strMonth+"-"+strDay;
		return datastr;  
	},	
	getNextMonthYestdy : function(strDate){//获得下个月在昨天这一天的日期  
		var regEx = new RegExp("\\-","gi");
		strDate = strDate.replace(regEx,"/"); 
		var date = new Date(strDate);
	    
		var daysInMonth = new Array([0],[31],[28],[31],[30],[31],[30],[31],[31],[30],[31],[30],[31]);  
		var strYear = date.getFullYear();    
		var strDay = date.getDate();    
		var strMonth = date.getMonth()+1;  
		if(strYear%4 == 0 && strYear%100 != 0){  
			daysInMonth[2] = 29;  
		}  
		
		if(strMonth == 12){  
			strYear += 1;  
			strMonth = 1;  
		}else{  
			strMonth += 1;  
		}
		
		strDay = daysInMonth[strMonth] >= strDay ? strDay : daysInMonth[strMonth];  
		if(strMonth<10){    
			strMonth="0"+strMonth;    
		}  
		
		if(strDay<10){    
			strDay="0"+strDay;    
		}  
		datastr = strYear+"-"+strMonth+"-"+strDay;
		return datastr;  
	},
	getPrevYearYestdy : function(strDate){ //获得上一年在昨天这一天的日期  
		var regEx = new RegExp("\\-","gi");
		strDate = strDate.replace(regEx,"/"); 
		var date = new Date(strDate);
	    
		var strYear = date.getFullYear() - 1;    
		var strDay = date.getDate();    
		var strMonth = date.getMonth()+1;  
		if(strMonth<10){    
			strMonth="0"+strMonth;    
		}  
		if(strDay<10){    
			strDay="0"+strDay;    
		}  
		datastr = strYear+"-"+strMonth+"-"+strDay;
		return datastr;  
	},
	getNextYearYestdy : function(strDate){ //获得下一年在昨天这一天的日期  
		var regEx = new RegExp("\\-","gi");
		strDate = strDate.replace(regEx,"/"); 
		var date = new Date(strDate);
	    
		var strYear = date.getFullYear() + 1;    
		var strDay = date.getDate();    
		var strMonth = date.getMonth()+1;  
		if(strMonth<10){    
			strMonth="0"+strMonth;    
		}  
		if(strDay<10){    
			strDay="0"+strDay;    
		}  
		datastr = strYear+"-"+strMonth+"-"+strDay;
		return datastr;  
	}
}