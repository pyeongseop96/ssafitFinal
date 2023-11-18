package com.ssafit.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.board.model.dao.FavoriteChannelDao;

@Service
public class FavoriteChannelServiceImpl implements FavoriteChannelService {
	
	@Autowired
	private FavoriteChannelDao favChanDao;

	// 구독 채널 조회
	@Override
	public List<String> getFavChannels(String userID) {
		return favChanDao.selectFavChannels(userID);
	}

	@Override
	public void addFavChannel(String userID, String channelName) {
		favChanDao.insertFavoriteChannel(userID, channelName);
	}

	@Override
	public void removeFavChannel(String userID, String channelName) {
		favChanDao.deleteFavoriteChannel(userID, channelName);
	}
}
