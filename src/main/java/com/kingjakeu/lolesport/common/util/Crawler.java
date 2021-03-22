package com.kingjakeu.lolesport.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingjakeu.lolesport.common.constant.CommonError;
import com.kingjakeu.lolesport.common.exception.CrawlException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

@Slf4j
public class Crawler {
    public static Document doGetDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CrawlException(CommonError.CRAWL_ERROR);
        }
    }

    public static Document doPost(String url){
        try {
            return Jsoup.connect(url)
                    .data("query", "Java")
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .post();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CrawlException(CommonError.CRAWL_ERROR);
        }
    }

    public static <T> T doGetObject(String url, Map<String, String> httpHeaders, Map<String, String> parameters, TypeReference<T> typeReference) {
        String result = HttpRequester.doGet(
                url,
                httpHeaders,
                parameters
        );
        try {
            return new ObjectMapper().readValue(result, typeReference);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new CrawlException(CommonError.CRAWL_MAP_ERROR);
        }
    }
}
