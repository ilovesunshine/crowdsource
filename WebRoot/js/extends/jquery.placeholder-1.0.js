/*!
 * jQuery Placeholder snippet
 *
 * This snippet is written for the purpose of supporting the 
 * HTML5 placeholder attribute on most non-HTML5-compliant browsers.
 * 
 * Usage: Just include it in the code: <script src="jquery.place.holder-1.0.js"></script>
 * and include the HTML5 placeholder attribute in your input or textarea tags.
 * Note: This script should go after client code, if client code sets field's color.
 * 
 * Date: May 2011
 * Author: Otacon (byf1987_at_gmail.com)
 * Version: 1.0
 * jQuery Version: 1.5
 * Changelog: Initial release
 * Tested on: Chrome 10.0; IE6 (IETester); IE8 (IETester)
 * Known Issues: 
 * 	Placeholder for Password is currently not supported
 */

$(function(){
	// -- Constants --
	var PLACE_HOLDER_COLOR = "rgb(169,169,169)"; // "darkGrey" does not work in IE6
	var PLACE_HOLDER_DATA_NAME = "original-font-color";
	
	// -- Util Methods --	
	//获取元素的value值
	var getContent = function(element){
		return $(element).val();		
	}

	//设置元素的value值
	var setContent = function(element, content){
		$(element).val(content);		
	}
	
	//获取元素的placeholder值
	var getPlaceholder = function(element){
		return $(element).attr("placeholder");
	}
	
	//判断元素是否有value值
	var isContentEmpty = function(element){
		var content = getContent(element);
		return (content.length === 0) || content == getPlaceholder(element);
	}
		
	//设置Placeholder元素的字体颜色
	var setPlaceholderStyle = function(element){
		$(element).data(PLACE_HOLDER_DATA_NAME, $(element).css("color"));
		$(element).css("color", PLACE_HOLDER_COLOR);		
	}
	

	//清除Placeholder元素的字体颜色
	var clearPlaceholderStyle = function(element){
		$(element).css("color", $(element).data(PLACE_HOLDER_DATA_NAME));		
		$(element).removeData(PLACE_HOLDER_DATA_NAME);
	}
	
	//用ph的值给value付值，并更改字体颜色
	var showPlaceholder = function(element){
		setContent(element, getPlaceholder(element));
		setPlaceholderStyle(element);	
	}
	
	
	//
	var hidePlaceholder = function(element){
		if($(element).data(PLACE_HOLDER_DATA_NAME)){
			setContent(element, "");
			clearPlaceholderStyle(element);
		}
	}
	
	var isPlaceholder = function(){  
	    var input = document.createElement('input');  
	    return 'placeholder' in input;  
	}  
	// -- Event Handlers --
	var inputFocused = function(){
		if(isContentEmpty(this)){
			hidePlaceholder(this);		
		}
	}
	
	var inputBlurred = function(){
		if(isContentEmpty(this)){
			showPlaceholder(this);
		}
	}
	
	var parentFormSubmitted = function(){
		if(isContentEmpty(this)){
			hidePlaceholder(this);		
		}	
	}
	
	// -- Bind event to components --
	var setPlaceholder = function(){
		$("textarea, input[type='text']").each(function(index, element){
			if($(element).attr("placeholder")){
				$(element).focus(inputFocused);
				$(element).blur(inputBlurred);
				$(element).bind("parentformsubmitted", parentFormSubmitted);
				
				// triggers show place holder on page load
				$(element).trigger("blur");
				// triggers form submitted event on parent form submit
				$(element).parents("form").submit(function(){
					$(element).trigger("parentformsubmitted");
				});
			}
		});


	
		$("input[type='password']").each(function(index, element){
			if($(element).attr("placeholder")){
				var oinput = $("<div></div>")
				$(this).wrap("<div style='float:left;'></div>");
				//$("<div style='clear:both'></div>").insertAfter($(this).parent());
				oinput.insertAfter($(this));
				oinput.html($(this).attr("placeholder"))
				oinput.focus(function(){
					oinput.hide();
				})
				var pwdField = $(this),
					pwdPlaceholder = oinput;
				var pwdFieldWidth = parseInt(pwdField.width()),
			    	  pwdFieldHeight = pwdField.height(),
			    	  pwdFieldLHeight = pwdField.css("line-height"),
			    	  pwdFieldCol = pwdField.css("color"),
			    	  pwdFieldPad = pwdField.css("padding"),
			    	  pwdFieldMargTop = parseInt(pwdField.css("margin-top")),
			    	  pwdFieldBgC = pwdField.css("background-color"),
			    	  pwdFieldPadLeft = parseInt(pwdField.css("padding-left")),
			    	  pwdFieldPadRight = parseInt(pwdField.css("padding-right")),
			    	  pwdFieldPadTop = parseInt(pwdField.css("padding-top")),
			    	  pwdFieldParentPadTop = parseInt(pwdField.parent().css("padding-top"));
				
				pwdPlaceholder.css({
			    	"position":"absolute",
			    	"z-index":"999",
			    	"border":0,
			    	"overflow":"hidden",
			    	"box-sizing":"border-box",
			    	"top":1+pwdFieldParentPadTop+pwdFieldMargTop,
			    	"background":pwdFieldBgC,
			    	"left":pwdFieldPadLeft,
			    	"width":pwdFieldWidth,
			    	"height":pwdFieldHeight,
			    	"line-height":pwdFieldLHeight,
			    	"color":"#a9a9a9",
			    	"font-weight":500,
			    	//"margin":pwdFieldMarg,
			    	"padding-left":0,
			    	"padding-top":pwdFieldPadTop
			    });
				
				 if(pwdField.val() != '') {  
			            pwdPlaceholder.hide();  
			            //pwdField.hide();  
			        }  
			    pwdField.keydown(function(){
			    	pwdPlaceholder.hide();  
			    })
				
			    pwdField.parent().css({
			    	"position":"relative",
			    	"z-index":"1"
			    });
				
			    pwdPlaceholder.focus(function(){  
			        pwdPlaceholder.hide();  
			        pwdField.show();  
			        pwdField.focus();  
			    });  
		      
			    pwdField.blur(function(){  
			        if(pwdField.val() == '') {  
			            pwdPlaceholder.show();  
			            //pwdField.hide();  
			        }  
			    });  
				
				
				
				
				$(element).focus(inputFocused);
				$(element).blur(inputBlurred);
				$(element).bind("parentformsubmitted", parentFormSubmitted);
				
				// triggers show place holder on page load
				$(element).trigger("blur");
				// triggers form submitted event on parent form submit
				$(element).parents("form").submit(function(){
					$(element).trigger("parentformsubmitted");
				});
			}
		});
	}
	
	//判断浏览器是否支持placeholder，支持返回，不支持调用
	if(isPlaceholder()){
		return this;
	}else {
		return;
		//setPlaceholder();
	}

});