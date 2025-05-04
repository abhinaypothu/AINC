package com.example.controller;

import com.example.model.Post;
import com.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    @GetMapping("/posts/bulkDelete")
    public String bulkDeletePosts(@RequestParam("ids") String ids) {
        // Split the comma-separated string of IDs into a list of Longs
        List<String> idList = Arrays.asList(ids.split(","));
        for (String id : idList) {
            try {
                postService.deletePost(Long.parseLong(id));
            } catch (NumberFormatException e) {
                // Handle the exception if the ID is not a valid number
                System.err.println("Invalid post ID: " + id);
            }
        }
        return "redirect:/posts";
    }

    @GetMapping("/posts/view/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "view";
    }

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public String listPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/new")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post";
    }

    @PostMapping("/posts")
    public String savePost(@ModelAttribute("post") Post post) {
        postService.savePost(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return "redirect:/posts";
        }
        model.addAttribute("post", post);
        return "post";
    }

    @PostMapping("/posts/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute("post") Post post) {
        Post updatedPost = postService.updatePost(id, post);
        if (updatedPost == null) {
            return "redirect:/posts";
        }
        return "redirect:/posts";
    }

    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
