package com.kingjakeu.promode.common.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;


public class HttpRequester {
    public static void doPost(String url){
        WebClient webClient = WebClient.create(url);

    }

    public static String doGet(String url, Map<String, String> httpHeader, Map<String, String> queryParams) {
        WebClient webClient = createDefaultGetClient(url);
        WebClient.RequestHeadersUriSpec<?> uriSpec = webClient.get();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.setAll(queryParams);

        WebClient.RequestHeadersSpec<?> headersSpec = uriSpec.uri(
                uriBuilder -> uriBuilder
                        .queryParams(params)
                        .build()
        );

        for(Map.Entry<String, String> header : httpHeader.entrySet()){
            headersSpec = headersSpec.header(header.getKey(), header.getValue());
        }

        Mono<String> response = headersSpec.retrieve().bodyToMono(String.class);
        return response.block();
    }

    private static WebClient createDefaultGetClient(String url){
        return WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                        .build())
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)

                .build();
    }
}