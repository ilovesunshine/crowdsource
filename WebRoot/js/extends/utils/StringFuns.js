
/**
 * 字符串替换
 * 
 * @param reallyDo 要替换的子字符串
 * @param replaceWith 替换后的子字符串
 * @param ignoreCase
 * @author FengHaiBing
 */
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);
 	} else {
 		return this.replace(reallyDo, replaceWith);
 	}
}

/**
 * 转义html代码
 * 
 * @param str 要转义的html字符串
 * @author FengHaiBing
 */
function html_encode(str) {   
  var s = "";   
  if (str.length == 0) return "";   
  s = str.replace(/&/g, "&gt;");   
  s = s.replace(/</g, "&lt;");   
  s = s.replace(/>/g, "&gt;");   
  s = s.replace(/ /g, "&nbsp;");   
  s = s.replace(/\'/g, "&#39;");   
  s = s.replace(/\"/g, "&quot;");   
  s = s.replace(/\n/g, "<br>");   
  return s;   
}   
 
/**
 * 解析已经转义的html代码
 * 
 * @param str 要解析的已经转义的html代码字符串
 * @author FengHaiBing
 */
function html_decode(str) {   
  var s = "";   
  if (str.length == 0) return "";   
  s = str.replace(/&gt;/g, "&");   
  s = s.replace(/&lt;/g, "<");   
  s = s.replace(/&gt;/g, ">");   
  s = s.replace(/&nbsp;/g, " ");   
  s = s.replace(/&#39;/g, "\'");   
  s = s.replace(/&quot;/g, "\"");   
  s = s.replace(/<br>/g, "\n");   
  return s;   
}


/**
 * 防止SQL注入
 * 
 * @param str 
 * @author FengHaiBing
 */
function checkSqlInject(str) {
	reg=/^[^\%\'\"\?]*$/;
	if(!reg.test(str)){
		return false;
	} else {
		return true;
	}
}
