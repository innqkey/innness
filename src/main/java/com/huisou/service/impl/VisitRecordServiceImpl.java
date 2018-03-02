package com.huisou.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.DateUtil;
import com.common.DateUtils;
import com.huisou.mapper.OrderPoMapper;
import com.huisou.mapper.VisitRecordPoMapper;
import com.huisou.po.VisitRecordPo;
import com.huisou.service.VisitRecordService;
import com.huisou.vo.VisitRecordVo;

/** 
* @author qinkai 
* @date 2018年2月10日
*/

@Service
public class VisitRecordServiceImpl implements VisitRecordService{

	@Autowired
	private VisitRecordPoMapper visitRecordPoMapper;
	@Autowired
	private OrderPoMapper orderPoMapper;
	
	@Override
	public VisitRecordVo select() {
		String beginDate = DateUtils.format(DateUtil.addDays(-1), DateUtils.Y_M_D);
		String endDate = DateUtils.format(new Date(), DateUtils.Y_M_D);
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("beginDate", beginDate);
		maps.put("endDate", endDate);
		List<VisitRecordVo> visitRecordVos = visitRecordPoMapper.select(maps);
		VisitRecordVo vo = new VisitRecordVo();
		vo.setLastDayDlCount(0);
		vo.setLastDayOrderCount(0);
		vo.setLastDaySpCount(0);
		vo.setLastDayYpCount(0);
		vo.setLastDayZlCount(0);
		if (null != visitRecordVos && visitRecordVos.size() > 0){
			for (int i = 0; i < visitRecordVos.size(); i++){
				if (visitRecordVos.get(i).getVisitType().equals("YP")){
					vo.setLastDayYpCount(visitRecordVos.get(i).getCount());
				} else if (visitRecordVos.get(i).getVisitType().equals("SP")){
					vo.setLastDaySpCount(visitRecordVos.get(i).getCount());
				} else if (visitRecordVos.get(i).getVisitType().equals("DL")){
					vo.setLastDayDlCount(visitRecordVos.get(i).getCount());
				} else if (visitRecordVos.get(i).getVisitType().equals("ZL")){
					vo.setLastDayZlCount(visitRecordVos.get(i).getCount());
				}
			}
		}
		Integer orderCount = orderPoMapper.getSuccessCount(maps);
		if (null != orderCount && orderCount > 0){
			vo.setLastDayOrderCount(orderCount);
		}
		return vo;
	}

	@Override
	public void insertOne(VisitRecordPo recordPo) {
		visitRecordPoMapper.insertSelective(recordPo);
	}
}
