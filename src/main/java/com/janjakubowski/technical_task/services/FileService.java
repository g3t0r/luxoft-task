package com.janjakubowski.technical_task.services;

import com.janjakubowski.technical_task.FileToRecordsConverter;
import com.janjakubowski.technical_task.Record;
import com.janjakubowski.technical_task.exceptions.IncorrectDataFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Service
public class FileService {

    private final FileToRecordsConverter fileToRecordsConverter;

    @Autowired
    public FileService(FileToRecordsConverter fileToRecordsConverter) {
        this.fileToRecordsConverter = fileToRecordsConverter;
    }

    public Set<Record> readRecordsFromFile(MultipartFile file) throws IOException, IncorrectDataFormatException {
        return fileToRecordsConverter.convertFileToRecords(file);
    }

}
