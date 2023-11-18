package com.ssafit.board.model.dao;

import java.util.List;

import com.ssafit.board.model.dto.Record;

public interface RecordDao {

    public List<Record> selectAll(Record record);
    public void deleteRecord(Record record);
    public void updateRecord(Record record);
}
