package com.paranoidal97.githubapi.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static com.paranoidal97.githubapi.data.WireMockStubs.setupMockGithubClient;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 8888)
public class GithubDetailsControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    WireMockServer wireMockServer;

    @BeforeEach
    void setUp() throws IOException {
        setupMockGithubClient(wireMockServer);
    }

    @Test
    public void getOwnerReposTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Paranoidal97"))
                .andDo(print());

    }

    @Test
    public void getGithubDetailsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Paranoidal97/medical-clinic"))
                .andDo(print());
    }

    @Test
    //TODO
    public void saveRepositoryTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Paranoidal97/medical-clinic"))
                .andDo(print());
    }

    @Test
    //TODO
    public void getLocalGithubDetailsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Paranoidal97/medical-clinic"))
                .andDo(print());
    }

    @Test
    //TODO
    public void editRepositoryTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Paranoidal97/medical-clinic"))
                .andDo(print());
    }

    @Test
    //TODO
    public void deleteRepositoryTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Paranoidal97/medical-clinic"))
                .andDo(print());
    }
}
