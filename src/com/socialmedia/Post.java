package com.socialmedia;

// wrappers here
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Post implements Serializable {
    private String content;
    private LocalDateTime scheduledTime;
    private List<String> hashtags;
    private PostType type;
    private String mediaUrl;
    private Boolean isPublished;
    private String author;
    private Set<String> likedBy;  
    private Integer viewCount;
    private Integer shareCount;
    private Integer commentCount;
    private Double engagementRate;

    // Overloaded constructors
    public Post(String content) {
        this.content = content;
        this.hashtags = new ArrayList<>();
        this.isPublished = false;
        this.likedBy = new HashSet<>();
        this.viewCount = 0;
        this.shareCount = 0;
        this.commentCount = 0;
        this.engagementRate = 0.0;
    }

    public Post(String content, LocalDateTime scheduledTime, List<String> hashtags) {
        this(content);
        this.scheduledTime = scheduledTime;
        this.hashtags = hashtags;
    }

    public Post(String content, LocalDateTime scheduledTime, List<String> hashtags, String author) {
        this(content, scheduledTime, hashtags);
        this.author = author;
    }

    // Overloaded methods
    public void addHashtag(String hashtag) {
        hashtags.add(hashtag);
    }

    public void addHashtags(String... hashtags) {
        for (String hashtag : hashtags) {
            this.hashtags.add(hashtag);
        }
    }

    // Like methods
    public void like(String username) {
        likedBy.add(username);
        updateEngagementRate();
    }

    public void unlike(String username) {
        likedBy.remove(username);
        updateEngagementRate();
    }

    public boolean isLikedBy(String username) {
        return likedBy.contains(username);
    }

    public Integer getLikeCount() {
        return likedBy.size();
    }

    public Set<String> getLikedBy() {
        return new HashSet<>(likedBy);  
    }

    // View methods
    public void incrementViewCount() {
        viewCount++;
        updateEngagementRate();
    }

    public Integer getViewCount() {
        return viewCount;
    }

    // Share methods
    public void incrementShareCount() {
        shareCount++;
        updateEngagementRate();
    }

    public Integer getShareCount() {
        return shareCount;
    }

    // Comment methods
    public void incrementCommentCount() {
        commentCount++;
        updateEngagementRate();
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    // Engagement rate calculation try again once to debug
    private void updateEngagementRate() {
        if (viewCount > 0) {
            Double totalEngagement = Double.valueOf(getLikeCount() + shareCount + commentCount);
            engagementRate = (totalEngagement / viewCount) * 100;
        }
    }

    public Double getEngagementRate() {
        return engagementRate;
    }

    // Getters and setters
    public String getContent() {
        return content;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public Boolean isPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Nested enum for post types
    public enum PostType {
        TEXT,
        IMAGE,
        VIDEO,
        LINK
    }

    @Override
    public String toString() {
        return "Post{" +
                "content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", likes=" + getLikeCount() +
                ", views=" + viewCount +
                ", shares=" + shareCount +
                ", comments=" + commentCount +
                ", engagementRate=" + String.format("%.2f", engagementRate) + "%" +
                '}';
    }
} 