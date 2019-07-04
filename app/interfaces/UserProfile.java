package interfaces;

import com.fasterxml.jackson.databind.node.ArrayNode;

import models.UserProfileResult;
/**
 *Interface to fetch User Profile.
 * @author Anam Dang
 * @version 1.0
 */
@FunctionalInterface
public interface UserProfile {
	/**
	 * @author Anam Dang
	 * @param searchKey Search keyword 
	 * @param count Number of Tweet's 
	 * @return UserProfileResult Returns associated list of tweet's for this user profile
	 */

	UserProfileResult getUserProfile(String searchKey,int count);
}
