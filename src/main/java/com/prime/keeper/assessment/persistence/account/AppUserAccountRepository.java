package com.prime.keeper.assessment.persistence.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.prime.keeper.assessment.model.account.AppUserAccount;

@Repository
public interface AppUserAccountRepository extends JpaRepository<AppUserAccount, Integer>, JpaSpecificationExecutor<AppUserAccount> {

}
