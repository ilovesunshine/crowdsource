<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${csssuffix}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${csssuffix}'/>" media="screen, projection"/> 
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/themeDS.${csssuffix}'/>"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/common.${csssuffix}'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/menu.${csssuffix}'/>"/>
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
<body>
<div class="pay_cont_sys">
	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
	<div class="barTitle">
			<div>系统参数配置
				  <div id="effect" class="toggler">
				     	 <c:out value="${ret.msg}"/>
				  </div>
			</div>
	</div>
	<div id="dialog" title="选择记录" style="display:none;valign:middle" >
		请选择一条记录进行操作。
	</div>
	<div id="deletedialog" title="删除记录" style="display:none;valign:middle" >
		请选择要删除的记录。
	</div>
	<form id="queryForm"  class="bizform" action="<c:url value='/sys/config/show'/>"  method="get">
    	<div class="row"> 
    		<div class="query-cell">
	        	<button id="addBtn" class="button" type="button" onclick="add();"><fmt:message key='button.add'/></button>
	        	<button id="editBtn" class="button" type="button" onclick="edit();"><fmt:message key='button.edit'/></button>
			 	<button id="viewBtn" class="button" type="button" onclick="view();"><fmt:message key='button.view'/></button>
			   <button id="deleteBtn" class="button" type="button" onclick="batchDelete();"><fmt:message key='button.delete'/></button>
	    	</div>
	    </div>
	<table id="parameterslist" style="table-layout:fixed" class="display">
        <thead>
          <tr>
           	<th width="15px;"><input type="checkbox" id="checkedAll" onClick="selectedAll('queryForm',this.checked)"/>选择</th>
            <th width="50px;">参数名</th>
            <th width="100px;">参数值</th>
            <th width="80px;">备注</th>
            <th width="40px;">创建时间</th>
            <th width="40px;">更新时间</th>
          </tr>
        </thead>
     </table>
     </form>
</div>

<!-- 引入尾文件-->
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	<c:if test='${ret!=null}'>
		runEffect();
	</c:if>
	var imgUrl = "<c:url value='/styles/images/loading.gif'/>";
	var sourceUrl = "<c:url value='/sys/config/list'/>";
    var sortColumns= "PKEY,PKEY,PVALUE,MEMO,CREATE_TIME,UPDATE_TIME";
    var sort = [[ 4, "desc" ]];
    /*
	data: The data for the cell
	type: this will be 'filter', 'display', 'type' or 'sort'.
	row: The full data source for the row (not based on columns.data)
	meta: An object that contains additional information about the cell being requested. This object contains the following properties:
		row - The row index for the requested cell. See row().index().
		col - The column index for the requested cell. See column().index().
		settings - The DataTables.Settings object for the table in question. This can be used to obtain an API instance if required.
	*/
    function renderCheckBox(data, type, row, meta){
    	return '<input name="ids" type="checkbox" value="'+data+
		'" id="someCheckbox"+'+data+' onchange="changCheckboxChecked(\'ids\',\'queryForm\',\'checkedAll\');"'
		+' name="someCheckbox" />';
	}
	var aocolumns = [
	    {"mDataProp": "pkey", "bSortable": false},
	    {"mDataProp": "pkey", },
		{"mDataProp": "pvalue"},
		{"mDataProp": "memo"},
		{"mDataProp": "createTimeStr"},
		{"mDataProp": "updateTimeStr"}
	];
	var columns = [ 
	    { "aTargets": [ 0 ] ,"render":  renderCheckBox},
	    { "aTargets": [ 0 ] }, 
	    { "aTargets": [ 1 ] }, 
	    { "aTargets": [ 2 ] }, 
	    { "aTargets": [ 3 ] }, 
	    { "aTargets": [ 5 ] }
	];
	initDataTable("#parameterslist",aocolumns,columns,sortColumns,sourceUrl,imgUrl,sort);
}

);
function toDetailPage(pkey){
	location.href="<c:url value='/sys/config/detail'/>?id="+pkey;
}
function add(){
	location.href="<c:url value='/sys/config/add'/>";
}
function batchDelete(){
	 var count = $("[name='ids']:checked").length;
	 if(count==0){
		 $("#deletedialog").dialog({
				buttons: [
				          	{ text: "确定", click: function() {
				          					$( this ).dialog( "close" );
				          			} } 
				          ] 
			});
		 return;
	 }
	 var now = 0;
	 var ids = '';
	 $("[name='ids']:checked").each(function(){
		 now ++;
		 ids+=$(this).val();
		 if(now!=count){
			 ids+=','
		 }
	 });
	 location.href="<c:url value='/sys/config/remove'/>?ids="+ids;
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
  function edit(){
	  if($("[name='ids']:checked").length==1){
		  location.href="<c:url value='/sys/config/edit'/>?id="+$("[name='ids']:checked").attr("value");
	  }else{
		  $("#dialog").dialog({
				buttons: [
				          	{ text: "确定", click: function() {
				          					$( this ).dialog( "close" );
				          			} } 
				          ] 
			});
	  }
  }
  function view(){
	  if($("[name='ids']:checked").length==1){
		  location.href="<c:url value='/sys/config/detail'/>?id="+$("[name='ids']:checked").attr("value");
	  }else{
		  $("#dialog").dialog({
				buttons: [
				          	{ text: "确定", click: function() {
				          					$( this ).dialog( "close" );
				          			} } 
				          ] 
			});
	  }
  }
</script>
</body>
</html>