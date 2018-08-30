package com.usbank.cms.core.models;

import com.usbank.cms.core.utils.LinksUtil;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * This class will read the image tiles configuration, prepares the link and target window and sends it
 * to HTL.
 *
 * @author ajena
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageTilesModel {
    /**
     * Logger configuration for ImageTilesModel
     */
    private static final Logger log = LoggerFactory.getLogger(ImageTilesModel.class);

    /*
     * The ResourceResolver Object
     */
    @SlingObject
    private ResourceResolver resourceResolver;

    @ValueMapValue
    private String ctaUrl;

    @ValueMapValue
    private String newWindow;

    @PostConstruct
    protected void init() {
        log.info("ImageTilesModel :: init :: Start");
        ctaUrl = LinksUtil.checkInternalURLByPath(ctaUrl, resourceResolver);
        newWindow = LinksUtil.isNewWindow(newWindow);
        log.info("ImageTilesModel :: init :: End");
    }

    public String getCtaUrl() {
        return ctaUrl;
    }

    public String getNewWindow() {
        return newWindow;
    }
}
