package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.TemplateSendHistoryPoMapper;
import com.huisou.po.TemplateSendHistoryPo;
import com.huisou.service.TemplateSendHistoryService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.TemplateHistoryVo;
import com.thoughtworks.xstream.mapper.Mapper;

/**
* 类说明：模板的数据库操作
* @author 
* @version 创建时间：2018年3月8日 下午4:21:29
* 
*/
@Service
public class TemplateSendHistoryServiceImpl implements TemplateSendHistoryService {

	@Autowired
	private TemplateSendHistoryPoMapper mapper;
	@Override
	public void insertOne(TemplateSendHistoryPo templateSendHistory) {
		mapper.insertSelective(templateSendHistory);
		
	}
	@Override
	public void deleteHistory(Integer historyId) {
		mapper.changeExistStatus(historyId);
	}
	/**
	 * 发现所有的状态为1的历史信息，包括了一个主题内容
	 */
	@Override
	public PageInfo<TemplateHistoryVo> findAll(PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<TemplateHistoryVo> list = mapper.findAll();
		PageInfo<TemplateHistoryVo> result = new PageInfo<TemplateHistoryVo>(list);
		return result;
	}
	@Override
	public void batchDeleteHistory(List<Integer> historyIds) {
		for (Integer historyId : historyIds) {
			mapper.changeExistStatus(historyId);
		}
		
	}
	

}
