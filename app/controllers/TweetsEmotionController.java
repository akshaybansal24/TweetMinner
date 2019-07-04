package controllers;

import java.util.concurrent.CompletionStage;

import com.google.inject.Inject;

import Actor.TweetEmotionActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import play.mvc.Controller;
import play.mvc.Result;
import scala.compat.java8.FutureConverters;
import static akka.pattern.Patterns.ask;
import com.fasterxml.jackson.databind.node.ArrayNode;
import views.html.*;


/**
 * The class is used to provide endpoint to get tweet sentiment
 * @author Akshay Bansal
 * @version 1.0
 */
public class TweetsEmotionController extends Controller{
	
	/**
	 * ActorRef of TweetEmotionActor
	 */
	private final ActorRef tweetEmotionActor;
	/**
	 * @param actorSystem
	 */
	@Inject
	public TweetsEmotionController(ActorSystem actorSystem) {
		tweetEmotionActor = actorSystem.actorOf(TweetEmotionActor.props());
	}
	
	/**
	 * The method is used to get the tweet sentiment
	 * @param Key
	 * @return CompletionStage<Result>
	 */
	public CompletionStage<Result> getTweetsEmotion(String Key) {
		return FutureConverters.toJava(ask(tweetEmotionActor, new TweetEmotionActor.TweetEmotionKey(Key),5000))
				.thenApplyAsync(tweets->ok((ArrayNode)tweets));
	}

}
