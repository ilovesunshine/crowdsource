//====================================================================
// 包括：命名空间函数 、 网站级别变量 & 函数
// by xx  xxx@chinasofti.com
// 2013-12-10
//=====================================================================
/** 全局命名空间函数
 * by jdh   jiangdh@chinasofti.com
 * 2013-12-10
 */
var JFGlobals = {},JF = JFGlobals;
JFGlobals.namespace = function(str){
    var arr = str.split("."),o = JFGlobals;
    for (i=(arr[0] == "JFGlobals" || arr[0] == "JF") ? 1 : 0; i<arr.length; i++) {
        o[arr[i]] = o[arr[i]] || {};
        o = o[arr[i]];
    }
}


/**
 * 初始化全局变量
 * 把常用的信息存放在全局变量里，方便获取
 * 例如登陆用户角色、文件根路径
 * by jdh   jiangdh@chinasofti.com
 * 2013-12-10
 */
JFGlobals.role = "";
JFGlobals.contextPath = ""; 

/**
 * jf ui组件
 * by jdh   jiangdh@chinasofti.com
 * 2013-12-10
 */
JFGlobals.ui = {
	/*弹出框下拉列表组件，用于从下拉列表tab标签页中选中checkbox值，
	 * 添加选中信息到id元素before
	 * 依赖：bootstrap popover 组件 （bizfoundation5.1已经引入）
	 * @param options 包含popover所有属性  
	 * @param column 设定下拉列表列数
	 * @param data 下拉列表数据源，格式：[{id:"",//单个tab页id
	 * 				title:"",//单个tab页标题
	 * 				data:{[name:"",value:""]}//此tab页下checkbox名称和值
	 * 				},……]  
	 * @event show  显示下拉列表时自定义事件，默认为查找目标元素前的标签值，选中下拉列表的checkbox
	 * @event checked  选中checkbox时自定义事件，默认为在目标元素前添加标签
	 * @event unChecked  取消checkbox时自定义事件，默认为在目标元素前删除标签
	 * 使用示例：
	 * html:
	 * <a  id="popoverRight" href="#" class="button">+</a>
	 * js:
	 *  new jf.ui.popoverList({
	 *	      id:"#popoverRight",
	 *	      column:4,
	 *        mutil:true,//是否多选
	 *        target:"#popoverRight",//默认放置的位置	
	 *	      data:[{id:"beijing",title:"北京",data:[{name:"海淀",value:"haidian"},{name:"东城",value:"dongcheng"}]},
	 *	          	{id:"shanghai",title:"上海",data:[{name:"黄埔",value:"huangpu"},{name:"外滩",value:"waitan"}]}]
	 *	});
	 * 
	 * */
		/**
		 * @author 钱崇勇 2014-07-15
		 * 方法说明： 比较两个时间的大小，时间的格式为 “YYYY-MM-DD”
		 * 如果参数中的第一个时间大于第二时间 返回 “greater”
		 * 		   第一个时间等于第二时间 返回 “equal”
		 * 		   第一个时间小于第二时间 返回 “less”
		 * 		   时间格式不正确或比较失败 返回 “exception”
		 */
		comptime:function(a, b) {
		    var arr = a.split("-");
		    var starttime = new Date(arr[0], arr[1], arr[2]);
		    var starttimes = starttime.getTime();

		    var arrs = b.split("-");
		    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
		    var lktimes = lktime.getTime();

		    if (starttimes > lktimes) {
		        return "greater";
		    } else if (starttimes == lktimes){
		    	return "equal";
		    } else if(starttimes < lktimes){
		    	return "less";
		    } else {
		    	return "exception";
		    }

		},
		/**
		 * @author 钱崇勇 2014-07-15
		 * 方法说明： 比较两个带时分秒的时间的大小，时间的格式为 “YYYY-MM-DD HH:MI:SS”
		 * 如果参数中的第一个时间大于第二时间 返回 “greater”
		 * 		   第一个时间等于第二时间 返回 “equal”
		 * 		   第一个时间小于第二时间 返回 “less”
		 * 		   时间格式不正确或比较失败 返回 “exception”
		 */
		comptimemin:function(beginTime, endTime) {
		    
		    var beginTimes = beginTime.substring(0, 10).split('-');
		    var endTimes = endTime.substring(0, 10).split('-');

		    beginTime = beginTimes[1] + '-' + beginTimes[2] + '-' + beginTimes[0] + ' ' + beginTime.substring(10, 19);
		    endTime = endTimes[1] + '-' + endTimes[2] + '-' + endTimes[0] + ' ' + endTime.substring(10, 19);
 
		    var a = (Date.parse(beginTime) - Date.parse(endTime)) / 3600 / 1000;
		    if (a > 0) {
		    	return "greater";
		    } else if (a == 0) {
		    	return "equal";
		    } else if (a < 0) {
		    	return "less";
		    } else {
		    	return "exception";
		    }
		},
		
	popoverList:function(options){
		var $this = this,
			ulList = "",//tab li部分内容
	        tabList = "" ,//tab div部分内容
	        active = "",//是否选中
	        content = "",//最终内容
	        content2="",//checkbox内容
	        datas = [];
	        
		//组件属性处理
	    $this.options = $.extend({
				        placement:"bottom",
				        html:true,
				        column:4,
				        title:"",
				        container:"body"},options);
	    datas = $this.options.data;
	    
	    //方法
	    $this.show = function(){$(options.id).popover("show");}
	    $this.hide = function(){$(options.id).popover("hide");}
	    $this.toggle = function(){$(options.id).popover("toggle");}
	    $this.destroy = function(){$(options.id).popover("destroy");}

	    //添加标签区域
		options.target = options.target ?  options.target : options.id;
	   //	var w = options.width?options.width:'276px';
		
		// $("#"+ options.id+"_tabs").parent().parent().css("max-width",w);
		
		// $(options.id).before("<div id='" + $(options.id).attr("id") + "_tags' style='display:inline-block'></div>");	
		 
	    var inpuType =options.mutil? 'checkbox':'radio';
		var display = options.mutil? '':'visibility:hidden';
		
	    //弹出内容
	    for(var i = 0;i < datas.length; i++){
	        active = (i==0)? "active" :"";
	        ulList += '<li class=" '+active+'"><a href="javascript:;" targets="#' + datas[i].id + '">' + datas[i].title + '</a></li>' ;
	        tabList +='<div class="tab-pane '+ active +'" id="'+datas[i].id+'">' +
	                '<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0" >' +
	                '<tbody>';
	        
	        for(var j=0;j < datas[i].data.length;j++){
	        	//根据column拼接table代码
	            var isTr = j%$this.options.column == 0 ? (j == 0 ? '<tr>': '</tr><tr>') : '' ;				
	            tabList += isTr ;
	            tabList += '<td style="cursor: pointer;"><input onfocus="this.blur();" type="'+inpuType+'" style="'+display+'" value="'+ datas[i].data[j].value +'" txt="'+ datas[i].data[j].name +'"';
	          
	            $(".btn").each(function(){
	            	if(datas[i].data[j].name==$(this).text()){
	            		tabList +=' checked="checked"';
	            	}
	            });
	           
	            
	            tabList +='><span  class ="popover-item">'+ datas[i].data[j].name +'</span></td>' ;
	            if(j == datas[i].data.length-1 ){tabList += '</tr>';}
	        }
	       
	        tabList += '</tbody></table></div>' ;
	    }
	    content  =  '<ul class="nav nav-tabs" id="'+$(options.id).attr("id") +'_tabs">'+
			         ulList +'<button id="closei" class="closeiButton"></button></ul>'+
			        '<div style="';
			         if(inpuType =="checkbox"){
			        	 content  += 'padding-left: 10px;';
			         }else{
			        	 content  += 'padding-right: 10px;';
			         }
			         
	    content  += '" class="tab-content" id="'+$(options.id).attr("id")+'_tabContent">'+
			        tabList +'</div>';
	    if(inpuType == "checkbox"){
	    	content  +='<div style="width:100%; margin-top: 10px; margin-bottom: 20px;text-align: left;padding-left: 10px;"><button id="submit_button" class="themeSubmit_button" type="button">确定</button>';
	    	content  +='<button id="cancel_button" class="themeCancel_button" type="button">取消</button></div>';
	    	content2=content;
	    }
    
	    //关闭下拉框
	   $("#closei").live("click",function(){
		   $this.hide();
	    });
	    
	    
	    //下拉框中的'确定'按钮
	    $("#submit_button").live("click",function(){
	    	 $this.hide();
	    });
	    
	    //下拉框中的'取消'按钮
	    $("#cancel_button").live("click",function(){
	    	$(".tab-content input[type='checkbox']").each(function(){
	    	    if($(this).attr("checked")=="checked"){
	    	    	$(this).attr("checked",false);
	    	    	$(".btn").each(function(){	            		            
            		    	$(this).remove(); 
            		}); 
	    	    }
	    	  });
	    	
	    });

	    //弹出提示
	    $($this.options.id).popover({
	        placement:$this.options.placement,
	        html:$this.options.html,
	        title:$this.options.title,
	        container:$this.options.container,
			showPopover:function(opt){
				},
	        show:function(opt){
	        	if($.isFunction($this.options.show)){
	                $this.options.show.call(this,$this.options);
	            }else if(options.mutil){
	                $(options.target + " span").each(function(){
	                    $($this.options.id +"_tabContent input[value='"+$(this).html()+"']")[0].checked = true;
	                });
	             }
	        },

	       // content:content  
	       content:  function(opt){
	    	   if(inpuType=="checkbox"){
	    		   var delandChecked='checked="checked"';
	    		   while(content2.indexOf("checked")>=0) {
	    			   content2=content2.replace(delandChecked, "");
	    		   }
	        		//content2=content2.replace('checked=""', "");
	    		   $(".btn").each(function(){
		        		var txtConcent='txt='+'"'+$(this).text()+'"';
		        		if(content2.indexOf(txtConcent)>=0){
		        			content2 = content2.split(txtConcent)[0] +txtConcent+' checked="checked"' +  content2.split(txtConcent)[1]; 
		        		}
		            	
		            });
	    		   
	    		   return content2;
	    	   }else{
	    		   return content;
	    	   }
	        	
	        	
	        }
	
	    });	
	    
	    //tab页切换
	    $($this.options.id +"_tabs  li").live("click",function(){
	        $($this.options.id +"_tabs .active").removeClass("active");
	        $($this.options.id +"_tabContent .active").removeClass("active");
	        $(this).addClass(" active");
	        $($("a",this).attr("targets")).addClass(" active");
	    });

	    //选中标签
	    $($this.options.id +"_tabContent  input").live("click",function(){
	    	$(this).attr("checked",true);
	    	var $that = this;
	        var _temp="";
	        if(!!$($that).attr("checked")){
	            if($.isFunction($this.options.checked)){
	                $this.options.checked.call($this,$that);
	            }else{
	            	if($(".btn").length==0){
	            		$(options.target).prepend("<span class='btn' value="+$(this).val()+">"+$(this).next().html()+"<a class='overTechSpan'></a></span>");
	            	}else{
		            		$(".btn").each(function(){	 
		            		    if($(this).text()==$($that).attr("txt")){
		            		    	// _temp="您已选择该项";
		            		    	$($that).attr("checked",false);
		            		    	$(this).remove();
		            		    	 return;
		            		    }
		            		});            		
		            		if($($that).attr("checked")=="checked"&&_temp==""&&($(".btn").length<=options.maxSelect)){
		  	            	  $(options.target).prepend("<span class='btn' value="+$(this).val()+">"+$(this).next().html()+"<a class='overTechSpan'></a></span>");
		  	            	  return;
		  	                }else if($($that).attr("checked")=="checked"&&_temp==""&&($(".btn").length>options.maxSelect)){
		  	                	new biz.alert({
		  	                		type:"alert",
		  	                		message:"选择项超过了最大限制数目"+options.maxSelect+"个，继续选择的话微简历中将不再继续生成相应技能项。",
		  	                		title:"注 意" 
		  	                	 }) ; 
		  	                	$($that).attr("checked",false);
		  	                	return;
		  	                }else return;	
	            	}
	            }
	        }else{
	            if($.isFunction($this.options.unChecked)){
	                $this.options.unChecked.call($this,$that);
	            }else{
				  $(options.target+" span[value='"+$(this).val()+"']").remove();				 
	            }
	        }
	    });
		
		
			
		//单选框时点击弹出框选项
		 $($this.options.id +"_tabContent span[class='popover-item']").live("click",function(){
	        var $that = this;
			if(!options.mutil){
			   $(options.target).val($(this).html()).blur();
			   $this.hide();
			   return;
			}
			
			 var $prev  = $(this).prev();			
			
	        if(!!$($prev).attr("checked")){
	            if($.isFunction($this.options.checked)){
	                $this.options.checked.call($this,$prev);
	            }else{				
					$(options.target).prepend("<span class='btn' value="+$(prev).val()+">"+$(prev).next().html()+"</span>");
	            }
	        }else{
	            if($.isFunction($this.options.unChecked)){
	                $this.options.unChecked.call($this,$prev);
	            }else{
					$(options.target+" span[value='"+$(prev).val()+"']").remove();
	            }
	        }
	    });
		
	}
		
}
