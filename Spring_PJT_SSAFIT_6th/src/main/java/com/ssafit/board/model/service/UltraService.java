package com.ssafit.board.model.service;

import java.util.List;

import com.ssafit.board.model.dto.SearchCondition;
import com.ssafit.board.model.dto.SortInfo;
import com.ssafit.board.model.dto.Ultra;
import com.ssafit.board.model.dto.Video;

public interface UltraService {

    List<Ultra> getList(SortInfo sortInfo);

}
