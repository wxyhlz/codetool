// JSON_DATA:eval('[{"id":"01","name":"一季度"},{"id":"02","name":"二季度"},{"id":"03","name":"三季度"},{"id":"04","name":"四季度"}]')
// 右键屏蔽       
// document.oncontextmenu=new Function("event.returnValue=false;");
// 输入控制：
// 1、只能输入
// (1)只能输入数字onkeyup="value=value.replace(/[^\d]/g,'') "
// (2)只能输入数字和字母onkeyup="value=value.replace(/[\W]/g,'') "
// (3)只能输入汉字onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"
// (4)只能输入汉字和字母onkeyup="value=value.replace(/[^\a-zA-Z\u4E00-\u9FA5]/g,'')"
// 2、禁止输入
// (1)禁止输入汉字<INPUT TYPE="text" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')">
// (2)禁止输入数字<INPUT TYPE="text" onkeyup="value=value.replace(/[\d]/g,'')">
// (3)禁止输入字母<INPUT TYPE="text" onkeyup="value=value.replace(/[a-z,A-Z]/g,'')">
// (4)禁止输入空格onkeyup="value=value.replace(/[/\s/g,]/g,'')"
// (5)禁止输入特殊字符<INPUT TYPE="text" onkeyup="value=value.replace(/[/\.-]/g,'')">
// 3、禁止粘贴onpaste="return false"


/**
 * function eg.
 *  
 * setAjaxLoading(); 
 * String.trim(); 
 * moveFocus(event, eleId);
 * setLength(eleName,lenth);
 * keyDown();
 * shortenChar(s, len, needDot);
 * quitIE(flag);
 * btn_mouseover(target) or btn_mounserout(target)
 * blankValidate(elename,elehint)  空验证
 * 
 */

/**********************************************************************************************************
 * 
 * ajax提交动作时显示动态效果，相关文件loading.jsp
 * 
 * @return
 */
function setAjaxLoading(){	
	$("#loading").ajaxStart(function () {
		$(this).show();
	}).ajaxStop(function () {
		$(this).hide();
	});
	
	$("#loading").ajaxError(function(event,request,settings){
		$(this).hide();
		self.location='./error.action';
	});
	
	$.ajaxSetup({cache:false});
}

/**********************************************************************************************************
 * String
 * 
 * 删除两边的空格
 * 
 * eg.
 * var str='abc '; str.trim();
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g,"");
};

/**
 * 删除左边的空格
 */
String.prototype.ltrim=function()
{
     return this.replace(/(^\s*)/g,"");
};

/**
 * 删除右边的空格
 */
String.prototype.rtrim=function()
{
    return this.replace(/(\s*$)/g,"");
};

/**********************************************************************************************************
 * 
 * 回车定位指定单元
 * 
 * @param evt：事件
 * @param eleId：元素id
 * @return
 */
function moveFocus(evt, eleId) {
	var objx = eleId; 
	evt = (evt) ? evt : ((window.event) ? window.event : "")
	key = evt.keyCode ? evt.keyCode : evt.which;
	if (key == 13) {
		var eleFocus = "document.getElementById(\"" + objx + "\").focus()";
		eval(eleFocus);
		return false;
	}
}

/**********************************************************************************************************
 * 
 * 设置输入框最大输入字符数name位控件名称前面加#  如="#stel" length=10 其中中文占两个字符
 * 
 * @param eleName：元素名
 * @param lenth：长度
 * @return
 */
function setLength(eleName,lenth){
    var _area=$(eleName); 
    var _max = lenth;
    var _val,_cur; 
    var chineseRegex = /[^\x00-\xff]/g;
    _area.bind('keyup change',function(){ //绑定keyup和change事件 
	    _val=$(this).val(); 
	    _cur = _val.replace(chineseRegex,"**").length; 
	    //_cur=_val.length; 
	    if(_val.indexOf('&')>=0){ 
	       $(this).val(_val.replace('&','')); 
	    }
	    if(_val.indexOf('%')>=0){ 
	        $(this).val(_val.replace('%','')); 
	    }
	    if(_val.indexOf('?')>=0){ 
	        $(this).val(_val.replace('?','')); 
	    }
	    if(_val.indexOf('|')>=0){ 
	       $(this).val(_val.replace('|','')); 
	    }
	    if(_val.indexOf('\"')>=0){ 
	       $(this).val(_val.replace('\"','')); 
	    }
	    if(_val.indexOf('\'')>=0){ 
	       $(this).val(_val.replace('\'','')); 
	    }
	    if(_cur>_max){ 
	       $(this).val(subString(_val,_max)); 
	       } 
	    } 
    ); 
}

/**********************************************************************************************************
 * 
 * 屏蔽鼠标右键、Ctrl+n、shift+F10、F5刷新、退格键
 * 
 * @return
 */
