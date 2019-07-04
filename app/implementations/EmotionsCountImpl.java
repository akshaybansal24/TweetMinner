package implementations;

import java.util.List;

import interfaces.HappyEmotionsCount;
import interfaces.SadEmotionsCount;
import twitter4j.Status;

/**
 * This class implements the functionality of determining 
 * if tweet's are happy, sad or neutral.
 * @author Akshay Bansal
 * @version 1.0
 */
public class EmotionsCountImpl implements HappyEmotionsCount,SadEmotionsCount{
	
	/**
	 * This method is scanning the tweet's for a specific 
	 * pattern of happy emoticon's or individual happy emoticon in the fetched tweet's.
	 * It returns the number of tweets having happy emoticons.
	 * @author Akshay Bansal
	 * @param tweetsStatus
	 * @return count
	 * @see interfaces.HappyEmotionsCount#getHappyEmotionsCount(java.util.List)
	 */

	@Override
	public long getHappyEmotionsCount(List<Status> tweetsStatus) {
		long count = tweetsStatus.stream().map(s->s.getText())
				.filter(s->{
					String pattern = ".*[☺😊😃😄🙂😀]+.*";
					return s.matches(pattern);
				})
				.count();
				
		return count;
	}
	
	/**
	 * This method is scanning the tweet's for a specific 
	 * pattern of sad tweet's or individual emoticon's in the fetched tweet's.
	 * It returns the number of tweets having sad emoticons.
	 * @author Akshay Bansal
	 * @param tweetsStatus
	 * @return count
	 * @see interfaces.HappyEmotionsCount#getHappyEmotionsCount(java.util.List)
	 */

	@Override
	public long getSadEmotionsCount(List<Status> tweetsStatus) {
		long count = tweetsStatus.stream().map(s->s.getText())
				.filter(s->{
					String pattern = ".*[☹🙁☹️😢😭😿]+.*";
					return s.matches(pattern);
				})
				.count();
				
		return count;
	}

}