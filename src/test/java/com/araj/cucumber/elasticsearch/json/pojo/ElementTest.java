package com.araj.cucumber.elasticsearch.json.pojo;

import com.araj.cucumber.elasticsearch.constants.Status;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ElementTest {
    private Element element;

    @Before
    public void setup() {
        element = new Element();
    }

    @Test
    public void getSkippedStatusInEmptyElementsTest() {
        Status status = element.getStatus();
        assertThat(Status.SKIPPED).isEqualByComparingTo(status);
        assertThat(element.isSkipped()).isTrue();
    }

    @Test
    public void getPassedStatusTest() {
        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        Result result = new Result();
        result.setStatus("passed");
        step.setResult(result);
        steps.add(step);
        element.setSteps(steps);

        Status status = element.getStatus();
        assertThat(Status.PASSED).isEqualByComparingTo(status);
        assertThat(element.isPassed()).isTrue();
    }

    @Test
    public void passedStatusOnPassedAndSkippedStepsTest() {
        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        Result result = new Result();
        result.setStatus("passed");
        step.setResult(result);
        steps.add(step);

        step = new Step();
        result = new Result();
        result.setStatus("skipped");
        step.setResult(result);
        steps.add(step);
        element.setSteps(steps);

        Status status = element.getStatus();
        assertThat(Status.PASSED).isEqualByComparingTo(status);
    }

    @Test
    public void failedStatusOnFailedBeforeHookTest() {
        List<ResultMatch> before = new ArrayList<>();
        ResultMatch beforeHook = new ResultMatch();
        Result beforeHookResult = new Result();
        beforeHookResult.setStatus("failed");
        beforeHook.setResult(beforeHookResult);
        before.add(beforeHook);
        element.setBefore(before);

        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        Result result = new Result();
        result.setStatus("passed");
        step.setResult(result);
        steps.add(step);

        step = new Step();
        result = new Result();
        result.setStatus("passed");
        step.setResult(result);
        steps.add(step);
        element.setSteps(steps);

        Status status = element.getStatus();
        assertThat(Status.FAILED).isEqualByComparingTo(status);

    }

    @Test
    public void failedStatusOnFailedAfterHookTest() {
        List<ResultMatch> after = new ArrayList<>();
        ResultMatch afterHook = new ResultMatch();
        Result afterHookResult = new Result();
        afterHookResult.setStatus("failed");
        afterHook.setResult(afterHookResult);
        after.add(afterHook);
        element.setAfter(after);

        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        Result result = new Result();
        result.setStatus("passed");
        step.setResult(result);
        steps.add(step);

        step = new Step();
        result = new Result();
        result.setStatus("passed");
        step.setResult(result);
        steps.add(step);
        element.setSteps(steps);

        Status status = element.getStatus();
        assertThat(Status.FAILED).isEqualByComparingTo(status);
    }

    @Test
    public void failedStatusOnFailedAfterStepHookTest() {
        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        Result result = new Result();
        result.setStatus("passed");
        step.setResult(result);

        List<ResultMatch> after = new ArrayList<>();
        ResultMatch afterStepHook = new ResultMatch();
        Result afterStepHookResult = new Result();
        afterStepHookResult.setStatus("failed");
        afterStepHook.setResult(afterStepHookResult);
        after.add(afterStepHook);
        step.setAfter(after);

        steps.add(step);

        step = new Step();
        result = new Result();
        result.setStatus("passed");
        step.setResult(result);
        steps.add(step);
        element.setSteps(steps);

        Status status = element.getStatus();
        assertThat(Status.FAILED).isEqualByComparingTo(status);
    }

    @Test
    public void failedStatusOnFailedBeforeStepHookTest() {
        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        Result result = new Result();
        result.setStatus("passed");
        step.setResult(result);

        List<ResultMatch> before = new ArrayList<>();
        ResultMatch beforeStepHook = new ResultMatch();
        Result beforeStepHookResult = new Result();
        beforeStepHookResult.setStatus("failed");
        step.setResult(beforeStepHookResult);
        before.add(beforeStepHook);
        step.setBefore(before);

        steps.add(step);

        step = new Step();
        result = new Result();
        result.setStatus("passed");
        step.setResult(result);
        steps.add(step);
        element.setSteps(steps);

        Status status = element.getStatus();
        assertThat(Status.FAILED).isEqualByComparingTo(status);
    }

    @Test
    public void getFailedStatusTest() {
        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        Result result = new Result();
        result.setStatus("failed");
        step.setResult(result);
        steps.add(step);
        element.setSteps(steps);

        Status status = element.getStatus();
        assertThat(Status.FAILED).isEqualByComparingTo(status);
        assertThat(element.isFailed()).isTrue();
    }

    @Test
    public void getUndefinedStatusTest() {
        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        Result result = new Result();
        result.setStatus("undefined");
        step.setResult(result);
        steps.add(step);
        element.setSteps(steps);

        Status status = element.getStatus();
        assertThat(Status.SKIPPED).isEqualByComparingTo(status);
        assertThat(element.isSkipped()).isTrue();
    }

    @Test
    public void totalDurationTest() {
        List<ResultMatch> beforeSteps = new ArrayList<>();
        ResultMatch before = new ResultMatch();
        Result beforeResult = new Result();
        beforeResult.setDuration(1000000);
        before.setResult(beforeResult);
        beforeSteps.add(before);
        element.setBefore(beforeSteps);

        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        Result stepResult = new Result();
        stepResult.setDuration(9991000003L);
        step.setResult(stepResult);
        steps.add(step);

        Step step2 = new Step();
        Result stepResult2 = new Result();
        stepResult2.setDuration(123667782L);
        step2.setResult(stepResult2);
        steps.add(step2);

        element.setSteps(steps);

        List<ResultMatch> afterSteps = new ArrayList<>();
        ResultMatch after = new ResultMatch();
        Result afterResult = new Result();
        afterResult.setDuration(2000000);
        after.setResult(afterResult);
        afterSteps.add(after);
        element.setAfter(afterSteps);

        assertThat(10117667785L).isEqualTo(element.getTotalDuration());
        assertThat("0m 10s 117ms").isEqualTo(element.returnTotalDurationString());
    }

    @Test
    public void stepSummaryTest() {
        List<Step> steps = new ArrayList<>();

        Step step1 = new Step();
        Result result1 = new Result();
        result1.setStatus("passed");
        step1.setResult(result1);
        steps.add(step1);
        steps.add(step1);
        steps.add(step1);

        Step step2 = new Step();
        Result result2 = new Result();
        result2.setStatus("skipped");
        step2.setResult(result2);
        steps.add(step2);

        Step step3 = new Step();
        Result result3 = new Result();
        result3.setStatus("pending");
        step3.setResult(result3);
        steps.add(step3);

        Step step4 = new Step();
        Result result4 = new Result();
        result4.setStatus("failed");
        step4.setResult(result4);
        steps.add(step4);

        element.setSteps(steps);

        assertThat(6).isEqualTo(element.getTotalNumberOfSteps());
        assertThat(3).isEqualTo(element.getTotalNumberOfPassedSteps());
        assertThat(1).isEqualTo(element.getTotalNumberOfFailedSteps());
        assertThat(2).isEqualTo(element.getTotalNumberOfSkippedSteps());
    }

    @Test
    public void hasDocStringsTest(){
        assertThat(element.hasDocStrings()).isFalse();

        List<Step> steps = new ArrayList<>();

        Step step1 = new Step();
        steps.add(step1);

        Step step2 = new Step();
        step2.setDocString(new DocString());
        steps.add(step2);

        element.setSteps(steps);

        assertThat(element.hasDocStrings()).isTrue();
    }

    @Test
    public void hasStepHooksBeforeTest(){
        assertThat(element.hasStepHooks()).isFalse();

        List<Step> steps = new ArrayList<>();

        Step step1 = new Step();
        steps.add(step1);

        Step step2 = new Step();
        List<ResultMatch> beforeStepHooks = new ArrayList<>();
        beforeStepHooks.add(new ResultMatch());
        step2.setBefore(beforeStepHooks);
        steps.add(step2);

        element.setSteps(steps);

        assertThat(element.hasStepHooks()).isTrue();
    }

    @Test
    public void hasStepHooksAfterTest(){
        assertThat(element.hasStepHooks()).isFalse();

        List<Step> steps = new ArrayList<>();

        Step step1 = new Step();
        steps.add(step1);

        Step step2 = new Step();
        List<ResultMatch> afterStepHooks = new ArrayList<>();
        afterStepHooks.add(new ResultMatch());
        step2.setAfter(afterStepHooks);
        steps.add(step2);

        element.setSteps(steps);

        assertThat(element.hasStepHooks()).isTrue();
    }
}
