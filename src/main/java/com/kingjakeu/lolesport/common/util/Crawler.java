package com.kingjakeu.lolesport.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingjakeu.lolesport.common.constant.CrawlUrl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Crawler {
    public static Document doGetDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    public static Document doPost(String url) throws IOException{
        return Jsoup.connect(url)
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(3000)
                .post();
    }

    public static <T> T doGetObject(CrawlUrl crawlUrl, Map<String, String> parameters, TypeReference<T> typeReference) throws JsonProcessingException {
        return doGetObject(crawlUrl.getUrl(), crawlUrl.getHttpHeader(), parameters, typeReference);
    }

    public static <T> T doGetObject(String url, Map<String, String> httpHeaders, Map<String, String> parameters, TypeReference<T> typeReference) throws JsonProcessingException {
        String result = HttpRequester.doGet(
                url,
                httpHeaders,
                parameters
        );
        return new ObjectMapper().readValue(result, typeReference);
    }


    public static Map<String, String> createCommonLolEsportParameters(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("hl", "ko-KR");
        return parameters;
    }
}
