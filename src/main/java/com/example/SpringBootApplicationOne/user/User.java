package com.example.SpringBootApplicationOne.user;

import  jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 characters")
    @Column(name = "name",nullable = false)
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 17, message = "You're under age")
    @Max(value = 120, message = "Age should be realistic")
    @Column(name = "age")
    private int age;

    @NotBlank(message = "City is required")
    @Size(min = 2, max = 50, message = "City mist be 2-50 characters")
    @Column(name = "city", nullable = false)
    private String city;

    public User() {}

    public User(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = cleanedData(name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = cleanedData(city);
    }

    @Override
    public String toString() {
        return "User "+name+" created { "
                + "name= '" + name + '\'' +
                "| age= " + age +
                "| city= '" +city + '\'' +
                '}';
    }

}
