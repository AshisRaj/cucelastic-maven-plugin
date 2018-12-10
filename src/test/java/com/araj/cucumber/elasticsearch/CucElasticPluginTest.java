package com.araj.cucumber.elasticsearch;


import org.junit.Before;
import org.junit.Test;

import com.araj.cucumber.elasticsearch.CucElasticPlugin;
import com.araj.cucumber.elasticsearch.exceptions.CucElasticPluginException;
import com.araj.cucumber.elasticsearch.filesystem.FileIO;
import com.araj.cucumber.elasticsearch.filesystem.FileSystemManager;
import com.araj.cucumber.elasticsearch.json.JsonPojoConverter;
import com.araj.cucumber.elasticsearch.logging.CucElasticPluginLogger;
import com.araj.cucumber.elasticsearch.properties.PropertyManager;
import com.araj.cucumber.elasticsearch.utils.CucElasticPluginReportGenerator;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CucElasticPluginTest {

    private CucElasticPlugin cucElasticPlugin;
    private JsonPojoConverter jsonPojoConverter;
    private FileIO fileIO;

    @Before
    public void setup() throws CucElasticPluginException {
        CucElasticPluginLogger cluecumberLogger = mock(CucElasticPluginLogger.class);
        PropertyManager propertyManager = mock(PropertyManager.class);

        FileSystemManager fileSystemManager = mock(FileSystemManager.class);
        List<Path> fileList = new ArrayList<>();
        Path path = mock(Path.class);
        fileList.add(path);
        when(fileSystemManager.getJsonFilePaths(anyString())).thenReturn(fileList);

        fileIO = mock(FileIO.class);
        jsonPojoConverter = mock(JsonPojoConverter.class);
        CucElasticPluginReportGenerator reportGenerator = mock(CucElasticPluginReportGenerator.class);
        cucElasticPlugin = new CucElasticPlugin(
                cluecumberLogger,
                propertyManager,
                fileSystemManager,
                fileIO,
                jsonPojoConverter,
                reportGenerator
        );
    }

    @Test
    public void executeTest() throws CucElasticPluginException {
    	cucElasticPlugin.execute();
    }

    @Test
    public void noErrorOnUnparsableJsonTest() throws CucElasticPluginException {
        when(fileIO.readContentFromFile(any())).thenReturn("json");
        when(jsonPojoConverter.convertJsonToReportPojos("json")).thenThrow(new CucElasticPluginException("failure"));
        cucElasticPlugin.execute();
    }
}
