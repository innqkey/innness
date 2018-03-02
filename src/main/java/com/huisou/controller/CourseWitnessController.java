package com.huisou.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.po.CourseWitnessPo;
import com.huisou.service.CourseWitnessService;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月29日 下午4:47:42 
* 类说明 
*/
@RestController
@RequestMapping(value = "/courseWitness")
public class CourseWitnessController extends BaseController{
	
	@Autowired
	private CourseWitnessService courseWitnessService;
	
	/**
	 * 添加一个见证内容
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addCourseWitness")
	public String addCourseWitness(HttpServletRequest request){
		try {
			String courseId = request.getParameter("courseId");
			String witnessContext = request.getParameter("witnessContext");
			String witnessLogo = request.getParameter("witnessLogo");
			if(StringUtils.isBlank(courseId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			CourseWitnessPo courseWitnessPo = new CourseWitnessPo();
			courseWitnessPo.setCourseId(Integer.parseInt(courseId));
			courseWitnessPo.setWitnessContext(witnessContext);
			courseWitnessPo.setWitnessLogo(witnessLogo);
			courseWitnessPo.setCreateTime(new Date());
			courseWitnessService.add(courseWitnessPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 添加和修改见证内容
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdate")
	public String addAndUpdate(HttpServletRequest request){
		try {
			String courseId = request.getParameter("courseId");
			String witnessId = request.getParameter("witnessId");
			String witnessContext = request.getParameter("witnessContext");
			String witnessLogo = request.getParameter("witnessLogo");
			if(StringUtils.isBlank(courseId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			CourseWitnessPo courseWitnessPo = new CourseWitnessPo();
			courseWitnessPo.setCourseId(Integer.parseInt(courseId));
			courseWitnessPo.setWitnessContext(witnessContext);
			courseWitnessPo.setWitnessLogo(witnessLogo);
			if(StringUtils.isBlank(witnessId)){
				courseWitnessPo.setCreateTime(new Date());
				courseWitnessService.add(courseWitnessPo);
			}else{
				courseWitnessPo.setWitnessId(Integer.parseInt(witnessId));
				courseWitnessPo.setUpdateTime(new Date());
				courseWitnessService.update(courseWitnessPo);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	
	/**
	 * 软删除见证内容
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteCourseWitness")
	public String deleteCourseWitness(HttpServletRequest request){
		try {
			String witnessId = request.getParameter("witnessId");
			if(StringUtils.isBlank(witnessId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			CourseWitnessPo courseWitnessPo = new CourseWitnessPo();
			courseWitnessPo.setWitnessId(Integer.parseInt(witnessId));
			courseWitnessPo.setWitnessStatus(ContextConstant.DELETE_STATUS);
			courseWitnessService.update(courseWitnessPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据课程Id查看见证人列表
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value = "/findWitnessByCourseId")
	public String findWitnessByCourseId(HttpServletRequest request,PageTemp pageTemp){
		try {
			String courseId = request.getParameter("courseId");
			if(StringUtils.isBlank(courseId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			PageInfo<CourseWitnessPo> result =  courseWitnessService.findWitnessByCourseId(Integer.parseInt(courseId),pageTemp);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 查询详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findOne")
	public String findOne(HttpServletRequest request){
		try {
			String witnessId = request.getParameter("witnessId");
			if(StringUtils.isBlank(witnessId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			CourseWitnessPo courseWitnessPo = courseWitnessService.findOne(Integer.parseInt(witnessId));
			return ResUtils.okRes(courseWitnessPo);
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
	public String deleteList(HttpServletRequest request,@RequestParam("witnessIds[]")List<Integer> witnessIds){
		try {
			if(witnessIds==null || witnessIds.size()==0){
				return ResUtils.errRes("102", "请求参数错误");
			}
			for (Integer witnessId : witnessIds) {
				CourseWitnessPo courseWitnessPo = new CourseWitnessPo();
				courseWitnessPo.setWitnessId(witnessId);
				courseWitnessPo.setWitnessStatus(ContextConstant.DELETE_STATUS);
				courseWitnessService.update(courseWitnessPo);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
		
	}
}
