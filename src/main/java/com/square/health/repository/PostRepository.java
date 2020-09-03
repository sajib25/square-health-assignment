package com.square.health.repository;

import com.square.health.model.Post;
import com.square.health.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUserAndApprovedOrderByCreateDateDesc(User user, boolean approve, Pageable pageable);

    Page<Post> findAllByApprovedOrderByCreateDateDesc(boolean approved,Pageable pageable);

    Optional<Post> findById(Long id);
}
