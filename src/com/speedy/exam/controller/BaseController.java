package com.speedy.exam.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
	
	@RequestMapping("page.do")
	public void page(Map<String,Object> map,String examType){
			
		System.out.println("—È÷§session");

	}
}
