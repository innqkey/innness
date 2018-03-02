package com.huisou.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.SayPublisherPo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.SayPublisherVo;

/** 
* @author qinkai 
* @date 2018年2月2日
*/

public interface PublisherService {

	/**
	 * 查找语录发布人
	 * @param publisherId
	 * @return
	 */
	SayPublisherPo findOne(Integer publisherId);

	/**
	 * 更新语录发布人
	 * @param publisherPo
	 */
	void updateOne(SayPublisherPo publisherPo);

	/**
	 * 编辑语录发布人
	 * @param publisherPo
	 */
	void addOne(SayPublisherPo publisherPo);

	/**
	 * 删除语录发布人
	 * @param publisherId
	 * @return
	 */
	void deleteOne(List<String> publisherId);

	/**
	 * 根据条件返回所有语录发布人
	 * @param maps
	 * @param pageTemp
	 * @return
	 */
	PageInfo<SayPublisherVo> findAll(Map<String, String> maps, PageTemp pageTemp);

}
