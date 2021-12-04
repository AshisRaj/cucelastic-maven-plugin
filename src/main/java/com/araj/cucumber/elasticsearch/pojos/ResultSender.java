package com.araj.cucumber.elasticsearch.pojos;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.araj.cucumber.elasticsearch.logging.CucElasticPluginLogger;
import com.araj.cucumber.elasticsearch.pojos.category.CategoryBodySearch;
import com.araj.cucumber.elasticsearch.pojos.category.Match;
import com.araj.cucumber.elasticsearch.pojos.category.Query;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;

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
    	logger.info("This is the URL: " + url);
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

    public Boolean checkCategoryTOElasticSearch(String error, String url) throws Exception{
		CategoryBodySearch request = new CategoryBodySearch();
		request.setQuery(new Query());
		request.getQuery().setMatch(new Match(error));
		try {
			HttpResponse<JsonNode> response = Unirest
					.post(url).header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
					.body(OM.writeValueAsString(request))
					.asJson();
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(response.getBody().toString(), JsonObject.class);
			if (jsonObject.has("error")) {
				return true;
			}
			Boolean res = jsonObject.get("hits").getAsJsonObject().get("total").getAsJsonObject().get("value").getAsInt() == 0;
			return res;
		} catch (Exception e) {
    		logger.info(e.toString());
    		throw e;
		}
	}
}
