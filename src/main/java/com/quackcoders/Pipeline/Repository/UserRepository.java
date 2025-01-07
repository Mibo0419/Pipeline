package com.quackcoders.Pipeline.Repository;

import com.quackcoders.Pipeline.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

}
