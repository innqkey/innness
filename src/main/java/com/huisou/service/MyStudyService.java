package com.huisou.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.MyStudyPo;
import com.huisou.vo.MyStudyVo;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2018年2月6日
*/

public interface MyStudyService {

	/**
	 * 添加一条的我学习记录
	 * @param studyPo
	 */
	void insertStudyRecord(MyStudyPo studyPo);

	/**
	 * 根据资源类型和userid查找购买的资料
	 */
	PageInfo<MyStudyVo> findAll(Map<String, String> maps, PageTemp pageTemp);

}
