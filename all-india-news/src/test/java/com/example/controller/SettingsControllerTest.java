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
public class SettingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetSettings() throws Exception {
        mockMvc.perform(get("/settings"))
               .andExpect(status().isOk());
    }

    @Test
    public void testSaveSettings() throws Exception {
        mockMvc.perform(post("/settings"))
               .andExpect(status().isOk());
    }
}
