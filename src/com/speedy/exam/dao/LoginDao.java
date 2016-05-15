package com.speedy.exam.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.speedy.exam.model.Person;

@Repository
public class LoginDao extends BaseDao{

	@Override
	public String getNamespace() {
		return "com.speedy.exam.dao.LoginDao.";
	}
	
	public List<Person> loginPerson(Person person){
		return sqlSessionErpAdmin.selectList(getNamespace() + "loginPerson",person);
	}

	public Person getPerson(String personkey){
		return sqlSessionMyExam.selectOne(getNamespace() + "getPerson",personkey);
	}
	
	public int addPerson(Person person){
		return sqlSessionMyExam.insert(getNamespace() + "addPerson",person);
	}
	
	public int updateFloor(String floor,String personkey){
		Map<String, Object> param=new HashMap<String, Object>();  
		param.put("floor", floor);  
		param.put("personkey", personkey);  
		return sqlSessionMyExam.update(getNamespace() + "updateFloor",param);
	}
	
	public int updateCredit(int score,String personkey){
		Map<String, Object> param=new HashMap<String, Object>();  
		param.put("score", score);  
		param.put("personkey", personkey);  
		return sqlSessionMyExam.update(getNamespace() + "updateCredit",param);
	}
	
}
