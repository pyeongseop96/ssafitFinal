package com.ssafit.board.model.dao;

import java.util.List;

import com.ssafit.board.model.dto.SearchCondition;
import com.ssafit.board.model.dto.SortInfo;
import com.ssafit.board.model.dto.Ultra;
import com.ssafit.board.model.dto.Video;

public interface UltraDao {

    public List<Ultra> selectList(SortInfo sortInfo);

}
