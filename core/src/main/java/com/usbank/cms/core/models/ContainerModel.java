package com.usbank.cms.core.models;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ContainerModel {
	
	/** The Constant LOG. */
	final private static Logger LOG = (Logger) LoggerFactory.getLogger(ContainerModel.class);
	
	/** Component List. */
	private String componentList[];
	
	/** The component selection. */

	private String component=" ";
	
	/** The number of occurrence. */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String count;
	
	
	/**
	 * Inits the Container Component.
	 *
	 */
	@PostConstruct
	protected void init(){
		
		LOG.info("Start containerModel class init method !");
		try{
			if(component!=null && count != null && count != ""){
				int size = Integer.parseInt(count);
				componentList = new String[size];
				Arrays.fill(componentList,component);
			}
		}
		catch (Exception e) {
            LOG.error("Exception in containerModel");
        }
	}


	public String[] getComponentList() {
		return componentList;
	}
}
