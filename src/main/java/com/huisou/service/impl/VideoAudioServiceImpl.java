package com.huisou.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.OrderPoMapper;
import com.huisou.mapper.ResourcesEvalPoMapper;
import com.huisou.mapper.VideoAudioPoMapper;
import com.huisou.po.VideoAudioPo;
import com.huisou.service.VideoAudioService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.VideoAudioVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月29日 下午2:26:51 
* 类说明 
*/
@Service
public class VideoAudioServiceImpl implements VideoAudioService{
	
	@Autowired
	private VideoAudioPoMapper videoAudioPoMapper;
	
	@Autowired
	private ResourcesEvalPoMapper resourcesEvalPoMapper;
	
	@Autowired
	private OrderPoMapper orderPoMapper;
	
	@Override
	public void updateCourseId(List<Integer> videoAudioIds, Integer courseId) {
		for (Integer videoAudioId : videoAudioIds) {
			videoAudioPoMapper.updateCourseId(videoAudioId,courseId);
		}
		
	}

	@Override
	public void resettingCourseId(Integer courseId) {
		// TODO Auto-generated method stub
		videoAudioPoMapper.resettingCourseId(courseId);
	}

	@Override
	public void addVideoAudio(VideoAudioPo videoAudioPo) {
		// TODO Auto-generated method stub
		videoAudioPoMapper.insertSelective(videoAudioPo);
	}

	@Override
	public void update(VideoAudioPo videoAudioPo) {
		// TODO Auto-generated method stub
		videoAudioPoMapper.updateByPrimaryKeySelective(videoAudioPo);
	}

	@Override
	public PageInfo<VideoAudioVo> search(String courseTitle, String videoAudioTitle,String videoAudioType, Date startDate, Date endDate,String videoAudioNum,
			PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<VideoAudioVo> list = videoAudioPoMapper.search(courseTitle,videoAudioTitle,videoAudioType,startDate,endDate,videoAudioNum,ContextConstant.EXIST_STATUS);
		return new PageInfo<VideoAudioVo>(list);
	}

	@Override
	public VideoAudioPo findOne(Integer videoAudioId) {
		// TODO Auto-generated method stub
		VideoAudioPo videoAudioPo = videoAudioPoMapper.selectByPrimaryKey(videoAudioId);
		return videoAudioPo;
	}

	@Override
	public void addAndUpdate(VideoAudioPo videoAudioPo) {
		// TODO Auto-generated method stub
		if(videoAudioPo.getVideoAudioId()==null){
			videoAudioPo.setCreateTime(new Date());
			videoAudioPoMapper.insertSelective(videoAudioPo);
		}else{
			videoAudioPo.setUpdateTime(new Date());
			videoAudioPoMapper.updateByPrimaryKeySelective(videoAudioPo);
		}
	}

	@Override
	public List<VideoAudioPo> findVideoTop(String videoAudioIstop) {
		// TODO Auto-generated method stub
		List<VideoAudioPo> list = videoAudioPoMapper.findVideoTop(videoAudioIstop);
		return list;
	}

	@Override
	public PageInfo<VideoAudioVo> findVideoAudio(PageTemp pageTemp,String videoAudioType,String videoAudioIspay) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<VideoAudioPo> list = videoAudioPoMapper.findVideoAudio(videoAudioType,videoAudioIspay);
		List<VideoAudioVo> vdideoVoList = new ArrayList<>();
		for (VideoAudioPo videoAudioPo : list) {
			VideoAudioVo videoAudioVo = new VideoAudioVo();
			 try {
				BeanUtils.copyProperties(videoAudioVo, videoAudioPo);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			Integer avgEvalLevel = resourcesEvalPoMapper.selectavgEvalLevel(videoAudioPo.getVideoAudioId(),videoAudioPo.getVideoAudioType());
			videoAudioVo.setAvgEvalLevel(avgEvalLevel);
			vdideoVoList.add(videoAudioVo);
		}
		return new PageInfo<>(vdideoVoList);
	}

	@Override
	public List<VideoAudioPo> findVideoAudioByPayNum(String videoAudioType) {
		// TODO Auto-generated method stub
		List<VideoAudioPo> list = videoAudioPoMapper.findVideoAudioByPayNum(videoAudioType);
		return list;
	}

	@Override
	public List<VideoAudioVo> selectVoByPo(List<VideoAudioPo> list) {
		// TODO Auto-generated method stub
		List<VideoAudioVo> vdideoVoList = new ArrayList<>();
		for (VideoAudioPo videoAudioPo : list) {
			VideoAudioVo videoAudioVo = new VideoAudioVo();
			 try {
				BeanUtils.copyProperties(videoAudioVo, videoAudioPo);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			Integer avgEvalLevel = resourcesEvalPoMapper.selectavgEvalLevel(videoAudioPo.getVideoAudioId(),videoAudioPo.getVideoAudioType());
			videoAudioVo.setAvgEvalLevel(avgEvalLevel);
			vdideoVoList.add(videoAudioVo);
		}
		return vdideoVoList;
	}

	@Override
	public List<VideoAudioPo> findRelevant(Integer videoAudioId) {
		// TODO Auto-generated method stub
		VideoAudioPo videoAudioPo = videoAudioPoMapper.selectByPrimaryKey(videoAudioId);
		List<VideoAudioPo> list = videoAudioPoMapper.findRelevant(videoAudioPo.getCreateTime(),videoAudioId);
		return list;
	}

	@Override
	public List<VideoAudioPo> findByCourseId(Integer courseId) {
		// TODO Auto-generated method stub
		List<VideoAudioPo> list = videoAudioPoMapper.findByCourseId(courseId);
		return list;
	}

	@Override
	public List<VideoAudioVo> findVideoAudioByUserId(Map map) {
		// TODO Auto-generated method stub
		List<VideoAudioPo> list = videoAudioPoMapper.findVideoAudioByUserId(map);
		List<VideoAudioVo> voList = new ArrayList<>();
		for (VideoAudioPo videoAudioPo : list) {
			VideoAudioVo videoAudioVo = new VideoAudioVo();
			try {
				BeanUtils.copyProperties(videoAudioVo, videoAudioPo);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			Integer avgEvalLevel = resourcesEvalPoMapper.selectavgEvalLevel(videoAudioPo.getVideoAudioId(),videoAudioPo.getVideoAudioType());
			videoAudioVo.setAvgEvalLevel(avgEvalLevel);
			voList.add(videoAudioVo);
		}
		return voList;
	}
}
