package implementations;

import interfaces.TwitterApiInstance;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


/**
 * The class provides implementation to create object for
 * Twitter4j to connect to twitter
 * @author Akshay Bansal
 * @version 1.0
 *
 */
public class TwitterAPiInstanceImpl implements TwitterApiInstance {

	/**
	 * Twitter Object
	 */
	private static Twitter realTwitter;
	/* (non-Javadoc)
	 * @see interfaces.TwitterApiInstance#geTwitterInstance()
	 */
	@Override
	public Twitter geTwitterInstance() {
		if(realTwitter == null) {
			ConfigurationBuilder conf = new ConfigurationBuilder();
			conf.setDebugEnabled(true)
			.setOAuthConsumerKey("1AuzFRQVdSOIDWa7Jl9xl8tCS")
			.setOAuthConsumerSecret("Opb5LycsGkx7u2LvJKXZDKosx6eQhCgjwNi0oYtTP2wGXfSWs7")
			.setOAuthAccessToken("1023051987150168064-Qkr749T4FQw5SeAnwLzYXxeRfwWfQY")
			.setOAuthAccessTokenSecret("H33ep7uAdJ4pdppkA6IfP42NOnlLXmVFcq6weWbKz2r8g");
			TwitterFactory factory = new TwitterFactory(conf.build());
			realTwitter =  factory.getInstance();
		}
		return realTwitter;
	}

}
