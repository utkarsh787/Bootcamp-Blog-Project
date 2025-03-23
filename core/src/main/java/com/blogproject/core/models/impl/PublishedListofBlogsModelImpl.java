package com.blogproject.core.models.impl;

import com.blogproject.core.models.PublishedListofBlogsModel;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import com.blogproject.core.services.PublishedBlogs;
import com.blogproject.core.services.PublishedBlogsService;
import java.util.*;

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = PublishedListofBlogsModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class PublishedListofBlogsModelImpl implements PublishedListofBlogsModel {
    @ScriptVariable
    private Page currentPage;

    @ScriptVariable
    private SlingHttpServletRequest request;

    @OSGiService
    private PublishedBlogsService publishedBlogsService;

    @OSGiService
    private PublishedBlogs publishedBlogs;

    @Override
    public List<Map<String, String>> getBlogs() {
        String monthParam = request.getParameter("month");
        int limit = publishedBlogs.noOfBlogs();
        return publishedBlogsService.getPublishedBlogs(currentPage, monthParam, limit);
    }
}