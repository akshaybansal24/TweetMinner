package Actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import implementations.Location;
import services.TwitterService;

/**
 * This is actor for fetching tweet's by location
 * @author Mohit Saini
 *@version 1.0
 */
public class LocationActor extends AbstractActor {

	/**
	 * This method is used to get props for actor
	 * @return
	 */
	public static Props props() {
		return Props.create(LocationActor.class);
	}
	
	
	/**
	 * This is the message class for LocationActor
	 * @author Mohit Saini
	 *@version 1.0
	 */
	public static class LocationCordinates{
		
		/**
		 * location - A variable of type String
		 */
		public final String location;
		/**
		 * latitude - A variable of type double
		 */
		public final double latitude;
		/**
		 * longitude - A variable of type double
		 */
		public final double longitude;
		
		/**
		 * 
		 * @param location
		 * @param latitude
		 * @param longitude
		 */
		public LocationCordinates(String location,double latitude,double longitude) {
			this.location = location;
			this.latitude = latitude;
			this.longitude = longitude;
		}
	}
	
	/* (non-Javadoc)
	 * @see akka.actor.AbstractActor#createReceive()
	 */
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(LocationCordinates.class, location->{
					sender().tell(TwitterService.getTweetsForLocation(location.location, location.latitude, location.longitude, 10), self());
				}).build();
	}

}
