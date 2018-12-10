package com.araj.cucumber.elasticsearch.files;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.araj.cucumber.elasticsearch.filesystem.FileIO;

import com.araj.cucumber.elasticsearch.exceptions.filesystem.FileCreationException;
import com.araj.cucumber.elasticsearch.exceptions.filesystem.MissingFileException;

import static org.assertj.core.api.Assertions.*;

public class FileIOTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    private FileIO fileIO = new FileIO();

    @Test(expected = FileCreationException.class)
    public void writeToInvalidFileTest() throws Exception {
        fileIO.writeContentToFile((byte[]) null, "");
    }

    @Test
    public void fileReadWriteTest() throws Exception {
        String testString = "This is a test!";
        String path = testFolder.getRoot().getPath().concat("/test.temp");
        fileIO.writeContentToFile(testString, path);
        assertThat(testString).isEqualTo(fileIO.readContentFromFile(path));
    }

    @Test(expected = FileCreationException.class)
    public void writeContentToFileStringInvalidTest() throws Exception {
        String testString = "This is a test!";
        String path = testFolder.getRoot().getPath().substring(1);
        fileIO.writeContentToFile(testString, path);
    }

    @Test(expected = FileCreationException.class)
    public void writeContentToFileByteArrayInvalidTest() throws Exception {
        String path = testFolder.getRoot().getPath();
        fileIO.writeContentToFile(new byte[]{}, path);
    }

    @Test(expected = MissingFileException.class)
    public void readFromMissingFileTest() throws Exception {
        String wrongPath = testFolder.getRoot().getPath().concat("/missing.temp");
        fileIO.readContentFromFile(wrongPath);
    }
}
