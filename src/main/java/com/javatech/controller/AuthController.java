package com.javatech.controller;

import com.javatech.model.AuthRequest;
import com.javatech.model.User;
import com.javatech.service.AuthService;
import com.javatech.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
@ControllerAdvice
@RequestMapping
public class AuthController {
    @Autowired
    private AuthService service ;
    @Autowired
    private JwtService jwtService ;
    @Autowired
    private AuthenticationManager authenticationManager ;
    @Autowired
    private PasswordEncoder passwordEncoder ;

    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/welcome")
    public String welcomePage(){
        return "Welcome in this page" ;
    }

    @PostMapping("/sign_up")
    public ResponseEntity<User> signUp(@RequestBody User user){
        User el = service.sign_up(user);

        if(el==null) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(el,HttpStatus.CREATED);
    }

    @PostMapping("/auth")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest, HttpServletResponse res){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            Cookie cookie = new Cookie("User", jwtService.generateToken(authRequest.getPassword()));
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60*60*40);
            res.addCookie(cookie);
            return jwtService.generateToken(authRequest.getUsername());
        }
        else throw new UsernameNotFoundException("Invalid user request !");
    }
}
