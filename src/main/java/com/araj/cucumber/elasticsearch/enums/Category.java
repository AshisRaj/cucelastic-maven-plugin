package com.araj.cucumber.elasticsearch.enums;

public enum Category {
  UI("UI", "selenium,webdriver"),
  API("API", "rest-assured,java.net"),
  GENERAL("GENERAL", "java.lang,java.cucumber,java.util");

  private final String category;
  private final String typeError;

  Category(String category, String typeError){
    this.category = category;
    this.typeError = typeError;
  }

  public String getCategory(){
    return category;
  }

  public String getTypeError(){
    return typeError;
  }
}
