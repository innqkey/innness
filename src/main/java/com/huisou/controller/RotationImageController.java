package com.huisou.controller;

import java.util.Date;
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
import com.huisou.po.NoticeCertificatePo;
import com.huisou.po.RotationImagePo;
import com.huisou.service.NoticeCertificateService;
import com.huisou.service.RotationImageService;
import com.huisou.vo.PageTemp;


/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年2月9日 上午9:14:24 
* 类说明 
*/
@RestController
@RequestMapping(value = "/rotationImage")
public class RotationImageController extends BaseController{
	
	@Autowired
	private RotationImageService rotationImageService;
	
	@Autowired
	private NoticeCertificateService noticeCertificateService;
	
	/**
	 * 添加和修改
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdate")
	public String addAndUpdate(HttpServletRequest request){
		try {
			String rotationImageNo = request.getParameter("rotationImageNo");
			String noticeCertificateId = request.getParameter("noticeCertificateId");
			String rotationImageName = request.getParameter("rotationImageName");
			String noticeCertificateContent = request.getParameter("noticeCertificateContent");
			String rotationImageUrl = request.getParameter("rotationImageUrl");
			String linkUrlType = request.getParameter("linkUrlType");
			String linkUrl = request.getParameter("linkUrl");
			String rotationImageId = request.getParameter("rotationImageId");
			if(StringUtils.isBlank(rotationImageNo) || StringUtils.isBlank(noticeCertificateId) || StringUtils.isBlank(rotationImageUrl) || StringUtils.isBlank(rotationImageUrl)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			RotationImagePo rotationImagePo = new RotationImagePo();
			rotationImagePo.setRotationImageNo(Integer.parseInt(rotationImageNo));
			rotationImagePo.setNoticeCertificateId(Integer.parseInt(noticeCertificateId));
			rotationImagePo.setRotationImageName(rotationImageName);
			rotationImagePo.setRotationImageUrl(rotationImageUrl);
			rotationImagePo.setLinkUrlType(linkUrlType);
			rotationImagePo.setLinkUrl(linkUrl);
			rotationImagePo.setNoticeCertificateContent(noticeCertificateContent);
			rotationImagePo.setRotationImageStatus(ContextConstant.EXIST_STATUS);
			if(StringUtils.isBlank(rotationImageId)){
				rotationImagePo.setCreateTime(new Date());
				rotationImageService.add(rotationImagePo);
			}else{
				rotationImagePo.setRotationImageId(Integer.parseInt(rotationImageId));
				rotationImagePo.setUpdateTime(new Date());
				rotationImageService.update(rotationImagePo);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 软删除
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delete")
	public String delete(HttpServletRequest request){
		try {
			String rotationImageId = request.getParameter("rotationImageId");
			if(StringUtils.isBlank(rotationImageId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			RotationImagePo rotationImagePo = new RotationImagePo();
			rotationImagePo.setRotationImageId(Integer.parseInt(rotationImageId));
			rotationImagePo.setUpdateTime(new Date());
			rotationImagePo.setRotationImageStatus(ContextConstant.DELETE_STATUS);
			rotationImageService.update(rotationImagePo);
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 轮播图首页 (rotationImageStatus: 1为正常；2为删除;pageNum;pageSize)
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,PageTemp pageTemp){
		try {
			Map map = super.getPara();
			PageInfo<RotationImagePo> result = rotationImageService.search(pageTemp,map);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 查看详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findOne")
	public String findOne(HttpServletRequest request){
		try {
			String rotationImageId = request.getParameter("rotationImageId");
			if(StringUtils.isBlank(rotationImageId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			RotationImagePo rotationImagePo = rotationImageService.selectOne(Integer.parseInt(rotationImageId));
			return ResUtils.okRes(rotationImagePo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 查找看轮播图(noticeCertificateId:4,rotationImageStatus:1)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findByparames")
	public String findByparames(HttpServletRequest request){
		try {
			Map map = super.getPara();
			List<RotationImagePo> list = rotationImageService.findByparames(map);
			return ResUtils.okRes(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 查看轮播图类型(type:3)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findNoticeCertificate")
	public String findNoticeCertificate(HttpServletRequest request){
		try {
			String type = request.getParameter("type");
			if(StringUtils.isBlank(type)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<NoticeCertificatePo> list =noticeCertificateService.findType(type);
			return ResUtils.okRes(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 批量删除轮播图(软删除)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deleteList")
	public String deleteList(HttpServletRequest request,@RequestParam("rotationImageIds[]")List<Integer> rotationImageIds){
		try {
			if(rotationImageIds==null || rotationImageIds.size()==0){
				return ResUtils.errRes("102", "请求参数错误");
			}
			for (Integer rotationImageId : rotationImageIds) {
				RotationImagePo rotationImagePo = new RotationImagePo();
				rotationImagePo.setRotationImageId(rotationImageId);
				rotationImagePo.setUpdateTime(new Date());
				rotationImagePo.setRotationImageStatus(ContextConstant.DELETE_STATUS);
				rotationImageService.update(rotationImagePo);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
}
