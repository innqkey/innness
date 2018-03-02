package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.NoticeCertificatePoMapper;
import com.huisou.po.NoticeCertificatePo;
import com.huisou.service.NoticeCertificateService;

/** 
* @author qinkai 
* @date 2018年2月1日
*/
@Service
public class NoticeCertificateServiceImpl implements NoticeCertificateService{
	@Autowired
	private NoticeCertificatePoMapper poMapper;

	@Override
	public NoticeCertificatePo findOne(Integer id) {
		return poMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateOne(NoticeCertificatePo po) {
		poMapper.updateByPrimaryKeySelective(po);
	}

	@Override
	public void addOne(NoticeCertificatePo po) {
		poMapper.insertSelective(po);
	}

	@Override
	public NoticeCertificatePo find(String type) {
		return poMapper.find(type);
	}

	@Override
	public List<NoticeCertificatePo> findType(String type) {
		List<NoticeCertificatePo> list = poMapper.findType(type);
		return list;
	}

}
