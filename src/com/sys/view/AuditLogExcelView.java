package com.sys.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.common.vo.PayAudit;
import com.sys.service.IAuditLogService;


public class AuditLogExcelView extends AbstractExcelView {

	private IAuditLogService logService;
	private PayAudit payAudit;
	private static final String[] COLUMNS = {"id","操作人","用户名","用户ID","账户类型","操作结果","行为","模块",
			"时间","事件"};
	public AuditLogExcelView(IAuditLogService logService,PayAudit payAudit) {
		this.logService = logService;
		this.payAudit = payAudit;
	}
	@Override
	protected void buildExcelDocument(Map<String, Object> arg0, HSSFWorkbook workbook, HttpServletRequest arg2,
			HttpServletResponse arg3) throws Exception {
		List<PayAudit> dataList = logService.queryAuditLogList(payAudit);
         
        int countRow=0;
        int nowRow = 1;
        int i = 0;
        int nowSheet = 1;
        HSSFSheet sheet = null;
        for(PayAudit payAuditLog : dataList){
        	if(countRow % 60000 ==0){
	        	sheet = workbook.createSheet("行为审核日志(" +nowSheet +")");   
	        	nowSheet++;
	            for(String columnTitle : COLUMNS)
	            {
	            	getCell(sheet, 0, i++).setCellValue(columnTitle); 
	            }
	            nowRow = 1;
        	}
        	countRow++;
        	HSSFRow sheetRow = sheet.createRow(nowRow++);  
        	sheetRow.createCell(i++, HSSFCell.CELL_TYPE_STRING).setCellValue(payAuditLog.getActId());
        	sheetRow.createCell(i++, HSSFCell.CELL_TYPE_STRING).setCellValue(payAuditLog.getActor());
        	sheetRow.createCell(i++, HSSFCell.CELL_TYPE_STRING).setCellValue(payAuditLog.getUserid());
        	sheetRow.createCell(i++, HSSFCell.CELL_TYPE_STRING).setCellValue(payAuditLog.getPersonuuid());
        	sheetRow.createCell(i++, HSSFCell.CELL_TYPE_STRING).setCellValue(payAuditLog.getAccountTypeName());
        	sheetRow.createCell(i++, HSSFCell.CELL_TYPE_STRING).setCellValue(payAuditLog.getActResultName());
        	sheetRow.createCell(i++, HSSFCell.CELL_TYPE_STRING).setCellValue(payAuditLog.getActAction());
        	sheetRow.createCell(i++, HSSFCell.CELL_TYPE_STRING).setCellValue(payAuditLog.getActObj());
        	sheetRow.createCell(i++, HSSFCell.CELL_TYPE_STRING).setCellValue(payAuditLog.getActTimeStr());
        	sheetRow.createCell(i++, HSSFCell.CELL_TYPE_STRING).setCellValue(payAuditLog.getActMessage());
        	i = 0;
        }
	}
	
}
