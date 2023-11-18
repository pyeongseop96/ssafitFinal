package com.ssafit.board.model.dto;

public class FavoriteChannel {
	
	private String userID;
	private String channelName;

	
	public FavoriteChannel(String userID, String channelName) {
		super();
		this.userID = userID;
		this.channelName = channelName;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	@Override
	public String toString() {
		return "favoriteChannel [userID=" + userID + ", channelName=" + channelName + "]";
	}
}
