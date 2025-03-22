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
        adaptables = {SlingHttpServletRequest.class, Resource.class},
        adapters = ArchiveModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ArchiveModelImpl implements ArchiveModel {

    @ValueMapValue
    private String blogpath;

    @ScriptVariable
    private ResourceResolver resolver;


    @Override
    public List<String> getArchiveDates() {
        Resource r=resolver.resolve(blogpath);
        Set<String> archive=new HashSet<>();
        Iterator<Resource> it=r.listChildren();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMMM yyyy");
        while(it.hasNext()){
            archive.add(sdf.format(it.next().getValueMap().get("jcr:created",Date.class)));
        }
        return new ArrayList<>(archive);
    }
}
