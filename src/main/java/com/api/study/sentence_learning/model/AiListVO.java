package com.api.study.sentence_learning.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AiListVO {
    private String kanji;
    private String mean;
    private String ondoku;
    private String kundoku;
    private String sentence;
    private String hurigana;
    private String translate;
    private String etc;

}
