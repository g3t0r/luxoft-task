package com.janjakubowski.technical_task;

import com.janjakubowski.technical_task.exceptions.FileDoesNotMatchRequirementsException;
import com.janjakubowski.technical_task.exceptions.IncorrectDateFormatException;
import com.janjakubowski.technical_task.exceptions.RecordNotFoundException;
import com.janjakubowski.technical_task.services.FileService;
import com.janjakubowski.technical_task.services.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@RestController
public class RecordController {

    private RecordService recordService;
    private FileService fileService;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RecordController(RecordService recordService, FileService fileService) {
        this.recordService = recordService;
        this.fileService = fileService;
    }

    @PostMapping("/api/records")
    public ResponseEntity<Record> handleFileUpdate(@RequestParam("file") MultipartFile file) throws IOException, IncorrectDateFormatException {
        Set<Record> records = fileService.readRecordsFromFile(file);
        records.forEach(record -> {
            log.info(record.toString());
        });

        recordService.saveRecords(records);
        return ResponseEntity.ok().build();
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

    @ExceptionHandler(IncorrectDateFormatException.class)
    public ResponseEntity<?> handleIncorrectDataFormatException(IncorrectDateFormatException exc) {
        return ResponseEntity.unprocessableEntity().body(exc.getMessage());
    }

    @ExceptionHandler(FileDoesNotMatchRequirementsException.class)
    public ResponseEntity<?> handleFileThatDoesNotMatchRegex(FileDoesNotMatchRequirementsException exc) {
        return ResponseEntity.unprocessableEntity().body(exc.getMessage());
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> handleRecordNotFoundException(RecordNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
