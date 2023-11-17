package com.ssafit.board.model.service;

import java.util.List;

import com.ssafit.board.model.dto.Record;

public interface RecordService {
	List<Record> getRecord(Record record);
	void removeRecord(Record record);
	void modifyRecord(Record record);
}
