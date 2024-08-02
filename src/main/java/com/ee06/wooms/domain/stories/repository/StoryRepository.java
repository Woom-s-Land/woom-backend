package com.ee06.wooms.domain.stories.repository;

import com.ee06.wooms.domain.stories.entity.Story;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {
    List<Story> findAllByWoomsId(Long woomsId, Pageable pageable);
}