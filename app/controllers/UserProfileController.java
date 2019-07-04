package controllers;

import java.util.concurrent.CompletionStage;

import com.google.inject.Inject;

import Actor.UserProfileActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import play.mvc.Controller;
import play.mvc.Result;
import scala.compat.java8.FutureConverters;
import services.TwitterService;
import twitter4j.TwitterException;
import views.html.*;
import static akka.pattern.Patterns.ask;
import models.UserProfileResult;
/**
 * This is controller for displaying user profile.
 * @author Anam Dang
 * @version 1.0
 * @see UserProfileController
 */
public class UserProfileController extends Controller{
	
	/**
	 * ActorRef of UserProfileActor
	 */
	private final ActorRef userProfileActor;
	
	/**
	 * @param system
	 */
	@Inject
	public UserProfileController(ActorSystem system) {
		userProfileActor = system.actorOf(UserProfileActor.props());
	}
	/**
	 * This method asynchronously fetches the user profile and displays
	 * their Username, location, picture and 10 latest tweet's.
	 * @author Anam Dang
	 * @param userName - A string
	 * @return Result
	 * @throws TwitterException
	 */
	public CompletionStage<Result> getUserProfile(String userName) throws TwitterException{
		return FutureConverters.toJava(ask(userProfileActor,new UserProfileActor.UserProfileQuery(userName),10000))
				.thenApply(response->ok(userProfilePage.render((UserProfileResult)response)));
	}

}
