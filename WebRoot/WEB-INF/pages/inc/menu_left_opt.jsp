<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<style>
.span2{width: 233px !important;margin-left: 5px !important;background-color: #f7f7f7;border:1px solid #e5e5e5}
</style>
<!--左侧导航栏-->
<div class="span2">
	<div class="accordion" id="menu" style="height: 423px; margin-bottom: 0px;">
	<div class="accordion-group">
	    <div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" href="#collapse8" title="" onclick="changeIcon(i8)"><i id="i8" class="icon-chevron-down"></i>&nbsp;众包订单跟踪</a>
		</div>
		<div id="collapse8" class="accordion-body collapse in">
			<div class="accordion-inner">
				<ul class="nav nav-list">
				<li><a href="/admin/order?status=0" target="contentFrame"><i class="icon-list-alt"></i>&nbsp;对外发包审核</a></li>
				<li><a href="/admin/order" target="contentFrame"><i class="icon-list-alt"></i>&nbsp;已审核订单监控</a></li>
				<li><a href="/admin/order?status=1" target="contentFrame"><i class="icon-list-alt"></i>&nbsp;投诉订单监控</a></li>
				</ul>
			</div>
		</div>
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" href="#collapse1" title="" onclick="changeIcon(i1)"><i id="i1" class="icon-chevron-right"></i>&nbsp;帐号管理</a>
		</div>
		<div id="collapse1" class="accordion-body collapse">
			<div class="accordion-inner">
				<ul class="nav nav-list">
					<li><a href="/useraccout" target="contentFrame"><i class="icon-user"></i>&nbsp;帐号管理</a></li>
					<li><a href="/admin/userGroup" target="contentFrame"><i class="icon-user"></i>&nbsp;用户组管理</a></li>
					<li><a href="/admin/showAccountRule" target="contentFrame"><i class="icon-asterisk"></i>&nbsp;帐号策略设置</a></li>					
				</ul>
			</div>
		</div>
		<div class="accordion-heading">
			<a class="accordion-toggle" href="/admin/auditLogin" target="contentFrame" title=""><i id="i4" class="icon-cog"></i>&nbsp;日志监控</a>
		</div>
		<div class="accordion-heading">
			<a class="accordion-toggle" href="<c:url value='/opt/auth'/>" target="contentFrame" title=""><i id="i4" class="icon-cog"></i>&nbsp;支付管理</a>
		</div>
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" href="#collapse6" title="" onclick="changeIcon(i6)"><i id="i6" class="icon-chevron-down"></i>&nbsp;运营设置</a>
		</div>
		<div id="collapse6" class="accordion-body collapse in">
			<div class="accordion-inner">
				<ul class="nav nav-list">
					<li><a href="https://dev.jointforce.com/inviteCode" target="contentFrame"><i class="icon-lock"></i>&nbsp;团队授权码发放</a></li>
					<li><a href="https://dev.jointforce.com/buEmailRule" target="contentFrame"><i class="icon-inbox"></i>&nbsp;允许注册邮箱规则</a></li>
				</ul>
			</div>
		</div>
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" href="#collapse7" title="" onclick="changeIcon(i7)"><i id="i7" class="icon-chevron-down"></i>&nbsp;task使用监控</a>
		</div>
		<div id="collapse7" class="accordion-body collapse in">
			<div class="accordion-inner">
				<ul class="nav nav-list">
				<li><a href="https://dev.jointforce.com/task/emportTask" target="contentFrame"><i class="icon-list-alt"></i>&nbsp;政府线任务分配导出</a></li>
				</ul>
			</div>
		</div>
	</div>
	</div>
      
</div>
<!--左侧导航栏-->
