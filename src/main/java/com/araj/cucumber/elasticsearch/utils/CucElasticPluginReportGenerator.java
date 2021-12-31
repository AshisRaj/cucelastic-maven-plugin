/*
 * Copyright 2018 Ashis Raj
 */

package com.araj.cucumber.elasticsearch.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.araj.cucumber.elasticsearch.constants.Status;
import com.araj.cucumber.elasticsearch.enums.Category;
import com.araj.cucumber.elasticsearch.exceptions.CucElasticPluginException;
import com.araj.cucumber.elasticsearch.json.pojo.Element;
import com.araj.cucumber.elasticsearch.json.pojo.Report;
import com.araj.cucumber.elasticsearch.json.pojo.Step;
import com.araj.cucumber.elasticsearch.json.pojo.Tag;
import com.araj.cucumber.elasticsearch.logging.CucElasticPluginLogger;
import com.araj.cucumber.elasticsearch.pojos.*;
import com.araj.cucumber.elasticsearch.pojos.collections.AllFeaturesCollection;
import com.araj.cucumber.elasticsearch.pojos.collections.AllScenariosCollection;
import com.araj.cucumber.elasticsearch.pojos.collections.AllTagsCollection;
import com.araj.cucumber.elasticsearch.properties.PropertyManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

@Singleton
public class CucElasticPluginReportGenerator {

  private final CucElasticPluginLogger logger;
  private final PropertyManager propertyManager;
  private final ResultSender resultSender;
  private static final String CONTENT_TYPE = "Content-Type";
  private static final String CONTENT_TYPE_VALUE = "application/json";

  private Set<String> errorTypes = new HashSet<>();

  @Inject
  public CucElasticPluginReportGenerator(final CucElasticPluginLogger logger,
      final PropertyManager propertyManager,
      final ResultSender resultSender) {
    this.logger = logger;
    this.propertyManager = propertyManager;
    this.resultSender = resultSender;
  }

  public void generateAndSendReportDocumentsForElasticSearch(
      final AllScenariosCollection allScenariosCollection)
      throws CucElasticPluginException {

    if ("true".equalsIgnoreCase(propertyManager.getSendFeatureSummaryToElasticSearch())) {
      // Feature Summary docs
      List<Object> featureSummaries =
          generateFeatureDocuments(allScenariosCollection);
      String url = String.format("http://%s/%s/%s",
          propertyManager.getElasticSearchHostName(),
          propertyManager.getFeatureSummaryIndex(),
          propertyManager.getFeatureSummaryDocumentType());
      logger.info("-----------------------------------------------------------");
      logger.info("Sending the FeatureSummary documnets to elastic search");
      resultSender.sendTOElasticSearch(featureSummaries, url);
    } else {
      logger.info("Sending FeatureSummary documents to elastic search"
          + " is disabled\n\tCheck in your pom file for setting of"
          + " configuration parameter 'sendFeatureSummaryToElasticSearch'.");
    }
    if ("true".equalsIgnoreCase(propertyManager.getSendScenarioSummaryToElasticSearch())) {
      // Scenario Summary docs
      List<Object> scenarioSummaries =
          generateScenarioSummaryDocuments(allScenariosCollection);
      logger.info("-----------------------------------------------------------");
      logger.info("Sending the ScenarioSummary documents to elastic search");
      String url = String.format("http://%s/%s/%s",
          propertyManager.getElasticSearchHostName(),
          propertyManager.getScenarioSummaryIndex(),
          propertyManager.getScenarioSummaryDocumentType());
      resultSender.sendTOElasticSearch(scenarioSummaries, url);
    } else {
      logger.info("Sending ScenarioSummary documents to elastic search"
          + " is disabled\n\tCheck in your pom file for setting of"
          + " configuration parameter 'sendScenarioSummaryToElasticSearch'.");
    }
    if ("true".equalsIgnoreCase(propertyManager.getSendStepSummaryToElasticSearch())) {
      // Step Summary docs
      List<Object> stepSummaries =
          generateStepSummaryDocuments(allScenariosCollection);
      logger.info("-----------------------------------------------------------");
      logger.info("Sending the StepSummary documents to elastic search");
      String url = String.format("http://%s/%s/%s",
          propertyManager.getElasticSearchHostName(),
          propertyManager.getStepSummaryIndex(),
          propertyManager.getStepSummaryDocumentType());

      resultSender.sendTOElasticSearch(stepSummaries, url);
    } else {
      logger.info("Sending StepSummary documents to elastic search"
          + " is disabled\n\tCheck in your pom file for setting of"
          + " configuration parameter 'sendStepSummaryToElasticSearch'.");
    }
    if ("true".equalsIgnoreCase(propertyManager.getSendTagSummaryToElasticSearch())) {
      // Tag Summary docs
      List<Object> tagSummaries =
          generateTagSummaryDocuments(allScenariosCollection);
      logger.info("-----------------------------------------------------------");
      logger.info("Sending the TagSummary documents to elastic search");
      String url = String.format("http://%s/%s/%s",
          propertyManager.getElasticSearchHostName(),
          propertyManager.getTagSummaryIndex(),
          propertyManager.getTagSummaryDocumentType());

      resultSender.sendTOElasticSearch(tagSummaries, url);
    } else {
      logger.info("Sending TagSummary documents to elastic search"
          + " is disabled\n\tCheck in your pom file for setting of"
          + " configuration parameter 'sendTagSummaryToElasticSearch'.");
    }
    if ("true".equalsIgnoreCase(propertyManager.getSendErrorSummaryToElasticSearch())) {
      // Error Summary docs
      List<Object> errorSummaries =
          generateErrorSummaryDocuments(allScenariosCollection);
      logger.info("-----------------------------------------------------------");
      logger.info("Sending the Error Summary documents to elastic search");
      String url = String.format("http://%s/%s/%s",
          propertyManager.getElasticSearchHostName(),
          propertyManager.getErrorSummaryIndex(),
          propertyManager.getErrorSummaryDocumentType());

      resultSender.sendTOElasticSearch(errorSummaries, url);
    } else {
      logger.info("Sending Error Summary documents to elastic search"
          + " is disabled\n\tCheck in your pom file for setting of"
          + " configuration parameter 'sendErrorSummaryToElasticSearch'.");
    }
    if ("true".equalsIgnoreCase(propertyManager.getSendCategoryToElasticSearch())) {
      List<Object> category =
          generateCategoryDocuments(errorTypes);
      if ("true".equalsIgnoreCase(propertyManager.getSendCategoryToElasticSearch())) {
        logger.info("-----------------------------------------------------------");
        logger.info("Sending the Category documents to elastic search");
        String url = String.format("http://%s/%s/%s",
            propertyManager.getElasticSearchHostName(),
            propertyManager.getCategoryIndex(),
            propertyManager.getCategoryDocumentType());
        resultSender.sendTOElasticSearch(category, url);
      }

    } else {
      logger.info("Sending Category Summary documents to elastic search"
          + " is disabled\n\tCheck in your pom file for setting of"
          + " configuration parameter 'sendCategoryToElasticSearch'.");
    }
  }


