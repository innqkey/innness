package com.huisou.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.SayPo;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2018年1月29日
*/

public interface SayService {
	/**
	 * 新增一条语录
	 * @param sayPo
	 */
	void addOne(SayPo sayPo);

	/**
	 * 根据语录id查找一 条语录
	 * @param sayId
	 */
	SayPo findOne(Integer sayId);

	/**
	 * 根据语录id更新一条语录
	 * @param sayPo
	 */
	void updateOne(SayPo sayPo);
	
	/**
	 * 根据语录id删除一条语录
	 * @param sayId
	 */
	void deleteOne(List<String> sayId);

	/**
	 * 分页返回所有语录
	 * @param maps 
	 * @param pageTemp
	 * @return
	 */
	PageInfo<SayPo> findAll(Map<String, String> maps, PageTemp pageTemp);

	/**
	 * 点赞/评论/取消点赞/删除评论时修改对应数量
	 * @param sayId
	 * @param type:1 评论 type:2点赞
	 * @return
	 */
	void updateNum(Integer sayId, Integer type,Integer num);

	/**
	 * 根据时间返回最新的语录
	 * @return
	 */
	SayPo selectOne();

}
