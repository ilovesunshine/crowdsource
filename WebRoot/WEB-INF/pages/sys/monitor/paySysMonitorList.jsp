<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="java.lang.*"%>
<%@page import="java.util.*"%>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统监控</title>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
<style type="text/css">
.butOnHover {
	width: 100px;
	height: 32px;
	margin-top: 8px;
	border: none;
	color: #ffffff;
}

.info-title {
	font-size: 15px;
	font-weight: bold;
	border-bottom: 1px solid #ccc;
	padding-bottom: 2px;
}

.info-btn {
	color: #fff;
	background-color: #1dd2ad;
	padding: 2px 5px;
	cursor: pointer;
	font-size: 14px;
	border-radius: 5px;
}

.info-back-span {
	display: block;
	float: right;
}
.jfpay-area-context{
	margin-top:20px;
}
.jfpay-sys-moni-td{
	width:300px;
}
.jfpay-sys-moni-span{
	float:left;
	margin-left:5px;
	font-size:16px;
	font-weight: bold;
}

</style>
</head>

<body>
	<%@ include file="/WEB-INF/pages/common.jsp" %>
	<!--cont-->
	<div class="pay_cont_sys">
		<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
	<div class="barTitle jfpay-area-context"> 
			<span><c:out value="${showdate}"></c:out>系统监控数据</span>
			<!-- 
			<span style="float:right;margin-right:10px;margin-top:10px;font-size:15px;"><a href="/jfpay/paySysMonitorLogs">查看历史</a></span>
			 -->
	</div>
	<form id="paySysMonitorForm" class="bizform">
		<div class="row"> 
			<div class="cell">
				<div class="valuecell">
					<span style="margin-left:10px;">日期：</span>
					<input id="date" name="date" type="text" class="Wdate" style="width:100px;" value="${showdate}" onfocus="selectDate()" /> 
					<button id="query_button" class="button" style="margin-left:10px;" type="button" onclick="query();">查询</button>
				</div>
			</div>
		</div>
	</form>
		<span class="jfpay-sys-moni-span">用户操作行为</span><br>
		<!-- 表格数据 start -->
			<div class="pay_opt_main">
				<table id="b-bill-table" border="0" cellpadding="0" cellspacing="0"
					class="pay_opt_table">
					<tr>
						<th class="hj_tab_topbj pay-td-center">用户行为</th>
						<th class="hj_tab_topbj pay-td-center">成功数量</th>
						<th class="hj_tab_topbj pay-td-center">失败数量</th>
						<th class="hj_tab_topbj pay-td-center">合计</th>
						<th class="hj_tab_topbj pay-td-center">创建时间</th>
					</tr>
					<c:if test="${!empty itemlistopt}">
						<c:forEach items="${itemlistopt }" var="ite" varStatus="status">
							<tr>
								<td class="pay_tr pay-td-left jfpay-sys-moni-td"><c:out value="${ite.operateName }" /></td>
								<c:if test="${ite.optSuccess > 0}">
									<td class="pay_tr pay-td-center"><a href="<c:url value='/sys/monitorsub/show'/>?status=1&sysMonitorId=${ite.sysMonitorId }"><c:out value="${ite.optSuccess }" /></a></td>
								</c:if>
								<c:if test="${ite.optSuccess <= 0}">
									<td class="pay_tr pay-td-center"><c:out value="0" /></td>
								</c:if>
								<c:if test="${ite.optFail > 0}">
									<td class="pay_tr pay-td-center"><a href="<c:url value='/sys/monitorsub/show'/>?status=0&sysMonitorId=${ite.sysMonitorId }"><c:out value="${ite.optFail }" /></a></td>
								</c:if>
								<c:if test="${ite.optFail <= 0}">
									<td class="pay_tr pay-td-center"><c:out value="0" /></td>
								</c:if>
								<td class="pay_tr pay-td-center"><c:out value="${ite.optTotal }" /></td>
								<td class="pay_tr pay-td-center"><c:out value="${ite.createTime }" /></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
		<!-- 表格数据 end -->
		
		<br><span class="jfpay-sys-moni-span">接口调用情况</span><br>
		<!-- 表格数据 start -->
			<div class="pay_opt_main">
				<table id="b-bill-table" border="0" cellpadding="0" cellspacing="0"
					class="pay_opt_table">
					<tr>
						<th class="hj_tab_topbj pay-td-center">接口调用</th>
						<th class="hj_tab_topbj pay-td-center">成功数量</th>
						<th class="hj_tab_topbj pay-td-center">失败数量</th>
						<th class="hj_tab_topbj pay-td-center">合计</th>
						<th class="hj_tab_topbj pay-td-center">创建时间</th>
					</tr>
					<c:if test="${!empty itemlistiface}">
						<c:forEach items="${itemlistiface }" var="ite" varStatus="status">
							<tr>
								<td class="pay_tr pay-td-left jfpay-sys-moni-td"><c:out value="${ite.operateName }" /></td>
								<c:if test="${ite.optSuccess > 0}">
									<td class="pay_tr pay-td-center"><a href="<c:url value='/sys/monitorsub/show'/>?status=1&sysMonitorId=${ite.sysMonitorId }"><c:out value="${ite.optSuccess }" /></a></td>
								</c:if>
								<c:if test="${ite.optSuccess <= 0}">
									<td class="pay_tr pay-td-center"><c:out value="0" /></td>
								</c:if>
								<c:if test="${ite.optFail > 0}">
									<td class="pay_tr pay-td-center"><a href="<c:url value='/sys/monitorsub/show'/>?status=0&sysMonitorId=${ite.sysMonitorId }"><c:out value="${ite.optFail }" /></a></td>
								</c:if>
								<c:if test="${ite.optFail <= 0}">
									<td class="pay_tr pay-td-center"><c:out value="0" /></td>
								</c:if>
								<td class="pay_tr pay-td-center"><c:out value="${ite.optTotal }" /></td>
								<td class="pay_tr pay-td-center"><c:out value="${ite.createTime }" /></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
		<!-- 表格数据 end -->
		
		<br><span class="jfpay-sys-moni-span">定时任务调用</span><br>
		<!-- 表格数据 start -->
			<div class="pay_opt_main">
				<table id="b-bill-table" border="0" cellpadding="0" cellspacing="0"
					class="pay_opt_table">
					<tr>
						<th class="hj_tab_topbj pay-td-center">定时任务</th>
						<th class="hj_tab_topbj pay-td-center">成功数量</th>
						<th class="hj_tab_topbj pay-td-center">失败数量</th>
						<th class="hj_tab_topbj pay-td-center">合计</th>
						<th class="hj_tab_topbj pay-td-center">创建时间</th>
					</tr>
					<c:if test="${!empty itemlistiser}">
						<c:forEach items="${itemlistiser }" var="ite" varStatus="status">
							<tr>
								<td class="pay_tr pay-td-left jfpay-sys-moni-td"><c:out value="${ite.operateName }" /></td>
								<c:if test="${ite.optSuccess > 0}">
									<td class="pay_tr pay-td-center"><a href="<c:url value='/sys/monitorsub/show'/>?status=1&sysMonitorId=${ite.sysMonitorId }"><c:out value="${ite.optSuccess }" /></a></td>
								</c:if>
								<c:if test="${ite.optSuccess <= 0}">
									<td class="pay_tr pay-td-center"><c:out value="0" /></td>
								</c:if>
								<c:if test="${ite.optFail > 0}">
									<td class="pay_tr pay-td-center"><a href="<c:url value='/sys/monitorsub/show'/>?status=0&sysMonitorId=${ite.sysMonitorId }"><c:out value="${ite.optFail }" /></a></td>
								</c:if>
								<c:if test="${ite.optFail <= 0}">
									<td class="pay_tr pay-td-center"><c:out value="0" /></td>
								</c:if>
								<td class="pay_tr pay-td-center"><c:out value="${ite.optTotal }" /></td>
								<td class="pay_tr pay-td-center"><c:out value="${ite.createTime }" /></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
		<!-- 表格数据 end -->
		</div>
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<script type="text/javascript">
	
	function selectDate(){
		var md = new Date();
		md.setDate(md.getDate()-1);
		//控件初始化
		new WdatePicker({
			id : "#date",
			maxDate:md
		});
	}
	function query(){
		var date = $("#date").val();
		if(date == null || date == ''){
			new biz.alert({
				type : "alert",//信息提示类型，可选alert、confirm、prompt
				message : "请选择日期！",//提示信息
				title : "提示" //提示框标题
			});
			return;
	    }
		
		window.location = "<c:url value='/sys/monitor/show'/>?date="+date;
	}
	function formatNumber(num,pattern){   
		var strarr = num?num.toString().split('.'):['0'];   
		var fmtarr = pattern?pattern.split('.'):[''];   
		var retstr='';   
		  
		// 整数部分   
		var str = strarr[0];   
		var fmt = fmtarr[0];   
		var i = str.length-1;     
		var comma = false;   
		for(var f=fmt.length-1;f>=0;f--){   
		    switch(fmt.substr(f,1)){   
		      case '#':   
		        if(i>=0 ) retstr = str.substr(i--,1) + retstr;   
		        break;   
		      case '0':   
		        if(i>=0) retstr = str.substr(i--,1) + retstr;   
		        else retstr = '0' + retstr;   
		        break;   
		      case ',':   
		         comma = true;   
		         retstr=','+retstr;   
		        break;   
		     }   
		}   
		if(i>=0){   
		    if(comma){   
		      var l = str.length;   
		      for(;i>=0;i--){   
		         retstr = str.substr(i,1) + retstr;   
		        if(i>0 && ((l-i)%3)==0) retstr = ',' + retstr;   
		       }   
		     }   
		    else retstr = str.substr(0,i+1) + retstr;   
		}   
		  
		retstr = retstr+'.';   
		// 处理小数部分   
		str=strarr.length>1?strarr[1]:'';   
		fmt=fmtarr.length>1?fmtarr[1]:'';   
		i=0;   
		for(var f=0;f<fmt.length;f++){   
		    switch(fmt.substr(f,1)){   
		    case '#':   
		        if(i<str.length) retstr+=str.substr(i++,1);   
		        break;   
		    case '0':   
		        if(i<str.length) retstr+= str.substr(i++,1);   
		        else retstr+='0';   
		        break;   
		    }   
		}   
		return retstr.replace(/^,+/,'').replace(/\.$/,'');   
	}

	//返回账户明细页面
	$("#infoBackBtn").click(function(){
		var accountId = $("#accountId").val();
		window.location = "<c:url value='/opt/account/info'/>?accountId="+accountId;
	});
</script>
</body>
</html>
