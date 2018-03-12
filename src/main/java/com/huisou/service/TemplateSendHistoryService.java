package com.huisou.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huisou.po.TemplateSendHistoryPo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.TemplateHistoryVo;

/**
* 类说明：
* @author 
* @version 创建时间：2018年3月8日 下午4:20:59
* 
*/
public interface TemplateSendHistoryService {

	void insertOne(TemplateSendHistoryPo templateSendHistory);

	void deleteHistory(Integer historyId);

	PageInfo<TemplateHistoryVo> findAll(PageTemp pageTemp);

	void batchDeleteHistory(List<Integer> historyIds);

}
