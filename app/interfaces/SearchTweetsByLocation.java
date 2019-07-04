package interfaces;

import com.fasterxml.jackson.databind.node.ArrayNode;

import models.TweetsResult;


/**
 * Interface to fetch tweetss by location
 * @author mohit saini
 *
 */
@FunctionalInterface
public interface SearchTweetsByLocation {
	/**
	 * @param latitude
	 * @param longitude
	 * @param count
	 * @return TweetsResult
	 */
	TweetsResult getTweetsByLocationAsync(String location,double latitude, double longitude, int count);
}
