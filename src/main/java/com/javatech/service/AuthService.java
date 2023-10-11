package com.javatech.service;

import com.javatech.model.User;
import com.javatech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UserRepository repository ;
    @Autowired
    private PasswordEncoder passwordEncoder ;

    /*
    public static byte[] getSalt() throws NoSuchAlgorithmException {
        // Always use a secure random generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG") ;
        // Create array for the salt (16 bytes length)
        byte[] salt = new byte[16];
        // get a random salt
        sr.nextBytes(salt);
        // Return the generated salt
        return salt       ;
    }

    public static String getSecurePassword(String password, byte[] salt) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public User sign_up(User user){
        user.setUserId(UUID.randomUUID().toString().split("-")[0]);

        if((user.getRole() ==null)||(user.getEmail()==null
                ||user.getPassword()==null))  return null ;
        else if(repository.findByEmail(user.getEmail())!=null) {
            System.out.println("Error : email already exists");
            return null ;
        }
        byte[] salt ;
        try{
            salt = getSalt() ;
            user.setSalt(salt.toString());
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace() ;
            return null         ;
        }
        user.setPassword(getSecurePassword(user.getPassword(), salt));
        return repository.save(user);
    }*/
    public User sign_up(User user){
        user.setUserId(UUID.randomUUID().toString().split("-")[0]);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
}
