package com.ssafit.board.model.dao;

import java.util.List;

import com.ssafit.board.model.dto.FavoriteVideo;

public interface FavoriteVideoDao {

	// 찜하지 않은 영상도 전부 다 조회
	public List<FavoriteVideo> selectAll(String userID);
	
	// 찜한 영상 전부다 조회~
	public List<FavoriteVideo> selectFavVideos(String userID);
	
	// 찜한 영상 목록에 추가
	public void insertFavoriteVideo(String userID, String videoID);
	
	// 찜한 영상 목록에서 삭제
	public void deleteFavoriteVideo(String userID, String videoID);
}
