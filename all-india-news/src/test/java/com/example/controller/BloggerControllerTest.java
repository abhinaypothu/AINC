package com.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BloggerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBloggerSettings() throws Exception {
        mockMvc.perform(get("/blogger/settings"))
               .andExpect(status().isOk());
    }

    @Test
    public void testSaveBloggerSettings() throws Exception {
        mockMvc.perform(post("/blogger/settings"))
               .andExpect(status().isOk());
    }
}
