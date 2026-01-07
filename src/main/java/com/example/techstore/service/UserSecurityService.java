package com.example.techstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.techstore.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        com.example.techstore.model.entity.User user = userRepository.findByUsername(username);
        
        if (user == null) {
            // Логирование или другая обработка
            System.out.println("Пользователь с именем '" + username + "' не найден");
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

}
