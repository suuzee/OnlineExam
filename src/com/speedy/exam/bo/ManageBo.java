package com.speedy.exam.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.speedy.exam.dao.ManageDao;
import com.speedy.exam.dao.MaterialDao;
import com.speedy.exam.exception.KeyException;
import com.speedy.exam.model.Material;
import com.speedy.exam.model.Notice;

@Transactional(rollbackFor = Exception.class)
@Component
public class ManageBo extends BaseBean{
		
	@Autowired
	ManageDao manageDao;

	public int addNotice(Notice notice){
		try {
			notice.setNoticekey(generatorKey("t_e_notice", "NK"));
		} catch (KeyException e) {
		}
		int a = manageDao.addNotice(notice);
		return a;
	}
	
	public int delNotice(String noticekey){
		int a = manageDao.delNotice(noticekey);
		return a;
	}
	
	
}
