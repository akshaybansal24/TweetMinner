package interfaces;

import com.fasterxml.jackson.databind.node.ArrayNode;

import models.TweetDistinctWordCountResult;


/**
 * Interface to fetch distic words and their count
 * @author Harmeet
 *
 */
@FunctionalInterface
public interface TweetDistictWord {
	/**
	 * @param searchKey
	 * @param count
	 * @return TweetDistinctWordCountResult
	 */
	TweetDistinctWordCountResult getTweetDistinctWordCount(String searchKey,int count);
}
