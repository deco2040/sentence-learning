package com.api.study.sentence_learning.controller;

import com.api.study.sentence_learning.model.AiListVO;
import com.api.study.sentence_learning.model.KanjiListVO;
import com.api.study.sentence_learning.service.ApiGetData;
import com.api.study.sentence_learning.service.WikiCrawler;

import org.fastily.jwiki.core.Wiki;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class IndexController {

	@GetMapping("/")
	public String first(Model studyList) throws IOException {
		List<AiListVO> aiList = new ArrayList<>();

		//aiList.add(new AiListVO("雨", "비 우", "う", "あめ、あま", "大雨が来て大変だ。", "おおあめがきてだいへんだ。", "큰 비가 와서 큰일이다.", "기타"));
		//studyList.addAttribute("aiList",aiList);

		List<KanjiListVO> kanjilist = new ArrayList<>();

		ApiGetData gd = new ApiGetData();
		kanjilist = gd.getData();

		studyList.addAttribute("kanjilist", kanjilist);


		return "index";
	}
}
