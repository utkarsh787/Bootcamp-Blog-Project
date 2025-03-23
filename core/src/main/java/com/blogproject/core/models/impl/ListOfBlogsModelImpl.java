package com.blogproject.core.models.impl;

import com.blogproject.core.models.ListOfBlogsModel;


import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import com.blogproject.core.services.PublishedBlogsService;
import java.util.*;

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = ListOfBlogsModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ListOfBlogsModelImpl implements ListOfBlogsModel {
    @ValueMapValue
    private String blogpath;

    @ScriptVariable
    private ResourceResolver resolver;

    @OSGiService
    PublishedBlogsService publishedBlogsService;


    @Override
    public List<Map<String, String>> getBlogs() {
        Resource r = resolver.resolve(blogpath);
        Page currPage = r.adaptTo(Page.class);
        return publishedBlogsService.getPublishedBlogs(currPage, null, 3);
    }
}
