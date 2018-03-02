package com.huisou.service;

import java.util.List;

import com.huisou.po.RegionPo;
import com.huisou.vo.RegionVo;

public interface RegionService {

	List<RegionPo> findAllProvince();

	List<RegionPo> findCityOrAreaByParentId(Integer regionId);

	RegionPo findRegionById(String valueOf);

	List<RegionVo> findAllRegion();

}
