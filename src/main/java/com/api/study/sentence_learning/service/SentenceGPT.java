package com.api.study.sentence_learning.service;

import com.api.study.sentence_learning.model.MeanVO;
import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SentenceGPT {

    @Autowired
    private ChatGPTapi chatGPTapi;
    @Autowired
    private RubyApi rubyApi;

    @Value("${deepl_trans_api_key}")
    private String deepl_trans_api_key;
    
    public MeanVO sentenceMaker(String kanji) throws Exception {



        String sentence = chatGPTapi.chatGPT("\'" + kanji + "\'が入る単語などが入っている短い文章を作文してくれ");
        sentence = sentence.substring(sentence.indexOf("\n"));
        String boldSentence = boldkanji(sentence, kanji);

        return new MeanVO(kanji,boldSentence,rubyApi.getRuby(sentence, kanji),translate(sentence));
    }

    public String translate(String sentence) throws DeepLException, InterruptedException {
        String authKey = deepl_trans_api_key;  // Replace with your key
        Translator translator = new Translator(authKey);
        TextResult result = translator.translateText(sentence, null, "ko");

        return result.getText();
    }

    public static String boldkanji(String text, String kanji) {
        text = text.replaceAll(kanji, "<b>" + kanji + "</b>");
        return text;
    }
}
