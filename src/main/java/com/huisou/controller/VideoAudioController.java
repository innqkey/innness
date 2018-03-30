package com.huisou.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.DateUtils;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.po.UserPo;
import com.huisou.po.VideoAudioPo;
import com.huisou.po.VisitRecordPo;
import com.huisou.service.OrderService;
import com.huisou.service.UserService;
import com.huisou.service.VideoAudioService;
import com.huisou.service.VisitRecordService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.VideoAudioVo;

import me.chanjar.weixin.mp.bean.message.WxMpXmlOutVideoMessage.Video;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月29日 下午6:43:56 
* 类说明 
*/
@RestController
@RequestMapping(value = "/videoAudio")
public class VideoAudioController extends BaseController{
 
	@Autowired
	private VideoAudioService videoAudioService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	//未支付或者支付失败返回的视频资源路径
	@Value(value = "${nopay.res.video.url}")
	private String noPayResVideoUrl;
	
	//未支付或者支付失败返回的视频资源路径
	@Value(value = "${nopay.res.audio.url}")
	private String noPayResAudioUrl;
	
	@Autowired
	private VisitRecordService visitRecordService;
	
	/**
	 * 添加一个新的视频音频
	 * @param request
	 * @return
	 *//*
	@RequestMapping(value = "/addVideoAudio")
	public String addVideoAudio(HttpServletRequest request){
		try {
			String videoAudioTitle = request.getParameter("videoAudioTitle");
			String videoAudioPrice = request.getParameter("videoAudioPrice");
			String videoAudioIntro = request.getParameter("videoAudioIntro");
			String videoAudioLogo = request.getParameter("videoAudioLogo");
			String videoAudioType = request.getParameter("videoAudioType");
			String videoAudioNo = request.getParameter("videoAudioNo");
			String courseId = request.getParameter("courseId");
			String videoAudioIstop = request.getParameter("videoAudioIstop");
			String videoAudioIsonline = request.getParameter("videoAudioIsonline");
			String videoAudioUrl = request.getParameter("videoAudioUrl");
			VideoAudioPo videoAudioPo = new VideoAudioPo();
			videoAudioPo.setCreateTime(new Date());
			videoAudioPo.setVideoAudioStatus(ContextConstant.EXIST_STATUS);
			videoAudioPo.setVideoAudioTitle(videoAudioTitle);
			videoAudioPo.setVideoAudioPrice(new BigDecimal(videoAudioPrice));
			videoAudioPo.setVideoAudioIntro(videoAudioIntro);
			videoAudioPo.setVideoAudioLogo(videoAudioLogo);
			videoAudioPo.setVideoAudioNo(Integer.parseInt(videoAudioNo));
			videoAudioPo.setVideoAudioType(videoAudioType);
			videoAudioPo.setCourseId(Integer.parseInt(courseId));
			videoAudioPo.setVideoAudioIscourse(ContextConstant.NO);
			videoAudioPo.setVideoAudioUrl(videoAudioUrl);
			if(StringUtils.isBlank(videoAudioPrice) || Integer.parseInt(videoAudioPrice)==0){
				videoAudioPo.setVideoAudioIspay(ContextConstant.NO);
			}else{
				videoAudioPo.setVideoAudioIspay(ContextConstant.YES);
			}
			videoAudioService.addVideoAudio(videoAudioPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}*/
	
