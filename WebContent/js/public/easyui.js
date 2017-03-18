/**********************************************************************************************************
 * 
 * 定义easyUI datagrid样式
 * @return
 * 
 * eg.
 * var easyui = datagridExtends();
 * easyui.datagrid.headcenter();
 * easyui.datagrid.fixRownumber();
 * 
 */

function datagridExtends(){
	var easyui = easyui || {};
	easyui.datagrid = easyui.datagrid || {}; 
	//one function
	easyui.datagrid.headcenter = function () {
		 $(".datagrid-header .datagrid-cell").css('text-align', 'center').css('color', '#173967');
	};
	//two function
	easyui.datagrid.fixRownumber = function(){
		var headerTds = $(".datagrid-header-inner table tr:first-child").children();
		var bodyTds = $(".datagrid-body table tr:first-child").children();
		var headerTd = $(headerTds.get(0));
		headerTd.width(50);
		var bodyTd = $(bodyTds.get(0));
		bodyTd.width(50);
	};
	//three function
	easyui.datagrid.fixColwidth = function(){
		//datagrid头部 table 的第一个tr 的td们，即columns的集合
        var headerTds = $(".datagrid-header-inner table tr:first-child").children();
        //datagrid主体 table 的第一个tr 的td们，即第一个数据行
        var bodyTds = $(".datagrid-body table tr:first-child").children();
        var totalWidth = 0; //合计宽度，用来为datagrid头部和主体设置宽度
        //循环设置宽度
        bodyTds.each(function (i, obj) {
            var headerTd = $(headerTds.get(i));
            var bodyTd = $(bodyTds.get(i));
            $("div:first-child", headerTds.get(i)).css("text-align", "center");
            var headerTdWidth = headerTd.width(); //获取第i个头部td的宽度
            //这里加5个像素 是因为数据主体我们取的是第一行数据，不能确保第一行数据宽度最宽，预留5个像素。有兴趣的朋友可以先判断最大的td宽度都在进行设置
            var bodyTdWidth = bodyTd.width() + 5;
            var width = 0;
            //如果头部列名宽度比主体数据宽度宽，则它们的宽度都设为头部的宽度。反之亦然
            if (headerTdWidth > bodyTdWidth) {
                width = headerTdWidth;
                bodyTd.width(width);
                headerTd.width(width);
                totalWidth += width;
            } else {
                width = bodyTdWidth;
                headerTd.width(width);
                bodyTd.width(width);
                totalWidth += width;
            }
        });
        var headerTable = $(".datagrid-header-inner table:first-child");
        var bodyTable = $(".datagrid-body table:first-child");
        //循环完毕即能得到总得宽度设置到头部table和数据主体table中
        headerTable.width(totalWidth);
        bodyTable.width(totalWidth);
        bodyTds.each(function (i, obj) {
            var headerTd = $(headerTds.get(i));
            var bodyTd = $(bodyTds.get(i));
            var headerTdWidth = headerTd.width();
            bodyTd.width(headerTdWidth);
        });
	}
	
	return easyui;
}	