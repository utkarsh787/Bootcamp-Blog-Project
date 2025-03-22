package com.blogproject.core.models.impl;

import com.blogproject.core.models.PublishedListofBlogsModel;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import java.text.SimpleDateFormat;
import com.blogproject.core.services.PublishedBlogs;
import com.blogproject.core.services.PublishedBlogsService;
import java.util.*;

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = PublishedListofBlogsModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class PublishedListofBlogsImplModel implements PublishedListofBlogsModel {
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
//    @ScriptVariable
//    private Page currentPage;
//
//
//    @Override
//    public List<Map<String, String>> getBlogs() {
//        List<Map<String, String>> Blogs = new ArrayList<>();
//        Iterator<Page> childPages = currentPage.listChildren();
//        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
//
//        while (childPages.hasNext()) {
//            Page childPage = childPages.next();
//            Map<String, String> Blog = new HashMap<>();
//            Blog.put("Title", childPage.getTitle());
//            Blog.put("Date", sdf.format(childPage.getProperties().get("jcr:created", Date.class)));
//            Blog.put("Img", getImagePath(childPage));
//            Blog.put("Link", childPage.getPath()+".html");
//            Blog.put("SubHeading", childPage.getDescription() != null ? childPage.getDescription() : "No Description");            Blogs.add(Blog);
//        }
//        return Blogs;
//    }
//
//   private String getImagePath(Page childPage) {
//       String imagePath = childPage.getPath() + "/jcr:content/root/responsivegrid_356875995/image/file";
//        return imagePath;
//   }
//    private String getImagePath(Page childPage) {
//        Resource imageResource = childPage.getContentResource("root/responsivegrid_356875995/image");
//
//        if (imageResource != null) {
//            // Check if image was uploaded through browse (has file node)
//            Resource fileResource = imageResource.getChild("file");
//            if (fileResource != null) {
//                return fileResource.getPath();
//            }
//
//            // Check if image was uploaded through Assets (has fileReference property)
//            ValueMap properties = imageResource.getValueMap();
//            String fileReference = properties.get("fileReference", String.class);
//            if (fileReference != null) {
//                return fileReference;
//            }
//        }
//        // Return null or a default image path if no image is found
//        return null;
//    }
}
