package com.org.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class TradeDTO.
 */
public class TradeDTO {
	
	/** The entity. */
	String entity;
	
	/** The buy or sell. */
	BuySell buyOrSell;
	
	/** The agreed FX. */
	BigDecimal agreedFX;
	
	/** The currency. */
	Currency currency;
	
	/** The instruction. */
	Date instruction;
	
	/** The settlement. */
	Date settlement;
	
	/** The reviewed settlement. */
	Date reviewedSettlement;
	
	/** The units. */
	Integer units;
	
	/** The price. */
	BigDecimal price;
	
	/** The trade amount. */
	BigDecimal tradeAmount;
	
	/**
	 * Gets the entity.
	 *
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}
	
	/**
	 * Sets the entity.
	 *
	 * @param entity the new entity
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	/**
	 * Gets the buy or sell.
	 *
	 * @return the buy or sell
	 */
	public BuySell getBuyOrSell() {
		return buyOrSell;
	}
	
	/**
	 * Sets the buy or sell.
	 *
	 * @param buyOrSell the new buy or sell
	 */
	public void setBuyOrSell(BuySell buyOrSell) {
		this.buyOrSell = buyOrSell;
	}
	
	/**
	 * Gets the agreed FX.
	 *
	 * @return the agreed FX
	 */
	public BigDecimal getAgreedFX() {
		return agreedFX;
	}
	
	/**
	 * Sets the agreed FX.
	 *
	 * @param agreedFX the new agreed FX
	 */
	public void setAgreedFX(BigDecimal agreedFX) {
		this.agreedFX = agreedFX;
	}
	
	/**
	 * Gets the currency.
	 *
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}
	
	/**
	 * Sets the currency.
	 *
	 * @param currency the new currency
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	/**
	 * Gets the instruction.
	 *
	 * @return the instruction
	 */
	public Date getInstruction() {
		return instruction;
	}
	
	/**
	 * Sets the instruction.
	 *
	 * @param instruction the new instruction
	 */
	public void setInstruction(Date instruction) {
		this.instruction = instruction;
	}
	
	/**
	 * Gets the settlement.
	 *
	 * @return the settlement
	 */
	public Date getSettlement() {
		return settlement;
	}
	
	/**
	 * Sets the settlement.
	 *
	 * @param settlement the new settlement
	 */
	public void setSettlement(Date settlement) {
		this.settlement = settlement;
	}
	
	/**
	 * Gets the review settlement.
	 *
	 * @return the review settlement
	 */
	public Date getReviewSettlement() {
		return reviewedSettlement;
	}
	
	/**
	 * Sets the review settlement.
	 *
	 * @param reviewSettlement the new review settlement
	 */
	public void setReviewSettlement(Date reviewSettlement) {
		this.reviewedSettlement = reviewSettlement;
	}
	
	/**
	 * Gets the units.
	 *
	 * @return the units
	 */
	public Integer getUnits() {
		return units;
	}
	
	/**
	 * Sets the units.
	 *
	 * @param units the new units
	 */
	public void setUnits(Integer units) {
		this.units = units;
	}
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * Gets the reviewed settlement.
	 *
	 * @return the reviewed settlement
	 */
	public Date getReviewedSettlement() {
		return reviewedSettlement;
	}
	
	/**
	 * Sets the reviewed settlement.
	 *
	 * @param reviewedSettlement the new reviewed settlement
	 */
	public void setReviewedSettlement(Date reviewedSettlement) {
		this.reviewedSettlement = reviewedSettlement;
	}
	
	/**
	 * Gets the trade amount.
	 *
	 * @return the trade amount
	 */
	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}
	
	/**
	 * Sets the trade amount.
	 *
	 * @param tradeAmount the new trade amount
	 */
	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TradeDTO [entity=" + entity + ", buyOrSell=" + buyOrSell + ", agreedFX=" + agreedFX + ", currency="
				+ currency + ", instruction=" + instruction + ", settlement=" + settlement + ", reviewedSettlement="
				+ reviewedSettlement + ", units=" + units + ", price=" + price + ", tradeAmount=" + tradeAmount + "]";
	}
	
	

} 
