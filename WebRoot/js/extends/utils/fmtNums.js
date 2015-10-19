
/**
 * 数字格式转换成千分位 
 * @param{Object}num
 * @author JCJ
 */
function commafy(num) {
	if ($.trim((num + "")) == "") {
		return "";
	}
	if (isNaN(num)) {
		return "";
	}
	num = num + "";
	if (/^.*\..*$/.test(num)) {
		varpointIndex = num.lastIndexOf(".");
		varintPart = num.substring(0, pointIndex);
		varpointPart = num.substring(pointIndex + 1, num.length);
		intPart = intPart + "";
		var re = /(-?\d+)(\d{3})/;
		while (re.test(intPart)) {
			intPart = intPart.replace(re, "$1,$2");
		}
		num = intPart + "." + pointPart;
	} else {
		num = num + "";
		var re = /(-?\d+)(\d{3})/;
		while (re.test(num)) {
			num = num.replace(re, "$1,$2");
		}
	}
	return num;
}