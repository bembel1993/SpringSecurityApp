package com.example.techstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.techstore.model.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findById(Integer id);
}
