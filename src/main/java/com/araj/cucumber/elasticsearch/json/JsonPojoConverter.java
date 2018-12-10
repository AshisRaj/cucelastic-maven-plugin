package com.araj.cucumber.elasticsearch.json;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.araj.cucumber.elasticsearch.exceptions.CucElasticPluginException;
import com.araj.cucumber.elasticsearch.json.pojo.Element;
import com.araj.cucumber.elasticsearch.json.pojo.Report;
import com.araj.cucumber.elasticsearch.json.postprocessors.ElementPostProcessor;
import com.araj.cucumber.elasticsearch.json.postprocessors.ReportPostProcessor;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import io.gsonfire.GsonFireBuilder;

@Singleton
public class JsonPojoConverter {

    private final Gson gsonParserWithProcessors;

    @Inject
    public JsonPojoConverter(final ReportPostProcessor reportPostProcessor, final ElementPostProcessor elementPostProcessor) {
        GsonFireBuilder builder = new GsonFireBuilder()
                .registerPostProcessor(Report.class, reportPostProcessor)
                .registerPostProcessor(Element.class, elementPostProcessor);
        gsonParserWithProcessors = builder.createGson();
    }

    public Report[] convertJsonToReportPojos(final String json) throws CucElasticPluginException {
        Report[] reports;
        try {
            reports = gsonParserWithProcessors.fromJson(json, Report[].class);
        } catch (JsonParseException e) {
            throw new CucElasticPluginException(e.getMessage());
        }
        return reports;
    }
}