  /**
   * Generate Documents for features.
   *
   * @param allScenariosCollection The {@link AllScenariosCollection}.
   * @return a List of Feature documents
   * @throws CucElasticPluginException The {@link CucElasticPluginException}.
   */
  private List<Object> generateFeatureDocuments(
      final AllScenariosCollection allScenariosCollection)
      throws CucElasticPluginException {
    List<Object> featureSummaries = new ArrayList<>();

    // Feature summary collection
    AllFeaturesCollection allFeaturesCollection =
        new AllFeaturesCollection(allScenariosCollection.getReports());

    // Feature summary documents
    for (Map.Entry<Feature, ResultCount> entry :
        allFeaturesCollection.getFeatureResultCounts().entrySet()) {
      FeatureSummary featureSummary = new FeatureSummary();
      featureSummary.setfeatureIndex(entry.getKey().getIndex());
      featureSummary.setfeatureName(entry.getKey().getName());
      featureSummary.setTotalScenarios(entry.getValue().getTotal());
      featureSummary.setPassedScenarios(entry.getValue().getPassed());
      featureSummary.setFailedScenarios(entry.getValue().getFailed());
      featureSummary.setSkippedScenarios(entry.getValue().getSkipped());
      featureSummary.setStatus(
          entry.getValue().getFailed() > 0 ?
              Status.FAILED.getStatusAsString() :
              Status.PASSED.getStatusAsString());
      featureSummary.setDate(LocalDateTime.now().toString());
      featureSummaries.add(featureSummary);
    }
    return featureSummaries;
  }

  /**
   * Generate Documents for scenarios.
   *
   * @param allScenariosCollection The {@link AllScenariosCollection}.
   * @return a List of Scenario documents
   * @throws CucElasticPluginException The {@link CucElasticPluginException}.
   */
  private List<Object> generateScenarioSummaryDocuments(
      final AllScenariosCollection allScenariosCollection)
      throws CucElasticPluginException {
    List<Object> scenarioSummaries = new ArrayList<>();
    for (Report report : allScenariosCollection.getReports()) {
      for (Element element : report.getElements()) {
        ScenarioSummary scenarioSummary = new ScenarioSummary();
        scenarioSummary.setFeatureIndex(report.getFeatureIndex());
        scenarioSummary.setFeatureName(report.getName());
        scenarioSummary.setScenarioIndex(element.getScenarioIndex());
        scenarioSummary.setscenarioName(element.getName());
        scenarioSummary.setStatus(element.getStatus().getStatusAsString());
        scenarioSummary.setDate(LocalDateTime.now().toString());
        scenarioSummaries.add(scenarioSummary);
      }
    }
    return scenarioSummaries;
  }

