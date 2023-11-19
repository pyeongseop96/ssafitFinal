package com.ssafit.board.model.service;

import com.ssafit.board.model.dao.VideoRatingDao;
import com.ssafit.board.model.dto.VideoRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoRatingServiceImpl implements  VideoRatingService {

    @Autowired
    private VideoRatingDao videoRatingDao;
    @Override
    public VideoRating getRating(String videoID) {
        return videoRatingDao.selectRating(videoID);
    }

    @Override
    public List<VideoRating> getRatingAll() {
        return videoRatingDao.selectRatingAll();
    }

    @Override
    public void updateRating(String videoID) {
        videoRatingDao.updateRating(videoID);
    }
}
