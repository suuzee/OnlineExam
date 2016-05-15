package com.speedy.exam.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedy.exam.bo.ManageBo;
import com.speedy.exam.model.Notice;

@Controller
public class ManageController {

	@Autowired
	ManageBo manageBo;
		
	@RequestMapping("addNotice")
	public String addNotice(Notice notice,Map<String,Object> map){	
		int a = manageBo.addNotice(notice);
		return "redirect:getNotice.do";
	}
	
	@RequestMapping("delNotice")
	public String delNotice(String noticekey){	
		int a = manageBo.delNotice(noticekey);
		return "redirect:getNotice.do";
	}
	/*@RequestMapping("importMaterial")
	public String importMaterial(HttpSession session,Map<String,Object> map){	
		String personkey=(String)session.getAttribute("personkey");
		List<Paper> papers = archivesBo.getMyInfo(personkey);
		map.put("paper",papers);
		List<Archives> archives = archivesBo.getArchives(personkey);
		Archives archive = archives.get(0);
		session.setAttribute("archive", archive);
		return "archives_page";
	}
	
	@RequestMapping("importTest")
	public String importTest(HttpSession session,Map<String,Object> map){	
		String personkey=(String)session.getAttribute("personkey");
		List<Paper> papers = archivesBo.getMyInfo(personkey);
		map.put("paper",papers);
		List<Archives> archives = archivesBo.getArchives(personkey);
		Archives archive = archives.get(0);
		session.setAttribute("archive", archive);
		return "archives_page";
	}*/
	
	
	
	
	
	
	
}