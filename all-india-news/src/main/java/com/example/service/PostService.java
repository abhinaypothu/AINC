package com.example.service;

import com.example.model.Post;
import com.example.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null);
    }

    public Post savePost(Post post) {
       return postRepository.save(post);
    }

    public Post updatePost(Long id, Post post) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post updatedPost = existingPost.get();
            updatedPost.setTitle(post.getTitle());
            updatedPost.setContent(post.getContent());
            updatedPost.setAuthor(post.getAuthor());
            updatedPost.setUpdatedAt(LocalDateTime.now());
            return postRepository.save(updatedPost);
        } else {
            return null;
        }
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
