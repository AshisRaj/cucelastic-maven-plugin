package com.araj.cucumber.elasticsearch.utils;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.araj.cucumber.elasticsearch.json.pojo.Element;
import com.araj.cucumber.elasticsearch.json.pojo.Report;
import com.araj.cucumber.elasticsearch.logging.CucElasticPluginLogger;
import com.araj.cucumber.elasticsearch.pojos.ResultSender;
import com.araj.cucumber.elasticsearch.pojos.collections.AllScenariosCollection;
import com.araj.cucumber.elasticsearch.properties.PropertyManager;

public class ReportGeneratorTest {

	private CucElasticPluginLogger cucElasticPluginLogger;
    private ResultSender resultSender;

    private CucElasticPluginReportGenerator reportGenerator;

    @Before
    public void setup() {
        cucElasticPluginLogger = mock(CucElasticPluginLogger.class);

        CucElasticPluginLogger logger = mock(CucElasticPluginLogger.class);
        PropertyManager propertyManager = new PropertyManager(logger);
    	resultSender = mock(ResultSender.class);
        reportGenerator = new CucElasticPluginReportGenerator(cucElasticPluginLogger, propertyManager, resultSender);
    }

    @Test
    public void loggingOperationsTest() throws Exception {
        AllScenariosCollection allScenariosPageCollection = new AllScenariosCollection();

        Report report1 = new Report();
        List<Element> elements1 = new ArrayList<>();
        Element element1 = new Element();
        elements1.add(element1);
        report1.setElements(elements1);

        Report report2 = new Report();
        List<Element> elements2 = new ArrayList<>();
        Element element2 = new Element();
        elements2.add(element2);
        report2.setElements(elements2);

        Report[] reportList = {report1, report2};
        allScenariosPageCollection.addReports(reportList);

        reportGenerator.generateAndSendReportDocumentsForElasticSearch(allScenariosPageCollection);

        verify(cucElasticPluginLogger, times(6)).info(anyString());
    }
}
