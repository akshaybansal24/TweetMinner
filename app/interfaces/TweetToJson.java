package interfaces;

import com.fasterxml.jackson.databind.node.ObjectNode;

import twitter4j.Status;

/**
 *Interface to convert Status to JSON.
 * @author Akshay Bansal
 * @version 1.0
 */
@FunctionalInterface
public interface TweetToJson{
	
	/**
	 * @param s
	 * @return ObjectNode
	 */
	ObjectNode convertTweetToJson(Status s);
}
