package com.janjakubowski.technical_task;

import com.janjakubowski.technical_task.exceptions.IncorrectDataFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.Set;

@SpringBootTest
public class FileToRecordsConverterTests {

    @Test
    void fileMatchRegex() throws Exception {
        // Given
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/test-file.txt");
        MultipartFile multipartFile = new MockMultipartFile("nazwa.txt", fileInputStream);
        FileToRecordsConverter fileToRecordsConverter = new FileToRecordsConverter();

        // When
        Set<Record> records = fileToRecordsConverter.convertFileToRecords(multipartFile);

        // Then
        Assertions.assertEquals(1, records.size());
        Assertions.assertEquals(new Record("asdf", "asdf", "asdf", "01-02-2001 01:23:34"), records.toArray()[0]);
    }

    @Test
    void fileDoesNotMatchRegex() throws Exception {

        // Given
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/test-file-incorrect.txt");
        MultipartFile multipartFile = new MockMultipartFile("nazwa.txt", fileInputStream);
        FileToRecordsConverter fileToRecordsConverter = new FileToRecordsConverter();

        // When - Then
        Assertions.assertThrows(IncorrectDataFormatException.class, () -> {
            fileToRecordsConverter.convertFileToRecords(multipartFile);
        });
    }
}
