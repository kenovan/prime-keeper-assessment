package com.prime.keeper.assessment.service.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.keeper.assessment.model.properties.AppProperties;
import com.prime.keeper.assessment.persistence.properties.AppPropertiesRepository;

@Service
public class AppPropertiesServiceImpl implements AppPropertiesService {

	@Autowired
	private AppPropertiesRepository appPropertiesRespository;
	
	/* (non-Javadoc)
	 * @see com.prime.keeper.assessment.service.properties.AppPropertiesService#getAppProperties(java.lang.String)
	 */
	@Override
	public AppProperties getAppProperties(final String appKey) {
		return appPropertiesRespository.findByAppKey(appKey);
	}
}
