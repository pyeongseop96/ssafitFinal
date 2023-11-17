package com.ssafit.board.model.dto;

public class Record {
	private String recordDate;
	private String userID;
	private String tag;
	private Integer weight = null;
	private Integer eatCal = null;
	private Integer burnCal = null;
	private String text;
	
	public Record() {
	}
	
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getEatCal() {
		return eatCal;
	}
	public void setEatCal(Integer eatCal) {
		this.eatCal = eatCal;
	}
	public Integer getBurnCal() {
		return burnCal;
	}
	public void setBurnCal(Integer burnCal) {
		this.burnCal = burnCal;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Record [recordDate=" + recordDate + ", userID=" + userID + ", tag=" + tag + ", weight=" + weight
				+ ", eatCal=" + eatCal + ", burnCal=" + burnCal + ", text=" + text + "]";
	}
	
	

}
