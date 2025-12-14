package com.example.SpringBootApplicationOne.utils;

import com.example.SpringBootApplicationOne.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserUtils {
    private String cleanedData(String name){
        String rawName = name.trim();
        String[] names = rawName.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : names) {
            if (!s.isEmpty()){
                String letters = s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
                stringBuilder.append(letters);
                stringBuilder.append(" ");
            }
        }
        String cleanedName = stringBuilder.toString().trim();
        return cleanedName;
    }

    public User  cleanUser(User user){
       User cleaning = new User();
       cleaning.setName(cleanedData(user.getName()));
       cleaning.setCity(cleanedData(user.getCity()));
       cleaning.setAge(user.getAge());
       return cleaning;
    }

    public static  void ValidateAge(User user,Integer age){
        if (user.getAge() < 0){
            throw new IllegalArgumentException("Age can't be negative");
        }
    }

}
