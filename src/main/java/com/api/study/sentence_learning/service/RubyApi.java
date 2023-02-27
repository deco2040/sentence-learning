package com.api.study.sentence_learning.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class RubyApi {
    @Value("${yahoo_dev_api_key}")
    private String yahoo_dev_api_key;

    public String getRuby(String query, String kanji) throws IOException, JSONException {
                String APPID = yahoo_dev_api_key; // <-- Set your own application ID here.
                String URL = "https://jlp.yahooapis.jp/FuriganaService/V2/furigana";

                JSONObject paramDic = new JSONObject();
                paramDic.put("id", "2040");
                paramDic.put("jsonrpc", "2.0");
                paramDic.put("method", "jlp.furiganaservice.furigana");
                JSONObject params = new JSONObject();
                params.put("q", query);
                params.put("grade", 1);
                paramDic.put("params", params);

                URL url = new URL(URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("User-Agent", "Yahoo AppID: " + APPID);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                os.write(paramDic.toString().getBytes());
                os.flush();
                os.close();

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                StringBuilder sb = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    sb.append(output);
                }
                conn.disconnect();

                return rubyTrans(sb.toString(), kanji);
            }

    public static String rubyTrans(String ruby, String kanji){
        String result = "";

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(ruby);
            JSONArray wordsArr = (JSONArray) ((JSONObject) jsonObj.get("result")).get("word");

            StringBuilder resultBuilder = new StringBuilder();
            for (Object wordObj : wordsArr) {
                JSONObject word = (JSONObject) wordObj;
                String surface = (String) word.get("surface");
                String furigana = (String) word.get("furigana");

                if (furigana != null) {
                    resultBuilder.append(surface).append("(").append(furigana).append(")");
                }else {
                    resultBuilder.append(surface);
                }
            }

            result = resultBuilder.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = boldKanji(result, kanji);

        return result;
    }

    public static String boldKanji(String text, String kanji) {
        text = text.replaceAll(kanji, "<b>" + kanji + "</b>");
        return text;
    }
}
