package implementations;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import interfaces.SearchTweetsByLocation;
import models.TweetsResult;
import play.libs.Json;
import services.TwitterService;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
/**
 * Implements functionality of fetching Tweet's by location
 * @author Mohit Saini
 * @version 1.0
 */
public class Location implements SearchTweetsByLocation{
	/**
	 * Get 10 latest tweet's from a location asynchronously,
	 *  wherever it is available through the location field
	 *  @author Mohit Saini
	 *  @param location of type String
	 *  @param latitude Latitude of the geolocation
	 *  @param longitude Longitude of the geolocation
	 *  @param count 
	 *  @return TweetsResult
	 */

	@Override
	public TweetsResult getTweetsByLocationAsync(String location,double latitude, double longitude, int count) {
		TweetsResult locationResult = new TweetsResult();
		locationResult.setSearchKey(location);
		GeoLocation geolocation = new GeoLocation(latitude, longitude);
		Twitter twitter = TwitterService.twitterObject.geTwitterInstance();
		Query query = new Query();
		query.geoCode(geolocation, 5, "mi");
		query.count(count);
		List<Status> tweetsStatus = new ArrayList<Status>();
		try {
			QueryResult queryResult = twitter.search(query);
			tweetsStatus = queryResult.getTweets();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayNode tweets = Json.newArray();
		List<String> result = tweetsStatus.stream()
				.map(status -> status.getText())
				.collect(toList());
		locationResult.setTweets(result);
		return locationResult;
	}

}
