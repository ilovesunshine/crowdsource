<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="payConfig.form.title"/></title>
<style>
  .toggler {
      display: none;  
            position: absolute; 
            top: 90px; 
            left: 240px; 
            width: 280px; 
            height: 60px; 
            padding: 16px;
            z-index:1002;
            overflow: auto;
            filter:alpha(opacity=50); /*IE滤镜，透明度50%*/
			-moz-opacity:0.5; /*Firefox私有，透明度50%*/
			opacity:0.5;/*其他，透明度50%*/
  }
  </style>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
</head>
<body>
<div class="pay_cont_sys">
	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
    <div class="barTitle">
		<div><fmt:message key="payConfig.form.title"/>
		 <div id="effect" class="toggler">
				     	 添加配置信息失败！
				  </div>
		</div>
	</div>
	<form id="payConfigForm" action="<c:url value='/sys/config/save'/>"  method="post" >
      <div class="ui-table ui-border">
       <table class="table">
       	<tbody>
			<tr>
				<td class="inputLabelTd"><fmt:message key="payConfig.form.pkey"/>：</td>
				<td class="inputTd">
					<input id="pkey" name="pkey" type="text" value="<c:out value='${payConfig.pkey}'/>" class="text"/>
				</td>

				<td class="inputLabelTd"><fmt:message key="payConfig.form.pvalue"/>：</td>
				<td class="inputTd">
					<input id="pvalue" name="pvalue" type="text" class="text" value="<c:out value='${payConfig.pvalue}'/>" />
				</td>

			</tr>
			<tr>
				<td class="inputLabelTd"><fmt:message key="payConfig.form.memo"/>：</td>
				<td class="inputTd" colspan="3">
					<textarea id="memo" name="memo"  class="textarea"> <c:out value='${payConfig.memo}'/>  </textarea>
				</td>

			</tr>
		</tbody>
		<tfoot class="footTd">
			<tr>
				<td class="inputTd" colspan="4">
					<button id="submit_button" class="button" type="button" onclick="save(this);"><fmt:message key='button.submit'/></button>&nbsp;&nbsp;
        			<button id="reset_button" class="button" type="button" onclick="resetForm();"><fmt:message key='button.reset'/></button>&nbsp;&nbsp;
        			<button id="back_button" class="button" type="button" onclick="window.location = '<c:url value="/sys/config/show"/>'"><fmt:message key='button.back'/></button>
				</td>
			</tr>
		</tfoot>
	  </table>
	 </div>
	</form>
</div>
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>

<script type="text/javascript">

	function save(button){
		form = button.form;
		form.submit();
	}
	function resetForm(){
		$("#pkey").val('');
		$("#pvalue").val('');
		$("#memo").val('');
	}
	function runEffect() {
	    // get effect type from
	    var selectedEffect = 'shake';

	    // most effect types need no options passed by default
	    var options = {};
	    // some effects have required parameters
	    if ( selectedEffect === "scale" ) {
	      options = { percent: 100 };
	    } else if ( selectedEffect === "size" ) {
	      options = { to: { width: 280, height: 185 } };
	    }

	    // run the effect
	    $( "#effect" ).show( selectedEffect, options, 500, callback );
	  };

	  //callback function to bring a hidden box back
	  function callback() {
	    setTimeout(function() {
	      $( "#effect:visible" ).removeAttr( "style" ).fadeOut();
	    }, 1000 );
	  };
	  $(document).ready(function() {
		  <c:if test='${ret!=null && !ret.flag}'>
			runEffect();
		</c:if>
	  });
</script>
</body>
</html>
