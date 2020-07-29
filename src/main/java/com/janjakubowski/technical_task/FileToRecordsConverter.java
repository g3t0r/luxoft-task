package com.janjakubowski.technical_task;

import com.janjakubowski.technical_task.exceptions.IncorrectDataFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class FileToRecordsConverter {


    private final String REGEX = "^((([A-Z]|[a-z]|[0-9])+),(([A-Z]|[a-z]|[0-9])+),(([A-Z]|[a-z]|[0-9])+),(([0-9])+-([0-9])+-([0-9])+ ([0-9])+:([0-9])+:([0-9])+))$";
    private final Logger log  = LoggerFactory.getLogger(this.getClass());

    public Set<Record> convertFileToRecords(MultipartFile file) throws IOException, IncorrectDataFormatException {

        InputStream inputStream = file.getInputStream();
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = br.readLine();
        log.info(line);
        while (line != null) {
            lines.add(line);
            line = br.readLine();
            log.info(line);
        }

        if (lines.stream().allMatch(Pattern.compile(REGEX).asPredicate())) {
            return lines
                    .stream()
                    .map(this::lineToRecord)
                    .collect(Collectors.toSet());
        } else throw new IncorrectDataFormatException("Line in file does not match regex");
    }

    private Record lineToRecord(String line) {
        String[] fields = line.split(",");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy hh:mm:ss");
        format.setLenient(false);
        Date date = null;
        try {
            date = format.parse(fields[3]);
        } catch (ParseException e) {
            throw new RuntimeException(new IncorrectDataFormatException("Incorrect date format"));
        }

        return new Record(fields[0], fields[1], fields[2], new Timestamp(date.getTime()));
    }

}
