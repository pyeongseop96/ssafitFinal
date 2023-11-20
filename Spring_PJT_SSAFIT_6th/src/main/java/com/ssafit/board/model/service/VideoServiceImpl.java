package com.ssafit.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.board.model.dao.VideoDao;
import com.ssafit.board.model.dto.SearchCondition;
import com.ssafit.board.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService{

	@Autowired
	private VideoDao videoDao;
	
	@Override
	public List<Video> getList() {
		return videoDao.selectAll();
	}

	@Override
	public Video getVideo(String videoID) {
		return videoDao.selectOne(videoID);
	}

	@Override
	public void writeVideo(Video video) {
		videoDao.insertVideo(video);
	}

	@Override
	public void modifyVideo(Video video) {
		videoDao.updateVideo(video);
	}

	@Override
	public void removeVideo(String videoID) {
		videoDao.deleteVideo(videoID);
	}

	@Override
	public List<Video> search(SearchCondition condition) {
		return videoDao.search(condition);
	}

	@Override
	public void addViewCnt(String videoID) {
		videoDao.updateViewCnt(videoID);
	}
}
