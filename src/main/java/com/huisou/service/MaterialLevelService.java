package com.huisou.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.MaterialLevelPo;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2018年1月29日
*/

public interface MaterialLevelService {

	/**
	 * 添加资料二级菜单
	 * @param levelPo
	 */
	void addOne(MaterialLevelPo levelPo);

	/**
	 * 返回一个二级菜单
	 * @param parseInt
	 * @return
	 */
	MaterialLevelPo findOne(int parseInt);

	/**
	 * 更新一个二级菜单
	 * @param levelPo
	 */
	void updateOne(MaterialLevelPo levelPo);

	/**
	 * 删除二级菜单
	 * @param materialLevelId
	 */
	void deleteOne(List<String> materialLevelId);

	/**
	 * 资料分类列表
	 * @param maps
	 * @param pageTemp
	 * @return
	 */
	PageInfo<MaterialLevelPo> findAll(Map<String, String> maps, PageTemp pageTemp);

	/**
	 * 更新二级菜单下的资源数量
	 * @param materialLevelId
	 * @param num
	 */
	void updateLevelNum(Integer materialLevelId, Integer num);

	/**
	 * 返回所有一级菜单下的二级菜单
	 * @param maps
	 * @return
	 */
	List<MaterialLevelPo> selectAll(Map<String, String> maps);

}
