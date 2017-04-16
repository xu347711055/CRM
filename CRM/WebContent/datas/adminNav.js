var navs = [{
	"title": "客户管理",
	"icon": "fa-cubes",
	"spread": true,
	"children": [{
		"title": "客户档案",
		"icon": "&#xe641;",
		"href": "/CRM/customer/custManage.action"
	}, {
		"title": "联系人管理",
		"icon": "&#xe63c;",
		"href": "/CRM/customer/listContByUser.action"
	}, {
		"title": "联系记录",
		"icon": "&#xe63c;",
		"href": "/CRM/customer/listRecordByUser.action"
	}, {
		"title": "客户策略",
		"icon": "&#xe609;",
		"href": "/CRM/customer/strategy.action"
	}]
}, {
	"title": "统计分析",
	"icon": "&#x1002;",
	"spread": false,
	"children": [{
		"title": "客户来源组成",
		"icon": "fa-check-square-o",
		"href": "/CRM/statistic/analyse.action?code=source&chartName=客户来源组成"
	},{
		"title": "客户等级组成",
		"icon": "fa-check-square-o",
		"href": "/CRM/statistic/analyse.action?code=level&chartName=客户等级组成"
	},{
		"title": "客户性质组成",
		"icon": "fa-check-square-o",
		"href": "/CRM/statistic/analyse.action?code=custNature&chartName=客户性质组成"
	}]
},{
	"title": "系统设置",
	"icon": "fa-cogs",
	"spread": false,
	"children": [{
		"title": "用户管理",
		"icon": "fa-table",
		"href": "/CRM/system/listUser.action"
	}, {
		"title": "Navbar组件",
		"icon": "fa-navicon",
		"href": "navbar.html"
	}, {
		"title": "Laytpl+Laypage",
		"icon": "&#xe628;",
		"href": "paging.html"
	}]
}];