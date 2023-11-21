package com.ssafit.board.model.dao;

import java.util.List;

import com.ssafit.board.model.dto.SearchCondition;
import com.ssafit.board.model.dto.Video;

public interface VideoDao {
	
	// 전체 영상을 몽땅 들고 오쎄용
	public List<Video> selectAll();

	// ID에 해당하는 영상 하나 가져오기
	public Video selectOne(String videoID);

	// 영상 등록
	public void insertVideo(Video video);

	// 영상 삭제
	public void deleteVideo(String videoID);

	// 영상 수정
	public void updateVideo(Video video);

	// 조회수 증가
	public void updateViewCnt(String videoID);

	// 검색 기능
	public List<Video> search(SearchCondition condition);

	// 채널 영상 조회
	public List<Video> selectChannelVideos(String channelName);


}
