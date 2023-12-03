package com.paranoidal97.githubapi.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.paranoidal97.githubapi.service.GithubDetailsServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.io.IOException;

import static com.paranoidal97.githubapi.data.WireMockStubs.setupMockGithubClient;

@SpringBootTest
@AutoConfigureWireMock(port = 8888)
public class GithubClientTest {
    @Autowired
    GithubClient githubClient;

    @Autowired
    GithubDetailsServiceTest githubDetailsService;

    @Autowired
    WireMockServer wireMockServer;

    @BeforeEach
    void setUp() throws IOException {
        setupMockGithubClient(wireMockServer);
    }
}
