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
import com.huisou.constant.ContextConstant;
import com.huisou.po.IntegralRecordPo;
import com.huisou.po.MaterialLevelPo;
import com.huisou.po.MaterialPo;
import com.huisou.po.MyStudyPo;
import com.huisou.po.VisitRecordPo;
import com.huisou.service.IntegralRecordService;
import com.huisou.service.MaterialLevelService;
import com.huisou.service.MaterialService;
import com.huisou.service.MyStudyService;
import com.huisou.service.UserService;
import com.huisou.service.VisitRecordService;
import com.huisou.vo.IntegralRecordVo;
import com.huisou.vo.MaterialVo;
import com.huisou.vo.MyStudyVo;
import com.huisou.vo.PageTemp;



/** 
* @author qinkai
* @date 2018年1月29日
*/
@RestController
@RequestMapping(value = "/material")
public class MaterialController extends BaseController{

	@Autowired
	private MaterialService materialService;
	@Autowired
	private MaterialLevelService levelService;
	@Autowired
	private MyStudyService studyService;
	@Autowired
	private IntegralRecordService integralService;
	@Autowired
	private UserService userService;
	@Autowired
	private VisitRecordService recordService;
	
	/**
	 * 更新/上传一条资料
	 * @param request
	 * @param materialPo
	 * @return
	 */
	@RequestMapping(value ="/add",method = RequestMethod.POST)
	public String add(HttpServletRequest request,MaterialPo materialPo){
		if (StringUtils.isBlank(materialPo.getMaterialName()) || null == materialPo.getMaterialLevelId() ||
				StringUtils.isBlank(materialPo.getMaterialUrl())){
			return ResUtils.errRes("404", "请求的参数有误");
		}
		String materialIspay = materialPo.getMaterialIspay();
		if (materialIspay != null && materialIspay.equals("Y")){
			Long materialIntegral = materialPo.getMaterialIntegral();
			if (null == materialIntegral || materialIntegral <= 0){
				return ResUtils.errRes("404", "请添加资料所需积分");
			}
		} else {
			materialPo.setMaterialIspay("N");
			materialPo.setMaterialIntegral(0L);
		}
		materialPo.setMaterialUrl(materialPo.getMaterialUrl());
		Integer materialId = materialPo.getMaterialId();
		if (null == materialId){
			materialPo.setCreateTime(new Date());
			Integer resId = materialService.addOne(materialPo);
			if (resId > 0){
				levelService.updateLevelNum(materialPo.getMaterialLevelId(),1);
				return ResUtils.okRes();
			}
		} else {
			MaterialPo po = materialService.findOne(materialId);
			po.setMaterialUrl(materialPo.getMaterialUrl());
			po.setMaterialName(materialPo.getMaterialName());
			if (po.getMaterialLevelId().equals(materialPo.getMaterialLevelId())){
				
			} else {
				levelService.updateLevelNum(po.getMaterialLevelId(),-1);
				levelService.updateLevelNum(materialPo.getMaterialLevelId(),1);
				po.setMaterialLevelId(materialPo.getMaterialLevelId());
			}
			if (StringUtils.isNotBlank(materialPo.getMaterialIntro())){
				po.setMaterialIntro(materialPo.getMaterialIntro());
			}
			if (materialIspay != null && materialIspay.equals("Y")){
				po.setMaterialIspay("Y");
				po.setMaterialIntegral(materialPo.getMaterialIntegral());
			} else {
				po.setMaterialIspay("N");
				po.setMaterialIntegral(0L);
			}
			materialService.updateOne(po);
		}
		return ResUtils.okRes();
	}
	
