package com.janjakubowski.technical_task;

import com.janjakubowski.technical_task.exceptions.IncorrectDataFormatException;
import com.janjakubowski.technical_task.services.FileService;
import com.janjakubowski.technical_task.services.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@RestController
public class FileController {

    private final FileService fileService;
    private final RecordService recordService;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public FileController(FileService fileService, RecordService recordService) {
        this.fileService = fileService;
        this.recordService = recordService;
    }

    @PostMapping("/api/files")
    public ResponseEntity<Record> handleFileUpdate(@RequestParam("file")MultipartFile file) throws IOException, IncorrectDataFormatException {
        Set<Record> records = fileService.readRecordsFromFile(file);
        records.forEach(record -> {
            log.info(record.toString());
        });

        recordService.saveRecords(records);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IncorrectDataFormatException.class)
    public ResponseEntity<?> handleIncorrectDataFormatException(IncorrectDataFormatException exc) {
        return ResponseEntity.unprocessableEntity().body(exc.getMessage());
    }
}
