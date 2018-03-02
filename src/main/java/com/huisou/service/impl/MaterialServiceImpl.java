package com.huisou.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.MaterialPoMapper;
import com.huisou.mapper.MyStudyPoMapper;
import com.huisou.po.MaterialLevelPo;
import com.huisou.po.MaterialPo;
import com.huisou.po.MyStudyPo;
import com.huisou.service.MaterialService;
import com.huisou.vo.MaterialVo;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2018年1月29日
*/
@Service
public class MaterialServiceImpl implements MaterialService{

	@Autowired
	private MaterialPoMapper materialPoMapper;
	@Autowired
	private MyStudyPoMapper studyPoMapper;
	
	@Override
	public Integer addOne(MaterialPo materialPo) {
		int resId = materialPoMapper.insertSelective(materialPo);
		return resId;
	}
	
	@Override
	public MaterialPo findOne(Integer materialId) {
		return materialPoMapper.selectByPrimaryKey(materialId);
	}

	@Override
	public void updateOne(MaterialPo materialPo) {
		materialPoMapper.updateByPrimaryKeySelective(materialPo);
	}

	@Override
	public void deleteOne(List<String> materialId) {
		materialPoMapper.deleteOne(materialId);
	}

	@Override
	public PageInfo<MaterialVo> findAll(Map<String, String> maps, PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<MaterialVo> poList = materialPoMapper.findAll(maps);
		if (StringUtils.isNotBlank(maps.get("userId"))){
			Integer userId = Integer.parseInt(maps.get("userId"));
			if (null != poList && poList.size() > 0){
				Iterator<MaterialVo>iterator = poList.iterator();
				while (iterator.hasNext()) {
					MaterialVo po = iterator.next();
					Integer materialId = po.getMaterialId();
					List<MyStudyPo> findOne = studyPoMapper.findOne(userId, materialId,"KJ");
					if (null != findOne && findOne.size() > 0){
						po.setMaterialIspay("N");;
					}
				}
			}
		} 
		return new PageInfo<MaterialVo>(poList);
	}

	@Override
	public void increaseOne(Integer materialId) {
		materialPoMapper.increaseNum(materialId);
	}


}
