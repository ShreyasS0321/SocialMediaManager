import java.util.ArrayList;
import java.util.List;

public abstract class SocialMediaPlatform {
    protected String platformName;
    protected String apiKey;
    protected String apiSecret;
    protected List<Post> posts;

    // Overloaded constructors
    public SocialMediaPlatform(String platformName) {
        this.platformName = platformName;
        this.posts = new ArrayList<>();
    }

    public SocialMediaPlatform(String platformName, String apiKey, String apiSecret) {
        this(platformName);
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    // Abstract methods that must be implemented by subclasses
    public abstract void authenticate();
    public abstract void postContent(Post post);
    public abstract Analytics getAnalytics();
    public abstract List<Post> getScheduledPosts();

    // Common methods for all platforms
    public String getPlatformName() {
        return platformName;
    }

    // Nested interface for platform-specific features
    public interface PlatformFeatures {
        void enableAutoPosting();
        void disableAutoPosting();
        void setPostingSchedule(String schedule);
    }
} 