package com.blogproject.core.models.impl;

import com.blogproject.core.models.AboutModel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,
        adapters = AboutModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AboutModelImpl implements AboutModel {

    @ValueMapValue
    private String about;

    @Override
    public String getAbout() {
        return about != null ? about : "";
    }
}
