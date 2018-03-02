package com.huisou.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.IntegralRecordPoMapper;
import com.huisou.po.IntegralRecordPo;
import com.huisou.service.IntegralRecordService;
import com.huisou.vo.IntegralRecordVo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月31日 下午7:17:37 
* 类说明 
*/
@Service
public class IntegralRecordServiceImpl implements IntegralRecordService{
	
	@Autowired
	private IntegralRecordPoMapper integralRecordPoMapper;

	@Override
	public PageInfo<IntegralRecordVo> search(String nickname, String phone, Date startDate, Date endDate,PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<IntegralRecordVo> list = integralRecordPoMapper.search(nickname,phone,startDate,endDate);
		return new PageInfo<IntegralRecordVo>(list);
	}

	@Override
	public void insertIntegralRecord(IntegralRecordPo integralRecordPo) {
		integralRecordPoMapper.insertSelective(integralRecordPo);
	}

	@Override
	public PageInfo<IntegralRecordVo> selectAll(Map<String, String> maps, PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<IntegralRecordVo> list = integralRecordPoMapper.selecAll(maps);
		return new PageInfo<IntegralRecordVo>(list);
	}
}
