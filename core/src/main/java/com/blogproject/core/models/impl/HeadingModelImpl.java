package com.blogproject.core.models.impl;

import com.adobe.granite.security.user.UserProperties;
import com.adobe.granite.security.user.UserPropertiesManager;
import com.adobe.granite.security.user.UserPropertiesService;
import com.blogproject.core.models.HeadingModel;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


import java.text.SimpleDateFormat;

import java.util.Date;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = HeadingModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeadingModelImpl implements HeadingModel {

    @ScriptVariable
    private Page currentPage; // Fetches Page Properties

    @ScriptVariable
    private ResourceResolver resolver;

    @ValueMapValue(name = "jcr:created")
    private Date created; // Fetches the Creation Date

    // Getter for Page Title
    @Override
    public String getTitle() {
        return currentPage.getTitle();
    }

    @ValueMapValue(name = "jcr:createdBy")
    private String userId;

    // Getter for Created Date (Formatted)
    @Override
    public String getCreatedDate() {
        if (created != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMM dd, yyyy");
            return dateFormat.format(created.getTime());
        }
        return "No Date Found";
    }

    // Getter for Created By
    @Override
    public String getAuthorName() {
        String createdBy=getUserName();
        return createdBy != null ? createdBy : "Unknown Author";
    }




    private String getUserName() {
        try {
                UserPropertiesManager userPropertiesManager = resolver.adaptTo(UserPropertiesManager.class);
                if (userPropertiesManager != null) {
                    UserProperties userProperties = userPropertiesManager.getUserProperties(userId, UserPropertiesService.PROFILE_PATH);
                    if (userProperties != null) {
                        String fullName = (String) userProperties.getDisplayName();
                        return (fullName != null && !fullName.isEmpty()) ? fullName : userId;
                    }
                }
         //   }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown User";
    }
}