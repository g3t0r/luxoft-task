package com.janjakubowski.technical_task;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordController {

    @GetMapping
    ResponseEntity<?> record(String primaryKey) {
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @DeleteMapping
    ResponseEntity<?> delete(String primaryKey) {
        return (ResponseEntity<?>) ResponseEntity.ok();
    }
}
