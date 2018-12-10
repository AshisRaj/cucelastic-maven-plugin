/*
 * Copyright 2018 Ashis Raj
 */

package com.araj.cucumber.elasticsearch.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Enum to manage all states for steps and scenarios.
 */
public enum Status {
    PASSED("passed"),
    FAILED("failed"),
    SKIPPED("skipped"),
    PENDING("pending"),
    UNDEFINED("undefined"),
    AMBIGUOUS("ambiguous");

    public static final List<Status> BASIC_STATES = Arrays.asList(Status.PASSED, Status.FAILED, Status.SKIPPED);

    private final String status;

    Status(final String statusString) {
        this.status = statusString;
    }

    public static Status fromString(String status) {
        return valueOf(status.toUpperCase());
    }

    public String getStatusAsString() {
        return status;
    }
}
