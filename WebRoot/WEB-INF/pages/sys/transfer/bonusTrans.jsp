<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>JointForce</title>
	<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${webConfig["csssuffix"]}'/>" media="screen, projection"/> 
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/themeDS.${webConfig["csssuffix"]}'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/components/jqgrid/jquery.jqgrid.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/components/tree/zTreeStyle.${webConfig["csssuffix"]}'/>" media="screen, projection"/>

	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/common.${webConfig["csssuffix"]}'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/menu.${webConfig["csssuffix"]}'/>"/>
	<script type="text/javascript" src="<c:url value='/js/extends/utils/DatePattern.${webConfig["jssuffix"]}'/>"></script>	
	<style>
	  input{
	   width :200px;
	  }
	  .bouns-info{
	  	font-size:16px;
	  	color:#ffa800;
	  	font-weight: bold;
	  }
	</style>
</head>

<body>
<%@ include file="/WEB-INF/pages/inc/shade.jsp" %>
<div class="pay_cont_sys">

	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
	<div class="barTitle">
		<span>红包转账</span>&nbsp;|&nbsp;
		<a href="<c:url value='/sys/transfer/show'/>">订单转账</a> 
	</div>
	<div style="padding-left:5px;">共需支付红包个数：<font class="bouns-info"><c:out value="${ bounsCount }" /></font>（个），共计：<font class="bouns-info"><c:out value="${ toatlAmount }" /></font>（元）。</div>
	<form id="bonusTransForm"  class="bizform">
		<div class="cell">
			<div class="labelcell">中奖人：<input id="payeeAccountName" name="payeeAccountName" type="text" class="text"/>
			<button id="query_button" class="kbtn kbtn_green kbtn30" type="button" onclick="query();" style="margin-left:10px;height:26px;">查询</button>
			<button id="query_button" class="kbtn kbtn_green kbtn30" type="button" onclick="payBouns();" style="margin-left:10px;height:26px;">立即支付</button>
			</div>
		</div>
	</form>
    <div class="ui-table ui-margin" style="margin-top:20px;">
	    <table id="bonusTransTable"></table>
		<div id="bonusTransPager"></div>
	</div>
</div>
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<script type="text/javascript">
var bonusTransGrid;
$(function(){
	bonusTransGrid = new biz.grid({
		id : "#bonusTransTable",
		url : "<c:url value='/sys/transfer/bonusTransList' />",
		rowNum : 50,
		width:"auto",
		multiselect:false,
		pager : "#bonusTransPager",
		prmNames : {page:"pageNo",rows:"pageSize",sort:"orderFields",order:"order"},
		sortname : "",
		sortorder : "",
		colModel : [
		    {name : "tId", hidden:true,key:true},
			{name : "orderNo",label:"红包编号",width:190,align:"center"},
			{name : "transId",label:"交易流水号",align:"center"},
			{name : "payeeAccountName",label:"中奖人名称",align:"center"},
			{name : "payeeJfId",label:"中奖人jfId",align:"center"},
			{name : "payeeAccountId",label:"中奖人资金账户ID",align:"center"},
			{name : "transAmountS",label:"红包金额",align:"right"},
			{name : "createTime",label:"红包中奖时间",align:"center"}
		],
		serializeGridData:function(postData){//添加查询条件值
			var obj = biz.utils.serializeObject("#bonusTransForm");//示例中心搜索"biz.utils方法详解"查看方法说明
			$.extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
			return obj;
		}
	});
});

function query(){
	bonusTransGrid.setGridParam({"page":"1"});
	bonusTransGrid.trigger('reloadGrid');
}

function payBouns(){
	var count = <c:out value="${ bounsCount }" />;
	if(count <= 0){
		new biz.alert({
      		type : "alert", 
      		message : "没有需要支付的红包",
      		title : "提示" 
    	});
	      return false ;
	}
	 new biz.alert({
		type:"confirm",
		message:'您是否确定立即支付所有红包？',
		title:"提示",
		callback:function(r){
			if(r){
				  showShade();
				  $.ajax({
			          url : "<c:url value='/sys/transfer/payBonus'/>",
			          type: "POST",
			          cache : false,
			          async: true,
			          success : function(data, textStatus) {
					  hideShade();
				       if(data.returncode == '1'){
				    	   new biz.alert({
			              		type : "alert", 
			              		message : "成功支付红包",
			              		title : "提示" 
			              });
					   }else{ 
						   new biz.alert({
			              		type : "alert", 
			              		message : "红包支付失败！" ,
			              		title : "提示" 
			              });
						}
			       	   
			       	   location.reload();
			          },
			          error : function() {	 
			          hideShade();     	   
			       	   new biz.alert({
			             		type : "alert", 
			             		message : "红包支付失败！" ,
			             		title : "提示" 
			             });
			          }
			    });
			}
		}
	}); 
}
</script>
</body>
</html>
