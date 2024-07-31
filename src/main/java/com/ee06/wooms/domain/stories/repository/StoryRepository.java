package com.ee06.wooms.domain.stories.repository;

import com.ee06.wooms.domain.stories.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
