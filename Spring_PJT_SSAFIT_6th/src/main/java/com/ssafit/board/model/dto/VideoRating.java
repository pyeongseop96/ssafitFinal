package com.ssafit.board.model.dto;

public class VideoRating {
    private String videoID;
    private Integer averageRating = 0;
    private Integer totalReviews = 0;

    public VideoRating() {
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public Integer getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Integer averateRating) {
        this.averageRating = averateRating;
    }

    public Integer getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(Integer totalReviews) {
        this.totalReviews = totalReviews;
    }
}
