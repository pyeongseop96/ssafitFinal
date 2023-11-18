package com.ssafit.board.model.dto;

public class Review {
	private int reviewID;
	private String videoID;
	private String userID;
	private String title;
	private String content;
	private String regDate;
	private int reviewCnt;
	
	public Review() {
	}

	public int getReviewID() {
		return reviewID;
	}

	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}

	public String getVideoID() {
		return videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getReviewCnt() {
		return reviewCnt;
	}

	public void setReviewCnt(int reviewCnt) {
		this.reviewCnt = reviewCnt;
	}

	@Override
	public String toString() {
		return "Review [reviewID=" + reviewID + ", videoID=" + videoID + ", userID=" + userID + ", title=" + title
				+ ", content=" + content + ", regDate=" + regDate + ", reviewCnt=" + reviewCnt + "]";
	}
	
	
}
