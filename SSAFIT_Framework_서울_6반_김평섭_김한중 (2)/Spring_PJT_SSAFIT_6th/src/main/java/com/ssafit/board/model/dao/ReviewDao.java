package com.ssafit.board.model.dao;

import java.util.List;

import com.ssafit.board.model.dto.Review;

public interface ReviewDao {
	
	public List<Review> selectAllReview(String videoID);
	
	public void insertReview(Review review);
	
	public void updateReview(Review review);
	
	public Review selectReview(int reviewID);
	
	public void deleteReview(int reviewID);
	
	public void updateReviewCnt(int reviewID);
	
	
}
