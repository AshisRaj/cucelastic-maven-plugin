package com.araj.cucumber.elasticsearch.properties;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.araj.cucumber.elasticsearch.exceptions.CucElasticPluginException;
import com.araj.cucumber.elasticsearch.exceptions.properties.WrongOrMissingPropertyException;
import com.araj.cucumber.elasticsearch.logging.CucElasticPluginLogger;

@Singleton
public class PropertyManager {

    private final CucElasticPluginLogger logger;

    private String sourceJsonReportDirectory;

    private String elasticSearchHostName;

    private String featureSummaryIndex;

	private String scenarioSummaryIndex;

    private String stepSummaryIndex;

    private String tagSummaryIndex;

    private String errorSummaryIndex;

    private String categoryIndex;

    private String featureSummaryDocumentType;

    private String scenarioSummaryDocumentType;

    private String stepSummaryDocumentType;

    private String tagSummaryDocumentType;

    private String errorSummaryDocumentType;

    private String categoryDocumentType;

    private String sendFeatureSummaryToElasticSearch;

    private String sendScenarioSummaryToElasticSearch;

    private String sendStepSummaryToElasticSearch;

    private String sendTagSummaryToElasticSearch;

    private String sendErrorSummaryToElasticSearch;

    private String sendCategoryToElasticSearch;

    private String projectName;

    private String squadId;

    private String env;

    @Inject
    public PropertyManager(final CucElasticPluginLogger logger) {
        this.logger = logger;
    }

    public String getSourceJsonReportDirectory() {
        return sourceJsonReportDirectory;
    }

    public void setSourceJsonReportDirectory(final String reportDirectory) {
        this.sourceJsonReportDirectory = reportDirectory;
    }

    public String getElasticSearchHostName() {
		return elasticSearchHostName;
	}

	public void setElasticSearchHostName(String elasticSearchHostName) {
		this.elasticSearchHostName = elasticSearchHostName;
	}

	public String getFeatureSummaryIndex() {
		return featureSummaryIndex;
	}

	public void setFeatureSummaryIndex(String featureSummaryIndex) {
		this.featureSummaryIndex = featureSummaryIndex;
	}

	public String getScenarioSummaryIndex() {
		return scenarioSummaryIndex;
	}

	public void setScenarioSummaryIndex(String scenarioSummaryIndex) {
		this.scenarioSummaryIndex = scenarioSummaryIndex;
	}

	public String getStepSummaryIndex() {
		return stepSummaryIndex;
	}

	public void setStepSummaryIndex(String stepSummaryIndex) {
		this.stepSummaryIndex = stepSummaryIndex;
	}

	public String getTagSummaryIndex() {
		return tagSummaryIndex;
	}

	public void setTagSummaryIndex(String tagSummaryIndex) {
		this.tagSummaryIndex = tagSummaryIndex;
	}

	public String getFeatureSummaryDocumentType() {
		return featureSummaryDocumentType;
	}

	public void setFeatureSummaryDocumentType(String featureSummaryDocumentType) {
		this.featureSummaryDocumentType = featureSummaryDocumentType;
	}

	public String getScenarioSummaryDocumentType() {
		return scenarioSummaryDocumentType;
	}

	public void setScenarioSummaryDocumentType(String scenarioSummaryDocumentType) {
		this.scenarioSummaryDocumentType = scenarioSummaryDocumentType;
	}

	public String getStepSummaryDocumentType() {
		return stepSummaryDocumentType;
	}

	public void setStepSummaryDocumentType(String stepSummaryDocumentType) {
		this.stepSummaryDocumentType = stepSummaryDocumentType;
	}

    public String getTagSummaryDocumentType() {
		return tagSummaryDocumentType;
	}

	public void setTagSummaryDocumentType(String tagSummaryDocumentType) {
		this.tagSummaryDocumentType = tagSummaryDocumentType;
	}

	public String getSendFeatureSummaryToElasticSearch() {
		return sendFeatureSummaryToElasticSearch;
	}

	public void setSendFeatureSummaryToElasticSearch(String sendFeatureSummaryToElasticSearch) {
		this.sendFeatureSummaryToElasticSearch = sendFeatureSummaryToElasticSearch;
	}

