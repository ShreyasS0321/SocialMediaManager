

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post implements Serializable {
    private String content;
    private LocalDateTime scheduledTime;
    private List<String> hashtags;
    private PostType type;
    private String mediaUrl;
    private boolean isPublished;

    // Overloaded constructors
    public Post(String content) {
        this.content = content;
        this.hashtags = new ArrayList<>();
        this.isPublished = false;
    }

    public Post(String content, LocalDateTime scheduledTime, List<String> hashtags) {
        this(content);
        this.scheduledTime = scheduledTime;
        this.hashtags = hashtags;
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

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    // Nested enum for post types
    public enum PostType {
        TEXT,
        IMAGE,
        VIDEO,
        LINK
    }
} 