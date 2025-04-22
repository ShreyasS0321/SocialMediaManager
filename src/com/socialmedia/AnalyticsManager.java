
import java.io.*;
import java.util.*;

public class AnalyticsManager implements AnalyticsFeatures, Serializable {
    private Map<String, Analytics> platformAnalytics;
    private String reportFormat;
    private Boolean isExportEnabled;

    // Overloaded constructors
    public AnalyticsManager() {
        this.platformAnalytics = new HashMap<>();
        this.isExportEnabled = false;
    }

    public AnalyticsManager(String reportFormat) {
        this();
        this.reportFormat = reportFormat;
    }

    // Implementation of AnalyticsFeatures interface
    @Override
    public void generateReport() {
        System.out.println("Generating analytics report...");
        // Implementation for report generation
    }

    @Override
    public void exportData(String format) {
        System.out.println("Exporting data in " + format + " format...");
        // Implementation for data export
    }

    @Override
    public void setTimeRange(String startDate, String endDate) {
        System.out.println("Setting time range from " + startDate + " to " + endDate);
        // Implementation for setting time range
    }

    // Overloaded methods
    public void addAnalytics(String platform, Analytics analytics) {
        platformAnalytics.put(platform, analytics);
    }

    public void addAnalytics(Map<String, Analytics> analyticsMap) {
        platformAnalytics.putAll(analyticsMap);
    }

    // File handling with exception
    public void saveAnalyticsToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(platformAnalytics);
        } catch (IOException e) {
            System.err.println("Error saving analytics: " + e.getMessage());
        }
    }

    // Nested class for analytics visualization
    public static class AnalyticsVisualizer {
        private String chartType;
        private Integer dataPoints;

        public AnalyticsVisualizer(String chartType, Integer dataPoints) {
            this.chartType = chartType;
            this.dataPoints = dataPoints;
        }

        public void generateChart() {
            System.out.println("Generating " + chartType + " chart with " + dataPoints + " data points");
        }
    }
} 