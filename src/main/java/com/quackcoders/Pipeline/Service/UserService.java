package com.quackcoders.Pipeline.Service;

import com.quackcoders.Pipeline.Models.User;
import com.quackcoders.Pipeline.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

@Autowired
    private UserRepository userRepository;

@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser(User user){
        return userRepository.save(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public boolean emailExists(String email){
        return userRepository.findByEmail(email) !=null;
    }
    public void deleteUserById(String id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User does not exist");
        }
        userRepository.deleteById(id);
    }

}
