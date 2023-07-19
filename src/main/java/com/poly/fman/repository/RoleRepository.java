package com.poly.fman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.fman.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}