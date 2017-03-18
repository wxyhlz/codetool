/*****************************************************************************
 * 
 *				ZTREE 
 * 
 *****************************************************************************/
//定义ztree结构
var setting;
setting = {
	data: {
		simpleData: {
			enable: true,
			idKey:"id",
			pIdKey:"pid"			
		}
	},
	checkable: false,
	callback:{
        onClick: zTreeOnClick
	}
};

//装载ztree
//var zNodes =[];
function loadZtree() {
	//var url = path+'/sysmodule/selectEntites.action';
	var url = path + '/operator/findModules.action';
	$.ajax({
		type:"POST",
		url:url,
		dataType:"json",
		success:function callback(data){
			var analyMsg = data;
			if(!analyMsg.successed){ 
				$.messager.alert('友情提醒',analyMsg.errorMsg,'info');
			}else{ 
				$.fn.zTree.init($("#treeDemo"), setting,analyMsg.dataMap.data);
			}
		},
		error: function (data) {
			$.messager.alert('友情提醒',data.responseText,'info');
        }
	});  
}

//弹出窗体打开
function openDialog(url){
	window.showModalDialog(path+"/"+url,"","dialogwidth:"+width+";dialogheight:"+height+";help=no;status=no;center=yes;edge=sunken;resizable=yes;Minimize=no;Maximize=no");
}
//新建Tab页签打开
var curtab="";

function newIframe(url){	
	var joinHtml = '<iframe scrolling="auto" frameborder="0" src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return joinHtml;
}

function addTab(subtitle,url,icon){
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:newIframe(url),
			closable:true,
			icon:icon
		});
	}else{
		$('#tabs').tabs('select',subtitle); 
	}
	curtab = subtitle;
	tabClose();
}

//ztree节点点击操作
var start=new Date();
function zTreeOnClick(event, treeId, treeNode) {
 	var now = new Date(); 
 	//控制点击过快造成的bug
 	if(now.getTime()-start.getTime()>1000){
		if(!treeNode.isParent){
			if(treeNode.opentype=="1"){
				addTab(treeNode.name,treeNode.actionurl,"");
				start=now;
			}else{
				openDialog(treeNode.actionurl);
			}
		}
 	}  
}


//获取左侧导航的图标
function getIcon(menuid){
	var icon = 'icon ';
	$.each(_menus.menus, function(i, n) {
		 $.each(n.menus, function(j, o) {
		 	if(o.menuid==menuid){
				icon += o.icon;
			}
		 })
	})

	return icon;
}

function selectTab(subtitle){
	$('#tabs').tabs('select',subtitle);
}
/*****************************************************************************
 * 
 *				TAB 
 * 
 *****************************************************************************/
function tabClose(){
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}
//绑定右键菜单事件
function tabCloseEven(){
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update',{
			tab:currTab,
			options:{
				content:newIframe(url)
			}
		})
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t!="欢迎使用")
			  $('#tabs').tabs('close',t);
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			//alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			if(t!="欢迎使用")
			   $('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			//alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			if(t!="欢迎使用")
			  $('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

/*****************************************************************************
 * 
 *				JQUERY LOAD	NAVIGATE 
 * 
 *****************************************************************************/
$(function(){
	loadZtree();
	tabClose();
	tabCloseEven();
	var currTab = $('#tabs').tabs('getSelected');
	$('#tabs').tabs('update',{
		tab:currTab,
		options:{
			//content:createFrame('sysWelcome.action')
		}
	});
})
