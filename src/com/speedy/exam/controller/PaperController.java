package com.speedy.exam.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedy.exam.bo.PaperBo;
import com.speedy.exam.model.Option;
import com.speedy.exam.model.Question;

@Controller
public class PaperController {

	@Autowired
	PaperBo paperBo;

	@RequestMapping("getQuestion")
	public String startExam(Map<String,Object> map,String examType){
			List<Question> questions = paperBo.startExam(examType);
			map.put("question",questions);
			return "paper_page";

	}
	
	@RequestMapping("paper")
	public String score(Map<String,Object> map,String[] que,String[] quekey,String personkey){
			List<?> list = paperBo.getScore(que,quekey,personkey);
			map.put("score",Integer.parseInt(String.valueOf(list.get(0))));
			map.put("quekey",quekey);
			map.put("que", que);
			map.put("paperkey",(String)list.get(1));
			return "score_page";

	}
	
	@RequestMapping("check")
	public String check(Map<String,Object> map,String paperkey){
			List<Question> question = paperBo.getQuestionByKey(paperkey);
			map.put("question",question);
			return "check_page";

	}
	
	@RequestMapping("exam")
	public String exam(Map<String,Object> map){
			List<Option> list = paperBo.getExam();
			map.put("exam",list);
			return "test_page";
	}
	
	
}