	public String getSendScenarioSummaryToElasticSearch() {
		return sendScenarioSummaryToElasticSearch;
	}

	public void setSendScenarioSummaryToElasticSearch(String sendScenarioSummaryToElasticSearch) {
		this.sendScenarioSummaryToElasticSearch = sendScenarioSummaryToElasticSearch;
	}

	public String getSendStepSummaryToElasticSearch() {
		return sendStepSummaryToElasticSearch;
	}

	public void setSendStepSummaryToElasticSearch(String sendStepSummaryToElasticSearch) {
		this.sendStepSummaryToElasticSearch = sendStepSummaryToElasticSearch;
	}

	public String getSendTagSummaryToElasticSearch() {
		return sendTagSummaryToElasticSearch;
	}

	public void setSendTagSummaryToElasticSearch(String sendTagSummaryToElasticSearch) {
		this.sendTagSummaryToElasticSearch = sendTagSummaryToElasticSearch;
	}

  public String getErrorSummaryIndex() {
    return errorSummaryIndex;
  }

  public void setErrorSummaryIndex(String errorSummaryIndex) {
    this.errorSummaryIndex = errorSummaryIndex;
  }

  public String getErrorSummaryDocumentType() {
    return errorSummaryDocumentType;
  }

  public void setErrorSummaryDocumentType(String errorSummaryDocumentType) {
    this.errorSummaryDocumentType = errorSummaryDocumentType;
  }

  public String getSendErrorSummaryToElasticSearch() {
    return sendErrorSummaryToElasticSearch;
  }

  public void setSendErrorSummaryToElasticSearch(String sendErrorSummaryToElasticSearch) {
    this.sendErrorSummaryToElasticSearch = sendErrorSummaryToElasticSearch;
  }

  public String getSquadId() { return squadId; }

  public void setSquadId(String squadId) { this.squadId = squadId; }

  public String getProjectName() { return projectName; }

  public void setProjectName(String projectName) { this.projectName = projectName; }

  public String getEnv() {
    return env;
  }

  public void setEnv(String env) {
    this.env = env;
  }

  public String getCategoryIndex() {
    return categoryIndex;
  }

  public void setCategoryIndex(String categoryIndex) {
    this.categoryIndex = categoryIndex;
  }

  public String getCategoryDocumentType() {
    return categoryDocumentType;
  }

  public void setCategoryDocumentType(String categoryDocumentType) {
    this.categoryDocumentType = categoryDocumentType;
  }

  public String getSendCategoryToElasticSearch() {
    return sendCategoryToElasticSearch;
  }

  public void setSendCategoryToElasticSearch(String sendCategoryToElasticSearch) {
    this.sendCategoryToElasticSearch = sendCategoryToElasticSearch;
  }

