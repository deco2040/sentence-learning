package com.api.study.sentence_learning.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    
    
    @GetMapping("/")
	public String first(Model Model) {


		return "index";
	}

    
}
