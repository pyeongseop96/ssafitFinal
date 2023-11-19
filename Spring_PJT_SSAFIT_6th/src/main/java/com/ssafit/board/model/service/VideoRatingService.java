package com.ssafit.board.model.service;


import com.ssafit.board.model.dto.VideoRating;

import java.util.List;

public interface VideoRatingService {

    // 영상 상세 조회
    VideoRating getRating(String videoID);

    List<VideoRating> getRatingAll();
}
