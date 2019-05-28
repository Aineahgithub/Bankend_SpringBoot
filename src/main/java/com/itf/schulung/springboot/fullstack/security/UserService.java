package com.itf.schulung.springboot.fullstack.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itf.schulung.springboot.fullstack.model.User;
import com.itf.schulung.springboot.fullstack.repositorys.*;
@Service
public class UserService implements UserDetailsService {
   private UserRepository userRepo;
    
 
    @Override
    public UserDetails loadUserByUsername(String username) {
    	
    
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
        
        
        }
        
    }
