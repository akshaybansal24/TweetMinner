package interfaces;

import java.util.List;

import twitter4j.Status;


/**
 * Interface to count Happy tweets
 * @author Akshay Bansal
 *
 */
@FunctionalInterface
public interface HappyEmotionsCount {
	/**
	 * @param tweetsStatus
	 * @return long
	 */
	long getHappyEmotionsCount(List<Status> tweetsStatus);
}
