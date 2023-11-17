package com.ssafit.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.board.model.dto.FavoriteVideo;
import com.ssafit.board.model.service.FavoriteChannelService;
import com.ssafit.board.model.service.FavoriteVideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-favorite")
@Api(tags = "게시판 컨트롤러")
@CrossOrigin("*")
public class FavoriteRestController {

	@Autowired
	private FavoriteVideoService favVideoService;
	
	@Autowired
	private FavoriteChannelService favChanService;
	
	
	// 찜하지 않았으면 userID값이 null이고 찜했으면 userID에 해당 유저의 ID가 들어간 favoriteVideo테이블의 비디오 목록 반환
	@PutMapping("/video/all")
	@ApiOperation(value = "모든 영상 반환", notes = "유저 ID에 따른 찜 여부를 표시하여 모든 영상들을 반환합니다.")
	public ResponseEntity<?> allVideo(@RequestBody String userID) {
		// 로그인한 경우
//		if (userID != null) {
		System.out.println(userID);
		List<FavoriteVideo> list = favVideoService.getAll(userID);
		System.out.println(list.toString());
		return new ResponseEntity<List<FavoriteVideo>>(list, HttpStatus.OK);
//		}
//		return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
	}	
	
	

	// 찜한 비디오 목록 반환
	@PutMapping("/video/favorite")
	@ApiOperation(value = "찜한 영상 반환", notes = "유저 ID에 따른 찜한 영상들이 나옵니다.")
	public ResponseEntity<?> favoriteVideo(@RequestBody String userID) {
		// 로그인한 경우
//		if (userID != null) {
		System.out.println(userID);
		List<FavoriteVideo> list = favVideoService.getFavVideos(userID);
		System.out.println(list.toString());
		return new ResponseEntity<List<FavoriteVideo>>(list, HttpStatus.OK);
//		}
//		return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
	}

	// 구독한 채널 목록 반환
	@PutMapping("/channel")
	@ApiOperation(value = "구독한 채널명 반환", notes = "유저 ID에 따른 구독 채널 이름들이 나옵니다.")
	public ResponseEntity<?> favoriteChannel(@RequestBody String userID) {
		// 로그인한 경우
//			if (userID != null) {
		System.out.println(userID);
		List<String> list = favChanService.getFavChannels(userID);
		System.out.println(list.toString());
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
//			}
//			return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	// 찜한 영상 추가 및 삭제
	@PostMapping("/toggle/video")
	@ApiOperation(value = "찜 등록 및 해제", notes = "해당 유저의 찜 목록에서 동영상을 추가하거나 제거합니다.")
	public ResponseEntity<?> toggleFavVideo(@RequestParam String userID, @RequestParam String videoID, @RequestParam Boolean isFavorite) {
		System.out.println(userID);
		System.out.println(videoID);
		if (isFavorite) {
			// 찜한 비디오가 들어오면 목록에서 삭제
			favVideoService.removeFavVideo(userID, videoID);
		} else {
			// 찜하지 않은 비디오의 경우 목록에 추가
			favVideoService.addFavVideo(userID, videoID);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
		
//		return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	// 구독 채널 추가 및 삭제
	@PostMapping("/toggle/channel")
	@ApiOperation(value = "채널 구독 및 해제", notes = "해당 유저의 구독 목록에서 채널을 구독하거나 해제합니다.")
	public ResponseEntity<?> toggleFavChannel(@RequestParam String userID, @RequestParam String channelName, @RequestParam Boolean isFavorite) {
		
//		if (userID != null) {
//			if (isFavorite) { // 구독한 경우
//				favChanService.removeFavChannel(userID, channelName);
//			} else { //  구독하지 않은 경우
//				favChanService.addFavChannel(userID, channelName);
//			}
//			return new ResponseEntity<Void>(HttpStatus.OK);
//		}
		
		return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
	}
}
