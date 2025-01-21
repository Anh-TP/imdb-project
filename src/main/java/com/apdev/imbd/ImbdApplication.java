package com.apdev.imbd;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class ImbdApplication {

    @Autowired
    private MovieRepository movieRepository;

    public static void main(String[] args) {
		SpringApplication.run(ImbdApplication.class, args);
	}

    @Transactional
    //    TODO: change to use spring batch to load data for increased performance
    public void readAndSaveData(Path filePath) {
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.skip(1)
                    .limit(300000)
                    .map(this::parseLine)
                    .filter(movie -> movie != null)
                    .forEach(movieRepository::save);

            System.out.println("Completed data processing from file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + filePath + ", " + e.getMessage());
        }
    }

    private Movie parseLine(String line) {
        try {
            String[] columns = line.split("\t");

            String tconst = columns[0].trim();
            String titleType = columns[1].trim();
            String primaryTitle = columns[2].trim();
            String originalTitle = columns[3].trim();
            boolean isAdult = "1".equals(columns[4].trim());
            Integer startYear = parseInteger(columns[5]);
            Integer endYear = parseInteger(columns[6]);
            Integer runtimeMinutes = parseInteger(columns[7]);
            List<String> genres = Arrays.asList(columns[8].split(","));

            return Movie.builder()
                    .tconst(tconst)
                    .titleType(titleType)
                    .primaryTitle(primaryTitle)
                    .originalTitle(originalTitle)
                    .isAdult(isAdult)
                    .startYear(startYear != null ? startYear : 0)
                    .endYear(endYear)
                    .runtimeMinutes(runtimeMinutes)
                    .build();
        } catch (Exception e) {
            System.err.println("Error parsing line: " + line);
            return null;
        }
    }

    private Integer parseInteger(String value) {
        try {
            return (value != null && !value.equals("\\N") && !value.isEmpty())
                    ? Integer.parseInt(value.trim())
                    : null;
        } catch (NumberFormatException e) {
            System.err.println("Invalid integer value: " + value);
            return null;
        }
    }
}