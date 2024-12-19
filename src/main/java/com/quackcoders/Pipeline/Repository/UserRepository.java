package com.quackcoders.Pipeline.Repository;

import com.quackcoders.Pipeline.Models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<Users, String> {
    Optional<Users> findByUsername(String email);
    Boolean existsByUsername

}
