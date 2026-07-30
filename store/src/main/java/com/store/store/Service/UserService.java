package com.store.store.Service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.store.store.Model.Dao;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    Dao dao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return dao.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    }
    

