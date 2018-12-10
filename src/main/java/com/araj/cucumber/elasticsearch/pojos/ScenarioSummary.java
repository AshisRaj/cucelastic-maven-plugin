package com.araj.cucumber.elasticsearch.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScenarioSummary {

	@JsonProperty("feature_index")
	private int featureIndex;
	
	@JsonProperty("featureName")
	private String featureName;
	
    @JsonProperty("scenario_index")
    private int scenarioIndex;

    @JsonProperty("scenarioName")
    private String scenarioName;

    @JsonProperty("status")
    private String status;
    
    @JsonProperty("date")
    private String date;

    public void setFeatureIndex(int featureIndex) {
    	this.featureIndex = featureIndex;
    }
    public void setFeatureName(String featureName) {
    	this.featureName = featureName;
    }
    
    public void setScenarioIndex(int scenarioIndex) {
        this.scenarioIndex = scenarioIndex;
    }

    public void setscenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
}

