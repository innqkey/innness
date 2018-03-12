package com.huisou.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.po.ResourcesEvalPo;
import com.huisou.service.OrderService;
import com.huisou.service.ResourcesEvalService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.ResourcesEvalVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 上午11:28:00 
* 类说明 
*/
@RestController
@RequestMapping(value = "/resourcesEval")
public class ResourcesEvalController extends BaseController{
	
	@Autowired
	private  ResourcesEvalService resourcesEvalService;
	
	@Autowired
	private OrderService orderService;
	/**
	 * 添加一条评论
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request){
		try {
			String resId = request.getParameter("resId");
			String resType = request.getParameter("resType");
			String userToken = request.getParameter("userToken");
			String evalContext = request.getParameter("evalContext");
			String evalLevel = request.getParameter("evalLevel");
			if(StringUtils.isBlank(resId) || StringUtils.isBlank(resType) || StringUtils.isBlank(userToken) || StringUtils.isBlank(userToken) ){
				return ResUtils.errRes("102", "请求参数错误");
			}
			ResourcesEvalPo resourcesEvalPo = new ResourcesEvalPo();
			resourcesEvalPo.setEvalContext(evalContext);
			resourcesEvalPo.setEvalLevel(Integer.parseInt(evalLevel));
			resourcesEvalPo.setResId(Integer.parseInt(resId));
			resourcesEvalPo.setResType(resType);
			resourcesEvalPo.setUserId(super.getUserIdByToken(userToken));
			resourcesEvalPo.setCreateTime(new Date());
			resourcesEvalService.addOne(resourcesEvalPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**后台接口
	 * 根据资源id和资源类型查询评论内容(resType视频SP，音频YP，语录YL)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findByResIdAndResType")
	public String findByResIdAndResType(HttpServletRequest request){
		try {
			String resId = request.getParameter("resId");
			String resType = request.getParameter("resType");
			if(StringUtils.isBlank(resId) || StringUtils.isBlank(resType)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			Map<String, String> map = new HashMap<>();
			map.put("resId", resId);
			map.put("resType", resType);
			map.put("evalStatus", ContextConstant.EXIST_STATUS);
			PageInfo<ResourcesEvalVo> result = resourcesEvalService.findAllByMap(map);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**前台接口
	 * 根据资源id和资源类型查询评论内容(resType视频SP，音频YP，语录YL)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findByResIdAndResTypeWeb")
	public String findByResIdAndResTypeWeb(HttpServletRequest request,PageTemp pageTemp){
		try {
			String resId = request.getParameter("resId");
			String resType = request.getParameter("resType");
			if(StringUtils.isBlank(resId) || StringUtils.isBlank(resType)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			Map<String, String> map = new HashMap<>();
			map.put("resId", resId);
			map.put("resType", resType);
			map.put("evalStatus", ContextConstant.EXIST_STATUS);
			PageInfo<ResourcesEvalVo> result = resourcesEvalService.findAllByMapAndStatus(map,pageTemp);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 *删除单条评论
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delete")
	public String delete(HttpServletRequest request){
		try {
			String evalId = request.getParameter("evalId");
			if(StringUtils.isBlank(evalId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			ResourcesEvalPo resourcesEvalPo = new ResourcesEvalPo();
			resourcesEvalPo.setEvalId(Integer.parseInt(evalId));
			resourcesEvalPo.setEvalStatus(ContextConstant.DELETE_STATUS);
			resourcesEvalService.update(resourcesEvalPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据语录评论id删除(或审核)一条评论(type 1:审核，2:删除)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteList")
	public String deleteList(HttpServletRequest request,@RequestParam("evalIds[]")List<Integer> evalIds,String type){
		try {
			if(evalIds==null || evalIds.size()==0 || StringUtils.isBlank(type)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			Map params = new HashMap<>();
			params.put("evalId", evalIds);
			params.put("type", type);
			if(type.equals("1") || type.equals("2")){
				resourcesEvalService.updateStatus(params);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
		
	}
	
}
