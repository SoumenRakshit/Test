package com.org.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.org.exception.TradeReportException;
import com.org.model.BuySell;
import com.org.model.Currency;
import com.org.model.TradeDTO;

/**
 * The Class TradeReportOrganizer.
 */
public class TradeReportOrganizer {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(TradeReportOrganizer.class.getName());
	
	/**
	 * Generate trade report.
	 *
	 * @param filePath the file path
	 * @throws TradeReportException the trade report exception
	 */
	public void generateTradeReport(String filePath) throws TradeReportException{
		
		List<TradeDTO> tradeList = readData(filePath);
		
		Map<Date, BigDecimal> inComing = new HashMap<Date,BigDecimal>();
		Map<Date, BigDecimal> outGoing = new HashMap<Date,BigDecimal>();
		
		Map<String, BigDecimal> entityOutgoing = new HashMap<String,BigDecimal>();
		
		Map<String, BigDecimal> entityInComing = new HashMap<String,BigDecimal>();
		
		List<TradeDTO> rankOutGoing = new ArrayList<TradeDTO>();
		
		List<TradeDTO> rankInComing = new ArrayList<TradeDTO>();
		
		
		for(TradeDTO trade : tradeList){
			switch(trade.getBuyOrSell()){
				case BUY:
					if(outGoing.containsKey(trade.getReviewedSettlement())){
						outGoing.put(trade.getReviewedSettlement(), outGoing.get(trade.getReviewedSettlement()).add(trade.getTradeAmount()));
					}else{
						outGoing.put(trade.getReviewedSettlement(), trade.getTradeAmount());
					}
					if(entityOutgoing.containsKey(trade.getEntity())){
						entityOutgoing.put(trade.getEntity(), entityOutgoing.get(trade.getEntity()).add(trade.getTradeAmount()));
					}else{
						entityOutgoing.put(trade.getEntity(),trade.getTradeAmount());
					}
					break;
				case SELL:
					if(inComing.containsKey(trade.getReviewedSettlement())){
						inComing.put(trade.getReviewedSettlement(), inComing.get(trade.getReviewedSettlement()).add(trade.getTradeAmount()));
					}else{
						inComing.put(trade.getReviewedSettlement(), trade.getTradeAmount());
					}
					if(entityInComing.containsKey(trade.getEntity())){
						entityInComing.put(trade.getEntity(), entityInComing.get(trade.getEntity()).add(trade.getTradeAmount()));
					}else{
						entityInComing.put(trade.getEntity(),trade.getTradeAmount());
					}
					break;
			
			}
			
		}
		for(String key : entityOutgoing.keySet()){
			TradeDTO trade = new TradeDTO();
			trade.setEntity(key);
			trade.setTradeAmount(entityOutgoing.get(key));
			rankOutGoing.add(trade);
		}
		for(String key : entityInComing.keySet()){
			TradeDTO trade = new TradeDTO();
			trade.setEntity(key);
			trade.setTradeAmount(entityInComing.get(key));
			rankInComing.add(trade);
		}

		Collections.sort(rankOutGoing,new Comparator<TradeDTO>() {
	        @Override
	        public int compare(TradeDTO trade1, TradeDTO trade2)
	        {

	            return  trade2.getTradeAmount().compareTo(trade1.getTradeAmount());
	        }
	    });
		
		Collections.sort(rankInComing,new Comparator<TradeDTO>() {
	        @Override
	        public int compare(TradeDTO trade1, TradeDTO trade2)
	        {

	            return  trade2.getTradeAmount().compareTo(trade1.getTradeAmount());
	        }
	    });
		
		LOGGER.info("Amount in USD settled incoming everyday");
		LOGGER.info(inComing.toString());
		
		LOGGER.info("Amount in USD settled outgoing everyday");
		LOGGER.info(outGoing.toString());
		
		LOGGER.info("Incoming Rank 1");
		LOGGER.info(rankInComing.get(0).toString());
		
		
		LOGGER.info("Outgoing Rank 1");
		LOGGER.info(rankOutGoing.get(0).toString());
		
	}
	
