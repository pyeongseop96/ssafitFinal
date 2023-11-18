package com.ssafit.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.board.model.dao.RecordDao;
import com.ssafit.board.model.dto.Record;

@Service
public class RecordServiceImpl implements RecordService{

    @Autowired
    private RecordDao recordDao;

    @Override
    public List<Record> getRecord(Record record) {
        return recordDao.selectAll(record);
    }

    @Override
    public void removeRecord(Record record) {
        recordDao.deleteRecord(record);

    }

    @Override
    public void modifyRecord(Record record) {
        recordDao.updateRecord(record);
    }


}
