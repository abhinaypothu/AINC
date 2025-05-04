package com.example.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.repository.PostRepository;
import java.util.List;
import java.util.ArrayList;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import java.util.Optional;
import com.example.model.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    public void testGetAllPosts() {
        List<Post> posts = new ArrayList<>();
        when(postRepository.findAll()).thenReturn(posts);
        List<Post> result = postService.getAllPosts();
        assertNotNull(result);
    }

    @Test
    public void testGetPostById() {
        Post post = new Post();
        post.setId(1L);
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        Post result = postService.getPostById(1L);
        assertNotNull(result);
    }

    @Test
    public void testSavePost() {
        Post post = new Post();
        when(postRepository.save(any(Post.class))).thenReturn(post);
        Post savedPost = postService.savePost(post);
        assertNotNull(savedPost);
    }

    @Test
    public void testDeletePost() {
        Post post = new Post();
        post.setId(1L);
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        postService.deletePost(1L);
    }
}
