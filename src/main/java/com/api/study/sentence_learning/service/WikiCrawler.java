package com.api.study.sentence_learning.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class WikiCrawler {


    public Set<String> crawler() throws IOException {

        String url = "https://ja.wikipedia.org/wiki/%E5%B8%B8%E7%94%A8%E6%BC%A2%E5%AD%97%E4%B8%80%E8%A6%A7";

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements imports = doc.select("link[href]");

        Set<String> result = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        Elements media = doc.select("[src]");
            for (Element src : media) {
                result.add(src.absUrl("src"));
                //result.add(src.attr("abs:src"));
            }


        for (Element link : imports) {
            result.add(link.absUrl("abs:href"));
        }

        for (Element link : links) {
            result.add(link.absUrl("abs:href"));
        }

        return result;
    }
}



