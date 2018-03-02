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
import com.huisou.po.CommentPo;
import com.huisou.po.NoticeCertificatePo;
import com.huisou.service.CommentService;
import com.huisou.service.NoticeCertificateService;
import com.huisou.vo.CommentVo;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2018年2月1日
*/
@RestController
@RequestMapping(value = "/setting")
public class SettingController extends BaseController{
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private NoticeCertificateService noticeCertificateService;
	
	/**
	 * 用户添加意见反馈
	 * @param request
	 * @param commentPo
	 * @return
	 */
	@RequestMapping(value = "/addComment",method = RequestMethod.POST)
	public String add(HttpServletRequest request,CommentPo commentPo){
		if (super.getUserIdByToken(request.getParameter("userToken")) <= 0 || StringUtils.isBlank(commentPo.getComment())){
			return ResUtils.execRes();
		} else {
			commentPo.setUserId(super.getUserIdByToken(request.getParameter("userToken")));
			commentPo.setCreateTime(new Date());
			commentService.addOne(commentPo);
			return ResUtils.okRes();
		}
	}
	
	/**
	 * 删除意见反馈
	 * @param request
	 * @param commentId
	 * @return
	 */
	@RequestMapping(value = "/deleteComment")
	public String delete(HttpServletRequest request, @RequestParam(required = false, value = "commentId[]") List<String> commentId){
		if (null == commentId || commentId.size() <= 0){
			return ResUtils.execRes();
		} else {
			commentService.delete(commentId);
			return ResUtils.okRes();
		}
	}
	
	/**
	 * 查找/意见反馈列表首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listComment")
	public String list(HttpServletRequest request,PageTemp pageTemp){
		try {
			String userName = StringUtils.stripToEmpty(request.getParameter("userName"));
			String comment = StringUtils.stripToEmpty(request.getParameter("comment"));
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			Map<String, String> maps = new HashMap<String, String>();
			maps.put("userName", userName);
			maps.put("comment", comment);
			maps.put("beginDate", beginDate);
			maps.put("endDate", endDate);
			PageInfo<CommentVo> poList = commentService.queryByEmpParas(maps,pageTemp);
	        return ResUtils.okRes(poList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.exceCode;
		}
	}
	
	/**
	 * 通知编辑/添加
	 * @param content
	 * @param type
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/addNotice", method = RequestMethod.POST)
	public String addNotice(String content,Integer id){
		try {
			if (StringUtils.isBlank(content)){
				return ResUtils.errRes("404", "请填写通知内容");
			}
			if (null != id && id > 0){
				NoticeCertificatePo po = noticeCertificateService.findOne(id);
				po.setContent(content);
				po.setType("1");
				noticeCertificateService.updateOne(po);
			} else {
				NoticeCertificatePo po = new NoticeCertificatePo();
				po.setContent(content);
				po.setType("1");
				po.setCreateTime(new Date());
				noticeCertificateService.addOne(po);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.okRes();
		}
	}
	
	/**
	 * 认证说明编辑/添加
	 * @param content
	 * @param type
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/addCertificate", method = RequestMethod.POST)
	public String addCertificate(String content,Integer id){
		try {
			if (StringUtils.isBlank(content)){
				return ResUtils.errRes("404", "请填写认证说明内容");
			}
			if (null != id && id > 0){
				NoticeCertificatePo po = noticeCertificateService.findOne(id);
				po.setContent(content);
				po.setType("2");
				noticeCertificateService.updateOne(po);
			} else {
				NoticeCertificatePo po = new NoticeCertificatePo();
				po.setContent(content);
				po.setType("2");
				po.setCreateTime(new Date());
				noticeCertificateService.addOne(po);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.okRes();
		}
	}
	
	/**
	 * 返回通知/认证说明内容
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/noticeCertificateDetail")
	public String noticeCertificateDetail(String type){
		if (StringUtils.isBlank(type)){
			return ResUtils.execRes();
		} else {
			NoticeCertificatePo po = noticeCertificateService.find(type);
			return ResUtils.okRes(po);
		}
	}
}
