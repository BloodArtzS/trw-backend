package com.projecttitan.backend.repositories;

import com.projecttitan.backend.model.ChapterView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterViewRepository extends JpaRepository<ChapterView, Integer> {
}