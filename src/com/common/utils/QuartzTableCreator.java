package com.common.utils;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class QuartzTableCreator implements InitializingBean {
	private static final Logger LOG = LoggerFactory.getLogger(QuartzTableCreator.class.getName());
	@Resource
	private SqlSessionTemplate sqlSession;
	private String tableNamePrefix;
	private boolean isCreateTableAuto;

	public QuartzTableCreator() {
		this("qrtz", false);
	}

	public QuartzTableCreator(String quartzPrefix) {
		this(quartzPrefix, false);
	}

	public QuartzTableCreator(String quartzPrefix, boolean autoCreateQuartzTable) {
		this.tableNamePrefix = quartzPrefix;
		this.isCreateTableAuto = autoCreateQuartzTable;
	}

	public void afterPropertiesSet() throws Exception {
		if (!this.isCreateTableAuto) {
			return;
		}
		Map<String, String> paraMap = new HashMap<String, String>();
		try {
		
			paraMap.put("tableNamePrefix", tableNamePrefix);
			sqlSession.selectOne("com.chinasofti.vo.JobTrigger.getTriggerCount",paraMap);
		} catch (Exception e) {
			JfLog.error(LOG,e.getMessage(),e);
			try {
				sqlSession.update("com.chinasofti.vo.JobTrigger.createJobDetails",paraMap);
				sqlSession.update("com.chinasofti.vo.JobTrigger.createTriggers",paraMap);
				sqlSession.update("com.chinasofti.vo.JobTrigger.createSimpleTriggers",paraMap);
				sqlSession.update("com.chinasofti.vo.JobTrigger.createCronTriggers",paraMap);
				sqlSession.update("com.chinasofti.vo.JobTrigger.createSimpropTriggers",paraMap);
				sqlSession.update("com.chinasofti.vo.JobTrigger.createBlobTriggers",paraMap);
				sqlSession.update("com.chinasofti.vo.JobTrigger.createcalendars",paraMap);
				sqlSession.update("com.chinasofti.vo.JobTrigger.createPausedTriggerGrps",paraMap);
				sqlSession.update("com.chinasofti.vo.JobTrigger.createFiredTriggers",paraMap);
				sqlSession.update("com.chinasofti.vo.JobTrigger.createSchedulersState",paraMap);
				sqlSession.update("com.chinasofti.vo.JobTrigger.createLocks",paraMap);
			} catch (Exception e2) {
				JfLog.error(LOG,e.getMessage(),e);
			}
			
		}
		
		
	}
}
