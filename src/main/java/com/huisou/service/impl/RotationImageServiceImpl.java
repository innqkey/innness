package com.huisou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.RotationImagePoMapper;
import com.huisou.po.RotationImagePo;
import com.huisou.service.RotationImageService;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年2月9日 上午9:15:48 
* 类说明 
*/
@Service
public class RotationImageServiceImpl implements RotationImageService{

	@Autowired
	private RotationImagePoMapper rotationImagePoMapper;
	
	@Override
	public void update(RotationImagePo rotationImagePo) {
		rotationImagePoMapper.updateByPrimaryKeySelective(rotationImagePo);
	}

	@Override
	public void add(RotationImagePo rotationImagePo) {
		rotationImagePoMapper.insertSelective(rotationImagePo);
	}

	@Override
	public PageInfo<RotationImagePo> search(PageTemp pageTemp,Map map) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<RotationImagePo> list = rotationImagePoMapper.search(map);
		return new PageInfo<>(list);
	}

	@Override
	public List<RotationImagePo> findByparames(Map map) {
		return rotationImagePoMapper.search(map);
	}

	@Override
	public RotationImagePo selectOne(Integer rotationImageId) {
		return rotationImagePoMapper.selectByPrimaryKey(rotationImageId);
	}

	@Override
	public RotationImagePo findOne(String linkUrlType) {
		List<RotationImagePo> imagePos = rotationImagePoMapper.findOne(linkUrlType);
		if (null != imagePos && imagePos.size() > 0){
			return imagePos.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void deleteImageByType(String linkUrlType) {
		rotationImagePoMapper.deleteImageByType(linkUrlType);
	}

}
