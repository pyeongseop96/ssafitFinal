package com.ssafit.board.model.dao;

import java.util.List;

public interface FavoriteChannelDao {
	
	// 구독한 채널 전부 다 조회~
	public List<String> selectFavChannels(String userID);
	
	// 구독 채널 목록에 추가
	public void insertFavoriteChannel(String userID, String channelName);
	
	// 구독 채널 목록에서 삭제
	public void deleteFavoriteChannel(String userID, String channelName);
}
