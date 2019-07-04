package implementations;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import interfaces.UserProfile;
import models.UserProfileResult;
import play.libs.Json;
import services.TwitterService;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * Displays the profile information of the User and latest 10 tweet's of the user
 * @author Anam Dang
 * @version 1.0
 */
public class UserProfileImpl implements UserProfile {
	/**
	 * Fetches all the profile information of a user
	 * @author Anam Dang
	 * @param searchKey Search keyword 
	 * @param count Number of Tweet's 
	 * @return UserProfileResult Returns associated list of tweet's for this user profile
	 */

	@Override
	public UserProfileResult getUserProfile(String searchKey, int count) {
		UserProfileResult profileResult = new UserProfileResult();
		Twitter twitter = TwitterService.twitterObject.geTwitterInstance();
		List<Status> timelineTweets = new ArrayList<Status>();
		ArrayNode userProfile = Json.newArray();
		try {
			User user = twitter.showUser(searchKey);
			Paging paging = new Paging();
			paging.setCount(count);
			timelineTweets = twitter.getUserTimeline(user.getId(),paging);
			StringBuilder stringBuilder = new StringBuilder();
			List<String> timelineTweetsList = new ArrayList<String>();
			if(timelineTweets!=null && timelineTweets.size()>0)
			{
				timelineTweets.forEach(t->{
					timelineTweetsList.add(t.getText());
				});
			}
			profileResult.setTimelineTweets(timelineTweetsList);
			profileResult.setUserDescription(user.getDescription());
			profileResult.setUserFollowerCount(user.getFollowersCount());
			profileResult.setUserId(user.getId());
			profileResult.setUserImageUrl(user.getBiggerProfileImageURL());
			profileResult.setUserIsProtected(user.isProtected());
			profileResult.setUserLocation(user.getLocation());
			profileResult.setUserName(user.getName());
			profileResult.setUserProfileUrl(user.getURL());
			profileResult.setUserScreenName(user.getScreenName());
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profileResult;
	}

}
