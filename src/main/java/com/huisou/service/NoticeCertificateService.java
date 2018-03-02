package com.huisou.service;

import java.util.List;

import com.huisou.po.NoticeCertificatePo;

/** 
* @author qinkai 
* @date 2018年2月1日
*/

public interface NoticeCertificateService {

	/**
	 * 根据id查找一个通知
	 * @param id
	 * @return
	 */
	NoticeCertificatePo findOne(Integer id);

	/**
	 * 更新一个通知
	 * @param po
	 */
	void updateOne(NoticeCertificatePo po);

	/**
	 * 保存一个通知
	 * @param po
	 */
	void addOne(NoticeCertificatePo po);

	/**
	 * 根据type查看详情
	 * @param type
	 * @return
	 */
	NoticeCertificatePo find(String type);

	List<NoticeCertificatePo> findType(String type);

}
