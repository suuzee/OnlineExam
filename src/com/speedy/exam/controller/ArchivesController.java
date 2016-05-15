package com.speedy.exam.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedy.exam.bo.ArchivesBo;
import com.speedy.exam.model.Archives;
import com.speedy.exam.model.Paper;

@Controller
public class ArchivesController {

	@Autowired
	ArchivesBo archivesBo;

	@RequestMapping("myArchives")
	public String getMyInfo(HttpSession session,Map<String,Object> map){	
		String personkey=(String)session.getAttribute("personkey");
		List<Paper> papers = archivesBo.getMyInfo(personkey);
		map.put("paper",papers);
		List<Archives> archives = archivesBo.getArchives(personkey);
		Archives archive = archives.get(0);
		session.setAttribute("archive", archive);
		return "archives_page";
	}
	
	
	
	
}