package com.ssafit.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.board.model.dto.Review;
import com.ssafit.board.model.service.ReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-review")
@Api(tags = "게시판 컨트롤러")
public class ReviewRestController {
	@Autowired
	private ReviewService reviewService;

	// 1. 리뷰 전체 조회
	@GetMapping("/reviewAll")
	@CrossOrigin(origins = "*")
	@ApiOperation(value = "전체 리뷰 조회", notes = "영상 ID에 따른 모든 리뷰가 나옵니다.")
	public ResponseEntity<?> list(@RequestParam String videoID) {
		List<Review> list = reviewService.getReviewList(videoID);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}

	// 2. 리뷰 상세 조회
	@GetMapping("/review")
	@CrossOrigin(origins = "*")
	@ApiOperation(value = "리뷰 상세 보기", notes = "리뷰 ID에 따른 상세 내용이 나옵니다.")
	public ResponseEntity<Review> detail(@RequestParam int reviewID) {
		Review review = reviewService.getReview(reviewID);
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}

	// 3. 리뷰 등록
	@PostMapping("/review")
	@CrossOrigin(origins = "*")
	@ApiOperation(value = "리뷰 등록하기", notes = "리뷰를 등록합니다.")
	public ResponseEntity<Review> write( Review review) {
		reviewService.writeReview(review);
		return new ResponseEntity<Review>(review, HttpStatus.CREATED);
	}

	// 4. 리뷰 삭제
	@DeleteMapping("/review/{reviewID}")
	@CrossOrigin(origins = "*")
	@ApiOperation(value = "리뷰 삭제", notes = "리뷰 ID에 따른 리뷰를 삭제합니다.")
	public ResponseEntity<Void> delete(@PathVariable int reviewID) {
		reviewService.removeReview(reviewID);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 5. 리뷰 수정
	@PutMapping("/review")
	@CrossOrigin(origins = "*")
	@ApiOperation(value = "리뷰 수정", notes = "리뷰 ID에 따른 리뷰를 수정합니다.")
	public ResponseEntity<Review> update(Review review) {
		reviewService.modifyReview(review);
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}
	
	
}
