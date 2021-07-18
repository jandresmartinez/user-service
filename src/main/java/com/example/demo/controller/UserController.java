package com.example.demo.controller;


import com.example.demo.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.*;

@RestController
public class UserController {

    private Map<String, User> userMap;

    @PostConstruct
    public void initMap() {
        userMap = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            String uuid = UUID.randomUUID().toString();
            userMap.put(uuid, new User(uuid, "User" + i, "user" + i + "@gmail.com"));
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") String id) {
        User user = userMap.get(id);
        if (null == user) {
            throw new IllegalArgumentException("User not found for : " + id);
        }
        return user;
    }
}
