package com.ssafit.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.board.model.dao.FavoriteVideoDao;
import com.ssafit.board.model.dto.FavoriteVideo;

@Service
public class FavoriteVideoServiceImpl implements FavoriteVideoService {

	@Autowired
	private FavoriteVideoDao favVideoDao;
	
	// 찜 여부 상관없이 영상 조회
	@Override
	public List<FavoriteVideo> getAll(String userID) {
		return favVideoDao.selectAll(userID);
	}

	// 찜한 영상 조회
	@Override
	public List<FavoriteVideo> getFavVideos(String userID) {
		return favVideoDao.selectFavVideos(userID);
	}
	
	@Override
	public void addFavVideo(String userID, String videoID) {
		favVideoDao.insertFavoriteVideo(userID, videoID);
	}

	@Override
	public void removeFavVideo(String userID, String videoID) {
		favVideoDao.deleteFavoriteVideo(userID, videoID);
	}

	///////////////////수정한 부분////////////////////
	///////////////////수정한 부분////////////////////
	///////////////////수정한 부분////////////////////
	@Override
	public List<FavoriteVideo> getPartVideo(String userID, String partInfo) {
		return favVideoDao.selectPartVideo(userID, partInfo);
	}
	///////////////////수정한 부분////////////////////
	///////////////////수정한 부분////////////////////
	///////////////////수정한 부분////////////////////

}
