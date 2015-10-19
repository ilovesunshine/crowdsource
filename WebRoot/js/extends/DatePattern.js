	/**       
	 * 对Date的扩展，将 Date 转化为指定格式的String       
	 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符       
	 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)       
	 * eg:       
	 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423       
	 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04       
	 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04       
	 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04       
	 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18       
	 */          
	Date.prototype.pattern=function(fmt) {           
		var o = {           
		"M+" : this.getMonth()+1, //月份           
		"d+" : this.getDate(), //日           
		"h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时           
		"H+" : this.getHours(), //小时           
		"m+" : this.getMinutes(), //分           
		"s+" : this.getSeconds(), //秒           
		"q+" : Math.floor((this.getMonth()+3)/3), //季度           
		"S" : this.getMilliseconds() //毫秒           
		};           
		var week = {           
		"0" : "/u65e5",           
		"1" : "/u4e00",           
		"2" : "/u4e8c",           
		"3" : "/u4e09",           
		"4" : "/u56db",           
		"5" : "/u4e94",           
		"6" : "/u516d"          
		};           
		if(/(y+)/.test(fmt)){           
			fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));           
		}           
		if(/(E+)/.test(fmt)){           
			fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);           
		}           
		for(var k in o){           
			if(new RegExp("("+ k +")").test(fmt)){           
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
			}           
		}           
		return fmt;           
	};
	/*添加格式化今天日期到Date原型中*/
	Date.prototype.formatToday = function (){
		var month=this.getMonth();
		month=parseInt(month)+1;
		if(month <10){
			month='0'+month;
		}
		var day=this.getDate();
		if(parseInt(day) <10){
			day='0'+day;
		}
		return this.getFullYear()+"-"+month+"-"+day;
	};
	/**
	 * 把传入的字符式的日期转化成yyyy-MM-dd格式
	 * @param {String} todayStr 今天的日期字符串化
	 */
	Date.toDate = function (todayStr){
		todayStr=todayStr.replace('-','/');
		todayStr=todayStr.replace('-','/');
		var todayDate=new Date(todayStr);
		var month=todayDate.getMonth();
		month=parseInt(month)+1;
		if(month <10){
			month='0'+month;
		}
		var day=todayDate.getDate();
		if(parseInt(day) <10){
			day='0'+day;
		}
		return todayDate.getFullYear()+"-"+month+"-"+day;
	};
	/**
	 * 将当前日期转换成数字类型 例如:20131212
	 * @author baiyu
	 * @return 返回日期数字化
	 */
	Date.prototype.toNumber = function (){
		return (strDate.replace(/[\/\:\-]/g,""))*1;
	};
	/**
	 * 给定日期格式，返回匹配的日期字符串
	 * @author baiyu
	 * @Description : 如果没有指定date参数那么就把当前日期格式为指定的格式，如果指定就把指定的日期格式化
	 * @param {String} formate 日期格式形式
	 * @param {Date} date 默认为当前日期对象，也可以指定要格式的date对象
	 * @returns 格式化后的日期字符串
	 * 例如:
	 * 		format "yyyy-MM-dd hh:mm:ss"  
	 * 		date 当前日期
	 * 		2013-10-23 12:12:12
	 * 
	 * 		format "yyyy年MM月dd日 hh时mm分ss秒" 
	 * 		date 当前日期
	 * 		2013年10月23日 12时12分12秒
	 */
	Date.prototype.formate = function (format,date){
		var todayDate = null;
		if(arguments.length == 1){
			todayDate = this;
		} else if (arguments.length == 2){
			todayDate = date;
		}
		return format.replace("yyyy", todayDate.getFullYear())
					 .replace("MM", (todayDate.getMonth() + 1) < 10 ? "0" + (todayDate.getMonth() + 1) : (todayDate.getMonth() + 1))
					 .replace("dd", todayDate.getDate() < 10 ? "0" + todayDate.getDate() : todayDate.getDate())
					 .replace("hh", todayDate.getHours() < 10 ? "0" + todayDate.getHours() : todayDate.getHours())
					 .replace("mm", todayDate.getMinutes() < 10 ? "0" + todayDate.getMinutes() : todayDate.getMinutes())
					 .replace("ss", todayDate.getSeconds() < 10? "0" + todayDate.getSeconds() : todayDate.getSeconds());
	};
	
	/**
	 * 把日期格式方法绑定到Date.formate方法上可以直接对给出的date对象进行格式化
	 */
	Date.formate = Date.prototype.formate;
	/**
	 * 计算出日期之间的差值 几天几小时几分钟几秒
	 */
	Date.diff = function (d1,d2){
		var diffTime = Date.parse(d2) - Date.parse(d1);
//		var diffTime = d2.getTime() - d1.getTime();
		var s = "";
		var days = Math.floor(diffTime/(24*3600*1000)),
			hourTimes = diffTime%(24*3600*1000),
			hours = Math.floor(hourTimes/(3600*1000)),
			minuteTimes = hourTimes%(3600*1000),
			minutes = Math.floor(minuteTimes/(60*1000)),
			secondTimes = minuteTimes%(60*1000),
			seconds = Math.round(secondTimes/1000);
		if(days){s += days + "天";}
		if(hours){s += hours + "时";}
		if(minutes){s += minutes + "分";}
		if(seconds){s += seconds + "秒";}
		return s; 
	};
	/**
	 * 用于分隔字符串
	 */
	Date.splits = /[\-\/\ \,\-]/g;
	/**
	 * 把给定格式的日期字符串，转化成日期对象
	 * @author baiyu
	 * @Description : 将给定格式的字符串转化成Date对象
	 * @param {String} str 格式化的日期字符串
	 * @returns {Date} Date对象
	 */
	Date.date = function (str){
		if(""==str)return null;
		var s = "";
		var aStr = str.split(' '),
			aDate = null,
			aTime = null;
		if(1 == aStr.length) {
			aDate = aStr[0].split(Date.splits);
		} else if(2 == aStr.length){
			aDate = aStr[0].split(Date.splits);
			aTime = aStr[1].split(Date.splits);
		}
		if(null != aDate){
			s = aDate.join('/');
		}
		if(null != aTime) {
			s += " " + aTime.join(':');
		}
		return new Date(s);
	};
	
	
 	function formatToday(){
		var myDate = new Date();
		var month=myDate.getMonth();
		month=parseInt(month)+1;
		if(month <10){
			month='0'+month;
		}
		var day=myDate.getDate();
		if(parseInt(day) <10){
			day='0'+day;
		}
		return myDate.getFullYear()+"-"+month+"-"+day;
	}
	
	/**
	* 昨天、前天、明天或者后天的日期
	* 
	* 前天：getDateStr(-2)
	* 昨天：getDateStr(-1)
	* 今天：getDateStr(0)
	* 明天：getDateStr(1)
	* 后天：getDateStr(2)
	* 大后天：getDateStr(3)
	* 
	* author: FengHaiBing
	*/
	function getDateStr(AddDayCount) {
	    var dd = new Date();
	    dd.setDate(dd.getDate() + AddDayCount);//获取AddDayCount天后的日期
	    var y = dd.getFullYear();
	    var m = dd.getMonth()+1;//获取当前月份的日期
	    var d = dd.getDate();
	    return y+"-"+m+"-"+d;
	}
	
	//计算时间差
	function miDate(date1,date2){
		var date3 = Date.parse(date2) - Date.parse(date1);
//		var date3=date2.getTime()-date1.getTime()  //时间差的毫秒数
		//计算出相差天数
		// var days=Math.floor(date3/(24*3600*1000))
		 
		//计算出小时数

		var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数
		var hours=Math.floor(leave1/(3600*1000));
		//计算相差分钟数
		var leave2=leave1%(3600*1000);        //计算小时数后剩余的毫秒数
		var minutes=Math.floor(leave2/(60*1000));
		 

		//计算相差秒数
		//var leave3=leave2%(60*1000) ;     //计算分钟数后剩余的毫秒数
		//var seconds=Math.round(leave3/1000);
		var str="";
		if(hours!=0)
			str+=hours+"时";
		return str+minutes+"分钟前";
	}
	
	/**
	* 格式化日期串为  今天:5分钟前
	* 只对今天的数据进行时间处理
	* 
	* _dateStr yyyy-MM-dd
	* _timeStr hh:mm:ss
	* 
	* author FengHaiBing
	*/
	function formatDateByRecent(_dateStr, _timeStr) {
		if(_dateStr == '') return '';

		var todayx = new Date();
//		var todayStr = todayx.getFullYear() + '-' + (todayx.getMonth() + 1) + '-' + todayx.getDate();
		var todayStr = getDateStr(0);
		var yesterdayStr = getDateStr(-1);
		var beforeYesterdayStr = getDateStr(-2);

		_dateStr = _dateStr.replaceAll('  ',' ');
		var dateStrs = _dateStr.split("-");
		var timeStrs = _timeStr.split(":");
		var minuteTime = timeStrs[0] + ':' + timeStrs[1];
		var retStr = '';
		if(todayStr == _dateStr) {
//			retStr = '今天';
		} else if(yesterdayStr == _dateStr) {
			return minuteTime;
//			return '昨天' + ' ' + minuteTime;
		} else if(beforeYesterdayStr == _dateStr) {
			return minuteTime;
//			return '前天' + ' ' + minuteTime;
		} else {
			return _dateStr + ' ' + minuteTime;
		}

		if(timeStrs.length < 2) {
			retStr += ' ' + _timeStr;
			return retStr;
		}

		//their interval is little of 60 minutes.
		var dateFromDB = new Date(dateStrs[0], parseInt(dateStrs[1]) - 1 , dateStrs[2], timeStrs[0], timeStrs[1], timeStrs[2]);
		var diff = Date.parse(todayx) - Date.parse(dateFromDB);
		var aMinutex = 60 * 1000;
		var anHourx = aMinutex * 60;
		if(diff < aMinutex ) {
			retStr += "不到1分钟";
			return retStr;
		}
		if(diff < anHourx ) {
			retStr += ' ' + Math.round(diff/aMinutex) + "分钟前";
			return retStr;
		} else {
			retStr += ' ' + minuteTime;
			return retStr;
		}
	}

	/**
	* 格式化日期串为  今天:5分钟前
	* 只对今天的数据进行时间处理
	* 
	* _dateTimeStr yyyy-MM-dd hh:mm:ss
	* 
	* author FengHaiBing
	*/
	function formatDateByDateTime(_dateTimeStr) {
		if(_dateTimeStr == '') return '';

		_dateTimeStr = _dateTimeStr.replaceAll('  ',' ');
		_dateStr = _dateTimeStr.substring(0,10);
		_timeStr = _dateTimeStr.substring(11,18);
//		var currDateArr=(new Date()).Format("yyyy-MM-dd hh:mm:ss");  //取时分秒
//		currDateArr = currDateArr.substring(10,19);

		return formatDateByRecent(_dateStr, _timeStr);
	}
	