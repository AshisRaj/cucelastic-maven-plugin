package com.araj.cucumber.elasticsearch.properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.araj.cucumber.elasticsearch.exceptions.properties.WrongOrMissingPropertyException;
import com.araj.cucumber.elasticsearch.logging.CucElasticPluginLogger;

public class PropertyManagerTest {
    private PropertyManager propertyManager;
    private CucElasticPluginLogger logger;

    @Before
    public void setup() {
        logger = mock(CucElasticPluginLogger.class);
        propertyManager = new PropertyManager(logger);
    }

    @Test
    public void sourceJsonReportDirectoryTest() {
        propertyManager.setSourceJsonReportDirectory("test");
        assertThat(propertyManager.getSourceJsonReportDirectory(), is("test"));
    }

    @Test(expected = WrongOrMissingPropertyException.class)
    public void missingJsonReportDirectoryTest() throws Exception {
        propertyManager.validateSettings();
    }

    @Test(expected = WrongOrMissingPropertyException.class)
    public void missingHtmlReportDirectoryTest() throws Exception {
        propertyManager.setSourceJsonReportDirectory("test");
        propertyManager.validateSettings();
    }

    @Test
    public void logFullPropertiesTest() {
        propertyManager.logProperties();
        verify(logger, times(15)).info(anyString());
    }
}
