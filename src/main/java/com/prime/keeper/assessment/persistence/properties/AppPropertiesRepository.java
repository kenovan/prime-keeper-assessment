package com.prime.keeper.assessment.persistence.properties;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.prime.keeper.assessment.model.properties.AppProperties;

@Repository
public interface AppPropertiesRepository extends JpaRepository<AppProperties, String>, JpaSpecificationExecutor<AppProperties> {

	public AppProperties findByAppKey(String appKey);
}
