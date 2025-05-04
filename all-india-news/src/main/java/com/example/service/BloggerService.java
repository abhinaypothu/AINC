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

@Service
public class BloggerService {

    private static final String BLOGGER_API_URL = "https://www.googleapis.com/blogger/v3/blogs/";

    @Autowired
    private SettingsService settingsService;

    private String getApiKey() {
        BloggerSettings settings = settingsService.getSettings();
        return settings.getBloggerApiKey();
    }

    public String getBlogPosts(String blogId) throws IOException {
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

    public String createBlogPost(String blogId, String title, String content) throws IOException {
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

    public String getPostMetrics(String blogId, String postId) throws IOException {
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

     public String getCategories(String blogId) throws IOException {
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

    public String getTags(String blogId) throws IOException {
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
}
