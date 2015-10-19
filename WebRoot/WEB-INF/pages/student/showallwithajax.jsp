<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Ajax request demo</title>
<script type="text/javascript">
$.ajax({
    url: './getinfowithajax',
    type: 'POST',
    dataType: 'JSON',
    timeout: 30000, //超时时间：30秒
    data: {
        name:"miaozhengsheng"
    },
    error: function () {
        alert('error');
    },
    success: function (json) {
        var content = '';
        var list = json.list;
        for(var i=0;i<list.length;i++){
        	content+=list[i].id+":"+list[i].name+"</br>";
        }
        $("#stuinfo").append(content);
    }


});
</script>
</head>
<body>
	<h1>content:<c:url value='/styles/reset.${webConfig["csssuffix"]}'/></h1>
	<c:out value='${csstheme}'></c:out>
	<div id="stuinfo"></div>
</body>
</html>