package models;

import java.util.List;

public class UserProfileResult {

	/**
	 * userName - A variable of type String
	 * userId - A variable of type long
	 * userScreenName - A variable of type String
	 * userLocation - A variable of type String
	 * userDescription - A variable of type String
	 * userFollowerCount - A variable of type int
	 * userProfileUrl - A variable of type String
	 * userImageUrl - A variable of type String
	 * userIsProtected - A variable of type Boolean
	 * timelineTweets - A variable of type List
	 */
	private String userName;
	private long userId;
	private String userScreenName;
	private String userLocation;
	private String userDescription;
	private int userFollowerCount;
	private String userProfileUrl;
	private String userImageUrl;
	private boolean userIsProtected;
	private List<String> timelineTweets;
	/**
	 * Method to return username
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * Method to set username
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * Method to return UserID
	 * @return
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * Method to set userId
	 * @param userId
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	/**
	 * Method to get user screen name
	 * @return
	 */
	public String getUserScreenName() {
		return userScreenName;
	}
	/**
	 * Method to set user screen name
	 * @param userScreenName
	 */
	public void setUserScreenName(String userScreenName) {
		this.userScreenName = userScreenName;
	}
	/**
	 * Method to get user location
	 * @return
	 */
	public String getUserLocation() {
		return userLocation;
	}
	/**
	 * Method to set set user location
	 * @param userLocation
	 */
	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}
	/**
	 * Method to get user description
	 * @return
	 */
	public String getUserDescription() {
		return userDescription;
	}
	/**
	 * Method to get user description
	 * @param userDescription
	 */
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	/**
	 * Method to get user follower count
	 * @return
	 */
	public int getUserFollowerCount() {
		return userFollowerCount;
	}
	/**
	 * Method to set user follower count
	 * @param userFollowerCount
	 */
	public void setUserFollowerCount(int userFollowerCount) {
		this.userFollowerCount = userFollowerCount;
	}
	/**
	 * Method to get user profile URL
	 * @return
	 */
	public String getUserProfileUrl() {
		return userProfileUrl;
	}
	/**
	 * Method to set user profile url
	 * @param userProfileUrl
	 */
	public void setUserProfileUrl(String userProfileUrl) {
		this.userProfileUrl = userProfileUrl;
	}
	/**
	 * Method to get user image URL
	 * @return
	 */
	public String getUserImageUrl() {
		return userImageUrl;
	}
	/**
	 * Method to set user Image URL
	 * @param userImageUrl
	 */
	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}
	/**
	 * Method to check wheather the user is protected or not
	 * @return
	 */
	public boolean isUserIsProtected() {
		return userIsProtected;
	}
	/**
	 * Method to set the user protected
	 * @param userIsProtected
	 */
	public void setUserIsProtected(boolean userIsProtected) {
		this.userIsProtected = userIsProtected;
	}
	/**
	 * Method to get list of time line tweets
	 * @return
	 */
	public List<String> getTimelineTweets() {
		return timelineTweets;
	}
	/**
	 * Method to set time line tweets
	 * @param timelineTweets
	 */
	public void setTimelineTweets(List<String> timelineTweets) {
		this.timelineTweets = timelineTweets;
	}
	
	
}
