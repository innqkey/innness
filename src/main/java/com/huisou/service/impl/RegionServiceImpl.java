package com.huisou.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.RegionPoMapper;
import com.huisou.po.RegionPo;
import com.huisou.service.RegionService;
import com.huisou.vo.ChildRegionVo;
import com.huisou.vo.RegionVo;

@Service
public class RegionServiceImpl implements RegionService {
	@Autowired
	private RegionPoMapper regionPoMapper;
	@Override
	public List<RegionPo> findAllProvince() {
		RegionPo regionPo = new RegionPo();
		regionPo.setGrade(1);
		List<RegionPo> list = regionPoMapper.select(regionPo);
		return list;
	}
	@Override
	public List<RegionPo> findCityOrAreaByParentId(Integer regionId) {
		RegionPo regionPo = new RegionPo();
		regionPo.setParent(regionId);
		List<RegionPo> list = regionPoMapper.select(regionPo);
		return list;
	}
	@Override
	public RegionPo findRegionById(String valueOf) {
		if(StringUtils.isNotBlank(valueOf)&&StringUtils.isNumeric(valueOf)){
			RegionPo regionPo = new RegionPo();
			regionPo.setId(Integer.valueOf(valueOf));
			List<RegionPo> select = regionPoMapper.select(regionPo);
			if(null!=select&&select.size()>0){
				return select.get(0);
			}
		}
		return null;
	}
	@Override
	public List<RegionVo> findAllRegion() {
		List<RegionVo> list = new ArrayList<>();
		RegionPo regionPo = new RegionPo();
		regionPo.setGrade(1);
		List<RegionPo> provinceList = regionPoMapper.select(regionPo);
		if(provinceList.size()!=0){
			for (RegionPo po : provinceList) {
				RegionVo provinceVo = new RegionVo();
				try {
					BeanUtils.copyProperties(provinceVo, po);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				RegionPo province = new RegionPo();
				province.setParent(po.getId());
				List<RegionPo> cityList = regionPoMapper.select(province);
				List<ChildRegionVo> childRegionVoList = new ArrayList<>();
				if(cityList.size()!=0){
					for (RegionPo po2 : cityList) {
						ChildRegionVo cityVo = new ChildRegionVo();
						try {
							BeanUtils.copyProperties(cityVo, po2);
						} catch (IllegalAccessException | InvocationTargetException e) {
							e.printStackTrace();
						}
						RegionPo city = new RegionPo();
						city.setParent(po2.getId());
						List<RegionPo> areaList = regionPoMapper.select(city);
						if(areaList.size()!=0){
							cityVo.setChild(areaList);
						}
						childRegionVoList.add(cityVo);
					}
					provinceVo.setChild(childRegionVoList);
				}
				list.add(provinceVo);
			}
		}
		return list;
	}

}
