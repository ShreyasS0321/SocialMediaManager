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
    private boolean isPublished;
    private String author;
    private Set<String> likedBy;  // Set of usernames who liked the post

    // Overloaded constructors
    public Post(String content) {
        this.content = content;
        this.hashtags = new ArrayList<>();
        this.isPublished = false;
        this.likedBy = new HashSet<>();
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
    }

    public void unlike(String username) {
        likedBy.remove(username);
    }

    public boolean isLikedBy(String username) {
        return likedBy.contains(username);
    }

    public int getLikeCount() {
        return likedBy.size();
    }

    public Set<String> getLikedBy() {
        return new HashSet<>(likedBy);  // Return a copy to prevent external modification
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
} 