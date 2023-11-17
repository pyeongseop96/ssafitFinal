package com.ssafit.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.board.model.dao.ReviewDao;
import com.ssafit.board.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewDao reviewDao;

	@Autowired
	public void setReviewDao(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}

	// 리뷰 목록 조회
	@Override
	public List<Review> getReviewList(String videoID) {
		return reviewDao.selectAllReview(videoID);
	}

	// 리뷰 작성
	@Override
	public void writeReview(Review review) {
		reviewDao.insertReview(review);
	}

	// 리뷰 수정
	@Override
	public void modifyReview(Review review) {
		reviewDao.updateReview(review);
	}

	// 리뷰 상세 조회
	@Override
	public Review getReview(int reviewID) {
		return reviewDao.selectReview(reviewID);
	};

	// 리뷰 삭제
	@Override
	public void removeReview(int reviewID) {
		reviewDao.deleteReview(reviewID);
	}
	

}
