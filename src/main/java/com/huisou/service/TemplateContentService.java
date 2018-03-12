package com.huisou.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huisou.po.TemplateContentPo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.TemplateContentVo;

/**
* 类说明：
* @author 
* @version 创建时间：2018年3月8日 下午2:10:04
* 
*/
public interface TemplateContentService {



	void delete(Integer id);

	PageInfo<TemplateContentVo> findAll(PageTemp pageTemp);

	TemplateContentPo getById(Integer id);

	void deleteBatch(List<Integer> contentIds);

	void addOrEdit(TemplateContentPo po);

}
