package com.huisou.service;

import java.util.List;

import com.huisou.po.MemberSetPo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月22日 下午3:58:29 
* 类说明 
*/
public interface MemberSetService {

	List<MemberSetPo> findAll();

	void updateOne(MemberSetPo memberSetPo);

	void addOne(MemberSetPo memberSetPo);

	MemberSetPo findOne(Integer memberSetId);

}