  /**
   * Generate Documents from steps
   *
   * @param allScenariosCollection The {@link AllScenariosCollection}.
   * @return a List of Steps documents
   * @throws CucElasticPluginException The {@link CucElasticPluginException}.
   */
  private List<Object> generateStepSummaryDocuments(
      final AllScenariosCollection allScenariosCollection)
      throws CucElasticPluginException {
    List<Object> stepSummaries = new ArrayList<>();
    for (Report report : allScenariosCollection.getReports()) {
      for (Element element : report.getElements()) {
        StepSummary stepSummary = new StepSummary();
        stepSummary.setScenarioIndex(element.getScenarioIndex());
        stepSummary.setscenarioName(element.getName());
        stepSummary.setTotalSteps(element.getTotalNumberOfSteps());
        stepSummary.setPassedSteps(element.getTotalNumberOfPassedSteps());
        stepSummary.setFailedSteps(element.getTotalNumberOfFailedSteps());
        stepSummary.setSkippedSteps(element.getTotalNumberOfSkippedSteps());
        stepSummaries.add(stepSummary);
      }
    }
    return stepSummaries;
  }

  /**
   * Generate Documents for tags.
   *
   * @param allScenariosCollection The {@link AllScenariosCollection}.
   * @throws CucElasticPluginException The {@link CucElasticPluginException}.
   */
  private List<Object> generateTagSummaryDocuments(
      final AllScenariosCollection allScenariosCollection)
      throws CucElasticPluginException {

    List<Object> tagSummaries = new ArrayList<>();

    AllTagsCollection allTagsCollection =
        new AllTagsCollection(allScenariosCollection.getReports());

    // Tag summary
    for (Map.Entry<Tag, ResultCount> entry :
        allTagsCollection.getTagResultCounts().entrySet()) {
      TagSummary tagSummary = new TagSummary();
      tagSummary.setTagName(entry.getKey().getName());
      tagSummary.setPassedScenarios(entry.getValue().getPassed());
      tagSummary.setFailedScenarios(entry.getValue().getFailed());
      tagSummary.setSkippedScenarios(entry.getValue().getSkipped());
      tagSummary.setTotalScenarios(entry.getValue().getTotal());
      tagSummary.setDate(LocalDateTime.now().toString());
      tagSummaries.add(tagSummary);
    }
    return tagSummaries;
  }

  /**
   * Generate Documents for errors.
   *
   * @param allScenariosCollection The {@link AllScenariosCollection}.
   * @throws CucElasticPluginException The {@link CucElasticPluginException}.
   */
  private List<Object> generateErrorSummaryDocuments(
      final AllScenariosCollection allScenariosCollection
  ) throws CucElasticPluginException {
    List<Object> errorSummaries = new ArrayList<>();

    for (Report report : allScenariosCollection.getReports()) {
      for (Element element : report.getElements()) {
        ErrorSummary errorSummary = new ErrorSummary();
        for (Step step :
            element.getSteps()) {
          if (step.getResult().getErrorMessage().length() != 0) {
            errorSummary.setErrorMessage(step.getResult().getErrorMessage());
            errorSummary.setErrorStep(step.getName());
            errorTypes.add(errorSummary.getErrorName());
          }
        }
        errorSummary.setProjectName(propertyManager.getProjectName());
        errorSummary.setScenarioName(element.getName());
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        errorSummary.setDate(now.toString());
        if (errorSummary.getErrorMessage() != null && errorSummary.getErrorStep() != null) {
          errorSummaries.add(errorSummary);
        }
      }
    }
    return errorSummaries;
  }

  /**
   * Generate Documents for category.
   */
  private List<Object> generateCategoryDocuments(Set<String> errorTypes)
      throws CucElasticPluginException {
    List<Object> categories = new ArrayList<>();

    String url = String.format("http://%s/%s/%s",
        propertyManager.getElasticSearchHostName(),
        propertyManager.getCategoryIndex(),
        "_search");
    try {
      for (String errorType : errorTypes) {
        if (resultSender.checkCategoryTOElasticSearch(errorType, url)) {
          List<String> data = new ArrayList<>();
          for (Category category : Category.values()) {
            for (String contain : category.getTypeError().split(",")) {
              if (errorType.contains(contain)) {
                data.add(category.getCategory());
              }
            }
          }
          HashMap<String, Object> category = new HashMap<>();
          category.put("errorType", errorType);
          category.put("category", data);
          categories.add(category);
        }
      }
    } catch (Exception e) {
      logger.info(e.getMessage());
    }
    return categories;
  }
}

