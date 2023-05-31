package com.example.login_demo.services;

import com.example.login_demo.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<User> getAllUser();
    public User getUserById(Integer user_id);
    public void updateUser(User newUser,Integer id);
    public void addUser(User user);
    public void deleteUser(Integer user_id);
}
