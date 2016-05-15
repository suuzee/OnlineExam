package com.speedy.exam.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.speedy.exam.dao.LoginDao;
import com.speedy.exam.dao.PaperDao;
import com.speedy.exam.exception.KeyException;
import com.speedy.exam.model.Option;
import com.speedy.exam.model.Paper;
import com.speedy.exam.model.PaperQuestion;
import com.speedy.exam.model.Question;

@Transactional(rollbackFor = Exception.class)
@Component
public class PaperBo extends BaseBean{
		
	@Autowired
	PaperDao paperDao;
	@Autowired
	LoginDao loginDao;
	
	public List<Question> startExam(String examType){
		
		int count = paperDao.getQuestionCount(examType);
		List<Question> newList = new ArrayList<Question>();
		int len = count;  
	    //初始化给定范围的待选数组  
	    int[] source = new int[len];  
	       for (int i = 0; i < count; i++){  
	        source[i] = i+1;  
	       }  
	         
	    int[] result = new int[100];  
	    Random rd = new Random();  
	    int index = 0;  
	    for (int i = 0; i < result.length; i++) {  
	     //待选数组0到(len-2)随机一个下标  
	        index = Math.abs(rd.nextInt() % len--);  
	        //将随机到的数放入结果集  
	        result[i] = source[index];  
	        //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换  
	        source[index] = source[len];  
	    }
	    for (int i : result) {
	    	System.out.println(i);
	    	Question question = paperDao.startExam(i,examType);
	    	String alterquestion = question.getAlternativeanswer();
	    	question.setAlternativeanswers(alterquestion.split("&"));
	    	newList.add(question);
		}
		
		return newList;
	}
	
	public List getScore(String[] que,String[] quekey,String personkey){
		List list = new ArrayList();
		int score = 0;
		int length = que.length;
		String answer = "";
		int i = 0;
		for(i = 0;i<length;i++){
			answer = paperDao.getScore(quekey[i]);
			if(answer.equals(que[i]))
				score++;
		}
		loginDao.updateCredit(score,personkey);
		String passState;
		if(score >= 60){
			passState = "Y";
			loginDao.updateFloor("00",personkey);
		}else{
			passState = "N";
		}
		try {
			Paper paper=new Paper();
			paper.setPaperkey(generatorKey("t_e_paper","PN"));
			paper.setPapertype("jc");
			paper.setPersonkey(personkey);
			paper.setScore(score);
			paper.setPassstate(passState);
			paperDao.addPaper(paper);
			PaperQuestion pq = new PaperQuestion();
			for(i = 0;i<length;i++){
				pq.setRelationkey(generatorKey("t_e_paper_question_rel","RN"));
				pq.setPaperkey(paper.getPaperkey());
				pq.setQuestionkey(quekey[i]);
				pq.setQuestionanswer(que[i]);
				pq.setQuestionorder(i);
				paperDao.addPaperQuestion(pq);
			}
			list.add(score);
			list.add(paper.getPaperkey());
		} catch (KeyException e) {
			e.printStackTrace();
		}
		
		return list;
	}
		
	public List<Question> getQuestionByKey(String paperkey){

		List<Question> question = paperDao.getQuestionByKey(paperkey);
		
		for (Question q : question) {

	    	String alterquestion = q.getAlternativeanswer();
	    	q.setAlternativeanswers(alterquestion.split("&"));

		}
		
		return question;
	}
	
	public List<Option> getExam(){
		List<Option> list = paperDao.getExam();
		return list;
	} 
}
