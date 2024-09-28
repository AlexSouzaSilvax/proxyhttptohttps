package com.alexsouzasilvax.proxy.proxyhttptohttps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final String TARGET_API_URL = "http://api-helper-teste.gtech.site/";

    @GetMapping("/**")
    public Mono<ResponseEntity<String>> proxyGetRequest() {
        String targetUrl = TARGET_API_URL + "/data"; // ajusta a URL conforme a API de destino

        return webClientBuilder.build()
                .get()
                .uri(targetUrl)
                .retrieve()
                .toEntity(String.class);
    }

    @PostMapping("/**")
    public Mono<ResponseEntity<String>> proxyPostRequest(@RequestBody String body) {
        String targetUrl = TARGET_API_URL + "/data"; // ajusta a URL conforme a API de destino

        return webClientBuilder.build()
                .post()
                .uri(targetUrl)
                .bodyValue(body)
                .retrieve()
                .toEntity(String.class);
    }
}
