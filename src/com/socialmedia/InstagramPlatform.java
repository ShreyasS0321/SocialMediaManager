import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InstagramPlatform extends SocialMediaPlatform implements SocialMediaPlatform.PlatformFeatures {
    private boolean autoPostingEnabled;
    private String postingSchedule;
    private List<String> hashtagSuggestions;
    private int followerCount;
    private List<String> followers;
    private List<Post> stories;
    private List<Post> reels;

    // Overloaded constructors
    public InstagramPlatform() {
        super("Instagram");
        this.hashtagSuggestions = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.stories = new ArrayList<>();
        this.reels = new ArrayList<>();
        this.followerCount = 0;
    }

    public InstagramPlatform(String apiKey, String apiSecret) {
        super("Instagram", apiKey, apiSecret);
        this.hashtagSuggestions = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.stories = new ArrayList<>();
        this.reels = new ArrayList<>();
        this.followerCount = 0;
    }

    @Override
    public void authenticate() {
        System.out.println("Authenticating with Instagram...");
        System.out.println("API Key: " + apiKey);
        System.out.println("API Secret: " + apiSecret);
    }

    @Override
    public void postContent(Post post) {
        System.out.println("Posting to Instagram: " + post.getContent());
        System.out.println("Hashtags: " + post.getHashtags());
        
        // Simulate engagement
        Random random = new Random();
        int likes = random.nextInt(1000);
        int comments = random.nextInt(100);
        int shares = random.nextInt(50);
        
        System.out.println("Post Engagement:");
        System.out.println("Likes: " + likes);
        System.out.println("Comments: " + comments);
        System.out.println("Shares: " + shares);
    }

    @Override
    public Analytics getAnalytics() {
        Random random = new Random();
        return new Analytics(
            random.nextInt(1000),  // likes
            random.nextInt(100),   // shares
            random.nextInt(50),    // comments
            followerCount          // followers
        );
    }

    @Override
    public List<Post> getScheduledPosts() {
        return posts;
    }

    // Implementation of PlatformFeatures interface
    @Override
    public void enableAutoPosting() {
        autoPostingEnabled = true;
        System.out.println("Auto-posting enabled for Instagram");
    }

    @Override
    public void disableAutoPosting() {
        autoPostingEnabled = false;
        System.out.println("Auto-posting disabled for Instagram");
    }

    @Override
    public void setPostingSchedule(String schedule) {
        this.postingSchedule = schedule;
        System.out.println("Posting schedule set to: " + schedule);
    }

    // Instagram-specific methods
    public void addStory(Post story) {
        stories.add(story);
        System.out.println("Story added: " + story.getContent());
    }

    public void addReel(Post reel) {
        reels.add(reel);
        System.out.println("Reel added: " + reel.getContent());
    }

    public void followUser(String username) {
        followers.add(username);
        followerCount++;
        System.out.println("Followed user: " + username);
    }

    public void unfollowUser(String username) {
        followers.remove(username);
        followerCount--;
        System.out.println("Unfollowed user: " + username);
    }

    // Overloaded methods for hashtag suggestions
    public void addHashtagSuggestion(String hashtag) {
        hashtagSuggestions.add(hashtag);
    }

    public void addHashtagSuggestions(String... hashtags) {
        for (String hashtag : hashtags) {
            hashtagSuggestions.add(hashtag);
        }
    }

    // Nested class for Instagram-specific features
    public static class InstagramStory {
        private String mediaUrl;
        private Integer duration;
        private List<String> stickers;
        private List<String> mentions;
        private String location;
        private boolean isHighlight;

        public InstagramStory(String mediaUrl, Integer duration) {
            this.mediaUrl = mediaUrl;
            this.duration = duration;
            this.stickers = new ArrayList<>();
            this.mentions = new ArrayList<>();
            this.isHighlight = false;
        }

        public void addSticker(String sticker) {
            stickers.add(sticker);
        }

        public void addMention(String username) {
            mentions.add("@" + username);
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setHighlight(boolean isHighlight) {
            this.isHighlight = isHighlight;
        }
    }

    // Getter methods
    public int getFollowerCount() {
        return followerCount;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public List<Post> getStories() {
        return stories;
    }

    public List<Post> getReels() {
        return reels;
    }
} 