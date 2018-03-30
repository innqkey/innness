package com.huisou.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.VideoAudioPo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.VideoAudioVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月29日 下午2:26:41 
* 类说明 
*/
public interface VideoAudioService {
	/**
	 * 修改视频对应的课程id
	 * @param videoAudioIds
	 * @param courseId
	 */
	void updateCourseId(List<Integer> videoAudioIds, Integer courseId);
	
	/**
	 * 根据courseId解除 所有对该课程id的视频 绑定
	 * @param courseId
	 */
	void resettingCourseId(Integer courseId);

	void addVideoAudio(VideoAudioPo videoAudioPo);

	void update(VideoAudioPo videoAudioPo);

	PageInfo<VideoAudioVo> search(String courseTitle, String videoAudioTitle,String videoAudioType, Date startDate, Date endDate,
			String videoAudioNum, PageTemp pageTemp);

	VideoAudioPo findOne(Integer videoAudioId);

	void addAndUpdate(VideoAudioPo videoAudioPo);

	List<VideoAudioPo> findVideoTop(String videoAudioIstop);

	PageInfo<VideoAudioVo> findVideoAudio(PageTemp pageTemp,String videoAudioType,String videoAudioIspay);

	List<VideoAudioPo> findVideoAudioByPayNum(String videoAudioType);

	List<VideoAudioVo> selectVoByPo(List<VideoAudioPo> list);

	List<VideoAudioPo> findRelevant(Integer videoAudioId);

	List<VideoAudioPo> findByCourseId(Integer courseId);

	PageInfo<VideoAudioVo> findVideoAudioByUserId(Map map,PageTemp pageTemp);

	List<VideoAudioPo> findVideoAndAudioByNoCourse(Integer courseId);
}
