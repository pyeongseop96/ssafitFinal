package com.ssafit.board.model.dao;

import com.ssafit.board.model.dto.VideoRating;

import java.util.List;

public interface VideoRatingDao {

    public VideoRating selectRating(String videoID);

    public List<VideoRating> selectRatingAll();

    public void updateRating(String videoID);
}
