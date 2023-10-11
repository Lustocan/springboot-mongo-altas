package com.javatech.config;

import com.javatech.model.User;
import com.javatech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class usrUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(repository.findByUsername(username));

        return user.map(usrUserDetails::new)
              .orElseThrow(()->new UsernameNotFoundException("user not found "+username));
    }
}
