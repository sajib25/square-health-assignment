package com.square.health.service;

import com.square.health.model.Post;
import com.square.health.model.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PostService {

    Optional<Post> findForId(Long id);

    Post save(Post post);

    /**
     * Finds a {@link Page) of {@link Post} of provided user ordered by date
     */
    Page<Post> findByUserOrderedByDatePageable(User user, int page);

    /**
     * Finds a {@link Page) of all {@link Post} ordered by date
     */
    Page<Post> findAllByApprovedOrderedByDatePageable(int page);

    Page<Post> findListToApproveOrReject(int page);

    void delete(Post post);
}
