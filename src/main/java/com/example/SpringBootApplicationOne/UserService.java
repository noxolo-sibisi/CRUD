package com.example.SpringBootApplicationOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {

        if (user.getName() == null || user.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Name is required");
        }
        if (user.getAge() < 18){
            throw new IllegalArgumentException("You're under age");
        }
        if(user.getCity() == null || user.getCity().trim().isEmpty()){
            throw new IllegalArgumentException("City is required");
        }

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public List<User> getAllUsers() {
        if (userRepository.findAll().isEmpty()) {
            throw new IllegalArgumentException("No User records found");
        }
        return userRepository.findAll();
    }

    public User getUserById(Long id) {

        if (id == null || id <= 0){
            throw new IllegalArgumentException("User ID must be positive");
        }
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new IllegalArgumentException("No User was found at ID: " + id);
        }
        return user.get();

    }

    public User updateUser(Long id, User user) {
        if (id == null || id <= 0){
            throw new IllegalArgumentException("User ID must be positive");
        }
        Optional<User> existingUser = userRepository.findById(id);
        if(existingUser.isEmpty()){
            throw new IllegalArgumentException("No User found at ID: "+ id);
        }
        User userFound = existingUser.get();
        if (user.getName() == null || user.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Name is required");
        }
        userFound.setName(user.getName());
        if (user.getAge() < 18){
            throw new IllegalArgumentException("You're under age");
        }
        userFound.setAge(user.getAge());
        if(user.getCity() == null || user.getCity().trim().isEmpty()){
            throw new IllegalArgumentException("City is required");
        }
        userFound.setCity(user.getCity());
        User updatedUser = userRepository.save(userFound);
        return updatedUser;

    }

    public long count() {
        return userRepository.count();
    }

    public String deleteUserById(Long id) {
        if (id == null || id <= 0){
            throw new IllegalArgumentException("User ID must be positive");
        }
        Optional<User> findId = userRepository.findById(id);
        if (findId.isEmpty()){
            throw new IllegalArgumentException("No User found at ID: "+id);
        }
        User userFound = findId.get();
        userRepository.deleteById(id);
        return "User "+ userFound.getName() + " at ID: "+ id +" deleted ";



    }
}