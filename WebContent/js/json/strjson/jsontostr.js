function JsonToStr(o) {      
	var arr = [];      
	var fmt = function(s) {      
	if (typeof s == 'object' && s != null) return JsonToStr(s);      
		return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;      
	};      
	for (var i in o) arr.push("'" + i + "':" + fmt(o[i]));      
	return '{' + arr.join(',') + '}';   
}

function strToJson(str){ 
	var json = eval('(' + str + ')'); 
	return json; 
}
/*
function JsonToStr(o) {
	var r = [];
	if (typeof o == "string" || o == null) {
		return o;            
	}            
	if (typeof o == "object") {
		if (!o.sort) {
			r[0] = "{"
				for (var i in o) {
					r[r.length] = i;
					r[r.length] = ":";
					r[r.length] = JsonToStr(o[i]);
					r[r.length] = ",";
				}
			r[r.length - 1] = "}"
		} else {
			r[0] = "["
				for (var i = 0; i < o.length; i++) {
					r[r.length] = JsonToStr(o[i]);
					r[r.length] = ",";
				}
			r[r.length - 1] = "]"
		}                
		return r.join("");
	}
	return o.toString();        
}*/