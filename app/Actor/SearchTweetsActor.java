package Actor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import play.libs.Json;
import services.TwitterService;
import twitter4j.Twitter;

/**
 * This is actor for searching tweet's
 * @author Akshay Bansal
 * @version 1.0
 */
public class SearchTweetsActor extends AbstractActor{
	
	/**
	 * This method is used to get props for actor
	 * @param out
	 * @return
	 */
	public static Props props(ActorRef out) {
		System.out.println("asasa");
		return Props.create(SearchTweetsActor.class, out);
	}
	
	/**
	 * out - A variable of ActorRef
	 */
	private final ActorRef out;
	
	/**
	 * @param out
	 */
	public SearchTweetsActor(ActorRef out) {
		this.out = out;
	}
	/* (non-Javadoc)
	 * @see akka.actor.AbstractActor#createReceive()
	 */
	@Override
	public Receive createReceive() {
		System.out.println("in actor");
		return receiveBuilder()
				.match(String.class, message->{
					System.out.println("lets see if this works");
					Runnable searchOperation = new Runnable() {
						
						@Override
						public void run() {
							try {
								
								ArrayNode searchResult = TwitterService.getTweets(message, 10,100);
								ObjectNode node = Json.newObject();
								node.put("searchKey", message);
								searchResult.add(node);
								System.out.println(searchResult.size());
								out.tell(searchResult.toString(), self());
							}catch(Exception e) {
							}
						}
					};
					ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
					service.scheduleAtFixedRate(searchOperation, 0, 10, TimeUnit.SECONDS);
				})
				.build();
	}

}
