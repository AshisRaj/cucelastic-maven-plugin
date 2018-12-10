package com.araj.cucumber.elasticsearch.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TagSummary {

	@JsonProperty("tag_name")
    private String tagName;

    @JsonProperty("total_scenarios")
    private int totalScenarios;
    
    @JsonProperty("passed_scenarios")
    private int passedScenarios;

    @JsonProperty("failed_scenarios")
    private int failedScenarios;
    
    @JsonProperty("skipped_scenarios")
    private int skippedScenarios;
    
    @JsonProperty("date")
    private String date;
    
    public void setTagName(String tagName) {
        this.tagName = tagName;
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
        
    public void setDate(String date) {
        this.date = date;
    }
}
