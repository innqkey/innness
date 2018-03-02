package com.huisou.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.MaterialPo;
import com.huisou.vo.MaterialVo;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2018年1月29日
*/

public interface MaterialService {

	/**
	 * 添加一条资料
	 * @param materialPo
	 */
	Integer addOne(MaterialPo materialPo);

	/**
	 * 根据ID查找上传资料
	 * @param materialId
	 * @return
	 */
	MaterialPo findOne(Integer materialId);

	/**
	 * 更新上传资料
	 * @param materialPo
	 */
	void updateOne(MaterialPo materialPo);

	/**
	 * 批量/删除上传资料
	 * @param materialId
	 */
	void deleteOne(List<String> materialId);

	/**
	 * 资料列表
	 * @param materialName
	 * @param materialName
	 * @param materialLevelId
	 * @param beginDate
	 * @param endDate
	 * @param pageTemp
	 * @return
	 */
	PageInfo<MaterialVo> findAll(Map<String, String> maps,
			PageTemp pageTemp);

	/**
	 * 下载次数加1
	 * @param valueOf
	 */
	void increaseOne(Integer materialId);


}