	/**
	 *添加和编辑修改视频音频
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdate")
	public String addAndUpdate(HttpServletRequest request){
		try {
			String videoAudioTitle = request.getParameter("videoAudioTitle");
			String videoAudioPrice = request.getParameter("videoAudioPrice");
			String videoAudioIntro = request.getParameter("videoAudioIntro");
			String videoAudioLogo = request.getParameter("videoAudioLogo");
			String videoAudioNo = request.getParameter("videoAudioNo");
			String videoAudioIstop = request.getParameter("videoAudioIstop");
			String videoAudioId = request.getParameter("videoAudioId");
			String videoAudioUrl = request.getParameter("videoAudioUrl");
			String videoAudioType = request.getParameter("videoAudioType");
			String videoAudioPayNum = request.getParameter("videoAudioPayNum");
			VideoAudioPo videoAudioPo = new VideoAudioPo();
			if(StringUtils.isNotBlank(videoAudioId)){
				videoAudioPo.setVideoAudioId(Integer.parseInt(videoAudioId));
			}
			videoAudioPo.setVideoAudioPayNum(Integer.parseInt(videoAudioPayNum));
			videoAudioPo.setVideoAudioTitle(videoAudioTitle);
			videoAudioPo.setVideoAudioPrice(new BigDecimal(videoAudioPrice));
			videoAudioPo.setVideoAudioIntro(videoAudioIntro);
			videoAudioPo.setVideoAudioLogo(videoAudioLogo);
			videoAudioPo.setVideoAudioNo(Integer.parseInt(videoAudioNo));
			videoAudioPo.setVideoAudioUrl(videoAudioUrl);
			videoAudioPo.setVideoAudioType(videoAudioType);
			videoAudioPo.setVideoAudioIstop(videoAudioIstop);
			videoAudioPo.setVideoAudioStatus(ContextConstant.EXIST_STATUS);
			if(StringUtils.isBlank(videoAudioPrice) || BigDecimal.ZERO.equals(new BigDecimal(videoAudioPrice))){
				videoAudioPo.setVideoAudioIspay(ContextConstant.NO);
			}else{
				videoAudioPo.setVideoAudioIspay(ContextConstant.YES);
			}
			videoAudioService.addAndUpdate(videoAudioPo);
			super.addAndUpdate(request, videoAudioPo.getVideoAudioId(), videoAudioPo.getVideoAudioType());
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 后台管理视频音频首页展示
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,PageTemp pageTemp){
		try {
			String courseTitle = request.getParameter("courseTitle");
			String videoAudioTitle = request.getParameter("videoAudioTitle");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String videoAudioType = request.getParameter("videoAudioType");
			String videoAudioNum= request.getParameter("videoAudioNum");
			Date startDate = null;
			Date endDate = null;
			if(StringUtils.isNotBlank(startTime)){
				startDate = DateUtils.format(startTime, DateUtils.Y_M_D);
			}
			if(StringUtils.isNotBlank(endTime)){
				endDate = DateUtils.format(endTime, DateUtils.Y_M_D);
			}
			PageInfo<VideoAudioVo> result = videoAudioService.search(courseTitle,videoAudioTitle,videoAudioType,startDate,endDate,videoAudioNum,pageTemp);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 软删除视频音频
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request){
		try {
			String videoAudioId = request.getParameter("videoAudioId"); 
			if(StringUtils.isBlank(videoAudioId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			VideoAudioPo videoAudioPo = new VideoAudioPo();
			videoAudioPo.setVideoAudioId(Integer.parseInt(videoAudioId));
			videoAudioPo.setVideoAudioStatus(ContextConstant.DELETE_STATUS);
			videoAudioService.update(videoAudioPo);
			return ResUtils.okRes();
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
			String videoAudioId = request.getParameter("videoAudioId"); 
			if(StringUtils.isBlank(videoAudioId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			VideoAudioPo videoAudioPo = videoAudioService.findOne(Integer.parseInt(videoAudioId));
			return ResUtils.okRes(videoAudioPo);
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
	public String deleteList(HttpServletRequest request,@RequestParam("videoAudioIds[]")List<Integer> videoAudioIds){
		try {
			if(videoAudioIds==null || videoAudioIds.size()==0){
				return ResUtils.errRes("102", "请求参数错误");
			}
			for (Integer videoAudioId : videoAudioIds) {
				VideoAudioPo videoAudioPo = new VideoAudioPo();
				videoAudioPo.setVideoAudioId(videoAudioId);
				videoAudioPo.setVideoAudioStatus(ContextConstant.DELETE_STATUS);
				videoAudioService.update(videoAudioPo);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
		
	}
	
	/**
	 * 查找置顶的视频
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findVideoTop")
	public String findVideoTop(HttpServletRequest request){
		try {
			List<VideoAudioPo> list = videoAudioService.findVideoTop(ContextConstant.YES);
			List<VideoAudioVo> result = videoAudioService.selectVoByPo(list);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 查看最近发布的n条音频或视频
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findVideoAudio")
	public String findVideoAudio(HttpServletRequest request,PageTemp pageTemp){
		try {
			String userToken = request.getParameter("userToken");
			if(StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			String videoAudioType = request.getParameter("videoAudioType");
			String videoAudioIspay = request.getParameter("videoAudioIspay");
			UserPo userPo = userService.find(super.getUserIdByToken(userToken));
			PageInfo<VideoAudioVo> pageInfo = videoAudioService.findVideoAudio(pageTemp,videoAudioType,videoAudioIspay);
			List<VideoAudioVo> list = pageInfo.getList();
			for (VideoAudioVo videoAudioVo : list) {
				if(orderService.IsPay(super.getUserIdByToken(userToken), videoAudioVo.getVideoAudioId(), videoAudioVo.getVideoAudioType())){
					videoAudioVo.setIsPlay(ContextConstant.YES);
				}else if(ContextConstant.NO.equals(videoAudioVo.getVideoAudioIspay())){
					videoAudioVo.setIsPlay(ContextConstant.YES);
				}else if(null!=userPo.getMemberSetId()){
					videoAudioVo.setIsPlay(ContextConstant.YES);
				}else{
					videoAudioVo.setIsPlay(ContextConstant.NO);
				}
			}
			pageInfo.setList(list);;
			return ResUtils.okRes(pageInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 *根据播放次数排序
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findVideoAudioByPayNum")
	public String findVideoAudioByPayNum(HttpServletRequest request,PageTemp pageTemp){
		try {
			String userToken = request.getParameter("userToken");
			if(StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			String videoAudioType = request.getParameter("videoAudioType");
			PageInfo<VideoAudioVo> pageInfo = videoAudioService.findVideoAudio(pageTemp,videoAudioType,null);
			List<VideoAudioVo> list = pageInfo.getList();
			for (VideoAudioVo videoAudioVo : list) {
				if(orderService.IsPay(super.getUserIdByToken(userToken), videoAudioVo.getVideoAudioId(), videoAudioVo.getVideoAudioType())){
					videoAudioVo.setIsPlay(ContextConstant.YES);
				}else if(ContextConstant.NO.equals(videoAudioVo.getVideoAudioIspay())){
					videoAudioVo.setIsPlay(ContextConstant.YES);
				}else{
					videoAudioVo.setIsPlay(ContextConstant.NO);
				}
			}
			pageInfo.setList(list);
			return ResUtils.okRes(pageInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 查找相关的视频
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findRelevant")
	public String findRelevant(HttpServletRequest request){
		try {
			String videoAudioId = request.getParameter("videoAudioId");
			String userToken = request.getParameter("userToken");
			if(StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			if(StringUtils.isBlank(videoAudioId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<VideoAudioPo> list = videoAudioService.findRelevant(Integer.parseInt(videoAudioId));
			List<VideoAudioVo> result = videoAudioService.selectVoByPo(list);
			for (VideoAudioVo videoAudioVo : result) {
				if(orderService.IsPay(super.getUserIdByToken(userToken), videoAudioVo.getVideoAudioId(), videoAudioVo.getVideoAudioType())){
					videoAudioVo.setIsPlay(ContextConstant.YES);
				}else if(ContextConstant.NO.equals(videoAudioVo.getVideoAudioIspay())){
					videoAudioVo.setIsPlay(ContextConstant.YES);
				}else{
					videoAudioVo.setIsPlay(ContextConstant.NO);
				}
			}
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 前台查看详情(需要校验是否付费)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/selectOne")
	public String selectOne(HttpServletRequest request){
		try {
			String videoAudioId = request.getParameter("videoAudioId");
			String userToken = request.getParameter("userToken");
			if(StringUtils.isBlank(videoAudioId) || StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			VideoAudioPo videoAudioPo = videoAudioService.findOne(Integer.parseInt(videoAudioId));
			boolean isPay = orderService.IsPay(super.getUserIdByToken(userToken), videoAudioPo.getVideoAudioId(), videoAudioPo.getVideoAudioType());
			if(!isPay && ContextConstant.YES.equals(videoAudioPo.getVideoAudioIspay())){
				if("SP".equals(videoAudioPo.getVideoAudioType())){
					videoAudioPo.setVideoAudioUrl(noPayResVideoUrl);
				}else if("YP".equals(videoAudioPo.getVideoAudioType())){
					videoAudioPo.setVideoAudioUrl(noPayResAudioUrl);
				}
			}
			return ResUtils.okRes(videoAudioPo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据课程id查出所有的视频
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findByCourseId")
	public String findByCourseId(HttpServletRequest request){
		try {
			String courseId = request.getParameter("courseId");
			if(StringUtils.isBlank(courseId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<VideoAudioPo> list = videoAudioService.findByCourseId(Integer.parseInt(courseId));
			List<VideoAudioVo> result = videoAudioService.selectVoByPo(list);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 添加播放次数
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addPayNum")
	public String addPayNum(HttpServletRequest request){
		try {
			String videoAudioId = request.getParameter("videoAudioId");
			String userToken = request.getParameter("userToken");
			if(StringUtils.isBlank(videoAudioId) || StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			VideoAudioPo videoAudioPo = videoAudioService.findOne(Integer.parseInt(videoAudioId));
			videoAudioPo.setVideoAudioPayNum(videoAudioPo.getVideoAudioPayNum()+1);
			videoAudioPo.setRealVideoAudioPayNum(videoAudioPo.getRealVideoAudioPayNum()+1);
			videoAudioService.update(videoAudioPo);
			VisitRecordPo visitRecordPo = new VisitRecordPo();
			visitRecordPo.setResId(videoAudioPo.getVideoAudioId());
			visitRecordPo.setVisitType(videoAudioPo.getVideoAudioType());
			visitRecordPo.setUserId(super.getUserIdByToken(userToken));
			visitRecordPo.setCreateTime(new Date());
			visitRecordService.insertOne(visitRecordPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据userId查询付费的视频或音频（userId;resType:SP-视频订单；YP-音频订单;payStatus:支付状态：1-未支付；2-已支付；3-支付失败）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findVideoAudioByUserId")
	public String findVideoAudioByUserId(HttpServletRequest request,PageTemp pageTemp){
		try {
			Map map = super.getPara();
			String userToken = map.get("userToken").toString();
			if(StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			Integer userId = super.getUserIdByToken(userToken);
			map.put("userId", userId);
			PageInfo<VideoAudioVo> list = videoAudioService.findVideoAudioByUserId(map,pageTemp);
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 查找不是属于课程的视频
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findVideoAndAudioByNoCourse")
	public String findVideoAndAudioByNoCourse(HttpServletRequest request){
		try {
			String courseId = request.getParameter("courseId");
			Integer id = null;
			if(StringUtils.isNotBlank(courseId) && StringUtils.isNumeric(courseId)){
				id = Integer.parseInt(courseId);
			}
			List<VideoAudioPo> list = videoAudioService.findVideoAndAudioByNoCourse(id);
			return ResUtils.okRes(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
