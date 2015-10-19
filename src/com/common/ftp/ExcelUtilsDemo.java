/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Boolean;
import jxl.write.DateFormats;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.utils.JfLog;

/**
 * @Title: ExcelUtilsDemo.java
 * @Description: <br>
 * <br>
 * @Company: crowdsource
 * @Created on 2015-12-11 上午6:09:14
 * @author FengHaiBing
 * @version $Revision: 1.0 $
 * @since 1.0
 */
class ExcelUtilsDemo {
	private static final Logger LOG = LoggerFactory.getLogger(ExcelUtilsDemo.class);
    /**
     * JSP生成简单的Excel文件
     * 
     * String fname = "学校竞争力情况"; OutputStream os =
     * response.getOutputStream();//取得输出流 response.reset();//清空输出流
     * 
     * //下面是对中文文件名的处理 response.setCharacterEncoding("UTF-8");//设置相应内容的编码格式 fname
     * = java.net.URLEncoder.encode(fname,"UTF-8");
     * response.setHeader("Content-Disposition","attachment;filename="+new
     * String(fname.getBytes("UTF-8"),"GBK")+".xls");
     * response.setContentType("application/msexcel");//定义输出类型 ExcelUtilsDemo sw
     * = new ExcelUtilsDemo(); sw.createExcel(os);
     * 
     * @param os
     * @throws WriteException
     * @throws IOException
     */
    public void createExcel(OutputStream os) throws WriteException, IOException {
        // 创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        // 创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);
        // 创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        Label xuexiao = new Label(0, 0, "学校");
        sheet.addCell(xuexiao);
        Label zhuanye = new Label(1, 0, "专业");
        sheet.addCell(zhuanye);
        Label jingzhengli = new Label(2, 0, "专业竞争力");
        sheet.addCell(jingzhengli);

        Label qinghua = new Label(0, 1, "清华大学");
        sheet.addCell(qinghua);
        Label jisuanji = new Label(1, 1, "计算机专业");
        sheet.addCell(jisuanji);
        Label gao = new Label(2, 1, "高");
        sheet.addCell(gao);

        Label beida = new Label(0, 2, "北京大学");
        sheet.addCell(beida);
        Label falv = new Label(1, 2, "法律专业");
        sheet.addCell(falv);
        Label zhong = new Label(2, 2, "中");
        sheet.addCell(zhong);

        Label ligong = new Label(0, 3, "北京理工大学");
        sheet.addCell(ligong);
        Label hangkong = new Label(1, 3, "航空专业");
        sheet.addCell(hangkong);
        Label di = new Label(2, 3, "低");
        sheet.addCell(di);

        // 把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }

    /**
     * 生成复杂数据格式Excel文件
     * 
     * @param os
     * @throws WriteException
     * @throws IOException
     */
    public void createComplexExcel(OutputStream os) throws WriteException,
            IOException {
        // 创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        // 创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);
        // 创建要显示的具体内容
        Label formate = new Label(0, 0, "数据格式");
        sheet.addCell(formate);
        Label floats = new Label(1, 0, "浮点型");
        sheet.addCell(floats);
        Label integers = new Label(2, 0, "整型");
        sheet.addCell(integers);
        Label booleans = new Label(3, 0, "布尔型");
        sheet.addCell(booleans);
        Label dates = new Label(4, 0, "日期格式");
        sheet.addCell(dates);

        Label example = new Label(0, 1, "数据示例");
        sheet.addCell(example);
        // 浮点数据
        Number number = new Number(1, 1, 3.1415926535);
        sheet.addCell(number);
        // 整形数据
        Number ints = new Number(2, 1, 15042699);
        sheet.addCell(ints);
        Boolean bools = new Boolean(3, 1, true);
        sheet.addCell(bools);
        // 日期型数据
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        WritableCellFormat cf1 = new WritableCellFormat(DateFormats.FORMAT1);
        DateTime dt = new DateTime(4, 1, date, cf1);
        sheet.addCell(dt);
        // 把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();

    }

    /**
     * 生成复杂布局和样式的Excel文件
     * 
     * @param os
     * @throws WriteException
     * @throws IOException
     */
    public void createMutiStyleExcel(OutputStream os) throws WriteException,
            IOException {
        // 创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        // 创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);
        // 构造表头
        sheet.mergeCells(0, 0, 4, 0);// 添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行
        WritableFont bold = new WritableFont(WritableFont.ARIAL, 10,
                WritableFont.BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
        WritableCellFormat titleFormate = new WritableCellFormat(bold);// 生成一个单元格样式控制对象
        titleFormate.setAlignment(jxl.format.Alignment.CENTRE);// 单元格中的内容水平方向居中
        titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
        Label title = new Label(0, 0, "JExcelApi支持数据类型详细说明", titleFormate);
        sheet.setRowView(0, 600, false);// 设置第一行的高度
        sheet.addCell(title);

        // 创建要显示的具体内容
        WritableFont color = new WritableFont(WritableFont.ARIAL);// 选择字体
        color.setColour(Colour.GOLD);// 设置字体颜色为金黄色
        WritableCellFormat colorFormat = new WritableCellFormat(color);
        Label formate = new Label(0, 1, "数据格式", colorFormat);
        sheet.addCell(formate);
        Label floats = new Label(1, 1, "浮点型");
        sheet.addCell(floats);
        Label integers = new Label(2, 1, "整型");
        sheet.addCell(integers);
        Label booleans = new Label(3, 1, "布尔型");
        sheet.addCell(booleans);
        Label dates = new Label(4, 1, "日期格式");
        sheet.addCell(dates);

        Label example = new Label(0, 2, "数据示例", colorFormat);
        sheet.addCell(example);
        // 浮点数据
        // 设置下划线
        WritableFont underline = new WritableFont(WritableFont.ARIAL,
                WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD, false,
                UnderlineStyle.SINGLE);
        WritableCellFormat greyBackground = new WritableCellFormat(underline);
        greyBackground.setBackground(Colour.GRAY_25);// 设置背景颜色为灰色
        Number number = new Number(1, 2, 3.1415926535, greyBackground);
        sheet.addCell(number);
        // 整形数据
        WritableFont boldNumber = new WritableFont(WritableFont.ARIAL, 10,
                WritableFont.BOLD);// 黑体
        WritableCellFormat boldNumberFormate = new WritableCellFormat(
                boldNumber);
        Number ints = new Number(2, 2, 15042699, boldNumberFormate);
        sheet.addCell(ints);
        // 布尔型数据
        Boolean bools = new Boolean(3, 2, true);
        sheet.addCell(bools);
        // 日期型数据
        // 设置黑体和下划线
        WritableFont boldDate = new WritableFont(WritableFont.ARIAL,
                WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD, false,
                UnderlineStyle.SINGLE);
        WritableCellFormat boldDateFormate = new WritableCellFormat(boldDate,
                DateFormats.FORMAT1);
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        DateTime dt = new DateTime(4, 2, date, boldDateFormate);
        sheet.addCell(dt);
        // 把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();

    }
    
    /**
     * 读取文件
     * 
     * @param sourceFile    F:/红楼人物.xls
     * @param targetFile    F:/红楼人物1.xls
     */
    public void readFile(String sourceFile, String targetFile) {
        jxl.Workbook readwb = null;
        try {
            // 构建Workbook对象, 只读Workbook对象
            // 直接从本地文件创建Workbook
            InputStream instream = new FileInputStream(sourceFile);
            readwb = Workbook.getWorkbook(instream);

            // Sheet的下标是从0开始
            // 获取第一张Sheet表
            Sheet readsheet = readwb.getSheet(0);

            // 获取Sheet表中所包含的总列数
            int rsColumns = readsheet.getColumns();

            // 获取Sheet表中所包含的总行数
            int rsRows = readsheet.getRows();

			// 获取指定单元格的对象引用
			for (int i = 0; i < rsRows; i++) {
				for (int j = 0; j < rsColumns; j++) {
					Cell cell = readsheet.getCell(j, i);
					JfLog.info(LOG, cell.getContents() + " ");
				}
				JfLog.info(LOG, "\n");
			}

            // 利用已经创建的Excel工作薄,创建新的可写入的Excel工作薄
            jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(new File(targetFile), readwb);

            // 读取第一张工作表
            jxl.write.WritableSheet ws = wwb.getSheet(0);

            // 获得第一个单元格对象
            jxl.write.WritableCell wc = ws.getWritableCell(0, 0);

            // 判断单元格的类型, 做出相应的转化
            if (CellType.LABEL.equals(wc.getType())) {
                Label l = (Label) wc;
                l.setString("新姓名");
            }

            // 写入Excel对象
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            JfLog.error(LOG,e.getMessage(),e);
		} finally {
			try {
				if (readwb != null) {
					readwb.close();
				}
			} catch (Exception e) {
				JfLog.error(LOG, e.getMessage(), e);
			}
		}        
    }

}
