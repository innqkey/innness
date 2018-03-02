package com.huisou.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.AwardRecordPoMapper;
import com.huisou.po.AwardRecordPo;
import com.huisou.service.AwardRecordService;
import com.huisou.vo.AwardRecordVo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 下午1:53:36 
* 类说明 
*/
@Service
public class AwardRecordServiceImpl implements AwardRecordService{

	@Autowired
	private AwardRecordPoMapper awardRecordPoMapper;
	
	@Override
	public Integer add(AwardRecordPo awardRecordPo) {
		// TODO Auto-generated method stub
		awardRecordPoMapper.insertSelective(awardRecordPo);
		 return awardRecordPo.getAwardRecordId();
	}

	@Override
	public PageInfo<AwardRecordVo> search(Integer resId, String resType, PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<AwardRecordVo> list = awardRecordPoMapper.search(resId,resType);
		return new PageInfo<AwardRecordVo>(list);
	}

	@Override
	public PageInfo<AwardRecordVo> findAll(String nickname, String phone, Date startDate, Date endDate,
			PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<AwardRecordVo> list = awardRecordPoMapper.findAll(nickname,phone,startDate,endDate,ContextConstant.PAY_STATUS_SUCCESS);
		for (AwardRecordVo awardRecordVo : list) {
			if("SP".equals(awardRecordVo.getResType().trim())){
				awardRecordVo.setVideoAudioTypeName("视频");
			}else if("YP".equals(awardRecordVo.getResType().trim())){
				awardRecordVo.setVideoAudioTypeName("音频");
			}
		}
		return new PageInfo<AwardRecordVo>(list);
	}

	@Override
	public AwardRecordPo findOne(String awardNo) {
		
		AwardRecordPo awardRecordPo = awardRecordPoMapper.findOne(awardNo);
		
		return awardRecordPo;
	}

	@Override
	public void update(AwardRecordPo awardRecordPo) {
		// TODO Auto-generated method stub
		awardRecordPoMapper.updateByPrimaryKeySelective(awardRecordPo);
	}

}
