package com.ssafit.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.board.model.dao.FoodDao;
import com.ssafit.board.model.dto.Food;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    private FoodDao foodDao;

    @Override
    public List<Food> getFood(String DESC_KOR) {
        return foodDao.selectOne(DESC_KOR);
    }

}
