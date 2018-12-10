package com.araj.cucumber.elasticsearch.json.postprocessors;

import com.araj.cucumber.elasticsearch.json.pojo.Element;
import com.araj.cucumber.elasticsearch.json.pojo.Report;
import com.araj.cucumber.elasticsearch.json.pojo.Step;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ReportPostProcessorTest {
    private ReportPostProcessor reportPostProcessor;

    @Before
    public void setup() {
        reportPostProcessor = new ReportPostProcessor();
    }

    @Test
    public void postDeserializeTest() {
        Report report = new Report();

        List<Element> elements = new ArrayList<>();

        Element backgroundElement = new Element();
        List<Step> backgroundSteps = new ArrayList<>();

        Step backgroundStep1 = new Step();
        backgroundStep1.setName("background step 1");
        backgroundSteps.add(backgroundStep1);

        Step backgroundStep2 = new Step();
        backgroundStep2.setName("background step 2");
        backgroundSteps.add(backgroundStep2);

        backgroundElement.setSteps(backgroundSteps);
        backgroundElement.setType("background");
        elements.add(backgroundElement);

        Element element = new Element();
        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        step.setName("element step 1");
        steps.add(step);
        Step step2 = new Step();
        step2.setName("element step 2");
        steps.add(step2);
        element.setSteps(steps);
        elements.add(element);

        report.setElements(elements);

        assertThat(2).isEqualTo(report.getElements().size());
        reportPostProcessor.postDeserialize(report, null, null);
        assertThat(1).isEqualTo(report.getElements().size());
        List<Step> firstElementSteps = report.getElements().get(0).getSteps();
        assertThat(4).isEqualTo(firstElementSteps.size());
        assertThat("background step 1").isEqualTo(firstElementSteps.get(0).getName());
        assertThat("background step 2").isEqualTo(firstElementSteps.get(1).getName());
        assertThat("element step 1").isEqualTo(firstElementSteps.get(2).getName());
        assertThat("element step 2").isEqualTo(firstElementSteps.get(3).getName());
    }

    @Test
    public void postSerializeTest() {
        reportPostProcessor.postSerialize(null, null, null);
    }
}
