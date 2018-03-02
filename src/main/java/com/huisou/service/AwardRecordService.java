package com.huisou.service;

import java.util.Date;

import com.github.pagehelper.PageInfo;
import com.huisou.po.AwardRecordPo;
import com.huisou.vo.AwardRecordVo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 下午1:53:09 
* 类说明 
*/
public interface AwardRecordService {

	Integer add(AwardRecordPo awardRecordPo);

	PageInfo<AwardRecordVo> search(Integer resId, String resType, PageTemp pageTemp);

	PageInfo<AwardRecordVo> findAll(String nickname, String phone, Date startDate, Date endDate, PageTemp pageTemp);

	AwardRecordPo findOne(String outTradeNo);

	void update(AwardRecordPo awardRecordPo);

}
