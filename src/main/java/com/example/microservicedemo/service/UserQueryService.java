package com.example.microservicedemo.service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.microservicedemo.model.User;
import com.example.microservicedemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryService implements GraphQLQueryResolver {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String id) {
        Optional<User> result = userRepository.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    public List<User> getUsers() {
        List<User> result = userRepository.findAll();
        return result;
    }
}
