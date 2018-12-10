/*
 * Copyright 2018 Ashis Raj
 */

package com.araj.cucumber.elasticsearch.json.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Step extends ResultMatch {
    private List<ResultMatch> before = new ArrayList<>();
    private int line;
    private String name = "";
    private String keyword = "";
    private List<Row> rows = new ArrayList<>();
    private List<ResultMatch> after = new ArrayList<>();
    @SerializedName("doc_string")
    private DocString docString;

    public List<ResultMatch> getBefore() {
        return before;
    }

    public void setBefore(final List<ResultMatch> before) {
        this.before = before;
    }

    public int getLine() {
        return line;
    }

    public void setLine(final int line) {
        this.line = line;
    }

    public String getName() {
        return !name.isEmpty() ? name : "[Unnamed]";
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String returnNameWithArguments() {
        String tmpName = name;
        List<Argument> arguments = getArguments();
        for (int i = arguments.size() - 1; i >= 0; i--) {
            String argument = arguments.get(i).getVal();
            if (argument != null) {
                tmpName = tmpName.replaceFirst(Pattern.quote(argument), Matcher.quoteReplacement("<strong>" + argument + "</strong>"));
            }
        }
        return tmpName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(final String keyword) {
        this.keyword = keyword;
    }


    public List<Row> getRows() {
        return rows;
    }

    public void setRows(final List<Row> rows) {
        this.rows = rows;
    }

    public List<ResultMatch> getAfter() {
        return after;
    }

    public void setAfter(final List<ResultMatch> after) {
        this.after = after;
    }

    public DocString getDocString() {
        return docString;
    }

    public void setDocString(final DocString docString) {
        this.docString = docString;
    }
}
