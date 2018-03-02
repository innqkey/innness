package com.huisou.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.CommentPo;
import com.huisou.vo.CommentVo;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2018年2月1日
*/

public interface CommentService {

	/**
	 * 添加意见反馈
	 * @param commentPo
	 */
	void addOne(CommentPo commentPo);

	/**
	 * 删除意见反馈
	 * @param commentId
	 */
	void delete(List<String> commentId);

	/**
	 * 查找/返回所有意见反馈
	 * @param maps
	 * @param pageTemp
	 * @return
	 */
	PageInfo<CommentVo> queryByEmpParas(Map<String, String> maps, PageTemp pageTemp);

}
