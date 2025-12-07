package com.example.SpringBootApplicationOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDataCleaner userDataCleaner;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator userValidator;

    public User createUser(User user) {
        User cleanedUser = userDataCleaner.cleanUser(user);
        userValidator.RequiredUserFields(user);
        User savedUser = userRepository.save(cleanedUser);
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

        User cleanedUser = userDataCleaner.cleanUser(user);
        userValidator.RequiredUserFields(user);

        userFound.setName(cleanedUser.getName());
        userFound.setAge(cleanedUser.getAge());
        userFound.setCity(cleanedUser.getCity());
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