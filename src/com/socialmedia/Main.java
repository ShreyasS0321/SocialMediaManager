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

    public static void main(String[] args) {
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
        System.out.println("4. Logout");
        System.out.print("Enter your choice (1-4): ");
        
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
                currentUser = null;
                break;
            default:
                System.out.println("Invalid choice!");
        }
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
        System.out.println("Select role (1-3):");
        System.out.println("1. Admin");
        System.out.println("2. Content Creator");
        System.out.println("3. Marketing Analyst");
        System.out.print("Enter role number: ");
        int roleChoice = scanner.nextInt();
        
        User.UserRole role = switch (roleChoice) {
            case 1 -> User.UserRole.ADMIN;
            case 2 -> User.UserRole.CONTENT_CREATOR;
            case 3 -> User.UserRole.MARKETING_ANALYST;
            default -> throw new IllegalArgumentException("Invalid role choice");
        };
        
        User newUser = new User(username, email, password, role);
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
        
        Post newPost = new Post(content, LocalDateTime.now().plusHours(1), hashtags);
        posts.add(newPost);
        saveData();
        System.out.println("\nPost created successfully!");
    }

    private static void viewMyPosts() {
        System.out.println("\n=== My Posts ===");
        for (Post post : posts) {
            System.out.println("Content: " + post.getContent());
            System.out.println("Hashtags: " + post.getHashtags());
            System.out.println("Scheduled Time: " + post.getScheduledTime());
            System.out.println("-------------------");
        }
    }

    private static void viewAnalytics() {
        System.out.println("\n=== Analytics ===");
        Analytics analytics = new Analytics(100, 50, 25, 1000);
        System.out.println("Current Metrics:");
        System.out.println("Likes: " + analytics.getLikes());
        System.out.println("Shares: " + analytics.getShares());
        System.out.println("Comments: " + analytics.getComments());
        System.out.println("Followers: " + analytics.getFollowerCount());
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