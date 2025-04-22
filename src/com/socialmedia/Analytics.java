import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Analytics implements Serializable {
    private int likes;
    private int shares;
    private int comments;
    private int followerCount;
    private Map<String, Integer> hashtagPerformance;
    private LocalDateTime analysisTime;

    // Overloaded constructors
    public Analytics() {
        this.analysisTime = LocalDateTime.now();
        this.hashtagPerformance = new HashMap<>();
    }

    public Analytics(int likes, int shares, int comments, int followerCount) {
        this();
        this.likes = likes;
        this.shares = shares;
        this.comments = comments;
        this.followerCount = followerCount;
    }

    // Overloaded methods
    public void updateMetrics(int likes, int shares) {
        this.likes += likes;
        this.shares += shares;
    }

    public void updateMetrics(int likes, int shares, int comments) {
        updateMetrics(likes, shares);
        this.comments += comments;
    }

    // Getters and setters
    public int getLikes() {
        return likes;
    }

    public int getShares() {
        return shares;
    }

    public int getComments() {
        return comments;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public Map<String, Integer> getHashtagPerformance() {
        return hashtagPerformance;
    }

    public LocalDateTime getAnalysisTime() {
        return analysisTime;
    }

    // Nested class for sentiment analysis
    public static class SentimentAnalysis {
        private double positiveScore;
        private double negativeScore;
        private double neutralScore;

        public SentimentAnalysis(double positiveScore, double negativeScore, double neutralScore) {
            this.positiveScore = positiveScore;
            this.negativeScore = negativeScore;
            this.neutralScore = neutralScore;
        }

        public double getPositiveScore() {
            return positiveScore;
        }

        public double getNegativeScore() {
            return negativeScore;
        }

        public double getNeutralScore() {
            return neutralScore;
        }
    }
} 