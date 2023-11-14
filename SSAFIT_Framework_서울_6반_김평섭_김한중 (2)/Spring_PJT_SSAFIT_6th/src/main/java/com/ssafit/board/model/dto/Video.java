package com.ssafit.board.model.dto;

public class Video {
	
	private String videoID;
	private String title;
	private String channelName;
	private int viewCnt;
	private String partInfo;
	
	
	public Video(String videoID, String title, String channelName, int viewCnt, String partInfo) {
		super();
		this.videoID = videoID;
		this.title = title;
		this.channelName = channelName;
		this.viewCnt = viewCnt;
		this.partInfo = partInfo;
	}
	
	
	public String getVideoID() {
		return videoID;
	}
	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getPartInfo() {
		return partInfo;
	}
	public void setPartInfo(String partInfo) {
		this.partInfo = partInfo;
	}


	@Override
	public String toString() {
		return "Video [videoID=" + videoID + ", title=" + title + ", channelName=" + channelName + ", viewCnt="
				+ viewCnt + ", partInfo=" + partInfo + "]";
	}
}

