<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<html>
<head>
<title>平台账户验签</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${csssuffix}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${csssuffix}'/>" media="screen, projection"/> 
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/themeDS.${csssuffix}'/>"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/common.${csssuffix}'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/menu.${csssuffix}'/>"/>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
</head>

<body>
<div class="pay_cont_sys">
	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
	<div class="barTitle">
		平台账户验签&nbsp;|&nbsp;
		<span>
			<a href="<c:url value='/sys/checkvalue/show'/>">企业/个人用户验签</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/checkvalue/showorder'/>">订单验签</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/checkvalue/showtrans'/>">交易验签</a> 
		</span>
	</div>
	<form id="queryForm"  class="bizform">
		<div class="row"> 
		<!-- 
            <div class="cell">&nbsp;&nbsp;用户名：<input id="extUsername" name="extUsername" type="text" class="text" value="<c:out value='${jfAccount.extUsername}'/>"/>
            <button id="query_button" class="button" type="button" onclick="query();">查询</button>
			</div>
		 -->
		</div>
    	<div class="nav">
    	
        	<button id="resetAllBtn" class="button" type="button" onclick="resetAll();">重置所有记录</button>
			<!--
        	<button id="restBtn" class="button" type="button" onclick="resetSel();">重置选中记录</button>
			-->
	    </div>
	    <table id="accountlist" style="table-layout:fixed" class="display">
        <thead>
          <tr>
          	<th width="60px;">账户ID</th>
            <th width="60px;">JF平台ID</th>
            <th width="110px;">账号</th>
            <th width="50px;">类别</th>
            <th width="100px;">验签值</th>
            <th width="50px;">状态</th>
            <th width="70px;">操作</th>
          </tr>
        </thead>
      </table>
      </form>
      
      <div id="recheckdialog" title="重新验签" style="display:none;valign:middle" >
		确认重新验签？
	  </div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	var sourceUrl = "<c:url value='/sys/checkvalue/queryjf'/>";
	var imgUrl = "<c:url value='/styles/images/loading.gif'/>";
    var sortColumns= "ACCOUNT_ID";
    var sort = [[ 0, "desc" ]];
    function renderType(data, type, row, meta){
    	var string = '未知';
		if(data == '01'){
			string = "资金账户";
		}else if(data == '02'){
			string = "冻结账户";
		}else if(data == '03'){
			string = "运营账户";
		}
		return string;
    }
    function renderStatus(data, type, row, meta){
    	var string = '未知';
		if(data == true){
			string = "<span style='color:greeb'>正常</span>";
		}else if(data == false){
			string = "<span style='color:red'>验签失败</span>";
		}
		return string;
    }
    function renderOperate(data, type, row, meta){
    	return '<a href="#" onClick="reCheckValue(\''+row.accountIdStr+'\')">重新验签</a>';
    }
	var aocolumns = [ 
	    {"mDataProp": "accountIdStr"},
		{"mDataProp": "jfId"},
		{"mDataProp": "extCardNo"},
		{"mDataProp": "useType"},
		{"mDataProp": "checkValue"},
		{"mDataProp": "checkFlag"}
	];
	var columnDefs = [ 
		{"aTargets": [3], "render": renderType},
		{"aTargets": [5], "render": renderStatus},
		{"aTargets": [6], "render": renderOperate}
	]; 

	initDataTable("#accountlist",aocolumns,columnDefs,sortColumns,sourceUrl,imgUrl,sort);
   
});

	function query(){
		listGrid.setGridParam({"page":"1"});
		listGrid.trigger('reloadGrid');
	}
	function resetAll(){
		window.location="jfresetAll";
	}
	
  function resetSingle(id,value){
		window.location="jfreset/" + id;
  }
  
  function reCheckValue(accountId){
		$("#recheckdialog").dialog({
			buttons: [
			          	{ text: "确定", click: function() {
			          			$( this ).dialog( "close" );
			          			exeReCheckValue(accountId);
			          		} 
			          	} ,
			          	{text: "取消", click: function() {
		          				$( this ).dialog( "close" );
		          			} 
			          	} ,
			         ] 
		});
	}
	function exeReCheckValue(accountId){  
		location.href="<c:url value='/sys/checkvalue/jfreset'/>?accountId="+accountId; 
	}
	
	function resetAll(){
		location.href="<c:url value='/sys/checkvalue/jfresetAll'/>";
	}
	
</script>
</body>
</html>