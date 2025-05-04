package com.example.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

import com.example.model.BloggerSettings;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import com.example.model.BloggerSettings;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@Service
public class BloggerService {

    @Value("${blogger.api.url}")
    private String BLOGGER_API_URL;

    @Autowired
    private SettingsService settingsService;

    private String blogId = "3213900";

    private String getApiKey() {
        Map<String, Object> settings = settingsService.getSettings();
        return (String) settings.get("bloggerApiKey");
    }

    public String getBlogPosts() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BLOGGER_API_URL + blogId + "/posts?key=" + getApiKey());

        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
            httpClient.close();
        }
    }

    public String createBlogPost(String title, String content) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(BLOGGER_API_URL + blogId + "/posts?key=" + getApiKey());
        httpPost.setHeader("Content-Type", "application/json");

        String json = String.format("{\"title\": \"%s\", \"content\": \"%s\"}", title, content);
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
            httpClient.close();
        }
    }

    public String getPostMetrics(String postId) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BLOGGER_API_URL + blogId + "/posts/" + postId + "/?key=" + getApiKey() + "&fields=replies,kind,id");

        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
            httpClient.close();
        }
    }

     public String getCategories() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BLOGGER_API_URL + blogId + "/categories?key=" + getApiKey());

        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
            httpClient.close();
        }
    }

    public String getTags() throws IOException {
         CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BLOGGER_API_URL + blogId + "/posts?key=" + getApiKey() + "&fields=items(labels)");

        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
            httpClient.close();
        }
    }

    public String getTrendingBlogPosts() throws IOException {
        Map<String, Object> settings = settingsService.getSettings();
        Object countObj = settings.get("trendingBlogsCount");
        Integer count = 0;
        if (countObj instanceof Double) {
            count = ((Double) countObj).intValue();
        } else if (countObj instanceof Integer) {
            count = (Integer) countObj;
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();
        Gson gson = new Gson();
        String allPostsJson = getBlogPosts();
        JsonObject allPosts = gson.fromJson(allPostsJson, JsonObject.class);
        JsonArray items = allPosts.getAsJsonArray("items");

        if (items == null) {
            return gson.toJson(new ArrayList<>());
        }

        List<BlogPost> blogPosts = new ArrayList<>();
        for (JsonElement item : items) {
            JsonObject post = item.getAsJsonObject();
            String postId = post.get("id").getAsString();
            String metricsJson = getPostMetrics(postId);
            JsonObject metrics = gson.fromJson(metricsJson, JsonObject.class);
            JsonObject replies = metrics.getAsJsonObject("replies");
            int totalReplies = replies.get("totalItems").getAsInt();

            BlogPost blogPost = new BlogPost(postId, totalReplies);
            blogPosts.add(blogPost);
        }

        blogPosts.sort(Comparator.comparingInt(BlogPost::getReplies).reversed());

        List<String> trendingPosts = new ArrayList<>();
        for (int i = 0; i < Math.min(count, blogPosts.size()); i++) {
            trendingPosts.add(blogPosts.get(i).getPostId());
        }

        return gson.toJson(trendingPosts);
    }

    private static class BlogPost {
        private String postId;
        private int replies;

        public BlogPost(String postId, int replies) {
            this.postId = postId;
            this.replies = replies;
        }

        public String getPostId() {
            return postId;
        }

        public int getReplies() {
            return replies;
        }
    }
}
