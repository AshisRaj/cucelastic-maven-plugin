/*
 * Copyright 2018 Ashis Raj
 */

package com.araj.cucumber.elasticsearch.pojos.collections;

import com.araj.cucumber.elasticsearch.json.pojo.Element;

public class ScenarioDetailsCollection {
    private final Element element;

    public ScenarioDetailsCollection(final Element element) {
        this.element = element;
    }

    public Element getElement() {
        return element;
    }
}
