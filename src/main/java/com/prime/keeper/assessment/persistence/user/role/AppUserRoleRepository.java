package com.prime.keeper.assessment.persistence.user.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.prime.keeper.assessment.model.join.AppUserRole;

@Repository
public interface AppUserRoleRepository extends JpaRepository<AppUserRole, Integer>, JpaSpecificationExecutor<AppUserRole> {

}
