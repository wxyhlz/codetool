/**
 * 文本光标操作
 * 1、getCurPos获取光标位置
 * 2、setCurPos设置光标位置
 * 3、selection读取选中内容
 */
jQuery.fn.extend({  
    getCurPos: function(){//��ȡ���λ��  
        var e=$(this).get(0);  
        e.focus();  
        if(e.selectionStart){    //FF  
            return e.selectionStart;  
        }  
        if(document.selection){    //IE  
            var r = document.selection.createRange();  
            if (r == null) {  
                return e.value.length;  
            }  
            var re = e.createTextRange();  
            var rc = re.duplicate();  
            re.moveToBookmark(r.getBookmark());  
            rc.setEndPoint('EndToStart', re);  
            return rc.text.length;  
        }  
        return e.value.length;  
    },  
    setCurPos: function(pos) {//����λ��  
        var e=$(this).get(0);  
        e.focus();  
        if (e.setSelectionRange) {  
            e.setSelectionRange(pos, pos);  
        } else if (e.createTextRange) {  
            var range = e.createTextRange();  
            range.collapse(true);  
            range.moveEnd('character', pos);  
            range.moveStart('character', pos);  
            range.select();  
        }  
    },
	selection : function(){//��ȡѡ������$.selection().text;
        var s,e,range,stored_range;
        if(this[0].selectionStart == undefined){
            var selection=document.selection;
            if (this[0].tagName.toLowerCase() != "textarea") {
                var val = this.val();
                range = selection.createRange().duplicate();
                range.moveEnd("character", val.length);
                s = (range.text == "" ? val.length:val.lastIndexOf(range.text));
                range = selection.createRange().duplicate();
                range.moveStart("character", -val.length);
                e = range.text.length;
            }else {
                range = selection.createRange(),
                stored_range = range.duplicate();
                stored_range.moveToElementText(this[0]);
                stored_range.setEndPoint('EndToEnd', range);
                s = stored_range.text.length - range.text.length;
                e = s + range.text.length;
            }
        }else{
            s=this[0].selectionStart,
            e=this[0].selectionEnd;
        }
        var te=this[0].value.substring(s,e);
        return {start:s,end:e,text:te}
    }
}); 