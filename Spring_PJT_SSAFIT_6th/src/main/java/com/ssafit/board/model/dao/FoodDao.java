package com.ssafit.board.model.dao;

import java.util.List;

import com.ssafit.board.model.dto.Food;

public interface FoodDao {

    public List<Food> selectOne(String DESC_KOR);
}
