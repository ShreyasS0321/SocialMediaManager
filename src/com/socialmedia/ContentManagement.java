package com.socialmedia;

public interface ContentManagement {
    void scheduleContent(String content, String platform);
    void scheduleContent(String content, String platform, String... hashtags);
    void scheduleContent(String content, String platform, String[] hashtags, String[] mentions);
    
    void publishContent(String content);
    void publishContent(String content, String... platforms);
    
    void deleteContent(String contentId);
    void deleteContent(String contentId, String platform);
    
    void editContent(String contentId, String newContent);
    void editContent(String contentId, String newContent, String... hashtags);
} 