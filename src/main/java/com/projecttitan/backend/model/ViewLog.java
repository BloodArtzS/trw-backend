package com.projecttitan.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "view_logs", indexes = {
        @Index(name = "idx_chapter_ip_time", columnList = "chapterId, visitorIp, viewedAt")
})
public class ViewLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer chapterId;
    private String visitorIp;
    private LocalDateTime viewedAt;

    public ViewLog() {}

    public ViewLog(Integer chapterId, String visitorIp, LocalDateTime viewedAt) {
        this.chapterId = chapterId;
        this.visitorIp = visitorIp;
        this.viewedAt = viewedAt;
    }

    public Long getId() {
        return id;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public String getVisitorIp() {
        return visitorIp;
    }

    public LocalDateTime getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(LocalDateTime viewedAt) {
        this.viewedAt = viewedAt;
    }
}