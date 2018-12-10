package com.araj.cucumber.elasticsearch.pojos;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.araj.cucumber.elasticsearch.logging.CucElasticPluginLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;

@Singleton
public class ResultSender {

    private static final ObjectMapper OM = new ObjectMapper();
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json"; 
    private final CucElasticPluginLogger logger;   
    
    @Inject
    public ResultSender(final CucElasticPluginLogger logger) {
    	this.logger = logger;
    }
    
    public void sendTOElasticSearch(List<Object> summaries, String url) {
		for(Object summary : summaries) {
			try {
				logger.info(OM.writeValueAsString(summary));
				Unirest.post(url).header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
					.body(OM.writeValueAsString(summary)).asJson();
			} catch (Exception e) {
	    		logger.info(e.toString());
			}
		}
    }
}
