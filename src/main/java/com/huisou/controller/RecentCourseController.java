package com.huisou.controller;

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
import com.huisou.po.RecentCoursePo;
import com.huisou.po.RegionPo;
import com.huisou.service.RecentCourseService;
import com.huisou.service.RegionService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RecentCourseVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 下午2:45:55 
* 类说明 
*/
@RestController
@RequestMapping(value = "/recentCourse")
public class RecentCourseController extends BaseController{

	@Autowired
	private RecentCourseService recentCourseService;
	
	@Autowired
	private RegionService regionService;
	
	/**
	 * 添加一条新的课程记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request){
		try {
			String recentCourseTitle = request.getParameter("recentCourseTitle");
			String recentCourseAddress = request.getParameter("recentCourseAddress");
			String recentCourseMaxNum = request.getParameter("recentCourseMaxNum");
			String courseId = request.getParameter("courseId");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			if(StringUtils.isBlank(courseId) || StringUtils.isBlank(beginTime) || StringUtils.isBlank(endTime) ){
				return ResUtils.errRes("102", "请求参数");
			}
			RecentCoursePo recentCoursePo = new RecentCoursePo();
			recentCoursePo.setRecentCourseTitle(recentCourseTitle);
			recentCoursePo.setRecentCourseAddress(recentCourseAddress);
			recentCoursePo.setRecentCourseMaxNum(recentCourseMaxNum);
			recentCoursePo.setCourseId(Integer.parseInt(courseId));
			recentCoursePo.setBeginTime(DateUtils.formatStringToDate(beginTime, DateUtils.Y_M_D));
			recentCoursePo.setEndTime(DateUtils.formatStringToDate(endTime, DateUtils.Y_M_D));
			recentCoursePo.setRecentCourseApplyNum("0");
			//设置状态为未上课
			recentCoursePo.setRecentCourseStatus("1");
			recentCoursePo.setRecentCourseDeleteStatus(ContextConstant.EXIST_STATUS);
			recentCoursePo.setCreateTime(new Date());
			recentCourseService.add(recentCoursePo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 添加或修改一条新的近期课程记录(包括修改是否上课)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdate")
	public String addAndUpdate(HttpServletRequest request){
		try {
			String recentCourseId = request.getParameter("recentCourseId");
			String recentCourseTitle = request.getParameter("recentCourseTitle");
			String recentCourseAddress = request.getParameter("recentCourseAddress");
			String recentCourseMaxNum = request.getParameter("recentCourseMaxNum");
			String courseId = request.getParameter("courseId");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			String recentCourseStatus = request.getParameter("recentCourseStatus");
			String regionsIds = request.getParameter("regionsIds");
			if( StringUtils.isBlank(courseId) || StringUtils.isBlank(beginTime) || StringUtils.isBlank(endTime) || StringUtils.isBlank(regionsIds)){
				return ResUtils.errRes("102", "请求参数");
			}
			RecentCoursePo recentCoursePo = new RecentCoursePo();
			String[] ids= regionsIds.split(",");
			if(ids.length==1){
				RegionPo provinceRegionPo = regionService.findRegionById(ids[0]);
				if(null != provinceRegionPo){
					recentCoursePo.setProvince(provinceRegionPo.getName());
				}
			}else if(ids.length==2){
				RegionPo provinceRegionPo = regionService.findRegionById(ids[0]);
				if(null != provinceRegionPo){
					recentCoursePo.setProvince(provinceRegionPo.getName());
				}
				RegionPo cityRegionPo = regionService.findRegionById(ids[1]);
				if(null != cityRegionPo){
					recentCoursePo.setCity(cityRegionPo.getName());
				}
			}else if(ids.length==3){
				RegionPo provinceRegionPo = regionService.findRegionById(ids[0]);
				if(null != provinceRegionPo){
					recentCoursePo.setProvince(provinceRegionPo.getName());
				}
				RegionPo cityRegionPo = regionService.findRegionById(ids[1]);
				if(null != cityRegionPo){
					recentCoursePo.setCity(cityRegionPo.getName());
				}
				RegionPo areaRegionPo = regionService.findRegionById(ids[2]);
				if(null != areaRegionPo){
					recentCoursePo.setArea(areaRegionPo.getName());
				}
			}
			recentCoursePo.setRegionsids(regionsIds);
			recentCoursePo.setRecentCourseTitle(recentCourseTitle);
			recentCoursePo.setRecentCourseAddress(recentCourseAddress);
			recentCoursePo.setRecentCourseMaxNum(recentCourseMaxNum);
			recentCoursePo.setCourseId(Integer.parseInt(courseId));
			recentCoursePo.setBeginTime(DateUtils.formatStringToDate(beginTime, DateUtils.Y_M_D_HMS));
			recentCoursePo.setEndTime(DateUtils.formatStringToDate(endTime, DateUtils.Y_M_D_HMS));
			recentCoursePo.setRecentCourseStatus(recentCourseStatus);
			if(StringUtils.isBlank(recentCourseId) ){
				recentCoursePo.setCreateTime(new Date());
				recentCourseService.add(recentCoursePo);
			}else{
				recentCoursePo.setUpdateTime(new Date());
				recentCoursePo.setRecentCourseId(Integer.parseInt(recentCourseId));
				recentCourseService.update(recentCoursePo);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 软删除（改变状态）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request){
		try {
			String recentCourseId = request.getParameter("recentCourseId");
			if(StringUtils.isBlank(recentCourseId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			RecentCoursePo recentCoursePo = new RecentCoursePo();
			recentCoursePo.setRecentCourseId(Integer.parseInt(recentCourseId));
			recentCoursePo.setUpdateTime(new Date());
			recentCoursePo.setRecentCourseDeleteStatus(ContextConstant.DELETE_STATUS);
			recentCourseService.update(recentCoursePo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 近期课程列表页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,PageTemp pageTemp){
		try {
			String courseTitle = request.getParameter("courseTitle");
			String recentCourseTitle = request.getParameter("recentCourseTitle");
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
			PageInfo<RecentCourseVo> result = recentCourseService.search(courseTitle,recentCourseTitle,startDate,endDate,pageTemp);
			List<RecentCourseVo> list = result.getList();
			for (RecentCourseVo recentCourseVo : list) {
				String address = recentCourseVo.getRecentCourseAddress();
				String regionsids = recentCourseVo.getRegionsids();
				String[] ids = regionsids.split(",");
				if(ids.length==1){
					RegionPo provinceRegionPo = regionService.findRegionById(ids[0]);
					if(null != provinceRegionPo){
						address = provinceRegionPo.getName()+address;
					}
				}else if(ids.length==2){
					RegionPo provinceRegionPo = regionService.findRegionById(ids[0]);
					RegionPo cityRegionPo = regionService.findRegionById(ids[1]);
					if(null != cityRegionPo && null != provinceRegionPo){
						address = provinceRegionPo.getName()+cityRegionPo.getName()+address;
					}
				}else if(ids.length==3){
					RegionPo provinceRegionPo = regionService.findRegionById(ids[0]);
					RegionPo cityRegionPo = regionService.findRegionById(ids[1]);
					RegionPo areaRegionPo = regionService.findRegionById(ids[2]);
					if(null != areaRegionPo && null != provinceRegionPo && null != cityRegionPo){
						address = provinceRegionPo.getName()+cityRegionPo.getName()+areaRegionPo.getName()+address;
					}
				}
				recentCourseVo.setRecentCourseAddress(address);
			}
			result.setList(list);
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
			String recentCourseId = request.getParameter("recentCourseId");
			if(StringUtils.isBlank(recentCourseId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			RecentCoursePo recentCoursePo = recentCourseService.findOneByrecentCourseId(Integer.parseInt(recentCourseId));
			return ResUtils.okRes(recentCoursePo);
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
	public String deleteList(HttpServletRequest request,@RequestParam("recentCourseIds[]")List<Integer> recentCourseIds){
		try {
			if(recentCourseIds==null || recentCourseIds.size()==0){
				return ResUtils.errRes("102", "请求参数错误");
			}
			for (Integer recentCourseId : recentCourseIds) {
				RecentCoursePo recentCoursePo =new RecentCoursePo();
				recentCoursePo.setRecentCourseId(recentCourseId);
				recentCoursePo.setRecentCourseDeleteStatus(ContextConstant.DELETE_STATUS);
				recentCourseService.update(recentCoursePo);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
		
	}
	
	/**
	 * 前台近期课程
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findAllByUserId")
	public String findAllByUserId(HttpServletRequest request){
		try {
			String userToken = request.getParameter("userToken");
			if(StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			Date now = new Date();
			List<RecentCourseVo> list = recentCourseService.findAllByUserId(super.getUserIdByToken(userToken),now);
			for (RecentCourseVo recentCourseVo : list) {
				String address = recentCourseVo.getRecentCourseAddress();
				String regionsids = recentCourseVo.getRegionsids();
				String[] ids = regionsids.split(",");
				if(ids.length==1){
					RegionPo provinceRegionPo = regionService.findRegionById(ids[0]);
					if(null != provinceRegionPo){
						address = provinceRegionPo.getName()+address;
					}
				}else if(ids.length==2){
					RegionPo provinceRegionPo = regionService.findRegionById(ids[0]);
					RegionPo cityRegionPo = regionService.findRegionById(ids[1]);
					if(null != cityRegionPo && null != provinceRegionPo){
						address = provinceRegionPo.getName()+cityRegionPo.getName()+address;
					}
				}else if(ids.length==3){
					RegionPo provinceRegionPo = regionService.findRegionById(ids[0]);
					RegionPo cityRegionPo = regionService.findRegionById(ids[1]);
					RegionPo areaRegionPo = regionService.findRegionById(ids[2]);
					if(null != areaRegionPo && null != provinceRegionPo && null != cityRegionPo){
						address = provinceRegionPo.getName()+cityRegionPo.getName()+areaRegionPo.getName()+address;
					}
				}
				recentCourseVo.setRecentCourseAddress(address);
			}
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
