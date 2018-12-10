package com.araj.cucumber.elasticsearch.json.postprocessors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import javax.activation.MimeType;

import org.junit.Before;
import org.junit.Test;

import com.araj.cucumber.elasticsearch.filesystem.FileIO;
import com.araj.cucumber.elasticsearch.json.pojo.Element;
import com.araj.cucumber.elasticsearch.json.pojo.Step;
import com.araj.cucumber.elasticsearch.logging.CucElasticPluginLogger;
import com.araj.cucumber.elasticsearch.properties.PropertyManager;

public class ElementPostProcessorTest {
    private ElementPostProcessor elementPostProcessor;

    @Before
    public void setup() {
        PropertyManager propertyManager = mock(PropertyManager.class);
        FileIO fileIO = mock(FileIO.class);
        CucElasticPluginLogger logger = new CucElasticPluginLogger();
        elementPostProcessor = new ElementPostProcessor(propertyManager, fileIO, logger);
    }

    @Test
    public void postDesiralizeAddScenarioIndexBackgroundScenarioTest() {
        Element element = new Element();
        assertThat(0).isEqualTo(element.getScenarioIndex());
        elementPostProcessor.postDeserialize(element, null, null);
        assertThat(0).isEqualTo(element.getScenarioIndex());
    }

    @Test
    public void postDesiralizeAddScenarioIndexTest() {
        Element element = new Element();
        element.setType("scenario");
        assertThat(0).isEqualTo(element.getScenarioIndex());
        elementPostProcessor.postDeserialize(element, null, null);
        assertThat(1).isEqualTo(element.getScenarioIndex());
    }

    @Test
    public void postDeserializeTest() {
        Element element = new Element();
        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        steps.add(step);
        element.setSteps(steps);

        elementPostProcessor.postDeserialize(element, null, null);
    }

    @Test
    public void postSerializeTest() {
        elementPostProcessor.postSerialize(null, null, null);
    }
}
