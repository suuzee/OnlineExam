package com.speedy.exam.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.speedy.exam.model.Material;

@Repository
public class MaterialDao extends BaseDao{

	@Override
	public String getNamespace() {
		return "com.speedy.exam.dao.MaterialDao.";
	}
	
	public List<Material> getMaterial(){
		return sqlSessionMyExam.selectList(getNamespace() + "getMaterial");
	}
	
	public List<Material> getMaterial1(){
		return sqlSessionMyExam.selectList(getNamespace() + "getMaterial1");
	}
	
	public Material getMaterialByKey(String key){
		return sqlSessionMyExam.selectOne(getNamespace() + "getMaterialByKey",key);
	}

}
