package com.huisou.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huisou.po.AwardSetPo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年2月1日 上午9:41:05 
* 类说明 
*/
public interface AwardSetService{

	void add(AwardSetPo awardSetPo);

	void update(AwardSetPo awardSetPo);

	PageInfo<AwardSetPo> search(PageTemp pageTemp);

	AwardSetPo findOne(Integer awardSetId);

	List<AwardSetPo> findAllByStatus(String existStatus);

}
