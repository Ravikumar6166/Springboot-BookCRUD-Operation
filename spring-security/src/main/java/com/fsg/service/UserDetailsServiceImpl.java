package com.fsg.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fsg.model.MyUserDetails;
import com.fsg.model.User;
import com.fsg.repo.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //return new User("test", "test", new ArrayList<>());
        Optional<User> user = userRepo.findByEmail(username);
        user.orElseThrow(()->new UsernameNotFoundException("Not Found: "+username));
        return user.map(MyUserDetails::new).get();
    }
}
