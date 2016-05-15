package com.speedy.exam.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.speedy.exam.dao.MaterialDao;
import com.speedy.exam.model.Material;

@Transactional(rollbackFor = Exception.class)
@Component
public class MaterialBo extends BaseBean{
		
	@Autowired
	MaterialDao materialDao;

	public List<Material> getMaterial(){
		List<Material> material = materialDao.getMaterial();
		return material;
	}
	
	public List<Material> getMaterial1(){
		List<Material> material = materialDao.getMaterial1();
		return material;
	}
	
	public Material getMaterialByKey(String key){
		Material material = materialDao.getMaterialByKey(key);
		return material;
	}
	
}
