import java.util.ArrayList;
import java.util.List;

public class InstagramPlatform extends SocialMediaPlatform implements SocialMediaPlatform.PlatformFeatures {
    private boolean autoPostingEnabled;
    private String postingSchedule;
    private List<String> hashtagSuggestions;

    // Overloaded constructors
    public InstagramPlatform() {
        super("Instagram");
        this.hashtagSuggestions = new ArrayList<>();
    }

    public InstagramPlatform(String apiKey, String apiSecret) {
        super("Instagram", apiKey, apiSecret);
        this.hashtagSuggestions = new ArrayList<>();
    }

    @Override
    public void authenticate() {
        // Implementation for Instagram authentication
        System.out.println("Authenticating with Instagram...");
    }

    @Override
    public void postContent(Post post) {
        // Implementation for posting to Instagram
        System.out.println("Posting to Instagram: " + post.getContent());
    }

    @Override
    public Analytics getAnalytics() {
        // Implementation for getting Instagram analytics
        return new Analytics();
    }

    @Override
    public List<Post> getScheduledPosts() {
        return posts;
    }

    // Implementation of PlatformFeatures interface
    @Override
    public void enableAutoPosting() {
        autoPostingEnabled = true;
    }

    @Override
    public void disableAutoPosting() {
        autoPostingEnabled = false;
    }

    @Override
    public void setPostingSchedule(String schedule) {
        this.postingSchedule = schedule;
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
        private Integer duration; // Using Integer wrapper class
        private List<String> stickers;

        public InstagramStory(String mediaUrl, Integer duration) {
            this.mediaUrl = mediaUrl;
            this.duration = duration;
            this.stickers = new ArrayList<>();
        }

        public void addSticker(String sticker) {
            stickers.add(sticker);
        }
    }
} 