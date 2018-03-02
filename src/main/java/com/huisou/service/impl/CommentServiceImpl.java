package com.huisou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.CommentPoMapper;
import com.huisou.po.CommentPo;
import com.huisou.service.CommentService;
import com.huisou.vo.CommentVo;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2018年2月1日
*/
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentPoMapper poMapper;
	
	@Override
	public void addOne(CommentPo commentPo) {
		poMapper.insertSelective(commentPo);
	}

	@Override
	public void delete(List<String> commentId) {
		poMapper.delete(commentId);
	}

	@Override
	public PageInfo<CommentVo> queryByEmpParas(Map<String, String> maps, PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());	
		List<CommentVo> resultMap = poMapper.selectByEmpParas(maps);
		return new PageInfo<CommentVo>(resultMap);
	}

}
