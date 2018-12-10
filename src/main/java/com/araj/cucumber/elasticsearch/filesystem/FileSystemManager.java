/*
 * Copyright 2018 Ashis Raj
 */

package com.araj.cucumber.elasticsearch.filesystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import com.araj.cucumber.elasticsearch.exceptions.CucElasticPluginException;

@Singleton
public class FileSystemManager {
    /**
     * Return a list of JSON files in a given directory.
     *
     * @param sourcePath The path in which to search for JSON files.
     * @return A list of JSON file paths.
     * @throws CucElasticPluginException see {@link CucElasticPluginException}.
     */
    public List<Path> getJsonFilePaths(final String sourcePath) throws CucElasticPluginException {
        List<Path> jsonFilePaths;
        try {
            jsonFilePaths =
                    Files.walk(Paths.get(sourcePath))
                            .filter(Files::isRegularFile)
                            .filter(p -> p.toString().toLowerCase().endsWith(".json"))
                            .collect(Collectors.toList());

        } catch (IOException e) {
            throw new CucElasticPluginException(
                    "Unable to traverse JSON files in " + sourcePath);
        }
        return jsonFilePaths;
    }

     /**
     * Copy file to a new location.
     *
     * @param source      The source file.
     * @param destination The destination file.
     * @throws CucElasticPluginException see {@link CucElasticPluginException}.
     */
    public void copyResource(final String source, final String destination) throws CucElasticPluginException {
        Path sourcePath = Paths.get(source);
        Path destinationPath = Paths.get(destination);
        try {
            Files.copy(sourcePath, destinationPath);
        } catch (IOException e) {
            throw new CucElasticPluginException("Cannot copy resource '" + source + "': " + e.getMessage());
        }
    }
}
