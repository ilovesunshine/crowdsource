package com.common.inf;


/**
 * 字段加密解密接口
 * 建议需要加解密的Model实现此接口
 * 加密或解密关键字段
 * 
 * @Title: ModelFieldsCheckInf.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-6 上午9:35:50
 * @author FengHaiBing
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public interface ModelFieldsCheckInf {
    
    /**
     * 是否可以解密所有加密的字段
     * 返回false时可抛出AccountSecureException
     * 
     * @return 任何一个字段解密失败就返回false
     * 
     * @author FengHaiBing
     */
    public boolean couldDecryptAllFields();

    /**
     * 是否可以解密所有金额字段
     * 返回false时可抛出AccountSecureException
     * 
     * @return 任何一个金额字段解密失败就返回false
     * 
     * @author FengHaiBing
     */
    public boolean couldDecryptAmountFields();

    /**
     * 加密所有金额外其他关键字段
     * 
     * @author FengHaiBing
     */
    public void encryptKeyFields();

    /**
     * 加密所有金额字段
     * 
     * @author FengHaiBing
     */
    public void encryptAmountFields();
    
    /**
     * 解密所有金额外其他关键字段
     * 
     * @author FengHaiBing
     */
    public void decryptKeyFields();

    /**
     * 解密所有金额字段
     * 
     * @author FengHaiBing
     */
    public void decryptAmountFields();
    
}