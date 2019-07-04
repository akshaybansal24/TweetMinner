package controllers;

import java.util.List;
import java.util.concurrent.CompletionStage;
import models.TweetsResult;
import com.google.inject.Inject;

import Actor.HashTagActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import play.mvc.Controller;
import play.mvc.Result;
import scala.compat.java8.FutureConverters;
import services.TwitterService;
import twitter4j.TwitterException;
import static akka.pattern.Patterns.ask;
import views.html.*;


/**
 * Date July 27,2018
 * This is controller for fetching tweet's by hashtag's
 * @author Simrenjeet Singh
 * @version 1.0
 *
 */
public class HashTagController extends Controller{
	
	/**
	 * Actor Reference of HashTagActor
	 */
	private final ActorRef hashTagActor;
	
	/**
	 * @param system of type ActorSystem
	 */
	@Inject
	public HashTagController(ActorSystem system) {
		hashTagActor = system.actorOf(HashTagActor.props());
	}
	/**
	 * This method asynchronously fetches tweet's by hashtag's
	 * @author Simrenjeet Singh
	 * @param searchKey
	 * @return Result
	 * @throws TwitterException
	 */
	public CompletionStage<Result> searchTweetsByHashTag(String searchKey) throws TwitterException{
		return FutureConverters.toJava(ask(hashTagActor,new HashTagActor.HashTagKey(searchKey),5000))
				.thenApply(response->ok(hashTagPage.render((TweetsResult)response)));
	}

}
