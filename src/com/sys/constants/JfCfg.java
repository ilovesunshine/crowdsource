package com.sys.constants;

import java.util.HashMap;
import java.util.Map;

public class JfCfg {

	public static final String DATE_PATTERN_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	private JfCfg(){
		
	}
	
	public static class GuestType{
		public static final Integer TYPE_GUEST = -1;
		public static final String TYPE_GUEST_NAME = "GUEST";
		public static final Integer TYPE_PER = 0;
		public static final String TYPE_PER_NAME = "个人用户";
		public static final Integer TYPE_ENT = 1;
		public static final String TYPE_ENT_NAME = "企业用户";
		public static final Integer TYPE_OPERATOR = 2;
		public static final String TYPE_OPERATOR_NAME = "运营管理员";
		public static final Integer TYPE_SYSMGR = 3;
		public static final String TYPE_SYSMGR_NAME = "系统管理员";
		public static final Integer TYPE_APIREQUESTOR = 4;
		public static final String TYPE_APIREQUESTOR_NAME = "API请求者";
		public static final Integer TYPE_JOBRUNNER = 5;
		public static final String TYPE_JOBRUNNER_NAME = "JOB运行者";
		public static final Integer TYPE_SERVICE = 6;
		public static final String TYPE_SERVICE_NAME = "SERVICE";
		public static final String TYPE_OTHER_NAME = "游客";
		public static final Map<Integer, String> DATAMAP = new HashMap<Integer,String>();
		static{
			DATAMAP.put(TYPE_GUEST, TYPE_GUEST_NAME);
			DATAMAP.put(TYPE_PER, TYPE_PER_NAME);
			DATAMAP.put(TYPE_ENT, TYPE_ENT_NAME);
			DATAMAP.put(TYPE_OPERATOR, TYPE_OPERATOR_NAME);
			DATAMAP.put(TYPE_SYSMGR, TYPE_SYSMGR_NAME);
			DATAMAP.put(TYPE_APIREQUESTOR, TYPE_APIREQUESTOR_NAME);
			DATAMAP.put(TYPE_JOBRUNNER, TYPE_JOBRUNNER_NAME);
			DATAMAP.put(TYPE_SERVICE, TYPE_SERVICE_NAME);
		}
	}
	public static class ActionResult{
		public static final Integer RESULT_SUCCESS = 1;
		public static final String RESULT_SUCCESS_NAME = "成功";
		public static final Integer RESULT_FAILED = 0;
		public static final String RESULT_FAILED_NAME = "失败";
		public static final String RESULT_OTHER_NAME = "结果未知";
		public static final Map<Integer, String> DATAMAP = new HashMap<Integer,String>();
		static{
			DATAMAP.put(RESULT_SUCCESS, RESULT_SUCCESS_NAME);
			DATAMAP.put(RESULT_FAILED, RESULT_FAILED_NAME);
		}
	}
}
