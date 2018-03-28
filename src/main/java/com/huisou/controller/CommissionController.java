package com.huisou.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.ExcelUtils;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.DictConConstant;
import com.huisou.po.UniversalPo;
import com.huisou.po.UserAccountPo;
import com.huisou.po.UserBankPo;
import com.huisou.po.UserPo;
import com.huisou.po.WithdrawalRecordPo;
import com.huisou.service.UserAccountService;
import com.huisou.service.UserBankService;
import com.huisou.service.UserService;
import com.huisou.service.WithdrawalRecordService;
import com.huisou.vo.MemberWithdrawVo;
import com.huisou.vo.PageTemp;

/**
* 类说明： 用于前后台的佣金的展示和管理
* @author 
* @version 创建时间：2018年3月22日 下午4:53:20
* 
*/
@RestController
@RequestMapping("/commission")
public class CommissionController extends BaseController{
	
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private WithdrawalRecordService withdrawalRecordService;
	@Autowired
	private UserBankService userBankService;
	@Autowired
	private UserService userService;
	
	
	/**
	 * 通过userToken来查询出佣金
	 * @param userToken
	 * @return
	 */
	@RequestMapping("/showCommission")
	public String displayCommission(String userToken) {
		if (StringUtils.isBlank(userToken)) {
			return ResUtils.execRes("参数不能空");
		}
		
		int userId = getUserIdByToken(userToken);
		UserAccountPo userAccountPo = userAccountService.getByUserId(userId);
		return ResUtils.okRes(userAccountPo);
	}
	
