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
import com.huisou.po.AwardSetPo;
import com.huisou.service.AwardSetService;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年2月1日 上午9:38:45 
* 类说明 
*/
@RestController
@RequestMapping(value = "/awardSet")
public class AwardSetController extends BaseController{
	
	@Autowired
	private AwardSetService awardSetService;
	
	@RequestMapping(value = "/addAwardSet")
	public String addAwardSet(HttpServletRequest request){
		try {
			String awardSetMoney = request.getParameter("awardSetMoney");
			if(StringUtils.isBlank(awardSetMoney)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			AwardSetPo awardSetPo = new AwardSetPo();
			awardSetPo.setAwardSetMoney(Long.parseLong(awardSetMoney));
			awardSetPo.setCreateTime(new Date());
			awardSetService.add(awardSetPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 修改打赏设置
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdate")
	public String addAndUpdate(HttpServletRequest request){
		try {
			String awardSetId = super.getPara().get("awardSetId");
			String awardSetMoney = super.getPara().get("awardSetMoney");
			if(StringUtils.isBlank(awardSetMoney)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			AwardSetPo awardSetPo = new AwardSetPo();
			awardSetPo.setAwardSetMoney(Long.parseLong(awardSetMoney));
			if(StringUtils.isBlank(awardSetId)){
				awardSetPo.setCreateTime(new Date());
				awardSetService.add(awardSetPo);
			}else{
				awardSetPo.setUpdateTime(new Date());
				awardSetPo.setAwardSetId(Integer.parseInt(awardSetId));
				awardSetService.update(awardSetPo);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 启用或禁用打赏
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateStatus")
	public String updateStatus(HttpServletRequest request){
		try {
			String awardSetStatus = request.getParameter("awardSetStatus");
			String awardSetId = super.getPara().get("awardSetId");
			if(StringUtils.isBlank(awardSetId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			AwardSetPo awardSetPo = new AwardSetPo();
			awardSetPo.setUpdateTime(new Date());
			awardSetPo.setAwardSetId(Integer.parseInt(awardSetId));
			awardSetPo.setAwardSetStatus(awardSetStatus);
			awardSetService.update(awardSetPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 批量修改状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateList")
	public String updateList(HttpServletRequest request,@RequestParam("awardSetIds[]")List<Integer> awardSetIds){
		try {
			String awardSetStatus = request.getParameter("awardSetStatus");
			if(awardSetIds==null || awardSetIds.size()==0){
				return ResUtils.errRes("102", "请求参数错误");
			}
			for (Integer awardSetId : awardSetIds) {
				AwardSetPo awardSetPo = new AwardSetPo();
				awardSetPo.setUpdateTime(new Date());
				awardSetPo.setAwardSetId(awardSetId);
				awardSetPo.setAwardSetStatus(awardSetStatus);
				awardSetService.update(awardSetPo);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 打赏设置列表首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,PageTemp pageTemp){
		try {
			PageInfo<AwardSetPo> result = awardSetService.search(pageTemp);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 *	查看打赏设置详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findOne")
	public String findOne(HttpServletRequest request){
		try {
			String awardSetId = request.getParameter("awardSetId");
			if(StringUtils.isBlank(awardSetId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			AwardSetPo awardSetPo = awardSetService.findOne(Integer.parseInt(awardSetId));
			return ResUtils.okRes(awardSetPo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	
	/**
	 * 前台展示打赏金额
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findAllByStatus")
	public String findAllByStatus(HttpServletRequest request){
		try {
			List<AwardSetPo> list = awardSetService.findAllByStatus(ContextConstant.EXIST_STATUS);
			return ResUtils.okRes(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
