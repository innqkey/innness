package com.huisou.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.ConvertUtil;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.po.ResourcesEvalPo;
import com.huisou.po.ResourcesThumbsPo;
import com.huisou.po.SayPo;
import com.huisou.po.SayPublisherPo;
import com.huisou.service.PublisherService;
import com.huisou.service.ResourcesEvalService;
import com.huisou.service.ResourcesThumbsService;
import com.huisou.service.SayService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.ResourcesEvalVo;
import com.huisou.vo.ResourcesThumbsVo;
import com.huisou.vo.SayThumbsEvalVo;

/**
 * @author qinkai
 * @date 2018年1月29日
 */
@RestController
@RequestMapping(value = "/say")
public class SayController extends BaseController {

	@Autowired
	private SayService sayService;
	@Autowired
	private ResourcesEvalService evalService;
	@Autowired
	private ResourcesThumbsService thumbsService;
	@Autowired
	private PublisherService publisherService;

	/**
	 * 新增/更新一条语录
	 * 
	 * @param sayTitle
	 * @param sayContext
	 * @param createTime
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(String sayTitle, String sayContext, Integer publisherId,HttpServletRequest request) {
		if (StringUtils.isBlank(sayTitle) || StringUtils.isBlank(sayContext) || null == publisherId ||
				StringUtils.isBlank(request.getParameter("sayLogo"))) {
			return ResUtils.errRes("404", "请求参数有误");
		}
		String sayId = request.getParameter("sayId");
		SayPublisherPo publisher = publisherService.findOne(publisherId);
		if (null == publisher){
			return ResUtils.errRes("404", "语录发布人不存在");
		}
		if (StringUtils.isBlank(sayId)){
			SayPo sayPo = new SayPo();
			sayPo.setSayTitle(sayTitle);
			sayPo.setSayContext(sayContext);
			sayPo.setSayLogo(request.getParameter("sayLogo"));
			sayPo.setSayName(publisher.getPublisherName());
			sayPo.setSayHeadImg(publisher.getPublisherLogo());
			sayPo.setCreateBy(publisherId);
			sayPo.setCreateTime(new Date());
			sayService.addOne(sayPo);
			return ResUtils.okRes();
		} else {
			SayPo sayPo = sayService.findOne(Integer.valueOf(sayId));
			sayPo.setSayTitle(sayTitle);
			sayPo.setSayContext(sayContext);
			sayPo.setSayLogo(request.getParameter("sayLogo"));
			sayPo.setSayHeadImg(publisher.getPublisherLogo());
			sayPo.setSayName(publisher.getPublisherName());
			sayPo.setCreateBy(publisherId);
//			sayPo.setUpdateBy(super.);
			sayService.updateOne(sayPo);
			return ResUtils.okRes();
		}
	}

	/**
	 * 查看语录详情
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String Detail(String sayId){
		try {
			if (StringUtils.isBlank(sayId)){
				return ResUtils.errRes("404", "请求参数有误");
			}
			SayPo sayPo = sayService.findOne(Integer.valueOf(sayId));
			return ResUtils.okRes(sayPo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	/**
	 * 根据语录id删除语录
	 * @param sayId
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request,@RequestParam(required = false, value = "sayId[]") List<String> sayId) {
		try {
			if (null == sayId || sayId.size() < 0) {
				return ResUtils.errRes("404", "请求参数有误");
			}
			sayService.deleteOne(sayId);
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResUtils.errRes("404", "删除语录失败");
	}
	
	/**
	 * 语录列表首页
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, PageTemp pageTemp){
//		String type = request.getParameter("type");
		String sayTitle = request.getParameter("sayTitle");
		String evalMin = request.getParameter("evalMin");
		String evalMax = request.getParameter("evalMax");
		String thumbsMin = request.getParameter("thumbsMin");
		String thumbsMax = request.getParameter("thumbsMax");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("sayTitle", sayTitle);
		maps.put("evalMin", evalMin);
		maps.put("evalMax", evalMax);
		maps.put("thumbsMin", thumbsMin);
		maps.put("thumbsMax", thumbsMax);
		maps.put("beginDate", beginDate);
		maps.put("endDate", endDate);
		PageInfo<SayPo> sayPoList = sayService.findAll(maps,pageTemp);
		return ResUtils.okRes(sayPoList);
	}
	
	/**
	 * 添加一条语录评论(前台)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addEval",method = RequestMethod.POST)
	public String addEval(HttpServletRequest request){
		try {
			if (StringUtils.isBlank(request.getParameter("evalContext")) || StringUtils.isBlank(request.getParameter("resId")) ||
					super.getUserIdByToken(request.getParameter("userToken")) <= 0){
				return ResUtils.errRes("404", "请求的参数有误");
			}
			String evalContext = request.getParameter("evalContext");
			String resId = request.getParameter("resId");
			Integer userId = super.getUserIdByToken(request.getParameter("userToken"));
			ResourcesEvalPo evalPo = new ResourcesEvalPo();
			evalPo.setEvalContext(evalContext);
			evalPo.setResId(Integer.parseInt(resId));
			evalPo.setResType("YL");
			evalPo.setUserId(userId);
			evalPo.setCreateTime(new Date());
			Integer res = evalService.addOne(evalPo);
//			if (null != res){
//				sayService.updateNum(Integer.parseInt(resId), 1, 1);
//			}
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResUtils.execRes();
	}
	
	/**
	 * 根据语录评论id删除(或审核)一条评论(type 1:审核，2:删除)
	 * @param evalId
	 * @param resId
	 * @return
	 */
	@RequestMapping(value = "/deleteEval")
	public String deleteEval(@RequestParam(required = false, value = "evalId[]") List<String> evalId,
			@RequestParam(required = false, value = "resId[]") List<String> resId,String type) {
		try {
			if (null == evalId || null == resId || evalId.size() <= 0 || resId.size() <= 0 || StringUtils.isBlank(type) ||
					resId.size() != evalId.size()) {
				return ResUtils.errRes("404", "请求参数有误");
			}
			Map params = new HashMap<>();
			params.put("evalId", evalId);
			params.put("type", type);
			if(type.equals("1") || type.equals("2")){
				for (int i = 0; i < evalId.size(); i++){
					if (type.equals("1")){
						sayService.updateNum(Integer.parseInt(resId.get(i)), 1, 1);
					} else if (type.equals("2")){
						ResourcesEvalPo evalPo = evalService.selectOne(Integer.parseInt(evalId.get(i)));
						if (null != evalPo && evalPo.getEvalStatus().equals("1")){
							sayService.updateNum(Integer.parseInt(resId.get(i)), 1, -1);
						}
					}
				}
				evalService.updateStatus(params);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 语录评论列表
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value = "/evalList")
	public String evalList(HttpServletRequest request, PageTemp pageTemp){
		String nickName = StringUtils.stripToEmpty(request.getParameter("nickName"));
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String resId = request.getParameter("resId");
		String resType = request.getParameter("resType");
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("resId", resId);
		maps.put("nickName", nickName);
		maps.put("resType", resType);
		maps.put("beginDate", beginDate);
		maps.put("endDate", endDate);
		PageInfo<ResourcesEvalVo> evalVoList = evalService.findAll(maps,pageTemp);
		return ResUtils.okRes(evalVoList);
	}
	
	/**
	 * 更新语录点赞状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateThumbs")
	public String updateThumbs(HttpServletRequest request){
		return ResUtils.okRes();
	}
	
	/**
	 * 语录点赞,
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addThumbs",method = RequestMethod.POST)
	public String addThumbs(HttpServletRequest request){
		try {
			if (StringUtils.isBlank(request.getParameter("resId")) ||
					super.getUserIdByToken(request.getParameter("userToken")) <= 0){
				return ResUtils.errRes("404", "请求的参数有误");
			}
			String resId = request.getParameter("resId");
			Integer userId = super.getUserIdByToken(request.getParameter("userToken"));
//			ResourcesThumbsPo findOne = thumbsService.findOne(Integer.parseInt(resId),userId);
//			if (null == findOne){
				ResourcesThumbsPo thumbsPo = new ResourcesThumbsPo();
				thumbsPo.setResId(Integer.parseInt(resId));
				thumbsPo.setResType("YL");
				thumbsPo.setUserId(userId);
				thumbsPo.setCreateTime(new Date());
				Integer res = thumbsService.addOne(thumbsPo);
				if (null != res){
					sayService.updateNum(Integer.parseInt(resId), 2, 1);
				}
//			} else {
//				findOne.setThumbsStatus("1");
//				thumbsService.updateOne(findOne);
//				sayService.updateNum(Integer.parseInt(resId), 2, 1);
//			}
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResUtils.execRes();
	}
	
	/**
	 * 取消语录点赞
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteThumbs",method = RequestMethod.POST)
	public String deleteThumbs(HttpServletRequest request){
		try {
			if (super.getUserIdByToken(request.getParameter("userToken")) <= 0 ||
					StringUtils.isBlank(request.getParameter("resId"))){
				return ResUtils.errRes("404", "请求的参数有误");
			}
			Integer userId = super.getUserIdByToken(request.getParameter("userToken"));
			String resId = request.getParameter("resId");
			thumbsService.deleteOne(userId,Integer.valueOf(resId));
			sayService.updateNum(Integer.parseInt(resId), 2, -1);
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResUtils.execRes();
	}
	
	/**
	 * 语录点赞列表(1:前台;2:后台)
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value = "/thumbsList")
	public String thumbsList(HttpServletRequest request, PageTemp pageTemp,String type){
		try {
			if (StringUtils.isBlank(type)){
				return ResUtils.errRes("404", "请选择type值");
			}
			if (type.equals("1") && StringUtils.isNotBlank(request.getParameter("resId"))){
				request.getParameter("resId");
				List<ResourcesThumbsVo> selectAll = thumbsService.selectAll(Integer.parseInt(request.getParameter("resId")));
				return ResUtils.okRes(selectAll);
			} else if (type.equals("2")){
				String nickName = StringUtils.stripToEmpty(request.getParameter("nickName"));
				String beginDate = request.getParameter("beginDate");
				String endDate = request.getParameter("endDate");
				String resId = request.getParameter("resId");
				Map<String, String> maps = new HashMap<String, String>();
				maps.put("resId", resId);
				maps.put("nickName", nickName);
				maps.put("resType", "YL");
				maps.put("beginDate", beginDate);
				maps.put("endDate", endDate);
				PageInfo<ResourcesThumbsVo> thumbsVoList = thumbsService.findAll(maps,pageTemp);
				return ResUtils.okRes(thumbsVoList);
			} else {
				return ResUtils.execRes();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 判断用户是否点赞(1:点赞,0:未点赞)
	 * @param request
	 * @param resId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/thumbsExist")
	public String thumbsExist(HttpServletRequest request, Integer resId){
		if (null == resId || resId <= 0 || super.getUserIdByToken(request.getParameter("userToken")) <= 0){
			return ResUtils.errRes("404", "请求参数有误");
		}
		int userId = super.getUserIdByToken(request.getParameter("userToken"));
		ResourcesThumbsPo findOne = thumbsService.findOne(resId,userId);
		if (null != findOne){
			return ResUtils.okRes("1");
		} else {
			return ResUtils.okRes("0");
		}
	}
	
	/**
	 * 前台语录展示列表(type:1,首页的只显示1条,type:2,查看全部)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/frontList")
	public String frontList(HttpServletRequest request,String type,PageTemp pageTemp){
		try {
			if (null != type && type.equals("1")){
				List<SayPo> poList = new ArrayList<>();
				SayPo sayPo = sayService.selectOne();
				if (null != sayPo){
					poList.add(sayPo);
				}
				return ResUtils.okRes(poList);
			} else if (null != type && type.equals("2")) {
				PageInfo<SayPo> allSays = sayService.findAll(null, pageTemp);
				List<SayPo> vos = ConvertUtil.convertDtoAndVo(allSays.getList(), SayPo.class);
				List<SayThumbsEvalVo> thumbsEvalVos = new ArrayList<SayThumbsEvalVo>();
				for (SayPo sayPo : vos) {
					SayThumbsEvalVo thumbsEvalVo = new SayThumbsEvalVo();
					ConvertUtil.convertDtoAndVo(sayPo, thumbsEvalVo);
					Integer sayId = sayPo.getSayId();
					List<ResourcesThumbsVo> thumbsPos = thumbsService.selectAll(sayId);
					List<ResourcesEvalVo> evalPos = evalService.selectAll(sayId);
					thumbsEvalVo.setEvalList(evalPos);
					thumbsEvalVo.setThumbsList(thumbsPos);
					thumbsEvalVos.add(thumbsEvalVo);
				}
				return ResUtils.okRes(new PageInfo<SayThumbsEvalVo>(thumbsEvalVos));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResUtils.execRes();
	}
}
