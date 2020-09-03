package com.square.health.service;

import com.square.health.model.Like;

public interface LikeService {

    void save(Like like);
    void delete(Like like);

}
