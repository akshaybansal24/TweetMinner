package controllers;

import java.util.concurrent.CompletionStage;

import com.google.inject.Inject;

import Actor.TweetWordCountActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import models.TweetDistinctWordCountResult;
import play.mvc.Controller;
import play.mvc.Result;
import scala.compat.java8.FutureConverters;
import twitter4j.TwitterException;
import static akka.pattern.Patterns.ask;
import views.html.*;
/**
 * 
 * This is controller for determining the count of all
 * the distinct words used in the 100 latest tweets.
 * @author Harmeet Singh
 * @version 1.0
 * @see SearchTweetWordController
 */
public class SearchTweetWordController extends Controller{
	
	/**
	 * ActorRef for TweetWordCountActor
	 */
	private final ActorRef tweetWordCountActor;
	
	/**
	 * @param system
	 */
	@Inject
	public SearchTweetWordController(ActorSystem system) {
		tweetWordCountActor = system.actorOf(TweetWordCountActor.props());
	}
	/**
	 * This method asynchronously counts the distinct words
	 * in 100 latest tweets and displaying it.
	 * @author Harmeet Singh
	 * @param tweetWord - A string of keyword(s)
	 * @return Result
	 * @throws TwitterException
	 */
	public CompletionStage<Result> searchTweetDistinctWordCount(String tweetWord) throws TwitterException{
		return FutureConverters.toJava(ask(tweetWordCountActor,new TweetWordCountActor.TweetWordCountKey(tweetWord),5000))
				.thenApply(response->ok(tweetWordCountPage.render((TweetDistinctWordCountResult)response)));
	}
}
