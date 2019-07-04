package implementations;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import akka.stream.impl.fusing.Collect;
import interfaces.TweetDistictWord;
import models.TweetDistinctWordCountResult;
import play.libs.Json;
import services.TwitterService;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import java.util.*;
/**
 * Implements functionality of displaying a word-level statistics for the 100 latest tweet's 
 * @author Harmeet Singh
 * @version 1.0
 */
public class TweetDistictWordImpl implements TweetDistictWord{

	/**
	 * Counting all the unique words and there count in descending order
	 * @author Harmeet Singh
	 * @param searchKey Search keyword
	 * @param count Number of tweet's
	 * @return TweetDistinctWordCountResult 
	 */

	@Override
	public TweetDistinctWordCountResult getTweetDistinctWordCount(String searchKey, int count) {
		TweetDistinctWordCountResult countResult = new TweetDistinctWordCountResult();
		countResult.setSearchKey(searchKey);
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
		ArrayNode distinctWordJson = Json.newArray();
		
		Map <String, Integer > wordCounter =tweetsStatus.stream()
												.map(tweetS->tweetS.getText())
												.map(s->s.split(" "))
												.flatMap(Arrays::stream)
												.map(s->s.toLowerCase())
												.collect(toMap(w -> w, w -> 1, Integer::sum));
		
		
		Map <String, Integer > wordCounterSorted = wordCounter.entrySet().stream()
	        	.sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
	        			.thenComparing(Map.Entry.<String, Integer>comparingByKey().reversed()))
	        	.collect(Collectors.toMap(Map.Entry::getKey , Map.Entry::getValue,
	        			(e1,e2)-> e2,LinkedHashMap::new));
		wordCounterSorted.entrySet().stream().forEach(e->{System.out.println(e.getValue() + " " + e.getKey());});
		countResult.setDistinctWordsCount(wordCounterSorted);
		return countResult;
	}

}
