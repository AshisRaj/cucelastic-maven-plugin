package com.araj.cucumber.elasticsearch.json.pojo;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ResultTest {
    private Result result;

    @Before
    public void setup() {
        result = new Result();
    }

    @Test
    public void hasErrorMessageTest() {
        assertThat(result.hasErrorMessage());
        result.setErrorMessage("My Error");
        assertThat(result.hasErrorMessage()).isTrue();
    }

    @Test
    public void durationTest() {
        result.setDuration(1234567890);
        assertThat(1234L).isEqualTo(result.getDurationInMilliseconds());
        assertThat("0m 01s 234ms").isEqualTo(result.returnDurationString());
    }
}