	/**
	 *  提现的判断   如果：提现处于审核状态，就返回提现历史
	 *  如果不是，就判断是否有用户的账户信息，如果没有就返回为空
	 * @param userToken
	 * @return
	 */
	@RequestMapping("/withdrawOp")
	public String  withdrawOperation(String userToken) {
		if (StringUtils.isBlank(userToken)) {
			return ResUtils.execRes("参数不能空");
		}
		
		Boolean isOrNoExist;
		try {
			int userId = getUserIdByToken(userToken);
			isOrNoExist = withdrawalRecordService.findNoWithdrawAuditing(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResUtils.execRes(e);
		}
		return ResUtils.okRes(isOrNoExist);
		
	}
	
	/**
	 * 展示提现记录
	 * @param userId
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping("/withdrawHistory")
	public String withdrawHistory(String userToken,PageTemp pageTemp) {
		if (StringUtils.isBlank(userToken)) {
			return ResUtils.execRes("参数不能为空");
		}
		int userId = getUserIdByToken(userToken);
		PageInfo<WithdrawalRecordPo> result = withdrawalRecordService.findAllByUserId(userId,pageTemp);
		return ResUtils.okRes(result);
	}
	
	@RequestMapping("/getBankInfo")
	public String getBankInfo(String userToken) {
		if (StringUtils.isBlank(userToken)) {
			return ResUtils.execRes("参数不能为空");
		}
		int userId = getUserIdByToken(userToken);
		UserPo po = userService.find(userId);
		List<UserBankPo> userBankPos = userBankService.getByUserId(userId);
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("banks", userBankPos);
		hashMap.put("user",po.getUsername());
		return ResUtils.okRes(hashMap);
	}
	
	
	
	/**
	 * 提交提现的申请， 同时包含了支付宝或者银行卡的修改
	 * @param withdrawalRecordPo
	 * @param userBankPo
	 * @return
	 */
	@RequestMapping(value="/submiWithdraw",method=RequestMethod.POST)
	public String submiWithdraw(String userToken,WithdrawalRecordPo withdrawalRecordPo,UserBankPo userBankPo) {
		//数据校验
		if (StringUtils.isBlank(userToken) || withdrawalRecordPo.getWithdrawAccount() == null
				|| userBankPo.getUserBankType() == null && userBankPo.getUserBankAccount() == null) {
			return ResUtils.execRes("参数错误");
		}
		
		int userId = getUserIdByToken(userToken);
		userBankPo.setUserId(userId);
		withdrawalRecordPo.setUserId(userId);
		userBankService.saveAndUpdate(userBankPo,withdrawalRecordPo);
		return ResUtils.okRes();
		
	}
	
	
	// 后台的  操作
	
	/**
	 * 用于所有的会员提交的申请表展示
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping("showVipWdRecord")
	public String showVipWithdraw(PageTemp pageTemp,String username,String phone,Integer status) {
		PageInfo<UniversalPo> result;
		try {
			result = withdrawalRecordService.showVipWithdrawRecord(username,phone,status,pageTemp);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
		return ResUtils.okRes(result);
	}
	
	/**
	 * 用于Execl的导出
	 * 2个导出是一个接口
	 * ，根据条件是否为空判断选中数据，还是搜索的数据
	 * @param recordIds
	 * @param username
	 * @param phone
	 * @param status
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping("/exportExecl")
	public String exportExecl(HttpServletResponse response,@RequestParam(value="recordIds[]",required = false) List<Integer> recordIds,String username,String phone,Integer status) {
		ServletOutputStream out = null;
		try {
			List<MemberWithdrawVo> recoredList = null;
			if (recordIds != null && recordIds.size() > 0) {
				recoredList = withdrawalRecordService.listByRecords(recordIds);
			}
			
			if (StringUtils.isNotBlank(username) || StringUtils.isNotBlank(phone)
					|| status != null) {
				recoredList = withdrawalRecordService.showVipWithdrawRecordNoPaging(username,phone,status);
			}
			response.setContentType("application/vnd.ms-excel");
		    response.setHeader("content-disposition", "attachment;filename=" +  new String("用户提现记录.xls".getBytes("utf-8"), "ISO-8859-1"));
			if (recoredList != null && recoredList.size() > 0) {
				Map<String, String> convertMap = new LinkedHashMap<String, String>();
				convertMap.put("会员id", "userid");
				convertMap.put("会员名称", "username");
				convertMap.put("会员昵称", "nickname");
				convertMap.put("会员手机号", "phone");
				convertMap.put("会员类别", "membersetname");
				convertMap.put("提现金额", "withdrawaccount");
				convertMap.put("银行卡类型", "userbanktype");
				convertMap.put("卡号", "userbankaccount");
				convertMap.put("银行名称", "userbankname");
				convertMap.put("提现状态", "recorestatus");
				convertMap.put("申请时间", "createtime");
				
				Map<String, Object> fillExcelData = ExcelUtils.fillExcelData(convertMap, recoredList);
				  // 获取头信息  
		        List<String> heads = (List<String>) fillExcelData.get("heads");  
		        // 获取数据信息  
		        List<List<String>> dataList = (List<List<String>>) fillExcelData.get("dataList");  
		        // 创建Excel文件  
		        HSSFWorkbook workbook = ExcelUtils.createExcelFile("客服提现信息", heads,dataList);  
		        // 输出Excel文件  
		         out = response.getOutputStream();
		        workbook.write(out);
		        out.flush();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes("导出异常");
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ResUtils.okRes("");
	}
	
	
	
	
	
	
	/**
	 * 用于展示提现先账户的银行卡信息
	 * @param recordId
	 * @return
	 */
	@RequestMapping("/recordInfo")
	public String showRecordBankInfo(Integer recordId) {
		if (recordId == null) {
			return ResUtils.execRes("参数不能为空");
		}
		WithdrawalRecordPo po = withdrawalRecordService.getOneByRecordId(recordId);
		po.setUserBankType(DictConConstant.getDicName("BankType", po.getUserBankType()));
		return ResUtils.okRes(po);
	}
	
	/**
	 * 可以是批量提现
	 * 提现的动作，可以是拒绝，可以是 确认。
	 * @param recordId
	 * @param status
	 * @return
	 */
	@RequestMapping("/withdraw")
	public String withdraw(HttpServletRequest request,@RequestParam(value="recordIds[]",required = false) List<Integer> recordIds,Integer status) {
		if (recordIds == null || recordIds.size() < 1 
				|| status == null || status > 3.5 || status < 1.5) {
			return ResUtils.execRes("参数错误");
		}
		
		try {
			withdrawalRecordService.withdrawOpeartion(recordIds,status);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
		return ResUtils.okRes();
	}
	
}
