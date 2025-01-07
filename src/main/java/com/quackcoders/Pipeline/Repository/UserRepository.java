package com.quackcoders.Pipeline.Repository;

import com.quackcoders.Pipeline.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String email);
    User findByEmail(String email);

}
