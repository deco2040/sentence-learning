package com.api.study.sentence_learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JapaneseStringSeparator {

    public String getKunndoku(String mean){
        String kunndoku = mean.replaceAll("[^ぁ-ん、]", "");
        kunndoku = removeComma(kunndoku);
        return  kunndoku;
    }

    public String getOnndoku(String mean){
        String onndoku = mean.replaceAll("[^ァ-ン、]", "");
        onndoku = removeComma(onndoku);
        return  onndoku;
    }

    public static String removeComma(String input) {
        if (input == null || input == "、") {
            return null;
        }
        String result = input.trim();

        while (result.startsWith("、")) {
            result = result.substring(1).trim();
        }

        while (result.endsWith("、")) {
            result = result.substring(0, result.length() - 1).trim();
        }

        return result;
    }


}
