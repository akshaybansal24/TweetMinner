package Actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import services.TwitterService;

/**
 *  This is actor for counting the words in the tweet's
 * @author Harmeet Singh
 *
 */
public class TweetWordCountActor extends AbstractActor {

	/**
	 * This method returns the props
	 * @return
	 */
	public static Props props() {
		return Props.create(TweetWordCountActor.class);
	}
	
	/**
	 * This is the message class for counting words of tweets
	 * @author Harmeet Singh
	 *
	 */
	public static class TweetWordCountKey{
		public final String searchKey;
		
		public TweetWordCountKey(String searchKey) {
			this.searchKey = searchKey;
		}
	}
	
	/* (non-Javadoc)
	 * @see akka.actor.AbstractActor#createReceive()
	 */
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(TweetWordCountKey.class, searchKey->{
					sender().tell(TwitterService.getTweetDistinctWordCount(searchKey.searchKey, 100), self());
				}).build();
	}

}
