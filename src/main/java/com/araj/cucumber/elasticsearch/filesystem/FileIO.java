/*
 * Copyright 2018 Ashis Raj
 */

package com.araj.cucumber.elasticsearch.filesystem;

import javax.inject.Singleton;

import com.araj.cucumber.elasticsearch.exceptions.filesystem.FileCreationException;
import com.araj.cucumber.elasticsearch.exceptions.filesystem.MissingFileException;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

/**
 * This class manages reading from and writing to files.
 */
@Singleton
public class FileIO {
    /**
     * Write string content to a file.
     *
     * @param content  the string content to be written.
     * @param filePath the complete path to the target file.
     * @throws FileCreationException a {@link FileCreationException} in case the file cannot be created.
     */
    public void writeContentToFile(final String content, final String filePath) throws FileCreationException {
        try (PrintStream ps = new PrintStream(filePath)) {
            ps.println(content);
        } catch (IOException e) {
            throw new FileCreationException(filePath);
        }
    }

    /**
     * Write byte array content to a file.
     *
     * @param content  the byte array content to be written.
     * @param filePath the complete path to the target file.
     * @throws FileCreationException a {@link FileCreationException} in case the file cannot be created.
     */
    public void writeContentToFile(final byte[] content, final String filePath) throws FileCreationException {
        Path path = Paths.get(filePath);
        try {
            Files.write(path, content);
        } catch (Exception e) {
            throw new FileCreationException(path.toString());
        }
    }

    /**
     * Read string content from a file.
     *
     * @param filePath the complete path to the source file.
     * @return the file contents as a string.
     * @throws MissingFileException a {@link MissingFileException} in case the file does not exist.
     */
    public String readContentFromFile(final String filePath) throws MissingFileException {
        try {
            byte[] bytes = readAllBytes(Paths.get(filePath));
            return new String(bytes).trim();
        } catch (IOException e) {
            throw new MissingFileException(filePath);
        }
    }
}
