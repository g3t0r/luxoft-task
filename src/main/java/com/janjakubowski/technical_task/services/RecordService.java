package com.janjakubowski.technical_task.services;

import com.janjakubowski.technical_task.Record;
import com.janjakubowski.technical_task.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public void saveRecords(Set<Record> records) {
        recordRepository.saveAll(records);
    }

}
