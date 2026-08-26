package com.projecttitan.backend.service;

import com.projecttitan.backend.model.ChapterView;
import com.projecttitan.backend.model.ViewLog;
import com.projecttitan.backend.repositories.ChapterViewRepository;
import com.projecttitan.backend.repositories.ViewLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ViewService {

    private final ChapterViewRepository chapterViewRepository;
    private final ViewLogRepository viewLogRepository;

    public ViewService(ChapterViewRepository chapterViewRepository, ViewLogRepository viewLogRepository) {
        this.chapterViewRepository = chapterViewRepository;
        this.viewLogRepository = viewLogRepository;
    }

    @Transactional
    public long registerView(int chapterId, String visitorIp) {
        LocalDateTime cutoffTime = LocalDateTime.now().minusHours(24);
        Optional<ViewLog> recentLog = viewLogRepository.findTopByChapterIdAndVisitorIpOrderByViewedAtDesc(chapterId, visitorIp);

        // If never visited before or visited more than 24 hours ago, increment total
        if (recentLog.isEmpty() || recentLog.get().getViewedAt().isBefore(cutoffTime)) {
            viewLogRepository.save(new ViewLog(chapterId, visitorIp, LocalDateTime.now()));

            ChapterView chapterView = chapterViewRepository.findById(chapterId)
                    .orElse(new ChapterView(chapterId, 0L));

            chapterView.setTotalViews(chapterView.getTotalViews() + 1);
            chapterViewRepository.save(chapterView);

            return chapterView.getTotalViews();
        }

        // Return current count without incrementing if within 24 hours
        return getViews(chapterId);
    }

    public long getViews(int chapterId) {
        return chapterViewRepository.findById(chapterId)
                .map(ChapterView::getTotalViews)
                .orElse(0L);
    }
}