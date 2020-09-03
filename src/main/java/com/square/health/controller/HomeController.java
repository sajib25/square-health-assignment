package com.square.health.controller;

import com.square.health.model.Like;
import com.square.health.model.Post;
import com.square.health.model.User;
import com.square.health.service.LikeService;
import com.square.health.service.PostService;
import com.square.health.service.UserService;
import com.square.health.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
public class HomeController {

    private final PostService postService;
    private final UserService userService;
    private final LikeService likeService;
    @Autowired
    public HomeController(PostService postService, UserService userService, LikeService likeService) {
        this.postService = postService;
        this.userService = userService;
        this.likeService = likeService;
    }

    @GetMapping("/home")
    public String home(@RequestParam(defaultValue = "0") int page,
                       Model model) {

        Page<Post> posts = postService.findAllByApprovedOrderedByDatePageable(page);
        Pager pager = new Pager(posts);

        model.addAttribute("pager", pager);
        Integer approvePage = 0;
        Integer pageLike = 1;
        model.addAttribute("approvePage",approvePage);
        model.addAttribute("pageLike",pageLike);
        return "/home";
    }

    @RequestMapping(value = "/like/{id}", method = RequestMethod.POST)
    public String like(@PathVariable Long id,
                                    Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        Optional<Post> optionalPost = postService.findForId(id);

        if (optionalPost.isPresent() && user.isPresent()) {
            Post post = optionalPost.get();
            Optional<Like> like1 = post.getLikes().stream().filter(p->p.getUser().equals(user.get())).findFirst();
            if(like1.isPresent()){
                likeService.delete(like1.get());
            }else{
                Like like = new Like();
                like.setPost(post);
                like.setUser(user.get());
                likeService.save(like);
            }

            //postService.save(post);
            return "redirect:/home";


        } else {
            return "/error";
        }
    }

}
