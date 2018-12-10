/*
 * Copyright 2018 Ashis Raj
 */

package com.araj.cucumber.elasticsearch.pojos.collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.araj.cucumber.elasticsearch.constants.Status;
import com.araj.cucumber.elasticsearch.json.pojo.Element;
import com.araj.cucumber.elasticsearch.json.pojo.Report;
import com.araj.cucumber.elasticsearch.pojos.Feature;
import com.araj.cucumber.elasticsearch.pojos.ResultCount;

public class AllFeaturesCollection extends SummaryCollection {
    private Map<Feature, ResultCount> resultCounts;

    public AllFeaturesCollection(final List<Report> reports) {
        calculateFeatureResultCounts(reports);
    }

    /**
     * Get a map of {@link ResultCount} lists connected to features.
     *
     * @return a map of {@link ResultCount} lists with features as keys.
     */
    public Map<Feature, ResultCount> getFeatureResultCounts() {
        return resultCounts;
    }

    public Set<Feature> getFeatures() {
        return resultCounts.keySet();
    }

    public int getTotalNumberOfFeatures() {
        return resultCounts.size();
    }

    public int getTotalNumberOfPassedFeatures() {
        return getNumberOfResultsWithStatus(resultCounts.values(), Status.PASSED);
    }

    public int getTotalNumberOfFailedFeatures() {
        return getNumberOfResultsWithStatus(resultCounts.values(), Status.FAILED);
    }

    public int getTotalNumberOfSkippedFeatures() {
        return getNumberOfResultsWithStatus(resultCounts.values(), Status.SKIPPED);
    }

    public boolean hasFailedScenarios() {
        return getTotalNumberOfFailedFeatures() > 0;
    }

    public boolean hasPassedScenarios() {
        return getTotalNumberOfPassedFeatures() > 0;
    }

    public boolean hasSkippedScenarios() {
        return getTotalNumberOfSkippedFeatures() > 0;
    }
    /**
     * Calculate the numbers of failures, successes and skips per feature.
     *
     * @param reports The {@link Report} list.
     */
    private void calculateFeatureResultCounts(final List<Report> reports) {
        if (reports == null) return;
        resultCounts = new HashMap<>();
        for (Report report : reports) {
            Feature feature = new Feature(report.getName(), report.getFeatureIndex());
            ResultCount featureResultCount = this.resultCounts.getOrDefault(feature, new ResultCount());
            for (Element element : report.getElements()) {
                updateResultCount(featureResultCount, element.getStatus());
            }
            this.resultCounts.put(feature, featureResultCount);
        }
    }
}
