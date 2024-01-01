package com.javatech.service;

import com.javatech.model.User;
import com.javatech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UserRepository repository ;
    @Autowired
    private PasswordEncoder passwordEncoder ;

    public User sign_up(User user){
        if((user.getRole() ==null)||(user.getEmail()==null
                ||user.getPassword()==null)
                ||user.getUsername()==null)  return null ;
        else if(repository.findByEmail(user.getEmail())!=null) {
            return null ;
        }
        user.setUserId(UUID.randomUUID().toString().split("-")[0]);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
}
