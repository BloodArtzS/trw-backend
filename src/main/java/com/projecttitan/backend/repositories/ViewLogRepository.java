package com.projecttitan.backend.repositories;

import com.projecttitan.backend.model.ViewLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViewLogRepository extends JpaRepository<ViewLog, Long> {
    Optional<ViewLog> findTopByChapterIdAndVisitorIpOrderByViewedAtDesc(Integer chapterId, String visitorIp);
}