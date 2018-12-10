package com.araj.cucumber.elasticsearch.exceptions;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * The base Cucumber Report plugin exception that all exceptions extend.
 * Since this extends the {@link MojoExecutionException},
 * it will stop the plugin execution when thrown.
 */
public class CucElasticPluginException extends MojoExecutionException {
    /**
     * Constructor.
     *
     * @param message The error message for the exception.
     */
    public CucElasticPluginException(final String message) {
        super(message);
    }
}

