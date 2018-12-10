package com.araj.cucumber.elasticsearch.json.pojo;

import com.araj.cucumber.elasticsearch.constants.Status;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ResultMatchTest {
    private ResultMatch resultMatch;

    @Before
    public void setup() {
        resultMatch = new ResultMatch();
    }

    @Test
    public void glueMethodNameTest() {
        Match match = new Match();
        match.setLocation("someMethod");
        resultMatch.setMatch(match);
        assertThat("someMethod").isEqualTo(resultMatch.getGlueMethodName());
    }

    @Test
    public void argumentsTest() {
        Match match = new Match();
        List<Argument> arguments = new ArrayList<>();
        Argument argument = new Argument();
        argument.setVal("arg1");
        argument.setOffset(10);
        arguments.add(argument);
        match.setArguments(arguments);
        resultMatch.setMatch(match);
        assertThat(1).isEqualTo(resultMatch.getArguments().size());
        assertThat("arg1").isEqualTo(resultMatch.getArguments().get(0).getVal());
        assertThat(10).isEqualTo(resultMatch.getArguments().get(0).getOffset());
    }

    @Test
    public void getStatusStringTest() {
        Result result = new Result();
        result.setStatus(Status.SKIPPED.getStatusAsString());
        resultMatch.setResult(result);
        assertThat("skipped").isEqualTo(resultMatch.getStatusString());
    }

    @Test
    public void isFailedTest() {
        Result result = new Result();
        result.setStatus(Status.FAILED.getStatusAsString());
        resultMatch.setResult(result);
        assertThat(resultMatch.isFailed()).isTrue();
        assertThat(resultMatch.isPassed()).isFalse();
        assertThat(resultMatch.isSkipped()).isFalse();
    }

    @Test
    public void isPassedTest() {
        Result result = new Result();
        result.setStatus(Status.PASSED.getStatusAsString());
        resultMatch.setResult(result);
        assertThat(resultMatch.isFailed()).isFalse();
        assertThat(resultMatch.isPassed()).isTrue();
        assertThat(resultMatch.isSkipped()).isFalse();
    }

    @Test
    public void isSkippedTest() {
        Result result = new Result();
        result.setStatus(Status.SKIPPED.getStatusAsString());
        resultMatch.setResult(result);
        assertThat(resultMatch.isFailed()).isFalse();
        assertThat(resultMatch.isPassed()).isFalse();
        assertThat(resultMatch.isSkipped()).isTrue();
    }

    @Test
    public void getConsolidatedStatusTest() {
        Result result = new Result();
        result.setStatus(Status.SKIPPED.getStatusAsString());
        resultMatch.setResult(result);
        assertThat(Status.SKIPPED).isEqualTo(resultMatch.getConsolidatedStatus());
        assertThat("skipped").isEqualTo(resultMatch.getConsolidatedStatusString());

        result = new Result();
        result.setStatus(Status.PENDING.getStatusAsString());
        resultMatch.setResult(result);
        assertThat(Status.SKIPPED).isEqualTo(resultMatch.getConsolidatedStatus());
        assertThat("skipped").isEqualTo(resultMatch.getConsolidatedStatusString());

        result = new Result();
        result.setStatus(Status.UNDEFINED.getStatusAsString());
        resultMatch.setResult(result);
        assertThat(Status.SKIPPED).isEqualTo(resultMatch.getConsolidatedStatus());
        assertThat("skipped").isEqualTo(resultMatch.getConsolidatedStatusString());

        result = new Result();
        result.setStatus(Status.AMBIGUOUS.getStatusAsString());
        resultMatch.setResult(result);
        assertThat(Status.SKIPPED).isEqualTo(resultMatch.getConsolidatedStatus());
        assertThat("skipped").isEqualTo(resultMatch.getConsolidatedStatusString());

        result = new Result();
        result.setStatus(Status.PASSED.getStatusAsString());
        resultMatch.setResult(result);
        assertThat(Status.PASSED).isEqualTo(resultMatch.getConsolidatedStatus());
        assertThat("passed").isEqualTo(resultMatch.getConsolidatedStatusString());
    }
}
