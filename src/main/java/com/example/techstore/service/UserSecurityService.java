package com.example.techstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.techstore.model.entity.User;
import com.example.techstore.model.entity.UserRoles;
import com.example.techstore.repository.UserRepository;
import com.example.techstore.repository.UserRoleRepository;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        com.example.techstore.model.entity.User user = userRepository.findByUsername(username);
        
        if (user == null) {
            System.out.println("User with name '" + username + "' not found");
            throw new UsernameNotFoundException("User not found");
        }

        List<UserRoles> roles = getRolesByUser(user);
        
        String[] roleNames = roles.stream()
            .map(userRole -> userRole.getRole().getRolename())
            .toArray(String[]::new);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(roleNames)
                .build();
    }

    public List<UserRoles> getRolesByUser(User user) {
        return userRoleRepository.findByUser(user);
    }
}
