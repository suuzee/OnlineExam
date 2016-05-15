package com.speedy.exam.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.speedy.exam.model.Notice;

@Repository
public class NoticeDao extends BaseDao{

	@Override
	public String getNamespace() {
		return "com.speedy.exam.dao.NoticeDao.";
	}

	public List<Notice> getNotices(){
		return sqlSessionMyExam.selectList(getNamespace() + "getNotices");
	}
}
