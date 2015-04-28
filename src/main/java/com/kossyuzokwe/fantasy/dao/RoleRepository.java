package com.kossyuzokwe.fantasy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.model.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

	Role findByRoleName(String roleName);

}
