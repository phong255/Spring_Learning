package com.example.login_demo;

import com.example.login_demo.models.User;
import com.example.login_demo.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;
    @Test
    public void testAddNew(){
        User user = new User();
        user.setUsername("nam123");
        user.setPassword("12");
        user.setFirstname("nguyen van");
        user.setLastname("Nam");
        user.setClassname("A3");
        User savedUser = repo.save(user);
        Assertions.assertNotNull(savedUser);
    }
    @Test
    public void testListAll(){
        List<User> users =  repo.findAll();
        for (User u :
                users) {
            System.out.println(u.toString());
        }
    }
    @Test
    public void testUpdate(){
        Optional<User> optionalUser = repo.findById(2);
        User user = optionalUser.get();
        user.setUsername("hue");
        repo.save(user);
    }
    @Test
    public void testGet(){
        Optional<User> optionalUser = repo.findById(2);
        System.out.println(optionalUser.get());
    }
    @Test
    public void testDelete(){
        repo.deleteById(3);
    }
}
