package com.araj.cucumber.elasticsearch.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorSummary {

  @JsonProperty("projectName")
  private String projectName;

  @JsonProperty("squadId")
  private String squadId;

  @JsonProperty("squadName")
  private String squadName;

  @JsonProperty("scenarioName")
  private String scenarioName;

  @JsonProperty("errorType")
  private String errorName;

  @JsonProperty("errorMessage")
  private String errorMessage;

  @JsonProperty("errorStep")
  private String errorStep;

  @JsonProperty("time")
  private String date;

  @JsonProperty("env")
  private String env;

  public String getScenarioName() {
    return scenarioName;
  }

  public void setScenarioName(String scenarioName) {
    this.scenarioName = scenarioName;
  }

  public String getDate() {
    return date;
  }

  public String getEnv() {
    return env;
  }

  public void setEnv(String env) {
    this.env = env;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setErrorMessage(String errorMessage){
    this.errorMessage = errorMessage;
    int index1 = errorMessage.indexOf(":");
    int index2 = errorMessage.indexOf("\n");
    int index3 = errorMessage.indexOf("\r");
    int minIndex1 = Math.min(index1, index2);
    int index = Math.min(minIndex1, index3);
    this.errorName = errorMessage.substring(0, index);
  }

  public String getErrorMessage(){
    return this.errorMessage;
  }

  public String getErrorName(){
    return this.errorName;
  }

  public String getSquadId() { return squadId; }

  public void setSquadId(String squadId) { this.squadId = squadId; }

  public String getErrorStep() {
    return errorStep;
  }

  public void setErrorStep(String errorStep) {
    this.errorStep = errorStep;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getSquadName() {
    return squadName;
  }

  public void setSquadName(String squadName) {
    this.squadName = squadName;
  }
}
