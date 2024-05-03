package com.ecom.service.impl;

import com.ecom.dao.UserRepository;
import com.ecom.entity.User;
import com.ecom.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load the user from database by userName
        System.out.println("Loading User from database");
        User user = this.userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User with given userName " + username + "not found..!"));
        return user;
    }
}
