package com.projecttitan.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chapter_views")
public class ChapterView {

    @Id
    private Integer chapterId;
    private long totalViews;

    public ChapterView() {}

    public ChapterView(Integer chapterId, long totalViews) {
        this.chapterId = chapterId;
        this.totalViews = totalViews;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public long getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(long totalViews) {
        this.totalViews = totalViews;
    }
}