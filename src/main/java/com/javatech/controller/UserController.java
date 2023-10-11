package com.javatech.controller;

import com.javatech.model.AuthRequest;
import com.javatech.model.User;
import com.javatech.service.JwtService;
import com.javatech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService   ;


    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User cur = userService.getUserById(userId);
        if(cur==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        return new ResponseEntity<>(cur, HttpStatus.OK);
    }

    @GetMapping("/email")
    public User getUserByEm(@RequestBody String email){
        return userService.getUserByEmail(email);
    }

    @GetMapping("/username")
    public ResponseEntity<User> getUserByUs(@RequestBody String username){
        User cur = userService.getUserByUsername(username);
        if(cur==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        return new ResponseEntity<>(cur, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return userService.deleteUser(userId);
    }

}
