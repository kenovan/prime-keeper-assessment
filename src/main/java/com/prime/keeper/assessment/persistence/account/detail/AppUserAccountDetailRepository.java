package com.prime.keeper.assessment.persistence.account.detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.prime.keeper.assessment.model.account.AppUserAccountDetail;

@Repository
public interface AppUserAccountDetailRepository extends JpaRepository<AppUserAccountDetail, Integer>, JpaSpecificationExecutor<AppUserAccountDetail> {

	
}
