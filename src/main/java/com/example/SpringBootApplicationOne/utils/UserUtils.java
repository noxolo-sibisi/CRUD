package com.example.SpringBootApplicationOne.utils;

import com.example.SpringBootApplicationOne.user.User;

public class UserUtils {
    public static void RequiredUserFields(User user) {
    }

    public static  void ValidateAge(User user,Integer age){
        if (user.getAge() < 0){
            throw new IllegalArgumentException("Age can't be negative");
        }
    }
}
