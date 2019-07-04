package implementations;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import interfaces.SearchTweets;
import play.libs.Json;
import services.TwitterService;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
/**
 * Retrieves 10 most recent tweet's for each of search phrases(single or multiple)
 *  provided as input 
 * @author Akshay Bansal
 * @version 1.0
 */
public class SearchTweetsImpl implements SearchTweets{
	/**
	 * List of 10 most recent tweet's that contains the search phrase asynchronously. 
	 * @author Akshay Bansal
	 * @param searchKey Search keyword 
	 * @param count Number of Tweet's 
	 * @return ArrayNode
	 */

	@Override
	public ArrayNode getTweetsAsync(String searchKey, int count) {
		Twitter twitter = TwitterService.twitterObject.geTwitterInstance();
		Query query = new Query(searchKey);
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
		List<CompletableFuture<ObjectNode>> nodesFuture = tweetsStatus.stream()
										.map(status -> CompletableFuture.supplyAsync(
												()->new TweetToJsonImpl().convertTweetToJson(status)))
										.collect(toList());
		
		List<ObjectNode> nodes = nodesFuture.stream().map(CompletableFuture::join)
												.collect(toList());
		nodes.forEach(node->tweets.add(node));
		return tweets;
	}
	
}
