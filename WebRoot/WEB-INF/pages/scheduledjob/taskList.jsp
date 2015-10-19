<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common.jsp" %>
<html>
<style type="text/css">
.datagrid-mask {
	background: #ccc;
}

.datagrid-mask-msg {
	border-color: #95B8E7;
}

.datagrid-mask-msg {
	background: #ffffff url('../images/loading.gif') no-repeat scroll 5px
		center;
}

.datagrid-mask {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	opacity: 0.3;
	filter: alpha(opacity = 30);
	display: none;
}

.datagrid-mask-msg {
	position: absolute;
	top: 50%;
	margin-top: -20px;
	padding: 12px 5px 10px 30px;
	width: auto;
	height: 16px;
	border-width: 2px;
	border-style: solid;
	display: none;
}

.list_table {
	border: 1px solid #CCCCCC;
	border-collapse: collapse;
	color: #333333;
	margin: 0 0 0;
	width: 100%;
}
.add_table {
	border: 1px solid #CCCCCC;
	border-collapse: collapse;
	color: #333333;
	margin: 0 0 0;
	width: 600px;
	align:center;
}
.add_table td {
	line-height: 2em;
	font-size: 12px;
	vertical-align: central;
	align: left;
}
.list_table tbody td {
	border-top: 1px solid #CCCCCC;
	text-align: center;
}

.list_table th {
	line-height: 1.2em;
	vertical-align: top;
}

.list_table td {
	line-height: 2em;
	font-size: 12px;
	vertical-align: central;
	align: left;
}

.list_table td input {
	width: 90%;
}

.list_table tbody tr:hover th,.list_table tbody tr:hover td {
	background: #EEF0F2;
}

.list_table thead tr {
	background: none repeat scroll 0 0 #09f;
	color: #fff;
	font-weight: bold;
	border-bottom: 1px solid #CCCCCC;
	border-right: 1px solid #CCCCCC;
}
.span_add{
	background: none repeat scroll 0 0 #fff;
	font-weight: bold;
	border-bottom: 1px solid #CCCCCC;
	border-right: 1px solid #CCCCCC;
	align:center;
	text-align:cener;
	width: 100%;
}
</style>
</head>

