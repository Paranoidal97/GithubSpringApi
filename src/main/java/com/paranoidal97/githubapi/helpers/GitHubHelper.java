package com.paranoidal97.githubapi.helpers;


import org.springframework.http.HttpHeaders;

public class GitHubHelper {
    public static HttpHeaders getUserAgentHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "http://developer.github.com/v3/#user-agent-required");
        return headers;

    }
}
