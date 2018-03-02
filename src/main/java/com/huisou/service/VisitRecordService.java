package com.huisou.service;

import com.huisou.po.VisitRecordPo;
import com.huisou.vo.VisitRecordVo;

/** 
* @author qinkai 
* @date 2018年2月10日
*/

public interface VisitRecordService {

	/**
	 * 昨日访问统计
	 * @return
	 */
	VisitRecordVo select();
	
	/**
	 * 保存一条记录
	 * @param visitRecordVo
	 */
	void insertOne(VisitRecordPo visitRecordVo);

}
