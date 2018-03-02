package com.huisou.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.ResourcesThumbsPo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.ResourcesThumbsVo;

/** 
* @author qinkai 
* @date 2018年1月29日
*/

public interface ResourcesThumbsService {

	/**
	 * 语录点赞
	 * @param thumbsPo
	 */
	Integer addOne(ResourcesThumbsPo thumbsPo);

	/**
	 * 取消语录点赞
	 * @param resId
	 * @param type
	 */
	void deleteOne(Integer userId,Integer resId);

	/**
	 * 查找用户是否点赞(语录)
	 * @param resId
	 * @param userId
	 * @return
	 */
	ResourcesThumbsPo findOne(Integer resId, Integer userId);

	/**
	 * 语录点赞
	 * @param findOne
	 */
	void updateOne(ResourcesThumbsPo findOne);

	/**
	 * 语录点赞列表
	 * @param maps
	 * @param pageTemp
	 * @return
	 */
	PageInfo<ResourcesThumbsVo> findAll(Map<String, String> maps, PageTemp pageTemp);

	/**
	 * 根据sayid查找所有点赞
	 * @param sayId
	 * @return
	 */
	List<ResourcesThumbsVo> selectAll(Integer sayId);


}
