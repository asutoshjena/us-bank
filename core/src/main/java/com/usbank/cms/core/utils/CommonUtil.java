package com.usbank.cms.core.utils;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ajena
 */
public class CommonUtil {
    private CommonUtil() {
        //Restricting Instantiation
    }

    /**
     * Logger configuration for CommonUtil
     */
    private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);

    public static Object getObjectFromJson(String jsonString, Object obj) {
        Gson gson = new Gson();
        Object returnValue = null;
        try {
            returnValue = gson.fromJson(jsonString, obj.getClass());
        } catch (Exception e) {
            log.error("Exception occured in CommonUtil :: getObjectFromJson {}", e);
        }
        return returnValue;
    }
}
