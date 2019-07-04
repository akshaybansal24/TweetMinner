package interfaces;

import java.util.List;

import twitter4j.Status;


/**
 * Interface to count sad emotion
 * @author Akshay Bansal
 *
 */
@FunctionalInterface
public interface SadEmotionsCount {
	/**
	 * @param tweetsStatus
	 * @return long
	 */
	long getSadEmotionsCount(List<Status> tweetsStatus);
}
