import java.util.ArrayList;
import java.util.List;

public class ContentCreator extends User implements ContentManagement {
    private List<ContentCategory> contentCategories;
    private int totalPosts;
    private int totalLikes;
    private List<String> followers;

    // Nested class for content categories
    public static class ContentCategory {
        private String name;
        private String description;
        private int postCount;

        public ContentCategory(String name) {
            this.name = name;
            this.postCount = 0;
        }

        public ContentCategory(String name, String description) {
            this(name);
            this.description = description;
        }

        public void incrementPostCount() {
            postCount++;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public int getPostCount() {
            return postCount;
        }
    }

    // Overloaded constructors
    public ContentCreator(String username, String email, String password) {
        super(username, email, password, UserRole.CONTENT_CREATOR);
        this.contentCategories = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.totalPosts = 0;
        this.totalLikes = 0;
    }

    public ContentCreator(String username, String email, String password, String... initialCategories) {
        this(username, email, password);
        for (String category : initialCategories) {
            addContentCategory(category);
        }
    }

    // Overloaded methods for content categories
    public void addContentCategory(String category) {
        contentCategories.add(new ContentCategory(category));
    }

    public void addContentCategory(String category, String description) {
        contentCategories.add(new ContentCategory(category, description));
    }

    public void addContentCategories(String... categories) {
        for (String category : categories) {
            addContentCategory(category);
        }
    }

    // Overloaded methods for followers
    public void addFollower(String username) {
        followers.add(username);
    }

    public void addFollowers(String... usernames) {
        for (String username : usernames) {
            followers.add(username);
        }
    }

    public void removeFollower(String username) {
        followers.remove(username);
    }

    public void removeFollowers(String... usernames) {
        for (String username : usernames) {
            followers.remove(username);
        }
    }

    public void incrementPostCount() {
        totalPosts++;
    }

    public void addLikes(int likes) {
        totalLikes += likes;
    }

    public List<ContentCategory> getContentCategories() {
        return contentCategories;
    }

    public int getTotalPosts() {
        return totalPosts;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public int getFollowerCount() {
        return followers.size();
    }

    // Implementation of ContentManagement interface
    @Override
    public void scheduleContent(String content, String platform) {
        System.out.println("Scheduling content for " + platform + ": " + content);
    }

    @Override
    public void scheduleContent(String content, String platform, String... hashtags) {
        System.out.println("Scheduling content for " + platform + " with hashtags: " + content);
        for (String hashtag : hashtags) {
            System.out.println("Hashtag: " + hashtag);
        }
    }

    @Override
    public void scheduleContent(String content, String platform, String[] hashtags, String[] mentions) {
        System.out.println("Scheduling content for " + platform + " with hashtags and mentions: " + content);
        for (String hashtag : hashtags) {
            System.out.println("Hashtag: " + hashtag);
        }
        for (String mention : mentions) {
            System.out.println("Mention: @" + mention);
        }
    }

    @Override
    public void publishContent(String content) {
        System.out.println("Publishing content: " + content);
        incrementPostCount();
    }

    @Override
    public void publishContent(String content, String... platforms) {
        System.out.println("Publishing content to multiple platforms: " + content);
        for (String platform : platforms) {
            System.out.println("Platform: " + platform);
        }
        incrementPostCount();
    }

    @Override
    public void deleteContent(String contentId) {
        System.out.println("Deleting content with ID: " + contentId);
    }

    @Override
    public void deleteContent(String contentId, String platform) {
        System.out.println("Deleting content with ID: " + contentId + " from platform: " + platform);
    }

    @Override
    public void editContent(String contentId, String newContent) {
        System.out.println("Editing content with ID: " + contentId + " to: " + newContent);
    }

    @Override
    public void editContent(String contentId, String newContent, String... hashtags) {
        System.out.println("Editing content with ID: " + contentId + " to: " + newContent);
        for (String hashtag : hashtags) {
            System.out.println("New hashtag: " + hashtag);
        }
    }

    @Override
    public String toString() {
        return "ContentCreator{" +
                "username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", contentCategories=" + contentCategories +
                ", totalPosts=" + totalPosts +
                ", totalLikes=" + totalLikes +
                ", followers=" + followers.size() +
                '}';
    }
} 