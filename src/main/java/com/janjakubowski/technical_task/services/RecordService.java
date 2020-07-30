package com.janjakubowski.technical_task.services;

import com.janjakubowski.technical_task.Record;
import com.janjakubowski.technical_task.RecordRepository;
import com.janjakubowski.technical_task.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public Set<Record> getAllRecords() {
        return StreamSupport
                .stream(recordRepository
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toSet());
    }

    public void saveRecords(Set<Record> records) {
        recordRepository.saveAll(records);
    }

    public Record findRecord(String primaryKey) {
        Optional<Record> record = recordRepository.findById(primaryKey);
        return record.orElseThrow(() -> new RecordNotFoundException(primaryKey));
    }

    public void deleteRecord(String primaryKey) {
        Optional<Record> record = recordRepository.findById(primaryKey);
        recordRepository.delete(record.orElseThrow(() -> new RecordNotFoundException(primaryKey)));
    }

}
