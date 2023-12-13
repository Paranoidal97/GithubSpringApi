package com.paranoidal97.githubapi.data;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.paranoidal97.githubapi.model.dto.RepoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class WireMockStubs {
    public static void setupMockGithubClient(WireMockServer mockServer) throws IOException {
        mockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/Paranoidal97"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        RepoDTO.class
                                                .getClassLoader()
                                                .getResourceAsStream("payload/getGithubUserRepos.json"),
                                        defaultCharset()
                                )
                        )
                )
        );

        mockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/Paranoidal97/medical-clinic"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        RepoDTO.class
                                                .getClassLoader()
                                                .getResourceAsStream("payload/getGithubDetails.json"),
                                        defaultCharset()
                                )
                        )
                )
        );
    }
}
