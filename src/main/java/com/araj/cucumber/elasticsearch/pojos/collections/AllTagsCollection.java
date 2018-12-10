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
import com.araj.cucumber.elasticsearch.json.pojo.Tag;
import com.araj.cucumber.elasticsearch.pojos.ResultCount;

public class AllTagsCollection extends SummaryCollection {
    private Map<Tag, ResultCount> tagResultCounts;

    public AllTagsCollection(List<Report> reports) {
        calculateTagResultCounts(reports);
    }

    /**
     * Get a map of {@link ResultCount} lists connected to tag names.
     *
     * @return a map of {@link ResultCount} lists with tags as keys.
     */
    public Map<Tag, ResultCount> getTagResultCounts() {
        return tagResultCounts;
    }

    public Set<Tag> getTags() {
        return tagResultCounts.keySet();
    }

    public int getTotalNumberOfTags() {
        return tagResultCounts.size();
    }

    public int getTotalNumberOfPassedTags() {
        return getNumberOfResultsWithStatus(tagResultCounts.values(), Status.PASSED);
    }

    public int getTotalNumberOfFailedTags() {
        return getNumberOfResultsWithStatus(tagResultCounts.values(), Status.FAILED);
    }

    public int getTotalNumberOfSkippedTags() {
        return getNumberOfResultsWithStatus(tagResultCounts.values(), Status.SKIPPED);
    }

    /**
     * Calculate the numbers of failures, successes and skips per tag name.
     *
     * @param reports The {@link Report} list.
     */
    private void calculateTagResultCounts(final List<Report> reports) {
        if (reports == null) return;
        tagResultCounts = new HashMap<>();
        for (Report report : reports) {
            for (Element element : report.getElements()) {
                for (Tag tag : element.getTags()) {
                    ResultCount tagResultCount = tagResultCounts.getOrDefault(tag, new ResultCount());
                    updateResultCount(tagResultCount, element.getStatus());
                    tagResultCounts.put(tag, tagResultCount);
                }
            }
        }
    }
}
