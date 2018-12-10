/*
 * Copyright 2018 Ashis Raj
 */

package com.araj.cucumber.elasticsearch.json.postprocessors;

import com.araj.cucumber.elasticsearch.json.pojo.Element;
import com.araj.cucumber.elasticsearch.json.pojo.Report;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import io.gsonfire.PostProcessor;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ReportPostProcessor implements PostProcessor<Report> {

    private final List<String> featureUris;

    @Inject
    public ReportPostProcessor() {
        featureUris = new ArrayList<>();
    }

    @Override
    public void postDeserialize(final Report report, final JsonElement jsonElement, final Gson gson) {
        mergeBackgroundScenarios(report);
        addFeatureIndex(report);
    }

    private void addFeatureIndex(final Report report) {
        if (report == null) return;

        String featureName = report.getName();
        if (!featureUris.contains(featureName)) {
            featureUris.add(featureName);
        }
        report.setFeatureIndex(featureUris.indexOf(featureName));
    }

    private void mergeBackgroundScenarios(final Report report) {
        List<Element> cleanedUpElements = new ArrayList<>();
        Element currentBackgroundElement = null;

        for (Element element : report.getElements()) {
            if (element.getType().equalsIgnoreCase("background")) {
                currentBackgroundElement = element;
            } else {
                if (currentBackgroundElement != null) {
                    element.getSteps().addAll(0, currentBackgroundElement.getSteps());
                }
                cleanedUpElements.add(element);
            }
        }
        report.setElements(cleanedUpElements);
    }

    @Override
    public void postSerialize(final JsonElement jsonElement, final Report report, final Gson gson) {
        // not used
    }
}
