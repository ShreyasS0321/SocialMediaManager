package com.socialmedia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ContentScheduler {
    private List<Post> scheduledPosts;
    private Timer scheduler;
    private boolean isAutoPostingEnabled;

    // Overloaded constructors
    public ContentScheduler() {
        this.scheduler = new Timer();
        this.isAutoPostingEnabled = false;
        this.scheduledPosts = new ArrayList<>();
    }

    public ContentScheduler(boolean autoPostingEnabled) {
        this();
        this.isAutoPostingEnabled = autoPostingEnabled;
    }

    // Overloaded methods
    public void schedulePost(Post post) {
        scheduledPosts.add(post);
        if (isAutoPostingEnabled) {
            schedulePostExecution(post);
        }
    }

    public void schedulePosts(Post... posts) {
        for (Post post : posts) {
            schedulePost(post);
        }
    }

    private void schedulePostExecution(Post post) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Execute post
                post.setPublished(true);
            }
        };
        scheduler.schedule(task, java.sql.Timestamp.valueOf(post.getScheduledTime()));
    }

    // Getters and setters
    public List<Post> getScheduledPosts() {
        return scheduledPosts;
    }

    public boolean isAutoPostingEnabled() {
        return isAutoPostingEnabled;
    }

    public void setAutoPostingEnabled(boolean autoPostingEnabled) {
        isAutoPostingEnabled = autoPostingEnabled;
    }

    // Nested class for scheduling exceptions
    public static class SchedulingException extends Exception {
        public SchedulingException(String message) {
            super(message);
        }
    }
} 