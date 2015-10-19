package com.common.exception;

/**
 * @Title: JfEventException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-17 下午5:16:32
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfEventException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4505394761189396182L;

	/**
	 * 
	 */
	public JfEventException() {
		super();
	}

	/**
	 * @param e
	 * @param t
	 */
	public JfEventException(String e, Throwable t) {
		super(e, t);
	}

	/**
	 * @param t
	 */
	public JfEventException(String t) {
		super(t);
	}

	/**
	 * @param t
	 */
	public JfEventException(Throwable t) {
		super(t);
	}

}
