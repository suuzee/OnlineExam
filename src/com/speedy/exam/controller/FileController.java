package com.speedy.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedy.exam.bo.FileBo;

@Controller
public class FileController {

	@Autowired
	FileBo fileBo;

	@RequestMapping("uploadfile")
	public String importQuestions(String fileName) throws Exception{
		
			try {
				fileBo.importQuestions(fileName);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e);
				
			}
			return "importAData";

	}
	
	
}