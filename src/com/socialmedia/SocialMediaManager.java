
package com.socialmedia;

import java.io.*;
import java.util.*;

public class SocialMediaManager {
    private List<User> users;
    private List<SocialMediaPlatform> platforms;
    private ContentScheduler scheduler;
    private Map<String, Analytics> platformAnalytics;

    // Overloaded constructors
    public SocialMediaManager() {
        this.users = new ArrayList<>();
        this.platforms = new ArrayList<>();
        this.scheduler = new ContentScheduler();
        this.platformAnalytics = new HashMap<>();
    }

    public SocialMediaManager(boolean autoPostingEnabled) {
        this();
        this.scheduler = new ContentScheduler(autoPostingEnabled);
    }

    // Overloaded methods
    public void addUser(User user) {
        users.add(user);
    }

    public void addUsers(User... users) {
        for (User user : users) {
            this.users.add(user);
        }
    }

    // File handling methods
    public void saveUserData(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
        }
    }

    public void loadUserData(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            users = (List<User>) ois.readObject();
        }
    }

    // Exception handling
    public void handlePlatformConnection(SocialMediaPlatform platform) {
        try {
            platform.authenticate();
        } catch (Exception e) {
            System.err.println("Failed to connect to platform: " + e.getMessage());
        }
    }

    public void handlePostScheduling(Post post) {
        try {
            scheduler.schedulePost(post);
        } catch (Exception e) {
            System.err.println("Failed to schedule post: " + e.getMessage());
        }
    }

  
    public static void main(String[] args) {
        SocialMediaManager manager = new SocialMediaManager(true);
        
        User admin = new User("admin", "admin@example.com", "password", User.UserRole.ADMIN);
        manager.addUser(admin);

        try {
            manager.saveUserData("users.dat");
        } catch (IOException e) {
            System.err.println("Failed to save user data: " + e.getMessage());
        }
    }
} 