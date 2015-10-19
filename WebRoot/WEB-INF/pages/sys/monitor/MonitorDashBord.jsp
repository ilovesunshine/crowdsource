<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/inc/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统监控</title>
<%@ include file="/pages/inc/header_sys.jsp" %>
<style>
		.moni-container_div{
			width: 1190px;height: auto;margin: 0 auto;overflow: hidden;
		}
		.moni-container_div .container-base_row1{
			height:auto;
			margin-top:10px;
			padding-bottom: 150px;
			font-size:13px;
			border:1px solid #f3f3f3;
		}
		.moni-container_div .container-base_row2{
			height:auto;
			margin-top:10px;
			padding-bottom: 150px;
			font-size:13px;
			border:1px solid #f3f3f3;
		}
		.moni-container_div .container-base_row3{
			height:auto;
			margin-top:10px;
			padding-bottom: 150px;
			font-size:13px;
			border:1px solid #f3f3f3;
		}
		
		.moni_title_div{
			width: 1190px;
			float:left;
		}
		
		.moni_title_span{
			margin-top:12px;
			margin-left:10px;
			font-size:16px;
		}
		.moni_sub_title_span{
			font-size:15px;
			margin-right:15px;
		}
		
		.container-rows_col1{
			float:left;
			height:auto;
			width:350px;
			margin-left:10px;
			margin-top:12px;
			border:1px solid #f3f3f3;
		}
		.container-rows_col2{
			float:left;
			height:auto;
			width:350px;
			margin-left:10px;
			margin-top:12px;
			border:1px solid #f3f3f3;
		}
		.container-rows_col3{
			float:left;
			height:auto;
			width:350px;
			margin-left:10px;
			margin-top:12px;
			border:1px solid #f3f3f3;
		}
		.item-context-div{
			line-height:30px;
		}
		.item-context-span{
			color:cccccc;
		}
		
</style>
</head>
<body>
<%@ include file="/pages/inc/common.jsp" %>
<div class="pay_cont_sys">
<%@ include file="/pages/inc/menu_sys.jsp" %>
	<div class="barTitle jfpay-area-context"> 
		<span>2015-08-05系统监控数据</span>
	</div>
    <div class="moni-container_div">
		<div class="container-base_row1">
			<div class="moni_title_div"><span class="moni_title_span">操作</span></div><br>
			<div class="container-rows_col1">
				<div class="item-context-div">
					<div><span class="moni_sub_title_span">开户</span>
						<span class="item-context-span">成功：0 </span>
						<span class="item-context-span">失败 ：0 </span> 
						<span class="item-context-span">合计：0</span>
					</div><br>
					<div><span class="moni_sub_title_span">充值</span>
						<span class="item-context-span">成功：0 </span>
						<span class="item-context-span">失败 ：0 </span> 
						<span class="item-context-span">合计：0</span>
					</div><br>
					<div><span class="moni_sub_title_span">提现</span>
						<span class="item-context-span">成功：0 </span>
						<span class="item-context-span">失败 ：0 </span> 
						<span class="item-context-span">合计：0</span>
					</div>
				</div>
			</div>
			<div class="container-rows_col2">
				<div class="item-context-div">
					<div><span class="moni_sub_title_span">绑定银行卡</span>
						<span class="item-context-span">成功：0 </span>
						<span class="item-context-span">失败 ：0 </span> 
						<span class="item-context-span">合计：0</span>
					</div><br>
					<div><span class="moni_sub_title_span">修改账户信息</span>
						<span class="item-context-span">成功：0 </span>
						<span class="item-context-span">失败 ：0 </span> 
						<span class="item-context-span">合计：0</span>
					</div><br>
					<div><span class="moni_sub_title_span">账单支付</span>
						<span>成功：0 </span>
						<span>失败 ：0 </span> 
						<span>合计：0</span>
					</div>
				</div>
			</div>
			<div class="container-rows_col3"></div>
		</div>
		<div class="container-base_row2">
			<div class="moni_title_div"><span class="moni_title_span">接口</span></div><br>
			<div class="container-rows_col1">
				<div class="item-context-div">
					<div><span class="moni_sub_title_span">订单资金托管</span>
						<span class="item-context-span">成功：0 </span>
						<span class="item-context-span">失败 ：0 </span> 
						<span class="item-context-span">合计：0</span>
					</div><br>
					<div><span class="moni_sub_title_span">订单同意支付</span>
						<span class="item-context-span">成功：0 </span>
						<span class="item-context-span">失败 ：0 </span> 
						<span class="item-context-span">合计：0</span>
					</div><br>
					<div><span class="moni_sub_title_span">是否新订单</span>
						<span class="item-context-span">成功：0 </span>
						<span class="item-context-span">失败 ：0 </span> 
						<span class="item-context-span">合计：0</span>
					</div>
				</div>
			</div>
			<div class="container-rows_col2">
				<div class="item-context-div">
					<div><span class="moni_sub_title_span">订单结束</span>
						<span class="item-context-span">成功：0 </span>
						<span class="item-context-span">失败 ：0 </span> 
						<span class="item-context-span">合计：0</span>
					</div><br>
					<div><span class="moni_sub_title_span">订单取消</span>
						<span class="item-context-span">成功：0 </span>
						<span class="item-context-span">失败 ：0 </span> 
						<span class="item-context-span">合计：0</span>
					</div><br>
					<div><span class="moni_sub_title_span">是否是新订单</span>
						<span class="item-context-span">成功：0 </span>
						<span class="item-context-span">失败 ：0 </span> 
						<span class="item-context-span">合计：0</span>
					</div>
				</div>
			</div>
			<div class="container-rows_col3"></div>
		</div>
		<div class="container-base_row3">
			<div class="moni_title_div"><span class="moni_title_span">服务</span></div><br>
			<div class="container-rows_col1">
				<div class="item-context-div">
					<div><span class="moni_sub_title_span">下载同步EBC交易日志</span>
						<span class="item-context-span">成功：0 </span>
						<span class="item-context-span">失败 ：0 </span> 
						<span class="item-context-span">合计：0</span>
					</div><br>
					<div><span class="moni_sub_title_span">生成每月信用账单</span>
						<span class="item-context-span">成功：0 </span>
						<span class="item-context-span">失败 ：0 </span> 
						<span class="item-context-span">合计：0</span>
					</div><br>
					<div><span class="moni_sub_title_span">检查账单是否逾期</span>
						<span class="item-context-span">成功：0 </span>
						<span class="item-context-span">失败 ：0 </span> 
						<span class="item-context-span">合计：0</span>
					</div>
				</div>
			</div>
			<div class="container-rows_col2">
				<div class="item-context-div">
					<div><span class="moni_sub_title_span">红包失效</span>
						<span class="item-context-span">成功：0 </span>
						<span class="item-context-span">失败 ：0 </span> 
						<span class="item-context-span">合计：0</span>
					</div><br>
				</div>
			</div>
			<div class="container-rows_col3"></div>
		</div>
	</div>
</div>

<%@ include file="/pages/inc/footer.jsp" %>
<script type="text/javascript">

</script>
</body>
</html>