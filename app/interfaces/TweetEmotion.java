package interfaces;

import com.fasterxml.jackson.databind.node.ArrayNode;


/**
 * Interface to fetch emotion from tweetss
 * @author Akshay Bansal
 *
 */
@FunctionalInterface
public interface TweetEmotion {
	
	/**
	 * @param searchKey
	 * @param count
	 * @return ArrayNode
	 */
	ArrayNode getTweetEmotion(String searchKey,int count);
}
