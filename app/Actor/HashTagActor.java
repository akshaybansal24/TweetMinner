package Actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import services.TwitterService;

/**
 * This is actor for fetching tweet's by hashtag's
 * @author Simrenjeet Singh
 * @version 1.0
 */
public class HashTagActor extends AbstractActor{

	/**
	 * This method is used to get props for actor
	 * @return Props
	 */
	public static Props props() {
		return Props.create(HashTagActor.class);
	}
	
	/**
	 * This is the message class for HashTagActor
	 * @author akshay bansal
	 */
	public static class HashTagKey{
		public final String hashTagKey;
		
		/**
		 * @param hashTagKey
		 */
		public HashTagKey(String hashTagKey) {
			this.hashTagKey = hashTagKey;
		}
	}
	/* (non-Javadoc)
	 * @see akka.actor.AbstractActor#createReceive()
	 */
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(HashTagKey.class, searchKey->{
					sender().tell(TwitterService.getHashTagTweets(searchKey.hashTagKey, 10), self());
				}).build();
	}

}
