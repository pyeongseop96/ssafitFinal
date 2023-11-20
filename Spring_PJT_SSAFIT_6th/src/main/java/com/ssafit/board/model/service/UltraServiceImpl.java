package com.ssafit.board.model.service;

import java.util.List;

import com.ssafit.board.model.dao.UltraDao;
import com.ssafit.board.model.dto.SortInfo;
import com.ssafit.board.model.dto.Ultra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.board.model.dao.VideoDao;
import com.ssafit.board.model.dto.SearchCondition;
import com.ssafit.board.model.dto.Video;

@Service
public class UltraServiceImpl implements UltraService{

    @Autowired
    private UltraDao ultraDao;

    @Override
    public List<Ultra> getList(SortInfo sortInfo) {
        return ultraDao.selectList(sortInfo);
    }
}
