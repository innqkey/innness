package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.CourseWitnessPo;

public interface CourseWitnessPoMapper {
    int deleteByPrimaryKey(Integer witnessId);

    int insert(CourseWitnessPo record);

    int insertSelective(CourseWitnessPo record);

    CourseWitnessPo selectByPrimaryKey(Integer witnessId);

    int updateByPrimaryKeySelective(CourseWitnessPo record);

    int updateByPrimaryKey(CourseWitnessPo record);

	List<CourseWitnessPo> findWitnessByCourseId(@Param(value="courseId")Integer courseId,@Param(value="witnessStatus")String witnessStatus);
}