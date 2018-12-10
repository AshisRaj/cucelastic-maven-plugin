/*
 * Copyright Ashis Raj
 */

package com.araj.cucumber.elasticsearch.pojos.collections;

import java.util.Collection;

import com.araj.cucumber.elasticsearch.constants.Status;
import com.araj.cucumber.elasticsearch.pojos.ResultCount;

class SummaryCollection {
    
    void updateResultCount(ResultCount resultCount, final Status status) {
        switch (status) {
            case PASSED:
                resultCount.addPassed(1);
                break;
            case FAILED:
                resultCount.addFailed(1);
                break;
            case SKIPPED:
            case PENDING:
            case UNDEFINED:
            case AMBIGUOUS:
                resultCount.addSkipped(1);
                break;
        }
    }

    int getNumberOfResultsWithStatus(final Collection<ResultCount> resultCounts, final Status status) {
        int sum = 0;
        for (ResultCount resultCount : resultCounts) {
            switch (status) {
                case PASSED:
                    sum += resultCount.getPassed();
                    break;
                case FAILED:
                    sum += resultCount.getFailed();
                    break;
                case SKIPPED:
                case PENDING:
                case UNDEFINED:
                case AMBIGUOUS:
                    sum += resultCount.getSkipped();
                    break;
            }
        }
        return sum;
    }
}
