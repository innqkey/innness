package com.huisou.service;

import java.util.Date;

import com.github.pagehelper.PageInfo;
import com.huisou.po.PayRecordPo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.PayRecordVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月31日 上午10:45:31 
* 类说明 
*/
public interface PayRecordService {

	PageInfo<PayRecordVo> search(String nickname, String phone, Date startDate, Date endDate, PageTemp pageTemp);
	Integer add(PayRecordPo payRecordPo);
}
