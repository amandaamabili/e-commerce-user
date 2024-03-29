package com.santander.user.repository;

import com.santander.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByEmail(String email);
}
