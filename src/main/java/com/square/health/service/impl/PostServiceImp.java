package com.square.health.service.impl;

import com.square.health.model.Post;
import com.square.health.model.User;
import com.square.health.repository.PostRepository;
import com.square.health.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Optional<Post> findForId(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.saveAndFlush(post);
    }

    @Override
    public Page<Post> findByUserOrderedByDatePageable(User user, int page) {
        return postRepository.findByUserAndApprovedOrderByCreateDateDesc(user,true, new PageRequest(subtractPageByOne(page), 5));
    }

    @Override
    public Page<Post> findAllByApprovedOrderedByDatePageable(int page) {
        return postRepository.findAllByApprovedOrderByCreateDateDesc(true,new PageRequest(subtractPageByOne(page), 5));
    }

    @Override
    public Page<Post> findListToApproveOrReject(int page) {
        return postRepository.findAllByApprovedOrderByCreateDateDesc(false,new PageRequest(subtractPageByOne(page), 5));
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    private int subtractPageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }
}
