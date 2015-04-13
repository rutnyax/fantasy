package com.kossyuzokwe.fantasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

	Role findByRoleName(String roleName);

}
