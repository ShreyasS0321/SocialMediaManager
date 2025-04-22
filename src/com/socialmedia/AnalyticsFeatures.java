

public interface AnalyticsFeatures {
    void generateReport();
    void exportData(String format);
    void setTimeRange(String startDate, String endDate);
} 