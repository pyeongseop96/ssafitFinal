package com.ssafit.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.board.model.dto.Video;
import com.ssafit.board.model.service.VideoService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api-channel")
@Api(tags = "게시판 컨트롤러")
@CrossOrigin("*")
public class ChannelRestContoller {

	@Autowired
	private VideoService videoService;
	
	@GetMapping("/{channelName}")
	public ResponseEntity<?> getChannelVideoList(@PathVariable String channelName) {
		List<Video> list = videoService.getChannelVideos(channelName);
		
		if(list != null) {
			return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
