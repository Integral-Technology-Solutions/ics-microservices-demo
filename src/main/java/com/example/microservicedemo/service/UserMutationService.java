package com.example.microservicedemo.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.microservicedemo.model.User;
import com.example.microservicedemo.model.UserInput;
import com.example.microservicedemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserMutationService implements GraphQLMutationResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserMutationService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    private User getUser(String id) {
        Optional<User> result = userRepository.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    public User createUser(UserInput userInput) {
        User user = new User();
        user.setName(userInput.getName());
        user.setId(UUID.randomUUID().toString());
        user.setActive(userInput.getActive());
        user.setEmail(userInput.getEmail());

        User result = userRepository.save(user);
        notificationService.sendWelcomeEmail(result);
        return result;
    }

    public User updateUser(UserInput userInput) {
        User result = getUser(userInput.getId());

        if (result == null) {
            return null;
        }

        result.setName(userInput.getName());
        result.setActive(userInput.getActive());
        result.setEmail(userInput.getEmail());

        return userRepository.save(result);
    }

    public List<User> updateUsers(List<UserInput> userInputs) {
        List<User> users = new ArrayList<>();

        for (UserInput userInput : userInputs) {
            User result = updateUser(userInput);
            if (result != null) {
                users.add(result);
            }
        }

        return users;
    }

    public User deleteUser(String id) {
        User result = getUser(id);

        if (result == null) {
            return null;
        }

        result.setActive(false);
        return userRepository.save(result);
    }

    public List<User> deleteUsers(List<String> ids) {
        List<User> users = new ArrayList<>();


        for (String id : ids) {
            User result = deleteUser(id);
            if (result != null) {
                users.add(result);
            }
        }

        return users;
    }


}
