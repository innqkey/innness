package com.huisou.service;

import java.util.Date;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.IntegralRecordPo;
import com.huisou.vo.IntegralRecordVo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月31日 下午7:17:22 
* 类说明 
*/
public interface IntegralRecordService {

	PageInfo<IntegralRecordVo> search(String nickname, String phone, Date startDate, Date endDate,PageTemp pageTemp);

	/**
	 * 课件下载消耗积分时，保存积分记录
	 * @param integralRecordPo
	 */
	void insertIntegralRecord(IntegralRecordPo integralRecordPo);

	/**
	 * 资料订单
	 * @param maps
	 * @param pageTemp
	 * @return
	 */
	PageInfo<IntegralRecordVo> selectAll(Map<String, String> maps, PageTemp pageTemp);

	/**
	 * 根据integralRecordId 更新 resId
	 * @param integralRecordId
	 * @param userId
	 */
	void updateIntegralRecord(Integer integralRecordId, Integer resId);
	
}
