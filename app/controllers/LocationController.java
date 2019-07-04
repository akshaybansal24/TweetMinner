package controllers;

import java.util.concurrent.CompletionStage;

import com.google.inject.Inject;

import Actor.LocationActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import play.mvc.Controller;
import play.mvc.Result;
import scala.compat.java8.FutureConverters;
import services.TwitterService;
import twitter4j.TwitterException;
import static akka.pattern.Patterns.ask;
import views.html.*;
import models.TweetsResult;
/**
 * 
 * This controller is being used to find the location of the tweet.
 * @author Mohit Saini
 * @version 1.0
 * @see LocationController
 *
 */
public class LocationController extends Controller {
	private final ActorRef locationActor;
	
	@Inject
	public LocationController(ActorSystem system) {
		locationActor = system.actorOf(LocationActor.props());
	}
	/**
	 * This method asynchronously fetches the location of 10 latest tweets for search keyword.
	 * @author Mohit Saini
	 * @param latitude - A String value
	 * @param longitude - A String value
	 * @return Result
	 * @throws TwitterException
	 */
	public CompletionStage<Result> searchTweetsByLocation(String location,String latitude, String longitude) throws TwitterException{
		return FutureConverters.toJava(ask(locationActor, new LocationActor.LocationCordinates(location,Double.parseDouble(latitude), Double.parseDouble(longitude)),5000))
				.thenApply(response->ok(locationPage.render((TweetsResult)response)));
	}
}
