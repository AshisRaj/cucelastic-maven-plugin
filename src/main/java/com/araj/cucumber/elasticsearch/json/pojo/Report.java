/*
 * Copyright 2018 Ashis Raj
 */

package com.araj.cucumber.elasticsearch.json.pojo;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private int line;
    private List<Element> elements = new ArrayList<>();
    private String name = "";
    private String description = "";
    private String id = "";
    private String keyword = "";
    private String uri = "";

    private transient int featureIndex = -1;

    public int getLine() {
        return line;
    }

    public void setLine(final int line) {
        this.line = line;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(final List<Element> elements) {
        this.elements = elements;
    }

    public String getName() {
        return !name.isEmpty() ? name : "[Unnamed]";
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(final String keyword) {
        this.keyword = keyword;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public int getFeatureIndex() {
        return featureIndex;
    }

    public void setFeatureIndex(final int featureIndex) {
        this.featureIndex = featureIndex;
    }

    public long getTotalDuration() {
        long totalDurationMicroseconds = 0;
        for (Element element : elements) {
            totalDurationMicroseconds += element.getTotalDuration();
        }
        return totalDurationMicroseconds;
    }
}
