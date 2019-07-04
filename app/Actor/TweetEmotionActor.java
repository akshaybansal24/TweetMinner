package Actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import services.TwitterService;

/**
 * This is actor for fetching tweet's sentiment
 * @author Harmeet Singh
 * @version 1.0
 *
 */
public class TweetEmotionActor extends AbstractActor {

	/**
	 * This method is used to get props for actor
	 * @return
	 */
	public static Props props() {
		return Props.create(TweetEmotionActor.class);
	}
	
	/**
	 * This is the message class for tweet's sentiment actor
	 * @author Harmeet Singh
	 * @version 1.0
	 *
	 */
	public static class TweetEmotionKey{
		public final String searchKey;
		
		public TweetEmotionKey(String searchKey) {
			this.searchKey = searchKey;
		}
	}
	/* (non-Javadoc)
	 * @see akka.actor.AbstractActor#createReceive()
	 */
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(TweetEmotionKey.class, searchKey->{
					sender().tell(TwitterService.getTweetsEmotion(searchKey.searchKey,100), self());
				}).build();
	}

}
