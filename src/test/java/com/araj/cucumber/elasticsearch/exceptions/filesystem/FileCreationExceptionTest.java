/*
 * Copyright Ashis Raj
 */

package com.araj.cucumber.elasticsearch.exceptions.filesystem;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class FileCreationExceptionTest {

    @Test
    public void testErrorMessage() {
        FileCreationException exception = new FileCreationException("Filename");
        assertThat("File 'Filename' could not be created.").isEqualTo(exception.getMessage());
    }
}
