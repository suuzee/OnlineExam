package com.speedy.exam.dao;

import org.springframework.stereotype.Repository;

import com.speedy.exam.model.Notice;

@Repository
public class ManageDao extends BaseDao{

	@Override
	public String getNamespace() {
		return "com.speedy.exam.dao.ManageDao.";
	}
	
	public int addNotice(Notice notice){
		return sqlSessionMyExam.insert(getNamespace() + "addNotice",notice);
	}
	
	public int delNotice(String noticekey){
		return sqlSessionMyExam.delete(getNamespace() + "delNotice",noticekey);
	}
	
}
