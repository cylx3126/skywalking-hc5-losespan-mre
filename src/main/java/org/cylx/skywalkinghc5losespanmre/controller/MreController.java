package org.cylx.skywalkinghc5losespanmre.controller;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author syt
 * @since 2024/03/12 20:44
 */
@RestController
public class MreController {

    private final CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    @GetMapping("haveSpan")
    public String haveSpan() throws URISyntaxException, IOException {
        HttpGet httpGet = new HttpGet("https://www.gstatic.com/generate_204");
        HttpHost httpHost = HttpHost.create("https://www.gstatic.com");
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpHost, httpGet, (HttpContext) null)) {
            return String.format("httpCode: %d", httpResponse.getCode());
        }
    }

    @GetMapping("noSpan")
    public String noSpan() throws IOException {
        HttpGet httpGet = new HttpGet("https://www.gstatic.com/generate_204");
        try (CloseableHttpResponse httpResponse = httpClient.execute(null, httpGet, (HttpContext) null)) {
            return String.format("httpCode: %d", httpResponse.getCode());
        }
    }
}
