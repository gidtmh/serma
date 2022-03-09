package com.event.EventApp.service;

import com.event.EventApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.event.EventApp.model.User user=userRepository.getById(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }

        return new User(user.getUserId(), user.getPassword(), new ArrayList<>());
    }
}
