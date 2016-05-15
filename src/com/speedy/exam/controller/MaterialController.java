package com.speedy.exam.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedy.exam.bo.MaterialBo;
import com.speedy.exam.model.Material;

@Controller
public class MaterialController {

	@Autowired
	MaterialBo materialBo;

	@RequestMapping("material")
	public String getMaterial(Map<String,Object> map){	
		List<Material> material = materialBo.getMaterial();
		List<Material> material1 = materialBo.getMaterial1();
		map.put("material",material);
		map.put("material1",material1);
		return "learning_page";
	}
	
	@RequestMapping("getDown")
	public String getDown(Map<String,Object> map,String materialkey){	
		Material material = materialBo.getMaterialByKey(materialkey);
		map.put("material",material);
		map.put("downpath", material.getDownloadpath());
		map.put("title", material.getTitle());
		return "down_page";
	}
	
	@RequestMapping("getDown1")
	public String getDown1(Map<String,Object> map,String materialkey){	
		Material material = materialBo.getMaterialByKey(materialkey);
		map.put("material",material);
		return "video_page";
	}
	
	@RequestMapping("moreMaterial")
	public String getMoreMaterial(Map<String,Object> map){	
		List<Material> material = materialBo.getMaterial();
		map.put("material",material);
		return "down_list";
	}
	
	@RequestMapping("moreMaterial1")
	public String getMoreMaterial1(Map<String,Object> map){	
		List<Material> material = materialBo.getMaterial1();
		map.put("material",material);
		return "video_list";
	}
	
}