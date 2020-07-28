package com.janjakubowski.technical_task;

import com.janjakubowski.technical_task.exceptions.RecordNotFoundException;
import com.janjakubowski.technical_task.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class RecordController {

    private RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("api/records")
    public ResponseEntity<Set<Record>> records() {
        return ResponseEntity.ok(recordService.getAllRecords());
    }

    @GetMapping("api/records/{id}")
    public ResponseEntity<Record> record(@PathVariable("id") String primaryKey) throws RecordNotFoundException {
        Record record = recordService.findRecord(primaryKey);
        return ResponseEntity.ok(record);
    }

    @DeleteMapping("api/records/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String primaryKey) throws RecordNotFoundException {
        recordService.deleteRecord(primaryKey);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> handleRecordNotFoundException(RecordNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
