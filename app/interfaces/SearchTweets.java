package interfaces;

import com.fasterxml.jackson.databind.node.ArrayNode;


/**
 * Interface to fetch tweetss by search keyword
 * @author akshay bansal
 *
 */
@FunctionalInterface
public interface SearchTweets{
	/**
	 * @param searchKey
	 * @param count
	 * @return ArrayNode
	 */
	ArrayNode getTweetsAsync(String searchKey,int count);
}
