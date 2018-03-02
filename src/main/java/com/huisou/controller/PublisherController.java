package com.huisou.controller;

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

import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.po.SayPublisherPo;
import com.huisou.service.PublisherService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.SayPublisherVo;

/** 
* @author qinkai 
* @date 2018年2月2日
*/

@RestController
@RequestMapping(value = "/publisher")
public class PublisherController extends BaseController{

	@Autowired
	private PublisherService publisherService;
	
	/**
	 * 编辑语录发布人
	 * @param publisherId
	 * @param publisherLogo
	 * @param publisherName
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public String add(Integer publisherId,String publisherLogo,String publisherName){
		try {
			if (StringUtils.isBlank(publisherName) || StringUtils.isBlank(publisherLogo)){
				return ResUtils.errRes("404", "请求参数有误");
			}
			if (null != publisherId && publisherId > 0){
				SayPublisherPo publisherPo = publisherService.findOne(publisherId);
				publisherPo.setPublisherLogo(publisherLogo);
				publisherPo.setPublisherName(publisherName);
//				publisherPo.setUpdateBy();
				publisherService.updateOne(publisherPo);
			} else {
				SayPublisherPo publisherPo = new SayPublisherPo();
//				publisherPo.setCreateBy();
				publisherPo.setCreateTime(new Date());
				publisherPo.setPublisherLogo(publisherLogo);
				publisherPo.setPublisherName(publisherName);
				publisherService.addOne(publisherPo);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 语录发布人查看详情
	 * @param publisherId
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Integer publisherId){
		if (null == publisherId || publisherId <= 0){
			return ResUtils.errRes("404", "请传入正确的语录发布人id");
		}
		SayPublisherPo publisherPo = publisherService.findOne(publisherId);
		return ResUtils.okRes(publisherPo);
	}
	
	/**
	 * 删除语录发布人
	 * @param publisherId
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request,@RequestParam(required = false, value = "publisherId[]") List<String> publisherId){
		if (null == publisherId || publisherId.size() <= 0){
			return ResUtils.errRes("404", "请传入正确的语录发布人id");
		}
		publisherService.deleteOne(publisherId);
		return ResUtils.okRes();
	}
	
	/**
	 * 语录发布人列表首页
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, PageTemp pageTemp){
		String publisherId = request.getParameter("publisherId");
		String publisherName = StringUtils.stripToEmpty(request.getParameter("publisherName"));
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("publisherId", publisherId);
		maps.put("publisherName", publisherName);
		maps.put("beginDate", beginDate);
		maps.put("endDate", endDate);
		PageInfo<SayPublisherVo> sayPoList = publisherService.findAll(maps,pageTemp);
		return ResUtils.okRes(sayPoList);
	}
}
