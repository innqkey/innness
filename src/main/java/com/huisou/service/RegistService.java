package com.huisou.service;

import java.util.List;

import com.huisou.po.RegistPo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 下午8:41:36 
* 类说明 
*/
public interface RegistService {

	Integer add(RegistPo registPo);

	String isRegist(List<RegistPo> list);

	List<RegistPo> findRegistByorderId(Integer orderId);

	RegistPo selectByUserId(Integer userIdByToken);

}
