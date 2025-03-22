package com.blogproject.core.models;


import java.util.List;
import java.util.Map;

public interface HeaderModel {

    String getLogoPath();

    String getHeaderText();
    String getLogoAlt();
    List<Map<String,String>> getNavigation();

}