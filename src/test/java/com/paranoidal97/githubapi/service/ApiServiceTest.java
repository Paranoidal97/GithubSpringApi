package com.paranoidal97.githubapi.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.paranoidal97.githubapi.controller.GithubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiServiceTest {

    @Autowired
    GithubClient githubClient;

    @Autowired
    WireMockServer wireMockServer;


}
