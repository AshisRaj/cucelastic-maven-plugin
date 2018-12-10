package com.araj.cucumber.elasticsearch.exceptions.filesystem;

import com.araj.cucumber.elasticsearch.exceptions.CucElasticPluginException;

/**
 * Thrown when a file cannot be created.
 */
public class FileCreationException extends CucElasticPluginException {
    /**
     * Constructor.
     *
     * @param fileName The file to be created.
     */
    public FileCreationException(final String fileName) {
        super("File '" + fileName + "' could not be created.");
    }
}
