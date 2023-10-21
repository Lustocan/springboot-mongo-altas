package com.javatech.service;

import com.javatech.model.User;
import com.javatech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository ;
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User getUserById(String id){
        if(id==null) return null ;
        User cur = repository.findById(id).get();
        if(cur==null) return null ;
        return cur ;

    }

    public User getUserByEmail(String email){
        if(email==null) return null ;
        User cur =  repository.findByEmail(email);
        if(cur==null) return null ;
        return cur ;
    }

    public User getUserByUsername(String username){
        if(username==null) return null ;
        User cur = repository.findByUsername(username);
        if(cur==null) return null ;
        return cur ;
    }

    public String deleteUser(String id){
        if(id==null) return null ;
        repository.deleteById(id);
        return id+" task deleted from dashboard" ;
    }
}
