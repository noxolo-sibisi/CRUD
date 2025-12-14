package com.example.SpringBootApplicationOne.user;

import com.example.SpringBootApplicationOne.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserUtils userUtils;

    public User createUser(User user) {
        User cleanedUser = userUtils.cleanUser(user);
        return userRepository.save(cleanedUser);
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
        User cleanedUser = userUtils.cleanUser(user);
        if (id == null || id <= 0){
            throw new IllegalArgumentException("User ID must be positive");
        }
        Optional<User> existingUser = userRepository.findById(id);
        if(existingUser.isEmpty()){
            throw new IllegalArgumentException("No User found at ID: "+ id);
        }
        User userFound = existingUser.get();

        userFound.setName(user.getName());
        userFound.setAge(user.getAge());
        userFound.setCity(user.getCity());
        return userRepository.save(cleanedUser);
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