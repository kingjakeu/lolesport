package com.kingjakeu.lolesport.common.util;

import com.kingjakeu.lolesport.common.constant.DateTimeFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDate;

public class Crawler {
    public static Document doGet(String url) throws IOException {
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

    public static void main(String[] args) throws IOException {

        String str  = "June 7, 2000 ";
        LocalDate localDate = LocalDate.parse(str, DateTimeFormat.PLAYER_BIRTHDAY);
        System.out.println(localDate);
//        Document doc = doGet("https://lol.gamepedia.com/Kiin");
//        Element el = doc.getElementById("infoboxPlayer");
//
//        Elements elements = el.getElementsByTag("td");
//        int idx = 0;
//        for(Element element : elements){
//            System.out.println(idx++ + " "+ element.text());
//        }
    }
}
