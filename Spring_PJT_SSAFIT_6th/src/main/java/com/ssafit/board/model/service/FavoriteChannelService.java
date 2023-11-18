package com.ssafit.board.model.service;

import java.util.List;

public interface FavoriteChannelService {

	// 구독 채널 조회
	List<String> getFavChannels(String userID);
	
	// 구독 채널 추가
	void addFavChannel(String userID, String channelName);
	
	// 구독 채널 삭제
	void removeFavChannel(String userID, String channelName);
}
