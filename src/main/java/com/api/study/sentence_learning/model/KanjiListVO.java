package com.api.study.sentence_learning.model;

import com.api.study.sentence_learning.service.JapaneseStringSeparator;
import lombok.Data;

@Data
public class KanjiListVO {

    int num;
    String kanji;
    String mean;
    String onndoku;
    String kunndoku;

    public KanjiListVO(int num, String kanji, String mean){
        JapaneseStringSeparator jss = new JapaneseStringSeparator();
        this.onndoku = jss.getOnndoku(mean);
        this.kunndoku = jss.getKunndoku(mean);
        this.kanji = kanji;
        this.mean = mean;
        this.num = num;
    }








}
