package com.org.model;

import static com.org.model.WorkDays.*;

/**
 * The Enum Currency.
 */
public enum Currency {
	
	/** The usd. */
	USD("USD",MONFRI),
	
	/** The eur. */
	EUR("EUR",MONFRI),
	
	/** The aud. */
	AUD("AUD",MONFRI),
	
	/** The cad. */
	CAD("CAD",MONFRI),
	
	/** The cny. */
	CNY("CNY",MONFRI),
	
	/** The hkd. */
	HKD("HKD",MONFRI),
	
	/** The jpy. */
	JPY("JPY",MONFRI),
	
	/** The sar. */
	SAR("SAR",SUNTHURS),
	
	/** The aed. */
	AED("AED",SUNTHURS),
	
	/** The sgp. */
	SGP("SGP",MONFRI);
	
	/** The currency code. */
	private String currencyCode;
	
	/** The work days. */
	private WorkDays workDays;
	
	/**
	 * Instantiates a new currency.
	 *
	 * @param currencyCode the currency code
	 * @param workDays the work days
	 */
	private Currency(final String currencyCode, final WorkDays workDays){
		this.currencyCode = currencyCode;
		this.workDays = workDays;
	}
	
	
	/**
	 * Gets the currency code.
	 *
	 * @return the currency code
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}


	/**
	 * Gets the work days.
	 *
	 * @return the work days
	 */
	public WorkDays getWorkDays() {
		return workDays;
	}


	/**
	 * Gets the currency.
	 *
	 * @param currencyCode the currency code
	 * @return the currency
	 */
	public static Currency getCurrency(final String currencyCode){
		Currency currency  = null;
		for(Currency cur : Currency.values()){
			if(currencyCode.equalsIgnoreCase(cur.getCurrencyCode())){
				currency = cur;
				break;
			}
		}
		return currency;
	}
	
	/**
	 * Gets the currency code.
	 *
	 * @param currency the currency
	 * @return the currency code
	 */
	public static String getCurrencyCode(Currency currency){
		 return currency.getCurrencyCode();
	}
	
}
