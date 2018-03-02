package com.huisou.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.RotationImagePo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年2月9日 上午9:15:05 
* 类说明 
*/
public interface RotationImageService {

	void update(RotationImagePo rotationImagePo);

	void add(RotationImagePo rotationImagePo);

	PageInfo<RotationImagePo> search(PageTemp pageTemp,Map map);

	List<RotationImagePo> findByparames(Map map);

	RotationImagePo selectOne(Integer rotationImageId);

}
