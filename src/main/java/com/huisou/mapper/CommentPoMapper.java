package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import com.huisou.po.CommentPo;
import com.huisou.vo.CommentVo;

public interface CommentPoMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(CommentPo record);

    int insertSelective(CommentPo record);

    CommentPo selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(CommentPo record);

    int updateByPrimaryKey(CommentPo record);

	void delete(List<String> commentId);

	List<CommentVo> selectByEmpParas(Map<String, String> maps);
}