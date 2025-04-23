import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String USERS_FILE = "users.dat";
    private static final String POSTS_FILE = "posts.dat";
    private static List<User> users = new ArrayList<>();
    private static List<Post> posts = new ArrayList<>();
    private static User currentUser = null;
    private static SocialMediaManager socialMediaManager;
    private static InstagramPlatform instagram;

    // Hardcoded admin credentials
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private static final String ADMIN_EMAIL = "admin@socialmedia.com";

    public static void main(String[] args) {
        // Initialize social media components
        socialMediaManager = new SocialMediaManager(true);
        instagram = new InstagramPlatform("dummy_api_key", "dummy_api_secret");
        
        // Create hardcoded admin if not exists
        createHardcodedAdmin();
        
        loadData();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            if (currentUser == null) {
                showLoginMenu(scanner);
            } else {
                showMainMenu(scanner);
            }
        }
    }

    private static void createHardcodedAdmin() {
        // Check if admin already exists
        boolean adminExists = false;
        for (User user : users) {
            if (user.getUsername().equals(ADMIN_USERNAME)) {
                adminExists = true;
                break;
            }
        }

        // Create admin if not exists
        if (!adminExists) {
            Admin admin = new Admin(ADMIN_USERNAME, ADMIN_EMAIL, ADMIN_PASSWORD, true);
            users.add(admin);
            saveData();
        }
    }

    private static void showLoginMenu(Scanner scanner) {
        System.out.println("\n=== Social Media Management System ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Enter your choice (1-3): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                login(scanner);
                break;
            case 2:
                register(scanner);
                break;
            case 3:
                saveData();
                System.out.println("Exiting program...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void showMainMenu(Scanner scanner) {
        System.out.println("\n=== Welcome, " + currentUser.getUsername() + " ===");
        System.out.println("1. Create Post");
        System.out.println("2. View My Posts");
        System.out.println("3. View Analytics");
        System.out.println("4. Manage Social Media");
        System.out.println("5. Schedule Posts");
        
        // Add admin-specific options
        if (currentUser instanceof Admin) {
            System.out.println("6. View All Users");
            System.out.println("7. View User Posts");
            System.out.println("8. Manage Users");
            System.out.println("9. View System Logs");
            System.out.println("10. Logout");
            System.out.print("Enter your choice (1-10): ");
        } else {
            System.out.println("6. Logout");
            System.out.print("Enter your choice (1-6): ");
        }
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                createPost(scanner);
                break;
            case 2:
                viewMyPosts();
                break;
            case 3:
                viewAnalytics();
                break;
            case 4:
                manageSocialMedia(scanner);
                break;
            case 5:
                schedulePosts(scanner);
                break;
            case 6:
                if (currentUser instanceof Admin) {
                    viewAllUsers(scanner);
                } else {
                    currentUser = null;
                }
                break;
            case 7:
                if (currentUser instanceof Admin) {
                    viewUserPosts(scanner);
                } else {
                    System.out.println("Invalid choice!");
                }
                break;
            case 8:
                if (currentUser instanceof Admin) {
                    manageUsers(scanner);
                } else {
                    System.out.println("Invalid choice!");
                }
                break;
            case 9:
                if (currentUser instanceof Admin) {
                    viewSystemLogs(scanner);
                } else {
                    System.out.println("Invalid choice!");
                }
                break;
            case 10:
                if (currentUser instanceof Admin) {
                    currentUser = null;
                } else {
                    System.out.println("Invalid choice!");
                }
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void manageSocialMedia(Scanner scanner) {
        System.out.println("\n=== Social Media Management ===");
        System.out.println("1. Connect to Instagram");
        System.out.println("2. Configure Auto-posting");
        System.out.println("3. View Connected Platforms");
        System.out.print("Enter your choice (1-3): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                connectToInstagram(scanner);
                break;
            case 2:
                configureAutoPosting(scanner);
                break;
            case 3:
                viewConnectedPlatforms();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void connectToInstagram(Scanner scanner) {
        System.out.println("\n=== Connect to Instagram ===");
        System.out.print("Enter API Key: ");
        String apiKey = scanner.nextLine();
        System.out.print("Enter API Secret: ");
        String apiSecret = scanner.nextLine();
        
        instagram = new InstagramPlatform(apiKey, apiSecret);
        instagram.authenticate();
        currentUser.connectPlatform(instagram);
        System.out.println("Successfully connected to Instagram!");
    }

    private static void configureAutoPosting(Scanner scanner) {
        System.out.println("\n=== Configure Auto-posting ===");
        System.out.println("1. Enable Auto-posting");
        System.out.println("2. Disable Auto-posting");
        System.out.println("3. Set Posting Schedule");
        System.out.print("Enter your choice (1-3): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                instagram.enableAutoPosting();
                break;
            case 2:
                instagram.disableAutoPosting();
                break;
            case 3:
                System.out.print("Enter posting schedule (e.g., 'daily at 9:00 AM'): ");
                String schedule = scanner.nextLine();
                instagram.setPostingSchedule(schedule);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void viewConnectedPlatforms() {
        System.out.println("\n=== Connected Platforms ===");
        List<SocialMediaPlatform> platforms = currentUser.getConnectedPlatforms();
        if (platforms.isEmpty()) {
            System.out.println("No platforms connected.");
        } else {
            for (SocialMediaPlatform platform : platforms) {
                System.out.println("- " + platform.getPlatformName());
            }
        }
    }

    private static void schedulePosts(Scanner scanner) {
        System.out.println("\n=== Schedule Posts ===");
        System.out.print("Enter number of posts to schedule: ");
        int count = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        for (int i = 0; i < count; i++) {
            System.out.println("\nPost #" + (i + 1));
            System.out.print("Enter post content: ");
            String content = scanner.nextLine();
            System.out.print("Enter number of hashtags: ");
            int hashtagCount = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            List<String> hashtags = new ArrayList<>();
            for (int j = 0; j < hashtagCount; j++) {
                System.out.print("Enter hashtag #" + (j + 1) + ": ");
                hashtags.add(scanner.nextLine());
            }
            
            Post newPost = new Post(content, LocalDateTime.now().plusHours(1), hashtags);
            socialMediaManager.handlePostScheduling(newPost);
            posts.add(newPost);
        }
        saveData();
        System.out.println("\nPosts scheduled successfully!");
    }

    private static void login(Scanner scanner) {
        System.out.println("\n=== Login ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Login successful!");
                return;
            }
        }
        System.out.println("Invalid username or password!");
    }

    private static void register(Scanner scanner) {
        System.out.println("\n=== Register ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // Only show admin role option if current user is admin
        boolean isAdmin = currentUser != null && currentUser.getRole() == User.UserRole.ADMIN;
        
        System.out.println("Select role (1-3):");
        System.out.println("1. " + (isAdmin ? "Admin" : "Content Creator"));
        System.out.println("2. Content Creator");
        System.out.println("3. Marketing Analyst");
        System.out.print("Enter role number: ");
        int roleChoice = scanner.nextInt();
        
        User newUser;
        if (isAdmin && roleChoice == 1) {
            newUser = new Admin(username, email, password);
        } else {
            if (roleChoice == 1 || roleChoice == 2) {
                newUser = new ContentCreator(username, email, password);
                // Add default content categories for content creators
                ((ContentCreator) newUser).addContentCategory("General");
            } else {
                newUser = new User(username, email, password, User.UserRole.MARKETING_ANALYST);
            }
        }
        
        users.add(newUser);
        saveData();
        System.out.println("Registration successful!");
    }

    private static void createPost(Scanner scanner) {
        System.out.println("\n=== Create New Post ===");
        System.out.print("Enter post content: ");
        String content = scanner.nextLine();
        System.out.print("Enter number of hashtags: ");
        int hashtagCount = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        List<String> hashtags = new ArrayList<>();
        for (int i = 0; i < hashtagCount; i++) {
            System.out.print("Enter hashtag #" + (i + 1) + ": ");
            hashtags.add(scanner.nextLine());
        }
        
        Post newPost = new Post(content, LocalDateTime.now().plusHours(1), hashtags, currentUser.getUsername());
        
        // If user is a content creator, update their stats
        if (currentUser instanceof ContentCreator) {
            ContentCreator creator = (ContentCreator) currentUser;
            creator.incrementPostCount();
        }
        
        // Post to social media platform
        if (instagram != null) {
            instagram.postContent(newPost);
        }
        
        posts.add(newPost);
        saveData();
        System.out.println("\nPost created successfully!");
    }

    private static void viewMyPosts() {
        System.out.println("\n=== My Posts ===");
        int postNumber = 1;
        for (Post post : posts) {
            if (post.getAuthor().equals(currentUser.getUsername())) {
                System.out.println("Post #" + postNumber);
                System.out.println("Content: " + post.getContent());
                System.out.println("Hashtags: " + post.getHashtags());
                System.out.println("Scheduled Time: " + post.getScheduledTime());
                System.out.println("Likes: " + post.getLikeCount());
                System.out.println("-------------------");
                postNumber++;
            }
        }
        
        System.out.println("\nOptions:");
        System.out.println("1. Like a post");
        System.out.println("2. Return to main menu");
        System.out.print("Enter your choice (1-2): ");
        
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        if (choice == 1) {
            likePost(scanner, true);
        }
    }

    private static void viewAnalytics() {
        System.out.println("\n=== Analytics ===");
        
        // Calculate total likes across all posts
        int totalLikes = 0;
        for (Post post : posts) {
            totalLikes += post.getLikeCount();
        }
        
        Analytics analytics = new Analytics(totalLikes, 50, 25, 1000);
        System.out.println("Current Metrics:");
        System.out.println("Total Likes: " + analytics.getLikes());
        System.out.println("Shares: " + analytics.getShares());
        System.out.println("Comments: " + analytics.getComments());
        System.out.println("Followers: " + analytics.getFollowerCount());
        
        // Show most liked posts
        System.out.println("\nMost Liked Posts:");
        posts.sort((p1, p2) -> Integer.compare(p2.getLikeCount(), p1.getLikeCount()));
        for (int i = 0; i < Math.min(3, posts.size()); i++) {
            Post post = posts.get(i);
            System.out.println((i + 1) + ". " + post.getContent());
            System.out.println("   Likes: " + post.getLikeCount());
            System.out.println("   Author: " + post.getAuthor());
            System.out.println("-------------------");
        }

        // Show content creator specific analytics if user is a content creator
        if (currentUser instanceof ContentCreator) {
            ContentCreator creator = (ContentCreator) currentUser;
            System.out.println("\nContent Creator Analytics:");
            System.out.println("Total Posts: " + creator.getTotalPosts());
            System.out.println("Total Likes: " + creator.getTotalLikes());
            System.out.println("Followers: " + creator.getFollowerCount());
            System.out.println("Content Categories: " + creator.getContentCategories());
        }
    }

    private static void viewAllUsers(Scanner scanner) {
        System.out.println("\n=== All Users ===");
        System.out.printf("%-20s %-30s %-15s %-20s%n", "Username", "Email", "Role", "Additional Info");
        System.out.println("----------------------------------------------------------------------------");
        
        for (User user : users) {
            String additionalInfo = "";
            if (user instanceof ContentCreator) {
                ContentCreator creator = (ContentCreator) user;
                additionalInfo = "Posts: " + creator.getTotalPosts() + 
                               ", Likes: " + creator.getTotalLikes() + 
                               ", Followers: " + creator.getFollowerCount();
            }
            
            System.out.printf("%-20s %-30s %-15s %-20s%n", 
                user.getUsername(), 
                user.getEmail(), 
                user.getRole().toString(),
                additionalInfo);
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private static void viewUserPosts(Scanner scanner) {
        System.out.println("\n=== View User Posts ===");
        System.out.print("Enter username to view posts: ");
        String username = scanner.nextLine();
        
        User targetUser = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                targetUser = user;
                break;
            }
        }
        
        if (targetUser == null) {
            System.out.println("User not found!");
            return;
        }
        
        System.out.println("\nPosts by " + targetUser.getUsername() + ":");
        System.out.println("------------------------------------------------------------");
        
        boolean hasPosts = false;
        int postNumber = 1;
        for (Post post : posts) {
            if (post.getAuthor().equals(targetUser.getUsername())) {
                hasPosts = true;
                System.out.println("Post #" + postNumber);
                System.out.println("Content: " + post.getContent());
                System.out.println("Hashtags: " + post.getHashtags());
                System.out.println("Scheduled Time: " + post.getScheduledTime());
                System.out.println("Likes: " + post.getLikeCount());
                System.out.println("------------------------------------------------------------");
                postNumber++;
            }
        }
        
        if (!hasPosts) {
            System.out.println("No posts found for this user.");
        } else {
            System.out.println("\nOptions:");
            System.out.println("1. Like a post");
            System.out.println("2. Return to main menu");
            System.out.print("Enter your choice (1-2): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            if (choice == 1) {
                likePost(scanner, false);
            }
        }
    }

    private static void likePost(Scanner scanner, boolean isMyPosts) {
        System.out.println("\n=== Like a Post ===");
        System.out.print("Enter post number to like: ");
        int postNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        int currentNumber = 1;
        boolean found = false;
        
        for (Post post : posts) {
            if ((isMyPosts && post.getAuthor().equals(currentUser.getUsername())) ||
                (!isMyPosts && post.getAuthor().equals(currentUser.getUsername()))) {
                if (currentNumber == postNumber) {
                    if (post.isLikedBy(currentUser.getUsername())) {
                        System.out.println("You have already liked this post!");
                    } else {
                        post.like(currentUser.getUsername());
                        System.out.println("Post liked successfully!");
                    }
                    found = true;
                    break;
                }
                currentNumber++;
            }
        }
        
        if (!found) {
            System.out.println("Invalid post number!");
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private static void manageUsers(Scanner scanner) {
        if (!(currentUser instanceof Admin)) {
            System.out.println("Access denied!");
            return;
        }

        Admin admin = (Admin) currentUser;
        System.out.println("\n=== Manage Users ===");
        System.out.println("1. Add User to Manage");
        System.out.println("2. Remove User from Management");
        System.out.println("3. View Managed Users");
        System.out.print("Enter your choice (1-3): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter username to manage: ");
                String username = scanner.nextLine();
                admin.addManagedUser(username);
                break;
            case 2:
                System.out.print("Enter username to remove from management: ");
                username = scanner.nextLine();
                admin.removeManagedUser(username);
                break;
            case 3:
                System.out.println("\nManaged Users:");
                for (String managedUser : admin.getManagedUsers()) {
                    System.out.println("- " + managedUser);
                }
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void viewSystemLogs(Scanner scanner) {
        if (!(currentUser instanceof Admin)) {
            System.out.println("Access denied!");
            return;
        }

        Admin admin = (Admin) currentUser;
        System.out.println("\n=== System Logs ===");
        for (String log : admin.getSystemLogs()) {
            System.out.println(log);
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private static void loadData() {
        try {
            // Load users
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
                users = (List<User>) ois.readObject();
            }
            // Load posts
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(POSTS_FILE))) {
                posts = (List<Post>) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing data found. Starting with empty data.");
        }
    }

    private static void saveData() {
        try {
            // Save users
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
                oos.writeObject(users);
            }
            // Save posts
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(POSTS_FILE))) {
                oos.writeObject(posts);
            }
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
} 