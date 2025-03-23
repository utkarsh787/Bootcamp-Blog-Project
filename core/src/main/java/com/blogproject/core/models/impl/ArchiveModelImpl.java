package com.blogproject.core.models.impl;

import com.blogproject.core.models.ArchiveModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.text.SimpleDateFormat;
import java.util.*;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = ArchiveModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ArchiveModelImpl implements ArchiveModel {

    @ValueMapValue
    private String blogpath;

    @ScriptVariable
    private ResourceResolver resolver;


    @Override
    public List<String> getArchiveDates(){
        Set<String> archive = new HashSet<>();
        Resource r=resolver.resolve(blogpath);
        Iterator<Resource> it=r.listChildren();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMM yyyy");
        while(it.hasNext()){
            archive.add(dateFormat.format(it.next().getValueMap().get("jcr:created",Date.class)));
        }
        return new ArrayList<>(archive);
    }
    public String getBlogpath(){
        return blogpath;
    }
}
