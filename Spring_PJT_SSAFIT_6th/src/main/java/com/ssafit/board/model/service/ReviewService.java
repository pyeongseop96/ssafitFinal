package com.ssafit.board.model.service;

import java.util.List;

import com.ssafit.board.model.dto.Review;

public interface ReviewService {

	List<Review> getReviewList(String videoID);
	
	void writeReview(Review review);
	
	void modifyReview(Review review);
	
	Review getReview(int reviewID);
	
	void removeReview(int reviewID);
}