	/**
	 * Read data.
	 *
	 * @param filePath the file path
	 * @return the list
	 * @throws TradeReportException the trade report exception
	 */
	/*
	 * Reading the trade details from the input file.
	 */
	private List<TradeDTO> readData(String filePath) throws TradeReportException{
		
		File file;
		FileReader fileReader;
		BufferedReader bufferedReader;
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		
		String line;
		String data[];
		
		List<TradeDTO>dataList = new ArrayList<TradeDTO>();
		
		try{
			LOGGER.info("File Path : "+filePath);
			file = new File(filePath);
			LOGGER.info("File Exists : "+file.exists());
			if(file.exists()){
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);
				
				try {
					while((line = bufferedReader.readLine())!=null){
						data = line.split(",");
						boolean valResult = validateData(data);
						if(!valResult)
							throw new TradeReportException("Validation of Data failed");
						else{
							TradeDTO trade = new TradeDTO();
							trade.setEntity(data[0]);
							trade.setBuyOrSell(BuySell.getBuySell(data[1]));
							trade.setAgreedFX(new BigDecimal(data[2]));
							trade.setCurrency(Currency.getCurrency(data[3]));
							trade.setInstruction(formatter.parse(data[4]));
							trade.setSettlement(formatter.parse(data[5]));
							trade.setUnits(Integer.valueOf(data[6]));
							trade.setPrice(new BigDecimal(data[7]));
							trade.setTradeAmount(trade.getAgreedFX().multiply(trade.getPrice()).multiply(new BigDecimal(trade.getUnits())));
							reviewedSettlement(trade);
							dataList.add(trade);
						}
					}
				}catch(Exception e){
					throw new TradeReportException(e.getMessage());
				}finally{
					bufferedReader.close();
				}
			}else{
				throw new FileNotFoundException(filePath + " File Not Found");
			}
		}catch(Exception e){
			throw new TradeReportException(e.getMessage());
		}
		
		return dataList;
	}
	
	/**
	 * Validate data.
	 *
	 * @param str the str
	 * @return true, if successful
	 */
	/*
	 * Validations of data getting read from the input file. 
	 */
	private static boolean validateData(String[] str) {
		boolean result = true;
		
		// field checks
		if (str[0].isEmpty() || str[1].isEmpty() || str[2].isEmpty()
					|| str[3].isEmpty() || str[4].isEmpty() || str[5].isEmpty() || str[6].isEmpty() || str[7].isEmpty()) {
			LOGGER.info("All fileds are mandatory validation failed");
			result = false;
		}
		
		//Currency check
		if(Currency.getCurrency(str[3]) == null) {
			LOGGER.info("Invalid Currecy");
			result = false;
		}
		
		//Date check
		if(!isValidDate(str[4]) || !isValidDate(str[5])){
			LOGGER.info("Invalid Instruction/Settlement Date");
			result = false;
		}
		return result;	
	}
	
	/**
	 * Reviewed settlement.
	 *
	 * @param trade the trade
	 */
	/*
	 * Review of the settlement date to check whether its a working day or not.
	 */
	@SuppressWarnings("deprecation")
	private static void reviewedSettlement(TradeDTO trade){
		if(trade.getCurrency().equals(Currency.AED) || trade.getCurrency().equals(Currency.SAR)){
			if(trade.getSettlement().getDay() == 5){
				Calendar c = Calendar.getInstance();
				c.setTime(trade.getSettlement());
				c.add(Calendar.DATE, 2);  // number of days to add
				trade.setReviewSettlement(c.getTime());
			}else if(trade.getSettlement().getDay() == 6){
				Calendar c = Calendar.getInstance();
				c.setTime(trade.getSettlement());
				c.add(Calendar.DATE, 1);  // number of days to add
				trade.setReviewSettlement(c.getTime());
			} else{
				trade.setReviewSettlement(trade.getSettlement());
			}
			
		}else{
			if(trade.getSettlement().getDay() == 0){
				Calendar c = Calendar.getInstance();
				c.setTime(trade.getSettlement());
				c.add(Calendar.DATE, 1);  // number of days to add
				trade.setReviewSettlement(c.getTime());
			}else if(trade.getSettlement().getDay() == 6){
				Calendar c = Calendar.getInstance();
				c.setTime(trade.getSettlement());
				c.add(Calendar.DATE, 2);  // number of days to add
				trade.setReviewSettlement(c.getTime());
			} else{
				trade.setReviewSettlement(trade.getSettlement());
			}
			
		}
	}
	
	/**
	 * Checks if is valid date.
	 *
	 * @param inDate the in date
	 * @return true, if is valid date
	 */
	/*
	 * Check for valid date.
	 */
	public static boolean isValidDate(String inDate) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
	    dateFormat.setLenient(false);
	    try {
	      dateFormat.parse(inDate.trim());
	    } catch (ParseException pe) {
	      return false;
	    }
	    return true;
	}
	
	/**
	 * Sort by value.
	 *
	 * @param <K> the key type
	 * @param <V> the value type
	 * @param map the map
	 * @return the map
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
}
