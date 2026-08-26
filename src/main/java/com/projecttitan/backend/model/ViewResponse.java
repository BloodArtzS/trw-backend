package com.projecttitan.backend.model;

public class ViewResponse {
    private int chapterId;
    private long views;

    public ViewResponse() {}

    public ViewResponse(int chapterId, long views) {
        this.chapterId = chapterId;
        this.views = views;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }
}