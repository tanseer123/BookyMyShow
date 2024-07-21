package com.example.bookmyshow.services;

import com.example.bookmyshow.models.User;
import com.example.bookmyshow.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(String name, String email, String password){

        Optional<User> optionalUser = userRepository.findAllByEmail(email);
        User savedUser =null;

        if(optionalUser.isPresent()){
            //move to login
        }
        else {
            //Create a user object amd save to DB.
            User user = new User();
            user.setName(name);
            user.setEmail(email);

            //BcryptPasswordEncoder
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(password));
            savedUser=userRepository.save(user);

        }
        return savedUser;

    }

}
