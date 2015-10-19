<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/inc/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理员</title>
<%@ include file="/pages/inc/header_sys.jsp" %>
</head>

<body>
<%@ include file="/pages/inc/common.jsp" %>
<div class="pay_cont_sys">
<%@ include file="/pages/inc/menu_sys.jsp" %>
	<div class="barTitle">
		<span>管理员</span>
	</div>
	<form id="payAdminsForm"  class="bizform">
		<div class="row"> 
            <div class="cell">&nbsp;&nbsp;用户名：<input id="userName" name="userName" type="text" class="text" value="<c:out value='${payAdmins.userName}'/>"/><button id="query_button" class="button" type="button" onclick="query();">查询</button>
			</div>
            <!--<div class="querycell">
				<button id="query_button" class="button" type="button" onclick="query();"><fmt:message key='button.search'/></button>
            </div>-->
		</div>
    </form>
	<div class="ui-table ui-margin">
    	<div class="nav">
        	<button id="addBtn" class="button" type="button" onclick="add();">新增</button>
        	<button id="editBtn" class="button" type="button" onclick="edit();">编辑</button>
		   <button id="deleteBtn" class="button" type="button" onclick="batchDelete();">删除</button>
	    </div>
	    <table id="payAdminsTable"></table>
		<div id="payAdminsPager"></div>
	</div>
</div>

<!-- 引入尾文件-->
<%@ include file="/pages/inc/footer.jsp" %>

<script type="text/javascript">
	var payAdminsGrid;
    $(function(){

		function roleFmt (cellvalue, options, rowObject) {
			var string = '未知';
			if(cellvalue=='1'){
				string = "运营管理员";
			}else if(cellvalue=='2'){
				string = "系统管理员";
			}else if(cellvalue=='3'){
				string = "运营专员";
			}
			return string;
		}
		function statusFmt (cellvalue, options, rowObject) {
			var string = '未知';
			if(cellvalue=='0'){
				string = "锁定";
			}else if(cellvalue=='1'){
				string = "正常";
			}
			return string;
		}
		function deleteFmt (cellvalue, options, rowObject) {
			var string = '未知';
			if(cellvalue=='0'){
				string = "否";
			}else if(cellvalue=='1'){
				string = "是";
			}
			return string;
		}

    	payAdminsGrid = new biz.grid({
    		id : "#payAdminsTable",
    		url : "admins/list",
    		page : "<c:out value='${page.pageNo}' default='1'/>",
    		rowNum : "<c:out value='${page.pageSize}' default='15'/>",
			width : "auto",
			multiselect : true,
    		pager : "#payAdminsPager",
    		prmNames : {page:"pageNo",rows:"pageSize",sort:"orderFields",order:"order"},
    		sortname : "",
    		sortorder : "",
    		colModel : [
			   {name : "saId",hidden:true,key:true}, 
				{name : "userName",label:"用户名"},
				{name : "userRole",label:"角色",align:"center",formatter:roleFmt},
				{name : "userStatus",label:"状态",align:"center",formatter:statusFmt},
				{name : "lastLoginTime",label:"最后登录时间",align:"center"},
				{name : "createTime",label:"添加时间",align:"center"},
				{name : "deleteFlag",label:"是否删除",align:"center",formatter:deleteFmt}
    		],
    		serializeGridData:function(postData){//添加查询条件值
    			var obj = biz.utils.serializeObject("#payAdminsForm");//示例中心搜索"biz.utils方法详解"查看方法说明
    			$.extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		}
    	});
    })

	function query(){
		payAdminsGrid.setGridParam({"page":"1"});
		payAdminsGrid.trigger('reloadGrid');
	}
	function add(){
		window.location="admins/add";
	}
	
	function edit(rowid){
		rowid = payAdminsGrid.getGridParam("selarrrow");
		if(rowid == null || rowid.length == 0){
			showInfo("<fmt:message key='grid.edit.chooseColAlert'/>",3000);
			return ;
		}else if(rowid.length > 1){
			showInfo("<fmt:message key='grid.edit.onlyOne'/>",3000);
			return ;
		}
		var id = payAdminsGrid.getCell(rowid[0],"saId");
		window.location="admins/edit/" + id;
	}

	function batchDelete(rowid){
		var rowid = payAdminsGrid.getGridParam('selarrrow');
		if(rowid == null || rowid.length == 0){
			showInfo("<fmt:message key='grid.delete.chooseColAlert'/>",3000);
			return ;
		}
		new biz.alert({type:"confirm",message:I18N.msg_del_confirm,
			title:I18N.promp,callback:function(result){
	    		if (!result) {
	    			return;
	    		}
				var ids="";
				for(var i=0; i < rowid.length;i++){
					var id = payAdminsGrid.getCell(rowid[i],'saId');
					if(i>0){
						ids +=',';
					}
					ids += id;
				}
				window.location="admins/delete/" + ids;
			}
		});
	}
	
</script>
</body>
</html>