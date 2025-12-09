package com.example.SpringBootApplicationOne.utils;

import com.example.SpringBootApplicationOne.user.User;

public class UserUtils {
    public static void RequiredUserFields(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty()){
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
        if (user.getCity() == null || user.getCity().trim().isEmpty()){
            throw new IllegalArgumentException("City is required");
        }
    }

    public static  void ValidateAge(User user,Integer age){
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
