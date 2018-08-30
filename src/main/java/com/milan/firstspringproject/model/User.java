package com.milan.firstspringproject.model;
import java.util.UUID;
public class User {

    private UUID userUid;
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final int age;
    private final String email;

    public User(UUID userUid, String firstName, String lastName, Gender gender, int age, String email) {
        this.userUid = userUid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }

    public UUID getUserUid() {
        return userUid;
    }

    public void setUserUid(UUID userId){
        this.userUid=userId;
    }
    public String getFirstName() {
        return firstName;
    }

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
