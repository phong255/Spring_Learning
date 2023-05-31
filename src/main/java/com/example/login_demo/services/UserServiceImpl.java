package com.example.login_demo.services;

import com.example.login_demo.models.User;
import com.example.login_demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer user_id) {
        Optional<User> u = userRepository.findById(user_id);
        return u.get();
    }


    @Override
    public void updateUser(User newUser,Integer user_id){
        System.out.println(user_id);
        Optional<User> optionalUser = userRepository.findById(user_id);
        User user;
        if(optionalUser.isPresent()) {
            user = optionalUser.get();
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setFirstname(newUser.getFirstname());
            user.setLastname(newUser.getLastname());
            user.setClassname(newUser.getClassname());
            userRepository.save(user);
        }
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer user_id) {
        userRepository.deleteById(user_id);
    }
}
