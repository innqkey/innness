package com.huisou.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.DateUtils;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.po.CoursePo;
import com.huisou.service.CourseService;
import com.huisou.service.VideoAudioService;
import com.huisou.vo.CourseVo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月29日 上午10:54:31 
* 类说明    ：课程
*/
@RestController
@RequestMapping(value = "/course")
public class CourseController extends BaseController{

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private VideoAudioService videoAudioService;
	
	/**
	 * 添加课程(添加课程的时候选择对应的视频)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addCourse")
	public String addCourse(HttpServletRequest request){
		try {
			String courseTitle = request.getParameter("courseTitle");
			String coursePrice = request.getParameter("coursePrice");
			String courseIntro = request.getParameter("courseIntro");
			String courseSpeaker = request.getParameter("courseSpeaker");
			String courseDuration = request.getParameter("courseDuration");
			String courseLogo = request.getParameter("courseLogo");
			String courseDetail = request.getParameter("courseDetail");
			String videoAudioIds = request.getParameter("videoAudioIds");
			if(StringUtils.isBlank(videoAudioIds)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<Integer> videoAudioIdList = new ArrayList<Integer>();
			String[] array = videoAudioIds.split(",");
			for (String videoAudioId : array) {
				videoAudioIdList.add(Integer.parseInt(videoAudioId));
			}
			Date createTime = new Date();
			CoursePo coursePo = new CoursePo();
			coursePo.setCourseTitle(courseTitle);
			coursePo.setCoursePrice(new BigDecimal(coursePrice));
			coursePo.setCourseIntro(courseIntro);
			coursePo.setCourseSpeaker(courseSpeaker);
			coursePo.setCourseDuration(courseDuration);
			coursePo.setCourseLogo(courseLogo);
			coursePo.setCourseDetail(courseDetail);
			coursePo.setCreateTime(createTime);
			coursePo.setCourseStatus(ContextConstant.EXIST_STATUS);
			courseService.addCourse(coursePo);
			videoAudioService.updateCourseId(videoAudioIdList,coursePo.getCourseId());
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 修改课程的信息
	 * @param request
	 * @param videoAudioIds
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdate")
	public String addAndUpdate(HttpServletRequest request){
		try {
			String courseId = request.getParameter("courseId");
			String courseTitle = request.getParameter("courseTitle");
			String coursePrice = request.getParameter("coursePrice");
			String courseIntro = request.getParameter("courseIntro");
			String courseSpeaker = request.getParameter("courseSpeaker");
			String courseDuration = request.getParameter("courseDuration");
			String coursePicture = request.getParameter("coursePicture");
			String courseLogo = request.getParameter("courseLogo");
			String introvideoUrl = request.getParameter("introvideoUrl");
			String courseDetail = request.getParameter("courseDetail");
			String videoAudioIds = request.getParameter("videoAudioIds");
			if(StringUtils.isBlank(videoAudioIds)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<Integer> videoAudioIdList = new ArrayList<Integer>();
			String[] array = videoAudioIds.split(",");
			for (String videoAudioId : array) {
				if(!StringUtils.isNumeric(videoAudioId)){
					return ResUtils.errRes("102", "请求参数错误");
				}
				videoAudioIdList.add(Integer.parseInt(videoAudioId));
			}
			CoursePo coursePo = new CoursePo();
			coursePo.setCourseTitle(courseTitle);
			coursePo.setCoursePrice(new BigDecimal(coursePrice));
			coursePo.setCourseIntro(courseIntro);
			coursePo.setCourseSpeaker(courseSpeaker);
			coursePo.setCourseDuration(courseDuration);
			coursePo.setCoursePicture(coursePicture);
			coursePo.setCourseLogo(courseLogo);
			coursePo.setCourseDetail(courseDetail);
			coursePo.setIntrovideoUrl(introvideoUrl);
			if(StringUtils.isBlank(courseId)){
				coursePo.setCreateTime(new Date());
				courseService.addCourse(coursePo);
				 courseId =coursePo.getCourseId().toString();
			}else{
				coursePo.setCourseId(Integer.parseInt(courseId));
				coursePo.setUpdateTime(new Date());
				courseService.updateCourse(coursePo);
			}
			videoAudioService.resettingCourseId(Integer.parseInt(courseId));
			videoAudioService.updateCourseId(videoAudioIdList, Integer.parseInt(courseId));
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 软删除课程
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteCourse")
	public String deleteCourse(HttpServletRequest request){
		try {
			String courseId = request.getParameter("courseId");
			if(StringUtils.isBlank(courseId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			CoursePo coursePo = new CoursePo();
			coursePo.setCourseId(Integer.parseInt(courseId));
			coursePo.setCourseStatus(ContextConstant.DELETE_STATUS);
			courseService.updateCourse(coursePo);
			videoAudioService.resettingCourseId(Integer.parseInt(courseId));
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 按条件搜索课程列表首页
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,PageTemp pageTemp){
		try {
			String courseTitle = request.getParameter("courseTitle");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			Date startDate = null;
			Date endDate = null;
			if(StringUtils.isNotBlank(startTime)){
				startDate = DateUtils.format(startTime, DateUtils.Y_M_D);
			}
			if(StringUtils.isNotBlank(endTime)){
				endDate = DateUtils.format(endTime, DateUtils.Y_M_D);
			}
			PageInfo<CoursePo> result = courseService.search(courseTitle,startDate,endDate,pageTemp);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	
	/**
	 * 查看详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findOne")
	public String findOne(HttpServletRequest request){
		try {
			String courseId = request.getParameter("courseId"); 
			if(StringUtils.isBlank(courseId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			CourseVo courseVo = courseService.findOne(Integer.parseInt(courseId));
			return ResUtils.okRes(courseVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 批量删除
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteList")
	public String deleteList(HttpServletRequest request,@RequestParam("courseIds[]")List<Integer> courseIds){
		try {
			if(courseIds==null || courseIds.size()==0){
				return ResUtils.errRes("102", "请求参数错误");
			}
			for (Integer courseId : courseIds) {
				CoursePo coursePo = new CoursePo();
				coursePo.setCourseId(courseId);
				coursePo.setCourseStatus(ContextConstant.DELETE_STATUS);
				courseService.updateCourse(coursePo);
				videoAudioService.resettingCourseId(courseId);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/*
	 * 根据用户id获取对应的已经报名的课程
	 */
	@RequestMapping(value = "/findCourseByUserid")
	public String findCourseByUserid(HttpServletRequest request){
		try {
			String userToken = request.getParameter("userToken");
			if(StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<CoursePo> list = courseService.findCourseByUserid(super.getUserIdByToken(userToken));
			return ResUtils.okRes(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 查找所有存在的课程
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findAll")
	public String findAll(HttpServletRequest request){
		try {
			List<CoursePo> list = courseService.findAll();
			return ResUtils.okRes(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
