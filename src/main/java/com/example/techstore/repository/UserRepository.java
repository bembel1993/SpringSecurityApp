package com.example.techstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.techstore.model.entity.User;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
    User findByPassword(String password);
}

