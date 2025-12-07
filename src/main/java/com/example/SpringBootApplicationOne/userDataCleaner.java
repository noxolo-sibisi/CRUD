package com.example.SpringBootApplicationOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataCleaner {
     private  String cleaning(String name){
         String trimmed = name.trim();
         String[] words = trimmed.split(" ");
         StringBuilder builder = new StringBuilder();
         for (String word : words) {
             if (!word.isEmpty()){
                 String cleanWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                 builder.append(cleanWord).append(" ");
             }
         }
         return builder.toString().trim();
     }

     public String cleanName(String rawName){
      return cleaning(rawName);
    }
    public String cleanCity(String rawCityName){
       return cleaning(rawCityName);
    }
    public Integer cleanAge(Integer age){
         return age;
    }
    public User cleanUser(User user){
        User cleanUser = new User();
        cleanUser.setName(cleanName(user.getName()));
        cleanUser.setCity(cleanCity(user.getCity()));
        cleanUser.setAge(user.getAge());
        return cleanUser;
    }
}
