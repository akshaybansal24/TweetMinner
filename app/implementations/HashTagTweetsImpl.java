package implementations;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import interfaces.HashTagTweetsSearch;
import models.TweetsResult;
import services.TwitterService;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;


/**
 * Implements functionality of fetching Tweet's by hash tag
 * @author Simranjeet Singh
 * @version 1.0
 *
 */
public class HashTagTweetsImpl implements HashTagTweetsSearch{

	/**
	 * Get 10 latest tweet's from a hash tag asynchronously
	 *  @author Simranjeet Singh
	 *  @param searchKey of type String
	 *  @param count 
	 *  @return TweetsResult
	 */

	@Override
	public TweetsResult getHashTweets(String searchKey, int count) {
		TweetsResult tweetResult = new TweetsResult();
		tweetResult.setSearchKey(searchKey);
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
		List<String> result = tweetsStatus.stream()
								.map(status -> status.getText())
								.collect(toList());
		tweetResult.setTweets(result);
		return tweetResult;
	}

}
