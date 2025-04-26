package com.socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private List<String> managedUsers;
    private List<String> systemLogs;
    private boolean isSuperAdmin;

    // Nested class for system logs
    public static class SystemLog {
        private String action;
        private String timestamp;
        private String details;

        public SystemLog(String action, String details) {
            this.action = action;
            this.timestamp = java.time.LocalDateTime.now().toString();
            this.details = details;
        }

        public String getAction() {
            return action;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public String getDetails() {
            return details;
        }

        @Override
        public String toString() {
            return "[" + timestamp + "] " + action + ": " + details;
        }
    }

    // Overloaded constructors
    public Admin(String username, String email, String password) {
        super(username, email, password, UserRole.ADMIN);
        this.managedUsers = new ArrayList<>();
        this.systemLogs = new ArrayList<>();
        this.isSuperAdmin = false;
    }

    public Admin(String username, String email, String password, boolean isSuperAdmin) {
        this(username, email, password);
        this.isSuperAdmin = isSuperAdmin;
    }

    // Overloaded methods for user management
    public void addManagedUser(String username) {
        managedUsers.add(username);
        logAction("ADD_USER", "Added user: " + username);
    }

    public void addManagedUsers(String... usernames) {
        for (String username : usernames) {
            addManagedUser(username);
        }
    }

    public void removeManagedUser(String username) {
        managedUsers.remove(username);
        logAction("REMOVE_USER", "Removed user: " + username);
    }

    public void removeManagedUsers(String... usernames) {
        for (String username : usernames) {
            removeManagedUser(username);
        }
    }

    // Overloaded methods for system logging
    public void logAction(String action, String details) {
        SystemLog log = new SystemLog(action, details);
        systemLogs.add(log.toString());
    }

    public void logAction(String action, String details, String severity) {
        SystemLog log = new SystemLog(action, details);
        systemLogs.add("[" + severity + "] " + log.toString());
    }

    // Admin-specific methods
    public void promoteToSuperAdmin() {
        if (!isSuperAdmin) {
            isSuperAdmin = true;
            logAction("PROMOTE_ADMIN", "Promoted to super admin");
        }
    }

    public void demoteFromSuperAdmin() {
        if (isSuperAdmin) {
            isSuperAdmin = false;
            logAction("DEMOTE_ADMIN", "Demoted from super admin");
        }
    }

    public boolean canManageUser(String username) {
        return isSuperAdmin || managedUsers.contains(username);
    }

    public List<String> getManagedUsers() {
        return new ArrayList<>(managedUsers);
    }

    public List<String> getSystemLogs() {
        return new ArrayList<>(systemLogs);
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", isSuperAdmin=" + isSuperAdmin +
                ", managedUsers=" + managedUsers.size() +
                ", systemLogs=" + systemLogs.size() +
                '}';
    }
} 