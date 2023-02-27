package com.api.study.sentence_learning.controller;


import com.api.study.sentence_learning.model.KanjiListVO;
import com.api.study.sentence_learning.model.MeanVO;
import com.api.study.sentence_learning.service.ChatGPTapi;
import com.api.study.sentence_learning.service.SentenceGPT;
import com.api.study.sentence_learning.service.WikiGetData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
	@Autowired
	private SentenceGPT sentenceGPT;

	public IndexController(SentenceGPT sentenceGPT) {
		this.sentenceGPT = sentenceGPT;
	}

	@GetMapping("/")
	public String index(Model studyList) throws Exception {
		List<KanjiListVO> kanjilist = new ArrayList<>();

		WikiGetData wikiGetData = new WikiGetData();
		kanjilist = wikiGetData.getData();

		studyList.addAttribute("kanjilist", kanjilist);

		return "index";
	}

	@GetMapping("/details")
	public String details(@RequestParam("id") int id, Model means) throws Exception {

		MeanVO meanVO = null;
		List<KanjiListVO> kanjilist = new ArrayList<>();

		WikiGetData wikiGetData = new WikiGetData();
		kanjilist = wikiGetData.getData();

		for (KanjiListVO kanjiListVO : kanjilist) {
			if (kanjiListVO.getNum() == id) {
				meanVO = sentenceGPT.sentenceMaker(kanjiListVO.getKanji());
			}
		}

		means.addAttribute("meanVO", meanVO);
		means.addAttribute("id", id);

		return "details";
	}








}
