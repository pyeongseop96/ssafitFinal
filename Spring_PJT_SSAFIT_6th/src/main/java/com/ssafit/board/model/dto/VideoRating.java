package com.ssafit.board.model.dto;

public class VideoRating {
    private String videoID;
    private float averageRating = 0;
    private float totalReviews = 0;

    public VideoRating() {
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averateRating) {
        this.averageRating = averateRating;
    }

    public float getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(float totalReviews) {
        this.totalReviews = totalReviews;
    }
}
