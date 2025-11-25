package com.example.SpringBootApplicationOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")

public class UserController{
@Autowired
private UserService userService;

    @PostMapping("/create")
    public String createUser(@RequestBody User userData){
        userService.createUser(userData);
        return  "User created " + userData.getName() + "User Data "+ userData.toString() + "\n Available User(s): " + userService.count();
    }

    @GetMapping("/view")
    public List<User> viewUser(){
     return userService.getAllUsers();
    }

    @GetMapping("/view/{id}")
    public User viewById(@PathVariable Long id){
      return userService.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser){
      return userService.updateUser(id, newUser);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
      return userService.deleteUserById(id);
    }
}