  /**
     * Checks the pom settings for the plugin.
     *
     * @throws CucElasticPluginException Thrown when a required setting
     *                                   is not specified in the pom.
     */
    public void validateSettings() throws CucElasticPluginException {
        String missingProperty = null;

        if (sourceJsonReportDirectory == null || sourceJsonReportDirectory.equals("")) {
            missingProperty = "sourceJsonReportDirectory";
        } else if (elasticSearchHostName == null || elasticSearchHostName.equals("")) {
            missingProperty = "elasticSearchHostName";
        } else if (featureSummaryIndex == null || featureSummaryIndex.equals("")) {
            missingProperty = "featureSummaryIndex";
        } else if (scenarioSummaryIndex == null || scenarioSummaryIndex.equals("")) {
            missingProperty = "scenarioSummaryIndex";
        } else if (stepSummaryIndex == null || stepSummaryIndex.equals("")) {
            missingProperty = "stepsSummaryIndex";
        } else if (tagSummaryIndex == null || tagSummaryIndex.equals("")) {
            missingProperty = "tagsSummaryIndex";
        } else if (errorSummaryIndex == null || errorSummaryIndex.equals("")) {
          missingProperty = "errorSummaryIndex";
        } else if (categoryIndex == null || categoryIndex.equals("")) {
          missingProperty = "categoryIndex";
        } else if (featureSummaryDocumentType == null || featureSummaryDocumentType.equals("")) {
            missingProperty = "featureSummaryDocumentType";
        } else if (scenarioSummaryDocumentType == null || scenarioSummaryDocumentType.equals("")) {
            missingProperty = "scenarioSummaryDocumentType";
        } else if (stepSummaryDocumentType == null || stepSummaryDocumentType.equals("")) {
            missingProperty = "stepSummaryDocumentType";
        } else if (tagSummaryDocumentType == null || tagSummaryDocumentType.equals("")) {
            missingProperty = "tagSummaryDocumentType";
        } else if (errorSummaryDocumentType == null || errorSummaryDocumentType.equals("")) {
          missingProperty = "errorSummaryDocumentType";
        } else if (categoryDocumentType == null || categoryDocumentType.equals("")) {
          missingProperty = "categoryDocumentType";
        } else if (sendFeatureSummaryToElasticSearch == null || sendFeatureSummaryToElasticSearch.equals("")) {
            missingProperty = "sendFeatureSummaryToElasticSearch";
        } else if (sendScenarioSummaryToElasticSearch == null || sendScenarioSummaryToElasticSearch.equals("")) {
            missingProperty = "sendScenarioSummaryToElasticSearch";
        } else if (sendStepSummaryToElasticSearch == null || sendStepSummaryToElasticSearch.equals("")) {
            missingProperty = "sendStepSummaryToElasticSearch";
        } else if (sendTagSummaryToElasticSearch == null || sendTagSummaryToElasticSearch.equals("")) {
            missingProperty = "sendTagSummaryToElasticSearch";
        } else if (sendErrorSummaryToElasticSearch == null || sendErrorSummaryToElasticSearch.equals("")) {
          missingProperty = "sendErrorSummaryToElasticSearch";
        } else if (sendCategoryToElasticSearch == null || sendCategoryToElasticSearch.equals("")) {
          missingProperty = "sendCategoryToElasticSearch";
        } else if (squadId == null || squadId.equals("")) {
          missingProperty = "squadId";
        } else if (projectName == null || projectName.equals("")) {
          missingProperty = "projectName";
        } else if (env == null || env.equals("")) {
          missingProperty = "env";
        }

        if (missingProperty != null) {
            throw new WrongOrMissingPropertyException(missingProperty);
        }
    }

    public void logProperties() {
        logger.info("- source JSON report directory    : " + sourceJsonReportDirectory);
        logger.info("- elastic Search Host Name    : " + elasticSearchHostName);
        logger.info("- feature Summary Index Name    : " + featureSummaryIndex);
        logger.info("- scenario Summary Index Name    : " + scenarioSummaryIndex);
        logger.info("- step Summary Index Name    : " + stepSummaryIndex);
        logger.info("- tag Summary Index Name    : " + tagSummaryIndex);
        logger.info("- error Summary Index Name    : " + errorSummaryIndex);
        logger.info("- category Index Name    : " + categoryIndex);
        logger.info("- feature Summary Document Type Name    : " + featureSummaryDocumentType);
        logger.info("- scenario Summary Document Type Name    : " + scenarioSummaryDocumentType);
        logger.info("- step Summary Document Type Name    : " + stepSummaryDocumentType);
        logger.info("- tag Summary Document Type Name    : " + tagSummaryDocumentType);
        logger.info("- error Summary Document Type Name    : " + errorSummaryDocumentType);
        logger.info("- category Document Type Name    : " + categoryDocumentType);
        logger.info("- send Feature Summary To Elastic Search    : " + sendFeatureSummaryToElasticSearch);
        logger.info("- send Scenario Summary To Elastic Search    : " + sendScenarioSummaryToElasticSearch);
        logger.info("- send Step Summary To Elastic Search    : " + sendStepSummaryToElasticSearch);
        logger.info("- send Tag Summary To Elastic Search    : " + sendTagSummaryToElasticSearch);
        logger.info("- send Error Summary To Elastic Search    : " + sendErrorSummaryToElasticSearch);
        logger.info("- send Category To Elastic Search    : " + sendCategoryToElasticSearch);
        logger.info("- send Squad ID   : " + squadId);
        logger.info("- send Project Name    : " + projectName);
        logger.info("- send Environment    : " + env);

        logger.info("------------------------------------------------------------------------");
    }
}
