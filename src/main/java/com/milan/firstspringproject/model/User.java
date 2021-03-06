package com.milan.firstspringproject.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;
public class User {

    private final UUID userUid;
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final int age;
    private final String email;

    public User(@JsonProperty("userUid") UUID userUid,
                @JsonProperty("firstName")String firstName,
                @JsonProperty("lastName")String lastName,
                @JsonProperty("gender")Gender gender,
                @JsonProperty("age")int age,
                @JsonProperty("email")String email) {
        this.userUid = userUid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }

    @JsonProperty("id")
    public UUID getUserUid() {
        return userUid;
    }
    @JsonIgnore
    public String getFirstName() {
        return firstName;
    }
    @JsonIgnore
    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName(){
        return firstName +" "+lastName;
    }

    public int getDOB(){
        return LocalDate.now().minusYears(age).getYear();
    }
    public static User newUser(UUID userUid, User user){
        return new User(userUid, user.firstName,
                user.lastName,user.gender,user.age,user.email);
    }
    @Override
    public String toString() {
        return "User{" +
                "userUid=" + userUid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }

    public enum Gender{
        MALE,
        FEMALE
    }

}
