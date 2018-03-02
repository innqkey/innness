package com.huisou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.MaterialLevelPoMapper;
import com.huisou.po.MaterialLevelPo;
import com.huisou.po.MaterialPo;
import com.huisou.service.MaterialLevelService;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2018年1月29日
*/
@Service
public class MaterialLevelServiceImpl implements MaterialLevelService{

	@Autowired
	private MaterialLevelPoMapper levelPoMapper;
	
	@Override
	public void addOne(MaterialLevelPo levelPo) {
		levelPoMapper.insertSelective(levelPo);
	}

	@Override
	public MaterialLevelPo findOne(int materialLevelId) {
		return levelPoMapper.selectByPrimaryKey(materialLevelId);
	}

	@Override
	public void updateOne(MaterialLevelPo levelPo) {
		levelPoMapper.updateByPrimaryKeySelective(levelPo);
	}

	@Override
	public void deleteOne(List<String> materialLevelId) {
		levelPoMapper.deleteOne(materialLevelId);
	}

	@Override
	public PageInfo<MaterialLevelPo> findAll(Map<String, String> maps, PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<MaterialLevelPo> poList = levelPoMapper.findAll(maps);
		return new PageInfo<MaterialLevelPo>(poList);
	}

	@Override
	public void updateLevelNum(Integer materialLevelId, Integer num) {
		levelPoMapper.updateLevelNum(materialLevelId,num);
	}

	@Override
	public List<MaterialLevelPo> selectAll(Map<String, String> maps) {
		return levelPoMapper.findAll(maps);
	}

}
