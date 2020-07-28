package com.janjakubowski.technical_task;

import com.janjakubowski.technical_task.exceptions.IncorrectDataFormatException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class FileToRecordsConverter {


//    private final String REGEX = "^((([A-Z]|[a-z]|[0-9])+),(([A-Z]|[a-z]|[0-9])+),(([A-Z]|[a-z]|[0-9])+),(([A-Z]|[a-z]|[0-9])+))$";
    private final String REGEX = "^((([A-Z]|[a-z]|[0-9])+),(([A-Z]|[a-z]|[0-9])+),(([A-Z]|[a-z]|[0-9])+),(([0-9])+-([0-9])+-([0-9])+ ([0-9])+:([0-9])+:([0-9])+))$";

    public Set<Record> convertFileToRecords(MultipartFile file) throws IOException, IncorrectDataFormatException {

        InputStream inputStream = file.getInputStream();
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = br.readLine();
        while (line != null) {
            lines.add(line);
            line = br.readLine();
        }


        if (lines.stream().allMatch(Pattern.compile(REGEX).asPredicate())) {
            return lines
                    .stream()
                    .map(this::lineToRecord)
                    .collect(Collectors.toSet());
        } else throw new IncorrectDataFormatException("Line in file does not match regex");
    }

    private Record lineToRecord(String line) {
        String fields[] = line.split(",");
        return new Record(fields[0], fields[1], fields[2], fields[3]);
    }
}
