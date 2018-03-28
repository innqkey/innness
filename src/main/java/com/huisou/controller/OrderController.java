package com.huisou.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.DateUtils;
import com.common.JacksonUtil;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.cache.RedisSmsCodeCache;
import com.huisou.constant.ContextConstant;
import com.huisou.po.OrderPo;
import com.huisou.po.RegistPo;
import com.huisou.po.VideoAudioPo;
import com.huisou.service.OrderService;
import com.huisou.service.RegistService;
import com.huisou.service.VideoAudioService;
import com.huisou.vo.OrderVo;
import com.huisou.vo.PageTemp;
import com.huisou.service.UserService;
import com.huisou.po.UserPo;

/** 
* @author 作者 :caoxt 
* @version 创建时间：2018年1月30日
* 类说明 :后台订单管理类
*/
@RestController
@RequestMapping(value = "/order")
public class OrderController extends BaseController{

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RegistService registService;
	
	@Autowired
	private VideoAudioService videoAudioService;
	
	@Autowired
	private RedisSmsCodeCache smsCache;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/orderCourseList")
	public String orderCourseList(HttpServletRequest request,PageTemp pageTemp){
		try {
			Map map = this.getPara();
			PageInfo<OrderVo> OrderVo = orderService.orderCourseList(map, pageTemp);
			return ResUtils.okRes(OrderVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	@RequestMapping(value = "/orderVideoAudioList")
	public String orderVideoAudioList(HttpServletRequest request,PageTemp pageTemp){
		try {
			Map map = this.getPara();
			PageInfo<OrderVo> OrderVo = orderService.orderVideoAudioList(map, pageTemp);
			return ResUtils.okRes(OrderVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	/**
	 * 查询所有的订单课程，或者查询未付费的订单和已支付的订单(userId;payStatus)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findOrderByParamers")
	public String findOrderByParamers(HttpServletRequest request){
		try {
			Map map = this.getPara();
			String userToken = map.get("userToken").toString();
			if(StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			map.put("userId", super.getUserIdByToken(userToken));
			List<OrderVo> list = orderService.findOrderByParamers(map);
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 查询已支付或未支付的课程订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findOrderByPayStatus")
	public String findOrderByPayStatus(HttpServletRequest request){
		try {
			Map map = this.getPara();
			String userToken = map.get("userToken").toString();
			if(StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			map.put("userId", super.getUserIdByToken(userToken));
			List<OrderVo> list = orderService.findOrderByParamers(map);
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 前台取消订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request){
		try {
			String userToken = request.getParameter("userToken");
			String orderId = request.getParameter("orderId");
			if(StringUtils.isBlank(orderId) || StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			OrderPo orderPo = orderService.selectOne(Integer.parseInt(orderId));
			if(orderPo.getUserId() != super.getUserIdByToken(userToken)){
				return ResUtils.errRes("108", "不是当前订单用户操作");
			}
			orderPo.setOrderStatus(ContextConstant.DELETE_STATUS);
			orderService.update(orderPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 报名课程
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/registCourse")
	public String registCourse(HttpServletRequest request){
		try {
			String userToken = request.getParameter("userToken");
//			String code = request.getParameter("code");
			if( StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
//			if( StringUtils.isBlank(code)){
//				return ResUtils.errRes("104", "验证码错误");
//			}
//			String redisCode = smsCache.getSmsCode(userToken);
//			if(!code.equals(redisCode)){
//				return ResUtils.errRes("104", "验证码错误");
//			}
			String registPos = request.getParameter("registPos");
			String courseId = request.getParameter("courseId");
//			String phone = request.getParameter("phone");
			if(StringUtils.isBlank(registPos) || StringUtils.isBlank(courseId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<RegistPo> list = JacksonUtil.toListObject(registPos, RegistPo.class);
			if(list==null || list.size()==0){
				return ResUtils.errRes("102", "请求参数错误");
			}
			  /* String msg = registService.isRegist(list);
			   if(StringUtils.isNotBlank(msg)){
				   return ResUtils.errRes("108","身份证号 "+msg+"已报过名");
			   }*/
			   OrderPo orderPo = orderService.registCourse(super.getUserIdByToken(userToken),Integer.parseInt(courseId),"",list);
			   OrderVo orderVo = new OrderVo();
			   BeanUtils.copyProperties(orderVo, orderPo);
			   orderVo.setOpenid(super.getOpenIdByToken(userToken));
			   return ResUtils.okRes(orderVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 添加视音频订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addVideoAudioOrder")
	public String addVideoAudioOrder(HttpServletRequest request){
		try {
			String resId = request.getParameter("resId");
			String resType = request.getParameter("resType");
			String userToken = request.getParameter("userToken");
			if(StringUtils.isBlank(resId) || StringUtils.isBlank(resType) || StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			VideoAudioPo videoAudioPo = videoAudioService.findOne(Integer.parseInt(resId));
			OrderPo orderPo = new OrderPo();
			Date date = new Date();
			String  orderNo = resType + DateUtils.format(date, DateUtils.YMD) + date.getTime();
			orderPo.setOrderNo(orderNo);
			orderPo.setOrderPay(videoAudioPo.getVideoAudioPrice());
			orderPo.setPayStatus(ContextConstant.PAY_STATUS_NO);
			orderPo.setOrderStatus(ContextConstant.EXIST_STATUS);
			orderPo.setResId(Integer.parseInt(resId));
			orderPo.setResType(resType);
			orderPo.setUserId(super.getUserIdByToken(userToken));
			orderPo.setCreateTime(date);
			orderService.add(orderPo);
			OrderVo orderVo = new OrderVo();
			BeanUtils.copyProperties(orderVo, orderPo);
			orderVo.setOpenid(super.getOpenIdByToken(userToken));
			return ResUtils.okRes(orderVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据订单id获取报名人
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findRegistByorderId")
	public String findRegistByorderId(HttpServletRequest request){
		try {
			String orderId = request.getParameter("orderId");
			if(StringUtils.isBlank(orderId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<RegistPo> list = registService.findRegistByorderId(Integer.parseInt(orderId));
			return ResUtils.okRes(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 导出已支付的课程定单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/exportExcel")
	public String exportExcel(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, String> map = new HashMap<>();
		map.put("payStatus", "2");
		List<OrderVo> orderVos = orderService.successOrderCourseList(map);
		if (null != orderVos && orderVos.size() > 0){
			for(int i = 0; i < orderVos.size(); i++){
				OrderVo orderVo = orderVos.get(i);
				Integer classmateId = orderVo.getClassmateId();
				Integer orderId = orderVo.getOrderId();
				StringBuffer stringBuffer = new StringBuffer();
				if (null != classmateId){
					UserPo findOne = userService.find(classmateId);
					if (null != findOne){
						orderVo.setClassmateName(findOne.getUsername());
						orderVo.setClassmatePhone(findOne.getPhone());
					}
				}
				if (null != orderId){
					List<RegistPo> list = registService.findRegistByorderId(orderId);
					if (null != list && list.size() > 0){
						for (RegistPo registPo : list) {
							stringBuffer.append(registPo.getRegistName());
							stringBuffer.append("/");
							stringBuffer.append(registPo.getCardPhone());
							stringBuffer.append(";");
						}
						int index = stringBuffer.indexOf(";", stringBuffer.length() - 2);
						if (-1 != index){
							stringBuffer.replace(index, stringBuffer.length(), "");
						}
						if (null != stringBuffer){
							orderVo.setCourseApplicants(stringBuffer.toString());
						}
					}
				}
			}
			OutputStream outputStream = null;
			try {
				HSSFWorkbook hssfBook = exportTrainingList(orderVos);
				response.setContentType("application/vnd.ms-excel;charset=utf-8");
				response.setHeader("Content-disposition", "attachment;filename="
						+ new String(("已支付课程订单" + ".xls").getBytes(), "iso-8859-1"));
				outputStream = response.getOutputStream();
				hssfBook.write(outputStream);
				outputStream.flush();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				try {
					outputStream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} 
		return ResUtils.okRes();
	}
	
	public HSSFWorkbook exportTrainingList(List<OrderVo> list) {
		String[] excelHeader = { "订单号", "课程名称", "分享人名字", "分享人手机号","用户名", "报名人数", "订单状态", "生成时间",
				"报名人"};
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("已支付课程订单");
		// 设置列的宽度
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFRow row = sheet.createRow((int) 0);
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
		}
		sheet.autoSizeColumn(4, true);
		for (int i = 0; i < list.size(); i++) {
			OrderVo orderVo = list.get(i);
			row = sheet.createRow(i + 1);
			HSSFCell createCell = row.createCell(0);
			createCell.setCellValue(orderVo.getOrderNo());
			createCell.setCellStyle(style);

			HSSFCell createCell1 = row.createCell(1);
			createCell1.setCellValue(orderVo.getResTitle());
			createCell1.setCellStyle(style);

			HSSFCell createCell2 = row.createCell(2);
			createCell2.setCellValue(orderVo.getClassmateName());
			createCell2.setCellStyle(style);

			HSSFCell createCell3 = row.createCell(3);
			createCell3.setCellValue(orderVo.getClassmatePhone());
			createCell3.setCellStyle(style);

			HSSFCell createCell4 = row.createCell(4);
			createCell4.setCellValue(orderVo.getUserName());
			createCell4.setCellStyle(style);

			HSSFCell createCell5 = row.createCell(5);
			createCell5.setCellValue(orderVo.getOrderPersonNum());
			createCell5.setCellStyle(style);

			HSSFCell createCell6 = row.createCell(6);
			createCell6.setCellValue(orderVo.getPayStatusName());
			createCell6.setCellStyle(style);

			HSSFCell createCell7 = row.createCell(7);
			createCell7.setCellValue(DateUtils.format(orderVo.getCreateTime(), DateUtils.Y_M_D_HMS));
			createCell7.setCellStyle(style);
			
			HSSFCell createCell8 = row.createCell(8);
			createCell8.setCellValue(orderVo.getCourseApplicants());
			createCell8.setCellStyle(style);
			
		}
		sheet.setColumnWidth(0, 256 * 40 );
		sheet.setColumnWidth(1, 256 * 30 + 184);
		sheet.setColumnWidth(2, 256 * 20 + 184);
		sheet.setColumnWidth(3, 256 * 20 + 184);
		sheet.setColumnWidth(4, 256 * 20 + 184);
		sheet.setColumnWidth(5, 256 * 14 );
		sheet.setColumnWidth(6, 256 * 14 );
		sheet.setColumnWidth(7, 256 * 30 + 184);
		sheet.autoSizeColumn(8, true);
		return wb;
	}
}
