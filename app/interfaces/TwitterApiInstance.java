package interfaces;

import twitter4j.Twitter;


/**
 * Interface to get Twitter API Instance
 * @author Akshay Bansal
 * @version 1.0
 *
 */
public interface TwitterApiInstance {
	/**
	 * Method to get Twitter Instance
	 * @return Twitter
	 */
	Twitter geTwitterInstance();
}
