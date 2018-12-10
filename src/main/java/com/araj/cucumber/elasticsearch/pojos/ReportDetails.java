/*
 * Copyright 2018 Ashis Raj
 */

package com.araj.cucumber.elasticsearch.pojos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportDetails {
    private String chartJson;
    private final String date;
    private final String pageName;

    public ReportDetails(final String pageName) {
        this.pageName = pageName;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.date = dateFormat.format(date);
    }

    public String getChartJson() {
        return chartJson;
    }

    public void setChartJson(final String chartJson) {
        this.chartJson = chartJson;
    }

    public String getDate() {
        return date;
    }
    
    public String getPageName() {
        return pageName;
    }
}