function keyDown(){
	// alert("ASCII代码是："+event.keyCode);
	//屏蔽 Alt+ 方向键 ← &&屏蔽 Alt+ 方向键 →
	if ((window.event.altKey)&&((window.event.keyCode==37)||(window.event.keyCode==39))){
		alert("禁止使用ALT+方向键前进或后退网页！");
		event.returnValue=false;
	}
	
	//屏蔽退格删除键 && 屏蔽 F5 刷新键 && 屏蔽 F1 刷新键 && Ctrl + R
	if ((event.keyCode==8)  ||(event.keyCode==116)||(event.keyCode==112)||(event.ctrlKey && event.keyCode==82)){
		event.keyCode=0;
		event.returnValue=false;
	}
	//屏蔽 Ctrl+n
	if ((event.ctrlKey)&&(event.keyCode==78)){   
		event.returnValue=false;
	}
	
	//屏蔽 shift+F10
	if ((event.shiftKey)&&(event.keyCode==121)){ 
		event.returnValue=false;
	}
	
	//屏蔽 shift 加鼠标左键新开一网页  
	if (window.event.srcElement.tagName == "A" && window.event.shiftKey){ 
		window.event.returnValue = false;
	}
	
	//屏蔽Alt+F4
	if ((window.event.altKey)&&(window.event.keyCode==115)){ 
		window.showModelessDialog("about:blank","","dialogWidth:1px;dialogheight:1px");
		return false;
	}
}

/**********************************************************************************************************
 * 
 * 缩短字符
 * 
 * @param str
 * @param len
 * @param hasDot
 * @return
 */
function shortenChar(s, len, needDot){ 
	var newLength = 0; 
	var newStr = ""; 
	var chineseRegex = /[^\x00-\xff]/g; 
	var singleChar = ""; 
	var strLength = s.replace(chineseRegex,"**").length; 
	for(var i=0;i< strLength;i++){ 
		singleChar = s.charAt(i).toString(); 
		if(singleChar.match(chineseRegex) != null){ 
			newLength += 2; 
		}else{ 
			newLength++; 
		} 
		if(newLength > len){ 
			break; 
		} 
		newStr += singleChar; 
	} 
	if(needDot && strLength > len){ 
		newStr += "..."; 
	} 
	return newStr; 
}

/**********************************************************************************************************
 * 
 * IE退出请求操作
 * 
 * @param flag
 * @return
 */
function quitIE(flag){
	if(flag!=""){
		url="logOut.action?subsysId="+flag;
	}else{
		url="logOut.action";
	}
	
	$.ajax({type:"POST",url:url});
}

/**********************************************************************************************************
 * 
 * 按钮显示
 * 
 * @param target
 * @return
 */
function btn12_mouseover(target){
	target.style.backgroundPosition='left -40px';
}

function btn12_mouseout(target){
	target.style.backgroundPosition='left top';
}

function btn22_mouseover(target){
	target.style.backgroundPosition='left -42px';
}

function btn22_mouseout(target){
	target.style.backgroundPosition='left top';
} 

function btn33_mouseover(target){
	target.style.backgroundPosition='left -49px';
}

function btn33_mouseout(target){
	target.style.backgroundPosition='left top';
}

function btn45_mouseover(target){
	target.style.backgroundPosition='left -75px';
	target.style.color='#bcf1a7';
}

function btn45_mouseout(target){
	target.style.backgroundPosition='left top';
	target.style.color='#ffafaf';
}

/**********************************************************************************************************
 * 
 * 图片缩放
 * 
 * @param maxWidth
 * @param maxHeight
 * @param objImg
 * @return
 */
function AutoResizeImage(maxWidth, maxHeight, objImg) {  
    var img = new Image();  
    img.src = objImg.src;  

    var hRatio;  
    var wRatio;  
    var Ratio = 1;  

    var w = img.width;  
    var h = img.height;  

    wRatio = maxWidth / w;  
    hRatio = maxHeight / h;  

    if (maxWidth == 0 && maxHeight == 0) {  
        Ratio = 1;  
    } else if (maxWidth == 0) { 
		if (hRatio < 1)  
            Ratio = hRatio;  
    } else if (maxHeight == 0) {  
        if (wRatio < 1) 
            Ratio = wRatio;  
    } else if (wRatio < 1 || hRatio < 1) { //取较小的比率 
        Ratio = (wRatio <= hRatio ? wRatio : hRatio);  
    }  

    if (Ratio < 1) { 
        w = w * Ratio;  
        h = h * Ratio;  
    }  

    objImg.height = h;  
    objImg.width = w;  
}  

/**********************************************************************************************************
 * 
 * 空验证
 * 
 * @param elename
 * @param elehint 
 * @return
 */
function blankValidate(elename,elehint){
	var nameArray = elename.split(",");
	var hintArray = elehint.split(",");
	var hint = "";
	for(var i=0; i<nameArray.length; i++){
		if($("#"+nameArray[i]).val()==""){
			hint = hintArray[i];
			break;
		}
	}	
	return hint;
}

/**********************************************************************************************************/
function isnull(value){
	if(!value){
		return "";
	}else{
		return value;
	}
}

//替换非法SQL注入字符
function replaceIllegalitySql(str){
	if (str != null && str != ""){
	    if(str.indexOf('&')>=0){ 
	    	str = str.replace(/\&/g,''); 
	     }
	     if(str.indexOf('%')>=0){ 
	    	 str = str.replace(/\%/g,''); 
	     }
	     if(str.indexOf('?')>=0){ 
	    	 str = str.replace(/\?/g,''); 
	     }
	     if(str.indexOf('|')>=0){ 
	    	 str = str.replace(/\|/g,''); 
	     }
	     if(str.indexOf('\"')>=0){ 
	    	 str = str.replace(/\"/g,''); 
	     }
	     if(str.indexOf('\'')>=0){ 
	    	 str = str.replace(/\'/g,'');
	     }
	     return str;
	}else{
		return str;
	}
}

function iniTab(){
	$('#tab').tabs({
		width:'100%',
		fit:true,
		onSelect:function(title){}		
	});
}	