package com.ssafit.board.controller;

import java.util.List;

import com.ssafit.board.model.dto.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafit.board.model.dto.SearchCondition;
import com.ssafit.board.model.dto.VideoRating;
import com.ssafit.board.model.service.VideoRatingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api-video")
@Api(tags = "게시판 컨트롤러")
public class VideoRatingRestController {

    @Autowired
    private VideoRatingService videoRatingService;

    // 1. 영상 별점, 조회수 조회
    @GetMapping("/rating")
    @ApiOperation(value="영상별 별점, 조회수 조회", notes="별점, 조회수")
    public ResponseEntity<VideoRating> getList(@RequestParam String videoID) {
        VideoRating rating = videoRatingService.getRating(videoID);
        return new ResponseEntity<VideoRating>(rating, HttpStatus.OK);
    }

    // 2. 전체영상 별점, 조회수 조회
    @GetMapping("/ratingAll")
    @ApiOperation(value="전체영상 별점, 조회수 조회", notes="별점, 조회수")
    public ResponseEntity<List<VideoRating>> getListAll() {
        List<VideoRating> rating = videoRatingService.getRatingAll();
        return new ResponseEntity<List<VideoRating>>(rating, HttpStatus.OK);
    }

    // 3. 영상 하나 별점 업데이트
    @GetMapping("/review")
    @ApiOperation(value="영상 별점 업데이트", notes="별점")
    public void updateRating(@RequestParam String videoID) {
        videoRatingService.updateRating(videoID);
    }
}
