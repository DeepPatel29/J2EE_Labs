package com.example.coffeeshop.services;

import com.example.coffeeshop.models.User;
import com.example.coffeeshop.repository.UserRepository;
import org.slf4j.Logger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(User user) {
        log.info("Adding a new user: {}", user.getUserName());
        userRepository.save(user);
    }

    public User userLogin(String userName, String password) {
        log.info("Fetching user by name from the database");
        List<User> userList = userRepository.findByUserName(userName);

        return userList.stream().filter(user -> user.getPassword().equals(password)).findFirst().orElse(null);
    }
}
