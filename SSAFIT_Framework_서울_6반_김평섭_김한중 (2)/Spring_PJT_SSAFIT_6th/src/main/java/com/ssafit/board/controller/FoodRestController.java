package com.ssafit.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.board.model.dto.Food;
import com.ssafit.board.model.service.FoodService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-food")
@Api(tags = "게시판 컨트롤러")
public class FoodRestController {
	
	@Autowired
	private FoodService foodService;
	
	@GetMapping("/food")
	@ApiOperation(value="식품 상세 조회", notes="해당 단어를 포함하는 식품을 조회합니다.")
	public ResponseEntity<?> detail(@RequestParam String DESC_KOR){
		List<Food> food = foodService.getFood(DESC_KOR);
		if (food != null) {
			return new ResponseEntity<List<Food>>(food, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
