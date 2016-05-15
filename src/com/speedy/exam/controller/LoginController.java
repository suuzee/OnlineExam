package com.speedy.exam.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedy.exam.bo.ArchivesBo;
import com.speedy.exam.bo.LoginBo;
import com.speedy.exam.model.Archives;
import com.speedy.exam.model.Person;

@Controller
public class LoginController {

	@Autowired
	LoginBo loginBo;
	@Autowired
	ArchivesBo archivesBo;

	@RequestMapping("login")
	public String loginPerson(Person person,String validateCode,HttpSession session,Map<String,Object> map){
		List<Person> count = loginBo.loginPerson(person);	
		String validateCodeSession=(String)session.getAttribute("validateCode");
		if(count.size()<1){
			map.put("mess","用户名或密码错误，请重新输入");
		}else if(!validateCode.equals(validateCodeSession)){
			map.put("mess","验证码错误，请重新输入");
		}
		if(count.size()>=1&&validateCode.equals(validateCodeSession)){
			session.setAttribute("personkey", count.get(0).getPersonkey());
			session.setAttribute("personname", count.get(0).getPersonname());
			Person p = loginBo.getPerson(count.get(0).getPersonkey());
			if(p==null){
				loginBo.addPerson(count.get(0));
			}
			List<Archives> archives = archivesBo.getArchives(count.get(0).getPersonkey());
			Archives archive = archives.get(0);
			session.setAttribute("archive", archive);//set到对象里
			return "redirect:notice.do";
		}
		else
			return "index";
	}
	
	@RequestMapping("notice")
	public String getNotices(Map<String,Object> map){
		List<com.speedy.exam.model.Notice> notices = loginBo.getNotices();
		map.put("notices", notices);
		return "first_page";
	}
	
	@RequestMapping("getNotice")
	public String getNotice(Map<String,Object> map){
		List<com.speedy.exam.model.Notice> notices = loginBo.getNotices();
		map.put("notices", notices);
		return "manage_notice";
	}
	
	
}