package com.speedy.exam.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.speedy.exam.dao.ArchivesDao;
import com.speedy.exam.model.Archives;
import com.speedy.exam.model.Paper;

@Transactional(rollbackFor = Exception.class)
@Component
public class ArchivesBo extends BaseBean{
		
	@Autowired
	ArchivesDao archivesDao;

	public List<Archives> getArchives(String personkey){
		List<Archives> notices = archivesDao.getArchives(personkey);
		return notices;
	}
	
	public List<Paper> getMyInfo(String personkey){
		List<Paper> papers = archivesDao.getMyInfo(personkey);
		return papers;
	}
}
