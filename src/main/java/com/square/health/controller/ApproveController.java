package com.square.health.controller;

import com.square.health.model.Post;
import com.square.health.service.PostService;
import com.square.health.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
public class ApproveController {

    private final PostService postService;

    @Autowired
    public ApproveController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/approve")
    public String approve(@RequestParam(defaultValue = "0") int page,
                       Model model) {

        Page<Post> posts = postService.findListToApproveOrReject(page);
        Pager pager = new Pager(posts);

        model.addAttribute("pager", pager);
        int approvePage = 1;
        model.addAttribute("approvePage",approvePage);
        return "/approve";
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.PUT)
    public String approvePostWithId(@PathVariable Long id,
                                    Principal principal) {

        Optional<Post> optionalPost = postService.findForId(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setApproved(true);

            postService.save(post);
            return "redirect:/approve";


        } else {
            return "/error";
        }
    }


    @RequestMapping(value = "/reject/{id}", method = RequestMethod.DELETE)
    public String rejectPostWithId(@PathVariable Long id,
                                   Principal principal) {

        Optional<Post> optionalPost = postService.findForId(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            postService.delete(post);
            return "redirect:/approve";


        } else {
            return "/error";
        }
    }


}
