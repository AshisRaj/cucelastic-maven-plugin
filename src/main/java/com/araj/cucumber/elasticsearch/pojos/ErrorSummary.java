package com.araj.cucumber.elasticsearch.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorSummary {

  @JsonProperty("project_name")
  private String projectName;

  @JsonProperty("squad_id")
  private String squadId;

  @JsonProperty("scenario_name")
  private String scenarioName;

  @JsonProperty("error_name")
  private String errorName;

  @JsonProperty("error_message")
  private String errorMessage;

  @JsonProperty("error_step")
  private String errorStep;

  @JsonProperty("date")
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
    int index = errorMessage.indexOf(":");
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
}
