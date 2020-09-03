package com.square.health.repository;

import com.square.health.model.Comment;
import com.square.health.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
