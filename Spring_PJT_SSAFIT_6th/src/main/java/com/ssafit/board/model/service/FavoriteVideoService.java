package com.ssafit.board.model.service;

import java.util.List;

import com.ssafit.board.model.dto.FavoriteVideo;

public interface FavoriteVideoService {
	
	// 찜 여부 상관없이 영상 조회
	public List<FavoriteVideo> getAll(String userID);
	
	// 찜한 영상 조회
	List<FavoriteVideo> getFavVideos(String userID);
	
	// 찜한 영상 추가
	void addFavVideo(String userID, String videoID);
	
	// 찜한 영상 삭제
	void removeFavVideo(String userID, String videoID);

	
	///////////////////수정한 부분////////////////////
	///////////////////수정한 부분////////////////////
	///////////////////수정한 부분////////////////////
	// 운동부위의 영상을 찜여부를 포함해 조회
	List<FavoriteVideo> getPartVideo(String userID, String partInfo);
	///////////////////수정한 부분////////////////////
	///////////////////수정한 부분////////////////////
	///////////////////수정한 부분////////////////////
}
