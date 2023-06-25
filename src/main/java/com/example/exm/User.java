package com.example.exm;

import com.gluonhq.charm.glisten.control.Avatar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class User implements Serializable {
    private String username;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private String bio;
    private String location;
    private String website;
    public HashSet<String> followers;
    public HashMap<String, Following> following;
    public HashMap<Integer, Tweet> tweets;
    public ArrayList<Comment> comments;

    public HashMap<Integer, Tweet> likes;
    private Image avatar;
    private Image header;
    private String birthDate;
    private LocalDateTime joinDate;
    private Gender gender;
    private String password;

    public User() {
        followers = new HashSet<>();
        following = new HashMap<>();
        tweets = new HashMap<>();
        comments = new ArrayList<>();
        gender = Gender.UNKNOWN;
        joinDate = LocalDateTime.MIN;
    }

    public User(String firstName, String lastName, String username, String password, LocalDateTime joinDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        followers = new HashSet<>();
        following = new HashMap<>();
        tweets = new HashMap<>();
        comments = new ArrayList<>();
        gender = Gender.UNKNOWN;
        this.joinDate = joinDate;
    }

    public User(String firstName, String lastName, String username, LocalDateTime joinDate, String password, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        followers = new HashSet<>();
        following = new HashMap<>();
        tweets = new HashMap<>();
        comments = new ArrayList<>();
        gender = Gender.UNKNOWN;
        this.joinDate = joinDate;
        this.birthDate = birthDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public Image getHeader() {
        return header;
    }

    public void setHeader(Image header) {
        this.header = header;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "id: " + ((phoneNumber == null) ? email : phoneNumber) + "\n"
                + "Name: " + firstName + " " + lastName + "\n"
                + "username: " + username;
    }

}