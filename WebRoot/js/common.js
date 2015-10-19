function selectedAll(formId,value){
	var id = "#"+formId;
	$(id).find("input:checkbox").each(function() {
		if(value){
			$(this).prop('checked', true);
		}else{
			$(this).prop("checked",false);
		}
	});
}
function changCheckboxChecked(name,formId,select){
	var id = "#"+formId;
	var count = 0;
	$(id).find("input[name='"+name+"']").each(function() {
        if ($(this).prop('checked')) {
        	count+=1;
    }
	});
	id = "#"+select;
	if(count>0){
		$(id).prop('checked', true);
	}else{
		$(id).prop('checked', false);
	}
}
function cutStr(data,length){
	if(data!='undefined'){
		if(data.length>0 && data.length>length){
			return data.substr(0,length)+'...';
		}else{
			return data;
		}
	}else{
		return '';
	}
}
function showInfo(msg,time) {
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
    $( "#effect").text(msg);
    // run the effect
    $( "#effect" ).show( selectedEffect, options, time, callback );
  };

  //callback function to bring a hidden box back
  function callback() {
    setTimeout(function() {
      $( "#effect:visible" ).removeAttr( "style" ).fadeOut();
    }, 1000 );
  };