<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="page.title"/></title>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
</head>
<body>
<div class="pay_cont_sys">
	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
    <div class="barTitle">
		<span>行为审核详情</span>
	</div>
	<div class="ui-table ui-border">
		<table class="table forview">
			<tbody>
				<tr>
					<td class="inputLabelTd">ID</td>
					<td class="inputTd"><c:out value='${payAudit.actId}'/></td>
					<td class="inputLabelTd">操作人</td>
					<td class="inputTd"><c:out value='${payAudit.actor}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">用户名</td>
					<td class="inputTd"><c:out value='${payAudit.userid}'/></td>
					<td class="inputLabelTd">用户ID</td>
					<td class="inputTd"><c:out value='${payAudit.personuuid}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">组织名称</td>
					<td class="inputTd"><c:out value='${payAudit.orgname}'/></td>
					<td class="inputLabelTd">组织ID</td>
					<td class="inputTd"><c:out value='${payAudit.orguuid}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">雇佣者组织</td>
					<td class="inputTd"><c:out value='${payAudit.enrolledOrg}'/></td>
					<td class="inputLabelTd">雇佣者组织ID</td>
					<td class="inputTd"><c:out value='${payAudit.enrolledOrguuid}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">账户类型</td>
					<td class="inputTd">
						<c:out value='${payAudit.accountTypeName}'/>
					</td>
					<td class="inputLabelTd">操作结果</td>
					<td class="inputTd">
						<c:out value='${payAudit.actResultName}'/>
					</td>
				</tr>
				<tr>
					<td class="inputLabelTd">行为</td>
					<td class="inputTd"><c:out value='${payAudit.actAction}'/></td>
					<td class="inputLabelTd">事件编码</td>
					<td class="inputTd"><c:out value='${payAudit.actCode}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">功能模块</td>
					<td class="inputTd"><c:out value='${payAudit.actObj}'/></td>
					<td class="inputLabelTd">登出时间</td>
					<td class="inputTd"><c:out value='${payAudit.logoutTime}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">登录ID</td>
					<td class="inputTd"><c:out value='${payAudit.loginid}'/></td>
					<td class="inputLabelTd">登录方式</td>
					<td class="inputTd"><c:out value='${payAudit.loginMode}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">时间</td>
					<td class="inputTd"><c:out value='${payAudit.actTimeStr}'/></td>
					<td class="inputLabelTd">事件项目编号</td>
					<td class="inputTd"><c:out value='${payAudit.actItemCode}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">事件项目序号</td>
					<td class="inputTd"><c:out value='${payAudit.actItemNum}'/></td>
					<td class="inputLabelTd">是否大字段</td>
					<td class="inputTd"><c:out value='${payAudit.isclob}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">事件</td>
					<td class="inputTd" style="WORD-WRAP: break-word"><c:out value='${payAudit.actMessage}'/></td>

					<td class="inputLabelTd">访问者IP</td>
					<td class="inputTd"><c:out value='${payAudit.actIp}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">签名</td>
					<td class="inputTd"><c:out value='${payAudit.signature}'/></td>
					<td class="inputLabelTd">扩展字段1</td>
					<td class="inputTd"><c:out value='${payAudit.ext1}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">扩展字段2</td>
					<td class="inputTd"><c:out value='${payAudit.ext2}'/></td>
					<td class="inputLabelTd">扩展字段3</td>
					<td class="inputTd"><c:out value='${payAudit.ext3}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">扩展字段4</td>
					<td class="inputTd"><c:out value='${payAudit.ext4}'/></td>
					<td class="inputLabelTd">扩展字段5</td>
					<td class="inputTd" colspan="0"><c:out value='${payAudit.ext5}'/></td>
				</tr>
			</tbody>
			<tfoot class="footTd">
				<tr>
					<td class="inputTd" colspan="4">
            			<button id="back_button" class="button"  type="button" onclick="window.close()">关闭</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<!-- 引入尾文件-->

<script type="text/javascript">

	function getAccountType(s){
		switch(s){
			case -1:return "游客";
			case 0:return "个人用户";
			case 1:return "企业用户";
			case 2:return "运营管理员";
			case 3:return "系统管理员";
			default:return "未知";
		}
	}

	function getActResult(s){
		if(s==1){
			return "成功";
		}else if(s==0){
			return "失败";
		}
	}

	window.onload = function(){
		$("#actResult").text(getActResult(<c:out value='${payAudit.actResult}'/>));
	}
</script>
</body>
</html>