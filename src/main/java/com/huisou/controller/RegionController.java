package com.huisou.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.huisou.po.RegionPo;
import com.huisou.service.RegionService;
import com.huisou.vo.RegionVo;

@RestController
@RequestMapping(value = "/region")
public class RegionController {

	@Autowired
	private RegionService regionService;
	/**
	 * 用于返回对应的省市区的数据
	 */
	@RequestMapping(value = "/getregion")
	public String getRegion(HttpServletRequest request, String flag,
			Integer regionId) {
		List<RegionPo> result = null;
		if (flag == null) {
			result = regionService.findAllProvince();
			return ResUtils.okRes(result);
		}
		// 为1就是返回所有的省
		if (flag.equals("1") || regionId == null) {
			result = regionService.findAllProvince();
		} else if (flag.equals("2") && regionId != null) {
			// 为2的话根据id查询对应的市或者区。
			result = regionService.findCityOrAreaByParentId(regionId);
		} else if (flag.equals("3") && regionId != null) {
			RegionPo regionById = regionService.findRegionById(String.valueOf(regionId));
			return ResUtils.okRes(regionById);
		}

		return ResUtils.okRes(result);

	}
	
	/**
	 * 按照格式返回所有的省市区详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAllRegion")
	public String getAllRegion(HttpServletRequest request){
		try {
			List<RegionVo> list = null;
		    list = regionService.findAllRegion();
			return ResUtils.okRes(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
