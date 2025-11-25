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
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new IllegalArgumentException("No user records found at ID: " + id);
        }
    }

    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        if (user.getAge() < 18){
            throw new IllegalArgumentException("Age must be 18+ years old");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Name is required");
        }

        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());
        existingUser.setCity(user.getCity());

        return userRepository.save(existingUser);
    }

    public long count() {
        return userRepository.count();
    }

    public String deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User " + " at ID: " + id + " was successfully deleted";
        }
        throw new IllegalArgumentException("User "+ userRepository.existsById(id)+"at ID: " + id + " Not deleted");
    }
}