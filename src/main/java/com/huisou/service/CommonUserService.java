package com.huisou.service;

import java.util.List;

import com.huisou.po.CommonUserPo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年2月11日 上午11:23:41 
* 类说明 
*/
public interface CommonUserService {

	void add(CommonUserPo commonUserPo);

	void update(CommonUserPo commonUserPo);

	List<CommonUserPo> findAll(PageTemp pageTemp);

	CommonUserPo search(String username,String password);

}
