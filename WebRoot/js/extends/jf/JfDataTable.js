	function initDataTable(id,aocolumns,columnDefs,sortColumns,sourceUrl,imgUrl,sort){
		var dt = $(id).DataTable( {
			"bAutoWidth":false,
			"columns": aocolumns,
			"columnDefs": columnDefs,
			"aaSorting": sort,
			"bProcessing": true, 
			"bFilter":false,
			"bServerSide": true, 
			"oLanguage": {
				"sLengthMenu": "每页显示 _MENU_ 条记录",
				"sZeroRecords": "抱歉， 没有找到",
				"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
				"sInfoEmpty": "没有数据",
				"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
				"oPaginate": {  
					"sFirst": "首页",  
					"sPrevious": "前一页",
					"sNext": "后一页",  
					"sLast": "尾页"  
				}, 
				"sZeroRecords": "没有检索到数据",  
				"sProcessing": "<img src='"+imgUrl+"' />"
				},
			"sPaginationType": "full_numbers",
			"sAjaxSource": sourceUrl,
			"fnServerData": function(sSource111,aoData111, fnCallback111){
				$.ajax({
					url : sSource111+"?"+$("#queryForm").serialize(),
					data : {"aoData":JSON.stringify(aoData111),"sortColumn":sortColumns},
					type : 'post',
					dataType : 'json',
					async : true,
					success : function(result) {
						fnCallback111(result);
					},
					error : function(msg) {
						alert("查询失败！");
					}
				});	
			} 
		} );
		
		var tbody = id + " tbody";
	    $(tbody).on( 'click', 'tr', function () {
	        $(this).toggleClass('row_selected');
	        $(this).find("input").prop('checked', $(this).hasClass('row_selected'));
	        changCheckboxChecked('ids','queryForm','checkedAll');
	    } );
	 
	    // Array to track the ids of the details displayed rows
	    var detailRows = [];
	 
	    $(tbody).on( 'click', 'tr td.details-control', function () {
	        var tr = $(this).closest('tr');
	        var row = dt.row( tr );
	        var idx = $.inArray( tr.attr('id'), detailRows );
	 
	        if ( row.child.isShown() ) {
	            tr.removeClass( 'details' );
	            row.child.hide();
	 
	            // Remove from the 'open' array
	            detailRows.splice( idx, 1 );
	        }
	        else {
	            tr.addClass( 'details' );
	            row.child( format( row.data() ) ).show();
	 
	            // Add to the 'open' array
	            if ( idx === -1 ) {
	                detailRows.push( tr.attr('id') );
	            }
	        }
	    } );
	 
	    // On each draw, loop over the `detailRows` array and show any child rows
	    dt.on( 'draw', function () {
	        $.each( detailRows, function ( i, id ) {
	            $('#'+id+' td.details-control').trigger( 'click' );
	        } );
	    } );
	}
