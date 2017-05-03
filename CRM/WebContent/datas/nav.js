var navs = [{
	"title": "客户管理",
	"icon": "&#xe613;",
	"spread": true,
	"children": [{
		"title": "客户档案",
		"icon": "&#xe622;",
		"href": "/CRM/customer/custManage.action"
	}, {
		"title": "联系人管理",
		"icon": "&#xe63b;",
		"href": "/CRM/customer/listContByUser.action"
	}, {
		"title": "联系记录",
		"icon": "&#xe63c;",
		"href": "/CRM/customer/listRecordByUser.action"
	}]
}, {
	"title": "统计分析",
	"icon": "&#xe62c;",
	"spread": false,
	"children": [{
		"title": "客户分析图表",
		"icon": "fa-check-square-o",
		"href": "/CRM/statistic/custChart.action"
	}]
},{
	"title": "附件管理",
	"icon": "&#xe624;",
	"spread": false,
	"children": [{
		"title": "附件管理",
		"icon": "&#xe62f;",
		"href": "/CRM/attachment/list.action"
	}]
}];