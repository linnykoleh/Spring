package com.ps.sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MusicCleaner {

    public static void main(String[] args) throws IOException {
        String location = "/Volumes/JULES/";
        Files.find(Paths.get(location), 999, (p, bfa) -> bfa.isRegularFile())
                .forEach(System.out::println);

    }
}
