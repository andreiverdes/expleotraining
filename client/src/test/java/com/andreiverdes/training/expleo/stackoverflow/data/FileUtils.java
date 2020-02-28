package com.andreiverdes.training.expleo.stackoverflow.data;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileUtils {

    public String getQuestionsJsonString(String fileName) throws IOException {
        URL url = getClass().getClassLoader().getResource(fileName);
        File file = new File(url.getFile());
        return new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
    }
}
