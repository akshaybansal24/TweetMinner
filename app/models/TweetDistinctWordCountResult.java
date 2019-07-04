package models;

import java.util.Map;


/**
 * Data Type for Tweet Search Result
 * @author Mohit Saini
 * @version 1.0
 *
 */
public class TweetDistinctWordCountResult {
	
	/**
	 * search key
	 */
	private String searchKey;
	/**
	 * Map of distinct words and their counts
	 */
	private Map<String, Integer> distinctWordsCount;
	
	
	/**
	 * Method to retrieve search key
	 * @return String
	 */
	public String getSearchKey() {
		return searchKey;
	}
	/**
	 * Method to put search key in Data Object
	 * @param searchKey
	 */
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	/**
	 * Method  to return distinct words and their counts
	 * @return Map<String,Integer>
	 */
	public Map<String, Integer> getDistinctWordsCount() {
		return distinctWordsCount;
	}
	/**
	 * Method to put distinct words and their counts
	 * @param distinctWordsCount
	 */
	public void setDistinctWordsCount(Map<String, Integer> distinctWordsCount) {
		this.distinctWordsCount = distinctWordsCount;
	}
	
	
}
