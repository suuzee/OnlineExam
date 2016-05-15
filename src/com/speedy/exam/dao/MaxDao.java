package com.speedy.exam.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MaxDao extends BaseDao{

	@Override
	public String getNamespace() {
		return "com.speedy.exam.dao.MaxDao.";
	}

	public int getMax(String maxCode){
		return sqlSessionMyExam.selectOne(getNamespace() + "getMax",maxCode);
	}
	
	public int updateMax(String maxCode){
		return sqlSessionMyExam.update(getNamespace() + "updateMax",maxCode);
	}
}
