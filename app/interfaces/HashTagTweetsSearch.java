package interfaces;

import java.util.List;

import models.TweetsResult;


/**
 * Interface to get tweets by Hash Tag
 * @author Akshay Bansal
 *
 */
public interface HashTagTweetsSearch {
	
	/**
	 * Method will return tweets by hash tag
	 * @param searchKey
	 * @param count
	 * @return TweetsResult
	 */
	TweetsResult getHashTweets(String searchKey,int count);
}
