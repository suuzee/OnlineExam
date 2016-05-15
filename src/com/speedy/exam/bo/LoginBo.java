package com.speedy.exam.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.speedy.exam.dao.LoginDao;
import com.speedy.exam.dao.NoticeDao;
import com.speedy.exam.model.Notice;
import com.speedy.exam.model.Person;

@Transactional(rollbackFor = Exception.class)
@Component
public class LoginBo extends BaseBean{
		
	@Autowired
	LoginDao loginDao;
	@Autowired
	NoticeDao noticeDao;
	
	public List<Person> loginPerson(Person person){
		List<Person> persons = loginDao.loginPerson(person);
		return persons;
	}
	
	public List<Notice> getNotices(){
		List<Notice> notices = noticeDao.getNotices();
		return notices;
	}
	
	public Person getPerson(String personkey){
		Person person = loginDao.getPerson(personkey);
		return person;
	}
	
	public int addPerson(Person person){
		person.setFloor("00");
		person.setCredit(0);
		person.setValidflag("Y");
		person.setPersoncode(this.getMax("personcode"));
		loginDao.addPerson(person);
		return 1;
	}
	
}