<title>task</title>
<body>
<div class="main">	
	<div class="barTitle">
		<div class="content">
			<a href="#"> </a>
			<span>计划任务</span>
		</div>
	</div>
	<div class="ui-table ui-widget ui-margin">
			<div class="nav">
				<button id="addBtn" onclick="showAddPanel();">增加</button>
			</div>
			<div class="ui-jqgrid ui-widget ui-widget-content">
			<table class="table forview">
				<tr class="ui-jqgrid-labels">
					<td class="ui-state-default ui-th-column ui-th-ltr" colspan="8" style="width: 70%">任务配置</td>
					<td class="ui-state-default ui-th-column ui-th-ltr" colspan="3" style="width: 30%">上次执行情况</td>
				</tr>
				<tr class="ui-jqgrid-labels">
					<td class="ui-state-default ui-th-column ui-th-ltr">操作</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">任务名</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">任务启用状态</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">类全名</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">起始时间</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">停止时间</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">当前状态</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">Cron表达式</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">触发时间</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">执行时间</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">执行状态</td>
				</tr>
				<c:forEach items="${taskList}" var="job">
					<tr class="ui-widget-content jqgrow ui-row-ltr">
						<td class="inputTd" style="white-space: normal;">
							<a href="javascript:;" onclick="deleteJob('${job.jobId}')">强制删除JOB</a><br/>
							<a href="javascript:;" onclick="updateCron('${job.jobId}')">更新cron</a>
						</td>
						<td class="inputTd"  style="white-space: normal;"
							title="<c:out value='${job.jobName}' />">
							<a href="javascript:detail('<c:out value='${job.jobId}'/>');" id="detailpage"><c:out value="${job.jobName}" /></a>
						</td>
						
						<td class="inputTd" style="white-space: normal;">
							<c:out value="${job.jobIsInUseStr}" />
							<c:choose>
								<c:when test="${job.jobIsInUse=='1' }">
									<a href="javascript:;"
										onclick="changeJobStatus('${job.jobId}','stop')">禁用</a>&nbsp;
								</c:when>
								<c:otherwise>
									<a href="javascript:;"
										onclick="changeJobStatus('${job.jobId}','start')">开启</a>&nbsp;
								</c:otherwise>
							</c:choose>
						</td>
						<td class="inputTd"  style="white-space: normal;">
							<c:out value="${job.beanClass}" />
						</td>
						<td class="inputTd" style="white-space: normal;" ><c:out value="${job.startTime}" /></td>
						<td class="inputTd" style="white-space: normal;" ><c:out value="${job.endTime}" /></td>
						<td class="inputTd" style="white-space: normal;">
						   <c:choose>
								<c:when test="${job.jobStatus=='NORMAL'}">
								运行中 <a href="javascript:;"
										onclick="changeJobStatus('${job.jobId}','paused')">暂停</a>&nbsp;
								</c:when>
								<c:when test="${job.jobStatus=='PAUSED'}">
								暂停 <a href="javascript:;"
										onclick="changeJobStatus('${job.jobId}','resume')">回复</a>&nbsp;
								</c:when>
								<c:when test="${job.jobStatus=='BLOCKED'}">
								阻塞中 <a href="javascript:;"
										onclick="changeJobStatus('${job.jobId}','paused')">暂停</a>&nbsp;
								</c:when>
							</c:choose>
						</td>
						<td class="inputTd" style="white-space: normal;"><c:out value="${job.cronExpression}" /></td>
						<td class="inputTd" style="white-space: normal;"><c:out value="${job.preFireTime}" /></td>
						<td class="inputTd" style="white-space: normal;">${job.duraTime}秒</td>
						<td class="inputTd" style="white-space: normal;">
						  ${job.lastExecStatusStr}
						</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
	</div>
	<div id="addJobDiv">
	<form id="addForm" method="post">
		<table class="add_table">
			<tr>
				<td class="inputLabelTd">Job名称</td>
				<td><input type="text" name="jobName" id="jobName"></input></td>
			</tr>
			<tr>
				<td class="inputLabelTd">Job分组</td>
				<td><input type="text" name="jobGroup" id="jobGroup"></input></td>
			</tr>
			
			<tr>
				<td class="inputLabelTd">Cron表达式 <input type="hidden" name="jobStatus" value="0"></input></td>
				<td><input type="text" name="cronExpression"
							id="cronExpression"></input></td>
			</tr>
			<tr>
				<td class="inputLabelTd">Job描述</td>
				<td><input type="text" name="description" id="description"></input></td>
			</tr>
			<tr>
				<td class="inputLabelTd">是否允许并发执行</td>
				<td><select name="isConcurrent" id="isConcurrent">
								<option value="1">允许</option>
								<option value="0">不允许</option>
						</select></td>
			</tr>
			<tr>
				<td class="inputLabelTd">类全路径</td>
				<td><input type="text" name="beanClass" id="beanClass"></input></td>
			</tr>
			<tr>
				<td class="inputLabelTd">Spring Id </td>
				<td><input type="text" name="springId" id="springId"></input></td>
			</tr>
			<tr>
				<td class="inputLabelTd">方法名称 </td>
				<td><input type="text" name="methodName" id="methodName"></input></td>
			</tr>
			
			<tr>
				<td class="inputLabelTd">起始时间</td>
				<td class="inputTd">
					<input id="startTime" name="startTime" type="text" class="Wdate" onClick="WdatePicker()"/>
				</td>
			</tr>
			<tr>
				<td class="inputLabelTd">结束时间</td>
				<td class="inputTd">
					<input id="endTime" name="endTime" type="text" class="Wdate" onClick="WdatePicker()"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" onclick="add()" value="保存" />
				<input type="button" onclick="cancel()" value="取消" />
				</td>
			</tr>
		</table>
	</form>
	</div>
	<script>
		function cancel(){
			$("#addJobDiv").hide();
		}
		function showAddPanel(){
			$("#addJobDiv").show();
		}
		function validateAdd() {
			if ($.trim($('#jobName').val()) == '') {
				alert('name不能为空！');
				$('#jobName').focus();
				return false;
			}
			if ($.trim($('#jobGroup').val()) == '') {
				alert('group不能为空！');
				$('#jobGroup').focus();
				return false;
			}
			if ($.trim($('#cronExpression').val()) == '') {
				alert('cron表达式不能为空！');
				$('#cronExpression').focus();
				return false;
			}
			if ($.trim($('#beanClass').val()) == '' && $.trim($('#springId').val()) == '') {
				$('#beanClass').focus();
				alert('类路径和spring id至少填写一个');
				return false;
			}
			if ($.trim($('#methodName').val()) == '') {
				$('#methodName').focus();
				alert('方法名不能为空！');
				return false;
			}
			return true;
		}
		function add() {
			if (validateAdd()) {
				showWaitMsg();
				$.ajax({
					type : "POST",
					async : false,
					dataType : "JSON",
					cache : false,
					url : "<c:url value='/task/add'/>",
					data : $("#addForm").serialize(),
					success : function(data) {
						hideWaitMsg();
						if (data.flag) {
							location.reload();
						} else {
							alert(data.msg);
						}

					}
				});
			}
		}
		function changeJobStatus(jobId, cmd) {
			showWaitMsg();
			$.ajax({
				type : "POST",
				async : false,
				dataType : "JSON",
				cache : false,
				url : "<c:url value='/task/changeJobStatus'/>",
				data : {
					jobId : jobId,
					cmd : cmd
				},
				success : function(data) {
					hideWaitMsg();
					if (data.flag) {
						location.reload();
					} else {
						alert(data.msg);
					}

				},
				error : function(){
					hideWaitMsg();
					alert("请求失败！");
				}
			});
		}
		function updateCron(jobId) {
			var cron = prompt("输入cron表达式！", "");
			if (cron) {
				showWaitMsg();

				$.ajax({
					type : "POST",
					async : false,
					dataType : "JSON",
					cache : false,
					url : "<c:url value='/task/updateCron'/>",
					data : {
						jobId : jobId,
						cron : cron
					},
					success : function(data) {
						hideWaitMsg();
						if (data.flag) {
							location.reload();
						} else {
							alert(data.msg);
						}

					}
				});
			}
		}
		function showWaitMsg(msg) {
			if (msg) {

			} else {
				msg = '正在处理，请稍候...';
			}
			var panelContainer = $("body");
			$("<div id='msg-background' class='datagrid-mask' style=\"display:block;z-index:10006;\"></div>").appendTo(panelContainer);
			var msgDiv = $("<div id='msg-board' class='datagrid-mask-msg' style=\"display:block;z-index:10007;left:50%\"></div>").html(msg).appendTo(
					panelContainer);
			msgDiv.css("marginLeft", -msgDiv.outerWidth() / 2);
		}
		function deleteJob(jobId) {
				showWaitMsg();
				$.ajax({
					type : "POST",
					async : false,
					dataType : "JSON",
					cache : false,
					url : "<c:url value='/task/deletejob'/>",
					data : {
						jobId : jobId
					},
					success : function(data) {
						hideWaitMsg();
						if (data.flag) {
							location.reload();
						} else {
							alert(data.msg);
						}

					}
				});
		}
		function hideWaitMsg() {
			$('.datagrid-mask').remove();
			$('.datagrid-mask-msg').remove();
		}
		function detail(jobId){
			location.href="<c:url value='/task/getDetailInfo'/>?jobId="+jobId;
		}
	</script>
</body>
</html>




