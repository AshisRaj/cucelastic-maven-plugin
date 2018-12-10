package com.araj.cucumber.elasticsearch.files;

import com.araj.cucumber.elasticsearch.exceptions.CucElasticPluginException;
import com.araj.cucumber.elasticsearch.filesystem.FileSystemManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileSystemManagerTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    private FileSystemManager fileSystemManager;

    @Before
    public void setup() {
        fileSystemManager = new FileSystemManager();
    }

    @Test(expected = CucElasticPluginException.class)
    public void invalidSourceFeaturesTest() throws Exception {
        fileSystemManager.getJsonFilePaths("nonexistentpath");
    }

    @Test
    public void validSourceFeaturesTest() throws Exception {
        String jsonPath = testFolder.getRoot().getPath();
        fileSystemManager.getJsonFilePaths(jsonPath);
    }
}
