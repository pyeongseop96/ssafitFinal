package com.ssafit.board.model.dto;

public class FavoriteVideo extends Video{
	
	private String userID;

	public FavoriteVideo(String userID, String videoID, String title, String channelName, int viewCnt, String partInfo) {
		super(videoID, title, channelName, viewCnt, partInfo);
		this.userID = userID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
}
