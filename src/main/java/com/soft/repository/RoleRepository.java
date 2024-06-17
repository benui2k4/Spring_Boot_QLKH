package com.soft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
