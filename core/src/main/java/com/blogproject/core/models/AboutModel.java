package com.blogproject.core.models;


import org.apache.sling.api.resource.Resource;
import org.osgi.annotation.versioning.ConsumerType;

@ConsumerType
public interface AboutModel {
    String getAbout();
}

