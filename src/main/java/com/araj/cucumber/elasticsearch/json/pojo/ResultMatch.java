/*
 * Copyright 2018 Ashis Raj
 */

package com.araj.cucumber.elasticsearch.json.pojo;


import java.util.ArrayList;
import java.util.List;

import com.araj.cucumber.elasticsearch.constants.Status;

public class ResultMatch {
    private Result result;
    private Match match;

    private List<String> output = new ArrayList<>();

    public Result getResult() {
        return result != null ? result : new Result();
    }

    public void setResult(final Result result) {
        this.result = result;
    }

    public Match getMatch() {
        return match != null ? match : new Match();
    }

    public void setMatch(final Match match) {
        this.match = match;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(final List<String> output) {
        this.output = output;
    }

    public String getGlueMethodName() {
        return getMatch().getLocation();
    }

    public List<Argument> getArguments() {
        return getMatch().getArguments();
    }

    public Status getStatus() {
        return Status.fromString(getResult().getStatus());
    }

    public String getStatusString() {
        return getStatus().getStatusAsString();
    }

    public boolean isFailed() {
        return getStatus() == Status.FAILED;
    }

    public boolean isPassed() {
        return getStatus() == Status.PASSED;
    }

    public boolean isSkipped() {
        return getConsolidatedStatus() == Status.SKIPPED;
    }

    public Status getConsolidatedStatus() {
        switch (getStatus()) {
            case SKIPPED:
            case PENDING:
            case UNDEFINED:
            case AMBIGUOUS:
                return Status.SKIPPED;
            default:
                return getStatus();
        }
    }

    public String getConsolidatedStatusString() {
        return getConsolidatedStatus().getStatusAsString();
    }
}
