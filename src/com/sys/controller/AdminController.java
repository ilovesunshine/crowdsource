package com.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admincontroller/*")
public class AdminController extends SysController {
	
//	private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);
//    /**
//     * 生成账单
//     * @param date
//     */
//    public void createBillIndex(Date date){
//	     View view = new View(SUCCESS);
//         this.render(view);
//    }
//    
//    /**
//     * 生成账单
//     * @param date
//     */
//    public void createBill(Date date){
//    	this.doAuthCheck();
//	     View view = new View(SUCCESS);
//        try {
//            BillsJob job = new BillsJob();
//            job.setCreateBillDate(date);
//            job.execute();
//            view.bind("msg", "账单生成成功！");
//        } catch (Exception e) {
//             JfLog.error(LOG,e.getMessage(),e);
//             view.bind("errorMsg", "订单生成失败！");
//         }
//         this.render(view);
//    }
//    
//    /**
//     * 刷新系统参数数据缓存
//     * @param url
//     */
//    public void resetDict(String action){
//        if("RefreshPayConfigDict".equals(action)){
//            DictUtil.resetDict("payConfig");
//            JfAuditor.audit("刷新系统缓存参数", "刷新成功", "", JfAuditor.ACT_SUCESS, "系统管理员");
//        }
//    }

}
