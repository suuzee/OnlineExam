package com.speedy.exam.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.speedy.exam.model.Option;
import com.speedy.exam.model.Paper;
import com.speedy.exam.model.PaperQuestion;
import com.speedy.exam.model.Question;

@Repository
public class PaperDao extends BaseDao{

	@Override
	public String getNamespace() {
		return "com.speedy.exam.dao.PaperDao.";
	}
	
	public Question startExam(int questionnumber,String examType){
		Map<String, Object> param=new HashMap<String, Object>();  
		param.put("questionnumber", questionnumber);  
		param.put("category", examType);
		return sqlSessionMyExam.selectOne(getNamespace() + "startExam",param);
	}
	
	public String getScore(String quekey) {
		return sqlSessionMyExam.selectOne(getNamespace() + "getScore",quekey);
	}
	
	public int addPaper(Paper paper) {
		return sqlSessionMyExam.insert(getNamespace() + "addPaper",paper);
	}
	
	public int addPaperQuestion(PaperQuestion pq) {
		return sqlSessionMyExam.insert(getNamespace() + "addPaperQuestion",pq);
	}
	
	public List<Question> getQuestionByKey(String quekey) {
		return sqlSessionMyExam.selectList(getNamespace() + "getQuestionByKey",quekey);
	}
	
	public int getQuestionCount(String examType) {
		return sqlSessionMyExam.selectOne(getNamespace() + "getQuestionCount",examType);
	}
	
	public List<Option> getExam(){
		return sqlSessionMyExam.selectList(getNamespace() + "getExam");
	}
}
