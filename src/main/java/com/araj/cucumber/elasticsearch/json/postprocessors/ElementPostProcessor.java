/*
 * Copyright 2018 Ashis Raj
 */

package com.araj.cucumber.elasticsearch.json.postprocessors;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.araj.cucumber.elasticsearch.filesystem.FileIO;
import com.araj.cucumber.elasticsearch.json.pojo.Element;
import com.araj.cucumber.elasticsearch.logging.CucElasticPluginLogger;
import com.araj.cucumber.elasticsearch.properties.PropertyManager;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import io.gsonfire.PostProcessor;

@Singleton
public class ElementPostProcessor implements PostProcessor<Element> {

    private final PropertyManager propertyManager;
    private final FileIO fileIO;
    private final CucElasticPluginLogger logger;

    private int scenarioIndex = 1;

    @Inject
    public ElementPostProcessor(
            final PropertyManager propertyManager,
            final FileIO fileIO,
            final CucElasticPluginLogger logger
    ) {
        this.propertyManager = propertyManager;
        this.fileIO = fileIO;
        this.logger = logger;
    }

    @Override
    public void postDeserialize(final Element element, final JsonElement jsonElement, final Gson gson) {
        addScenarioIndex(element);
    }

    /**
     * Add index to scenarios (used for link creation to the detail reports).
     *
     * @param element The current {@link Element}.
     */
    private void addScenarioIndex(final Element element) {
        // Filter out background scenarios
        if (!element.isScenario()) {
            return;
        }
        element.setScenarioIndex(scenarioIndex);
        scenarioIndex++;
    }

    @Override
    public void postSerialize(final JsonElement jsonElement, final Element element, final Gson gson) {
        // not used
    }
}
