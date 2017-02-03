/*
 * Trade Reporting Executor - To execute the code.
*/

package com.org.executor;

import com.org.service.TradeReportOrganizer;

/**
 * The Class TradeReportingExceutor.
 */
public class TradeReportingExceutor {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		TradeReportOrganizer trdReportOrg = new TradeReportOrganizer();
		trdReportOrg.generateTradeReport("TradeDetailsInputData.txt");

	}

}
