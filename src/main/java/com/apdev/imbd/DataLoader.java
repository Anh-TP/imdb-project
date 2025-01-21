package com.apdev.imbd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class DataLoader implements CommandLineRunner {

    private final ImbdApplication imbdApplication;

    public DataLoader(ImbdApplication imdbApplication) {
        this.imbdApplication = imdbApplication;
    }

    @Override
    public void run(String... args) throws Exception {
//        TODO: do not hardcode file path
        imbdApplication.readAndSaveData(Path.of("src/main/resources/title.basics.tsv"));
    }
}