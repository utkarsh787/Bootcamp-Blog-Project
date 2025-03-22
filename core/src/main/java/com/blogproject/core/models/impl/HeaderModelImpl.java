package com.blogproject.core.models.impl;

import com.blogproject.core.models.HeaderModel;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = HeaderModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HeaderModelImpl implements HeaderModel {

    @ValueMapValue
    private String logoPath;

    @ValueMapValue
    private String headerText;

    @SlingObject
    private Resource componentResource;
    @ValueMapValue
    private String logoAlt;
    @ScriptVariable
    private ResourceResolver resolver;
    @Override
    public String getLogoAlt() {
        return logoAlt;
    }

    @Override
    public String getLogoPath() {
        return logoPath;
    }

    @Override
    public String getHeaderText() {
        return headerText;
    }


@Override
    public List<Map<String, String>> getNavigation() {
        List<Map<String, String>> navigationMap = new ArrayList<>();
        Resource links = componentResource.getChild("navItems");
        if (links != null) {
            for (Resource r : links.getChildren()) {
                String link=r.getValueMap().get("link", String.class);
                Resource linkresource=resolver.resolve(link);
                if((linkresource.adaptTo(Page.class).getProperties().get("hideInNav",String.class))==null) {
                    Map<String, String> linkMap = new HashMap<>();
                    linkMap.put("text", r.getValueMap().get("text", String.class));
                    linkMap.put("link", link);
                    navigationMap.add(linkMap);
                }
            }
        }
        return navigationMap;
    }
//    @Override
//    public List<Map<String, String>> getNavigation() {
//        List<Map<String, String>> navigationMap = new ArrayList<>();
//
//        // Fetch the 'navItems' node containing multifield data
//        Resource navItems = componentResource.getChild("navItems");
//
//        if (navItems != null) {
//            for (Resource item : navItems.getChildren()) {
//                Map<String, String> linkMap = new HashMap<>();
//                linkMap.put("text", item.getValueMap().get("text", String.class));
//                linkMap.put("link", item.getValueMap().get("link", String.class));
//                navigationMap.add(linkMap);
//            }
//        }
//
//        return navigationMap;
//    }
}

