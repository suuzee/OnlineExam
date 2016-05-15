package com.speedy.exam.dao;

import org.springframework.stereotype.Repository;

import com.speedy.exam.model.Question;

@Repository
public class FileDao extends BaseDao{

	@Override
	public String getNamespace() {
		return "com.speedy.exam.dao.FileDao.";
	}
	
	public int importQuestions(Question question){
		return sqlSessionMyExam.insert(getNamespace() + "importQuestions",question);
	}
	
	public int getMaxNumber(){
		return sqlSessionMyExam.selectOne(getNamespace() + "getMaxNumber");
	}

	
}
