/**
 * 
 */
package services;

import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.inject.Guice;
import com.google.inject.Injector;

import implementations.HashTagTweetsImpl;
import implementations.Location;
import implementations.ReturnTwitterObject;
import implementations.SearchTweetsImpl;
import implementations.TweetDistictWordImpl;
import implementations.TweetEmotionImpl;
import implementations.UserProfileImpl;
import models.TweetDistinctWordCountResult;
import models.TweetsResult;
import models.UserProfileResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * This class is to implement data fetch operations from twitter API.
 * @author Akshay Bansal
 * @version 1.0
 */
public class TwitterService {
	
	/**
	 * guice - A object of type Injector
	 */
	public static Injector guice = Guice.createInjector(new TwitterModule());
	/**
	 * twitterObject - A object of type ReturnTwitterObject
	 */
	public static ReturnTwitterObject twitterObject = guice.getInstance(ReturnTwitterObject.class);

	/*public static Twitter getTwitterInstance() {
		ConfigurationBuilder conf = new ConfigurationBuilder();
		//Used keys of Kaushik Samanta
		conf.setDebugEnabled(true)
		.setOAuthConsumerKey("1AuzFRQVdSOIDWa7Jl9xl8tCS")
		.setOAuthConsumerSecret("Opb5LycsGkx7u2LvJKXZDKosx6eQhCgjwNi0oYtTP2wGXfSWs7")
		.setOAuthAccessToken("1023051987150168064-Qkr749T4FQw5SeAnwLzYXxeRfwWfQY")
		.setOAuthAccessTokenSecret("H33ep7uAdJ4pdppkA6IfP42NOnlLXmVFcq6weWbKz2r8g");
		TwitterFactory factory = new TwitterFactory(conf.build());
		return factory.getInstance();
	}*/
	
	/**
	 * Fetches tweets based on search(key) asynchronously
	 * @author Akshay Bansal
	 * @param searchKey A variable of type string
	 * @param count A variable of type int
	 * @return tweets based on search(key)
	 * @throws TwitterException
	 */
	public static ArrayNode getTweets(String searchKey,int count, int countEmotion) throws TwitterException {
		return new SearchTweetsImpl().getTweetsAsync(searchKey, count);
	}
	
	/**
	 * @author Akshay Bansal
	 * @param searchKey A variable of type String.
	 * @param count A variable of type int.
	 * @return emotion based on searchKey.
	 * @throws TwitterException
	 */
	public static ArrayNode getTweetsEmotion(String searchKey,int count) throws TwitterException{
		return new TweetEmotionImpl().getTweetEmotion(searchKey, count);
	}
	
	/**
	 * @author Akshay Bansal
	 * @param searchKey A variable of type String
	 * @param count A variable of type int
	 * @return tweet's based on hashtag.
	 * @throws TwitterException
	 */
	public static TweetsResult getHashTagTweets(String searchKey,int count) throws TwitterException {
		return new HashTagTweetsImpl().getHashTweets(searchKey,count);
	}
	
	/**
	 * @author Akshay Bansal
	 * @param searchKey A variable of type String
	 * @param count A variable of type int
	 * @return distinct words based on serachKey
	 * @throws TwitterException
	 */
	public static TweetDistinctWordCountResult getTweetDistinctWordCount(String searchKey,int count) throws TwitterException {
		return new TweetDistictWordImpl().getTweetDistinctWordCount(searchKey, count);
	}
	
	/**
	 * @param location A variable of type String
	 * @param latitude A variable of type double
	 * @param longitude A variable of type double
	 * @param count A variable of type int
	 * @return tweet's based on locations
	 * @throws TwitterException
	 */
	public static TweetsResult getTweetsForLocation(String location, double latitude, double longitude, int count)throws TwitterException {
		return new Location().getTweetsByLocationAsync(location, latitude, longitude, count);
	}
	
	/**
	 * @param userName A variable of type String
	 * @param count A variable of type int
	 * @return the profile of the user
	 * @throws TwitterException
	 */
	public static UserProfileResult getUserProfile(String userName,int count) throws TwitterException {
		return new UserProfileImpl().getUserProfile(userName, count);
	}
}
