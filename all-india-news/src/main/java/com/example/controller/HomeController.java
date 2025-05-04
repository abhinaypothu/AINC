package com.example.controller;

import com.example.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class HomeController {

    @Autowired
    private BloggerService bloggerService;

    @GetMapping("/")
    public String home(Model model) throws IOException {
        String trendingBlogs = bloggerService.getTrendingBlogPosts();
        model.addAttribute("trendingBlogs", trendingBlogs);
        return "home";
    }
}
