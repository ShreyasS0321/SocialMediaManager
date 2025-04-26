package com.socialmedia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String username;
    private String email;
    private String password;
    private UserRole role;
    private List<SocialMediaPlatform> connectedPlatforms;

    // Overloaded constructors
    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.connectedPlatforms = new ArrayList<>();
    }

    public User(String username, String email, String password, UserRole role) {
        this(username, email);
        this.password = password;
        this.role = role;
    }

    // Overloaded methods
    public void connectPlatform(SocialMediaPlatform platform) {
        connectedPlatforms.add(platform);
    }

    public void connectPlatform(SocialMediaPlatform... platforms) {
        for (SocialMediaPlatform platform : platforms) {
            connectedPlatforms.add(platform);
        }
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public List<SocialMediaPlatform> getConnectedPlatforms() {
        return connectedPlatforms;
    }

    // Nested enum for user roles
    public enum UserRole {
        ADMIN,
        CONTENT_CREATOR,
        MARKETING_ANALYST
    }
} 