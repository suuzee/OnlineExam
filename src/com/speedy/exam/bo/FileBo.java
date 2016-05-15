package com.speedy.exam.bo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.read.biff.BiffException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.speedy.base.util.ExcelReader;
import com.speedy.exam.dao.FileDao;
import com.speedy.exam.exception.KeyException;
import com.speedy.exam.model.Question;

@Transactional(rollbackFor = Exception.class)
@Component
public class FileBo extends BaseBean{
		
	@Autowired
	FileDao fileDao;

	public int importQuestions(String fileName) throws Exception{
		List<String[]> list;
		try {
			list = ExcelReader.readExcel(new File(fileName),2);
			List<Question> newList = new ArrayList<Question>();
			for(int i=0;i<list.size();i++)
			{
				if(list.get(i)!=null&&!"".equals(list.get(i)[0])&&list.get(i)[5].indexOf("&")>0){
				Question question = new Question();
				try {
					question.setQuestionkey(generatorKey("t_e_question","QN"));
				} catch (KeyException e) {
					e.printStackTrace();
				}
				question.setQuestiontype(list.get(i)[1]);
				question.setIndustry(list.get(i)[2]);
				question.setCategory(list.get(i)[3]);
				question.setContent(list.get(i)[4]);
				question.setAlternativeanswer(list.get(i)[5]);
				question.setCorrentanswer(list.get(i)[6]);
				question.setScore(Integer.valueOf(list.get(i)[7]));
			    newList.add(question);
				}else{
					throw new Exception("µ¼Èë³ö´í"+list.get(i)[0]);
				}
			}
			int i = 0;
			Iterator<Question> question = newList.iterator();
			while(question.hasNext())
			{
				Question que = (Question)question .next();
				int maxNumber = fileDao.getMaxNumber();
				que.setQuestionnumber(maxNumber+1);
				fileDao.importQuestions(que);
				i++;
			}
			return i;
			
		} catch (BiffException e) {
			e.printStackTrace();
			int a=0;
			return a;
		} catch (IOException e) {
			e.printStackTrace();
			int a=0;
			return a;
		}
		
	}
	
	
}
