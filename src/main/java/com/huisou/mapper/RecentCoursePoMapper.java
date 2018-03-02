package com.huisou.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.RecentCoursePo;
import com.huisou.vo.RecentCourseVo;

public interface RecentCoursePoMapper {
    int deleteByPrimaryKey(Integer recentCourseId);

    int insert(RecentCoursePo record);

    int insertSelective(RecentCoursePo record);

    RecentCoursePo selectByPrimaryKey(Integer recentCourseId);

    int updateByPrimaryKeySelective(RecentCoursePo record);

    int updateByPrimaryKey(RecentCoursePo record);
    
    List<RecentCourseVo> search(@Param(value="courseTitle")String courseTitle, @Param(value="recentCourseTitle")String recentCourseTitle, @Param(value="startDate")Date startDate, @Param(value="endDate")Date endDate,
			@Param(value="recentCourseDeleteStatus")String recentCourseDeleteStatus);

	List<RecentCoursePo> findByAfterDate(@Param(value="now")Date now);
	
	List<RecentCoursePo> findAll();
}