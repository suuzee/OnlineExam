package com.speedy.exam.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseDao {

	@Autowired(required = true)
	protected SqlSession sqlSessionErpAdmin;
	
	@Autowired(required = true)
	protected SqlSession sqlSessionMyExam;
	

	public abstract String getNamespace();

	
}
