package com.example.techstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.techstore.model.entity.User;
import com.example.techstore.model.entity.UserRoles;

public interface UserRoleRepository extends JpaRepository<UserRoles, Integer> {
    List<UserRoles> findByUser(User user);
}
