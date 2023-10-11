package com.javatech.repository;

import com.javatech.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

public interface UserRepository extends MongoRepository<User, String>{
    User findByEmail(String email);
    User findByUsername(String username);
}
