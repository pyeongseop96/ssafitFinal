package com.ssafit.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.board.model.dto.SearchCondition;
import com.ssafit.board.model.dto.Video;
import com.ssafit.board.model.service.VideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api-video")
@Api(tags = "게시판 컨트롤러")
public class VideoRestController {
	
	@Autowired
	private VideoService videoService;
	
	// 1. 영상 목록 조회
	@GetMapping("/video")
	@ApiOperation(value="전체 영상 조회", notes="검색조건 없이 전체 조회합니다.")
	public ResponseEntity<List<Video>> getList(){
		List<Video> list = videoService.getList();
		System.out.println(list);
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}

	// 2. 특정영상 상세정보 보기
	@GetMapping("/video/{videoID}")
	@ApiOperation(value="영상 상세 조회", notes="해당 ID를 가진 영상을 조회합니다.")
	public ResponseEntity<?> detail(@PathVariable String videoID){
		Video video = videoService.getVideo(videoID);
		System.out.println(video);
		if (video != null) {
			return new ResponseEntity<Video>(video, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	// 3. 영상 등록
	@PostMapping("/video")
	@ApiOperation(value = "영상 등록하기", notes = "영상을 등록합니다.")
	public ResponseEntity<Video> write(Video video) {
		videoService.writeVideo(video);
		return new ResponseEntity<Video>(video, HttpStatus.OK);
	}
	
	// 4. 영상 삭제
	@DeleteMapping("/video/{videoID}")
	@ApiOperation(value = "영상 삭제하기", notes = "영상을 삭제합니다.")
	public ResponseEntity<?> delete(@PathVariable String videoID) {
		videoService.removeVideo(videoID);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 5. 영상 수정
	@PutMapping("/video")
	@ApiOperation(value = "영상 수정하기", notes = "영상을 수정합니다.")
	public ResponseEntity<?> update(@RequestBody Video video) {
		videoService.modifyVideo(video);
		return new ResponseEntity<Video>(video, HttpStatus.OK);
	}
	
	// 6. 영상 검색
	@GetMapping("/video/search")
	@ApiOperation(value="영상 검색하기", notes="해당 정보를 가진 영상을 조회합니다.")
	public ResponseEntity<?> search(@RequestBody SearchCondition condition){
		List<Video> list = videoService.search(condition);
		if (list != null) {
			return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
