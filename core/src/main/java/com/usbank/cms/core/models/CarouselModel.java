package com.usbank.cms.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.usbank.cms.core.utils.CommonUtil;
import com.usbank.cms.core.utils.LinksUtil;
import com.usbank.cms.core.vo.CarouselImageVO;

/**
 * This class will read the Carousel configuration, prepares a list and sends it
 * to HTL.
 *
 * @author pannem
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CarouselModel {
    /**
     * Logger configuration for FooterModel
     */
    private static final Logger log = LoggerFactory.getLogger(CarouselModel.class);

    /*
     * The ResourceResolver Object
     */
    @SlingObject
    private ResourceResolver resourceResolver;

    private List<CarouselImageVO> carouselList;

    @ValueMapValue
    private String[] carouselData;
    
    /**
     * This method reads the Carousel multi field values
     * and prepares the list.
     */
    @PostConstruct
    protected void init() {
        log.info("CarouselModel :: init :: Start");
        if (ArrayUtils.isNotEmpty(carouselData)) {
            carouselList = new ArrayList<>();
            for (String data : carouselData) {
                CarouselImageVO carouselVo = (CarouselImageVO) CommonUtil.getObjectFromJson(data, new CarouselImageVO());
                carouselVo.setLinkUrl(LinksUtil.checkInternalURLByPath(carouselVo.getLinkUrl(), resourceResolver));
                carouselList.add(carouselVo);
            }
        } else {
            log.error("CarouselModel :: Empty List");
        }
        log.info("CarouselModel :: init :: End");
    }

    public List<CarouselImageVO> getCarouselList() {
        return carouselList;
    }
}
