package com.kingjakeu.lolesport.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingjakeu.lolesport.api.info.dto.matchHistory.GameDto;
import com.kingjakeu.lolesport.api.info.dto.schedule.ScheduleDataDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
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
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                //.defaultHeader("Cookie", "id_token=eyJraWQiOiJzMSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI1MDZmMzlmMC02MWVlLTU3NGEtYTE4YS0wYjAwYzE5MTNmMDYiLCJjb3VudHJ5Ijoia29yIiwicGxheWVyX3Bsb2NhbGUiOiJrby1LUiIsImFtciI6WyJwYXNzd29yZCJdLCJpc3MiOiJodHRwczpcL1wvYXV0aC5yaW90Z2FtZXMuY29tIiwibG9sIjpbeyJjdWlkIjo0NzQ0ODAwLCJjcGlkIjoiS1IiLCJ1aWQiOjQ3NDQ4MDAsInVuYW1lIjoiamFrZWhzdSIsInB0cmlkIjpudWxsLCJwaWQiOiJLUiIsInN0YXRlIjoiRU5BQkxFRCJ9XSwibG9jYWxlIjoiZW5fVVMiLCJhdWQiOiJyc28td2ViLWNsaWVudC1wcm9kIiwiYWNyIjoidXJuOnJpb3Q6YnJvbnplIiwicGxheWVyX2xvY2FsZSI6ImtvLUtSIiwiZXhwIjoxNjE0OTI5Mjg3LCJpYXQiOjE2MTQ4NDI4ODcsImFjY3QiOnsiZ2FtZV9uYW1lIjoi7ISc64yA66y46rWs7LqQ66as66i47IugIiwidGFnX2xpbmUiOiJLUjEifSwianRpIjoibzBVNzJOSzZqV0EiLCJsb2dpbl9jb3VudHJ5Ijoia29yIn0.ho-ri8As1cur6UjefjRyXDjdLNpkSRDNCmINXr2yITxPkmlU-Ox_OFxiYU1hI84pRXdZxANgZqSZCR59d4qW9_Q4cTVd6fKXxptFB89RbCou3A4s5SSMu6kR1i-EFIn-cm6JFtX_1b2zZPd8HWnbImaqIj9S8dNKdiWVnOOCoyA;")
                //.defaultHeader("user-agent", "Mozilla")
                .build();
    }

    public static void main(String[] args) throws UnsupportedEncodingException, JsonProcessingException {

    }
}