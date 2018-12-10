package com.araj.cucumber.elasticsearch.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeatureSummary {

    @JsonProperty("feature_index")
    private int featureIndex;

	@JsonProperty("feature_name")
    private String featureName;

    @JsonProperty("total_scenarios")
    private int totalScenarios;
    
    @JsonProperty("passed_scenarios")
    private int passedScenarios;

    @JsonProperty("failed_scenarios")
    private int failedScenarios;
    
    @JsonProperty("skipped_scenarios")
    private int skippedScenarios;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("date")
    private String date;
    
    public void setfeatureIndex(int featureIndex) {
        this.featureIndex = featureIndex;
    }

    public void setfeatureName(String featureName) {
        this.featureName = featureName;
    }

    public void setTotalScenarios(int totalScenarios) {
    	this.totalScenarios = totalScenarios;
    }
    
    public void setPassedScenarios(int passedScenarios) {
        this.passedScenarios = passedScenarios;
    }
    
    public void setFailedScenarios(int failedScenarios) {
    	this.failedScenarios = failedScenarios;
    }
    
    public void setSkippedScenarios(int skippedScenarios) {
    	this.skippedScenarios = skippedScenarios;
    }
    
    public void setStatus(String status) {
    	this.status = status;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
}

