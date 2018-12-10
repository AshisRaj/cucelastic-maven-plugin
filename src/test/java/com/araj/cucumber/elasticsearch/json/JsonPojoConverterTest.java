package com.araj.cucumber.elasticsearch.json;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.araj.cucumber.elasticsearch.constants.Status;
import com.araj.cucumber.elasticsearch.exceptions.CucElasticPluginException;
import com.araj.cucumber.elasticsearch.json.pojo.Element;
import com.araj.cucumber.elasticsearch.json.pojo.Report;
import com.araj.cucumber.elasticsearch.json.postprocessors.ElementPostProcessor;
import com.araj.cucumber.elasticsearch.json.postprocessors.ReportPostProcessor;

public class JsonPojoConverterTest {
    private JsonPojoConverter pojoConverter;

    @Before
    public void setup() {
        ElementPostProcessor elementPostProcessor = mock(ElementPostProcessor.class);
        ReportPostProcessor reportPostProcessor = mock(ReportPostProcessor.class);
        pojoConverter = new JsonPojoConverter(reportPostProcessor, elementPostProcessor);
    }

    @Test
    public void convertEmptyJsonToReportPojosTest() throws CucElasticPluginException {
        String json = "";
        Report[] reports = pojoConverter.convertJsonToReportPojos(json);
        assertThat(reports).isNull();
    }

    @Test
    public void convertJsonToReportPojosTest() throws CucElasticPluginException {
        String json = "[\n" +
                "  {\n" +
                "    \"line\": 1,\n" +
                "    \"elements\": [\n" +
                "      {\n" +
                "        \"before\": [\n" +
                "          {\n" +
                "            \"result\": {\n" +
                "              \"duration\": 5554929,\n" +
                "              \"status\": \"passed\"\n" +
                "            },\n" +
                "            \"match\": {\n" +
                "              \"location\": \"BeforeAfterScenario.before(Scenario)\"\n" +
                "            }\n" +
                "          }\n" +
                "        ],\n" +
                "        \"line\": 5,\n" +
                "        \"name\": \"Test feature\",\n" +
                "        \"description\": \"\",\n" +
                "        \"id\": \"test;id\",\n" +
                "        \"after\": [\n" +
                "          {\n" +
                "            \"result\": {\n" +
                "              \"duration\": 153270,\n" +
                "              \"status\": \"passed\"\n" +
                "            },\n" +
                "            \"match\": {\n" +
                "              \"location\": \"BeforeAfterScenario.after(Scenario)\"\n" +
                "            }\n" +
                "          }\n" +
                "        ],\n" +
                "        \"type\": \"scenario\",\n" +
                "        \"keyword\": \"Scenario\",\n" +
                "        \"steps\": [\n" +
                "          {\n" +
                "            \"result\": {\n" +
                "              \"duration\": 12453061125,\n" +
                "              \"status\": \"passed\"\n" +
                "            },\n" +
                "            \"line\": 7,\n" +
                "            \"name\": \"the start page is opened\",\n" +
                "            \"match\": {\n" +
                "              \"location\": \"PageSteps.theStartPageIsOpened()\"\n" +
                "            },\n" +
                "            \"keyword\": \"Given \"\n" +
                "          },\n" +
                "          {\n" +
                "            \"result\": {\n" +
                "              \"duration\": 292465492,\n" +
                "              \"status\": \"passed\"\n" +
                "            },\n" +
                "            \"line\": 8,\n" +
                "            \"name\": \"I see something\",\n" +
                "            \"match\": {\n" +
                "              \"location\": \"SomeSteps.iSeeSomething()\"\n" +
                "            },\n" +
                "            \"keyword\": \"Then \"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"tags\": [\n" +
                "          {\n" +
                "            \"line\": 3,\n" +
                "            \"name\": \"@sometag\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"line\": 4,\n" +
                "            \"name\": \"@someothertag\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ],\n" +
                "    \"name\": \"Test\",\n" +
                "    \"description\": \"\",\n" +
                "    \"id\": \"test\",\n" +
                "    \"keyword\": \"Feature\",\n" +
                "    \"uri\": \"parallel/features/Test.feature\"\n" +
                "  }\n" +
                "]";
        Report[] reports = pojoConverter.convertJsonToReportPojos(json);
        assertThat(1).isEqualTo(reports.length);
        Report report = reports[0];
        assertThat("Test").isEqualTo(report.getName());
        assertThat("test").isEqualTo(report.getId());
        assertThat(-1).isEqualTo(report.getFeatureIndex());
        assertThat(12751234816L).isEqualTo(report.getTotalDuration());
        assertThat("").isEqualTo(report.getDescription());
        assertThat(1).isEqualTo(report.getLine());
        assertThat("parallel/features/Test.feature").isEqualTo(report.getUri());
        assertThat(1).isEqualTo(report.getElements().size());
        Element element = report.getElements().get(0);
        assertThat(Status.PASSED).isEqualTo(element.getStatus());
        assertThat(2).isEqualTo(element.getSteps().size());
        assertThat(1).isEqualTo(element.getBefore().size());
        assertThat(1).isEqualTo(element.getAfter().size());
        assertThat(0).isEqualTo(element.getScenarioIndex());
        assertThat(12751234816L).isEqualTo(element.getTotalDuration());
        assertThat(2).isEqualTo(element.getTotalNumberOfPassedSteps());
        assertThat(0).isEqualTo(element.getTotalNumberOfSkippedSteps());
        assertThat(0).isEqualTo(element.getTotalNumberOfFailedSteps());
        assertThat(4).isEqualTo(element.getAllResultMatches().size());
    }

    @Test(expected = CucElasticPluginException.class)
    public void convertJsonToReportPojosInvalidTest() throws CucElasticPluginException {
        pojoConverter.convertJsonToReportPojos("@#$%^&");
    }
}
