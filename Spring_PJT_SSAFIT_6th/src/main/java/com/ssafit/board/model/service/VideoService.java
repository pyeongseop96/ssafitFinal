package com.ssafit.board.model.service;

import java.util.List;

import com.ssafit.board.model.dto.SearchCondition;
import com.ssafit.board.model.dto.Video;

public interface VideoService {
	// 영상 전체 조회
	List<Video> getList();

	// 영상 상세 조회
	Video getVideo(String videoID);

	// 영상 등록
	void writeVideo(Video video);

	// 영상 수정
	void modifyVideo(Video video);

	// 영상 삭제
	void removeVideo(String videoID);

	//검색 버튼을 눌렀을 때 처리할 메서드
	List<Video> search(SearchCondition condition);
	
}
