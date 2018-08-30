package com.usbank.cms.core.utils;

import com.day.cq.wcm.api.Page;
import com.usbank.cms.core.constants.GlobalConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class LinksUtil.
 *
 * @author ajena
 */
public class LinksUtil {

    private LinksUtil() {
        // Restricting Instantiation
    }

    private static final Logger LOG = LoggerFactory.getLogger(LinksUtil.class);

    /**
     * Check internal external URL by resource.
     *
     * @param url      the url
     * @param resource the resource
     * @return the string
     */
    public static String checkInternalExternalURLByResource(String url, Resource resource) {
        LOG.debug("LinksUtil :: checkInternalExternalURLByResource :: Start");
        try {
            if (null != resource) {
                LOG.debug("checking whether the resource is a cq:page or not");
                if (isCQPage(resource) && url.startsWith(GlobalConstants.FORWARD_SLASH)) {
                    url = url + GlobalConstants.DOT_HTML;
                }
            } else {
                LOG.debug("checking whether SSL protocol is available or not");
                if (url.startsWith(GlobalConstants.HTTP_PROTOCOL) || url.startsWith(GlobalConstants.HTTPS_PROTOCOL)
                        || url.equalsIgnoreCase(GlobalConstants.HASH)) {
                    return url;
                } else {
                    return GlobalConstants.HTTP_PROTOCOL + url;
                }
            }
            LOG.debug("LinksUtil :: checkInternalExternalURLByResource :: End");
        } catch (Exception e) {
            LOG.error("Exception in LinksUtil.checkInternalExternalURL method !", e);
        }
        return url;
    }

    /**
     * Check internal URL by page.
     *
     * @param page the page
     * @return the string
     */
    public static String checkInternalURLByPage(Page page) {
        LOG.debug("LinksUtil :: checkInternalURLByPage :: Start");
        String url = null;
        if (null != page) {
            url = page.getPath();
            url = url + GlobalConstants.DOT_HTML;
        }
        LOG.debug("LinksUtil :: checkInternalURLByPage :: End");
        return url;
    }

    /**
     * Check internal URL by path.
     *
     * @param path             the path
     * @param resourceResolver the resource resolver
     * @return the string
     */
    public static String checkInternalURLByPath(String path, ResourceResolver resourceResolver) {
        LOG.debug("LinksUtil :: checkInternalURLByPath :: Start");
        String url = null;
        try {
            if (null != path) {
                if (path.startsWith(GlobalConstants.FORWARD_SLASH) && StringUtils.contains(path, GlobalConstants.DOT_HTML)) {
                    return path;
                } else if (path.startsWith(GlobalConstants.FORWARD_SLASH) && !StringUtils.contains(path, GlobalConstants.DOT_HTML) && StringUtils.contains(path, GlobalConstants.QUERY_STRING)) {
                    String queryString = StringUtils.substringAfter(path, GlobalConstants.QUERY_STRING);
                    String urlString = StringUtils.substringBefore(path, new StringBuilder(GlobalConstants.QUERY_STRING).append(queryString).toString());
                    return new StringBuilder(urlString).append(GlobalConstants.DOT_HTML).append(GlobalConstants.QUERY_STRING).append(queryString).toString();
                } else {
                    Resource resource = resourceResolver.getResource(path);
                    url = checkInternalExternalURLByResource(path, resource);
                }
            }
            LOG.debug("LinksUtil :: checkInternalURLByPath :: End");
        } catch (Exception e) {
            LOG.error("LinksUtil :: checkInternalURLByPath :: Exception {}", e);
        }
        return url;
    }

    /**
     * Checks if is CQ page.
     *
     * @param resource the resource
     * @return true, if is CQ page
     */
    public static boolean isCQPage(Resource resource) {
        LOG.debug("Starts linksutil class IsCQPage method !");
        ValueMap properties = ResourceUtil.getValueMap(resource);
        String primaryType = properties.get(GlobalConstants.JCR_PRIMARY_TYPE, String.class);
        if (primaryType != null && primaryType.equals(GlobalConstants.CQ_PAGE)) {
            return true;
        }
        LOG.debug("EXIT LinksUtil class isCQPage method !");
        return false;
    }

    /**
     * Gets the window target.
     *
     * @param newWindow the new window
     * @return the window target
     */
    public static String getWindowTarget(boolean newWindow) {
        return newWindow ? GlobalConstants.TARGET_BLANK : GlobalConstants.TARGET_SELF;
    }

    /**
     * Gets the window target.
     *
     * @param newWindow the new window
     * @return the window target
     */
    public static String getWindowTarget(String newWindow) {
        boolean isNewWindow = Boolean.parseBoolean(newWindow);
        return getWindowTarget(isNewWindow);
    }
}
