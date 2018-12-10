package com.araj.cucumber.elasticsearch.exceptions;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class CucElasticPluginExceptionTest {
    @Test
    public void testErrorMessage() {
    	CucElasticPluginException exception = new CucElasticPluginException("This is a test");
    	assertThat("This is a test").isEqualTo(exception.getMessage());
	}
}
