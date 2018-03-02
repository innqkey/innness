package com.huisou.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.CoursePo;

public interface CoursePoMapper {
    int deleteByPrimaryKey(Integer courseId);

    int insert(CoursePo record);

    int insertSelective(CoursePo record);

    CoursePo selectByPrimaryKey(Integer courseId);

    int updateByPrimaryKeySelective(CoursePo record);

    int updateByPrimaryKeyWithBLOBs(CoursePo record);

    int updateByPrimaryKey(CoursePo record);
    
    List<CoursePo> search(@Param(value = "courseTitle")String courseTitle, @Param(value = "startDate")Date startDate, @Param(value = "endDate")Date endDate,@Param(value = "courseStatus")String courseStatus);

    List<CoursePo> findCourseByUserid(@Param(value = "userid")Integer userid);

}