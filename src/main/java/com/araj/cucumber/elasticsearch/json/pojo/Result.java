/*
 * Copyright 2018 Ashis Raj
 */

package com.araj.cucumber.elasticsearch.json.pojo;

import com.araj.cucumber.elasticsearch.constants.Status;
import com.araj.cucumber.elasticsearch.utils.CucElasticPluginUtils;
import com.google.gson.annotations.SerializedName;

public class Result {

    private long duration = 0;
    private String status = Status.UNDEFINED.toString();

    @SerializedName("error_message")
    private String errorMessage = "";

    public long getDuration() {
        return duration;
    }

    public void setDuration(final long duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public boolean hasErrorMessage() {
        return errorMessage != null && !errorMessage.trim().isEmpty();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getDurationInMilliseconds() {
        return CucElasticPluginUtils.convertMicrosecondsToMilliseconds(duration);
    }

    public String returnDurationString() {
        return CucElasticPluginUtils.convertMicrosecondsToTimeString(duration);
    }
}
