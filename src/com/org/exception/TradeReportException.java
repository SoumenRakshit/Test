
package com.org.exception;


/**
 * The Class TradeReportException.
 */
public class TradeReportException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2735423510847074443L;
	
	/**
	 * Instantiates a new trade report exception.
	 *
	 * @param message the message
	 */
	public TradeReportException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new trade report exception.
	 *
	 * @param throwable the throwable
	 */
	public TradeReportException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * Instantiates a new trade report exception.
	 *
	 * @param message the message
	 * @param throwable the throwable
	 */
	public TradeReportException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
