package com.prime.keeper.assessment.persistence.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.prime.keeper.assessment.model.role.AppRole;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Integer>, JpaSpecificationExecutor<AppRole> {

	public AppRole findByRoleName(String roleName);
}
