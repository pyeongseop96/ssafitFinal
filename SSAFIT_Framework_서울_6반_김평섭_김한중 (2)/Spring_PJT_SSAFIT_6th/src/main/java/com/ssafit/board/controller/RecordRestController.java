package com.ssafit.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafit.board.model.dto.Record;
import com.ssafit.board.model.service.RecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-record")
@Api(tags = "게시판 컨트롤러")
public class RecordRestController {
	@Autowired
	private RecordService recordService;

	@GetMapping("/recordAll")
	@ApiOperation(value = "달별 기록 조회", notes = "유저 ID, 날짜에 따른 모든 리뷰가 나옵니다.")
	public ResponseEntity<?> list(@RequestParam String userID, @RequestParam String recordDate) {
		Record record = new Record();
		record.setUserID(userID);
		record.setRecordDate(recordDate);
		List<Record> list = recordService.getRecord(record);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Record>>(list, HttpStatus.OK);
	}

}
