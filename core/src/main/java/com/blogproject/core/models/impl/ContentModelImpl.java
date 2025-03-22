package com.blogproject.core.models.impl;



import com.blogproject.core.models.ContentModel;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

/**
 * Implementation of the Content component model
 */
@Model(
        adaptables = Resource.class,
        adapters = ContentModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ContentModelImpl implements ContentModel {

    @ValueMapValue
    private String text;

    @Override
    public String getText() {
        return text;
    }

}
