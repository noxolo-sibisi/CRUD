package com.example.SpringBootApplicationOne;

import  jakarta.persistence.*;
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "city")
    private String city;

    public User() {}

    public User(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public User(String name) {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.city = city;
    }

    @Override
    public String toString() {
        return "User at ID :{ " +
                " user ID: "+ id + "| "+
                "name= '" + name + '\'' +
                "| age= " + age +
                "| city= '" + city + '\'' +
                '}';
    }
}
