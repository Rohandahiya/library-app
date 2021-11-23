package com.example.gfg.libraryapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCacheRepository userCacheRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // We can also add a cache here

        User user  = userCacheRepository.getUserFromCache(s);

        if(user==null){
            user = userRepository.findByUsername(s);
            userCacheRepository.setUserInRedisCache(user);
        }

        return user;
    }
}
