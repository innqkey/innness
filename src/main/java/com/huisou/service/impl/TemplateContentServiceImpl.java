package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.TemplateContentPoMapper;
import com.huisou.po.TemplateContentPo;
import com.huisou.service.TemplateContentService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.TemplateContentVo;

/**
* 类说明：
* @author 
* @version 创建时间：2018年3月8日 下午2:10:21
* 
*/
@Service
public class TemplateContentServiceImpl implements TemplateContentService {
	
	@Autowired
	private TemplateContentPoMapper mapper;
	/**
	 * 删除就是修改对应的状态
	 */
	@Override
	public void delete(Integer id) {
		mapper.changeStatus(id);
		
	}

	@Override
	public PageInfo<TemplateContentVo> findAll(PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<TemplateContentVo> list = mapper.findAll();
		PageInfo<TemplateContentVo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public TemplateContentPo getById(Integer id) {
		return mapper.getById(id);
	}

	@Override
	public void deleteBatch(List<Integer> contentIds) {
		for (Integer contentId : contentIds) {
			mapper.changeStatus(contentId);
		}
		
	}

	@Override
	public void addOrEdit(TemplateContentPo po) {
		if (po.getMsgTemplateId() == null) {
			mapper.insertSelective(po);
		}else {
			mapper.updateByPrimaryKeySelective(po);
		}
	}

}
