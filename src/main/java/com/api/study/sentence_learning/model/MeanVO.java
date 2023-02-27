package com.api.study.sentence_learning.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeanVO {
    String kanji;
    String sentence;
    String furigana;
    String translate;

}
