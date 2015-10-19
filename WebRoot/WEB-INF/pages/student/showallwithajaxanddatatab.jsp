<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Ajax request demo</title>

</head>
<body>
	<div style="width:600px;align:center" >
	<table cellpadding="10" cellspacing="0" width="200px" border="0" class="display" id="example">
        <thead>
          <tr>
            <th>name</th>
            <th>id</th>
          </tr>
        </thead>
        <tfoot>
          <tr>
            <th>name</th>
            <th>id</th>
          </tr>
        </tfoot>
      </table>
    </div>
<script type="text/javascript">
$(document).ready(function() {
    $('#example').dataTable( {
    	"aaSorting": [
    	      		[ 2, "desc" ],
    	      		[ 1, "asc" ]
    	      		],
    	"bProcessing": true,  
        "bServerSide": true, 
        "oLanguage": {
        	"sUrl": "<c:url value='/styles/cn.txt'/>"
        	},
        "sPaginationType": "full_numbers",
        "sAjaxSource": "<c:url value='/springmvc/getdata'/>",
        "fnServerData": retrieveData 
    } );
 	// 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行
    function retrieveData( sSource111,aoData111, fnCallback111) {
        $.ajax({
            url : sSource111,//这个就是请求地址对应sAjaxSource
            data : {"aoData":JSON.stringify(aoData111),"sortColumn":"name,id"},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
            type : 'post',
            dataType : 'json',
            async : false,
            success : function(result) {
                fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
            },
            error : function(msg) {
            }
        });
    }
    $('#example tfoot th').each( function () {
        var title = $('#example thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
    } );
 
    // DataTable
    var table = $('#example').dataTable();
 
    // Apply the search
    table.columns().every( function () {
        var that = this;
        $('input', this.footer() ).on( 'keyup change', function () {
            alert("");
            that.search( this.value ).draw();
        } );
    } );
} );
</script>
</body>
</html>