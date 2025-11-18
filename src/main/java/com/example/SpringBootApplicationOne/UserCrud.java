package com.example.SpringBootApplicationOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/api")

public class UserCrud {
@Autowired

private UserRepository userRepository;



    @PostMapping("/create")
    public String createUser(@RequestBody User userData){
        User savedUser = userRepository.save(userData);

     return  "User created " + savedUser.getName() + "User Data "+ savedUser.toString() + "\n Available User(s): " + userRepository.count();


    }

    @GetMapping("/view")
    public List<User> viewUser(){
     return userRepository.findAll();

    }

    @GetMapping("/view/{id}")
    public String viewById(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        if (userRepository.existsById(id)){
            return "User at id "+ userRepository.findById(id);
        }else {
            return " No user found at id "+ id;
        }
    }


    @PutMapping("/update/{id}/{newName}")
    public String updateUser(@PathVariable Long id,@PathVariable String newName){
        Optional<User> existingUser = userRepository.findById(id);
        if (id >= 0 && id < userRepository.count()){
            User newUser = existingUser.get();
            String existingName = newUser.getName();
            newUser.setName(newName);
            return "User's name updated: " + existingName +" -> "+newName;
        }
        return " No user updated at id: " + id;
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
          userRepository.deleteById(id);
            return "User deleted: ";
        } else {
            return "No User was deleted: ";
        }


    }
}