	/**
	 * 查看上传资料
	 * @param materialId
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Integer materialId){
		if (null == materialId || materialId <= 0){
			return ResUtils.errRes("404", "资料id不能为空");
		} else {
			MaterialPo po = materialService.findOne(materialId);
			return ResUtils.okRes(po);
		}
	}
	
	/**
	 * 批量/删除上传资料,同时使分类菜单下的资料数减1
	 * @param materialId
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, @RequestParam(required = false, value = "materialId[]") List<String> materialId,
			@RequestParam(required = false, value = "materialLevelId[]") List<String> materialLevelId){
		try {
			if (null == materialId || materialId.size() <= 0 || null == materialLevelId || materialLevelId.size() <= 0){
				return ResUtils.errRes("404", "请求参数有误");
			}
			if (materialId.size() == materialLevelId.size()){
				materialService.deleteOne(materialId);
				for(int i = 0; i < materialLevelId.size(); i++){
					levelService.updateLevelNum(Integer.valueOf(materialLevelId.get(i)),-1);
				}
				return ResUtils.okRes();
			} else {
				return ResUtils.execRes();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 资料购买后,每次点击资料时,下载次数加1
	 * @param materialId
	 * @return
	 */
	@RequestMapping(value = "/increase")
	public String increase(String materialId,HttpServletRequest request){
		try {
			if (StringUtils.isBlank(materialId) || Integer.parseInt(materialId) <= 0) {
				return ResUtils.errRes("404", "请求参数有误");
			}
			materialService.increaseOne(Integer.valueOf(materialId));
			VisitRecordPo recordPo = new VisitRecordPo();
			recordPo.setResId(Integer.parseInt(materialId));
			recordPo.setUserId(super.getUserIdByToken(request.getParameter("userToken")));
			recordPo.setVisitType(ContextConstant.VISIT_RECORD_TYPE_ZL);
			recordPo.setCreateTime(new Date());
			recordService.insertOne(recordPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResUtils.execRes();
	}
	
	/**
	 * 资料列表,默认创建时间 排序,(userId :前台;无:后台)
	 * orderFactor: 默认0:时间倒序，1:下载量倒序，2:下载量升序
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,PageTemp pageTemp){
		try {
			String materialName = StringUtils.stripToEmpty(request.getParameter("materialName"));
			String materialLevelId = request.getParameter("materialLevelId");
			String orderFactor = request.getParameter("orderFactor");
			String type = request.getParameter("type");
			String userId = null;
			if (null != type && type.equals("1")){
				userId = String.valueOf(super.getUserIdByToken(request.getParameter("userToken")));
			}
			if (StringUtils.isBlank(orderFactor)){
				orderFactor = "0";
			}
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			Map<String, String> maps = new HashMap<String, String>();
			maps.put("userId", userId);
			maps.put("materialName", materialName);
			maps.put("materialLevelId", materialLevelId);
			maps.put("orderFactor", orderFactor);
			maps.put("beginDate", beginDate);
			maps.put("endDate", endDate);
			PageInfo<MaterialVo> materialPoList = materialService.findAll(maps,pageTemp);
			return ResUtils.okRes(materialPoList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 更新/添加资料分类二级菜单
	 * @param materialLevelName
	 * @param materialLevelType
	 * @param materialLevelImg
	 * @return
	 */
	@RequestMapping(value = "/addMaterialLevel",method = RequestMethod.POST)
	public String addMaterialLevel(String materialLevelName,String materialLevelType,String materialLevelImg,String materialLevelId){
		try {
			if(StringUtils.isBlank(materialLevelImg) || StringUtils.isBlank(materialLevelName) || 
					StringUtils.isBlank(materialLevelType)){
				return ResUtils.errRes("404", "请求参数有误");
			}
			if (StringUtils.isBlank(materialLevelId)){
				MaterialLevelPo levelPo = new MaterialLevelPo();
				levelPo.setMaterialLevelName(materialLevelName);
				levelPo.setMaterialLevelType(materialLevelType);
				levelPo.setMaterialLevelImg(materialLevelImg);
//			levelPo.setCreateBy(super.);
				levelPo.setCreateTime(new Date());
				levelService.addOne(levelPo);
			} else {
				MaterialLevelPo levelPo = levelService.findOne(Integer.parseInt(materialLevelId));
				levelPo.setMaterialLevelName(materialLevelName);
				levelPo.setMaterialLevelType(materialLevelType);
				levelPo.setMaterialLevelImg(materialLevelImg);
				levelService.updateOne(levelPo);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResUtils.exceCode;
	}
	
	/**
	 * 查看二级资料详情
	 * @param materialLevelId
	 * @return
	 */
	@RequestMapping(value = "/detailLevel")
	public String detailLevel(Integer materialLevelId){
		if (null == materialLevelId || materialLevelId <= 0){
			return ResUtils.errRes("404", "资料id不能为空");
		} else {
			MaterialLevelPo levelPo = levelService.findOne(materialLevelId);
			return ResUtils.okRes(levelPo);
		}
	}
	
	/**
	 * 删除二级资料类目
	 * @param materialLevelId
	 * @return
	 */
	@RequestMapping(value = "/deleteMaterialLevel")
	public String deleteMaterialLevel(@RequestParam(required = false, value = "materialLevelId[]") List<String> materialLevelId){
		if (null == materialLevelId || materialLevelId.size() <= 0){
			return ResUtils.errRes("404", "二级资料类目id有误");
		} else {
			levelService.deleteOne(materialLevelId);
			return ResUtils.okRes();
		}
	}
	
	/**
	 * 资料分类列表(type: 1前台，2后台)
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value = "/levelList")
	public String levelList(HttpServletRequest request,PageTemp pageTemp,String type){
		try {
			if (StringUtils.isBlank(type)){
				return ResUtils.errRes("404", "请修传入type值");
			}
			String materialLevelType = request.getParameter("materialLevelType");
			String materialLevelName = StringUtils.stripToEmpty(request.getParameter("materialLevelName"));
			String minLevelNum = request.getParameter("minLevelNum");
			String maxLevelNum = request.getParameter("maxLevelNum");
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			Map<String, String>maps = new HashMap<String,String>();
			maps.put("materialLevelType", materialLevelType);
			maps.put("materialLevelName", materialLevelName);
			maps.put("minLevelNum", minLevelNum);
			maps.put("maxLevelNum", maxLevelNum);
			maps.put("beginDate", beginDate);
			maps.put("endDate", endDate);
			if (type.equals("1")){
				List<MaterialLevelPo> voList = levelService.selectAll(maps);
				return ResUtils.okRes(voList);
			} else if (type.equals("2")){
				PageInfo<MaterialLevelPo> levelPoList = levelService.findAll(maps,pageTemp);
				return ResUtils.okRes(levelPoList);
			} else {
				return ResUtils.errRes("404", "传入type值有误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 资料下载扣除积分时保存下载的课件记录
	 * @param request
	 * @param studyPo
	 * @return
	 */
	@RequestMapping(value = "materialDownload",method = RequestMethod.POST)
	public String materialDownload(HttpServletRequest request, MyStudyPo studyPo,String integral){
		try {
			if (null == studyPo.getResId() || StringUtils.isBlank(studyPo.getResTitle()) || super.getUserIdByToken(request.getParameter("userToken")) <= 0 || 
					StringUtils.isBlank(integral)){
				return ResUtils.errRes("404", "请求参数有误");
			} else {
				if (!userService.getUserIntegral(super.getUserIdByToken(request.getParameter("userToken")), Integer.valueOf(integral))){
					return ResUtils.errRes("102", "用户积分不足");
				} else {
					Integer userId = super.getUserIdByToken(request.getParameter("userToken"));
					IntegralRecordPo integralRecordPo = new IntegralRecordPo();
					integralRecordPo.setResId(studyPo.getResId());
					integralRecordPo.setResTitle(studyPo.getResTitle());
					integralRecordPo.setResPrice(-Long.parseLong(integral));
					integralRecordPo.setResType("1");
					integralRecordPo.setCreateTime(new Date());
					integralRecordPo.setUserId(userId);
					integralRecordPo.setPayStatus("1");
					integralService.insertIntegralRecord(integralRecordPo);
					studyPo.setCreateTime(new Date());
					studyPo.setResType("KJ");
					studyPo.setUserId(userId);
					studyService.insertStudyRecord(studyPo);
					userService.updateUserIntegral(userId,-Long.parseLong(integral));
					return ResUtils.okRes();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 我的学习中,课件列表展示
	 * @param userId
	 * @param resType
	 * @return
	 */
	@RequestMapping(value = "/myMaterialStudy")
	public String myMaterialStudy(HttpServletRequest request, String resType,PageTemp pageTemp){
		try {
			if (super.getUserIdByToken(request.getParameter("userToken")) <= 0 || StringUtils.isBlank(resType)){
				return ResUtils.errRes("404", "请求参数有误");
			}
			Map<String, String>maps = new HashMap<String,String>();
			maps.put("userId", String.valueOf(super.getUserIdByToken(request.getParameter("userToken"))));
			maps.put("resType", resType);
			PageInfo<MyStudyVo> materialVo = studyService.findAll(maps,pageTemp);
			return ResUtils.okRes(materialVo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
		
	}
	
	/**
	 * 资料订单首页(过滤)
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value = "/materialOrderList")
	public String materialOrderList(HttpServletRequest request,PageTemp pageTemp){
		try {
			String userName = StringUtils.stripToEmpty(request.getParameter("userName"));
			String resTitle = StringUtils.stripToEmpty(request.getParameter("resTitle"));
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			String resType = request.getParameter("resType");
			Map<String, String>maps = new HashMap<String,String>();
			maps.put("resType", resType);
			maps.put("userName", userName);
			maps.put("resTitle", resTitle);
			maps.put("beginDate", beginDate);
			maps.put("endDate", endDate);
			PageInfo<IntegralRecordVo> levelPoList = integralService.selectAll(maps,pageTemp);
			return ResUtils.okRes(levelPoList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	
}
