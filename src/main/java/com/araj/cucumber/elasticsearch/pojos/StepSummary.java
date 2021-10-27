package com.araj.cucumber.elasticsearch.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StepSummary {

    @JsonProperty("scenario_index")
    private int scenarioIndex;

    @JsonProperty("scenario_name")
    private String scenarioName;

    @JsonProperty("total_steps")
    private int totalSteps;
    
    @JsonProperty("passed_steps")
    private int passedSteps;

    @JsonProperty("failed_steps")
    private int failedSteps;
    
    @JsonProperty("skipped_steps")
    private int skippedSteps;
    
    public void setScenarioIndex(int scenarioIndex) {
        this.scenarioIndex = scenarioIndex;
    }

    public void setscenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public void setTotalSteps(int totalSteps) {
    	this.totalSteps = totalSteps;
    }
    
    public void setPassedSteps(int passedSteps) {
        this.passedSteps = passedSteps;
    }
    
    public void setFailedSteps(int failedSteps) {
    	this.failedSteps = failedSteps;
    }
    
    public void setSkippedSteps(int skippedSteps) {
    	this.skippedSteps = skippedSteps;
    }
}

