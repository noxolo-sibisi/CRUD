package com.example.SpringBootApplicationOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {
    @Autowired
    private UserDataCleaner userDataCleaner;

    public void RequiredUserFields(User user){
        User cleanedUser = userDataCleaner.cleanUser(user);
        if (cleanedUser.getName() == null || cleanedUser.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Name is required");
        }
        if (user.getAge() < 0){
            throw new IllegalArgumentException("Age can't be negative");
        }
        if (user.getAge() < 18){
            throw new IllegalArgumentException("You're under age");
        }
        if (user.getAge() > 100){
            throw new IllegalArgumentException("Age should be realistic");
        }
        if (cleanedUser.getCity() == null || cleanedUser.getCity().trim().isEmpty()){
            throw new IllegalArgumentException("City is required");
        }

    }

    private  void ValidateAge(User user,Integer age){
        if (user.getAge() < 0){
            throw new IllegalArgumentException("Age should not be negative");
        }
        if (user.getAge() < 18){
            throw new IllegalArgumentException("You're under age");
        }
        if (user.getAge() > 100){
            throw new IllegalArgumentException("Age should be realistic");
        }
    }


}
