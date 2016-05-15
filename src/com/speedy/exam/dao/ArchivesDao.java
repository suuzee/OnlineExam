package com.speedy.exam.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.speedy.exam.model.Archives;
import com.speedy.exam.model.Paper;

@Repository
public class ArchivesDao extends BaseDao{

	@Override
	public String getNamespace() {
		return "com.speedy.exam.dao.ArchivesDao.";
	}
	
	public List<Archives> getArchives(String personkey){
		return sqlSessionMyExam.selectList(getNamespace() + "getArchives",personkey);
	}
	
	public List<Paper> getMyInfo(String personkey){
		return sqlSessionMyExam.selectList(getNamespace() + "getMyInfo",personkey);
	}
	
}
