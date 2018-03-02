package com.huisou.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.VideoAudioPo;
import com.huisou.vo.VideoAudioVo;

public interface VideoAudioPoMapper {
    int deleteByPrimaryKey(Integer videoAudioId);

    int insert(VideoAudioPo record);

    int insertSelective(VideoAudioPo record);

    VideoAudioPo selectByPrimaryKey(Integer videoAudioId);

    int updateByPrimaryKeySelective(VideoAudioPo record);

    int updateByPrimaryKey(VideoAudioPo record);

	void updateCourseId(@Param(value = "videoAudioId")Integer videoAudioId, @Param(value = "courseId")Integer courseId);

	void resettingCourseId(@Param(value = "courseId")Integer courseId);

	List<VideoAudioVo> search(@Param(value = "courseTitle")String courseTitle, @Param(value = "videoAudioTitle")String videoAudioTitle, @Param(value = "videoAudioType")String videoAudioType,@Param(value = "startDate")Date startDate, @Param(value = "endDate")Date endDate,@Param(value = "videoAudioNum")String videoAudioNum,@Param(value = "videoAudioStatus")String videoAudioStatus);

	List<VideoAudioPo> findVideoTop(@Param(value = "videoAudioIstop")String videoAudioIstop);

	List<VideoAudioPo> findVideoAudio(@Param(value = "videoAudioType")String videoAudioType,@Param(value = "videoAudioIspay")String videoAudioIspay);

	List<VideoAudioPo> findVideoAudioByPayNum(@Param(value = "videoAudioType")String videoAudioType);

	List<VideoAudioPo> findRelevant(@Param(value = "createTime")Date createTime, @Param(value = "videoAudioId")Integer videoAudioId);

	List<VideoAudioPo> findByCourseId(@Param(value = "courseId")Integer courseId);

	List<VideoAudioPo> findVideoAudioByUserId(Map map);

}