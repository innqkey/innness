package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.RecentApplyPo;
import com.huisou.vo.OrderAndApplyVo;
import com.huisou.vo.RecentApplyVo;

public interface RecentApplyPoMapper {
    int deleteByPrimaryKey(Integer recentApplyId);

    int insert(RecentApplyPo record);

    int insertSelective(RecentApplyPo record);

    RecentApplyPo selectByPrimaryKey(Integer recentApplyId);

    int updateByPrimaryKeySelective(RecentApplyPo record);

    int updateByPrimaryKey(RecentApplyPo record);

	List<RecentApplyVo> findRecentApplyByrecentCourseId(@Param(value="recentCourseId")Integer recentCourseId);

	List<RecentApplyPo> findByUseridAndRecentCourseId(@Param(value="userId")Integer userId, @Param(value="recentCourseId")Integer recentCourseId);

	List<OrderAndApplyVo> findOrderAndApplyVo(Map map);

	void updateRecentCourseStatus(@Param(value="recentCourseId")Integer recentCourseId);
}