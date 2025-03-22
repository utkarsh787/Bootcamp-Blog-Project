package com.blogproject.core.models.impl;


import com.blogproject.core.models.FooterModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


import java.time.Year;

@Model(
        adaptables = {SlingHttpServletRequest.class, Resource.class},
        adapters = FooterModel.class,
        resourceType = "blogproject/components/footer",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class FooterModelImpl implements FooterModel {
    @ValueMapValue
    private String link1;

    @ValueMapValue
    private String link2;



    @Override
    public String getLink1() {
        return link1;
    }
    @Override
    public String getLink2() {
        return link2;
    }

    @Override
    public int getCurrentYear() {
        return Year.now().getValue();
    }
}