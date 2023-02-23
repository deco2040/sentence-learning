package com.api.study.sentence_learning.service;

import com.api.study.sentence_learning.model.AiListVO;
import com.api.study.sentence_learning.model.KanjiListVO;
import org.fastily.jwiki.core.Wiki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiGetData {
    public List<KanjiListVO> getData() {

        List<KanjiListVO> kanjilist = new ArrayList<>();
        String result = "";

        Wiki wiki = new Wiki.Builder().build();
        result = wiki.getPageText("List of jōyō kanji");

        //System.out.println(result);


        Pattern kanjiPattern = Pattern.compile("\\|\\d+\\|\\|style=\"font-size:2em\"\\|\\[\\[wikt:([^|\\]]+)\\|[^\\]]*\\]\\]");
        Matcher kanjiMatcher = kanjiPattern.matcher(result);

        Pattern meanPattern = Pattern.compile("\\|\\|([^|]+)<br>");
        Matcher meanMatcher = meanPattern.matcher(result);

        int kanjiCount = 1;
        int meanCount = 1;

        while (kanjiMatcher.find()) {
            if (meanCount > 2136) {
                break;
            }

            //System.out.println("======================= " + kanjiCount + "번째 한자 ===============================");
            //System.out.println("한자 : " + kanjiMatcher.group(1));

            while (meanMatcher.find()) {
               //System.out.println("======================= " + meanCount + "번째 음독 ===============================");

                String mean = meanMatcher.group(1);
                if (mean.contains("<")) {
                    mean = mean.substring(0, mean.indexOf("<"));
                }
                //System.out.println("음독 : " + mean);
                kanjilist.add(new KanjiListVO(kanjiMatcher.group(1), mean));

                meanCount++;
                break;
            }

            kanjiCount++;
        }

        return kanjilist;
    }

}
