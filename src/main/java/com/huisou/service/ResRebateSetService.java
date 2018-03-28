package com.huisou.service;

import java.util.List;

import com.huisou.po.ResRebateSetPo;
import com.huisou.vo.ResRebateSetVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月22日 下午4:00:57 
* 类说明 
*/
public interface ResRebateSetService {

	List<ResRebateSetVo> findAll(Integer resId,String resType);

	void updateOne(ResRebateSetPo resRebateSetPo);

	void addOne(ResRebateSetPo resRebateSetPo);

}
