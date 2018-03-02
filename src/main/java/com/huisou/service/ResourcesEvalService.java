package com.huisou.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.ResourcesEvalPo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.ResourcesEvalVo;

/** 
* @author qinkai 
* @date 2018年1月29日
*/

public interface ResourcesEvalService {

	/**
	 * 添加一条语录评论
	 * @param evalPo
	 */
	Integer addOne(ResourcesEvalPo evalPo);

	/**
	 * 视频/音频/语录评论列表
	 * @param maps
	 * @param pageTemp
	 * @return
	 */
	PageInfo<ResourcesEvalVo> findAll(Map<String, String> maps, PageTemp pageTemp);

	void update(ResourcesEvalPo resourcesEvalPo);

	/**
	 * 根据sayid查询所有评论
	 * @param sayId
	 * @return
	 */
	List<ResourcesEvalVo> selectAll(Integer sayId);


	/**
	 * 更新语录评论状态(type:1, 审核通过;type:2, 删除)
	 * @param evalId
	 * @param type
	 */
	void updateStatus(Map params);

	PageInfo<ResourcesEvalVo> findAllByMap(Map<String, String> map);

	PageInfo<ResourcesEvalVo> findAllByMapAndStatus(Map<String, String> map);

	/**
	 * 查找一条评论
	 * @param parseInt
	 * @return
	 */
	ResourcesEvalPo selectOne(Integer evalId);
}
