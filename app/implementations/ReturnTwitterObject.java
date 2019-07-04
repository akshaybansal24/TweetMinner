package implementations;

import static play.inject.Bindings.bind;
import com.google.inject.Inject;

import interfaces.TwitterApiInstance;
import models.Twitter4jFakeHappy;
import models.Twitter4jFakeNeutral;
import models.Twitter4jFakeSad;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import twitter4j.Twitter;


/**
 * It provides the object of Twitter4j
 * @author Akshay Bansal
 * @version 1.0
 *
 */
public class ReturnTwitterObject implements TwitterApiInstance {

	/**
	 * Twitter Object to be returned
	 */
	private static Twitter twitterInstance;
	/**
	 * boolean flag to check if it is for units tests or not
	 */
	public static boolean isTest = false;
	/**
	 * String for the type of testing
	 */
	public static String emotion = "";
	/**
	 * Application Object
	 */
	public static Application app;
	
	/**
	 * @param apiInstance
	 */
	@Inject
	public ReturnTwitterObject(TwitterApiInstance apiInstance) {
		this.twitterInstance = apiInstance.geTwitterInstance();
	}
	
	
	/* (non-Javadoc)
	 * @see interfaces.TwitterApiInstance#geTwitterInstance()
	 */
	@Override
	public Twitter geTwitterInstance() {
		if(!isTest) {
			return this.twitterInstance;
		}
		else {
			if(emotion.equals("HAPPY")) {
				app = new GuiceApplicationBuilder()
						.overrides(bind(TwitterApiInstance.class).to(Twitter4jFakeHappy.class))
						.build();
				
				TwitterApiInstance apiInstance = app.injector().instanceOf(TwitterApiInstance.class);
				this.twitterInstance = apiInstance.geTwitterInstance();
				return this.twitterInstance;
			}else if(emotion.equals("SAD")) {
				app = new GuiceApplicationBuilder()
						.overrides(bind(TwitterApiInstance.class).to(Twitter4jFakeSad.class))
						.build();
				
				TwitterApiInstance apiInstance = app.injector().instanceOf(TwitterApiInstance.class);
				this.twitterInstance = apiInstance.geTwitterInstance();
				return this.twitterInstance;
			}
			else if(emotion.equalsIgnoreCase("NEUTRAL")) {
				app = new GuiceApplicationBuilder()
						.overrides(bind(TwitterApiInstance.class).to(Twitter4jFakeNeutral.class))
						.build();
				
				TwitterApiInstance apiInstance = app.injector().instanceOf(TwitterApiInstance.class);
				this.twitterInstance = apiInstance.geTwitterInstance();
				return this.twitterInstance;
			}
		}
		return this.twitterInstance;
	}
	

}
