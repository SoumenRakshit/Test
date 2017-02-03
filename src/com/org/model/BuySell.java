package com.org.model;

/**
 * The Enum BuySell.
 */
public enum BuySell {
	
	/** The buy. */
	BUY("B"),
	
	/** The sell. */
	SELL("S");
	
	/** The code. */
	private String code;
	
	/**
	 * Instantiates a new buy sell.
	 *
	 * @param buySell the buy sell
	 */
	private BuySell(final String buySell){
		this.code = buySell;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Gets the buy sell.
	 *
	 * @param code the code
	 * @return the buy sell
	 */
	public static BuySell getBuySell(final String code){
		BuySell buySell  = null;
		for(BuySell bs : BuySell.values()){
			if(code.equalsIgnoreCase(bs.getCode())){
				buySell = bs;
				break;
			}
		}
		return buySell;
	}
}
